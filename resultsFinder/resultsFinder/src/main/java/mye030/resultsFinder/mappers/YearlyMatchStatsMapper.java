package mye030.resultsFinder.mappers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mye030.resultsFinder.datamodel.YearlyMatchStats;
import mye030.resultsFinder.datamodel.YearlyMatchStatsKey;
import mye030.resultsFinder.dto.YearlyMatchStatsDTO;

public interface YearlyMatchStatsMapper extends JpaRepository<YearlyMatchStats, YearlyMatchStatsKey> {
	
	@Query("SELECT y FROM YearlyMatchStats y WHERE :ISO_Code = y.ISO_Code")
    List<YearlyMatchStats> findCountryPerYear(@Param("ISO_Code") int ISO_Code);
	
	@Query("SELECT new mye030.resultsFinder.dto.YearlyMatchStatsDTO (SUM(y.total_year_matches) / 2, SUM(y.total_year_draws) / 2 , SUM(y.had_shootouts) / 2)"
			+ " FROM YearlyMatchStats y"
			+ " WHERE y.year = :year")
	List<YearlyMatchStatsDTO> findYearStats(@Param("year") int year);
	
	@Query("SELECT y.ISO_Code FROM YearlyMatchStats y WHERE year = :year")
	List<Integer> findCountriesInYear(@Param("year") int year);
	
	@Query("SELECT y.total_year_matches, 0 FROM YearlyMatchStats y WHERE y.year = :year AND y.ISO_Code = :iso_code")
	Integer findTotalYearMatch(@Param("year") long year, @Param("iso_code") int iso_code);
	

	@Query("SELECT new mye030.resultsFinder.dto.YearlyMatchStatsDTO (SUM(y.total_year_matches), SUM(y.total_year_draws) , SUM(y.had_shootouts), SUM(y.total_year_wins), SUM(y.total_year_losses))"
			+ " FROM YearlyMatchStats y"
			+ " WHERE y.year = :year AND y.ISO_Code = :isoCode")
	YearlyMatchStatsDTO findCountryYearStats(@Param("year") int year, @Param("isoCode") int isoCode);
}
