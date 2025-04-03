package org.tennisscoreboard.models;

import lombok.Getter;
import lombok.Setter;
import org.tennisscoreboard.dto.PlayerDTO;

@Getter
@Setter
public class CurrentMatch {

    private final PlayerDTO firstPlayer;
    private final PlayerDTO secondPlayer;
    private PlayerDTO winner;
    MatchScore score;

    public CurrentMatch(PlayerDTO firstPlayer, PlayerDTO secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        score = new MatchScore();
    }




}
