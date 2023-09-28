package src.patterns.structural.decorator;

import src.app.client.User;

public class UserDecorator extends User {
    
    protected User user;

    public UserDecorator(User user) {
        super(user.getName(), user.getSubscriptionStatus());
        this.user = user;
    }

    @Override
    public void watchMovie(String movieName, int episodeTime) {
        this.user.watchMovie(movieName, episodeTime);
    }

    @Override
    public void downloadMovie(String movieName) {
        this.user.downloadMovie(movieName);
    }

    @Override
    public void cancelSubscription() {
        this.user.cancelSubscription();
    }
}
