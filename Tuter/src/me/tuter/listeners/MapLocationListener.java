package me.tuter.listeners;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapLocationListener implements LocationListener{
	public static final String TAG = "MapLocationListener";
	private GoogleMap mMap;
	
	public MapLocationListener(GoogleMap map)
	{
		this.mMap = map;
	}
	@Override
	public void onLocationChanged(Location location) {
		LatLng coord = new LatLng(location.getLatitude(), location.getLongitude());
		
		Log.d(TAG, "My location is: " + location.getLatitude() + ", " + location.getLongitude());
		this.mMap.addMarker(new MarkerOptions().position(coord).title("You!"));
	}
	

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
