package be.flexteam.flex_imdb.infrastructure.persistence.adapter;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Actor;
import be.flexteam.flex_imdb.domain.port.output.ActorPersistencePort;
import be.flexteam.flex_imdb.infrastructure.persistence.entity.ActorEntity;
import be.flexteam.flex_imdb.infrastructure.persistence.entity.MovieEntity;
import be.flexteam.flex_imdb.infrastructure.persistence.mapper.ActorEntityMapper;
import be.flexteam.flex_imdb.infrastructure.persistence.repository.ActorEntityRepository;
import be.flexteam.flex_imdb.infrastructure.persistence.repository.MovieEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ActorPersistencePortImpl extends GenericPersistencePortImpl<ActorEntity,ActorEntityRepository,Actor,Integer> implements ActorPersistencePort {

    private final ActorEntityMapper actorEntityMapper;
    private final MovieEntityRepository movieEntityRepository;

    public ActorPersistencePortImpl(ActorEntityRepository actorEntityRepository, ActorEntityMapper actorEntityMapper, MovieEntityRepository movieEntityRepository) {
        super(actorEntityRepository);
        this.actorEntityMapper = actorEntityMapper;
        this.movieEntityRepository = movieEntityRepository;
    }

    @Override
    protected ActorEntity map(Actor model) {
        return actorEntityMapper.map(model);
    }

    @Override
    protected Actor map(ActorEntity entity) {
        return actorEntityMapper.map(entity);
    }

    @Override
    protected void partialUpdate(Actor model, ActorEntity entity) {
        actorEntityMapper.partialUpdate(model, entity);
    }

    @Override
    protected Integer getId(ActorEntity entity) {
        return entity.getId();
    }


    @Override
    public void addMovieForActor(Integer movieId, Integer actorId) throws NotFoundException, SaveException {
        try {
            if(!jpaRepository.existsById(actorId)){
               throw new NotFoundException("actorId=" + movieId + " cannot be found");
            }
            if(!movieEntityRepository.existsById(movieId)){
                throw new NotFoundException("movieId=" + movieId + " cannot be found");
            }

            jpaRepository.addMovieForActor(movieId, actorId);
        }catch (IllegalArgumentException e){
            throw new NotFoundException("movieId/actorId is null",e);
        }
    }

    @Override
    public Set<Actor> findActorsForMovie(Integer movieId) throws NotFoundException {
        try {
            return movieEntityRepository
                    .findById(movieId)
                    .map(MovieEntity::getActors)
                    .map(actorEntityMapper::map)
                    .orElseThrow(() -> new NotFoundException("movieId=" + movieId + " cannot be found"));
        }catch (IllegalArgumentException e){
            throw new NotFoundException("movieId is null",e);
        }
    }

}