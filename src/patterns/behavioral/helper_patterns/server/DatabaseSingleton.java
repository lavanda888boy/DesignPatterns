package src.patterns.behavioral.helper_patterns.server;

import src.patterns.behavioral.entities.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSingleton {
    private static DatabaseSingleton dbInstance = null;

    private List<User> users;

    private List<String> films;

    private DatabaseSingleton() {
        users = new ArrayList<>();
        films = new ArrayList<>();
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getFilms() {
        return this.films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public static DatabaseSingleton getDbInstance() {
        if (dbInstance == null) {
            dbInstance = new DatabaseSingleton();
        }
        return dbInstance;
    }
}
