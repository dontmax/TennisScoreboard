package org.tennisscoreboard.utils;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.models.CurrentMatch;

@Getter
@Setter
public class Game extends Point {
    private int firstPlayerGames=0;
    private int secondPlayerGames =0;

    protected Game(CurrentMatch currentMatch){
        super(currentMatch.getFirstPlayerPoints(), currentMatch.getSecondPlayerPoints());
        this.firstPlayerGames=currentMatch.getFirstPlayerGames();
        this.secondPlayerGames=currentMatch.getSecondPlayerGames();

    }

    protected void addPoints(int points){
        if(secondPlayerGames==firstPlayerGames&&firstPlayerGames==6){
            super.addTiebreakPoints(points);
            if(isTiebreakGameOver()) {
                if (getFirstPlayerPoints() > getSecondPlayerPoints()) {
                    firstPlayerGames++;
                } else {
                    secondPlayerGames++;
                }
                resetPoints();
            }
        } else {
            super.addPoints(points);
            if(isGameOver()){
                if(getFirstPlayerPoints()>getSecondPlayerPoints()){
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

    private boolean isGameOver(){
        return (getFirstPlayerPoints()>=4 && (getFirstPlayerPoints()-getSecondPlayerPoints())>=2)||
                (getSecondPlayerPoints()>=4 && (getSecondPlayerPoints()-getFirstPlayerPoints())>=2);
    }

    private boolean isTiebreakGameOver() {
        return (getFirstPlayerPoints()>=7 && (getFirstPlayerPoints()-getSecondPlayerPoints())>=2)||
                (getSecondPlayerPoints()>=7 && (getSecondPlayerPoints()-getFirstPlayerPoints())>=2);
    }
}
