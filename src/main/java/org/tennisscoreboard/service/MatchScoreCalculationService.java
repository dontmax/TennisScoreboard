package org.tennisscoreboard.service;

import org.tennisscoreboard.utils.SetMath;
import org.tennisscoreboard.models.CurrentMatch;

public class MatchScoreCalculationService {
    private static final int SETS_TO_WIN = 2;
    private final SetMath setMath;
    private final CurrentMatch currentMatch;

    public MatchScoreCalculationService(CurrentMatch currentMatch) {
        this.currentMatch = currentMatch;
        setMath = new SetMath(this.currentMatch);
    }

    public void addPoints(int scoreWinnerId) {
        setMath.addPoints(scoreWinnerId);
        setPointsToCurrentMatch();
        if(isMatchOver(
                currentMatch.getFirstPlayerSets(),
                currentMatch.getSecondPlayerSets()
        )) {
            if (currentMatch.getFirstPlayer().getId() == scoreWinnerId) {
                currentMatch.setWinner(currentMatch.getFirstPlayer());
            } else currentMatch.setWinner(currentMatch.getSecondPlayer());
            setMath.resetSets();
        }
    }

    private void setPointsToCurrentMatch() {
        currentMatch.setFirstPlayerPointsView(setMath.getFirstPlayerPointsView());
        currentMatch.setSecondPlayerPointsView(setMath.getSecondPlayerPointsView());
        currentMatch.setFirstPlayerPoints(setMath.getFirstPlayerPoints());
        currentMatch.setSecondPlayerPoints(setMath.getSecondPlayerPoints());
        currentMatch.setFirstPlayerGames(setMath.getFirstPlayerGames());
        currentMatch.setSecondPlayerGames(setMath.getSecondPlayerGames());
        currentMatch.setFirstPlayerSets(setMath.getFirstPlayerSets());
        currentMatch.setSecondPlayerSets(setMath.getSecondPlayerSets());
    }

    private boolean isMatchOver(int playerOneSets, int playerTwoSets) {
        return playerOneSets == SETS_TO_WIN ||
                playerTwoSets == SETS_TO_WIN;
    }

}
