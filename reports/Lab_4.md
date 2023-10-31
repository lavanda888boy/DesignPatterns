# Behavioral Design Patterns


## Author: Bajenov Sevastian, FAF-213

## Objectives:

* Get familiar with the Behavioral Design Patterns;
* Choose a specific domain;
* Develop the project for the specific domain using Behavioral Design Patterns;

## Theory:

`Behavioral design patterns` are concerned with the interaction and responsibility of objects. In these design patterns, the interaction between the objects should be in such a way that they can easily talk to each other and still should be loosely coupled. 

`Iterator` is a pattern that lets you traverse elements of a collection without exposing its underlying representation

`Observer` is a pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.

`Strategy` is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.

## Used Behavioral Design Patterns:

* Iterator
* Observer
* Strategy

## Implementation

In this laboratory work I continued the idea of my initial project and added to it a directory `src/patterns/behavioral` in which I created separate folders for each implemented design pattern. Moreover, I added a separate main class for testing the newly created classes and also copied some entities from the initial project implementation, patterns from the previous laboratory works. Let us go through all of them and describe their functionality:

* `behavioral/entities + behavioral/helper_patterns`
    
    The `entities` folder contains the updated representation of the user classes. In order to be suitable for being used within the `strategy` classes part of teir behavior was removed (they became state classes). The `helper_patterns` folder as well contains the updated versions of some structural and creational patterns which were modified in order to support the newly created patterns. More details will be discussed further.

* `behavioral/iterator`

    The folder contains the `UserCollection` interface which is implemented by the `UserCollectionImpl` class. Its methods describe addition and removal of an arbitrary user from the collection but also the interface contains a method for returning an iterator for the collection:

    ```
    public interface UserCollection {

        void addUser(User user);

        void removeUser(User user);

        Iterator<User> iterator();
    }
    ```

    The `iterator()` method inside of the implementation class returns an object of type `UserIterator` which is a private class inside of it, implementing Java built-in interface `Iterator<>` with `User` as the generic parameter. The resulting `UserIterator` object implements two main methods of the `Iterator<>` interface: `hasNext()` which verifies whether the next element in the collection exists and `next()` which actually returns the next element in the collection being iterated over:

    ```
    private class UserIterator implements Iterator<User> {

        private List<User> users;
        private int index;

        public UserIterator(List<User> users) {
            this.users = users;
        }

        @Override
        public boolean hasNext() {
            if (this.index < this.users.size()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public User next() {
            User user = this.users.get(this.index);
            this.index++;
            return user;
        }
    }
    ```

    Moreover, I added a new interface `FilmManagement` implmented on the server side in order to add or remove films from the database, sending the notifications to the observers when those methods are being called (below are presented already implemented versions of those methods):

    ```
    @Override
    public void addFilm(String filmName) {
        this.database.getFilms().add(filmName);
        this.notifyObservers(filmName + " is now available for streaming!");
    }

    @Override
    public void removeFilm(String filmName) {
        this.database.getFilms().remove(filmName);
        this.notifyObservers(filmName + " is no longer available for streaming!");
    }
    ```

* `behavioral/observer`

    In this folder only two interfaces are being stored: `Observer` and `Subject`. The first one is implemented by the observers or in my project - `User` objects (from the `entities` folder) and the second one - by the `StreamingPlatformSingleton` object (the server from the `helper_patterns` folder):

    ```
    public interface Observer {

        void update(String message);
    }

    public interface Subject {

        void register(User user);

        void unregister(User user);

        void notifyObservers(String message);
    }
    ```
 
* `behavioral/strategy`

    This directory contains an interface `UserStrategy` and two classes implementing it - `SimpleUserStrategy` and `PremiumUserStrategy`. As I already mentioned before the behavior of the original `User` classes was removed and now it is situated inside of the strategy classes (moreover, the instance of the `User` class is stored inside of the strategy class).

    ```
    public interface UserStrategy {

        void watchMovie(String movieName, int episodeTime);

        void downloadMovie(String movieName);

        void cancelSubscription();
    }
    ```

    The facade class was also modified in order to include a strategy object. Every time a user is being processed, depending on its subtype, the corresponding `UserStrategy` object is being created to perform the requested action. In this way it is possible to decouple user actions from the `User` objects itself, for example:

    ```
    ...
    @Override
    public void cancelContentFetch(User client) throws Exception {
        detectStrategy(client);
        this.userStrategy.cancelSubscription();
        server.handleSubscriptionStatus(client);
    }

    private void detectStrategy(User client) {
        if (client instanceof SimpleUser) {
            this.userStrategy = new SimpleUserStrategy(client);
        } else {
            this.userStrategy = new PremiumUserStrategy(client);
        }
    }
    ```

## Conclusions / Screenshots / Results 

For testing behavioral design patterns I modified the general main application class. First of all, I create the setup for the server by creating a `DatabaseSingleton` object and adding to it users and films. Then I am dding a new film into the server database in order to demonstrate that the notifications are sent to the observers:

```
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
        ...
```

The result is then the following:

```
src.patterns.behavioral.entities.SimpleUser Steve received the message: Pulp fiction is now available for streaming!
src.patterns.behavioral.entities.PremiumUser Harry received the message: Pulp fiction is now available for streaming!
src.patterns.behavioral.entities.SimpleUser Bella received the message: Pulp fiction is now available for streaming!
```

After that I create an `Iterator<User>` object and process the newly created `UserCollection` (each `User` tries to download a movie):

```
        ...
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
```

The output of this code demonstrates that all the `User` objects of the collection are processed and the corresponding strategy is chosen for each of them:

```
src.patterns.behavioral.strategy.SimpleUserStrategy Steve is trying to download Inception
...

src.patterns.behavioral.strategy.PremiumUserStrategy Harry is trying to download Inception
...

src.patterns.behavioral.strategy.SimpleUserStrategy Bella is trying to download Inception
...
```

In conclusion, I would say that behavioral design patterns are the most difficult patterns to implement. They require a clear understanding of the project scope and interraction between its modules. Moreover, it is necessary to define the behavioral patterns you will need before starting the project itself because eventually they influence the implementation of other design patterns (I faced this problem in this laboratory work when I had to change previously made structural and creational patterns). But when everything is done correctly these patterns become quite handy in oprtimizing the communication within the system you are designing.