package me.tuter.activities;

import me.tuter.R;
import me.tuter.datastructures.Course;
import me.tuter.datastructures.Group;
import me.tuter.datastructures.User;
import me.tuter.interfaces.GetGroupDataTaskActivity;
import me.tuter.tasks.GetGroupDataTask;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowGroupActivity extends BasicShowActivity implements GetGroupDataTaskActivity {
	private Group mGroup;
	private ListView mStudentsList;
	private ListView mTutorsList;
	private GetGroupDataTask mTask;
	
	public final static String TAG = "ShowGroupActivity"; 
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
		
		this.mTutorsList = (ListView) findViewById(R.id.tutors_list);
		this.mStudentsList = (ListView) findViewById(R.id.students_list);
		
		this.mTask = new GetGroupDataTask(this, this.mGroup);
		this.mTask.execute();
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
	
	private void populateTutorStudents(Group g)
	{
		//TODO: Implement
		ArrayAdapter<User> studentsListAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, g.getStudents());
		ArrayAdapter<User> tutorsListAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, g.getTutors());
		this.mTutorsList.setAdapter(tutorsListAdapter);
		this.mStudentsList.setAdapter(studentsListAdapter);
		
		setUserListsOnItemClickListener(this.mStudentsList);
		setUserListsOnItemClickListener(this.mTutorsList);
		
	}
	
	//Sets on item click to start new ShowUserActivity
	private void setUserListsOnItemClickListener(ListView lv)
	{
		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> adapterView, View arg1, int position,
					long arg3) {
				Intent i = new Intent(ShowGroupActivity.this, ShowUserActivity.class);
				Bundle extras = new Bundle();
				
				User u = (User) adapterView.getAdapter().getItem(position);
				extras.putString(SearchResultsActivity.USER_JSON, u.getJSONString());
				i.putExtras(extras);
				
				ShowGroupActivity.this.startActivity(i);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_group, menu);
		return true;
	}

	@Override
	public void onGetGroupDataTaskComplete(Group g) 
	{
		this.populateTutorStudents(g);
	}

}
