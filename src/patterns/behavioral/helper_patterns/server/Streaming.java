package src.patterns.behavioral.helper_patterns.server;

import src.patterns.behavioral.entities.User;

public interface Streaming {
    
    void displayMovie(User user, String movieName);

    void streamMovie(User user, String movieName);
}
