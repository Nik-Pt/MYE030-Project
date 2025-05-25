package mye030.resultsFinder.service;

import java.util.List;
import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.dto.ResultsDTO;

public interface ResultsService {
	public List<ResultsDTO> filterResults(int ISO_Code, int min, int max);
	public List<ResultsDTO> filterResultsByCountries(Integer year, String homeRegion, String awayRegion, String homeStatus, String awayStatus, String homeDevelopingOrDeveloped, String awayDevelopingOrDeveloped);
}
