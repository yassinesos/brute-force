package ma.sqli.brute.force.entities;


import java.util.*;

public class LoginSession implements Session {
    private Device device;
    private User user;

    public LoginSession(DeviceType deviceType) {
        device = new Device(deviceType,this);
    }

    public LoginSession(User user, DeviceType deviceType) {
        this.user = user;
        device = new Device(deviceType,this);
    }


    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginSession that = (LoginSession) o;
        return user.equals(that.user);
    }

    public int numberOfSessions(List<LoginSession> loginSessions){
        return Collections.frequency(loginSessions, this);
    }

    public int totalOfAttemptsInAllSessions(List<LoginSession> loginSessions){
        //return Collections.frequency(loginSessions, this);
        int attempt = 0;
        for (LoginSession loginSession : loginSessions){
            if(this.equals(loginSession)) attempt += loginSession.getDevice().getAttempts().getNbOfAttempts();
        }
        return attempt;
    }


    public LoginSession getLoginSession(List<LoginSession> loginSessions) {
        Iterator<LoginSession> it = loginSessions.iterator();
        while (it.hasNext()){
            LoginSession l = it.next();
            if(l.getUser().equals(user)) return l;
        }
        return null;
    }


}
