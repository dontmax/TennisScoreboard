package org.tennisscoreboard.service;

import org.tennisscoreboard.models.Match;
import org.tennisscoreboard.models.MatchApiDto;
import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.repository.HibernatePlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class FinishedMatchesPersistenceService {

    HibernateMatchRepository matchRepository;

    public FinishedMatchesPersistenceService(HibernateMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void save(Match match){
        matchRepository.save(match);
    }

    public List<MatchApiDto> getMatchesByPlayerName(String playerName, int pageNumber, int pageSize){
        List<Match> matches = matchRepository.getByPlayerName(playerName, pageNumber, pageSize);
        return map(matches);
    }

    public List<MatchApiDto> getMatches(int pageNumber, int tableSize){
        List<Match> matches = matchRepository.getMatches(pageNumber, tableSize);
        return map(matches);
    }

    public long getMatchCount(){
        return matchRepository.getMatchCount();
    }

    public long getMatchCountByPlayerName(String playerName){
        return matchRepository.getMatchCountByPlayerName(playerName);
    }

    public List<MatchApiDto> map(List<Match> matches){
        List<MatchApiDto> matchApiDtos = new ArrayList<MatchApiDto>();
        for (Match match : matches){
            MatchApiDto matchApiDto = new MatchApiDto();
            matchApiDto.setId(match.getId());
            String firstPlayerName=match.getFirstPlayer().getName();
            String secondPlayerName=match.getSecondPlayer().getName();
            matchApiDto.setFirstPlayerName(firstPlayerName);
            matchApiDto.setSecondPlayerName(secondPlayerName);
            matchApiDto.setWinnerName((match.getWinner().equals(match.getFirstPlayer()))?firstPlayerName:secondPlayerName);
            matchApiDtos.add(matchApiDto);
        }
        return matchApiDtos;
    }

    public void drop(){
        matchRepository.drop();
    }
}
