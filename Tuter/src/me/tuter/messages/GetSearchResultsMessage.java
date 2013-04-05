package me.tuter.messages;

import java.util.ArrayList;
import java.util.List;

import me.tutor.datastructures.Tutor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class GetSearchResultsMessage {
	public final String TAG = "GetSearchResultsMessage";

	
	JSONArray mResults;
	public GetSearchResultsMessage(String jsonString, Context c)
	{
			
		try {
			if(jsonString == null || jsonString.length() <= 0)
				throw new JSONException("Empty String");

			mResults = new JSONArray(jsonString);
		} catch (JSONException e) {
			Log.d(TAG, "Bad JSON String");
		}
	}
	
	public List<Tutor> extractTutors()
	{
		List<Tutor> tutors = new ArrayList<Tutor>();
		
		for(int i = 0; i < this.mResults.length(); i++)
		{
			JSONObject curr;
			try {
				curr = (JSONObject) this.mResults.get(i);
				tutors.add(new Tutor(curr));
			} catch (JSONException e) {
				Log.d(TAG, "Bad JSON");
			}
		}
		
		Log.d(TAG, tutors.toString());
		return tutors;
	}
}
