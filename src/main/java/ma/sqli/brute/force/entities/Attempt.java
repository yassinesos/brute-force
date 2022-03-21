package ma.sqli.brute.force.entities;

public class Attempt {

    private String username;
    private int nbOfAttempts;

    public Attempt(String username, int attempts) {
        this.username = username;
        this.nbOfAttempts = attempts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNbOfAttempts() {
        return nbOfAttempts;
    }

    public void setNbOfAttempts(int nbOfAttempts) {
        this.nbOfAttempts = nbOfAttempts;
    }

    public int incrementAttempts(){
        nbOfAttempts = nbOfAttempts + 1;
        return nbOfAttempts;
    }
}
