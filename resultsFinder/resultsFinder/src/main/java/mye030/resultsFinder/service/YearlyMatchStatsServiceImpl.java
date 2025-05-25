package mye030.resultsFinder.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mye030.resultsFinder.datamodel.YearlyMatchStats;
import mye030.resultsFinder.dto.YearlyMatchStatsDTO;
import mye030.resultsFinder.mappers.YearlyMatchStatsMapper;

@Service
public class YearlyMatchStatsServiceImpl implements YearlyMatchStatsService {
	
	@Autowired
	YearlyMatchStatsMapper yearlyMatchStatsMapper;

	@Override
	public List<YearlyMatchStats> findCountryPerYear(int ISO_Code) {
		return yearlyMatchStatsMapper.findCountryPerYear(ISO_Code);
	}
	
	@Override
	public List<List<Integer>> findCountryPerYearStats(int ISO_Code) {
		
		List<YearlyMatchStats> statList = findCountryPerYear(ISO_Code);
		
		List<Integer> wins = new ArrayList<>();
		List<Integer> losses = new ArrayList<>();
		List<Integer> draws = new ArrayList<>();
		List<Integer> matches = new ArrayList<>();
		List<Integer> years = new ArrayList<>();
	
		for (int i = 0; i < statList.size(); i ++) {
			wins.add(statList.get(i).getTotal_year_wins());
			losses.add(statList.get(i).getTotal_year_losses());
			draws.add(statList.get(i).getTotal_year_draws());
			matches.add(statList.get(i).getTotal_year_matches());
			years.add(statList.get(i).getYear());
		}
		
		List<List<Integer>> stats = new ArrayList<>();
		stats.add(wins);
		stats.add(losses);
		stats.add(draws);
		stats.add(matches);
		stats.add(years);
		
		return stats;
	}

	@Override
	public List<YearlyMatchStatsDTO> findYearStats(int year) {
		return yearlyMatchStatsMapper.findYearStats(year);
	}

	@Override
	public List<Integer> findTotalMatchesInYearRange(long starting, long ending, int iso_code) {
		List<Integer> results = new ArrayList<>();
		for (long i = starting; i <= ending; i ++) {
			results.add(yearlyMatchStatsMapper.findTotalYearMatch(i, iso_code));
		}
		return results;
	}

	@Override
	public List<Integer> findCountriesInYear(int year) {
		return yearlyMatchStatsMapper.findCountriesInYear(year);
	}

	@Override
	public List<YearlyMatchStatsDTO> findCountryStatsForYear(List<Integer> ISO_Codes, int year) {
		List<YearlyMatchStatsDTO> countriesStats = new ArrayList<>();
		for (int i = 0; i < ISO_Codes.size(); i++) {
			countriesStats.add(yearlyMatchStatsMapper.findCountryYearStats(year,ISO_Codes.get(i)));
		}
		return countriesStats;
	}

}
