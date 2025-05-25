package mye030.resultsFinder;

import mye030.resultsFinder.datamodel.GlobalStats;
import mye030.resultsFinder.dto.ScoreYearsRatioDTO;
import mye030.resultsFinder.dto.WinYearRatioStatsDTO;
import mye030.resultsFinder.mappers.GlobalStatsMapper;
import mye030.resultsFinder.service.GlobalStatsServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GlobalStatsServiceTest {

    @InjectMocks
    private GlobalStatsServiceImpl globalStatsService;

    @Mock
    private GlobalStatsMapper globalStatsMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindStats() {
        GlobalStats stats = new GlobalStats();
        when(globalStatsMapper.findById(0)).thenReturn(stats);

        GlobalStats result = globalStatsService.findStats();

        assertNotNull(result);
        verify(globalStatsMapper).findById(0);
    }
    
    @Test
    void testFindTopWins() {
        GlobalStats stats = new GlobalStats();
        when(globalStatsMapper.getTopWins()).thenReturn(List.of(stats));

        List<GlobalStats> result = globalStatsService.findTopWins();

        assertEquals(1, result.size());
        verify(globalStatsMapper).getTopWins();
    }
    
    @Test
    void testFindTopScores() {
        GlobalStats stats = new GlobalStats();
        when(globalStatsMapper.getTopScores()).thenReturn(List.of(stats));

        List<GlobalStats> result = globalStatsService.findTopScores();

        assertEquals(1, result.size());
        verify(globalStatsMapper).getTopScores();
    }
    
    @Test
    void testFindTopWinsYearRatio() {
        WinYearRatioStatsDTO dto = new WinYearRatioStatsDTO("Greece", 20, 5, 4.0);
        when(globalStatsMapper.getTopWinsYearRatio()).thenReturn(List.of(dto));

        List<WinYearRatioStatsDTO> result = globalStatsService.findTopWinsYearRatio();

        assertEquals(1, result.size());
        assertEquals("Greece", result.get(0).getDisplay_Name());
        assertEquals(20, result.get(0).getTotal_wins());
        assertEquals(5, result.get(0).getYears_playing());
        assertEquals(4.0, result.get(0).getWinsYearsRatio());
        verify(globalStatsMapper).getTopWinsYearRatio();
    }

    
    @Test
    void testFindTopScoresYearRatio() {
        ScoreYearsRatioDTO dto = new ScoreYearsRatioDTO("France", 50, 10, 5.0);

        when(globalStatsMapper.getTopScoresYearRatio()).thenReturn(List.of(dto));

        List<ScoreYearsRatioDTO> result = globalStatsService.findTopScoresYearRatio();

        assertEquals(1, result.size());
        assertEquals("France", result.get(0).getDisplay_Name());
        assertEquals(50, result.get(0).getScore());
        assertEquals(10, result.get(0).getYears_playing());
        assertEquals(5.0, result.get(0).getScoreYearsRatio());

        verify(globalStatsMapper).getTopScoresYearRatio();
    }

    @Test
    void testFindTopTenByWins() {
        when(globalStatsMapper.getTopWinsLimited()).thenReturn(List.of(new GlobalStats()));
        List<GlobalStats> result = globalStatsService.findTopTenByWins();

        assertEquals(1, result.size());
        verify(globalStatsMapper).getTopWinsLimited();
    }
    
    @Test
    void testFindTopTenByScores() {
        when(globalStatsMapper.getTopScoresLimited()).thenReturn(List.of(new GlobalStats()));
        List<GlobalStats> result = globalStatsService.findTopTenByScores();

        assertEquals(1, result.size());
        verify(globalStatsMapper).getTopScoresLimited();
    }
    
    @Test
    void testFindTopTenByWinsYearRatio() {
        WinYearRatioStatsDTO dto = new WinYearRatioStatsDTO("Spain", 30, 6, 5.0);
        when(globalStatsMapper.getTopWinsYearRatioLimited()).thenReturn(List.of(dto));

        List<WinYearRatioStatsDTO> result = globalStatsService.findTopTenByWinsYearRatio();

        assertEquals(1, result.size());
        assertEquals("Spain", result.get(0).getDisplay_Name());
        verify(globalStatsMapper).getTopWinsYearRatioLimited();
    }

    @Test
    void testFindTopTenByScoresYearRatio() {
        ScoreYearsRatioDTO dto = new ScoreYearsRatioDTO("Germany", 100, 10, 10.0);
        when(globalStatsMapper.getTopScoresYearRatioLimited()).thenReturn(List.of(dto));

        List<ScoreYearsRatioDTO> result = globalStatsService.findTopTenByScoresYearRatio();

        assertEquals(1, result.size());
        assertEquals("Germany", result.get(0).getDisplay_Name());
        verify(globalStatsMapper).getTopScoresYearRatioLimited();
    }

}