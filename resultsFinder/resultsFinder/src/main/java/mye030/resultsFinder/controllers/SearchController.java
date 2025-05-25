package mye030.resultsFinder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mye030.resultsFinder.mappers.CountryMapper;
import mye030.resultsFinder.mappers.PlayerMapper;
import mye030.resultsFinder.service.CountryService;
import mye030.resultsFinder.service.GlobalStatsService;
import mye030.resultsFinder.service.PlayerService;
import mye030.resultsFinder.service.YearlyMatchStatsService;

@Controller
public class SearchController {
	
	@Autowired 
	private CountryService countryService;
	
	@Autowired 
	private PlayerService playerService;
	
	@Autowired 
	private YearlyMatchStatsService yearlyMatchStatsService;
	
	@Autowired
	private GlobalStatsService globalStatsService;
	
	@RequestMapping("")
	public String showSearchPage(){
		return "homepage";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam String query, @RequestParam String type, Model model) {
		
		if("country".equals(type)) {
			model.addAttribute("results", countryService.findCountriesByName(query));
			model.addAttribute("type", "country");
			
			return "resultsPage";
		}else if("year".equals(type)) {
			int year;
			try {
				year = Integer.parseInt(query);
			} catch (NumberFormatException e) {
				return "wrongInput";
			}
			model.addAttribute("matches", yearlyMatchStatsService.findYearStats(year));
			model.addAttribute("query", query);
			model.addAttribute("type", "year");
			
			return "yearProfile";
		}else if("player".equals(type)) {
			model.addAttribute("results", playerService.findPlayersByName(query));
			model.addAttribute("type", "player");
			
			return "resultsPage";
		}
		return "homepage";
	}
	
	@RequestMapping("/show_leaderboard")
	public String showLeaderboard(Model model) {
		model.addAttribute("defaultLeaderboard", globalStatsService.findTopWins());
		
		return "leaderboard";
	}
	
}
