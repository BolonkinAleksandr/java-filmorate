package ru.yandex.practicum.filmorate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.stream.Collectors;

import java.util.List;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserService userService;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserService userService) {
        this.filmStorage = filmStorage;
        this.userService = userService;
    }

    public Film getFilmById(int filmId) throws NotFoundException {
        final var film = filmStorage.getFilmById(filmId);
        if (film == null) {
            throw new NotFoundException("User with id=" + filmId + " not found");
        }
        return film;
    }

    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film postFilms(Film film) throws ValidationException {
        return filmStorage.postFilm(film);
    }

    public Film putFilm(Film film) throws ValidationException {
        if (!filmStorage.mapContainsId(film.getId())) {
            throw new NotFoundException("object with this id doesn't exist");
        }
        return filmStorage.putFilm(film);
    }

    public void addLike(int userId, int filmId) {
        var film = getFilmById(filmId);
        var user = userService.getUserById(userId);
        filmStorage.addLike(user, film);
    }

    public void deleteLike(int userId, int filmId) {
        var film = getFilmById(filmId);
        var user = userService.getUserById(userId);
        filmStorage.deleteLike(user, film);
    }

    public List<Film> getBestFilms(int size) {
        List<Film> Films = filmStorage.getBestFilms();
        return Films.stream()
                .sorted(this::compare)
                .limit(size)
                .collect(Collectors.toList());
    }

    private int compare(Film p0, Film p1) {
        if (p0.getUserIds().size() >= p1.getUserIds().size()) {
            return -1;
        } else {
            return 1;
        }
    }
}
