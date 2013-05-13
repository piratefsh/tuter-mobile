package me.tutor.datastructures;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class User {
	public final String TAG 			= "Tutor";
	public final String FIRSTNAME_KEY 	= "first_name";
	public final String LASTNAME_KEY 	= "last_name";
	public final String AGE 			= "age";
	public final String YEAR_IN_SCHOOL 	= "year";
	public final String PHOTO 			= "photo";
	public final String RATE 			= "rate";
	public final String EMAIL_KEY 		= "email";
	public final String ID 				= "id";
	public final String YEAR 			= "year";
	public final String GROUPS 			= "groups";
	public final String LOCATION 		= "location";
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String year;
	private String age;
	private String rate;
	private String jsonString;
	private ArrayList<Group> groups;
	
	public Location loc;
	
	public User(JSONObject js) throws JSONException
	{
		this.jsonString = js.toString();
		this.firstName 	= js.getString(FIRSTNAME_KEY);
		this.lastName 	= js.getString(LASTNAME_KEY);
		this.email 		= js.getString(EMAIL_KEY);
		this.rate 		= js.getString(RATE);
		this.id 		= js.getString(ID);
		this.year 		= js.getString(YEAR);
		this.age 		= js.getString(AGE);
		
		this.groups = new ArrayList<Group>();
		
		//Only get groups if they exist for user
		if (js.has(GROUPS))
		{
			JSONArray groupsJSON = js.getJSONArray(GROUPS);
			
			for (int i = 0; i < groupsJSON.length(); i++) 
			{
				Group g = new Group(groupsJSON.getJSONObject(i));
				groups.add(g);
			}
		}
		
		// Get location of user
		if (js.has(LOCATION))
		{
			this.loc = new Location(js.getJSONObject(LOCATION));
		}
	}
	
	//Alternate constructor for taking in String JSON
	public User(String jsonString) throws JSONException
	{
		
		this(new JSONObject(jsonString));
	}
	
	public String getJSONString()
	{
		return this.jsonString;
	}
	
	public String getFullName(){ return this.firstName + " " + this.lastName;}
	public String getEmail(){ return this.email;}
	public String toString(){ return this.getFullName();}
	public String getAge(){ return this.age;}
	public String getYear(){ return this.year;}
	public String getRates(){ return this.rate;}
	public String getId(){ return this.id;}
	public ArrayList<Group> getGroupsList() {return this.groups;}
}
