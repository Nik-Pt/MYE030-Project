package mye030.resultsFinder.mappers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mye030.resultsFinder.datamodel.Country;

public interface CountryMapper extends JpaRepository<Country, Integer>{
	Country findById(int Id);
	
	@Query("SELECT c FROM Country c WHERE Display_Name LIKE %:name% OR Official_Name LIKE %:name%")
	List<Country> findByNameContaining(@Param("name") String name);
	
	@Query("SELECT c FROM Country c WHERE ISO_Code = :iso_code")
	Country findByIsoCode(@Param("iso_code") int iso_code);
	
	@Query("SELECT c.Area_SqKm FROM Country c WHERE ISO_Code = :iso_code")
	double findAreaByIsoCode(@Param("iso_code") int iso_code);
	
	@Query("SELECT c.Population FROM Country c WHERE ISO_Code = :iso_code")
	double findPopulationByIsoCode(@Param("iso_code") int iso_code);
}
