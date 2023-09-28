package src.app.client;

import java.util.Random;

import src.patterns.creational.builder.UserBuilder;

public abstract class User {

    private final int UPPER_ID_THRESHOLD = 1000000;
    private final int LOWER_ID_THRESHOLD = 100000;
    
    private final int ID;
    
    private String name;

    private boolean subscriptionStatus;

    public User(String name, boolean subscriptionStatus) {
        this.ID = generateUserID();
        this.name = name;
        this.subscriptionStatus = subscriptionStatus;
    }

    public User(UserBuilder userBuilder) {
		this.ID = userBuilder.getID();
		this.name = userBuilder.getName();
        this.subscriptionStatus = userBuilder.getSubscriptionStatus();
	}

    private int generateUserID() {
        Random r = new Random();
        return r.nextInt(this.UPPER_ID_THRESHOLD) + this.LOWER_ID_THRESHOLD;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public boolean getSubscriptionStatus() {
        return this.subscriptionStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubscriptionStatus(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public abstract void watchMovie(String movieName, int episodeTime);

    public abstract void downloadMovie(String movieName);

    public abstract void cancelSubscription();
}
