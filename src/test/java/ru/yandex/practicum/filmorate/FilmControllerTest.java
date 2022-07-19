package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.time.LocalDate;

public class FilmControllerTest {

    @Test
    void validateReleaseDatePostTest() {
        FilmController filmController = new FilmController(new FilmService(new InMemoryFilmStorage(), new UserService(new InMemoryUserStorage())));
        Film film = new Film(1, "gdmail", "ygd cyu", LocalDate.of(1700, 12, 28), 100);
        Assertions.assertThrows(ValidationException.class, () -> filmController.postFilm(film));
    }
    @Test
    void validateReleaseDatePutTest() {
        FilmController filmController = new FilmController(new FilmService(new InMemoryFilmStorage(), new UserService(new InMemoryUserStorage())));
        Film film = new Film(1, "gdmail", "ygd cyu", LocalDate.of(1700, 12, 28), 100);
        Assertions.assertThrows(ValidationException.class, () -> filmController.putFilm(film));
    }
}
