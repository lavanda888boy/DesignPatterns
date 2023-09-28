package src.app.client;

import src.patterns.creational.builder.UserBuilder;

public class PremiumUser extends User {
    
    public PremiumUser(String name, boolean subscriptionStatus) {
        super(name, subscriptionStatus);
    }

    public PremiumUser(UserBuilder userBuilder) {
        super(userBuilder);
    }

    @Override
    public void watchMovie(String movieName, int episodeTime) {
        System.out.println(this.getClass().getName()+ " " + this.getName() + " is trying to watch "
                            + movieName + " of length (minutes) " + episodeTime);
        System.out.println(this.getName() + " will not have any advertisement pauses");
    }

    @Override
    public void downloadMovie(String movieName) {
        System.out.println(this.getClass().getName()+ " " + this.getName() + " is trying to download " + movieName);
        System.out.println("The download speed will be boosted");
    }

    @Override
    public void cancelSubscription() {
        System.out.println(this.getClass().getName() + " " + this.getName() + " is trying to cancel premium subscription");
    }
}

