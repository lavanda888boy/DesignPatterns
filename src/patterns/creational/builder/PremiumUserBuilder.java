package src.patterns.creational.builder;

import src.app.client.PremiumUser;
import src.app.client.User;

public class PremiumUserBuilder extends UserBuilder {
    
    @Override
    public User build() {
        return new PremiumUser(this);
    }
}
