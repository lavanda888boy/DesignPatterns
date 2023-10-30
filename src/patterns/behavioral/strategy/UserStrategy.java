package src.patterns.behavioral.strategy;

public interface UserStrategy {

    void watchMovie(String movieName, int episodeTime);

    void downloadMovie(String movieName);

    void cancelSubscription();
}
