package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

@RestController
@RequestMapping("users")
public class UserController extends Controller<User> {

    @Override
    protected void validate(User user) {
        if (user.getLogin().contains(" ")) {
            throw new ValidationException("user's login can't contains of space");
        }
        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
    }
}
