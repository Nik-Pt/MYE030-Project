package mye030.resultsFinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mye030.resultsFinder.datamodel.GlobalStats;
import mye030.resultsFinder.dto.ScoreYearsRatioDTO;
import mye030.resultsFinder.dto.WinYearRatioStatsDTO;
import mye030.resultsFinder.mappers.GlobalStatsMapper;

@Service
public class GlobalStatsServiceImpl implements GlobalStatsService {

	@Autowired
	private GlobalStatsMapper globalStatsMapper;
	
	@Override
	public GlobalStats findStats() {
		GlobalStats stats = globalStatsMapper.findById(0);
		return stats;
	}
	
	@Override
	public List<GlobalStats> findTopWins() {
		List<GlobalStats> stats = globalStatsMapper.getTopWins(); 
		return stats;
	}

	@Override
	public List<GlobalStats> findTopScores() {
		List<GlobalStats> stats = globalStatsMapper.getTopScores(); 
		return stats;
	}

	@Override
	public List<WinYearRatioStatsDTO> findTopWinsYearRatio() {
		List<WinYearRatioStatsDTO> stats = globalStatsMapper.getTopWinsYearRatio(); 
		return stats;
	}

	@Override
	public List<ScoreYearsRatioDTO> findTopScoresYearRatio() {
		List<ScoreYearsRatioDTO> stats = globalStatsMapper.getTopScoresYearRatio(); 
		return stats;
	}
	
	@Override
	public List<GlobalStats> findTopTenByWins() {
		List<GlobalStats> stats = globalStatsMapper.getTopWinsLimited(); 
		return stats;
	}

	@Override
	public List<GlobalStats> findTopTenByScores() {
		List<GlobalStats> stats = globalStatsMapper.getTopScoresLimited(); 
		return stats;
	}

	@Override
	public List<WinYearRatioStatsDTO> findTopTenByWinsYearRatio() {
		List<WinYearRatioStatsDTO> stats = globalStatsMapper.getTopWinsYearRatioLimited(); 
		return stats;
	}

	@Override
	public List<ScoreYearsRatioDTO> findTopTenByScoresYearRatio() {
		List<ScoreYearsRatioDTO> stats = globalStatsMapper.getTopScoresYearRatioLimited(); 
		return stats;
	}
}
