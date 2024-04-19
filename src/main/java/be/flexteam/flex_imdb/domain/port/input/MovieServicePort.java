package be.flexteam.flex_imdb.domain.port.input;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Movie;

import java.util.Set;

public interface MovieServicePort extends GenericServicePort<Movie, Integer> {
    /**
     * Add the actor for the movie
     * @param actorId the actor id to add
     * @param movieId the movie id you want to add the actor
     * @throws NotFoundException actor or movie doesn't exist
     * @throws SaveException not possible to add the actor for the movie
     */
    void addActorForMovie(Integer actorId, Integer movieId);

    /**
     * Find all movies where the actor is present
     * @param actorId the actor id that you want to retrieve the movies
     * @return set of movies found or empty if no movies
     * @throws NotFoundException the actor id doesn't exist
     */
    Set<Movie> findMoviesForActor(Integer actorId);

}