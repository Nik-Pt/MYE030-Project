package mye030.resultsFinder.mappers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.datamodel.ResultsKey;
import mye030.resultsFinder.datamodel.Xores;
import mye030.resultsFinder.dto.ResultsDTO;

public interface ResultsMapper extends JpaRepository<Results, ResultsKey>{
	
	@Query("SELECT new mye030.resultsFinder.dto.ResultsDTO(r.date, c1.Display_Name, c2.Display_Name, r.home_score, r.away_score, r.tournament, r.city, r.country, r.neutral)"
			+ "FROM Results r "
			+ "JOIN Country c1 ON r.home_team = c1.ISO_Code "
			+ "JOIN Country c2 ON r.away_team = c2.ISO_Code "
			+ "WHERE YEAR(r.date) >= :minYear "
			+ "AND YEAR(r.date) <= :maxYear "
			+ "AND (r.home_team = :ISO_Code OR r.away_team = :ISO_Code)")
	List<ResultsDTO> findAllMatchesYtoY(@Param("minYear") int minYear, @Param("maxYear") int maxYear, @Param("ISO_Code") int ISO_Code);
	
	@Query("SELECT new mye030.resultsFinder.dto.ResultsDTO(r.date, c1.Display_Name, c2.Display_Name, r.home_score, r.away_score, r.tournament, r.city, r.country, r.neutral) FROM Results r "
			+ "JOIN Country c1 ON r.home_team = c1.ISO_Code "
			+ "JOIN Country c2 ON r.away_team = c2.ISO_Code "
			+ "WHERE YEAR(r.date) = :year "
			+ "AND (:homeRegion IS NULL OR :homeRegion = '' OR c1.Continent LIKE %:homeRegion%) "
			+ "AND (:awayRegion IS NULL OR :awayRegion = '' OR c2.Continent LIKE %:awayRegion%) "
			+ "AND (:homeStatus IS NULL OR :homeStatus = '' OR c1.Status LIKE %:homeStatus%) "
			+ "AND (:awayStatus IS NULL OR :awayStatus = '' OR c2.Status LIKE %:awayStatus%) "
			+ "AND (:homeDev IS NULL OR :homeDev = '' OR c1.Developed_or_Developing = :homeDev) " 
			+ "AND (:awayDev IS NULL OR :awayDev = '' OR c2.Developed_or_Developing = :awayDev)")
	List<ResultsDTO> findFilteredResults(@Param("year") int year,
			@Param("homeRegion") String homeRegion,
            @Param("awayRegion") String awayRegion,
            @Param("homeStatus") String homeStatus,
            @Param("awayStatus") String awayStatus,
            @Param("homeDev") String homeDev,
            @Param("awayDev") String awayDev);
			
	}