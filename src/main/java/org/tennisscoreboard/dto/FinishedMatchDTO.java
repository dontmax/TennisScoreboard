package org.tennisscoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinishedMatchDTO {

    private int id;
    private String firstPlayerName;
    private String secondPlayerName;
    private String winnerName;

}
