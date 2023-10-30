package src.patterns.behavioral;

import src.patterns.behavioral.entities.PremiumUser;
import src.patterns.behavioral.entities.SimpleUser;
import src.patterns.behavioral.entities.User;
import src.patterns.behavioral.helper_patterns.server.StreamingPlatformSingleton;
import src.patterns.behavioral.helper_patterns.server.DatabaseSingleton;
import src.patterns.behavioral.helper_patterns.api.StreamingFacade;
import src.patterns.behavioral.iterator.UserCollection;
import src.patterns.behavioral.iterator.UserCollectionImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BehavioralPatternsMain {
    public static void main(String[] args) throws Exception {
        User u1 = new SimpleUser("Steve", true);
        User u2 = new PremiumUser("Harry", true);
        User u3 = new SimpleUser("Bella", false);

        List<User> users = new ArrayList<>(Arrays.asList(u1, u2, u3));
        List<String> films = new ArrayList<>(Arrays.asList("Titanic", "Split", "1+1", "Jaws", "Inception", "Tenant"));

        DatabaseSingleton db = DatabaseSingleton.getDbInstance();
        db.setUsers(users);
        db.setFilms(films);

        StreamingPlatformSingleton server = StreamingPlatformSingleton.getStreamingPlatformInstance();
        server.setDatabase(db);

        System.out.println();
        server.addFilm("Pulp fiction");

        System.out.println();
        StreamingFacade api = new StreamingFacade(server);
        UserCollection userCollection = new UserCollectionImpl(users);
        Iterator<User> userIterator = userCollection.iterator();
        while (userIterator.hasNext()) {
            User u = userIterator.next();
            api.downloadContent(u, "Inception");
            System.out.println();
        }
    }
}
