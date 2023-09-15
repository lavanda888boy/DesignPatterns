package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.api.StreamingApi;
import src.client.PremiumUser;
import src.client.SimpleUser;
import src.client.User;
import src.database.Database;
import src.server.StreamingPlatform;

public class MainApplication {
    public static void main(String[] args) {
        SimpleUser u1 = new SimpleUser("Steve", true);
        PremiumUser u2 = new PremiumUser("Harry", true);
        SimpleUser u3 = new SimpleUser("Bella", false);
        
        List<User> users = new ArrayList<>(Arrays.asList(u1, u2, u3));
        List<String> films = new ArrayList<>(Arrays.asList("Titanic", "Split", "1+1", "Jaws", "Inception", "Tenant"));

        Database db = new Database();
        db.setUsers(users);
        db.setFilms(films);

        StreamingPlatform server = new StreamingPlatform(db);
        StreamingApi api = new StreamingApi(server);

        api.setClient(u2);
        api.cancelContentFetch();
    }
}
