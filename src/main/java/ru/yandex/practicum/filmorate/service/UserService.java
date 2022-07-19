package ru.yandex.practicum.filmorate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User getUserById(int userId) throws NotFoundException {
        final var user = userStorage.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("User with id=" + userId + " not found");
        }
        return user;
    }

    public List<User> getUsers() {
        return userStorage.getUsers();
    }

    public User postUser(User user) throws ValidationException {
        return userStorage.postUser(user);
    }

    public User putUser(User user) throws ValidationException {
        if (!userStorage.mapContainsId(user.getId())) {
            throw new NotFoundException("object with this id doesn't exist");
        }
        return userStorage.putUser(user);
    }

    public void addFriend(int userId, int friendId) {
        var user = getUserById(userId);
        var friend = getUserById(friendId);
        userStorage.addFriend(user, friend);
    }

    public void deleteFriend(int userId, int friendId) {
        var user = getUserById(userId);
        var friend = getUserById(friendId);
        userStorage.deleteFriend(user, friend);
    }

    public List<User> getMutualFriends(int userId, int friendId) {
        var user = getUserById(userId);
        var friend = getUserById(friendId);
        return userStorage.getMutualFriends(user, friend);
    }

    public List<User> getFriends(int userId) {
        List<User> friends = new ArrayList<>();
        Set<Integer> friendsId = userStorage.getFriends(userId);
        for (var id : friendsId) {
            friends.add(getUserById(id));
        }
        return friends;
    }
}
