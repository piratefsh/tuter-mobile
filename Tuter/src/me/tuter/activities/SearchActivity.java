package me.tuter.activities;

import me.tuter.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class SearchActivity extends SherlockFragmentActivity {
	public final static String TAG = "SearchActivity";
	public final static String COURSE_FIELD 	= "course_field";
	public final static String LOCATION_FIELD 	= "location_field";
	public final static String PAID_FIELD 		= "paid_field";
	public final static String VOLUNTEER_FIELD 	= "volunteer_field";
	
	private Button mSearchButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        initViews();

    }
    
    private void initViews()
    {
    	this.mSearchButton = (Button) findViewById(R.id.search_searchButton);
    	this.mSearchButton.setOnClickListener(new OnClickListener()
    	{
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SearchActivity.this, SearchResultsActivity.class);
				i.putExtras(SearchActivity.this.getSearchParams());
				SearchActivity.this.startActivity(i);
			}
		});
    }
    
    private Bundle getSearchParams()
    {
    	Bundle extras = new Bundle();
    	
    	TextView courseField = (TextView) findViewById(R.id.course_field);
    	TextView locationField = (TextView) findViewById(R.id.location_field);
    	extras.putString(SearchActivity.COURSE_FIELD, courseField.getText().toString());
    	extras.putString(SearchActivity.LOCATION_FIELD, locationField.getText().toString());
    	
    	return extras;
    }
}
