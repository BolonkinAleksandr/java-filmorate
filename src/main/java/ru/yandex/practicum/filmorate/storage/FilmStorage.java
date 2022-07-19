package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface FilmStorage {
    List<Film> getFilms();

    Film postFilm(Film film);

    Film putFilm(Film film);

    Film getFilmById(int id);

    Boolean mapContainsId(int id);

    void addLike(User user, Film film);

    void deleteLike(User user, Film film);

    List<Film> getBestFilms();
}
