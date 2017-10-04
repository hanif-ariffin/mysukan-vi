package masco.com.mysukan_vi.fragments.map;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import masco.com.mysukan_vi.R;
import masco.com.mysukan_vi.fragments.BaseFragment;

/**
 * Created by User on 2017-09-12.
 */

public class MapAmenitiesFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final static LatLng ottawaCity = new LatLng(45.393678, -75.674862);
    private final static LatLng masjid_ar_Rahmah = new LatLng(45.351783, -75.647358);
    private final static LatLng mosaic_canada = new LatLng(45.432860, -75.708003);
    private final static LatLng walmart_billing_bridge = new LatLng(45.385403, -75.679536);
    private final static LatLng rideau_centre = new LatLng(45.425120, -75.691228);
    private final static LatLng st_laurent_mall = new LatLng(45.422189, -75.639193);
    private final static LatLng butterfly_show = new LatLng(45.383751, -75.693586);
    private static View view;

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
        mMap.addMarker(new MarkerOptions().position(masjid_ar_Rahmah).title("Masjid ar-Rahmah"));
        mMap.addMarker(new MarkerOptions().position(mosaic_canada).title("Mosaic Canada 150"));
        mMap.addMarker(new MarkerOptions().position(walmart_billing_bridge).title("Walmart"));
        mMap.addMarker(new MarkerOptions().position(rideau_centre).title("Rideau Centre"));
        mMap.addMarker(new MarkerOptions().position(butterfly_show).title("Butterfly Show (End 8th Oct)"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ottawaCity, 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ottawaCity)      // Sets the center of the map to location user
                .zoom(12)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

}
