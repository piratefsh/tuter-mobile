package me.tuter.tasks;

import java.io.InputStream;
import java.util.Scanner;

import org.json.JSONException;

import me.tuter.TuterConstants;
import me.tuter.datastructures.User;
import me.tuter.http.BasicHTTPConnection;
import me.tuter.interfaces.GetUserDataTaskActivity;
import me.tuter.messages.GetUserDataMessage;
import android.content.Context;
import android.os.AsyncTask;

public class GetUserDataTask extends AsyncTask<Void, Void, GetUserDataMessage>
{
	private GetUserDataTaskActivity mActivity;
	@SuppressWarnings("unused")
	private Context mContext;
	private User mUser;
	
	public GetUserDataTask(GetUserDataTaskActivity a, Context c, User t)
	{
		this.mContext = c;
		this.mActivity = a;
		this.mUser = t;
	}
	
	@Override
	protected GetUserDataMessage doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return requestWebService(TuterConstants.DOMAIN + TuterConstants.URI_USER + this.mUser.getId() + TuterConstants.JSON_EXT);
	}
	
	protected void onPostExecute(GetUserDataMessage m) 
	{
		if(m == null || this.isCancelled())
		{
			this.mActivity.onTaskFail();
			return;
		}
		
		try {
			mActivity.onGetUserDataTaskComplete(m.extractUser());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public GetUserDataMessage requestWebService(String serviceURL)
	{
		BasicHTTPConnection httpConn = new BasicHTTPConnection(serviceURL);
		InputStream in = httpConn.openHTTPConnection();
		
		//If connection fail to open
		if(in == null)
		{
			this.cancel(true);
			return null;
		}
		
		String rawJSON = getResponseText(in);
		httpConn.closeHTTPConnection();
		return new GetUserDataMessage (rawJSON);
	}
	
	public String getResponseText(InputStream instream)
	{
		return new Scanner(instream).useDelimiter("\\A").next();
	}

}
