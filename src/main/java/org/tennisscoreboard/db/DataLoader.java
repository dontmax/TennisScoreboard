package org.tennisscoreboard.db;

import org.tennisscoreboard.models.Match;
import org.tennisscoreboard.models.Player;
import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.repository.HibernatePlayerRepository;

public class DataLoader {

    HibernatePlayerRepository playerRepository;
    HibernateMatchRepository matchRepository;

    public DataLoader(HibernatePlayerRepository playerRepository, HibernateMatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    public void loadData() {

        for (int i = 1; i < 10; i++) {
            playerRepository.save(new Player("Player" + i));
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 9; i++) {
                Player player1 = new Player(i,"Player" + i);
                Player player2 = new Player(i+1, "Player" + i + 1);
                matchRepository.save(new Match(player1, player2, player1));
            }
        }
    }
}
