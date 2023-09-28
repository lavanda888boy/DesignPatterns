package src.patterns.structural.proxy;

import src.app.client.User;
import src.app.database.Database;
import src.patterns.structural.composite.UserComposite;

public class StreamingPlatformProxy implements StreamingFunctionality {
    
    private StreamingFunctionality streamingFunctionality;

    private boolean userValid = true;

    public StreamingPlatformProxy(User user, Database db) {
        if (user.getID() < 100000) {
            userValid = false;
        }
        streamingFunctionality = new StreamingPlatformImpl(db);
    }

    public StreamingPlatformProxy(UserComposite userGroup, Database db) {
        for (User user : userGroup.getUsers()) {
            if (user.getID() < 100000) {
                userValid = false;
                break;
            }
        }
        streamingFunctionality = new StreamingPlatformImpl(db);
    }

    @Override
    public void displayMovie(User user, String movieName) throws Exception {
        if (userValid) {
            this.streamingFunctionality.displayMovie(user, movieName);
        } else {
            throw new Exception("User id is not valid");
        }
    }

    @Override
    public void streamMovie(User user, String movieName) throws Exception {
        if (userValid) {
            this.streamingFunctionality.streamMovie(user, movieName);
        } else {
            throw new Exception("User id is not valid");
        }
    }

    @Override
    public void handleSubscriptionStatus(User user) throws Exception {
        if (userValid) {
            this.streamingFunctionality.handleSubscriptionStatus(user);
        } else {
            throw new Exception("User id is not valid");
        }
    }

    @Override
    public void sendRecommendations(User user) throws Exception {
        if (userValid) {
            this.streamingFunctionality.sendRecommendations(user);
        } else {
            throw new Exception("User id is not valid");
        }
    }
}
