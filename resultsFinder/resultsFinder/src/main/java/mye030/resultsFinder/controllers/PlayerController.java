package mye030.resultsFinder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mye030.resultsFinder.datamodel.Player;
import mye030.resultsFinder.service.PlayerService;
import mye030.resultsFinder.service.YearlyMatchStatsService;

@Controller
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	YearlyMatchStatsService yearlyMatchStatsService;
	
	@RequestMapping("/player")
	public String showPlayerProfile(@RequestParam("scorer") String scorer, @RequestParam("isoCode") int iso_code, Model model) {
		Player player = playerService.findPlayerByName(scorer, iso_code);
		model.addAttribute("player", player);
		model.addAttribute("ratio", playerService.findGoalsMatchesRatioPerYear(player, yearlyMatchStatsService.findTotalMatchesInYearRange(player.getStarting_year(), player.getEnding_year(), player.getISO_Code())));
		model.addAttribute("years", playerService.findYearsPlaying(player));
		return "playerProfile";
	}
}
