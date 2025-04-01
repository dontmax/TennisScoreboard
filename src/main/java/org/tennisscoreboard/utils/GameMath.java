package org.tennisscoreboard.utils;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.models.CurrentMatch;

@Getter
@Setter
public class GameMath extends PointMath {
    private int firstPlayerGames = 0;
    private int secondPlayerGames = 0;
    private static final int MIN_DIFFERENCE = 2;
    private static final int POINTS_TO_WIN = 4;
    private static final int POINTS_TO_WIN_TIEBREAK = 7;

    protected GameMath(CurrentMatch currentMatch) {
        super(currentMatch.getFirstPlayerPoints(), currentMatch.getSecondPlayerPoints());
        this.firstPlayerGames = currentMatch.getFirstPlayerGames();
        this.secondPlayerGames = currentMatch.getSecondPlayerGames();

    }

    protected void addPoints(int points) {
        if (secondPlayerGames == firstPlayerGames && firstPlayerGames == 6) {
            super.addTiebreakPoints(points);
            if (isTiebreakGameOver()) {
                if (getFirstPlayerPoints() > getSecondPlayerPoints()) {
                    firstPlayerGames++;
                } else {
                    secondPlayerGames++;
                }
                resetPoints();
            }
        } else {
            super.addPoints(points);
            if (isGameOver()) {
                if (getFirstPlayerPoints() > getSecondPlayerPoints()) {
                    firstPlayerGames++;
                } else {
                    secondPlayerGames++;
                }
                resetPoints();
            }
        }
    }

    protected void resetGames() {
        firstPlayerGames = 0;
        secondPlayerGames = 0;
    }

    private boolean isGameOver() {
        return (getFirstPlayerPoints() >= POINTS_TO_WIN && (getFirstPlayerPoints() - getSecondPlayerPoints()) >= MIN_DIFFERENCE) ||
                (getSecondPlayerPoints() >= POINTS_TO_WIN && (getSecondPlayerPoints() - getFirstPlayerPoints()) >= MIN_DIFFERENCE);
    }

    private boolean isTiebreakGameOver() {
        return (getFirstPlayerPoints() >= POINTS_TO_WIN_TIEBREAK && (getFirstPlayerPoints() - getSecondPlayerPoints()) >= MIN_DIFFERENCE) ||
                (getSecondPlayerPoints() >= POINTS_TO_WIN_TIEBREAK && (getSecondPlayerPoints() - getFirstPlayerPoints()) >= MIN_DIFFERENCE);
    }
}
