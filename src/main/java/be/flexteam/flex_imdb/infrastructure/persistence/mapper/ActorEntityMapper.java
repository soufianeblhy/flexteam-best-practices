package be.flexteam.flex_imdb.infrastructure.persistence.mapper;

import be.flexteam.flex_imdb.domain.model.Actor;
import be.flexteam.flex_imdb.infrastructure.persistence.entity.ActorEntity;
import org.mapstruct.*;

import java.util.Set;

@Mapper
public interface ActorEntityMapper {

    @Mapping(target = "movies", ignore = true)
    ActorEntity map(Actor actor);

    @BeanMapping(ignoreUnmappedSourceProperties = "movies")
    Actor map(ActorEntity actorEntity);

    Set<Actor> map(Set<ActorEntity> actorEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "movies", ignore = true)
    void partialUpdate(Actor actor, @MappingTarget ActorEntity actorEntity);
}