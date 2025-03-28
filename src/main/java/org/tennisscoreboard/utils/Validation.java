package org.tennisscoreboard.utils;

public class Validation {

    public static boolean isName(String text) {
        return text.trim().matches("^[A-Z][a-zA-Z0-9\\s]{0,24}$");
    }

    public static boolean areNamesSame(String firstPlayerName, String secondPlayerName) {
        return firstPlayerName.trim().equalsIgnoreCase(secondPlayerName.trim());
    }
}
