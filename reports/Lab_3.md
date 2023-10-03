# Structural Design Patterns


## Author: Bajenov Sevastian, FAF-213

## Objectives:

* Get familiar with the Structural Design Patterns;
* Choose a specific domain;
* Develop the project for the specific domain using Structural Design Patterns;

## Theory:
`Structural design patterns` are concerned with how classes and objects can be composed, to form larger structures. The structural design patterns simplify the structure by identifying the relationships. These patterns focus on, how the classes inherit from each other and how they are composed from other classes.

`Composite` pattern allows clients to operate in generic manner on objects that may or may not represent a hierarchy of objects.

`Decorator` pattern attaches a flexible additional responsibilities to an object dynamically. In other words, this pattern uses composition instead of inheritance to extend the functionality of an object at runtime.

`Facade` pattern provides a unified and simplified interface to a set of interfaces in a subsystem, therefore it hides the complexities of the subsystem from the client. In other words, Facade pattern describes a higher-level interface that makes the sub-system easier to use.

`Proxy` pattern provides the control for accessing the original object. So, we can perform many operations like hiding the information of original object, on demand loading etc.


## Used Structural Design Patterns: 

* Composite
* Decorator
* Facade
* Proxy


## Implementation
In this laboratory work I continued the idea of my initial project and added to it a directory `src/patterns/structural` in which I created separate folders for each implemented design pattern. Moreover, I added a separate main class for testing the newly created classes. Let us go through all of them and describe their functionality:

* `structural/composite`
    
    The folder contains a single class `UserComposite` which contains a list of users and several methods for processing their operations. The users can be basically added or removed from the list but also the composite class is able to call server related methods on each `User` from the `List<User>`: `watchMovie()`, `downloadMovie()`, and `cancelSubscription()`. It is important to mention that the class extends the `User` class:

    ```
    public class UserComposite extends User {
    
        private List<User> users = new ArrayList<>();

        public UserComposite(String name, boolean subscriptionStatus) {
            super(name, subscriptionStatus);
        }

        public List<User> getUsers() {
            return this.users;
        }

        public void addUser(User user) {
            this.users.add(user);
        }

        public void removeUser(User user) {
            this.users.remove(user);
        }
        ...
    ```

    ```
        ...
        public void watchMovie(String movieName, int episodeTime) {
            System.out.println(this.getClass().getName() + " is watching movie");
            for (User user : this.users) {
                user.watchMovie(movieName, episodeTime);
            }
        }
        ...
    ```

* `structural/decorator`

    The folder contains three decorator classes. The first one, `UserDecorator`, extends the original `User` class, has its protected instance inside itself and basically overrides server related methods from the parent class. The other two classes `SimpleUserDecorator` and `PremiumUserDecorator` extend `UserDecorator` class and override either its constructor or its methods indicating that the user obtains either "premium" or "simple" behavior:

    ```
    public class UserDecorator extends User {
    
        protected User user;

        public UserDecorator(User user) {
            super(user.getName(), user.getSubscriptionStatus());
            this.user = user;
        }

        @Override
        public void watchMovie(String movieName, int episodeTime) {
            this.user.watchMovie(movieName, episodeTime);
        }
        ...
    ```

    ```
    public class PremiumUserDecorator extends UserDecorator {
    
        public PremiumUserDecorator(User user) {
            super(user);
            System.out.println("The user " + this.getName() + " becomes premium");
        }
        ...

        @Override
        public void cancelSubscription() {
            super.cancelSubscription();
            System.out.println(this.getClass().getName() + " has id " + this.getID());
            this.setSubscriptionStatus(false);
            System.out.println(this.getClass().getName() + " has the right to cancel the subscription");
        }
    ```

* `structural/facade`

    Inside of this folder there are two files: `StreamingFacade` class and `ClientServerCommunicationModified` interface. The interface is actually an enhancement of the original `ClientServerCommunication` interface. Now all its methods depend on a single client which is being sent as a parameter:

    ```
    public interface ClientServerCommunicationModified {
    
        void watchContent(User client, String contentName, int contentSize) throws Exception;

        void downloadContent(User client, String contentName) throws Exception;

        void cancelContentFetch(User client) throws Exception;
    }
    ```

    In this way the facade class becomes more stateless and does not contain `User` instance. The only attribute which it contains is the server. Actually, the `StreamingFacade` is very similar to the `StreamingApi` class but it also has additional overloaded methods for handling `UserComposite` objects:

    ```
    public class StreamingFacade implements ClientServerCommunicationModified {
    
        private final StreamingFunctionality server;

        public StreamingFacade(StreamingFunctionality server) {
            this.server = server;
        }

        @Override
        public void watchContent(User client, String contentName, int contentSize) throws Exception {
            client.watchMovie(contentName, contentSize);
            server.displayMovie(client, contentName);
        }
    ```

    ```
        ...
        public void watchContent(UserComposite clientGroup, String contentName, int contentSize) throws Exception {
            clientGroup.watchMovie(contentName, contentSize);
            System.out.println();
            for (User client : clientGroup.getUsers()) {
                server.displayMovie(client, contentName);
            }
        }
        ...
    ```

    It is also worth mentioning that the server attribute of the facade class has now the type `StreamingFunctionality`. This change in the object type will be explained when describing the `proxy` pattern.

