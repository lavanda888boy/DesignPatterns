package src.patterns.creational.builder;

import src.client.PremiumUser;
import src.client.User;

public class PremiumUserBuilder extends UserBuilder {
    
    @Override
    public User build() {
        return new PremiumUser(this);
    }
}
