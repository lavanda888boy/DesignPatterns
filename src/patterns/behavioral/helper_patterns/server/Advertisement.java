package src.patterns.behavioral.helper_patterns.server;

import src.patterns.behavioral.entities.User;

public interface Advertisement {
    
    void handleSubscriptionStatus(User user);

    void sendRecommendations(User user);
}
