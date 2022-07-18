package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Set;

public interface UserStorage {
    List<User> getUsers();

    User postUser(User user);

    User putUser(User user);

    void addFriend(User user, User friend);

    void deleteFriend(User user, User friend);

    List<User> getMutualFriends(User user, User friend);

    Set<Integer> getFriends(int userId);

    Boolean mapContainsId(int id);

    User getUserById(int userId);
}

