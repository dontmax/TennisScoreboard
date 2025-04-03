package org.tennisscoreboard.models;

public record SetScore(    GameScore gameScore,
                           int player1Sets,
                           int player2Sets) {
}
