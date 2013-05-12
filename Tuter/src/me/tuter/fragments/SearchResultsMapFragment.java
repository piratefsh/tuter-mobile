package me.tuter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

public class SearchResultsMapFragment extends SupportMapFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = super.onCreateView(inflater,  container, savedInstanceState);
		
		initMap();
		return v;
	}
	
	private void initMap(){
		//TODO: initialize map with user position
	}
}
