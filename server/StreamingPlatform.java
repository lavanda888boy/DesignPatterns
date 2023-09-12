package server;

import client.User;
import database.Database;

public class StreamingPlatform implements Streaming, Advertisement {

    private final Database database;

    public StreamingPlatform(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return this.database;
    }
    
    @Override
    public void displayMovie(User user) {

    }

    @Override
    public void streamMovie(User user) {

    }

    @Override
    public void handleSubscriptionStatus(User user) {

    }

    @Override
    public void sendRecommendations(User user) {

    }
}
