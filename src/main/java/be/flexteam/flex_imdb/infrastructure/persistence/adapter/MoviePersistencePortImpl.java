package be.flexteam.flex_imdb.infrastructure.persistence.adapter;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Movie;
import be.flexteam.flex_imdb.domain.port.output.MoviePersistencePort;
import be.flexteam.flex_imdb.infrastructure.persistence.entity.ActorEntity;
import be.flexteam.flex_imdb.infrastructure.persistence.entity.MovieEntity;
import be.flexteam.flex_imdb.infrastructure.persistence.mapper.MovieEntityMapper;
import be.flexteam.flex_imdb.infrastructure.persistence.repository.ActorEntityRepository;
import be.flexteam.flex_imdb.infrastructure.persistence.repository.MovieEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MoviePersistencePortImpl extends GenericPersistencePortImpl<MovieEntity,MovieEntityRepository,Movie,Integer> implements MoviePersistencePort {
    private final ActorEntityRepository actorEntityRepository;
    private final MovieEntityMapper movieMapper;

    public MoviePersistencePortImpl(MovieEntityRepository movieEntityRepository, ActorEntityRepository actorEntityRepository, MovieEntityMapper movieMapper) {
        super(movieEntityRepository);
        this.actorEntityRepository = actorEntityRepository;
        this.movieMapper = movieMapper;
    }


    @Override
    protected MovieEntity map(Movie model) {
        return movieMapper.map(model);
    }

    @Override
    protected Movie map(MovieEntity entity) {
        return movieMapper.map(entity);
    }

    @Override
    protected void partialUpdate(Movie model, MovieEntity entity) {
        movieMapper.partialUpdate(model, entity);
    }

    @Override
    protected Integer getId(MovieEntity entity) {
        return entity.getId();
    }

    @Override
    public void addActorForMovie(Integer actorId, Integer movieId) {
        try {
            if(!jpaRepository.existsById(movieId)){
                throw new NotFoundException("movieId=" + movieId + " cannot be found");
            }
            if(!actorEntityRepository.existsById(actorId)){
                throw new NotFoundException("actorId=" + movieId + " cannot be found");
            }
            jpaRepository.addActorForMovie(actorId, movieId);
        }catch (IllegalArgumentException e){
            throw new NotFoundException("movieId/actorId is null",e);
        }
    }

    @Override
    public Set<Movie> findMoviesForActor(Integer actorId) {
        try {
            return actorEntityRepository
                    .findById(actorId)
                    .map(ActorEntity::getMovies)
                    .map(movieMapper::map)
                    .orElseThrow(() -> new NotFoundException("Actor with id=" + actorId + " cannot be found"));
        }catch (IllegalArgumentException e){
            throw new NotFoundException("Actor id is null",e);
        }
    }
}