package be.flexteam.flex_imdb.domain.service;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Actor;
import be.flexteam.flex_imdb.domain.port.input.ActorServicePort;
import be.flexteam.flex_imdb.domain.port.output.ActorPersistencePort;

import java.util.Objects;
import java.util.Set;


public class ActorServiceImpl extends GenericServiceImpl<Actor,Integer> implements ActorServicePort {

    private final ActorPersistencePort actorPersistencePort;

    public ActorServiceImpl(ActorPersistencePort actorPersistencePort) {
        super(actorPersistencePort);
        this.actorPersistencePort = actorPersistencePort;
    }


    @Override
    public void addMovieForActor(Integer movieId, Integer actorId) throws NotFoundException, SaveException {
        this.actorPersistencePort.addMovieForActor(movieId, actorId);
    }

    @Override
    public Set<Actor> findActorsForMovie(Integer movieId) throws NotFoundException {
        return actorPersistencePort.findActorsForMovie(Objects.requireNonNull(movieId,"movieId cannot be null"));
    }
}