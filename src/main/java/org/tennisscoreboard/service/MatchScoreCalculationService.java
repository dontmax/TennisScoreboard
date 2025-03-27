package org.tennisscoreboard.service;

import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.utils.ScoreCalculator;

public class MatchScoreCalculationService {
    private final ScoreCalculator ScoreCalculator;

    public MatchScoreCalculationService(CurrentMatch currentMatch) {
        ScoreCalculator = new ScoreCalculator(currentMatch);
    }

    public void addPointsTo(int scoreWinnerId) {
        ScoreCalculator.addPointsTo(scoreWinnerId);
    }

    public boolean isMatchOver() {
        return ScoreCalculator.isMatchOver();
    }
}
