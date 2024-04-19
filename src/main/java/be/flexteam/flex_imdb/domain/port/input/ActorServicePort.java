package be.flexteam.flex_imdb.domain.port.input;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Actor;

import java.util.Set;


public interface ActorServicePort extends GenericServicePort<Actor, Integer> {
    /**
     * Add the movie for the actor
     * @param actorId the actor id to add
     * @param movieId the movie id you want to add the actor
     * @throws NotFoundException actor or movie doesn't exist
     * @throws SaveException not possible to add the movie for the actor
     */
    void addMovieForActor(Integer movieId, Integer actorId);

    /**
     * Find all actors where the movie is present
     * @param movieId the movie id that you want to retrieve the actors
     * @return set of actors found or empty if no actors
     * @throws NotFoundException the movie doesn't exist
     */
    Set<Actor> findActorsForMovie(Integer movieId);
}