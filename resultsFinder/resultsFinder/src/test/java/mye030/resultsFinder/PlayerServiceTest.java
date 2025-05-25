package mye030.resultsFinder;

import mye030.resultsFinder.datamodel.Player;
import mye030.resultsFinder.mappers.PlayerMapper;
import mye030.resultsFinder.service.PlayerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player();
        testPlayer.setScorer("Ronaldo");
        testPlayer.setISO_Code(123);
        testPlayer.setStarting_year(2010L);
        testPlayer.setEnding_year(2012L); 
    }
    
    @Test
    void testFindPlayersByName() {
        List<Player> expected = List.of(testPlayer);
        when(playerMapper.findPlayersByNameContaining("Ron")).thenReturn(expected);

        List<Player> result = playerService.findPlayersByName("Ron");

        assertEquals(1, result.size());
        assertEquals("Ronaldo", result.get(0).getScorer());
        verify(playerMapper).findPlayersByNameContaining("Ron");
    }
    
    @Test
    void testFindPlayerByName() {
        when(playerMapper.findPlayerByName("Ronaldo", 123)).thenReturn(testPlayer);

        Player result = playerService.findPlayerByName("Ronaldo", 123);

        assertNotNull(result);
        assertEquals("Ronaldo", result.getScorer());
        verify(playerMapper).findPlayerByName("Ronaldo", 123);
    }
    
    @Test
    void testFindGoalsMatchesRatioPerYear() {
        List<Integer> matches = Arrays.asList(10, 20, 0); 
        when(playerMapper.findPlayerGoalsPerYear("Ronaldo", 123, 2010L)).thenReturn(5);
        when(playerMapper.findPlayerGoalsPerYear("Ronaldo", 123, 2011L)).thenReturn(10);
        when(playerMapper.findPlayerGoalsPerYear("Ronaldo", 123, 2012L)).thenReturn(0);

        List<Float> result = playerService.findGoalsMatchesRatioPerYear(testPlayer, matches);

        assertEquals(3, result.size());
        assertEquals(0.5f, result.get(0));
        assertEquals(0.5f, result.get(1));
        assertTrue(Float.isInfinite(result.get(2)) || Float.isNaN(result.get(2)));

        verify(playerMapper).findPlayerGoalsPerYear("Ronaldo", 123, 2010L);
        verify(playerMapper).findPlayerGoalsPerYear("Ronaldo", 123, 2011L);
        verify(playerMapper).findPlayerGoalsPerYear("Ronaldo", 123, 2012L);
    }
    
    @Test
    void testFindYearsPlaying() {
        List<Long> result = playerService.findYearsPlaying(testPlayer);

        assertEquals(List.of(2010L, 2011L, 2012L), result);
    }

}