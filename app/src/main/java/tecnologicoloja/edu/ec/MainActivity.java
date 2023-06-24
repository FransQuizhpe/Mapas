package tecnologicoloja.edu.ec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    EditText txtlatitud;
    EditText txtlongitud;

    Button btnubicar;

    private SupportMapFragment ubicamap;



    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableMyLocation();
        txtlatitud=(EditText) findViewById(R.id.editTextText);
        txtlongitud=(EditText) findViewById(R.id.editTextText2);
        btnubicar=(Button) findViewById(R.id.button);

        btnubicar.setOnClickListener(this::ubicar);

        ubicamap =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        
    }



    public void ubicar (View v) {

        if (v == btnubicar){
            miposicion();
            ubicamap.getMapAsync(this);

        }

    }

    private void miposicion (){

        LocationManager objLocation = null;
        LocationListener objLocListener;
        objLocation = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        objLocListener = new miposicion();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,},1000);

        }

        objLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, objLocListener);

        if(objLocation.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            if (miposicion.latitud != 0){
                double lat = miposicion.latitud;
                double lon = miposicion.longitud;
                txtlongitud.setText(lat+"");
                txtlatitud.setText(lon+"");
            }

        }

    }

    private void enableMyLocation(){

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},1000);

        }

    }


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            mMap = googleMap;
            double lat = miposicion.latitud;
            double lon = miposicion.longitud;

            txtlatitud.setText(String.valueOf(lat));
            txtlongitud.setText(String.valueOf(lon));

            LatLng ubicacion = new LatLng(lat,lon);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
            mMap.addMarker(new MarkerOptions().position(ubicacion).title("Ubicación"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));

        }



}