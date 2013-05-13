package me.tutor.datastructures;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class User {
	public final String TAG 			= "Tutor";
	public final String FIRSTNAME_KEY 	= "first_name";
	public final String LASTNAME_KEY 	= "last_name";
	public final String AGE 			= "age";
	public final String DESC		 	= "desc";
	public final String YEAR_IN_SCHOOL 	= "year";
	public final String PHOTO 			= "photo";
	public final String RATE 			= "rate";
	public final String EMAIL_KEY 		= "email";
	public final String ID 				= "id";
	public final String YEAR 			= "year";
	public final String GROUPS 			= "groups";
	public final String LOCATION 		= "location";
	public final String COURSES 		= "courses";
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String year;
	private String age;
	private String rate;
	private String desc;
	private String jsonString;
	private ArrayList<Group> groups;
	private ArrayList<Course> courses;
	
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
		this.desc 		= js.getString(DESC);
		this.groups = new ArrayList<Group>();
		this.courses = new ArrayList<Course>();
		
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
		
		if(js.has(COURSES))
		{
			JSONArray jsonCourses = js.getJSONArray(COURSES);
			
			for (int i = 0; i < jsonCourses.length(); i++) 
			{
				JSONObject j = jsonCourses.getJSONObject(i);
				Log.d(TAG, j.toString());
				Course c = new Course(j);
				courses.add(c);
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
	public String getAge(){ return this.nullHandler(this.age);}
	public String getYear(){ return this.nullHandler(this.year);}
	public String getRates(){ return this.nullHandler(this.rate);}
	public String getId(){ return this.id;}
	public ArrayList<Group> getGroupsList() {return this.groups;}
	
	public String getDesc() { return this.nullHandler(this.desc);}
	
	private String nullHandler(String s)
	{
		String desc = (s == null || s.equals("null"))? "n/a" : s;
		return desc;
	}
	
	public String getCoursesString()
	{
		String courses = "COURSES: ";
		boolean first = true;
		
		for(Course c : this.courses)
		{
			courses +=  first? c.getCourseCode() : ", " + c.getCourseCode();
			first = false;
		}
		
		return courses;
	}
}
