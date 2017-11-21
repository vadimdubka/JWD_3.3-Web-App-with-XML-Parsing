package com.dubatovka.app.entity;

import java.util.Objects;

public class Player extends JAuctionUser {
    
    private PlayerProfile profile;
    
    public PlayerProfile getProfile() {
        return profile;
    }
    
    public void setProfile(PlayerProfile profile) {
        this.profile = profile;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(profile, player.profile);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), profile);
    }
    
    
    @Override
    public String toString() {
        return "Player{" + super.toString() +
                "profile=" + profile +
                '}';
    }
    
    
}