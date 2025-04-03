package org.tennisscoreboard.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScore {

    private boolean isTiebreak;
    private int firstPlayerPoints;
    private int secondPlayerPoints;
    private int firstPlayerGames;
    private int secondPlayerGames;
    private int firstPlayerSets;
    private int secondPlayerSets;

    public MatchScore() {
        resetAll();
    }

    private void resetAll(){
        this.isTiebreak = false;
        this.firstPlayerPoints = 0;
        this.secondPlayerPoints = 0;
        this.firstPlayerGames = 0;
        this.secondPlayerGames = 0;
        this.firstPlayerSets = 0;
        this.secondPlayerSets = 0;
    }

    public String getPointsView(int playerId) {
        final int PLAYER_ONE=1;
        boolean isPlayer1 = playerId == PLAYER_ONE;
        int points = isPlayer1 ? firstPlayerPoints : secondPlayerPoints;
        if(isTiebreak){
            return Integer.toString(points);
        } else {
            if(0<=points&&points<=4) {
                return PointsView.values()[points].getValue();
            } else {
                throw new RuntimeException("PointsView render error");
            }
        }
    }

    @Getter
    enum PointsView {
        LOVE("0"),
        FIFTEEN("15"),
        THIRTY("30"),
        FORTY("40"),
        ADVANTAGE("AD");

        private final String value;
        PointsView(String value) {
            this.value = value;
        }

    }
}
