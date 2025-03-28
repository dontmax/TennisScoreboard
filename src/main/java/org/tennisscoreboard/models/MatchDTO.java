package org.tennisscoreboard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {

    private int id;
    private String firstPlayerName;
    private String secondPlayerName;
    private String winnerName;

}
