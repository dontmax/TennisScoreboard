package org.tennisscoreboard.service;

import org.tennisscoreboard.repository.MatchRepository;
import org.tennisscoreboard.utils.Pagination;

public class PaginationService {

    private final MatchRepository matchRepository;

    public PaginationService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    private long getTotalMatchCount() {
        return matchRepository.getTotalMatchCount();
    }

    private long getTotalMatchCount(String playerName) {
        return matchRepository.getTotalMatchCountByPlayerName(playerName);
    }

    public Pagination getPagination(int pageNumber, String playerName) {
        long totalMatchCount = (playerName == null || playerName.isBlank())
                ?getTotalMatchCount()
                :getTotalMatchCount(playerName);
        return new Pagination(pageNumber, totalMatchCount);
    }

}
