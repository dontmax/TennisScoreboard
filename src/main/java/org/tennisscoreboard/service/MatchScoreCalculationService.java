package org.tennisscoreboard.service;

import org.tennisscoreboard.models.*;
import org.tennisscoreboard.utils.SetMath;

public class MatchScoreCalculationService {
    private static final int SETS_TO_WIN = 2;
    private static final int PLAYER_ONE = 1;

    private MatchScoreCalculationService() {}

    public static void addPoints(CurrentMatch currentMatch, int scoreWinnerId) {
        SetScore setScore;
        setScore = SetMath.addPoints(mapToSetScore(currentMatch.getScore()), scoreWinnerId);
        boolean isMatchOver = isMatchOver(setScore.player1Sets(), setScore.player2Sets());
        if(isMatchOver) {
            if(scoreWinnerId==PLAYER_ONE) {
                currentMatch.setWinner(currentMatch.getFirstPlayer());
            } else {
                currentMatch.setWinner(currentMatch.getSecondPlayer());
            }
        }
        setPointsToCurrentMatch(currentMatch, isMatchOver?SetMath.resetSets1():setScore);
    }

    private static SetScore mapToSetScore(MatchScore matchScore) {
        return new SetScore(
                new GameScore(
                        new PointScore(matchScore.getFirstPlayerPoints(), matchScore.getSecondPlayerPoints()),
                        matchScore.isTiebreak(),
                        matchScore.getFirstPlayerGames(),
                        matchScore.getSecondPlayerGames()),
                matchScore.getFirstPlayerSets(),
                matchScore.getSecondPlayerSets()
        );
    }

    private static boolean isMatchOver(int player1Sets, int player2Sets) {
        return player1Sets == SETS_TO_WIN ||
               player2Sets == SETS_TO_WIN;
    }

    private static void setPointsToCurrentMatch(CurrentMatch currentMatch, SetScore setScore) {
        MatchScore matchScore = currentMatch.getScore();
        matchScore.setFirstPlayerPoints(setScore.gameScore().pointScore().player1Points());
        matchScore.setSecondPlayerPoints(setScore.gameScore().pointScore().player2Points());
        matchScore.setTiebreak(setScore.gameScore().tiebreak());
        matchScore.setFirstPlayerGames(setScore.gameScore().player1Games());
        matchScore.setSecondPlayerGames(setScore.gameScore().player2Games());
        matchScore.setFirstPlayerSets(setScore.player1Sets());
        matchScore.setSecondPlayerSets(setScore.player2Sets());
    }

}
