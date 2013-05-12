package me.tuter.activities;

import me.tuter.R;
import me.tuter.adapters.GroupsListAdapter;
import me.tuter.interfaces.GetUserDataTaskActivity;
import me.tuter.tasks.GetUserDataTask;
import me.tutor.datastructures.User;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ShowUserActivity extends Activity implements GetUserDataTaskActivity {
	public static final String TAG = "ShowUserActivity";
	public static final String GROUP = "group";
	private Intent 			mIntent;
	private GetUserDataTask mTask;
	private ListView 		mGroupsListView; //list of user's groups
	private GroupsListAdapter	mGroupsListViewAdapter; //list of user's groups
	
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
		
		User tutor = null;
		try {
			tutor = new User(mIntent.getExtras().getString(SearchResultsActivity.USER_JSON));
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		//Populate view with known data of user
		this.populateInitialView(tutor);
		
		//Get more user data
		this.mTask = new GetUserDataTask(this, this, tutor);
		this.mTask.execute();
	}
	
	private void populateInitialView(User t)
	{
		TextView fullNameView = (TextView) findViewById(R.id.full_name);
		fullNameView.setText(t.getFullName());
		TextView emailView = (TextView) findViewById(R.id.email);
		emailView.setText(t.getEmail());
	}
	
	private void populateMoreView(User u)
	{
		TextView details  = (TextView) findViewById(R.id.tutor_details);
		String detailsText = getResources().getString(R.string.tutor_details);
		detailsText = detailsText.replace("#{AGE}", u.getAge()).replace("#{YEAR}", u.getYear()).replace("#{RATE}", u.getRates());
		details.setText(detailsText);
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
