package me.tuter.messages;

import me.tuter.datastructures.User;

import org.json.JSONException;

public class GetUserDataMessage {
	public static final String TAG = "GetUserDataMessage";
	
	private String rawJSON; 
	
	public GetUserDataMessage(String rawJSON)
	{
		this.rawJSON = rawJSON;	
	}
	
	public User extractUser() throws JSONException
	{
		return new User(this.rawJSON);
	}
}
