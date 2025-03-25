package org.tennisscoreboard.service;

import org.tennisscoreboard.models.Match;
import org.tennisscoreboard.models.MatchApiDto;
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

    public List<MatchApiDto> getMatchesByPlayerName(String playerName, int pageNumber){
        List<Match> matches = matchRepository.getMatchesByPlayerName(playerName, pageNumber, TABLE_SIZE);
        return map(matches);
    }

    public List<MatchApiDto> getMatches(int pageNumber){
        List<Match> matches = matchRepository.getMatches(pageNumber, TABLE_SIZE);
        return map(matches);
    }

    public List<MatchApiDto> map(List<Match> matches){
        List<MatchApiDto> matchApiDtos = new ArrayList<MatchApiDto>();
        for (Match match : matches){
            matchApiDtos.add(new MatchApiDto(
                    match.getId(),
                    match.getFirstPlayer().getName(),
                    match.getSecondPlayer().getName(),
                    match.getWinner().getName()
            ));
        }
        return matchApiDtos;
    }
}
