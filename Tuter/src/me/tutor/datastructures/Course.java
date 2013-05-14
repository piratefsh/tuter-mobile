package me.tutor.datastructures;

import org.json.JSONException;
import org.json.JSONObject;

public class Course {
	private String name;
	private String courseCode;
	
	public static final String NAME = "name";
	public static final String COURSE_NAME = "course_name";
	public static final String CODE = "course_code";
	
	public Course(JSONObject js) throws JSONException
	{
		this.name = js.has(NAME)? js.getString(NAME) : js.getString(COURSE_NAME);
		this.courseCode= js.getString(CODE);
	}
	
	public String getName(){return name;}
	public String getCourseCode(){return courseCode;}
}
