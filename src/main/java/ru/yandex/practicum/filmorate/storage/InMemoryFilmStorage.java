package ru.yandex.practicum.filmorate.storage;


import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    Map<Integer, Film> films = new HashMap<>();
    private int id = 0;

    public List<Film> createFilmList() {
        List<Film> valueList = new ArrayList<>();
        for (var film : films.values()) {
            valueList.add(film);
        }
        return valueList;
    }

    @Override
    public Film getFilmById(int id) {
        return films.get(id);
    }

    @Override
    public List<Film> getFilms() {
        return createFilmList();
    }

    @Override
    public Film postFilm(Film film) {
        film.setId(++id);
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film putFilm(Film film) {
        films.remove(film.getId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public void addLike(User user, Film film) {
        film.getUserIds().add(user.getId());
    }

    @Override
    public void deleteLike(User user, Film film) {
        film.getUserIds().remove(user.getId());
    }

    @Override
    public List<Film> getBestFilms() {
        List<Film> bestFilms = new ArrayList<>();
        for (var film : films.values()) {
            bestFilms.add(film);
        }
        return bestFilms;
    }

    @Override
    public Boolean mapContainsId(int id) {
        return films.containsKey(id);
    }
}
