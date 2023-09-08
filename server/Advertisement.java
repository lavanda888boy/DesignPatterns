package server;

import client.User;

public interface Advertisement {
    
    void handleSubscriptionStatus(User user);

    void sendRecommendations(User user);
}
