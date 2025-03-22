package org.tennisscoreboard.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;
import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.models.Player;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new CurrentMatch(new Player(1,"firstPlayer"), new Player(2,"secondPlayer")));
    }

    @ParameterizedTest
    @CsvSource({
            "3,3,1",
            "3,3,2",
            "3,4,1",
            "4,3,2"
    })
    void gameIsNotFinished(int firstPlayerPoints, int secondPlayerPoints, int pointsTo){
        game.setFirstPlayerPoints(firstPlayerPoints);
        game.setSecondPlayerPoints(secondPlayerPoints);
        game.addPoints(pointsTo);
        assertAll(
                ()->assertThat(game.getFirstPlayerGames()).isEqualTo(0),
                ()->assertThat(game.getSecondPlayerGames()).isEqualTo(0)
        );
    }

    @ParameterizedTest
    @CsvSource ({
            "3,0,1,1,0",
            "0,3,2,0,1",
            "3,1,1,1,0",
            "3,2,1,1,0",
            "1,3,2,0,1",
            "2,3,2,0,1",
            "3,4,2,0,1",
            "4,3,1,1,0"
            })
    void gameIsFinishedDutToScoreDifference(int firstPlayerPoints, int secondPlayerPoints, int pointsTo, int expectedFirstPlayerGames, int expectedSecondPlayerGames){
        game.setFirstPlayerPoints(firstPlayerPoints);
        game.setSecondPlayerPoints(secondPlayerPoints);
        game.addPoints(pointsTo);
            assertAll(
                    ()->assertThat(game.getFirstPlayerGames()).isEqualTo(expectedFirstPlayerGames),
                    ()->assertThat(game.getSecondPlayerGames()).isEqualTo(expectedSecondPlayerGames)
                    );
    }
}
