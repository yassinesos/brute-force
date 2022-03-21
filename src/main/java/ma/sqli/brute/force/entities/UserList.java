package ma.sqli.brute.force.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserList {
    private Set<User> users;
    private ListType listType;

    public UserList(ListType listType) {
        this.users = new HashSet<>();
        this.listType = listType;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }


    public boolean containsUser(User user){
        return users.contains(user.getUsername());
    }

    public User getUser(User user) {
        Iterator<User> it = users.iterator();
        while (it.hasNext()){
            User u = it.next();
            if(u.equals(user)) return u;
        }
        return null;
    }
}
