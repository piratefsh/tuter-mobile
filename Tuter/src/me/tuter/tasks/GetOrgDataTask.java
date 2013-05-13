package me.tuter.tasks;

import org.json.JSONException;

import me.tuter.interfaces.GetOrgDataTaskActivity;
import me.tuter.interfaces.GetUserDataTaskActivity;
import me.tuter.messages.GetOrgDataMessage;
import me.tuter.messages.GetUserDataMessage;
import me.tutor.datastructures.Organization;
import me.tutor.datastructures.User;
import android.content.Context;
import android.os.AsyncTask;

public class GetOrgDataTask
{
	private GetOrgDataTaskActivity 	mActivity;
	private Context 				mContext;
	private Organization 			mOrganization;
	
	
	public GetOrgDataTask(GetOrgDataTaskActivity a, Context c, Organization t)
	{
		this.mContext 		= c;
		this.mActivity 		= a;
		this.mOrganization 	= t;
	}
	
	
	protected void onPostExecute(GetOrgDataMessage m) 
	{
		try {
			mActivity.onGetOrgDataTaskComplete(m.extractOrg());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
