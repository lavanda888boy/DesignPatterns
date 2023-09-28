package src.patterns.creational.prototype;

public class SimpleUserPrototype extends UserPrototype {
    
    public SimpleUserPrototype(String name, boolean subscriptionStatus) {
        super(name, subscriptionStatus);
    }

    @Override
    public void watchMovie(String movieName, int episodeTime) {
        int advertisementPauses = episodeTime / 40;
        System.out.println(this.getClass().getName() + " " + this.getName() + " is trying to watch "
                            + movieName + " of length (minutes) " + episodeTime);
        System.out.println(this.getName() + " will have " + advertisementPauses + " advertisement pauses");
    }

    @Override
    public void downloadMovie(String movieName) {
        System.out.println(this.getClass().getName() + " " + this.getName() + " is trying to download " + movieName);
        System.out.println("The download speed will be half of the usual one");
    }

    @Override
    public void cancelSubscription() {
        System.out.println(this.getClass().getName() + " " + this.getName() + " is trying to cancel simple subscription");
    }

    @Override
    public UserPrototype getClone() {
        UserPrototype sp = new SimpleUserPrototype(this.getName(), this.getSubscriptionStatus());
        sp.setID(this.getID());
        return sp;
    }
}
