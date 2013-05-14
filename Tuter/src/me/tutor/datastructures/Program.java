package me.tutor.datastructures;

import org.json.JSONException;
import org.json.JSONObject;

public class Program
{
	public final String TAG 			= "Program";
	public final String NAME 			= "name";
	public final String DESC 			= "desc";
	public final String ID				= "id";
	
	private String jsonString;
	private String id;
	private String name;
	private String desc;
	private String associated_org;
	
	public Program(JSONObject js) throws JSONException
	{
		this.jsonString = js.toString();
		this.name 		= js.getString(NAME);
		this.desc 		= js.getString(DESC);
		this.id 		= js.getString(ID);
	}
	
	//Alternate constructor for taking in String JSON
	public Program(String jsonString) throws JSONException
	{
		this(new JSONObject(jsonString));
	}
	
	
	public void setAssociatedOrg(String org)
	{
		this.associated_org = org;
	}
	
	
	public String getJSONString()
	{
		return this.jsonString;
	}
	
	public String getName(){ return this.name;}
	public String getAssociatedOrg(){ return this.associated_org;}
	public String getDescription(){ return this.desc;}
	public String getID(){ return this.id;}
	
	public String toString(){return this.name + "\n"  + this.desc;}
	
}
