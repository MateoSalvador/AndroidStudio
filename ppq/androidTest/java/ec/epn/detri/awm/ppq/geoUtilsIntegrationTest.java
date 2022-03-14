package ec.epn.detri.awm.ppq;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class geoUtilsIntegrationTest {

    private  GeoUtils geoutils;

    @Before
    public void setUp(){
        Context ctx = InstrumentationRegistry.getInstrumentation().getTargetContext();
        geoutils = new GeoUtils(ctx);
    }

    @Test
    public void getCodigoZipToFail() throws Exception {
        String codigoZip = geoutils.getCodigoZip(0,0);
        assertEquals("12345", codigoZip);
    }

    @Test
    public void getCodigoZipQuito() throws Exception {
        String codigoZip = geoutils.getCodigoZip(36.139017, -86.796924);
        System.out.println(codigoZip);
        assertEquals("37212", codigoZip);
    }
    //170143
}