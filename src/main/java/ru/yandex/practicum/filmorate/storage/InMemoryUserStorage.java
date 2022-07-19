package ru.yandex.practicum.filmorate.storage;


import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {
    Map<Integer, User> users = new HashMap<>();
    private int id = 0;

    public List<User> createUserList() {
        List<User> valueList = new ArrayList<>();
        for (var user : users.values()) {
            valueList.add(user);
        }
        return valueList;
    }

    @Override
    public List<User> getUsers() {
        return createUserList();
    }

    @Override
    public User getUserById(int userId) {
        return users.get(userId);
    }

    @Override
    public User postUser(User user) {
        user.setId(++id);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User putUser(User user) {
        users.remove(user.getId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void addFriend(User user, User friend) {
        user.getFriendIds().add(friend.getId());
        friend.getFriendIds().add(user.getId());
    }

    @Override
    public void deleteFriend(User user, User friend) {
        user.getFriendIds().remove(friend.getId());
        friend.getFriendIds().remove(user.getId());
    }

    @Override
    public List<User> getMutualFriends(User user, User friend) {
        List<User> mutualFriends = new ArrayList<>();
        Set<Integer> firstUserFriends = user.getFriendIds();
        Set<Integer> secondUserFriends = friend.getFriendIds();
        for (var firstUserFriendId : firstUserFriends) {
            if (secondUserFriends.contains(firstUserFriendId)) {
                mutualFriends.add(getUserById(firstUserFriendId));
            }
        }
        return mutualFriends;
    }

    @Override
    public Set<Integer> getFriends(int userId) {
        var user = getUserById(userId);
        return user.getFriendIds();
    }

    @Override
    public Boolean mapContainsId(int id) {
        return users.containsKey(id);
    }
}
