package client;

import java.util.Random;

public abstract class User {

    private final int UPPER_ID_THRESHOLD = 1000000;
    private final int LOWER_ID_THRESHOLD = 100000;
    
    private final int ID;
    
    private String name;

    private boolean subscriptionStatus;

    private boolean watchingStatus;

    public User(String name, boolean subscriptionStatus) {
        this.ID = generateUserID();
        this.name = name;
        this.subscriptionStatus = subscriptionStatus;
        this.watchingStatus = false;
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

    public boolean getWatchingStatus() {
        return this.watchingStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubscriptionStatus(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public void setWatchingStatus(boolean watchingStatus) {
        this.watchingStatus = watchingStatus;
    }

    abstract void watchTVSeries(int episodeTime);

    abstract void downloadSeries();

    abstract void cancelSubscription();
}
