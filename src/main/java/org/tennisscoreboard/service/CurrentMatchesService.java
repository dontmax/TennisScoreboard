package org.tennisscoreboard.service;

import org.tennisscoreboard.models.CurrentMatch;

import java.util.HashMap;
import java.util.Map;

public class CurrentMatchesService {

    static Map<String, CurrentMatch> currentMatches = new HashMap<>();

    public static void add(String matchId, CurrentMatch currentMatch){
        currentMatches.put(matchId, currentMatch);
    }

    public static CurrentMatch get(String uuid){
        return currentMatches.get(uuid);
    }

    public static void remove(String uuid){
        currentMatches.remove(uuid);
    }
}
