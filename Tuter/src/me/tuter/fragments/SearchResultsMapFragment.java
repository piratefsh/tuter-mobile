package me.tuter.fragments;

import java.util.List;

import me.tuter.activities.SearchResultsActivity;
import me.tutor.datastructures.Location;
import me.tutor.datastructures.User;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchResultsMapFragment extends SupportMapFragment {
	
	private GoogleMap mMap;
	private SearchResultsActivity mActivity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.mActivity = (SearchResultsActivity) this.getActivity();
		
		View v = super.onCreateView(inflater,  container, savedInstanceState);
		initMap();
		return v;
	}
	
	private void initMap(){
		this.getList(this.mActivity.getList());
	}
	
	public void getList(List<User> users)
	{
		if(this.mMap == null)
			this.mMap = this.getMap();

		for(User u : users)
		{
			Location loc = u.loc;
			this.mMap.addMarker(new MarkerOptions().position(loc.coords).title(loc.name));
		}
	}
}
