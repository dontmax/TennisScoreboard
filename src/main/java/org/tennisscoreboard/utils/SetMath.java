package org.tennisscoreboard.utils;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.models.CurrentMatch;

@Getter
@Setter
public class SetMath extends GameMath {
    private int firstPlayerSets = 0;
    private int secondPlayerSets = 0;
    private static final int MIN_DIFFERENCE = 2;
    private static final int GAMES_TO_WIN = 6;
    private static final int GAMES_TO_WIN_TIEBREAK = 7;

    public SetMath(CurrentMatch currentMatch) {
        super(currentMatch);
        this.firstPlayerSets = currentMatch.getFirstPlayerSets();
        this.secondPlayerSets = currentMatch.getSecondPlayerSets();
    }

    public void addPoints(int points) {
        super.addPoints(points);
        if (isSetOver()) {
            if (getFirstPlayerGames() > getSecondPlayerGames()) {
                firstPlayerSets++;
            } else {
                secondPlayerSets++;
            }
            resetGames();
        }
    }

    public void resetSets() {
        firstPlayerSets = 0;
        secondPlayerSets = 0;
    }

    public boolean isSetOver() {
        return (getFirstPlayerGames() >= GAMES_TO_WIN && getFirstPlayerGames() - getSecondPlayerGames() >= MIN_DIFFERENCE) ||
                (getSecondPlayerGames() >= GAMES_TO_WIN && getSecondPlayerGames() - getFirstPlayerGames() >= MIN_DIFFERENCE) ||
                (getFirstPlayerGames() == GAMES_TO_WIN_TIEBREAK) ||
                (getSecondPlayerGames() == GAMES_TO_WIN_TIEBREAK);
    }
}
