package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private int id;
    @NotBlank(message = "film's name can't be empty")
    private String name;
    @Size(max = 200, message = "max length is 200 symbols")
    private String description;
    private LocalDate releaseDate;
    @Min(0)
    private int duration;
}

