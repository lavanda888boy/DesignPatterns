package src.patterns.structural.facade;

import src.app.client.User;
import src.patterns.structural.composite.UserComposite;
import src.patterns.structural.proxy.StreamingPlatformProxy;

public class StreamingFacade implements ClientServerCommunicationModified {
    
    private final StreamingPlatformProxy server;

    public StreamingFacade(StreamingPlatformProxy server) {
        this.server = server;
    }

    @Override
    public void watchContent(User client, String contentName, int contentSize) throws Exception {
        client.watchMovie(contentName, contentSize);
        server.displayMovie(client, contentName);
    }

    @Override
    public void downloadContent(User client, String contentName) throws Exception {
        client.downloadMovie(contentName);
        server.streamMovie(client, contentName);
        server.sendRecommendations(client);
    }

    @Override
    public void cancelContentFetch(User client) throws Exception {
        client.cancelSubscription();
        server.handleSubscriptionStatus(client);
    }

    public void watchContent(UserComposite clientGroup, String contentName, int contentSize) throws Exception {
        clientGroup.watchMovie(contentName, contentSize);
        for (User client : clientGroup.getUsers()) {
            server.displayMovie(client, contentName);
        }
    }

    public void downloadContent(UserComposite clientGroup, String contentName) throws Exception {
        clientGroup.downloadMovie(contentName);
        for (User client : clientGroup.getUsers()) {
            server.streamMovie(client, contentName);
            server.sendRecommendations(client);
        }
    }

    public void cancelContentFetch(UserComposite clientGroup) throws Exception {
        clientGroup.cancelSubscription();
        for (User client : clientGroup.getUsers()) {
            server.handleSubscriptionStatus(client);
        }
    }
}
