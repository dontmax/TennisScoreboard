package org.tennisscoreboard.utils;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.models.GameScore;
import org.tennisscoreboard.models.PointScore;

import static org.tennisscoreboard.utils.PointMath.resetPoints;

@Getter
@Setter
public class GameMath {
    private static final int GAMES_TO_START_TIEBREAK = 6;
    private static final int PLAYER_ONE = 1;
    private static final int MIN_DIFFERENCE = 2;
    private static final int POINTS_TO_WIN = 4;
    private static final int POINTS_TO_WIN_TIEBREAK = 7;
    private static final boolean TIEBREAK_GOING = true;
    private static final boolean CLASSIC_GAME = false;

    private GameMath() {
    }

    public static GameScore addPoints(GameScore gameScore, int scoreWinnerId) {
        PointScore pointScore;
        int player1Games = gameScore.player1Games();
        int player2Games = gameScore.player2Games();
        boolean gameStatus;
        if (isTiebreakGoing(player1Games, player2Games)) {
            pointScore = PointMath.addTiebreakPoints(gameScore.pointScore(), scoreWinnerId);
            if (isTiebreakGameOver(pointScore.player1Points(), pointScore.player2Points())) {
                return addGames(player1Games, player2Games, scoreWinnerId);
            }
            gameStatus = TIEBREAK_GOING;
        } else {
            pointScore = PointMath.addPoints(gameScore.pointScore(), scoreWinnerId);
            if (isGameOver(pointScore.player1Points(), pointScore.player2Points())) {
                return addGames(player1Games, player2Games, scoreWinnerId);
            }
            gameStatus = CLASSIC_GAME;
        }
        return new GameScore(pointScore, gameStatus, player1Games, player2Games);
    }

    private static GameScore addGames(int player1Games, int player2Games, int scoreWinnerId) {
        if (scoreWinnerId == PLAYER_ONE) {
            player1Games++;
        } else {
            player2Games++;
        }
        return new GameScore(resetPoints(),
                CLASSIC_GAME,
                player1Games,
                player2Games);
    }

    private static boolean isTiebreakGoing(int player1Games, int player2Games) {
        return player1Games == GAMES_TO_START_TIEBREAK &&
                player2Games == GAMES_TO_START_TIEBREAK;
    }

    public static GameScore resetGames() {
        return new GameScore(resetPoints(), CLASSIC_GAME, 0, 0);
    }

    private static boolean isGameOver(int player1Points, int player2Points) {
        return (player1Points >= POINTS_TO_WIN && (player1Points - player2Points) >= MIN_DIFFERENCE) ||
                (player2Points >= POINTS_TO_WIN && (player2Points - player1Points) >= MIN_DIFFERENCE);
    }

    private static boolean isTiebreakGameOver(int player1Points, int player2Points) {
        return (player1Points >= POINTS_TO_WIN_TIEBREAK && (player1Points - player2Points) >= MIN_DIFFERENCE) ||
                (player2Points >= POINTS_TO_WIN_TIEBREAK && (player2Points - player1Points) >= MIN_DIFFERENCE);
    }

}
