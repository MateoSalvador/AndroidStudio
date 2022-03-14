package ec.epn.detri.awm.ppq;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoUtils {

    private  Geocoder geocoder;

    public GeoUtils(Context ctx){
        geocoder = new Geocoder(ctx, Locale.getDefault());
    }

    public String getCodigoZip(double lat, double lng) throws IOException{
        List<Address> direccion = geocoder.getFromLocation(lat,lng,1);
        return direccion.get(0).getPostalCode();
    }
}
