package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Model;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public abstract class Controller<T extends Model> {

    Map<Integer, T> objects = new HashMap<>();
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private int id = 0;

    abstract void validate(T object);

    public List<T> createList() {
        List<T> valueList = new ArrayList<>();
        for (T object : objects.values()) {
            valueList.add(object);
        }
        return valueList;
    }

    private int generateId() {
        id++;
        return id;
    }

    @GetMapping("")
    public List<T> getObjects() {
        log.debug(" GET /objects.");
        return createList();
    }

    @PostMapping(value = "")
    public T postObject(@Valid @RequestBody T object) throws ValidationException {
        int id = generateId();
        object.setId(id);
        validate(object);
        objects.put(id, object);
        log.debug(" POST /object.");
        return object;
    }

    @PutMapping(value = "")
    public T putObject(@Valid @RequestBody T object) throws ValidationException {
        if (!objects.containsKey(object.getId())) {
            throw new ValidationException("object with this id doesn't exist");
        } else {
            validate(object);
            objects.remove(object.getId());
            objects.put(object.getId(), object);
            return object;
        }
    }
}