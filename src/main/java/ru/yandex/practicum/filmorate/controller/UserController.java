package ru.yandex.practicum.filmorate.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    Map<Integer, User> users = new HashMap<>();
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public List<User> getUsers() {
        log.debug(" GET /users.");
        return new ArrayList<>(users.values());
    }

    @PostMapping("/users")
    public User postUser(@Valid @RequestBody User user) throws ValidationException {
        if (user.getLogin().contains(" ")) {
            throw new ValidationException("user's login can't contains of space");
        }
        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        log.debug(" POST /users.");
        user.setId(users.size() + 1);
        users.put(user.getId(), user);
        return user;
    }


    @PutMapping("/users")
    public User putUser(@Valid @RequestBody User user) {
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("user with this id doesn't exist");
        }
        if (user.getLogin().contains(" ")) {
            throw new ValidationException("user's login can't contains of space");
        }
        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        log.debug(" POST /users.");
        users.remove(user.getId());
        users.put(user.getId(), user);
        return user;
    }

}
