package api;

import client.User;
import server.StreamingPlatform;

public class StreamingApi implements ClientServerCommunication {

    private User client;
    
    private final StreamingPlatform server;

    public StreamingApi(StreamingPlatform server) {
        this.server = server;
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(User client) {
        this.client = client;
    }
    
    @Override
    public void watchContent(String contentName, int contentSize) {
        this.client.watchMovie(contentName, contentSize);
        this.server.displayMovie(this.client, contentName);
    }

    @Override
    public void downloadContent(String contentName) {
        this.client.downloadMovie(contentName);
        this.server.displayMovie(this.client, contentName);
    }

    @Override
    public void cancelContentFetch() {
        this.client.cancelSubscription();
        this.server.handleSubscriptionStatus(this.client);
    }
}
