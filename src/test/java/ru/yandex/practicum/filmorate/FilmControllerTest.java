package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

public class FilmControllerTest {

    @Test
    void validateReleaseDatePostTest() {
        FilmController filmController = new FilmController();
        Film film = new Film(1, "gdmail", "ygd cyu", LocalDate.of(1700, 12, 28), 100);
        Assertions.assertThrows(ValidationException.class, () -> filmController.postObject(film));
    }
    @Test
    void validateReleaseDatePutTest() {
        FilmController filmController = new FilmController();
        Film film = new Film(1, "gdmail", "ygd cyu", LocalDate.of(1700, 12, 28), 100);
        Assertions.assertThrows(ValidationException.class, () -> filmController.putObject(film));
    }
}
