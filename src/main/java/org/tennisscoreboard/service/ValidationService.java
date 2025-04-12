package org.tennisscoreboard.service;

import org.tennisscoreboard.utils.Validation;

public class ValidationService {
    private static final String NAMING_RULE = " Player name must begin with uppercase letter, max length: 25 symbols, not \"";

    private ValidationService() {}

    public static String validateNames(String firstPlayerName, String secondPlayerName) {
        boolean validation = true;
        String errorMessage="";
        if (!Validation.isName(firstPlayerName)) {
            errorMessage = "First" + NAMING_RULE + firstPlayerName + "\"";
            validation = false;
        }
        if (validation && !Validation.isName(secondPlayerName)) {
            errorMessage = "Second" + NAMING_RULE + secondPlayerName + "\"";
            validation = false;
        }
        if (validation && Validation.areNamesSame(firstPlayerName, secondPlayerName)) {
            errorMessage = "Names can't be the same";
        }
        return errorMessage;
    }

}
