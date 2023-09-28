package src.app.server;

import src.app.client.User;

public interface Streaming {
    
    void displayMovie(User user, String movieName);

    void streamMovie(User user, String movieName);
}
