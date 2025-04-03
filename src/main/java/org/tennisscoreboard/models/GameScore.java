package org.tennisscoreboard.models;

public record GameScore(    PointScore pointScore,
                            boolean tiebreak,
                            int player1Games,
                            int player2Games) {
}
