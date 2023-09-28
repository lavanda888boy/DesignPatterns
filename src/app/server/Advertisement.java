package src.app.server;

import src.app.client.User;

public interface Advertisement {
    
    void handleSubscriptionStatus(User user);

    void sendRecommendations(User user);
}
