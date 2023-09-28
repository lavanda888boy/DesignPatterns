package src.patterns.structural.proxy.server;

import java.util.Random;

import src.app.client.User;
import src.app.database.Database;

public class StreamingPlatformImpl implements StreamingFunctionality {
    
    private final Database database;

    public StreamingPlatformImpl(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return this.database;
    }
    
    @Override
    public void displayMovie(User user, String movieName) throws Exception {
        if (this.database.getUsers().contains(user)) {
            if (this.database.getFilms().contains(movieName)) {
                System.out.println("Server displays " + movieName + " to the "
                                    + user.getClass().getName() + " " + user.getName());
            } else {
                System.out.println("Server cannot find film " + movieName);
            }
        } else {
            System.out.println(user.getName() + "is not present in the src.database");
        }
    }

    @Override
    public void streamMovie(User user, String movieName) throws Exception {
        if (this.database.getUsers().contains(user)) {
            if (this.database.getFilms().contains(movieName)) {
                System.out.println("Server streams " + movieName + " to the "
                                    + user.getClass().getName() + " " + user.getName());
            } else {
                System.out.println("Server cannot find film " + movieName);
            }
        } else {
            System.out.println(user.getName() + "is not present in the src.database");
        }
    }

    @Override
    public void handleSubscriptionStatus(User user) throws Exception {
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
            System.out.println(user.getName() + "is not present in the src.database");
        }
    }

    @Override
    public void sendRecommendations(User user) throws Exception {
        Random r = new Random();
        int filmIndex = r.nextInt(this.database.getFilms().size() - 1);
        System.out.println("Streaming platform suggests " + user.getClass().getName() + " " + user.getName() 
                            + " to watch " + this.database.getFilms().get(filmIndex));
    }
}
