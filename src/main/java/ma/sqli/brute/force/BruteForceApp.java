package ma.sqli.brute.force;

import ma.sqli.brute.force.entities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author : El Mahdi Benzekri
 * @since : 3/7/21, dim.
 **/
public class BruteForceApp {

    private List<String> blacklist = new ArrayList<>();
    private Map<String,String> usersList = new HashMap<>();
    private List<String> login = new ArrayList<>();
    private String message;
    private Map<String,Integer> numberOfAttempt = new HashMap<>();
    private Map<String,Integer> numberOfAttemptAndroid = new HashMap<>();

//////////////////////////////////////

   // private LoginSession loginSession = new LoginSession();

    private List<LoginSession> loginSessions = new ArrayList<>();
    private UserList blackList = new UserList(ListType.BlackList);
    private UserList whiteList = new UserList(ListType.WhiteList);
    LoginSession loginSession;
    Device device;
    Device android;
    public String login(String username, String password) {
        User user = new User(username, password);

        if(!loginSessions.contains(new LoginSession(user, DeviceType.Laptop))) {
            loginSession = new LoginSession(user, DeviceType.Laptop);
        }

        if(device == null) device = new Device(DeviceType.Laptop, new NormalSession(user));

        if(blackList.containsUser(user)) message = "Your account is blacklisted, contact the CRC to resolve the problem.";
        else if(user.checkPasswordStrength()) {
            loginSessions.add(loginSession);
            message = "Your password is too weak, please update it by going to your my account.";
            if(loginSession.numberOfSessions(loginSessions) > 1) message += " - We detected that your account is logged in multiple devices";
        } else if(whiteList.getUser(user).getPassword().equals(password)){
            loginSessions.add(loginSession);
            message = "Welcome sqli!";
            if(user.getUsername().equals("admin")) {
                message = "Welcome admin!";
            }
            loginSession.getLoginSession(loginSessions).getDevice().getAttempts().setNbOfAttempts(0);
        }else if(!whiteList.getUser(user).getPassword().equals(user.getPassword())) {
            device.getAttempts().incrementAttempts();
            message = "User or password are incorrect.";
           if(device.getAttempts().getNbOfAttempts() >= 3) message = "Multiple erroneous credentials, your account is locked.";
            //if(loginSession.totalOfAttemptsInAllSessions(loginSessions) >= 2) message = "Multiple erroneous credentials, your account is locked.";
            if(loginSessions.contains(user)) loginSessions.remove(user);

        }
        if(loginSession.numberOfSessions(loginSessions) > 1 && user.getPassword().length() > 1) message = "We detected that your account is logged in multiple devices";

        return message;
    }

    public void addUser(String username, String password) {
        whiteList.getUsers().add(new User(username, password));
    }

    public String loginWithAndroid(String username, String password) {
        User user = new User(username, password);

        if(!loginSessions.contains(new LoginSession(user, DeviceType.Android))) {
            loginSession = new LoginSession(user, DeviceType.Android);
        }

        if(android == null) android = new Device(DeviceType.Android, new NormalSession(user));


        if(whiteList.getUser(user).equals(user.getPassword())){
            loginSessions.add(loginSession);
            message = "Welcome sqli!";
            if(username.equals("admin")) {
                message = "Welcome admin!";
                return message;
            }
            android.getAttempts().setNbOfAttempts(0);
        }else if(!whiteList.getUser(user).getPassword().equals(password)) {
            android.getAttempts().incrementAttempts();
            message = "User or password are incorrect.";
            if(android.getAttempts().getNbOfAttempts() >= 3) message = "Multiple erroneous credentials, your account is locked.";
        }
        return message;
    }

    public void blacklist(String sqli) {
        blackList.getUsers().add(new User(sqli));
    }
}
