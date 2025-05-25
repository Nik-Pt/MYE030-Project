package mye030.resultsFinder.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mye030.resultsFinder.datamodel.Xores;
import mye030.resultsFinder.service.GlobalStatsService;
import mye030.resultsFinder.service.ResultsService;
import mye030.resultsFinder.service.XoraService;
import mye030.resultsFinder.service.YearlyMatchStatsService;

@Controller
public class CountryController {
	
	@Autowired
	XoraService xoresService;
	
	@Autowired
	YearlyMatchStatsService yearlyMatchStatsService;
	
	@Autowired
	GlobalStatsService globalStatsService;
	
	@Autowired
	ResultsService resultsService;
	
	@RequestMapping("/country")
	public String showCountryProfile(@RequestParam("isoCode") int ISO_Code, Model model) {
		Xores country = xoresService.findXoresByIsoCode(ISO_Code);
		if (country == null) {
			return "noCountryStatsFound";
		}
		model.addAttribute("country", country);
	
		List<List<Integer>> total_stats = yearlyMatchStatsService.findCountryPerYearStats(ISO_Code);
	
		model.addAttribute("wins", total_stats.get(0));
		model.addAttribute("losses", total_stats.get(1));
		model.addAttribute("draws", total_stats.get(2));
		model.addAttribute("matches", total_stats.get(3));
		model.addAttribute("years", total_stats.get(4));
	
		return "countryProfile";
	}
	
	@RequestMapping("/resultsFilter")
	public String showFilteredResults(@RequestParam("ISO_Code") int ISO_Code, @RequestParam("min") int min, @RequestParam("max") int max, Model model) {
		model.addAttribute("countryName", xoresService.findXoresByIsoCode(ISO_Code).getDisplay_Name());
		model.addAttribute("results", resultsService.filterResults(ISO_Code, min, max));
		return "countryMatches";
	}
}
