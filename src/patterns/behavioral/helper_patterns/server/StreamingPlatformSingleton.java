package src.patterns.behavioral.helper_patterns.server;

import src.patterns.behavioral.entities.User;
import src.patterns.behavioral.observer.Subject;

import java.util.Random;

public class StreamingPlatformSingleton implements Streaming, Advertisement, FilmManagement, Subject {

    private static StreamingPlatformSingleton streamingPlatformInstance = null;

    private DatabaseSingleton database;

    private StreamingPlatformSingleton() {
    }

    public void setDatabase(DatabaseSingleton database) {
        this.database = database;
    }

    public static StreamingPlatformSingleton getStreamingPlatformInstance() {
        if (streamingPlatformInstance == null) {
            streamingPlatformInstance = new StreamingPlatformSingleton();
        }
        return streamingPlatformInstance;
    }

    @Override
    public void register(User user) {
        this.database.getUsers().add(user);
    }

    @Override
    public void unregister(User user) {
        this.database.getUsers().remove(user);
    }

    @Override
    public void notifyObservers(String message) {
        for (User user : this.database.getUsers()) {
            user.update(message);
        }
    }

    @Override
    public void addFilm(String filmName) {
        this.database.getFilms().add(filmName);
        this.notifyObservers(filmName + " is now available for streaming!");
    }

    @Override
    public void removeFilm(String filmName) {
        this.database.getFilms().remove(filmName);
        this.notifyObservers(filmName + " is no longer available for streaming!");
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
            System.out.println(user.getName() + "is not present in the src.database");
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
            System.out.println(user.getName() + "is not present in the src.database");
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
            System.out.println(user.getName() + "is not present in the src.database");
        }
    }

    @Override
    public void sendRecommendations(User user) {
        Random r = new Random();
        int filmIndex = r.nextInt(this.database.getFilms().size() - 1);
        System.out.println("Streaming platform suggests " + user.getClass().getName() + " " + user.getName()
                + " to watch " + this.database.getFilms().get(filmIndex));
    }
}