* `structural/proxy`

    The folder contains three main files: `StreamingFunctionality` interface (it is basically the composition of the original `Streaming` and `Advertisement` interfaces; also all the methods are now able to throw Exceptions), `StreamingPlatformImpl` class which actually is a copy of the original `StreamingPlatform` with the same implementation of the main server related methods; and `StreamingPlatformProxy` class which represents a proxy server.

    Proxy server class contains user validation mechanism based on the ID assigned to the `User` upon creation (there are two constructors inside of it; it can also validate `UserComposite` objects):
    
    ```
    public class StreamingPlatformProxy implements StreamingFunctionality {
    
        private StreamingFunctionality streamingFunctionality;

        private boolean userValid = true;

        public StreamingPlatformProxy(User user, Database db) {
            if (user.getID() < 100000) {
                userValid = false;
            }
            streamingFunctionality = new StreamingPlatformImpl(db);
        }

        public StreamingPlatformProxy(UserComposite userGroup, Database db) {
            for (User user : userGroup.getUsers()) {
                if (user.getID() < 100000) {
                    userValid = false;
                    break;
                }
            }
            streamingFunctionality = new StreamingPlatformImpl(db);
        }
    ```

    Other method signatures are the same as in the `StreamingPlatformImpl` class but their contents differ. In each of these methods proxy class verifies if the user or the group of users is valid and either calls method in `StreamingPlatformImpl` or throws an Exception; for example:

    ```
        ...
        @Override
        public void streamMovie(User user, String movieName) throws Exception {
            if (userValid) {
                this.streamingFunctionality.streamMovie(user, movieName);
            } else {
                throw new Exception("User id is not valid");
            }
        }
        ...
    ```

## Conclusions / Screenshots / Results 

For testing structural design patterns I modified the general main application class. Three `User`s are created and added to the `UserComposite` object and the fourth `User` is created using the constructor from the `PremiumuserDecorator` class in order to test later the change in behavior:

```
public class StructuralPatternsMain {
    public static void main(String[] args) throws Exception {
        User u1 = new SimpleUser("Steve", true);
        User u2 = new PremiumUser("Harry", true);
        User u3 = new SimpleUser("Bella", false);
        User u4 = new PremiumUserDecorator(new SimpleUser("Andy", true));
        
        List<User> users = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
        List<String> films = new ArrayList<>(Arrays.asList("Titanic", "Split", "1+1", "Jaws", "Inception", "Tenant"));

        Database db = new Database();
        db.setUsers(users);
        db.setFilms(films);

        UserComposite clientGroup = new UserComposite("C.Nolan lovers", true);
        clientGroup.addUser(u1);
        clientGroup.addUser(u2);
        clientGroup.addUser(u3);
        ...
```

It can be also noticed that the `Database` object with already created users and films is initialized. Further on, the proxy server instance is created and injected as a dependency into the `StreamingFacade` object which then calls `downloadContent()` method on the newly created `UserComposite` object. After that the user decorator is added to the composition of users to demonstrate new behavior of the decorated user by calling the `cancelContentFetch()` method:

```
        ...
        StreamingFunctionality server = new StreamingPlatformProxy(clientGroup, db);
        StreamingFacade api = new StreamingFacade(server);
        api.downloadContent(clientGroup, "Tenant");
        
        System.out.println();
        clientGroup.addUser(u4);
        api.cancelContentFetch(clientGroup);
    }
}
```

The resulting output of the main program is presented below (the `SimpleUser` named Andy is now trying to cancel his subscription by using method from the `PremiumUserDecorator` class):

```
The user Andy becomes premium
src.patterns.structural.composite.UserComposite is downloading movie
src.app.client.SimpleUser Steve is trying to download Tenant
The download speed will be half of the usual one
src.app.client.PremiumUser Harry is trying to download Tenant
The download speed will be boosted

...

Server streams Tenant to the src.app.client.SimpleUser Steve
Streaming platform suggests src.app.client.SimpleUser Steve to watch 1+1
Server streams Tenant to the src.app.client.PremiumUser Harry
Streaming platform suggests src.app.client.PremiumUser Harry to watch Split

...

src.patterns.structural.decorator.PremiumUserDecorator has id 252626
src.patterns.structural.decorator.PremiumUserDecorator has the right to cancel the subscription

...

Server cannot cancel src.patterns.structural.decorator.PremiumUserDecorator Andy's subscription
Subscription is not activated
```

In conclusion, I would say that structural design patterns bring many benefits for designing software products. They help the programmers hide inner functionality of the system to prevent it from any unexpected changes, organize similar objects into a common structure and introduce basic security principles into the projects. In this laboratory work I managed to use structural design patterns to optimize my project. Proxy pattern was rather useful for giving users controlled access to the server. Facade pattern made the api robust without any redundant state inside. Decorator and composite patterns introduced variety in defining users of the system. Therefore, summing up everything having been said, structural design patterns simplify software products and make them more efficient.
