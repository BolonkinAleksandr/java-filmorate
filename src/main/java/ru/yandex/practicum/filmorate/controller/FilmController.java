package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.IncorrectParameterException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("films")
@Slf4j
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/popular")
    public List<Film> getBestFilms(@RequestParam(defaultValue = "10", required = false) int count) {
        if (count < 0) {
            throw new IncorrectParameterException("count best film < 0");
        }
        log.debug(" get first count={} popular films", count);
        return filmService.getBestFilms(count);
    }

    @GetMapping("")
    public List<Film> getFilms() {
        log.debug(" get films");
        return filmService.getFilms();
    }

    @GetMapping("/{filmId}")
    Film getFilm(@PathVariable int filmId) {
        log.debug("Get film by id={}", filmId);
        return filmService.getFilmById(filmId);
    }

    @PostMapping(value = "")
    public Film postFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.debug(" post film");
        var saved = filmService.postFilms(film);
        return saved;
    }

    @PutMapping(value = "")
    public Film putFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.debug(" put film");
        var saved = filmService.putFilm(film);
        return saved;

    }

    @PutMapping(value = "/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        log.debug(" add like to film with id= {}", id,
                "user id={}", userId);
        filmService.addLike(userId, id);
    }

    @DeleteMapping(value = "/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        log.debug(" delete like to film with id= {}", id,
                "user id={}", userId);
        filmService.deleteLike(userId, id);
    }

    protected void validate(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("film's release date too early");
        }
    }
}
