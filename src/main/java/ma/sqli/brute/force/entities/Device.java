package ma.sqli.brute.force.entities;

import java.util.ArrayList;
import java.util.List;

public class Device {
    private DeviceType deviceType;
    private Attempt attempts;
    private Session session;

    public Device(DeviceType deviceType, Session session) {
        this.deviceType = deviceType;
        this.attempts = new Attempt(session.getUser().getUsername(),0);
        this.session = session;
    }


    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(LoginSession loginSession) {
        this.session = session;
    }

    public Attempt getAttempts() {
        return attempts;
    }

    public void setAttempts(Attempt attempts) {
        this.attempts = attempts;
    }

    @Override
    public boolean equals(Object o){
        Device that = (Device) o;
        return this.session.equals(that.session);
    }


}
