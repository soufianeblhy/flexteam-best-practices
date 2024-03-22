package be.flexteam.flex_imdb.domain.service;

import be.flexteam.flex_imdb.domain.exception.NotFoundException;
import be.flexteam.flex_imdb.domain.exception.SaveException;
import be.flexteam.flex_imdb.domain.model.Movie;
import be.flexteam.flex_imdb.domain.port.input.MovieServicePort;
import be.flexteam.flex_imdb.domain.port.output.MoviePersistencePort;

import java.util.Objects;
import java.util.Set;


public class MovieServiceImpl extends GenericServiceImpl<Movie,Integer> implements MovieServicePort {

    private final MoviePersistencePort moviePersistencePort;

    public MovieServiceImpl(MoviePersistencePort moviePersistencePort) {
        super(moviePersistencePort);
        this.moviePersistencePort = moviePersistencePort;
    }


    @Override
    public void addActorForMovie(Integer actorId, Integer movieId) throws NotFoundException, SaveException {
        this.moviePersistencePort.addActorForMovie(actorId, movieId);
    }

    @Override
    public Set<Movie> findMoviesForActor(Integer actorId) throws NotFoundException {
        return moviePersistencePort.findMoviesForActor(Objects.requireNonNull(actorId,"actorId cannot be null"));
    }
}