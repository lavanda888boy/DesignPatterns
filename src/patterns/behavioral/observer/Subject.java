package src.patterns.behavioral.observer;

import src.patterns.behavioral.entities.User;

public interface Subject {

    void register(User user);

    void unregister(User user);

    void notifyObservers(String message);
}
