package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FilmController {
    Map<Integer, Film> films = new HashMap<>();
    private final static Logger log = LoggerFactory.getLogger(FilmController.class);


    @GetMapping("/films")
    public List<Film> getFilms() {
        log.debug(" GET /films.");
        return new ArrayList<>(films.values());
    }

    @PostMapping("/films")
    public Film postFilm(@Valid @RequestBody Film film) throws ValidationException {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("film's release date too early");
        }
        log.debug(" POST /films.");
        film.setId(films.size() + 1);
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping("/films")
    public Film putFilm(@Valid @RequestBody Film film) throws ValidationException {
        if (!films.containsKey(film.getId())) {
            throw new ValidationException("film with this id doesn't exist");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("film's release date too early");
        }
        log.debug(" PUT /films.");
        films.remove(film.getId());
        films.put(film.getId(), film);
        return film;
    }
}
