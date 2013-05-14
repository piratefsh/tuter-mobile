package me.tuter.activities;

import me.tuter.R;
import me.tuter.datastructures.Organization;
import me.tuter.datastructures.Program;
import me.tuter.tasks.GetOrgDataTask;

import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShowOrganizationActivity extends Activity
{
	private Intent mIntent;
	private Organization mOrg;
	private GetOrgDataTask mTask;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_organization);
		mIntent = getIntent();
		
		try {
			mOrg = new Organization(mIntent.getExtras().getString(ViewOrganizationsActivity.ORG_JSON));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Populate view with known data of user
		this.populateView(mOrg);
		
		this.mTask = new GetOrgDataTask(this, this.getApplicationContext(), this.mOrg);
		this.mTask.execute();
		
		//Get more user data
		//this.mTask = new GetOrgDataTask(this, this, org);
		//this.mTask.execute();
	}
	
	private void populatePrograms(Organization o)
	{
		ListView lv = (ListView) findViewById(R.id.programs_list);
		ArrayAdapter<Program> adapter = new ArrayAdapter<Program>(this, android.R.layout.simple_list_item_1, o.getPrograms());
		lv.setAdapter(adapter);
		
		lv.invalidate();
		
	}
	
	private void populateView(Organization t)
	{
		ImageView defaultPic = (ImageView) findViewById(R.id.imageView1);
		
		TextView fullNameView = (TextView) findViewById(R.id.name);
		fullNameView.setText(t.getName());
		
		TextView descriptionView = (TextView) findViewById(R.id.description);
		descriptionView.setText(t.getDescription());
		
		TextView websiteView = (TextView) findViewById(R.id.website);
		websiteView.setText(t.getWebsite());
		
		TextView emailView = (TextView) findViewById(R.id.email);
		emailView.setText(t.getEmail());
		
		TextView aboutView = (TextView) findViewById(R.id.about_organization);
		aboutView.setText("\t\t" + t.getAbout());
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_organization, menu);
		return true;
	}
	
	public void receiveProgramsString(String programs)
	{
		try {
			this.mOrg.associatePrograms(programs);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(programs);
		this.populatePrograms(this.mOrg);
	}
	
	

}
