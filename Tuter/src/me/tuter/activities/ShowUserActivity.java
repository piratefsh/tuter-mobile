package me.tuter.activities;

import me.tuter.R;
import me.tutor.datastructures.Tutor;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ShowUserActivity extends Activity {
	private Intent mIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_user);
		mIntent = getIntent();
		
		Tutor tutor = null;
		try {
			tutor = new Tutor(mIntent.getExtras().getString(SearchResultsActivity.TUTOR_JSON));
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		this.populateView(tutor);
	}
	
	private void populateView(Tutor t)
	{
		TextView fullNameView = (TextView) findViewById(R.id.full_name);
		fullNameView.setText(t.getFullName());
		TextView emailView = (TextView) findViewById(R.id.email);
		emailView.setText(t.getEmail());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_user, menu);
		return true;
	}

}
