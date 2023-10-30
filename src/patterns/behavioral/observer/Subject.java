package src.patterns.behavioral.observer;

import src.patterns.behavioral.entities.User;

public interface Subject {

    public void register(User user);

    public void unregister(User user);

    public void notifyObservers(String message);
}
