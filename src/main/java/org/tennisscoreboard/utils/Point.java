package org.tennisscoreboard.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {

    private int firstPlayerPoints = 0;
    private int secondPlayerPoints = 0;
    private String firstPlayerPointsView = "0";
    private String secondPlayerPointsView = "0";

    protected Point(int firstPlayerPoints, int secondPlayerPoints) {
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
    }

    protected void addPoints(int pointsTo) {
        if (pointsTo == 1) {
            firstPlayerPoints++;
        } else if (pointsTo == 2) {
            secondPlayerPoints++;
        }
        if (firstPlayerPoints == 4 && secondPlayerPoints == 4) {
            firstPlayerPoints--;
            secondPlayerPoints--;
        }
        setPointsView(firstPlayerPoints, secondPlayerPoints);
    }

    protected void addTiebreakPoints(int pointsTo) {
        if (pointsTo == 1) {
            firstPlayerPoints++;
        } else if (pointsTo == 2) {
            secondPlayerPoints++;
        }
        setTiebreakPointsView(firstPlayerPoints, secondPlayerPoints);
    }

    private void setPointsView(int firstPlayerPoints, int secondPlayerPoints) {
        firstPlayerPointsView = setPointsView(firstPlayerPoints);
        secondPlayerPointsView = setPointsView(secondPlayerPoints);
    }

    private String setPointsView(int playerPoints) {
        return switch (playerPoints) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            case 4 -> "AD";
            default -> "";
        };
    }

    private void setTiebreakPointsView(int firstPlayerPoints, int secondPlayerPoints) {
        firstPlayerPointsView = setTiebreakPointsView(firstPlayerPoints);
        secondPlayerPointsView = setTiebreakPointsView(secondPlayerPoints);
    }

    private String setTiebreakPointsView(int playerPoints) {
        return playerPoints + "";
    }

    protected void resetPoints() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        firstPlayerPointsView = "0";
        secondPlayerPointsView = "0";
    }
}
