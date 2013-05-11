package me.tuter.activities;

import me.tuter.R;
import me.tuter.interfaces.GetUserDataTaskActivity;
import me.tuter.tasks.GetUserDataTask;
import me.tutor.datastructures.User;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ShowUserActivity extends Activity implements GetUserDataTaskActivity {
	public static final String TAG = "ShowUserActivity";
	
	private Intent mIntent;
	private GetUserDataTask mTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_user);
		mIntent = getIntent();
		
		User tutor = null;
		try {
			tutor = new User(mIntent.getExtras().getString(SearchResultsActivity.TUTOR_JSON));
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
		Log.d(TAG, "" + u.getAge() + " " + u.getYear());
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
	}

}
