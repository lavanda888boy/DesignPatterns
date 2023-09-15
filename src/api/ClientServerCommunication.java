package src.api;

public interface ClientServerCommunication {
    
    void watchContent(String contentName, int contentSize);

    void downloadContent(String contentName);

    void cancelContentFetch();
}
