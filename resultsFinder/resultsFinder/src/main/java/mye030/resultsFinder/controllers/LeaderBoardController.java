package mye030.resultsFinder.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mye030.resultsFinder.datamodel.GlobalStats;
import mye030.resultsFinder.service.CountryService;
import mye030.resultsFinder.service.GlobalStatsService;

@Controller
public class LeaderBoardController {
	
	@Autowired 
	private GlobalStatsService globalStatsService;
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping("/sortLeaderboard")
	public String sortLeaderboard(@RequestParam(value = "sort", required = false, defaultValue = "") String sort, @RequestParam(value = "showChart", required = false) boolean showChart, @RequestParam(value = "area_pop", required = false, defaultValue = "0") String area_pop, Model model) {
		
		List<String> top10Names = new ArrayList<>();
		
		switch (sort) {
			case "wins" -> {
				List<GlobalStats> wins = globalStatsService.findTopWins();
				
				model.addAttribute("defaultLeaderboard", wins);
	            model.addAttribute("sortType", "wins");
	            
	            List<Integer> xValues = wins.stream().map(GlobalStats::getTotal_wins).toList();
	            
	            model.addAttribute("scatterXValues", xValues);
	            
	            if (area_pop.equals("0")) {
	            	model.addAttribute("scatterYValues", countryService.findAreasByIsoCode(wins));
	            } else if (area_pop.equals("1")) {
	            	model.addAttribute("scatterYValues", countryService.findPopulationsByIsoCode(wins));
	            }
	            
	            List<Integer> top10Values;
				var top10 = globalStatsService.findTopTenByWins();
				
				top10Names = top10.stream().map(gs -> gs.getDisplay_Name()).toList();
				top10Values = top10.stream().map(gs -> gs.getTotal_wins()).toList();
				
				model.addAttribute("top10Values", top10Values);
			}
			
	        case "score" -> {
	        	List<GlobalStats> scores = globalStatsService.findTopScores();
	        	
	            model.addAttribute("defaultLeaderboard", scores);
	            model.addAttribute("sortType", "score");
	            
	            List<Integer> xValues = scores.stream().map(GlobalStats::getScore).toList();
	            
	            model.addAttribute("scatterXValues", xValues);
	            
	            if (area_pop.equals("0")) {
	            	model.addAttribute("scatterYValues", countryService.findAreasByIsoCode(scores));
	            } else if (area_pop.equals("1")) {
	            	model.addAttribute("scatterYValues", countryService.findPopulationsByIsoCode(scores));
	            }
	            
	            List<Integer> top10Values;
				var top10 = globalStatsService.findTopTenByScores();
				
				top10Names = top10.stream().map(gs -> gs.getDisplay_Name()).toList();
				top10Values = top10.stream().map(gs -> gs.getScore()).toList();
				
				model.addAttribute("top10Values", top10Values);
	        }
	        
	        case "winsYearsRatio" -> {
	            model.addAttribute("defaultLeaderboard", globalStatsService.findTopWinsYearRatio());
	            model.addAttribute("sortType", "winsYearsRatio");
	            
	            List<Double> top10Values;
				var top10 = globalStatsService.findTopTenByWinsYearRatio();
				
				top10Names = top10.stream().map(dto -> dto.getDisplay_Name()).toList();
				top10Values = top10.stream().map(dto -> dto.getWinsYearsRatio()).toList();
				
				model.addAttribute("top10Values", top10Values);

	        }
	        
	        case "scoreYearsRatio" -> {
	            model.addAttribute("defaultLeaderboard", globalStatsService.findTopScoresYearRatio());
	            model.addAttribute("sortType", "scoreYearsRatio");
	            
	            List<Double> top10Values;
				var top10 = globalStatsService.findTopTenByScoresYearRatio();
				
				top10Names = top10.stream().map(dto -> dto.getDisplay_Name()).toList();
				top10Values = top10.stream().map(dto -> dto.getScoreYearsRatio()).toList();
				
				model.addAttribute("top10Values", top10Values);

	        }
	        
	        default -> {
	        	List<GlobalStats> wins = globalStatsService.findTopWins();
				
				model.addAttribute("defaultLeaderboard", wins);
	            model.addAttribute("sortType", "wins");
	        }
		}
		
		model.addAttribute("top10Names", top10Names);
		model.addAttribute("showChart", showChart);
		model.addAttribute("area_pop", area_pop);
		
		return "leaderboard";
	}
}
