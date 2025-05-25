package mye030.resultsFinder.mappers;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import mye030.resultsFinder.datamodel.GlobalStats;
import mye030.resultsFinder.dto.ScoreYearsRatioDTO;
import mye030.resultsFinder.dto.WinYearRatioStatsDTO;

public interface GlobalStatsMapper extends JpaRepository<GlobalStats, Integer> {
	GlobalStats findById(int Id);
	
	@Query("SELECT g FROM GlobalStats g ORDER BY total_wins DESC")
	List<GlobalStats>  getTopWins();
	
	@Query("SELECT g FROM GlobalStats g ORDER BY score DESC")
	List<GlobalStats>  getTopScores();
	
	@Query("SELECT new mye030.resultsFinder.dto.WinYearRatioStatsDTO(g.Display_Name, COALESCE(g.total_wins, 0), COALESCE(g.years_playing, 0), COALESCE(ROUND(CAST(g.total_wins AS DOUBLE) / CAST(g.years_playing AS DOUBLE), 4), 0)) AS WinsYearsRatio"
			+ " FROM GlobalStats g ORDER BY total_wins/years_playing DESC")
	List<WinYearRatioStatsDTO>  getTopWinsYearRatio();
	
	@Query("SELECT new mye030.resultsFinder.dto.ScoreYearsRatioDTO(g.Display_Name, COALESCE(g.score, 0), COALESCE(g.years_playing, 0), COALESCE(ROUND(CAST(g.score AS DOUBLE) / CAST(g.years_playing AS DOUBLE), 4), 0)) AS ScoreYearsRatio "
			+ "FROM GlobalStats g ORDER BY score/years_playing DESC")
	List<ScoreYearsRatioDTO>  getTopScoresYearRatio();
	
	@Query("SELECT g FROM GlobalStats g ORDER BY total_wins DESC LIMIT 10")
	List<GlobalStats>  getTopWinsLimited();
	
	@Query("SELECT g FROM GlobalStats g ORDER BY score DESC LIMIT 10")
	List<GlobalStats>  getTopScoresLimited();
	
	@Query("SELECT new mye030.resultsFinder.dto.WinYearRatioStatsDTO(g.Display_Name, COALESCE(g.total_wins, 0), COALESCE(g.years_playing, 0), COALESCE(ROUND(CAST(g.total_wins AS DOUBLE) / CAST(g.years_playing AS DOUBLE), 4), 0)) AS WinsYearsRatio"
			+ " FROM GlobalStats g ORDER BY total_wins/years_playing DESC LIMIT 10")
	List<WinYearRatioStatsDTO>  getTopWinsYearRatioLimited();
	
	@Query("SELECT new mye030.resultsFinder.dto.ScoreYearsRatioDTO(g.Display_Name, COALESCE(g.score, 0), COALESCE(g.years_playing, 0), COALESCE(ROUND(CAST(g.score AS DOUBLE) / CAST(g.years_playing AS DOUBLE), 4), 0)) AS ScoreYearsRatio"
			+ " FROM GlobalStats g ORDER BY score/years_playing DESC LIMIT 10")
	List<ScoreYearsRatioDTO>  getTopScoresYearRatioLimited();
	
}
