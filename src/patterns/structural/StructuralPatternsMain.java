package src.patterns.structural;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.app.client.PremiumUser;
import src.app.client.SimpleUser;
import src.app.client.User;
import src.patterns.creational.singleton.DatabaseSingleton;
import src.patterns.structural.composite.UserComposite;
import src.patterns.structural.decorator.PremiumUserDecorator;
import src.patterns.structural.facade.StreamingFacade;
import src.patterns.structural.proxy.StreamingFunctionality;
import src.patterns.structural.proxy.StreamingPlatformProxy;

public class StructuralPatternsMain {
    public static void main(String[] args) throws Exception {
        User u1 = new SimpleUser("Steve", true);
        User u2 = new PremiumUser("Harry", true);
        User u3 = new SimpleUser("Bella", false);
        User u4 = new PremiumUserDecorator(new SimpleUser("Andy", true));
        
        List<User> users = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
        List<String> films = new ArrayList<>(Arrays.asList("Titanic", "Split", "1+1", "Jaws", "Inception", "Tenant"));

        DatabaseSingleton db = DatabaseSingleton.getDbInstance();
        db.setUsers(users);
        db.setFilms(films);

        User clientGroup = new UserComposite("C.Nolan lovers", true);
        ((UserComposite) clientGroup).addUser(u1);
        ((UserComposite) clientGroup).addUser(u2);
        ((UserComposite) clientGroup).addUser(u3);

        StreamingFunctionality server = new StreamingPlatformProxy(clientGroup, db);
        StreamingFacade api = new StreamingFacade(server);
        api.downloadContent(clientGroup, "Tenant");
        
        System.out.println();
        ((UserComposite) clientGroup).addUser(u4);
        api.cancelContentFetch(clientGroup);
    }
}
