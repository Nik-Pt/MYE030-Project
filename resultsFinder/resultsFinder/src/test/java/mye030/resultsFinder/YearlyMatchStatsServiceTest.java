package mye030.resultsFinder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mye030.resultsFinder.datamodel.YearlyMatchStats;
import mye030.resultsFinder.dto.YearlyMatchStatsDTO;
import mye030.resultsFinder.mappers.YearlyMatchStatsMapper;
import mye030.resultsFinder.service.YearlyMatchStatsServiceImpl;

class YearlyMatchStatsServiceTest {

    @Mock
    private YearlyMatchStatsMapper yearlyMatchStatsMapper;

    @InjectMocks
    private YearlyMatchStatsServiceImpl yearlyMatchStatsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCountryPerYear() {
        List<YearlyMatchStats> mockList = List.of(new YearlyMatchStats());
        when(yearlyMatchStatsMapper.findCountryPerYear(100)).thenReturn(mockList);

        List<YearlyMatchStats> result = yearlyMatchStatsService.findCountryPerYear(100);
        assertEquals(mockList, result);
    }
    
    @Test
    void testFindCountryPerYearStats() {
        YearlyMatchStats stat = new YearlyMatchStats();
        stat.setTotal_year_wins(3);
        stat.setTotal_year_losses(2);
        stat.setTotal_year_draws(1);
        stat.setTotal_year_matches(6);
        stat.setYear(2010);

        when(yearlyMatchStatsMapper.findCountryPerYear(100)).thenReturn(List.of(stat));

        List<List<Integer>> result = yearlyMatchStatsService.findCountryPerYearStats(100);
        assertEquals(List.of(3), result.get(0)); //wins
        assertEquals(List.of(2), result.get(1)); //losses
        assertEquals(List.of(1), result.get(2)); //draws
        assertEquals(List.of(6), result.get(3)); //matches
        assertEquals(List.of(2010), result.get(4)); //years
    }
    
    @Test
    void testFindYearStats() {
        YearlyMatchStatsDTO dto = new YearlyMatchStatsDTO(100, 30, 5, 40, 30);
        List<YearlyMatchStatsDTO> mockList = List.of(dto);

        when(yearlyMatchStatsMapper.findYearStats(2010)).thenReturn(mockList);

        List<YearlyMatchStatsDTO> result = yearlyMatchStatsService.findYearStats(2010);

        assertNotNull(result);
        assertEquals(1, result.size());

        YearlyMatchStatsDTO resultDTO = result.get(0);
        assertEquals(100, resultDTO.getTotal_year_matches());
        assertEquals(30, resultDTO.getTotal_year_draws());
        assertEquals(5, resultDTO.getHad_shootouts());
        assertEquals(40, resultDTO.getTotal_year_wins());
        assertEquals(30, resultDTO.getTotal_year_losses());

        verify(yearlyMatchStatsMapper, times(1)).findYearStats(2010);
    }

    @Test
    void testFindTotalMatchesInYearRange() {
        when(yearlyMatchStatsMapper.findTotalYearMatch(2010L, 100)).thenReturn(5);
        when(yearlyMatchStatsMapper.findTotalYearMatch(2011L, 100)).thenReturn(6);

        List<Integer> result = yearlyMatchStatsService.findTotalMatchesInYearRange(2010L, 2011L, 100);
        assertEquals(List.of(5, 6), result);
    }

    @Test
    void testFindCountriesInYear() {
        List<Integer> countries = List.of(100, 200);
        when(yearlyMatchStatsMapper.findCountriesInYear(2010)).thenReturn(countries);

        List<Integer> result = yearlyMatchStatsService.findCountriesInYear(2010);
        assertEquals(countries, result);
    }
    
    @Test
    void testFindCountryStatsForYear() {
        YearlyMatchStatsDTO dto = new YearlyMatchStatsDTO(50, 10, 3, 25, 15);
        
        when(yearlyMatchStatsMapper.findCountryYearStats(2010, 100)).thenReturn(dto);
        
        List<YearlyMatchStatsDTO> result = yearlyMatchStatsService.findCountryStatsForYear(List.of(100), 2010);
        
        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
        
        YearlyMatchStatsDTO r = result.get(0);
        assertEquals(50, r.getTotal_year_matches());
        assertEquals(10, r.getTotal_year_draws());
        assertEquals(3, r.getHad_shootouts());
        assertEquals(25, r.getTotal_year_wins());
        assertEquals(15, r.getTotal_year_losses());

        verify(yearlyMatchStatsMapper, times(1)).findCountryYearStats(2010, 100);
    }

}    