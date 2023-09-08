package api;

import client.User;
import server.StreamingPlatform;

public class StreamingApi implements ClientServerCommunication {

    private User client;
    
    private final StreamingPlatform server;

    public StreamingApi(User client, StreamingPlatform server) {
        this.client = client;
        this.server = server;
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(User client) {
        this.client = client;
    }
    
    @Override
    public void watchContent() {

    }

    @Override
    public void downloadContent() {

    }

    @Override
    public void cancelContentFetch() {

    }
}
