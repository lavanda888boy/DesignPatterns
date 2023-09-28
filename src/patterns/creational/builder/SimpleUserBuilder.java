package src.patterns.creational.builder;

import src.app.client.SimpleUser;
import src.app.client.User;

public class SimpleUserBuilder extends UserBuilder {
    
    @Override
    public User build() {
        return new SimpleUser(this);
    }
}
