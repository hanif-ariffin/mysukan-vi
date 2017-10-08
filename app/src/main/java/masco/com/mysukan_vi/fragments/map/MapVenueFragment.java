package masco.com.mysukan_vi.fragments.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.fragments.BaseFragment;

/**
 * Created by User on 2017-09-07.
 */

public class MapVenueFragment extends BaseFragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    private final static LatLng carletonU = new LatLng(45.399196, -75.709853);
    private final static LatLng carletonU_UC = new LatLng(45.383331, -75.697630);
    private final static LatLng carletonU_NormFynn = new LatLng(45.385860, -75.692811);
    private final static LatLng carletonU_RavensNest = new LatLng(45.386450, -75.693282);
    private final static LatLng carletonU_FieldHouse = new LatLng(45.386843, -75.694530);
    private final static LatLng carletonU_parking_p5 = new LatLng(45.387292, -75.693663);
    private final static LatLng brewer_park = new LatLng(45.388139, -75.689722);
    private final static LatLng prayer_room = new LatLng(45.383688, -75.698232);
    private final static LatLng tom_brown_arena = new LatLng(45.407918, -75.723044);
    private static View view;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.map_venue_fragment, container, false);
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map_venue);
            mapFragment.getMapAsync(this);

        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int height = 170;
        int width = 100;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.mysukan_pinpoint);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        mMap.setBuildingsEnabled(true);
        //mMap.addMarker(new MarkerOptions().position(carletonU_UC).fragment_adminpage_login_text_title("University Centre").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addMarker(new MarkerOptions().position(carletonU_NormFynn).title("Norm Fynn").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addMarker(new MarkerOptions().position(carletonU_RavensNest).title("Raven's Nest").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addMarker(new MarkerOptions().position(carletonU_FieldHouse).title("Field House").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addMarker(new MarkerOptions().position(carletonU_parking_p5).title("Parking Lot").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addMarker(new MarkerOptions().position(brewer_park).title("Brewer Park").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addMarker(new MarkerOptions().position(prayer_room).title("Muslim Prayer Room [225A University Centre (2nd Floor)]"));
        mMap.addMarker(new MarkerOptions().position(tom_brown_arena).title("Tom Brown Arena").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(carletonU, 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(carletonU)      // Sets the center of the map to location user
                .zoom(13)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

}
