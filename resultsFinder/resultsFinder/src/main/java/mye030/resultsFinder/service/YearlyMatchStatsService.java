package mye030.resultsFinder.service;

import java.util.List;
import mye030.resultsFinder.datamodel.YearlyMatchStats;
import mye030.resultsFinder.dto.YearlyMatchStatsDTO;

public interface YearlyMatchStatsService {
	List<YearlyMatchStats> findCountryPerYear(int ISO_Code);
	List<List<Integer>> findCountryPerYearStats(int ISO_Code);
	List<YearlyMatchStatsDTO> findYearStats(int year);
	List<Integer> findTotalMatchesInYearRange(long starting, long ending, int iso_code);
	List<Integer> findCountriesInYear(int year);
	List<YearlyMatchStatsDTO> findCountryStatsForYear (List<Integer> ISO_Codes, int year);
}