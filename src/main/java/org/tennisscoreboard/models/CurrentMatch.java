package org.tennisscoreboard.models;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.dto.PlayerDTO;

@Getter
@Setter
public class CurrentMatch {

    private PlayerDTO firstPlayer;
    private PlayerDTO secondPlayer;
    private PlayerDTO winner;
    private int firstPlayerPoints;
    private int secondPlayerPoints;
    private String firstPlayerPointsView;
    private String secondPlayerPointsView;
    private int firstPlayerGames;
    private int secondPlayerGames;
    private int firstPlayerSets;
    private int secondPlayerSets;

    public CurrentMatch(PlayerDTO firstPlayer, PlayerDTO secondPlayer) {
        this();
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

    }

    public CurrentMatch() {
        this.firstPlayerPoints = 0;
        this.secondPlayerPoints = 0;
        this.firstPlayerPointsView = "0";
        this.secondPlayerPointsView = "0";
        this.firstPlayerGames = 0;
        this.secondPlayerGames = 0;
        this.firstPlayerSets = 0;
        this.secondPlayerSets = 0;
    }
}
