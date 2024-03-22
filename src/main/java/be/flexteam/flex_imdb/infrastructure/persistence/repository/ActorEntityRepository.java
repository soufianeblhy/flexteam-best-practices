package be.flexteam.flex_imdb.infrastructure.persistence.repository;

import be.flexteam.flex_imdb.infrastructure.persistence.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ActorEntityRepository extends JpaRepository<ActorEntity, Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value="INSERT into FLEX_IMDB.ACTORS_MOVIES (ACTOR_ID, MOVIE_ID) values (:actorId, :movieId)")
    @Transactional
    void addMovieForActor(Integer movieId, Integer actorId);

}