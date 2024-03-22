package be.flexteam.flex_imdb.domain.port.output;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Actor;

import java.util.Set;

public interface ActorPersistencePort extends GenericPersistencePort<Actor, Integer> {
    void addMovieForActor(Integer movieId, Integer actorId) throws NotFoundException, SaveException;

    Set<Actor> findActorsForMovie(Integer movieId) throws NotFoundException;

}