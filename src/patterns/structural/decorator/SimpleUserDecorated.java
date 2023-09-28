package src.patterns.structural.decorator;

import src.app.client.User;

public class SimpleUserDecorated extends UserDecorator {
    
    public SimpleUserDecorated(User user) {
        super(user);
        System.out.println("The user " + this.getName() + " becomes simple");
    }

    @Override
    public void watchMovie(String movieName, int episodeTime) {
        super.watchMovie(movieName, episodeTime);
        System.out.println(this.getClass().getName() + " has id " + this.getID());
    }

    @Override
    public void downloadMovie(String movieName) {
        super.downloadMovie(movieName);
        System.out.println(this.getClass().getName() + " has id " + this.getID());
    }

    @Override
    public void cancelSubscription() {
        super.cancelSubscription();
        System.out.println(this.getClass().getName() + " has id " + this.getID());
    }
}
