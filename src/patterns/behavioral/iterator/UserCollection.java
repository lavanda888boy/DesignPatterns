package src.patterns.behavioral.iterator;

import src.patterns.behavioral.entities.User;

import java.util.Iterator;

public interface UserCollection {

    void addUser(User user);

    void removeUser(User user);

    Iterator<User> iterator();
}
