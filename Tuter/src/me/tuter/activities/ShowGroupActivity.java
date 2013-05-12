package me.tuter.activities;

import me.tuter.R;
import me.tuter.R.layout;
import me.tuter.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShowGroupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_group);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_group, menu);
		return true;
	}

}
