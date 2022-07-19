package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film extends Model {
    @NotBlank(message = "film's name can't be empty")
    private String name;
    @Size(max = 200, message = "max length is 200 symbols")
    private String description;
    private LocalDate releaseDate;
    @Min(0)
    private int duration;
    @JsonIgnore
    Set<Integer> userIds = new HashSet<>();

    public Film(int id, String name, String description, LocalDate releaseDate, int duration) {
        super(id);
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}

