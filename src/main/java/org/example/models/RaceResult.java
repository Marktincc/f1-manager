package org.example.models;

public class RaceResult {
    private Driver driver;
    private Team team;
    private int position;
    private boolean fastestLap;
    private boolean dnf;
    
    public RaceResult() {
        // Default constructor for JSON deserialization
    }
    
    public RaceResult(Driver driver, Team team, int position, boolean fastestLap, boolean dnf) {
        this.driver = driver;
        this.team = team;
        this.position = position;
        this.fastestLap = fastestLap;
        this.dnf = dnf;
    }
    
    // Getters and Setters
    public Driver getDriver() {
        return driver;
    }
    
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    
    public Team getTeam() {
        return team;
    }
    
    public void setTeam(Team team) {
        this.team = team;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    public boolean hasFastestLap() {
        return fastestLap;
    }
    
    public void setFastestLap(boolean fastestLap) {
        this.fastestLap = fastestLap;
    }
    
    public boolean isDnf() {
        return dnf;
    }
    
    public void setDnf(boolean dnf) {
        this.dnf = dnf;
    }
    
    public int getPoints() {
        if (dnf || position > 10) {
            return 0;
        }
        
        int points = 0;
        switch (position) {
            case 1: points = 25; break;
            case 2: points = 18; break;
            case 3: points = 15; break;
            case 4: points = 12; break;
            case 5: points = 10; break;
            case 6: points = 8; break;
            case 7: points = 6; break;
            case 8: points = 4; break;
            case 9: points = 2; break;
            case 10: points = 1; break;
        }
        
        if (fastestLap && position <= 10) {
            points += 1;
        }
        
        return points;
    }
    
    @Override
    public String toString() {
        return position + ". " + driver.getName() + " (" + team.getName() + ")" + 
               (fastestLap ? " [FL]" : "") + (dnf ? " [DNF]" : "");
    }
}