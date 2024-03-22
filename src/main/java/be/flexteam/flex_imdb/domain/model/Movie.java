package be.flexteam.flex_imdb.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends Identifier<Integer>{
    private Integer id;
    @NotNull
    @Size(max = 200)
    private String title;
    @NotNull
    private GenreEnum genre;
}