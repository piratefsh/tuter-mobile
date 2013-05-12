package me.tuter.activities;

import me.tuter.R;
import me.tutor.datastructures.Course;
import me.tutor.datastructures.Group;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ShowGroupActivity extends Activity {
	private Group mGroup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_group);
		
		try {
			this.mGroup = new Group(this.getIntent().getExtras().getString(ShowUserActivity.GROUP));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		populateView();
	}
	
	private void populateView()
	{
		TextView groupName = (TextView) findViewById(R.id.group_name);
		TextView groupDesc = (TextView) findViewById(R.id.group_desc);
		TextView groupCourse = (TextView) findViewById(R.id.group_course);
		
		groupName.setText(this.mGroup.getName());
		groupDesc.setText(this.mGroup.getDesc());
		
		Course c = this.mGroup.getCourse();
		groupCourse.setText(c.getCourseCode() + " - " + c.getName());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_group, menu);
		return true;
	}

}
