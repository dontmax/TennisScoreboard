package org.tennisscoreboard.utils;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.models.CurrentMatch;

@Getter
@Setter
public class Set extends Game{
    private int firstPlayerSets=0;
    private int secondPlayerSets=0;

    public Set(CurrentMatch currentMatch){
        super(currentMatch);
        this.firstPlayerSets=currentMatch.getFirstPlayerSets();
        this.secondPlayerSets=currentMatch.getSecondPlayerSets();
    }

    public void addPoints(int points){
        super.addPoints(points);
        if(isSetOver()){
            if(getFirstPlayerGames()>getSecondPlayerGames()){
                firstPlayerSets++;
            } else {
                secondPlayerSets++;
            }
            resetGames();
        }
    }

    public void resetSets(){
        firstPlayerSets=0;
        secondPlayerSets=0;
    }

    public boolean isSetOver(){
        return (getFirstPlayerGames()>=6 && getFirstPlayerGames()-getSecondPlayerGames()>=2)||
                (getSecondPlayerGames()>=6 && getSecondPlayerGames()-getFirstPlayerGames()>=2)||
                (getFirstPlayerGames()==7&&getSecondPlayerGames()==6)||
                (getFirstPlayerGames()==6&&getSecondPlayerGames()==7);
    }
}
