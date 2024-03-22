package be.flexteam.flex_imdb.rest.v1.mapper;

import be.flexteam.flex_imdb.domain.model.GenreEnum;
import be.flexteam.flex_imdb.flex_imdb.rest.v1.model.GenreDTO;
import org.mapstruct.Mapper;

@Mapper
public interface GenreDTOMapper {
    GenreEnum map(GenreDTO genreDTO);

    GenreDTO map(GenreEnum genreEnum);

}