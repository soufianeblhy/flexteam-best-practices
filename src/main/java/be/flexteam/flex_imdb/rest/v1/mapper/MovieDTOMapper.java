package be.flexteam.flex_imdb.rest.v1.mapper;

import be.flexteam.flex_imdb.domain.model.Movie;
import be.flexteam.flex_imdb.flex_imdb.rest.v1.model.MovieDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface MovieDTOMapper {
    Movie map(MovieDTO movieDTO);

    MovieDTO map(Movie movie);

    List<MovieDTO> map(Set<Movie> movies);
    List<MovieDTO> map(List<Movie> movies);

}