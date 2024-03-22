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
public class Actor extends Identifier<Integer> {
    @NotNull
    @Size(max = 100)
    private String firstName;
    @NotNull
    @Size(max = 100)
    private String lastName;
}