package mye030.resultsFinder.service;

import java.util.List;
import mye030.resultsFinder.datamodel.Country;
import mye030.resultsFinder.datamodel.GlobalStats;
import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.dto.ResultsDTO;

public interface CountryService {
	public Country findCountry();
	public Country findCountryByIsoCode(int ISO_Code);
	public List<Country> findCountriesByName(String query);
	public List<ResultsDTO> findAllMatchesYtoY(int minYear, int maxYear, int iso);
	public List<Double> findAreasByIsoCode(List<GlobalStats> stats);
	public List<Double> findPopulationsByIsoCode(List<GlobalStats> stats);
	public List<String> findCountriesByIso(List<Integer> countryIsos);
	
}