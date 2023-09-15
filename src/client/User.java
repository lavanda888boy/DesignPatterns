package src.client;

import java.util.Random;

public  class User {

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

    public void watchMovie(String movieName, int episodeTime) {
        System.out.println(this.getClass().getName() + " " + this.getName() + " is watching "
                            + movieName + " of length (minutes) " + episodeTime + " in trial period");
    }

    public void downloadMovie(String movieName) {
        System.out.println(this.getClass().getName() + " " + this.getName() + " is downloading "
                            + movieName + " in trial period");
    }

    public void cancelSubscription() {
        System.out.println(this.getClass().getName() + " " + this.getName() + "does not have to cancel anything");
    }
}
