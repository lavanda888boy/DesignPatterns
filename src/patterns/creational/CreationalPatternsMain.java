package src.patterns.creational;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.app.client.User;
import src.patterns.creational.builder.PremiumUserBuilder;
import src.patterns.creational.builder.UserBuilder;
import src.patterns.creational.factory.UserFactory;
import src.patterns.creational.factory.UserFactory.UserCategory;
import src.patterns.creational.prototype.SimpleUserPrototype;
import src.patterns.creational.prototype.UserPrototype;
import src.patterns.creational.singleton.DatabaseSingleton;
import src.patterns.creational.singleton.StreamingApiSingleton;
import src.patterns.creational.singleton.StreamingPlatformSingleton;

public class CreationalPatternsMain {
    public static void main(String[] args) {
        User u1 = UserFactory.getUser("John", true, UserCategory.SimpleUser);
        UserBuilder ub = new PremiumUserBuilder();
        User u2 = ub.name("Bella")
                    .subscriptionStatus(false)
                    .build();
        
        List<User> users = new ArrayList<>(Arrays.asList(u1, u2));
        List<String> films = new ArrayList<>(Arrays.asList("Titanic", "Split", "1+1", "Jaws", "Inception", "Tenant"));

        DatabaseSingleton db = DatabaseSingleton.getDbInstance();
        db.setUsers(users);
        db.setFilms(films);

        StreamingPlatformSingleton server = StreamingPlatformSingleton.getStreamingPlatformInstance();
        server.setDatabase(db);
        StreamingApiSingleton api = StreamingApiSingleton.getStreamingApiInstance();
        api.setServer(server);

        api.setClient(u1);
        api.downloadContent("Inception");
        System.out.println();

        api.setClient(u2);
        api.downloadContent("Tenant");
        System.out.println();

        UserPrototype u3 = new SimpleUserPrototype("Patrick", false);
        u3.watchMovie("Split", 120);
        UserPrototype u4 = u3.getClone();
        u4.watchMovie("Split", 120);
    }
}
