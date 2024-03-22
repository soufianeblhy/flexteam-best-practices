package be.flexteam.flex_imdb.domain.port.input;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Actor;

import java.util.Set;


public interface ActorServicePort extends GenericServicePort<Actor, Integer> {

    void addMovieForActor(Integer movieId, Integer actorId) throws NotFoundException, SaveException;

    Set<Actor> findActorsForMovie(Integer movieId) throws NotFoundException;
}