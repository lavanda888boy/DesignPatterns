package client;

public class PremiumUser extends User {
    
    public PremiumUser(String name, boolean subscriptionStatus) {
        super(name, subscriptionStatus);
    }

    @Override
    public void watchTVSeries(int episodeTime) {

    }

    @Override
    public void downloadSeries() {

    }

    @Override
    public void cancelSubscription() {

    }
}

