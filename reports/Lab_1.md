# SOLID Principles


## Author: Bajenov Sevastian, FAF-213

## Objectives:

* Get familiar with the SOLID Principles;
* Choose a specific domain;
* Develop the project for the specific domain taking into account SOLID Principles; 


## Used SOLID Principles: 

* Single Responsibility principle
* Open-Closed principle
* Liskov Substitution principle
* Interface Segregation principle
* Dependency Inversion principle


## Project Description and SOLID Implementation

The project I decided to develop throughout this course is nothiing else but a simplified analogue of a movie streaming service. It has three main elements: `Client`, `Server` and `Api` which is used to connect client to the server.

`Client` module is implemented using three classes: parent `User` and its children `SimpleUser` and `PremiumUser`. they have some basic attributes like `name` and `ID` but there behavior varies because of the three methods declared in the parent class and overriden in the child classes:
```
public void watchMovie(String movieName, int episodeTime) {
    ...

public void downloadMovie(String movieName) {
    ...

public void cancelSubscription() {
    ...
```

`Server` module is represented by the `StreamingPlatform` class which implements two interfaces `Streaming`:
```
public interface Streaming {
    
    void displayMovie(User user, String movieName);

    void streamMovie(User user, String movieName);
}
```

and `Advertisement`:
```
public interface Advertisement {
    
    void handleSubscriptionStatus(User user);

    void sendRecommendations(User user);
}
```

In this way I managed to make my project corresponding to the `interface segregation principle`. By using interfaces in my project I also reached `dependency inversion` principle because my classes depend on abstract "sets of rules" rather then on concrete implementations.

Returning to the `StreamingPlatform` class, it contains the single `Database` attribute (class storing service users and films in a primitive way) and a few methods similar to the ones declared in the `User` class:
```
@Override
public void displayMovie(User user, String movieName) {
    ...

@Override
public void streamMovie(User user, String movieName) {
    ...

@Override
public void handleSubscriptionStatus(User user) {
    ...

@Override
public void sendRecommendations(User user) {
    ...
```

It should be mentioned here that this class corresponds to the `Liskov substitution` principle because these methods will receive objects of types `SimpleUser` and `PremiumUser` and still will work properly.

Finally, `Api` module of my streaming service app contains `StreamingApi` class which implements interface `ClientServerCommunication`:
```
public interface ClientServerCommunication {
    
    void watchContent(String contentName, int contentSize);

    void downloadContent(String contentName);

    void cancelContentFetch();
}
```

It has two attributes: `client` and `server`; `client` object will vary depending on the client connecting to the service while `server` object will remain constant. Each method will call the corresponding methods in the client module and then in the server module performing the desired connection.

That was a short introduction into my project. I did not mention `single responsibility` and `open-closed` principles but their impact can be noticed in the variety of single-purposed classes and in they way they were depending on the interfaces. It is only necessary to add or remove a method from an interface to change the behavior of the whole class without writing too much code.


## Conclusions / Screenshots / Results