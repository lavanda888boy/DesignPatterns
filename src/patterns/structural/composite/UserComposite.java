package src.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

import src.app.client.User;

public class UserComposite extends User {
    
    private List<User> users = new ArrayList<>();

    public UserComposite(String name, boolean subscriptionStatus) {
        super(name, subscriptionStatus);
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public void watchMovie(String movieName, int episodeTime) {
        System.out.println(this.getClass().getName() + " is watching movie");
        for (User user : this.users) {
            user.watchMovie(movieName, episodeTime);
        }
    }

    public void downloadMovie(String movieName) {
        System.out.println(this.getClass().getName() + " is downloading movie");
        for (User user : this.users) {
            user.downloadMovie(movieName);
        }
    }

    public void cancelSubscription() {
        System.out.println(this.getClass().getName() + " is trying to cancel subscription");
        for (User user : this.users) {
            user.cancelSubscription();
        }
    }
}
