package me.eqxdev.deathroom.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by eqxDev.
 */
public class Lists {

    @SafeVarargs
    public static <T> List<T> asList(T... t){
        List<T> list = new ArrayList<>();

        for(T type : t){
            list.add(type);
        }

        return list;
    }

    public static <T> List<T> asList(Collection<T> collection){
        return new ArrayList<>(collection);
    }

    public static <T> void addAll(List<T> list, Collection<T> collection){
        list.addAll(asList(collection));
    }

    @SafeVarargs
    public static <T> void addAll(List<T> list, T... t){
        list.addAll(asList(t));
    }

    public static boolean containsIgnoreCase(List<String> list, String string){
        for(String s : list){
            if(s.equalsIgnoreCase(string)) return true;
        }
        return false;
    }

}
