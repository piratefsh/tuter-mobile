package me.tuter.activities;

import me.tuter.R;
import me.tuter.interfaces.BasicAsyncTaskActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

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
		//display toast
		Toast t = Toast.makeText(this.getApplicationContext(), getResources().getString(R.string.network_err_msg), Toast.LENGTH_LONG);
		t.show();
	}
}
