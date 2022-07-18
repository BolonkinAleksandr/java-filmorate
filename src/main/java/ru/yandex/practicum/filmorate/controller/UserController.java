package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getFilms() {
        log.debug(" get users");
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable int userId) {
        log.debug("Get user by id={}", userId);
        return userService.getUserById(userId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getFriends(@PathVariable int id) {
        log.debug(" get friends");
        return userService.getFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    List<User> getMutualFriends(@PathVariable int id, @PathVariable int otherId) {
        log.debug("Get mutual friends user id={}", id,
                "friend id={}", otherId);
        return userService.getMutualFriends(id, otherId);
    }

    @PostMapping(value = "")
    public User postUser(@Valid @RequestBody User user) {
        validate(user);
        log.debug(" post user");
        var saved = userService.postUser(user);
        return saved;
    }

    @PutMapping(value = "")
    public User putUser(@Valid @RequestBody User user) {
        validate(user);
        log.debug(" put user");
        var saved = userService.putUser(user);
        return saved;

    }

    @PutMapping("/{userId}/friends/{friendId}")
    public void addFriend(@PathVariable int userId, @PathVariable int friendId) {
        log.debug("add friend, user id={}", userId,
                "friend id={}", friendId);
        userService.addFriend(userId, friendId);
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    public void deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
        log.debug("delete friend, user id={}", userId,
                "friend id={}", friendId);
        userService.deleteFriend(userId, friendId);
    }

    protected void validate(User user) {
        if (user.getLogin().contains(" ")) {
            throw new NotFoundException("user's login can't contains of space");
        }
        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
    }
}
