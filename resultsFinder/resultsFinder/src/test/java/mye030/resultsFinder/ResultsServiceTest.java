package mye030.resultsFinder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mye030.resultsFinder.datamodel.Results;
import mye030.resultsFinder.dto.ResultsDTO;
import mye030.resultsFinder.mappers.ResultsMapper;
import mye030.resultsFinder.service.ResultsServiceImpl;

public class ResultsServiceTest {

    @Mock
    private ResultsMapper resultsMapper;

    @InjectMocks
    private ResultsServiceImpl resultsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterResults() {
        List<ResultsDTO> mockedResults = Arrays.asList(new ResultsDTO(), new ResultsDTO());
        int isoCode = 123;
        int minYear = 2010;
        int maxYear = 2020;

        when(resultsMapper.findAllMatchesYtoY(minYear, maxYear, isoCode)).thenReturn(mockedResults);

        List<ResultsDTO> result = resultsService.filterResults(isoCode, minYear, maxYear);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(resultsMapper, times(1)).findAllMatchesYtoY(minYear, maxYear, isoCode);
    }
    
    @Test
    void testFilterResultsByCountries() {
        List<ResultsDTO> mockedResults = Arrays.asList(new ResultsDTO(), new ResultsDTO(), new ResultsDTO());

        Integer year = 2021;
        String homeRegion = "Europe";
        String awayRegion = "Asia";
        String homeStatus = "Independent";
        String awayStatus = "Independent";
        String homeDev = "Developed";
        String awayDev = "Developing";

        when(resultsMapper.findFilteredResults(year, homeRegion, awayRegion, homeStatus, awayStatus, homeDev, awayDev))
                .thenReturn(mockedResults);

        List<ResultsDTO> result = resultsService.filterResultsByCountries(year, homeRegion, awayRegion, homeStatus, awayStatus, homeDev, awayDev);

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(resultsMapper, times(1)).findFilteredResults(year, homeRegion, awayRegion, homeStatus, awayStatus, homeDev, awayDev);
    }
    
}