package me.tuter.activities;

import org.json.JSONException;

import me.tuter.R;
import me.tutor.datastructures.Organization;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ShowOrganizationActivity extends Activity
{
	private Intent mIntent;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_organization);
		mIntent = getIntent();
		
		Organization org = null;
		try {
			org = new Organization(mIntent.getExtras().getString(ViewOrganizationsActivity.ORG_JSON));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Populate view with known data of user
		this.populateView(org);
		
		//Get more user data
		//this.mTask = new GetOrgDataTask(this, this, org);
		//this.mTask.execute();
	}
	
	private void populateView(Organization t)
	{
		TextView fullNameView = (TextView) findViewById(R.id.name);
		fullNameView.setText(t.getName());
		TextView emailView = (TextView) findViewById(R.id.email);
		emailView.setText(t.getEmail());
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_organization, menu);
		return true;
	}
}
