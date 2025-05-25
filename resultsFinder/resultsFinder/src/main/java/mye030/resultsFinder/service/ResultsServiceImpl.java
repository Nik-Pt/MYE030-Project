package mye030.resultsFinder.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.dto.ResultsDTO;
import mye030.resultsFinder.mappers.ResultsMapper;

@Service
public class ResultsServiceImpl implements ResultsService {
	
	@Autowired
	ResultsMapper resultsMapper;

	@Override
	public List<ResultsDTO> filterResults(int ISO_Code, int min, int max) {
		return resultsMapper.findAllMatchesYtoY(min, max, ISO_Code);
	}
	
	@Override
	public List<ResultsDTO> filterResultsByCountries(Integer year, String homeRegion, String awayRegion, String homeStatus, String awayStatus, String homeDevelopingOrDeveloped, String awayDevelopingOrDeveloped) {
		return resultsMapper.findFilteredResults(year, homeRegion, awayRegion, homeStatus, awayStatus, homeDevelopingOrDeveloped, awayDevelopingOrDeveloped);
	}

}
