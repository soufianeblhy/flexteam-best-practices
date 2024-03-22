package be.flexteam.flex_imdb.rest.v1.mapper;

import be.flexteam.flex_imdb.domain.model.Actor;
import be.flexteam.flex_imdb.flex_imdb.rest.v1.model.ActorDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface ActorDTOMapper {
    Actor map(ActorDTO actorDTO);

    ActorDTO map(Actor actor);

    List<ActorDTO> map(Set<Actor> actors);
    List<ActorDTO> map(List<Actor> actors);

}