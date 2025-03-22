package org.tennisscoreboard.utils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.models.Player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class SetTest {

    Set set;

    SetTest (TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }

    @BeforeEach
    void  setup(){
        set = new Set(new CurrentMatch(new Player(1,"firstPlayer"),new Player(2,"secondPlayer")));
        set.setFirstPlayerGames(6);
        set.setSecondPlayerGames(6);
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
    void isTiebreakStartsAndNotEnds(int firstPlayerPoints, int secondPlayerPoints, int pointsTo, int expectedFirstPlayerPoints, int expectedSecondPlayerPoints) {
        set.setFirstPlayerPoints(firstPlayerPoints);
        set.setSecondPlayerPoints(secondPlayerPoints);
        set.addPoints(pointsTo);
        assertAll("In these cases tiebreak is keeping go",
                ()->assertEquals(0,set.getFirstPlayerSets(),"first player sets should be 0"),
                ()->assertEquals(0,set.getSecondPlayerSets(),"second player sets should be 0"),
                ()->assertEquals(6,set.getFirstPlayerGames(),"first player games should be 6"),
                ()->assertEquals(6,set.getSecondPlayerGames(), "second player games should be 6"),
                ()->assertEquals(""+expectedFirstPlayerPoints, set.getFirstPlayerPointsView(), "first player points view should be 0"+expectedFirstPlayerPoints),
                ()->assertEquals(""+expectedSecondPlayerPoints, set.getSecondPlayerPointsView(), "second player points view should be "+expectedSecondPlayerPoints)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "6,0,1,1,0",
            "0,6,2,0,1",
            "6,1,1,1,0",
            "1,6,2,0,1",
            "6,2,1,1,0",
            "2,6,2,0,1",
            "6,3,1,1,0",
            "3,6,2,0,1",
            "6,4,1,1,0",
            "4,6,2,0,1",
            "6,5,1,1,0",
            "5,6,2,0,1",
            "7,6,1,1,0",
            "6,7,2,0,1"

    })
    void isTiebreakEnds(int firstPlayerPoints, int secondPlayerPoints, int pointsTo, int expectedFirstPlayerSets, int expectedSecondPlayerSets) {
        set.setFirstPlayerPoints(firstPlayerPoints);
        set.setSecondPlayerPoints(secondPlayerPoints);
        set.addPoints(pointsTo);
        assertAll("tiebreak should be finished and Games are reset",
                ()->assertEquals(expectedFirstPlayerSets,set.getFirstPlayerSets(),"first player sets should be 0"),
                ()->assertEquals(expectedSecondPlayerSets,set.getSecondPlayerSets(),"second player sets should be 0"),
                ()->assertEquals(0,set.getFirstPlayerGames(),"first player games should be 6"),
                ()->assertEquals(0,set.getSecondPlayerGames(), "second player games should be 6")
        );
    }
}
