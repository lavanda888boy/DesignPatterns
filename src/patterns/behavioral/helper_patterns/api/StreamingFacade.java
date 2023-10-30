package src.patterns.behavioral.helper_patterns.api;

import src.patterns.behavioral.entities.User;
import src.patterns.behavioral.entities.SimpleUser;
import src.patterns.behavioral.helper_patterns.server.StreamingPlatformSingleton;
import src.patterns.behavioral.strategy.PremiumUserStrategy;
import src.patterns.behavioral.strategy.SimpleUserStrategy;
import src.patterns.behavioral.strategy.UserStrategy;

public class StreamingFacade implements ClientServerCommunicationModified {
    
    private final StreamingPlatformSingleton server;

    private UserStrategy userStrategy;

    public StreamingFacade(StreamingPlatformSingleton server) {
        this.server = server;
    }

    @Override
    public void watchContent(User client, String contentName, int contentSize) throws Exception {
        detectStrategy(client);
        this.userStrategy.watchMovie(contentName, contentSize);
        server.displayMovie(client, contentName);
    }

    @Override
    public void downloadContent(User client, String contentName) throws Exception {
        detectStrategy(client);
        this.userStrategy.downloadMovie(contentName);
        server.streamMovie(client, contentName);
        server.sendRecommendations(client);
    }

    @Override
    public void cancelContentFetch(User client) throws Exception {
        detectStrategy(client);
        this.userStrategy.cancelSubscription();
        server.handleSubscriptionStatus(client);
    }

    private void detectStrategy(User client) {
        if (client instanceof SimpleUser) {
            this.userStrategy = new SimpleUserStrategy(client);
        } else {
            this.userStrategy = new PremiumUserStrategy(client);
        }
    }
}
