package src.server;

import src.client.User;

public interface Advertisement {
    
    void handleSubscriptionStatus(User user);

    void sendRecommendations(User user);
}
