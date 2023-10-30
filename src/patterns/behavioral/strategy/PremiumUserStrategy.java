package src.patterns.behavioral.strategy;

import src.patterns.behavioral.entities.User;

public class PremiumUserStrategy implements UserStrategy{

    private User user;

    public PremiumUserStrategy(User user) {
        this.user = user;
    }

    @Override
    public void watchMovie(String movieName, int episodeTime) {
        System.out.println(this.getClass().getName()+ " " + this.user.getName() + " is trying to watch "
                + movieName + " of length (minutes) " + episodeTime);
        System.out.println(this.user.getName() + " will not have any advertisement pauses");
    }

    @Override
    public void downloadMovie(String movieName) {
        System.out.println(this.getClass().getName()+ " " + this.user.getName() + " is trying to download " + movieName);
        System.out.println("The download speed will be boosted");
    }

    @Override
    public void cancelSubscription() {
        System.out.println(this.getClass().getName() + " " + this.user.getName() + " is trying to cancel premium subscription");
    }
}
