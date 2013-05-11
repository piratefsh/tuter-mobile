package me.tuter.messages;

import org.json.JSONObject;

public class GetUserDataMessage {
	private String rawJSON; 
	private JSONObject json; 
	
	public GetUserDataMessage(String rawJSON)
	{
		this.rawJSON = rawJSON;		
	}

}
