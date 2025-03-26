package org.tennisscoreboard.service;

import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.models.Match;
import org.tennisscoreboard.models.MatchDTO;
import org.tennisscoreboard.repository.HibernateMatchRepository;

import java.util.ArrayList;
import java.util.List;

public class FinishedMatchesPersistenceService {

    HibernateMatchRepository matchRepository;
    private static final int TABLE_SIZE=5;

    public FinishedMatchesPersistenceService(HibernateMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void save(CurrentMatch currentMatch){
        matchRepository.save(mapToMatch(currentMatch));
    }

    public List<MatchDTO> getMatchesByPlayerName(String playerName, int pageNumber){
        List<Match> matches = matchRepository.getMatchesByPlayerName(playerName, pageNumber, TABLE_SIZE);
        return mapToMatchDTO(matches);
    }

    public List<MatchDTO> getMatches(int pageNumber){
        List<Match> matches = matchRepository.getMatches(pageNumber, TABLE_SIZE);
        return mapToMatchDTO(matches);
    }

    private List<MatchDTO> mapToMatchDTO(List<Match> matches){
        List<MatchDTO> matchDTOs = new ArrayList<>();
        for (Match match : matches){
            matchDTOs.add(new MatchDTO(
                    match.getId(),
                    match.getFirstPlayer().getName(),
                    match.getSecondPlayer().getName(),
                    match.getWinner().getName()
            ));
        }
        return matchDTOs;
    }

    private Match mapToMatch(CurrentMatch currentMatch){
        return new Match(
                currentMatch.getFirstPlayer(),
                currentMatch.getSecondPlayer(),
                currentMatch.getWinnerPlayer());
    }
}
