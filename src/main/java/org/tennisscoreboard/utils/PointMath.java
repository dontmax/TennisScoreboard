package org.tennisscoreboard.utils;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.models.PointScore;

@Getter
@Setter
public class PointMath {

    private static final int DEUCE = 4;

    private PointMath() {
    }

    public static PointScore addPoints(PointScore pointScore, int scoreWinnerId) {
        int player1Points = pointScore.player1Points();
        int player2Points = pointScore.player2Points();
        if (scoreWinnerId == 1) {
            player1Points++;
        } else {
            player2Points++;
        }
        if (player1Points == DEUCE && player2Points == DEUCE) {
            player1Points--;
            player2Points--;
        }
        return new PointScore(
                player1Points,
                player2Points
        );
    }

    public static PointScore addTiebreakPoints(PointScore pointScore, int scoreWinnerId) {
        int player1Points = pointScore.player1Points();
        int player2Points = pointScore.player2Points();
        if (scoreWinnerId == 1) {
            player1Points++;
        } else {
            player2Points++;
        }
        return new PointScore(
                player1Points,
                player2Points
        );
    }

    public static PointScore resetPoints() {
        return new PointScore(0, 0);
    }

}
