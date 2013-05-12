package me.tuter.messages;

import java.util.ArrayList;
import java.util.List;

import me.tutor.datastructures.Organization;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.content.Context;
import android.util.Log;

public class GetOrganizationsMessage
{
	public static final String TAG = "GetOrganizationsMessage";
	
	JSONArray mResults;
	
	public GetOrganizationsMessage(String jsonString, Context c)
	{
			
		try {
			if(jsonString == null || jsonString.length() <= 0)
				throw new JSONException("Empty String");

			mResults = new JSONArray(jsonString);
		} catch (JSONException e) {
			Log.d(TAG, "Bad JSON String");
		}
	}
	
	
	public List<Organization> extractOrganizations()
	{
		List<Organization> organizations = new ArrayList<Organization>();
		
		for(int i = 0; i < this.mResults.length(); i++)
		{
			JSONObject curr;
			try {
				curr = (JSONObject) this.mResults.get(i);
				organizations.add(new Organization(curr));
			} catch (JSONException e) {
				Log.d(TAG, "Bad JSON");
			}
		}
		
		return organizations;
	}
}
