package org.tennisscoreboard.repository;

import org.tennisscoreboard.models.Player;

public interface PlayerRepository {
    public boolean isPlayer(String name);
    public void createPlayer(Player player);
}
