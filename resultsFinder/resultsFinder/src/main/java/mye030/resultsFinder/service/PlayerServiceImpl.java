package mye030.resultsFinder.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mye030.resultsFinder.datamodel.Player;
import mye030.resultsFinder.mappers.PlayerMapper;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	private PlayerMapper playerMapper;
	
	@Override
	public List<Player> findPlayersByName(String query){
		return playerMapper.findPlayersByNameContaining(query);
	}

	@Override
	public Player findPlayerByName(String name, int iso_code) {
		return playerMapper.findPlayerByName(name, iso_code);
	}

	@Override
	public List<Float> findGoalsMatchesRatioPerYear(Player player, List<Integer> matches) {
        List<Float> ratio = new ArrayList<>();
        int counter = 0;
        for (Long i = player.getStarting_year(); i <= player.getEnding_year(); i ++) {
            if (matches.get(counter) == null){
                ratio.add(null);
                counter ++;
                continue;
            }
            ratio.add(((float)playerMapper.findPlayerGoalsPerYear(player.getScorer(), player.getISO_Code(), i)) / (float)matches.get(counter));
            counter ++;
        }
        return ratio;
    }

	@Override
	public List<Long> findYearsPlaying(Player player) {
		List<Long> years = new ArrayList<>();
		for (long i = player.getStarting_year(); i <= player.getEnding_year(); i ++) {
			years.add(i);
		}
		return years;
	}
}
