package org.tennisscoreboard.service;

import org.tennisscoreboard.models.CurrentMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CurrentMatchesService {

    static Map<UUID, CurrentMatch> currentMatches = new HashMap<>();

    public static void add(UUID matchId, CurrentMatch currentMatch){
        currentMatches.put(matchId, currentMatch);
    }

    public static CurrentMatch get(UUID uuid){
        return currentMatches.get(uuid);
    }

    public static void delete(UUID uuid){
        currentMatches.remove(uuid);
    }
}
