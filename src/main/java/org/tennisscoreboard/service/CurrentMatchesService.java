package org.tennisscoreboard.service;

import org.tennisscoreboard.dto.PlayerDTO;
import org.tennisscoreboard.models.CurrentMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchesService {

    private static final CurrentMatchesService INSTANCE = new CurrentMatchesService();
    private final ConcurrentHashMap<String, CurrentMatch> currentMatches = new ConcurrentHashMap<>();

    private CurrentMatchesService() {}

    public static CurrentMatchesService getInstance() {
        return INSTANCE;
    }

    public String add(String firstPlayerName, String secondPlayerName) {
        String matchId = UUID.randomUUID().toString();
        currentMatches.put(matchId, create(firstPlayerName, secondPlayerName));
        return matchId;
    }

    private CurrentMatch create(String firstPlayerName, String secondPlayerName) {
        int PLAYER_ONE = 1;
        int PLAYER_TWO = 2;
        PlayerDTO player1 = new PlayerDTO(PLAYER_ONE, firstPlayerName);
        PlayerDTO player2 = new PlayerDTO(PLAYER_TWO, secondPlayerName);
        return new CurrentMatch(player1, player2);
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
