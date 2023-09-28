package src.patterns.creational.factory;

import src.app.client.PremiumUser;
import src.app.client.SimpleUser;
import src.app.client.User;

public class UserFactory {
    
    public static User getUser(String name, boolean subscriptionStatus, UserCategory category) {
        if (category == UserCategory.SimpleUser) {
            return new SimpleUser(name, subscriptionStatus);
        } else if (category == UserCategory.PremiumUser) {
            return new PremiumUser(name, subscriptionStatus);
        } else {
            return null;
        }
    }

    public enum UserCategory{
        SimpleUser,
        PremiumUser
    }
}
