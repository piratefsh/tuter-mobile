package me.tutor.utils;

import java.util.ArrayList;

import me.tutor.datastructures.User;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONUtils {
	public static ArrayList<User> jsonArrayToUserArray(JSONArray jsa) throws JSONException
	{
		ArrayList<User> list = new ArrayList<User>();
		for(int i = 0; i < jsa.length(); i++)
		{
			list.add(new User(jsa.getJSONObject(i)));
		}
		
		return list;
	}
}
