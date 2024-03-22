package be.flexteam.flex_imdb.domain.port.input;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Movie;

import java.util.Set;

public interface MovieServicePort extends GenericServicePort<Movie, Integer> {
    void addActorForMovie(Integer actorId, Integer movieId) throws NotFoundException, SaveException;

    Set<Movie> findMoviesForActor(Integer actorId) throws NotFoundException;

}