package me.tutor.datastructures;

import org.json.JSONException;
import org.json.JSONObject;

public class Group {
	private String name;
	private String desc;
	private Course course;
	
	public static final String NAME 	= "name";
	public static final String COURSE	= "course";
	public static final String DESC 	= "desc";
	
	public Group(JSONObject js) throws JSONException
	{
		this.name 	= js.getString(NAME);
		this.desc 	= js.getString(DESC);
		this.course = new Course(js.getJSONObject(COURSE));
	}
	
	public String getName() { return this.name;}
	public String getDesc() { return this.desc;}
	public Course getCourse() { return this.course;}
}
