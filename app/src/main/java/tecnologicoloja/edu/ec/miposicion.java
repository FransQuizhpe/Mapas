package tecnologicoloja.edu.ec;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class miposicion implements LocationListener {

    public static double latitud;
    public static double longitud;
    public static boolean statusGPS;
    public static Location coordenadas;

    @Override
    public void onLocationChanged(@NonNull Location location) {

        latitud=location.getLatitude();
        longitud=location.getLongitude();
        coordenadas=location;

    }

    @Override
    public void onProviderEnabled (@NonNull String provider) {
        statusGPS=true;
    }

    @Override
    public void onProviderDisabled (@NonNull String provider) {
        statusGPS=false;
    }


}
