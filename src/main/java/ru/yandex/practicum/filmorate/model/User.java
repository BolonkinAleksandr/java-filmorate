package ru.yandex.practicum.filmorate.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    @Email(message = "incorrect email format")
    @NotBlank(message = "email can't be empty")
    private String email;
    @NotBlank(message = "login can't be empty")
    private String login;
    private String name;
    @Past(message = "birthday can't be in a future")
    private LocalDate birthday;
}
