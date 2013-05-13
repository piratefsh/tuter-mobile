package me.tuter.fragments;

import java.util.HashMap;
import java.util.List;

import me.tuter.activities.SearchResultsActivity;
import me.tuter.activities.ShowUserActivity;
import me.tutor.datastructures.Location;
import me.tutor.datastructures.User;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchResultsMapFragment extends SupportMapFragment {
	public final static String TAG = "SearchResultsMapFragment";
	private GoogleMap mMap;
	private SearchResultsActivity mActivity;
	private Marker mMyMarker;
	private HashMap <Marker, User> mMarkerToUser;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.mActivity = (SearchResultsActivity) this.getActivity();
		this.mMarkerToUser = new HashMap<Marker, User>();
		
		View v = super.onCreateView(inflater,  container, savedInstanceState);
		initMap();
		
		return v;
	}
	
	private void initMap(){
		this.mMap = this.getMap();
		this.mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener()
		{
			@Override
			public void onInfoWindowClick(Marker marker) {
				User u = SearchResultsMapFragment.this.mMarkerToUser.get(marker);
				Intent i = new Intent(SearchResultsMapFragment.this.mActivity.getApplicationContext(), ShowUserActivity.class);
				Bundle extras = new Bundle();
				extras.putString(SearchResultsActivity.USER_JSON, u.getJSONString());
				i.putExtras(extras);
				SearchResultsMapFragment.this.mActivity.startActivity(i);
				
			}
		});
		this.getList(this.mActivity.getList());
	}
	
	private void setUserLocation()
	{
		android.location.Location myLoc = getMyLocation();
		LatLng myLatLng = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
		
		MarkerOptions me = new MarkerOptions().position(myLatLng).title("ME!")
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
		
		if(this.mMyMarker == null)
			this.mMyMarker = this.mMap.addMarker(me);
		else
			this.mMyMarker.setPosition(myLatLng);
		
		this.mMyMarker.showInfoWindow();
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 12));
	}
	
	private android.location.Location getMyLocation() {
	    // Get location from GPS if it's available
	    LocationManager lm = (LocationManager)this.mActivity.getSystemService(Context.LOCATION_SERVICE);
	    android.location.Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

	    // Location wasn't found, check the next most accurate place for the current location
	    if (myLocation == null) {
	        Criteria criteria = new Criteria();
	        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
	        // Finds a provider that matches the criteria
	        String provider = lm.getBestProvider(criteria, true);
	        // Use the provider to get the last known location
	        myLocation = lm.getLastKnownLocation(provider);
	    }

	    return myLocation;
	}
	
	public void getList(List<User> users)
	{
		this.setUserLocation();
		
		if(users != null)
		{
			//add markers to map
			for(User u : users)
			{
				Location loc = u.loc;
				Marker m = this.mMap.addMarker(new MarkerOptions().position(loc.coords).title(u.getFullName()).snippet(loc.address));
				this.mMarkerToUser.put(m, u);
			}
		}
		
	}
}
