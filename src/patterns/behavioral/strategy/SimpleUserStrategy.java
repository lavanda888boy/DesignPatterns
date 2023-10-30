package src.patterns.behavioral.strategy;


import src.patterns.behavioral.entities.User;

public class SimpleUserStrategy implements UserStrategy{

    private User user;

    public SimpleUserStrategy(User user) {
        this.user = user;
    }

    @Override
    public void watchMovie(String movieName, int episodeTime) {
        int advertisementPauses = episodeTime / 40;
        System.out.println(this.getClass().getName() + " " + this.user.getName() + " is trying to watch "
                + movieName + " of length (minutes) " + episodeTime);
        System.out.println(this.user.getName() + " will have " + advertisementPauses + " advertisement pauses");
    }

    @Override
    public void downloadMovie(String movieName) {
        System.out.println(this.getClass().getName() + " " + this.user.getName() + " is trying to download " + movieName);
        System.out.println("The download speed will be half of the usual one");
    }

    @Override
    public void cancelSubscription() {
        System.out.println(this.getClass().getName() + " " + this.user.getName() + " is trying to cancel simple subscription");
    }
}
