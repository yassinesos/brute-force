package ma.sqli.brute.force.entities;

import java.util.Objects;

public class NormalSession implements Session{
    private User user;
    //private Device device;

    public NormalSession() {
    }

    public NormalSession(User user/*, Device device*/) {
        this.user = user;
        //this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalSession that = (NormalSession) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public User getUser() {
        return user;
    }
}
