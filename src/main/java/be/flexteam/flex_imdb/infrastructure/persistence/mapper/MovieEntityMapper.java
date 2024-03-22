package be.flexteam.flex_imdb.infrastructure.persistence.mapper;

import be.flexteam.flex_imdb.domain.model.Movie;
import be.flexteam.flex_imdb.infrastructure.persistence.entity.MovieEntity;
import org.mapstruct.*;

import java.util.Set;

@Mapper
public interface MovieEntityMapper {

    @Mapping(target = "actors", ignore = true)
    MovieEntity map(Movie movie);

    @BeanMapping(ignoreUnmappedSourceProperties = "actors")
    Movie map(MovieEntity movieEntity);

    Set<Movie> map(Set<MovieEntity> movieEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "actors", ignore = true)
    void partialUpdate(Movie actor, @MappingTarget MovieEntity movieEntity);
}