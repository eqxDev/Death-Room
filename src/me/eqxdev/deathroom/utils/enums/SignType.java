package me.eqxdev.deathroom.utils.enums;

/**
 * Created by eqxDev.
 */
public enum SignType {

    TIMER("timer"), LIVES("lives"), UNNOWN("unnown");


    private String name;
    SignType(String str) {
        this.name = str;
    }

    public String toString() {
        return name;
    }

    public static SignType getFromString(String str) {
        if(str.equalsIgnoreCase("timer")) return SignType.TIMER;
        if(str.equalsIgnoreCase("lives")) return SignType.LIVES;
        return SignType.UNNOWN;
    }

}
