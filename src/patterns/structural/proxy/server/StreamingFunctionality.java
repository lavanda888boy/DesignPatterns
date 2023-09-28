package src.patterns.structural.proxy.server;

import src.client.User;

public interface StreamingFunctionality {
    
    void displayMovie(User user, String movieName) throws Exception;

    void streamMovie(User user, String movieName) throws Exception;

    void handleSubscriptionStatus(User user) throws Exception;

    void sendRecommendations(User user) throws Exception;
}
