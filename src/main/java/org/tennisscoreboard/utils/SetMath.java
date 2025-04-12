package org.tennisscoreboard.utils;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.models.GameScore;
import org.tennisscoreboard.models.SetScore;

@Getter
@Setter
public class SetMath {
    private static final int PLAYER_ONE = 1;
    private static final int MIN_DIFFERENCE = 2;
    private static final int GAMES_TO_WIN = 6;
    private static final int GAMES_TO_WIN_TIEBREAK = 7;

    private SetMath() {
    }

    public static SetScore addPoints(SetScore setScore, int scoreWinnerId) {
        int player1Sets = setScore.player1Sets();
        int player2Sets = setScore.player2Sets();
        GameScore gameScore = GameMath.addPoints(setScore.gameScore(), scoreWinnerId);
        if (isSetOver(gameScore.player1Games(), gameScore.player2Games())) {
            if (scoreWinnerId == PLAYER_ONE) {
                player1Sets++;
            } else {
                player2Sets++;
            }
            return new SetScore(GameMath.resetGames(), player1Sets, player2Sets);
        }
        return new SetScore(gameScore, player1Sets, player2Sets);
    }

    public static SetScore resetSets1() {
        return new SetScore(GameMath.resetGames(), 0, 0);
    }

    public static boolean isSetOver(int player1Games, int player2Games) {
        return (player1Games >= GAMES_TO_WIN && player1Games - player2Games >= MIN_DIFFERENCE) ||
                (player2Games >= GAMES_TO_WIN && player2Games - player1Games >= MIN_DIFFERENCE) ||
                (player1Games == GAMES_TO_WIN_TIEBREAK) ||
                (player2Games == GAMES_TO_WIN_TIEBREAK);
    }

}
