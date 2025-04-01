package org.tennisscoreboard.service;

import org.tennisscoreboard.models.CurrentMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchesService {

    private static final CurrentMatchesService INSTANCE = new CurrentMatchesService();
    private final ConcurrentHashMap<String, CurrentMatch> currentMatches = new ConcurrentHashMap<>();

    private CurrentMatchesService() {}

    public static CurrentMatchesService getInstance() {
        return INSTANCE;
    }

    public void add(String matchId, CurrentMatch currentMatch) {
        currentMatches.put(matchId, currentMatch);
    }

    public CurrentMatch get(String uuid) {
        return currentMatches.get(uuid);
    }

    public void remove(String uuid) {
        currentMatches.remove(uuid);
    }

    public boolean contains(String matchId) {
        return currentMatches.containsKey(matchId);
    }
}
