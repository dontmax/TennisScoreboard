package org.tennisscoreboard.service;

import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.utils.ScoreCalculator;

public class MatchScoreCalculationService {
    CurrentMatch currentMatch;
    ScoreCalculator ScoreCalculator;

    public MatchScoreCalculationService(CurrentMatch currentMatch) {
        this.currentMatch = currentMatch;
        ScoreCalculator = new ScoreCalculator(this.currentMatch);
    }
    public void addPointsTo(int scoreWinnerId){
        ScoreCalculator.addPointsTo(scoreWinnerId);
    }

    public boolean isMatchOver(){
        return ScoreCalculator.isMatchOver();
    }
}
