package mye030.resultsFinder;

import mye030.resultsFinder.datamodel.Country;
import mye030.resultsFinder.datamodel.GlobalStats;
import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.dto.ResultsDTO;
import mye030.resultsFinder.mappers.CountryMapper;
import mye030.resultsFinder.mappers.ResultsMapper;
import mye030.resultsFinder.mappers.XoresMapper;
import mye030.resultsFinder.service.CountryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CountryServiceTest {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private CountryMapper countryMapper;

    @Mock
    private ResultsMapper resultsMapper;

    @Mock
    private XoresMapper xoresMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindCountry() {
        Country mockCountry = new Country();
        mockCountry.setDisplay_Name("Greece");
        when(countryMapper.findById(0)).thenReturn(mockCountry);

        Country result = countryService.findCountry();

        assertNotNull(result);
        assertEquals("Greece", result.getDisplay_Name());
        verify(countryMapper).findById(0);
    }
    
    @Test
    void testFindAllMatchesYtoY() {
    	ResultsDTO result1 = new ResultsDTO();
        ResultsDTO result2 = new ResultsDTO();
        List<ResultsDTO> mockResults = List.of(result1, result2);

        when(resultsMapper.findAllMatchesYtoY(2000, 2010, 1234)).thenReturn(mockResults);

        List<ResultsDTO> results = countryService.findAllMatchesYtoY(2000, 2010, 1234);

        assertEquals(2, results.size());
        verify(resultsMapper).findAllMatchesYtoY(2000, 2010, 1234);
    }
    
    @Test
    void testFindCountriesByName() {
        Country c1 = new Country();
        c1.setDisplay_Name("Germany");
        when(countryMapper.findByNameContaining("Ger")).thenReturn(List.of(c1));

        List<Country> results = countryService.findCountriesByName("Ger");

        assertEquals(1, results.size());
        assertEquals("Germany", results.get(0).getDisplay_Name());
    }

    @Test
    void testFindAreasByIsoCode() {
        GlobalStats g1 = new GlobalStats(); g1.setISO_Code(111);
        GlobalStats g2 = new GlobalStats(); g2.setISO_Code(222);
        List<GlobalStats> stats = List.of(g1, g2);

        when(countryMapper.findAreaByIsoCode(111)).thenReturn(10.0);
        when(countryMapper.findAreaByIsoCode(222)).thenReturn(20.0);

        List<Double> areas = countryService.findAreasByIsoCode(stats);

        assertEquals(List.of(10.0, 20.0), areas);
    }
    
    @Test
    void testFindPopulationsByIsoCode() {
        GlobalStats g1 = new GlobalStats(); g1.setISO_Code(333);
        GlobalStats g2 = new GlobalStats(); g2.setISO_Code(444);
        List<GlobalStats> stats = List.of(g1, g2);

        when(countryMapper.findPopulationByIsoCode(333)).thenReturn(5.0);
        when(countryMapper.findPopulationByIsoCode(444)).thenReturn(8.0);

        List<Double> populations = countryService.findPopulationsByIsoCode(stats);

        assertEquals(List.of(5.0, 8.0), populations);
    }
    
    @Test
    void testFindCountriesByIso() {
        int iso1 = 100;
        int iso2 = 200;

        Country c1 = new Country(); c1.setDisplay_Name("France");
        Country c2 = new Country(); c2.setDisplay_Name("Italy");

        when(countryMapper.findByIsoCode(iso1)).thenReturn(c1);
        when(countryMapper.findByIsoCode(iso2)).thenReturn(c2);

        List<String> result = countryService.findCountriesByIso(List.of(iso1, iso2));

        assertEquals(List.of("France", "Italy"), result);
    }
}