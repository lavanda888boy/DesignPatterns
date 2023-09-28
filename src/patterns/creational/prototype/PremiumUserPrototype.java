package src.patterns.creational.prototype;

public class PremiumUserPrototype extends UserPrototype {
    
    public PremiumUserPrototype(String name, boolean subscriptionStatus) {
        super(name, subscriptionStatus);
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

    @Override
    public UserPrototype getClone() {
        UserPrototype pp = new PremiumUserPrototype(this.getName(), this.getSubscriptionStatus());
        pp.setID(this.getID());
        return pp;
    }
}
