package me.tuter.messages;

import me.tuter.datastructures.Group;

import org.json.JSONException;

public class GetGroupDataMessage {
	private String rawJSON; 
	
	public GetGroupDataMessage(String rawJSON)
	{
		this.rawJSON = rawJSON;	
	}
	
	public Group extractGroup() throws JSONException
	{
		return new Group(this.rawJSON);
	}
	
}
