package src.patterns.creational.singleton;

import src.api.ClientServerCommunication;
import src.client.User;

public class StreamingApiSingleton implements ClientServerCommunication {

    private static StreamingApiSingleton streamingApiInstance = null;

    private User client;

    private StreamingPlatformSingleton server;

    private StreamingApiSingleton() {
    }

    public User getClient() {
        return this.client;
    }

    public void setServer(StreamingPlatformSingleton server) {
        this.server = server;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public static StreamingApiSingleton getStreamingApiInstance() {
        if (streamingApiInstance == null) {
            streamingApiInstance = new StreamingApiSingleton();
        }
        return  streamingApiInstance;
    }

    @Override
    public void watchContent(String contentName, int contentSize) {
        this.client.watchMovie(contentName, contentSize);
        this.server.displayMovie(this.client, contentName);
    }

    @Override
    public void downloadContent(String contentName) {
        this.client.downloadMovie(contentName);
        this.server.streamMovie(this.client, contentName);
        this.server.sendRecommendations(this.client);
    }

    @Override
    public void cancelContentFetch() {
        this.client.cancelSubscription();
        this.server.handleSubscriptionStatus(this.client);
    }
}
