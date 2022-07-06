package ru.yandex.practicum.filmorate;

import lombok.experimental.SuperBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;


import java.time.LocalDate;

public class UserControllerTest {

    @Test
    void validateLoginPostTest() {
        UserController userController = new UserController();
        User user = new User(1, "gd@mail.ru", "ygd cyu", "gdcfgds", LocalDate.of(1895, 12, 28));
        Assertions.assertThrows(ValidationException.class, () -> userController.postObject(user));
    }

    @Test
    void validateIdTest() {
        UserController userController = new UserController();
        User user = new User(1, "gd@mail.ru", "ygdcyu", "gdcfgds", LocalDate.of(1895, 12, 28));
        Assertions.assertThrows(ValidationException.class, () -> userController.putObject(user));
    }

    @Test
    void validateLoginPutTest() {
        UserController userController = new UserController();
        User user = new User(1, "gd@mail.ru", "ygd cyu", "gdcfgds", LocalDate.of(1895, 12, 28));
        Assertions.assertThrows(ValidationException.class, () -> userController.putObject(user));
    }
}
