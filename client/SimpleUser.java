package client;

public class SimpleUser extends User {
    
    public SimpleUser(String name, boolean subscriptionStatus) {
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
