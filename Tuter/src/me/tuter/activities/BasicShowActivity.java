package me.tuter.activities;

import me.tuter.R;
import me.tuter.R.layout;
import me.tuter.R.menu;
import me.tuter.interfaces.BasicAsyncTaskActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BasicShowActivity extends Activity implements BasicAsyncTaskActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_show);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.basic_show, menu);
		return true;
	}

	@Override
	public void onTaskFail() {
		// TODO Toast failure
		
	}
	
	

}
