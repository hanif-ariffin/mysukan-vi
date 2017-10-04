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

public class MapRestaurantFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final static LatLng ottawaCity = new LatLng(45.410492, -75.683090);
    private final static LatLng beaverTails = new LatLng(45.396261, -75.705869);
    private final static LatLng arianaKabab = new LatLng(45.372508, -75.662594);
    private final static LatLng sayCheese = new LatLng(45.379425, -75.667502);
    private final static LatLng popeyes = new LatLng(45.378554, -75.644269);
    private final static LatLng central_bergham = new LatLng(45.378077, -75.666855);
    private final static  LatLng palki_cuisine_of_india = new LatLng(45.425454, -75.632652);
    private final static LatLng burger_fries_forever = new LatLng(45.414379, -75.695334);
    private final static LatLng basmati_indian_cuisine = new LatLng(45.415365, -75.696804);
    private final static LatLng nandos_ottawa = new LatLng(45.422275, -75.694532);
    private final static LatLng assayyed_restaurant = new LatLng(45.376817, -75.666964);
    private final static LatLng the_captains_boil = new LatLng(45.415379, -75.688539);



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
        mMap.addMarker(new MarkerOptions().position(beaverTails).title("Beaver Tails"));
        mMap.addMarker(new MarkerOptions().position(arianaKabab).title("Ariana Kabab House"));
        mMap.addMarker(new MarkerOptions().position(sayCheese).title("Say Cheese"));
        mMap.addMarker(new MarkerOptions().position(popeyes).title("Popeyes"));
        mMap.addMarker(new MarkerOptions().position(central_bergham).title("Central Bergham"));
        mMap.addMarker(new MarkerOptions().position(palki_cuisine_of_india).title("Palki Cuisine Of India").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addMarker(new MarkerOptions().position(burger_fries_forever).title("Burger And Fries Forever"));
        mMap.addMarker(new MarkerOptions().position(basmati_indian_cuisine).title("Basmati Indian Cuisine"));
        mMap.addMarker(new MarkerOptions().position(nandos_ottawa).title("Nando's Ottawa"));
        mMap.addMarker(new MarkerOptions().position(assayyed_restaurant).title("Assayyed Restaurant"));
        mMap.addMarker(new MarkerOptions().position(the_captains_boil).title("The Captain's Boil"));
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
