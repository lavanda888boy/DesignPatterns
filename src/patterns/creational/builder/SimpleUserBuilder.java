package src.patterns.creational.builder;

import src.client.SimpleUser;
import src.client.User;

public class SimpleUserBuilder extends UserBuilder {
    
    @Override
    public User build() {
        return new SimpleUser(this);
    }
}
