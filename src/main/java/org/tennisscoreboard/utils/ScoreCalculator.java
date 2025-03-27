package org.tennisscoreboard.utils;

import org.tennisscoreboard.models.CurrentMatch;

public class ScoreCalculator {
    private final CurrentMatch currentMatch;
    private final Set set;

    public ScoreCalculator(CurrentMatch currentMatch) {
        this.currentMatch = currentMatch;
        this.set = new Set(this.currentMatch);
    }

    public void addPointsTo(int scoreWinnerId) {
        if (scoreWinnerId == currentMatch.getFirstPlayer().getId()) {
            set.addPoints(1);
        } else if (scoreWinnerId == currentMatch.getSecondPlayer().getId()) {
            set.addPoints(2);
        }
        setPointsToCurrentMatch();
        if (isMatchOver()) {
            if (currentMatch.getFirstPlayerSets() > currentMatch.getSecondPlayerSets()) {
                currentMatch.setWinner(currentMatch.getFirstPlayer());
            } else currentMatch.setWinner(currentMatch.getSecondPlayer());
            set.resetSets();
        }
    }

    private void setPointsToCurrentMatch() {
        currentMatch.setFirstPlayerPointsView(set.getFirstPlayerPointsView());
        currentMatch.setSecondPlayerPointsView(set.getSecondPlayerPointsView());
        currentMatch.setFirstPlayerPoints(set.getFirstPlayerPoints());
        currentMatch.setSecondPlayerPoints(set.getSecondPlayerPoints());
        currentMatch.setFirstPlayerGames(set.getFirstPlayerGames());
        currentMatch.setSecondPlayerGames(set.getSecondPlayerGames());
        currentMatch.setFirstPlayerSets(set.getFirstPlayerSets());
        currentMatch.setSecondPlayerSets(set.getSecondPlayerSets());
    }

    public boolean isMatchOver() {
        return currentMatch.getFirstPlayerSets() == 2 ||
                currentMatch.getSecondPlayerSets() == 2;
    }
}
