package me.tuter.tasks;

import java.io.InputStream;
import java.util.Scanner;

import me.tuter.TuterConstants;
import me.tuter.activities.ShowOrganizationActivity;
import me.tuter.http.BasicHTTPConnection;
import me.tutor.datastructures.Organization;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class GetOrgDataTask extends AsyncTask<Void, Void, String>
{
	private ShowOrganizationActivity 	mActivity;
	private Context 				mContext;
	private Organization 			mOrganization;
	
	
	public GetOrgDataTask(ShowOrganizationActivity a, Context c, Organization o)
	{
		this.mContext 		= c;
		this.mActivity 		= a;
		this.mOrganization  = o;
	}
	
	
	protected void onPostExecute(String m) 
	{
		this.mActivity.receiveProgramsString(m);
	}


	@Override
	protected String doInBackground(Void... params) {
		String url = TuterConstants.DOMAIN + TuterConstants.ORGS_LIST + "/" + mOrganization.getID() + TuterConstants.JSON_EXT;
		Log.d("GetOrgTask", url);
		return requestWebService(url);
	}
	
	
	public String requestWebService(String serviceURL)
	{
		BasicHTTPConnection httpConn = new BasicHTTPConnection(serviceURL);
		InputStream in = httpConn.openHTTPConnection();
		String rawJSON = getResponseText(in);
		httpConn.closeHTTPConnection();
		return rawJSON;
	}
	
	
	public String getResponseText(InputStream instream)
	{
		return new Scanner(instream).useDelimiter("\\A").next();
	}
}
