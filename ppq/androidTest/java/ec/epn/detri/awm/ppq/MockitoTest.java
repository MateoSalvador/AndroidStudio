package ec.epn.detri.awm.ppq;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import androidx.test.platform.app.InstrumentationRegistry;


@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

   //  private static final String FAKE_STRING = "HELLO WORLD"; geoutils = new GeoUtils(mockContext);

    @Mock
    Context mMockedContext;
    private  GeoUtils geoutils;

    @Before
    public void setUp(){
        Context ctx = InstrumentationRegistry.getInstrumentation().getTargetContext();
        geoutils = new GeoUtils(ctx);
    }

    @Test
    public void test_when_thenReturn(){
        ActividadResultado activity = Mockito.mock(ActividadResultado.class);
        when(activity.getName()).thenReturn("ActividadResultado");
        assertThat(activity.getName(),is("ActividadResultado"));
    }

    @Test
    public void getCodigoZipQuito() throws Exception {
        String codigoZip = geoutils.getCodigoZip(36.139017, -86.796924);
        System.out.println(codigoZip);
        assertEquals("37212", codigoZip);
    }

    @Test
    public void test_verify(){
        ActividadResultado activity = Mockito.mock(ActividadResultado.class);

        when(activity.getName()).thenReturn("ActividadResultado");
        when(activity.getNumber(anyInt())).thenReturn(0);

        //verify if getName() is never called
        verify(activity,never()).getName();

        //now call it one time
        activity.getName();

        //verify if it is called once
        verify(activity,atLeastOnce()).getName();

        //call getNumber method with a parameter
        activity.getNumber(1);

        //verify if getNumber was called with parameter 1
        verify(activity).getNumber(1);
    }
}
