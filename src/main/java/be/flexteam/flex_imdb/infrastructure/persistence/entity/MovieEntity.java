package be.flexteam.flex_imdb.infrastructure.persistence.entity;

import be.flexteam.flex_imdb.domain.model.GenreEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "MOVIES", schema = "FLEX_IMDB")
public class MovieEntity {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ToString.Include
    @Size(max = 200)
    @NotNull
    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @ManyToMany
    @JoinTable(name = "ACTORS_MOVIES",
            joinColumns = @JoinColumn(name = "ACTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private Set<ActorEntity> actors = new HashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MovieEntity that = (MovieEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}