package src.patterns.behavioral.helper_patterns.api;

import src.patterns.behavioral.entities.User;

public interface ClientServerCommunicationModified {
    
    void watchContent(User client, String contentName, int contentSize) throws Exception;

    void downloadContent(User client, String contentName) throws Exception;

    void cancelContentFetch(User client) throws Exception;
}
