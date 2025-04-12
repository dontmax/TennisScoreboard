package org.tennisscoreboard.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.entity.Match;
import org.tennisscoreboard.dto.FinishedMatchDTO;
import org.tennisscoreboard.entity.Player;
import org.tennisscoreboard.repository.MatchRepository;
import org.tennisscoreboard.repository.PlayerRepository;
import org.tennisscoreboard.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class FinishedMatchesPersistenceService {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private static final int TABLE_SIZE = 5;

    public FinishedMatchesPersistenceService(MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    public void save(CurrentMatch currentMatch) {
        Player firstPlayer;
        Player secondPlayer;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            firstPlayer = playerRepository.getByName(session, currentMatch.getFirstPlayer().getName())
                    .orElseGet(() -> new Player(currentMatch.getFirstPlayer().getName()));
            secondPlayer = playerRepository.getByName(session, currentMatch.getSecondPlayer().getName())
                    .orElseGet(() -> new Player(currentMatch.getSecondPlayer().getName()));
            if (firstPlayer.getId() == null) {
                playerRepository.save(session, firstPlayer);
            }
            if (secondPlayer.getId() == null) {
                playerRepository.save(session, secondPlayer);
            }
            matchRepository.save(
                    session,
                    new Match(
                            firstPlayer,
                            secondPlayer,
                            (firstPlayer.getName().equals(currentMatch.getWinner().getName())) ? firstPlayer : secondPlayer
                    ));
            transaction.commit();
        }
    }

    public List<FinishedMatchDTO> getMatches(int pageNumber, String playerName) {
        List<Match> matches;
        if (playerName == null || playerName.isBlank()) {
            matches = matchRepository.getMatches(pageNumber, TABLE_SIZE);
        } else {
            matches = matchRepository.getMatchesByPlayerName(playerName, pageNumber, TABLE_SIZE);
        }
        return mapToMatchDTO(matches);
    }

    private List<FinishedMatchDTO> mapToMatchDTO(List<Match> matches) {
        List<FinishedMatchDTO> finishedMatchDTOs = new ArrayList<>();
        for (Match match : matches) {
            finishedMatchDTOs.add(new FinishedMatchDTO(
                    match.getId(),
                    match.getFirstPlayer().getName(),
                    match.getSecondPlayer().getName(),
                    match.getWinner().getName()
            ));
        }
        return finishedMatchDTOs;
    }

}
