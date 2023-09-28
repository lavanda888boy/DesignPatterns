package src.patterns.structural.proxy.api;

public interface ClientServerCommunicationModified {
    
    void watchContent(String contentName, int contentSize) throws Exception;

    void downloadContent(String contentName) throws Exception;

    void cancelContentFetch() throws Exception;
}
