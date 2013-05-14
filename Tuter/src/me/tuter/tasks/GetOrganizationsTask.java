package me.tuter.tasks;

import java.io.InputStream;
import java.util.Scanner;

import me.tuter.TuterConstants;
import me.tuter.http.BasicHTTPConnection;
import me.tuter.interfaces.GetOrganizationsTaskActivity;
import me.tuter.messages.GetOrganizationsMessage;
import android.content.Context;
import android.os.AsyncTask;

public class GetOrganizationsTask extends AsyncTask<Void, Void, GetOrganizationsMessage>
{
	private GetOrganizationsTaskActivity 	mActivity;
	private Context 						mContext;
	
	
	public GetOrganizationsTask(GetOrganizationsTaskActivity a, Context c)
	{
		this.mContext = c;
		this.mActivity = a;
	}
	
	
	@Override
	protected GetOrganizationsMessage doInBackground(Void... params)
	{
		return requestWebService(TuterConstants.DOMAIN + TuterConstants.ORGS_LIST);
	}
	
	

	protected void onPostExecute(GetOrganizationsMessage m)
	{
		mActivity.onGetOrganizationsTaskComplete(m.extractOrganizations());
	}
	
	
	public GetOrganizationsMessage requestWebService(String serviceURL)
	{
		BasicHTTPConnection httpConn = new BasicHTTPConnection(serviceURL);
		InputStream in = httpConn.openHTTPConnection();
		String rawJSON = getResponseText(in);
		httpConn.closeHTTPConnection();
		return new GetOrganizationsMessage(rawJSON, mContext);
	}

	
	private static String getResponseText(InputStream instream)
	{
		return new Scanner(instream).useDelimiter("\\A").next();
	}
}
