package org.tennisscoreboard.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.tennisscoreboard.models.GameScore;
import org.tennisscoreboard.models.PointScore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({
            "3,3,1",
            "3,3,2",
            "3,4,1",
            "4,3,2"
    })
    void gameIsNotFinished(int player1Points, int player2Points, int scoreWinnerId) {
        boolean isTiebreak = false;
        int p1Games = 0;
        int p2Games = 0;
        GameScore gameScore = new GameScore(new PointScore(player1Points, player2Points), isTiebreak, p1Games, p2Games);
        GameScore result = GameMath.addPoints(gameScore, scoreWinnerId);
        assertAll(
                ()->assertThat(result.player1Games()).isEqualTo(0),
                ()->assertThat(result.player2Games()).isEqualTo(0)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "3,0,1,4,0",
            "4,0,1,5,0",
            "5,0,1,6,0",
            "0,3,2,0,4",
            "0,4,2,0,5",
            "0,5,2,0,6",
            "3,1,1,4,1",
            "4,1,1,5,1",
            "5,1,1,6,1",
            "1,3,2,1,4",
            "1,4,2,1,5",
            "1,5,2,1,6",
            "3,2,1,4,2",
            "4,2,1,5,2",
            "5,2,1,6,2",
            "2,3,2,2,4",
            "2,4,2,2,5",
            "2,5,2,2,6",
            "6,6,1,7,6",
            "6,6,2,6,7"
    })
    void TiebreakIsGoingIsAndNotFinished(int player1Points, int player2Points, int scoreWinnerId, int expectedPlayer1Points, int expectedPlayer2Points) {
        boolean isTiebreak = true;
        int p1Games = 6;
        int p2Games = 6;
        GameScore gameScore = new GameScore(new PointScore(player1Points, player2Points), isTiebreak, p1Games, p2Games);
        GameScore result = GameMath.addPoints(gameScore, scoreWinnerId);
        assertAll(
                ()->assertThat(result.player1Games()).isEqualTo(6),
                ()->assertThat(result.player2Games()).isEqualTo(6),
                ()->assertThat(result.pointScore().player1Points()).isEqualTo(expectedPlayer1Points),
                ()->assertThat(result.pointScore().player2Points()).isEqualTo(expectedPlayer2Points)
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
    void gameIsFinishedDutToScoreDifference(int player1Points, int player2Points, int scoreWinnerId, int expectedPlayer1Games, int expectedPlayer2Games){
        boolean isTiebreak = false;
        int p1Games = 0;
        int p2Games = 0;
        GameScore gameScore = new GameScore(new PointScore(player1Points, player2Points), isTiebreak, p1Games,p2Games);
        GameScore result = GameMath.addPoints(gameScore, scoreWinnerId);
            assertAll(
                    ()->assertThat(result.player1Games()).isEqualTo(expectedPlayer1Games),
                    ()->assertThat(result.player2Games()).isEqualTo(expectedPlayer2Games)
                    );
    }

    @ParameterizedTest@CsvSource({
            "6,0,1,7,6",
            "0,6,2,6,7",
            "6,1,1,7,6",
            "1,6,2,6,7",
            "6,2,1,7,6",
            "2,6,2,6,7",
            "6,3,1,7,6",
            "3,6,2,6,7",
            "6,4,1,7,6",
            "4,6,2,6,7",
            "6,5,1,7,6",
            "5,6,2,6,7",
            "7,6,1,7,6",
            "6,7,2,6,7"
    })
    void TiebreakIsEnds(int player1Points, int player2Points, int scoreWinnerId, int expectedPlayer1Games, int expectedSecondPlayerGames) {
        boolean isTiebreak = true;
        int p1Games = 6;
        int p2Games = 6;
        GameScore gameScore = new GameScore(new PointScore(player1Points, player2Points), isTiebreak, p1Games,p2Games);
        GameScore result = GameMath.addPoints(gameScore, scoreWinnerId);
        assertAll(
                ()->assertThat(result.player1Games()).isEqualTo(expectedPlayer1Games),
                ()->assertThat(result.player2Games()).isEqualTo(expectedSecondPlayerGames)
        );
    }
}
