package me.tuter.activities;

import java.util.List;

import me.tuter.R;
import me.tuter.R.layout;
import me.tuter.R.menu;
import me.tuter.adapters.OrganizationsListAdapter;
import me.tuter.interfaces.GetOrganizationsTaskActivity;
import me.tuter.tasks.GetOrganizationsTask;
import me.tutor.datastructures.Organization;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ViewOrganizationsActivity extends Activity implements GetOrganizationsTaskActivity
{
	private ListView 					mOrganizationsListView;
	private OrganizationsListAdapter	mAdapter;
	private List<Organization>			mOrganizations;
	private GetOrganizationsTask		mGetOrganizationsTask;
	
	public static final String ORG_JSON = "organizationJSON";
	public static final String TAG = "ViewOrganizationsActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_organizations);
		
		initViews();
		
        this.mGetOrganizationsTask = new GetOrganizationsTask(this, this);
        this.mGetOrganizationsTask.execute();
	}

	
    private void initViews()
    {
    	//Convert array to ArrayList
    	this.mOrganizationsListView = (ListView) findViewById(R.id.organization_name);
    	this.mOrganizationsListView.setOnItemClickListener(new OnItemClickListener()
    	{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent mIntent = new Intent(ViewOrganizationsActivity.this, ShowOrganizationActivity.class);
				Bundle mBundle = new Bundle();
				Organization t = (Organization) ViewOrganizationsActivity.this.mOrganizationsListView.getAdapter().getItem(position);
				mBundle.putString(ViewOrganizationsActivity.ORG_JSON, t.getJSONString());
				mIntent.putExtras(mBundle);
				
				ViewOrganizationsActivity.this.startActivity(mIntent);
			}
    		
    	});
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_organizations, menu);
		return true;
	}


	@Override
	public void onGetOrganizationsTaskComplete(List<Organization> organizations) {
		this.mOrganizations = organizations;
		this.mAdapter = new OrganizationsListAdapter(this, android.R.layout.simple_list_item_1, R.layout.list_single_result, this.mOrganizations);
    	this.mOrganizationsListView.setAdapter(mAdapter);
	}

}
