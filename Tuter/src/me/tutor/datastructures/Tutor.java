package me.tutor.datastructures;

import org.json.JSONException;
import org.json.JSONObject;


public class Tutor {
	public final String TAG = "Tutor";
	public final String FIRSTNAME_KEY = "first_name";
	public final String LASTNAME_KEY = "last_name";
	public final String EMAIL_KEY = "email";
	
	private String firstName;
	private String lastName;
	private String email;
	
	public Tutor(JSONObject js) throws JSONException
	{
		this.firstName = js.getString(FIRSTNAME_KEY);
		this.lastName = js.getString(LASTNAME_KEY);
		this.email = js.getString(EMAIL_KEY);
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
