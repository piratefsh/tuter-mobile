package me.tutor.datastructures;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Organization
{
	public final String TAG 			= "organizations";
	public final String ID				= "id";
	public final String NAME_KEY		= "name";
	public final String WEBSITE		 	= "website";
	public final String EMAIL_KEY 		= "email";
	public final String ABOUT			= "about";
	public final String DESC			= "desc";
	public final String PROGRAM			= "programs";
	
	private String id;
	private String name;
	private String email;
	private String about;
	private String website;
	private String description;
	private String jsonString;
	private ArrayList<Program> programs = new ArrayList<Program>();
	
	public Organization(JSONObject js) throws JSONException
	{
		this.jsonString = js.toString();
		this.id			= js.getString(ID);
		this.name	 	= js.getString(NAME_KEY);
		this.email 		= js.getString(EMAIL_KEY);
		this.website 	= js.getString(WEBSITE);
		this.about 		= js.getString(ABOUT);
		this.description= js.getString(DESC);
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
	
	public void associatePrograms(String jsonString) throws JSONException
	{
		JSONObject organizationObject = new JSONObject(jsonString);
		JSONArray programsArray = organizationObject.getJSONArray(PROGRAM);
		
		Program temp = null;
		for(int i = 0; i < programsArray.length(); ++i)
		{
			temp = new Program(programsArray.getJSONObject(i));
			programs.add(temp);
		}
	}
	
	public String getID(){ return this.id;}
	public String getName(){ return this.name;}
	public String getEmail(){ return this.email;}
	public String getWebsite(){ return this.website;}
	public String getAbout(){ return this.about;}
	public String getDescription(){ return this.description;}
	public ArrayList<Program> getPrograms(){return this.programs;}
}

