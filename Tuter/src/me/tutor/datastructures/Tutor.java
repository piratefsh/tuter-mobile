package me.tutor.datastructures;

import org.json.JSONException;
import org.json.JSONObject;


public class Tutor {
	public final String TAG = "Tutor";
	public final String FIRSTNAME_KEY = "first_name";
	public final String LASTNAME_KEY = "last_name";
	public final String AGE = "age";
	public final String YEAR_IN_SCHOOL = "year";
	public final String PHOTO = "photo";
	public final String RATE = "rate";
	public final String EMAIL_KEY = "email";
	
	private String firstName;
	private String lastName;
	private String email;
	private String jsonString;
	
	public Tutor(JSONObject js) throws JSONException
	{
		this.jsonString = js.toString();
		this.firstName = js.getString(FIRSTNAME_KEY);
		this.lastName = js.getString(LASTNAME_KEY);
		this.email = js.getString(EMAIL_KEY);
	}
	public Tutor(String jsonString) throws JSONException
	{
		
		this.jsonString = jsonString;
		
		JSONObject js = new JSONObject(jsonString);
		this.firstName = js.getString(FIRSTNAME_KEY);
		this.lastName = js.getString(LASTNAME_KEY);
		this.email = js.getString(EMAIL_KEY);
	}
	
	public String getJSONString()
	{
		return this.jsonString;
	}
	
	public String getFullName()
	{
		return this.firstName + " " + this.lastName;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String toString()
	{
		return this.getFullName();
	}
}
