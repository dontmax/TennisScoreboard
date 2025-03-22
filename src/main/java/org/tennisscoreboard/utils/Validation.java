package org.tennisscoreboard.utils;

public class Validation {

    public static boolean isName(String text){
        return text.matches("^[A-Z][a-zA-Z\\s]{0,24}$");
    }
}
