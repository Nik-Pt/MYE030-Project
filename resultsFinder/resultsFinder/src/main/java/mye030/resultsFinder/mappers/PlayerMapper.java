package mye030.resultsFinder.mappers;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mye030.resultsFinder.datamodel.Player;
import mye030.resultsFinder.datamodel.PlayerKey;

public interface PlayerMapper extends JpaRepository<Player, PlayerKey>{
	
	@Query("SELECT p FROM Player p WHERE scorer LIKE %:name%")
	List<Player> findPlayersByNameContaining(@Param("name") String name);
	
	
	@Query("SELECT p FROM Player p WHERE scorer = :name AND ISO_Code = :iso_code")
	Player findPlayerByName(@Param("name") String name, @Param("iso_code") int iso_code);
	
	@Query("SELECT COUNT(sc.team) FROM Scorer sc WHERE (sc.home_team = :iso_code OR sc.away_team = :iso_code) AND sc.scorer = :name AND YEAR(sc.date) = :year")
	Integer findPlayerGoalsPerYear(@Param("name") String name, @Param("iso_code") int iso_code, @Param("year") long year);
}
