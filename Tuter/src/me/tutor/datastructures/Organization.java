package me.tutor.datastructures;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Organization {
	public final String TAG 			= "organizations";
	public final String NAME_KEY		= "name";
	public final String WEBSITE		 	= "website";
	public final String EMAIL_KEY 		= "email";
	public final String ABOUT			= "about";
	
	private String name;
	private String email;
	private String about;
	private String website;
	private String jsonString;
	
	public Organization(JSONObject js) throws JSONException
	{
		this.jsonString = js.toString();
		this.name	 	= js.getString(NAME_KEY);
		this.email 		= js.getString(EMAIL_KEY);
		this.website 	= js.getString(WEBSITE);
		this.about 		= js.getString(ABOUT);
	}
	
	//Alternate constructor for taking in String JSON
	public Organization(String jsonString) throws JSONException
	{
		this(new JSONObject(jsonString));
	}
	
	public String getJSONString()
	{
		return this.jsonString;
	}
	
	public String getName(){ return this.name;}
	public String getEmail(){ return this.email;}
	public String getWebsite(){ return this.website;}
	public String getAbout(){ return this.about;}
}

