package ec.epn.detri.awm.ppq;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlacaUnitTest {
    // Considere dia Doming =1, Lunes=2, ...
    /*@Test
    public void puedoCircular_Lunes_True(){
        assertTrue(Placa.puedoCircular(2,5));
    }

    @Test
    public void puedoCircular_Lunes_False(){
        assertFalse(Placa.puedoCircular(2,1));
    }*/

    @Test
    public void esParticular_Error(){

        assertFalse(Placa.placaEsParticular("PR-0329"));
    }
    @Test
    public void esParticular_True(){

        assertTrue(Placa.placaEsParticular("PRR-0329"));
    }
    @Test
    public void esParticular_False(){

        assertFalse(Placa.placaEsParticular("PMR-0329"));
    }
}
