package me.tuter.activities;

import java.util.Locale;

import me.tuter.R;
import me.tuter.adapters.GroupsListAdapter;
import me.tuter.datastructures.User;
import me.tuter.interfaces.GetUserDataTaskActivity;
import me.tuter.tasks.GetUserDataTask;

import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ShowUserActivity extends BasicShowActivity implements GetUserDataTaskActivity {
	public static final String TAG = "ShowUserActivity";
	public static final String GROUP = "group";
	private Intent 				mIntent;
	private GetUserDataTask 	mTask;
	private ListView 			mGroupsListView; //list of user's groups
	private GroupsListAdapter	mGroupsListViewAdapter; //list of user's groups
	private User				mUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_user);
		this.mIntent = getIntent();
		this.mGroupsListView = (ListView) findViewById(R.id.groups_list);
		
		//Set on click listener
		this.mGroupsListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent i = new Intent(ShowUserActivity.this, ShowGroupActivity.class);
				Bundle extras = new Bundle();
				
				extras.putString(ShowUserActivity.GROUP, ShowUserActivity.this.mGroupsListViewAdapter.getItem(position).toString());
				i.putExtras(extras);
				
				ShowUserActivity.this.startActivity(i);
			}
		});
		
		try {
			this.mUser = new User(mIntent.getExtras().getString(SearchResultsActivity.USER_JSON));
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		//Populate view with known data of user
		this.populateInitialView(this.mUser);
		
		//Get more user data
		this.mTask = new GetUserDataTask(this, this, this.mUser);
		this.mTask.execute();
	}
	
	private void populateInitialView(User t)
	{
		TextView fullNameView = (TextView) findViewById(R.id.full_name);
		fullNameView.setText(t.getFullName());
		TextView emailView = (TextView) findViewById(R.id.email);
		emailView.setText(t.getEmail());
		
		emailView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				ShowUserActivity.this.setEmailIntent(ShowUserActivity.this);
			}
		});
	}
	
	public User getUser()
	{
		return this.mUser;
	}
	
	private void populateMoreView(User u)
	{
		TextView details  = (TextView) findViewById(R.id.tutor_details);
		String detailsText = getResources().getString(R.string.tutor_details);
		detailsText = detailsText.replace("#{AGE}", u.getAge()).replace("#{YEAR}", u.getYear()).replace("#{RATE}", u.getRates());
		details.setText(detailsText);
		
		TextView desc = (TextView) findViewById(R.id.description);
		desc.setText(u.getDesc());
		TextView address = (TextView) findViewById(R.id.address);
		address.setText(u.loc.address);
		address.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				User u = ShowUserActivity.this.getUser();
				if(u.loc.coords != null)
				{
					String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s&z=10", u.loc.address);
					Intent i  = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					ShowUserActivity.this.startActivity(i);
				}
			}
			
		});
	}
	
	//Code from http://stackoverflow.com/questions/5333119/android-opening-the-email-application
	public void setEmailIntent(Context context)
	{
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{this.mUser.getEmail()});
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "I'm Interested In Joining Your Tutoring Group!");

		context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_user, menu);
		return true;
	}

	@Override
	public void onGetUserDataTaskComplete(User u) {
		
		this.populateMoreView(u);
		this.mGroupsListViewAdapter = new GroupsListAdapter(this, android.R.layout.simple_list_item_1, u.getGroupsList());
		this.mGroupsListView.setAdapter(this.mGroupsListViewAdapter);
		this.mGroupsListView.invalidate();
		
	}

}
