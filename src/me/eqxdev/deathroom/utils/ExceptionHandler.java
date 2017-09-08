package me.eqxdev.deathroom.utils;

/**
 * Created by eqxDev.
 */
public class ExceptionHandler {
    public static void handle(Exception e){
        System.err.println("An error has occured: " + e.getMessage());
    }

    public static void handle(Throwable t){
        System.err.println("An error has occured: " + t.getMessage());
    }

    public static String convert(Exception e){
        String str = "";
        for(StackTraceElement element : e.getStackTrace()){
            str += element.toString() + "\n";
        }
        return str;
    }


}
