package me.tutor.datastructures;

import java.util.ArrayList;

import me.tutor.utils.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class Group {
	private String 			name;
	private String 			id;
	private String 			desc;
	private Course 			course;
	private JSONObject 		json;
	private ArrayList<User> students;
	private ArrayList<User> tutors;
	
	public static final String NAME 	= "name";
	public static final String COURSE	= "course";
	public static final String DESC 	= "desc";
	public static final String STUDENTS = "students";
	public static final String TUTORS 	= "tutors";
	public static final String ID 		= "id";
	
	public Group(JSONObject js) throws JSONException
	{
		this.name 	= js.getString(NAME);
		this.desc 	= js.getString(DESC);
		this.course = new Course(js.getJSONObject(COURSE));
		this.id 	= js.getString(ID);
		
		if(js.has(TUTORS))
			this.tutors = JSONUtils.jsonArrayToUserArray(js.getJSONArray(TUTORS));
		
		if(js.has(STUDENTS))
			this.students = JSONUtils.jsonArrayToUserArray(js.getJSONArray(STUDENTS));
			
		this.json 	= js;
	}
	
	public Group(String rawJSON) throws JSONException
	{
		this(new JSONObject(rawJSON));
		
	}
	
	public String getName() 	{ return this.name;}
	public String getDesc() 	{ return this.desc;}
	public Course getCourse() 	{ return this.course;}
	public String getId()		{ return this.id;}
	public String toString()	{ return this.json.toString();}
	public ArrayList<User> getStudents(){ return this.students;}
	public ArrayList<User> getTutors()	{ return this.tutors;}
}
