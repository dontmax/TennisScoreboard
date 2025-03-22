package org.tennisscoreboard.repository;

import org.tennisscoreboard.models.Match;
import java.util.List;

public interface MatchRepository {
    public void createMatch(Match match);
    public List<Match> getAll();
}
