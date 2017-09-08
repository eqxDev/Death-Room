package me.eqxdev.deathroom.utils;

/**
 * Created by eqxDev.
 */
public class TimeUtil {

    public enum FormatType {

        SHORT_TIME, LONG_TIME

    }

    public static String format(long time, FormatType type) {
        time = time * 1000;
        if(type == FormatType.LONG_TIME) {
            int i = (int)(time/1000);
            StringBuilder builder = new StringBuilder();
            int weeks = 0, days = 0, hours = 0, minutes = 0;
            while(i >= 60){
                i-=60;
                minutes+=1;
            }
            while(minutes >= 60){
                minutes-=60;
                hours+=1;
            }
            while(hours >= 24){
                hours-=24;
                days+=1;
            }
            while(days >= 7){
                days-=7;
                weeks+=1;
            }
            boolean w = weeks != 0;
            boolean d = days != 0;
            boolean h = hours != 0;
            boolean m = minutes != 0;
            boolean s = i != 0;
            builder.append((w ? weeks + " week" + (weeks > 1 ? "s" : "") + (d && !(h && m && s) ? " and " : ", ") : ""));
            builder.append((d ? days + " day" + (days > 1 ? "s" : "") + (h && !(m && s) ? " and " : ", ")  : ""));
            builder.append((h ? hours + " hour" + (hours > 1 ? "s" : "") + (m && !s ? " and " : ", ")  : ""));
            builder.append((m ? minutes + " minute" + (minutes > 1 ? "s" : "") + (s ? " and " : "") : ""));
            builder.append((s ? i + " second" + (i > 1 ? "s" : "") + ", " : ""));

            String str = builder.toString().trim();

            if(str.endsWith(",")){
                str = str.substring(0, str.length()-1);
            }

            return str;
        } else if(type == FormatType.SHORT_TIME) {
            int i = (int)(time/1000);
            StringBuilder builder = new StringBuilder();
            int weeks = 0, days = 0, hours = 0, minutes = 0;
            while(i >= 60){
                i-=60;
                minutes+=1;
            }
            while(minutes >= 60){
                minutes-=60;
                hours+=1;
            }
            while(hours >= 24){
                hours-=24;
                days+=1;
            }
            while(days >= 7){
                days-=7;
                weeks+=1;
            }
            boolean w = weeks != 0;
            boolean d = days != 0;
            boolean h = hours != 0;
            boolean m = minutes != 0;
            boolean s = i != 0;
            if(weeks !=0) {
                builder.append(weeks + "w ");
            }
            if(days !=0) {
                builder.append(days + "d ");
            }
            if(hours !=0) {
                builder.append(hours + "h ");
            }
            if(minutes !=0) {
                builder.append(minutes + "m ");
            }
            builder.append(i + "s");
            String str = builder.toString().trim();

            if(str.endsWith(",")){
                str = str.substring(0, str.length()-1);
            }

            return str;
        }
        return "";
    }

}
