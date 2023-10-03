# Creational Design Patterns


## Author: Bajenov Sevastian, FAF-213

## Objectives:

* Get familiar with the Creational Design Patterns;
* Choose a specific domain;
* Develop the project for the specific domain using Creational Design Patterns;

## Theory:
`Creational design patterns` are concerned with the way of creating objects. These design patterns are used when a decision must be made at the time of instantiation of a class (i.e. creating an object of a class). 

`Builder` is the design pattern that lets you construct complex objects step by step. The pattern allows you to produce different types and representations of an object using the same construction code.

`Factory` is the design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

`Prototype` pattern lets you copy existing objects without making your code dependent on their classes.

`Singleton` pattern lets you ensure that a class has only one instance, while providing a global access point to this instance.

## Used Creational Design Patterns: 

* Builder
* Factory
* Prototype
* Singleton


## Implementation
In this laboratory work I continued the idea of my initial project and added to it a directory `src/patterns/creational` in which I created separate folders for each implemented design pattern. Moreover, I added a separate main class for testing the newly created classes. Let us go through all of them and describe their functionality:

* `creational/builder`

    The folder contains three classes `UserBuilder`, `SimpleUserBuilder` and `PremiumUserBuilder`. `UserBuilder` as well as the class `User` is abstract. The changes that I had to make to the initial class in order for the new pattern to work implied creating a new constructor which receives an object of type `UserBuilder` as parameter:

    ```
    public User(UserBuilder userBuilder) {
        this.ID = userBuilder.getID();
        this.name = userBuilder.getName();
        this.subscriptionStatus = userBuilder.getSubscriptionStatus();
    }
    ```

    The `UserBuilder` class comparing to the `User` contains additional setters which return the object of the class they are placed and the `build()` method which returns object of type `User`:

    ```
    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder subscriptionStatus(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
        return this;
    }

    public abstract User build();
    ```

    `SimpleUserBuilder` and `PremiumUserBuilder` simply override `build()` method and do not change other inherited methods and fields; the classes are really compact:

    ```
    public class SimpleUserBuilder extends UserBuilder {
    
        @Override
        public User build() {
            return new SimpleUser(this);
        }
    }
    ```

    ```
    public class PremiumUserBuilder extends UserBuilder {

        @Override
        public User build() {
            return new PremiumUser(this);
        }
    }
    ```

* `creational/factory`

    The next folder contains a single rather compact class `UserFactory` which has method `getUser()` for creating different types of `User`s depending on the parameters:
    
    ```
    public static User getUser(String name, boolean subscriptionStatus, UserCategory category) {
        if (category == UserCategory.SimpleUser) {
            return new SimpleUser(name, subscriptionStatus);
        } else if (category == UserCategory.PremiumUser) {
            return new PremiumUser(name, subscriptionStatus);
        } else {
            return null;
        }
    }
    ```

    I also declared a small enum for describing `User` categories:

    ```
    public enum UserCategory{
        SimpleUser,
        PremiumUser
    }
    ```

* `creational/prototype`

    The prototype classes for the initial user classes differ only by a single method sfor creating a clone of the class they are placed in. Once again, `UserPrototype` is abstract and its child classes override its `getClone()` method:

    ```
    public abstract UserPrototype getClone();
    ```

    ```
    @Override
    public UserPrototype getClone() {
        SimpleUserPrototype sp = new SimpleUserPrototype(this.getName(), this.getSubscriptionStatus());
        sp.setID(this.getID());
        return sp;
    }
    ```

    ```
    @Override
    public UserPrototype getClone() {
        PremiumUserPrototype pp = new PremiumUserPrototype(this.getName(), this.getSubscriptionStatus());
        pp.setID(this.getID());
        return pp;
    }
    ```

* `creational/singleton`

    The final directory contains singletons for the database, api and server which are called `DatabaseSingleton`, `StreamingApiSingleton` and `StreamingPlatformSingleton`. The common thing about this classes is that they contain `private static` instance of itself and also a `static` method for getting an instance of the class. BElow are demonstrated these methods from each of the classes:

    ```
    public static DatabaseSingleton getDbInstance() {
        if (dbInstance == null) {
            dbInstance = new DatabaseSingleton();
        }
        return dbInstance;
    }
    ```

    ```
    public static StreamingApiSingleton getStreamingApiInstance() {
        if (streamingApiInstance == null) {
            streamingApiInstance = new StreamingApiSingleton();
        }
        return  streamingApiInstance;
    }
    ```

    ```
    public static StreamingPlatformSingleton getStreamingPlatformInstance() {
        if (streamingPlatformInstance == null) {
            streamingPlatformInstance = new StreamingPlatformSingleton();
        }
        return  streamingPlatformInstance;
    }
    ```


## Conclusions / Screenshots / Results 

For testing creational design patterns I modified the general main application class. First of all, I substituted server, api and database objects with their `singleton` versions. Also I created two `User` objects with the help of `UserBuilder` and `UserFactory`.

```
User u1 = UserFactory.getUser("John", true, UserCategory.SimpleUser);
UserBuilder ub = new PremiumUserBuilder();
User u2 = ub.name("Bella")
            .subscriptionStatus(false)
            .build();
```

Then I performed the same operations as before and ran the program:

```
src.client.SimpleUser John is trying to download Inception
The download speed will be half of the usual one
Server streams Inception to the src.client.SimpleUser John
Streaming platform suggests src.client.SimpleUser John to watch Split

src.client.PremiumUser Bella is trying to download Tenant
The download speed will be boosted
Server streams Tenant to the src.client.PremiumUser Bella
Streaming platform suggests src.client.PremiumUser Bella to watch Jaws
```

I also created a `UserPrototype` object to test whether its copy will produce the same output and it worked:

```
UserPrototype u3 = new SimpleUserPrototype("Patrick", false);
u3.watchMovie("Split", 120);
UserPrototype u4 = u3.getClone();
u4.watchMovie("Split", 120);
```

```
src.patterns.creational.prototype.SimpleUserPrototype Patrick is trying to watch Split of length (minutes) 120
Patrick will have 3 advertisement pauses
src.patterns.creational.prototype.SimpleUserPrototype Patrick is trying to watch Split of length (minutes) 120
Patrick will have 3 advertisement pauses
```

In conclusion, I would say that all the creational design patterns used in this laboratory work fit in perfectly in the concept of my project. The program runs succesfully and the classes are well-structured. However, I have to say that it is not necessary to use all of the creational patterns at the same time, while it can be not quite convenient and in fact not bring any benefits. It is a good practice to use creational design patterns if they are realy needed and simplify our work.