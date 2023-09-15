package src.server;

import src.client.User;

public interface Streaming {
    
    void displayMovie(User user, String movieName);

    void streamMovie(User user, String movieName);
}
