package me.tuter.messages;

import me.tutor.datastructures.Organization;

import org.json.JSONException;
import org.json.JSONObject;

public class GetOrgDataMessage {
	public static final String TAG = "GetOrgDataMessage";
	
	private String rawJSON; 
	private JSONObject json; 
	
	
	public GetOrgDataMessage(String rawJSON)
	{
		this.rawJSON = rawJSON;	
	}
	
	
	public Organization extractOrg() throws JSONException
	{
		return new Organization(this.rawJSON);
	}
}

