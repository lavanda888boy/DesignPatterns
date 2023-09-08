package database;

import java.util.ArrayList;
import java.util.List;

import client.User;

public class Database {
    
    private List<User> users;
    
    private List<String> films;

    public Database() {
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
}
