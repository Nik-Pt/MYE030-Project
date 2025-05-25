package mye030.resultsFinder.mappers;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mye030.resultsFinder.datamodel.Country;
import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.datamodel.Xores;

public interface XoresMapper extends JpaRepository<Xores, Integer> {
	
	@Query("SELECT x FROM Xores x WHERE ISO_Code = :iso_code")
	Xores findByIsoCode(@Param("iso_code") int iso_code);
}