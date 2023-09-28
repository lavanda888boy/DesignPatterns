package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.app.api.StreamingApi;
import src.app.client.PremiumUser;
import src.app.client.SimpleUser;
import src.app.client.User;
import src.app.database.Database;
import src.app.server.StreamingPlatform;

public class MainApplication {
    public static void main(String[] args) {
        User u1 = new SimpleUser("Steve", true);
        User u2 = new PremiumUser("Harry", true);
        User u3 = new SimpleUser("Bella", false);
        
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
