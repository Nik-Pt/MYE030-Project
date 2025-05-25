package mye030.resultsFinder.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mye030.resultsFinder.datamodel.Country;
import mye030.resultsFinder.datamodel.GlobalStats;
import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.dto.ResultsDTO;
import mye030.resultsFinder.mappers.CountryMapper;
import mye030.resultsFinder.mappers.ResultsMapper;
import mye030.resultsFinder.mappers.XoresMapper;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryMapper countryMapper;
	
	@Autowired
	private XoresMapper xoraMapper;
	
	@Autowired
	private ResultsMapper resultsMapper;

	@Override
	public Country findCountry() {
		Country country = countryMapper.findById(0);
		System.out.println("\n\n\n");
		System.out.println(country);
		System.out.println("\n");
		return country;
	}

	@Override
	public List<ResultsDTO> findAllMatchesYtoY(int minYear, int maxYear, int iso) {
		return resultsMapper.findAllMatchesYtoY(minYear, maxYear, iso);
	}

	@Override
	public List<Country> findCountriesByName(String query) {
		return countryMapper.findByNameContaining(query);
	}

	@Override
	public Country findCountryByIsoCode(int ISO_Code) {
		return countryMapper.findByIsoCode(ISO_Code);
	}

	@Override
	public List<Double> findAreasByIsoCode(List<GlobalStats> stats) {
		List<Double> results = new ArrayList<>();
		for (GlobalStats stat : stats) {
			results.add(countryMapper.findAreaByIsoCode(stat.getISO_Code()));
		}
		return results;
	}

	@Override
	public List<Double> findPopulationsByIsoCode(List<GlobalStats> stats) {
		List<Double> results = new ArrayList<>();
		for (GlobalStats stat : stats) {
			results.add(countryMapper.findPopulationByIsoCode(stat.getISO_Code()));
		}
		return results;
	}

	@Override
	public List<String> findCountriesByIso(List<Integer> countryISOs) {
		List<String> countries = new ArrayList<>();
		for (int i = 0; i < countryISOs.size(); i++) {
			countries.add(findCountryByIsoCode(countryISOs.get(i)).getDisplay_Name());
		}
		return countries;
	}

}