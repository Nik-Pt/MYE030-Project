package mye030.resultsFinder.service;

import java.util.List;

import mye030.resultsFinder.datamodel.GlobalStats;
import mye030.resultsFinder.dto.ScoreYearsRatioDTO;
import mye030.resultsFinder.dto.WinYearRatioStatsDTO;

public interface GlobalStatsService {
	public GlobalStats findStats();
	
	public List<GlobalStats> findTopWins();
	public List<GlobalStats> findTopScores();
	public List<WinYearRatioStatsDTO> findTopWinsYearRatio();
	public List<ScoreYearsRatioDTO> findTopScoresYearRatio();
	
	public List<ScoreYearsRatioDTO> findTopTenByScoresYearRatio();
	public List<WinYearRatioStatsDTO> findTopTenByWinsYearRatio();
	public List<GlobalStats> findTopTenByScores();
	public List<GlobalStats> findTopTenByWins();
}
