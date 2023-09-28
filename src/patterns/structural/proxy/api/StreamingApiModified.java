package src.patterns.structural.proxy.api;

import src.app.client.User;
import src.patterns.structural.proxy.server.StreamingPlatformImpl;

public class StreamingApiModified implements ClientServerCommunicationModified {
    
    private User client;
    
    private final StreamingPlatformImpl server;

    public StreamingApiModified(StreamingPlatformImpl server) {
        this.server = server;
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(User client) {
        this.client = client;
    }
    
    @Override
    public void watchContent(String contentName, int contentSize) throws Exception {
        this.client.watchMovie(contentName, contentSize);
        this.server.displayMovie(this.client, contentName);
    }

    @Override
    public void downloadContent(String contentName) throws Exception {
        this.client.downloadMovie(contentName);
        this.server.streamMovie(this.client, contentName);
        this.server.sendRecommendations(this.client);
    }

    @Override
    public void cancelContentFetch() throws Exception {
        this.client.cancelSubscription();
        this.server.handleSubscriptionStatus(this.client);
    }
}
