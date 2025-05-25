package mye030.resultsFinder.service;

import java.util.List;

import mye030.resultsFinder.datamodel.Player;

public interface PlayerService {
	public List<Player> findPlayersByName(String query);
	public Player findPlayerByName(String name, int iso_code);
	public List<Float> findGoalsMatchesRatioPerYear(Player player, List<Integer> matches);
	public List<Long> findYearsPlaying(Player player);
}
