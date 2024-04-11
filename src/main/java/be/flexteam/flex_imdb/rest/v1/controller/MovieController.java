package be.flexteam.flex_imdb.rest.v1.controller;

import be.flexteam.flex_imdb.domain.exception.DeleteException;
import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.port.input.ActorServicePort;
import be.flexteam.flex_imdb.domain.port.input.MovieServicePort;
import be.flexteam.flex_imdb.flex_imdb.rest.v1.api.MoviesApi;
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
public class MovieController implements MoviesApi {

    private final ActorServicePort actorServicePort;
    private final MovieServicePort movieServicePort;
    private final ActorDTOMapper actorDTOMapper;
    private final MovieDTOMapper movieDTOMapper;

    @Override
    public ResponseEntity<Void> addActorsForMovie(Integer movieId, List<Integer> actorIds) {
        try {
            for (Integer actorId : actorIds) {
                movieServicePort.addActorForMovie(actorId, movieId);
            }
            return ResponseEntity.noContent().location(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).build();
        } catch (NotFoundException e) {
            log.error("Error occured",e);
            return ResponseEntity.notFound().build();
        } catch (SaveException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> deleteMovie(Integer movieId) {
        try {
            movieServicePort.delete(movieId);
            return ResponseEntity.noContent().build();
        } catch (DeleteException e) {
            log.error("Error occured",e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<ActorDTO>> getActorsForMovie(Integer movieId) {
        try {
            return ResponseEntity.ok(actorDTOMapper.map(actorServicePort.findActorsForMovie(movieId)));
        } catch (NotFoundException e) {
            log.error("Error occured",e);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<MovieDTO> getMovie(Integer movieId) {
        try {
            return ResponseEntity.ok(movieDTOMapper.map(movieServicePort.get(movieId)));
        } catch (NotFoundException e) {
            log.error("Error occured",e);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<MovieDTO>> getMovies() {
        List<MovieDTO> movies = movieDTOMapper.map(movieServicePort.getAll());
        if (!movies.isEmpty()) {
            return ResponseEntity.ok(movies);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<MovieDTO> saveMovie(MovieDTO movieDTO) {
        try {
            MovieDTO movieCreated = movieDTOMapper.map(movieServicePort.save(movieDTOMapper.map(movieDTO)));
            return ResponseEntity
                    .created(
                            ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{movieId}")
                                    .buildAndExpand(movieCreated.getId()).toUri())
                    .body(movieCreated);
        } catch (SaveException e) {
            log.error("Error occured",e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<MovieDTO> updateMovie(Integer movieId, MovieDTO movieDTO) {
        if(!Objects.equals(movieId, movieDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"movieId is not the same");
        }

        try {
            return ResponseEntity.ok(movieDTOMapper.map(movieServicePort.update(movieDTOMapper.map(movieDTO),false)));
        } catch (NotFoundException e) {
            log.error("Error occured",e);
            return ResponseEntity.notFound().build();
        } catch (SaveException e) {
            log.error("Error occured",e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}