package server;

import client.User;
import database.Database;

public class StreamingPlatform implements Streaming, Advertisement {

    private final Database database;

    public StreamingPlatform(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return this.database;
    }
    
    @Override
    public void displayMovie(User user, String movieName) {
        if (this.database.getUsers().contains(user)) {
            if (this.database.getFilms().contains(movieName)) {
                System.out.println("Server displays " + movieName + " to the "
                                    + user.getClass().getName() + " " + user.getName());
            } else {
                System.out.println("Server cannot find film " + movieName);
            }
        } else {
            System.out.println(user.getName() + "is not present in the database");
        }
    }

    @Override
    public void streamMovie(User user, String movieName) {
        if (this.database.getUsers().contains(user)) {
            if (this.database.getFilms().contains(movieName)) {
                System.out.println("Server streams " + movieName + " to the "
                                    + user.getClass().getName() + " " + user.getName());
            } else {
                System.out.println("Server cannot find film " + movieName);
            }
        } else {
            System.out.println(user.getName() + "is not present in the database");
        }
    }

    @Override
    public void handleSubscriptionStatus(User user) {
        if (this.database.getUsers().contains(user)) {
            if (user.getSubscriptionStatus()) {
                user.setSubscriptionStatus(false);
                System.out.println("Server cancelled " + user.getClass().getName() + " "
                                    + user.getName() + "'s subscription");
            } else {
                System.out.println("Server cannot cancel " + user.getClass().getName() + " "
                                    + user.getName() + "'s subscription");
                System.out.println("Subscription is not activated");
            }
        } else {
            System.out.println(user.getName() + "is not present in the database");
        }
    }

    @Override
    public void sendRecommendations(User user) {

    }
}
