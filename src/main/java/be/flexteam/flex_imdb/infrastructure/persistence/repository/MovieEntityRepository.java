package be.flexteam.flex_imdb.infrastructure.persistence.repository;

import be.flexteam.flex_imdb.infrastructure.persistence.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface MovieEntityRepository extends JpaRepository<MovieEntity, Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value="INSERT into FLEX_IMDB.ACTORS_MOVIES (ACTOR_ID, MOVIE_ID) values (:actorId, :movieId)")
    @Transactional
    void addActorForMovie(@Param("actorId") Integer actorId, @Param("movieId") Integer movieId);

}