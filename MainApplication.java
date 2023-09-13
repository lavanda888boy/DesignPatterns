import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import api.StreamingApi;
import client.PremiumUser;
import client.SimpleUser;
import client.User;
import database.Database;
import server.StreamingPlatform;

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
