package me.tutor.datastructures;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

public class Location {
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public static final String LNG = "longitude";
	public static final String LAT = "latitude";
	public LatLng coords;
	public String name;
	public String address;
	
	public Location(JSONObject js) throws JSONException
	{
		this.name = js.getString(NAME);
		this.address= js.getString(ADDRESS);
		this.coords = new LatLng(js.getDouble(LAT), js.getDouble(LNG));
	}
}
