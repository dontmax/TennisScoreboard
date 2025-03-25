package org.tennisscoreboard.service;

import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.utils.Pagination;

public class PaginationService {

    HibernateMatchRepository matchRepository;
    private Pagination pagination;
    long totalMatchCount;

    public PaginationService(HibernateMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    private long getTotalMatchCount() {
        return matchRepository.getMatchCount();
    }

    private long getTotalMatchCount(String playerName) {
        return matchRepository.getMatchCountByPlayerName(playerName);
    }

    private void setPagination(int pageNumber, long matchCount) {
        pagination = new Pagination(pageNumber, matchCount);
    }

    public Pagination getPagination(int pageNumber) {
        totalMatchCount = getTotalMatchCount();
        setPagination(pageNumber, totalMatchCount);
        return pagination;
    }

    public Pagination getPagination(int pageNumber, String playerName) {
        totalMatchCount = getTotalMatchCount(playerName);
        setPagination(pageNumber, totalMatchCount);
        return pagination;
    }

}
