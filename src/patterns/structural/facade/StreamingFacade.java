package src.patterns.structural.facade;

import src.app.client.User;
import src.patterns.structural.composite.UserComposite;
import src.patterns.structural.proxy.StreamingFunctionality;

public class StreamingFacade implements ClientServerCommunicationModified {
    
    private final StreamingFunctionality server;

    public StreamingFacade(StreamingFunctionality server) {
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
}
