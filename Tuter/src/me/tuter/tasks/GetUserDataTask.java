package me.tuter.tasks;

import java.io.InputStream;
import java.util.Scanner;

import me.tuter.http.BasicHTTPConnection;
import me.tuter.interfaces.GetUserDataTaskActivity;
import me.tuter.messages.GetUserDataMessage;
import android.content.Context;
import android.os.AsyncTask;

public class GetUserDataTask extends AsyncTask<Void, Void, GetUserDataMessage> {
	private GetUserDataTaskActivity mActivity;
	private Context mContext;
	
	public GetUserDataTask(GetUserDataTaskActivity a, Context c)
	{
		this.mContext = c;
		this.mActivity = a;
	}
	
	@Override
	protected GetUserDataMessage doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public GetUserDataMessage requestWebService(String serviceURL)
	{
		BasicHTTPConnection httpConn = new BasicHTTPConnection(serviceURL);
		InputStream in = httpConn.openHTTPConnection();
		String rawJSON = getResponseText(in);
		httpConn.closeHTTPConnection();
		return new GetUserDataMessage (rawJSON);
	}
	
	public String getResponseText(InputStream instream)
	{
		return new Scanner(instream).useDelimiter("\\A").next();
	}

}
