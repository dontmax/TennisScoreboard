package org.tennisscoreboard.service;

import org.tennisscoreboard.models.Match;
import org.tennisscoreboard.models.MatchDto;
import org.tennisscoreboard.repository.HibernateMatchRepository;

import java.util.ArrayList;
import java.util.List;

public class FinishedMatchesPersistenceService {

    HibernateMatchRepository matchRepository;
    private static final int TABLE_SIZE=5;

    public FinishedMatchesPersistenceService(HibernateMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void save(Match match){
        matchRepository.save(match);
    }

    public List<MatchDto> getMatchesByPlayerName(String playerName, int pageNumber){
        List<Match> matches = matchRepository.getMatchesByPlayerName(playerName, pageNumber, TABLE_SIZE);
        return map(matches);
    }

    public List<MatchDto> getMatches(int pageNumber){
        List<Match> matches = matchRepository.getMatches(pageNumber, TABLE_SIZE);
        return map(matches);
    }

    public List<MatchDto> map(List<Match> matches){
        List<MatchDto> matchDtos = new ArrayList<>();
        for (Match match : matches){
            matchDtos.add(new MatchDto(
                    match.getId(),
                    match.getFirstPlayer().getName(),
                    match.getSecondPlayer().getName(),
                    match.getWinner().getName()
            ));
        }
        return matchDtos;
    }
}
