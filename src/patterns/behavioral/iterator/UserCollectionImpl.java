package src.patterns.behavioral.iterator;

import src.app.client.User;

import java.util.Iterator;
import java.util.List;

public class UserCollectionImpl implements UserCollection {

    private List<User> userList;

    public UserCollectionImpl(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public void addUser(User user) {
        this.userList.add(user);
    }

    @Override
    public void removeUser(User user) {
        this.userList.remove(user);
    }

    @Override
    public Iterator<User> iterator() {
        return new UserIterator(this.userList);
    }

    private class UserIterator implements Iterator<User> {

        private List<User> users;
        private int index;

        public UserIterator(List<User> users) {
            this.users = users;
        }

        @Override
        public boolean hasNext() {
            if (this.index < this.users.size() - 1) {
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
}
