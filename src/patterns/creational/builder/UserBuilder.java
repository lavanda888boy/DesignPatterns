package src.patterns.creational.builder;

import java.util.Random;

import src.app.client.User;

public abstract class UserBuilder {
    
    private final int UPPER_ID_THRESHOLD = 1000000;
    private final int LOWER_ID_THRESHOLD = 100000;
    
    private final int ID = generateUserID();
    
    private String name;

    private boolean subscriptionStatus;

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

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder subscriptionStatus(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
        return this;
    }

    public abstract User build();
}
