package be.flexteam.flex_imdb.rest.v1.controller;

import be.flexteam.flex_imdb.domain.exception.DeleteException;
import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.port.input.ActorServicePort;
import be.flexteam.flex_imdb.domain.port.input.MovieServicePort;
import be.flexteam.flex_imdb.flex_imdb.rest.v1.api.ActorApi;
import be.flexteam.flex_imdb.flex_imdb.rest.v1.api.ActorsApi;
import be.flexteam.flex_imdb.flex_imdb.rest.v1.model.ActorDTO;
import be.flexteam.flex_imdb.flex_imdb.rest.v1.model.MovieDTO;
import be.flexteam.flex_imdb.rest.v1.mapper.ActorDTOMapper;
import be.flexteam.flex_imdb.rest.v1.mapper.MovieDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ActorController implements ActorApi, ActorsApi {

    private final ActorServicePort actorServicePort;
    private final MovieServicePort movieServicePort;
    private final ActorDTOMapper actorDTOMapper;
    private final MovieDTOMapper movieDTOMapper;

    @Override
    public ResponseEntity<Void> addMoviesForActor(Integer actorId, List<Integer> movieIds) {
        try {
            for (Integer movieId : movieIds) {
                actorServicePort.addMovieForActor(movieId, actorId);
            }
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).build();
        } catch (NotFoundException e) {
            log.error("Error occured", e);
            return ResponseEntity.notFound().build();
        } catch (SaveException e) {
            log.error("Error occured",e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> deleteActor(Integer actorId) {
        try {
            actorServicePort.delete(actorId);
            return ResponseEntity.noContent().build();
        } catch (DeleteException e) {
            log.error("Error occured",e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ActorDTO> getActor(Integer actorId) {
        try {
            return ResponseEntity.ok(actorDTOMapper.map(actorServicePort.get(actorId)));
        } catch (NotFoundException e) {
            log.error("Error occured",e);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<MovieDTO>> getMoviesForActor(Integer actorId) {
        try {
            return ResponseEntity.ok(movieDTOMapper.map(movieServicePort.findMoviesForActor(actorId)));
        } catch (NotFoundException e) {
            log.error("Error occured",e);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<ActorDTO>> getActors() {
        List<ActorDTO> actors = actorDTOMapper.map(actorServicePort.getAll());
        if (!actors.isEmpty()) {
            return ResponseEntity.ok(actors);
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<ActorDTO> saveActor(ActorDTO actorDTO) {
        try {
            ActorDTO actorCreated = actorDTOMapper.map(actorServicePort.save(actorDTOMapper.map(actorDTO)));
            return ResponseEntity
                    .created(
                            ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{actorId}")
                                    .buildAndExpand(actorCreated.getId()).toUri())
                    .body(actorCreated);
        } catch (SaveException e) {
            log.error("Error occured",e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ActorDTO> updateActor(Integer actorId, ActorDTO actorDTO) {
        if (!Objects.equals(actorId, actorDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "actorId is not the same");
        }

        try {
            return ResponseEntity.ok(actorDTOMapper.map(actorServicePort.update(actorDTOMapper.map(actorDTO), false)));
        } catch (NotFoundException e) {
            log.error("Error occured",e);
            return ResponseEntity.notFound().build();
        } catch (SaveException e) {
            log.error("Error occured",e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}