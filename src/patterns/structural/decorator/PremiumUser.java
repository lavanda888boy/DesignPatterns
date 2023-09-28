package src.patterns.structural.decorator;

import src.client.User;

public class PremiumUser extends UserDecorator {
    
    public PremiumUser(User user) {
        super(user);
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
        this.setSubscriptionStatus(false);
        System.out.println(this.getClass().getName() + " has the right to cancel the subscription");
    }
}

