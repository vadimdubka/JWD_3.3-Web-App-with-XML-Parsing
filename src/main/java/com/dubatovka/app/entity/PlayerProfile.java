package com.dubatovka.app.entity;

import java.util.Objects;

public class PlayerProfile {
    
    private String fName;
    private String mName;
    private String lName;
    
    public String getfName() {
        return fName;
    }
    
    public void setfName(String fName) {
        this.fName = fName;
    }
    
    public String getmName() {
        return mName;
    }
    
    public void setmName(String mName) {
        this.mName = mName;
    }
    
    public String getlName() {
        return lName;
    }
    
    public void setlName(String lName) {
        this.lName = lName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerProfile)) {
            return false;
        }
        PlayerProfile profile = (PlayerProfile) o;
        return Objects.equals(fName, profile.fName) &&
                Objects.equals(mName, profile.mName) &&
                Objects.equals(lName, profile.lName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(fName, mName, lName);
    }
    
    @Override
    public String toString() {
        return "PlayerProfile{" + "fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", lName='" + lName +
                '}';
    }
    
}