package org.tennisscoreboard.service;

import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.utils.Pagination;

public class PaginationService {

    private final HibernateMatchRepository matchRepository;
    private Pagination pagination;

    public PaginationService(HibernateMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    private long getTotalMatchCount() {
        return matchRepository.getTotalMatchCount();
    }

    private long getTotalMatchCount(String playerName) {
        return matchRepository.getTotalMatchCountByPlayerName(playerName);
    }

    private void setPagination(int pageNumber, long matchCount) {
        pagination = new Pagination(pageNumber, matchCount);
    }

    public Pagination getPagination(int pageNumber, String playerName) {
        long totalMatchCount;
        if (playerName == null || playerName.isBlank()) {
            totalMatchCount = getTotalMatchCount();
        } else {
            totalMatchCount = getTotalMatchCount(playerName);
        }
        setPagination(pageNumber, totalMatchCount);
        return pagination;
    }

}
