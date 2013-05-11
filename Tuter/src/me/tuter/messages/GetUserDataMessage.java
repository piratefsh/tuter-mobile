package me.tuter.messages;

import me.tutor.datastructures.User;

import org.json.JSONException;
import org.json.JSONObject;

public class GetUserDataMessage {
	public static final String TAG = "GetUserDataMessage";
	
	private String rawJSON; 
	private JSONObject json; 
	
	public GetUserDataMessage(String rawJSON)
	{
		this.rawJSON = rawJSON;	
	}
	
	public User extractUser() throws JSONException
	{
		return new User(this.rawJSON);
	}
	

}
