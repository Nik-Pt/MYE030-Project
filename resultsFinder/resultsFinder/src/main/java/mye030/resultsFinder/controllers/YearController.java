package mye030.resultsFinder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.service.CountryService;
import mye030.resultsFinder.service.ResultsService;
import mye030.resultsFinder.service.YearlyMatchStatsService;

@Controller
public class YearController {

	@Autowired
	private YearlyMatchStatsService yearlyMatchStatsService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ResultsService resultsService;
	
	@RequestMapping("/displayCountriesStats")
	public String showCountryStats(@RequestParam("query") int year, Model model) {	
		List<Integer> countries = yearlyMatchStatsService.findCountriesInYear(year);
		
		model.addAttribute("stats", yearlyMatchStatsService.findCountryStatsForYear(countries, year));
		
		model.addAttribute("matches",  yearlyMatchStatsService.findYearStats(year));
		model.addAttribute("countries", countryService.findCountriesByIso(countries));
		model.addAttribute("query", year);
		
		return "yearProfile";
	}
	
	@RequestMapping("/displayYearMatches")
	public String showYearMatches(@RequestParam("year") Integer year, @RequestParam("homeRegion") String homeRegion, @RequestParam("awayRegion") String awayRegion, @RequestParam("homeStatus") String homeStatus, @RequestParam("awayStatus") String awayStatus, @RequestParam("homeDevelopingOrDeveloped") String homeDevelopingOrDeveloped, @RequestParam("awayDevelopingOrDeveloped") String awayDevelopingOrDeveloped, Model model) {
		model.addAttribute("results", resultsService.filterResultsByCountries(year, homeRegion, awayRegion, homeStatus, awayStatus, homeDevelopingOrDeveloped, awayDevelopingOrDeveloped));
		model.addAttribute("names");
		return "resultsYearProfile";
	}
	
}
