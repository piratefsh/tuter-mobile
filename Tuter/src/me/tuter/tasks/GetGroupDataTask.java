package me.tuter.tasks;

import java.io.InputStream;
import java.util.Scanner;

import me.tuter.TuterConstants;
import me.tuter.http.BasicHTTPConnection;
import me.tuter.interfaces.GetGroupDataTaskActivity;
import me.tuter.messages.GetGroupDataMessage;
import me.tutor.datastructures.Group;

import org.json.JSONException;

import android.os.AsyncTask;

public class GetGroupDataTask extends AsyncTask<Void, Void, GetGroupDataMessage> {
	public final String TAG = "GetGroupDataTask";
	private GetGroupDataTaskActivity mActivity;
	private Group mGroup;
	
	public GetGroupDataTask(GetGroupDataTaskActivity a, Group g)
	{
		mActivity = a;
		mGroup = g;
	}
	
	@Override
	protected GetGroupDataMessage doInBackground(Void... params) {
		
		return requestWebService(TuterConstants.DOMAIN + TuterConstants.URI_GROUP + mGroup.getId() + TuterConstants.JSON_EXT);
	}
	
	@Override
	protected void onPostExecute(GetGroupDataMessage m)
	{
		try {
			mActivity.onGetGroupDataTaskComplete(m.extractGroup());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public GetGroupDataMessage requestWebService(String serviceURL)
	{
		BasicHTTPConnection httpConn = new BasicHTTPConnection(serviceURL);
		InputStream in = httpConn.openHTTPConnection();
		String rawJSON = getResponseText(in);
		httpConn.closeHTTPConnection();
		return new GetGroupDataMessage(rawJSON);
	}
	
	private static String getResponseText(InputStream instream)
	{
		return new Scanner(instream).useDelimiter("\\A").next();
	}

}