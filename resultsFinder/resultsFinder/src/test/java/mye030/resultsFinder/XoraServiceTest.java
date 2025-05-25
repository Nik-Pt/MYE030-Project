package mye030.resultsFinder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mye030.resultsFinder.datamodel.Xores;
import mye030.resultsFinder.mappers.XoresMapper;
import mye030.resultsFinder.service.XoraServiceImpl;

public class XoraServiceTest {

    @Mock
    private XoresMapper xoraMapper;

    @InjectMocks
    private XoraServiceImpl xoraService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindXoresByIsoCode() {
        Xores mockXora = new Xores();
        
        mockXora.setDisplay_Name("Greece");
        mockXora.setISO_Code(123);
        
        
        Xores mockXora1 = new Xores();
        
        mockXora1.setDisplay_Name("Albania");
        mockXora1.setISO_Code(666);
        
        when(xoraMapper.findByIsoCode(123)).thenReturn(mockXora);
        when(xoraMapper.findByIsoCode(666)).thenReturn(mockXora1);

        Xores result = xoraService.findXoresByIsoCode(123);

        assertNotNull(result);
        assertEquals(mockXora, result);
        assertEquals(result.getDisplay_Name(), "Greece");
        assertNotEquals(result.getDisplay_Name(), "Albania");
        verify(xoraMapper, times(1)).findByIsoCode(123);
    }
}
