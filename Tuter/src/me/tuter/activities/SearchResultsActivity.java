package me.tuter.activities;

import java.util.List;

import me.tuter.R;
import me.tuter.adapters.SearchResultListAdapter;
import me.tuter.interfaces.GetSearchResultsTaskActivity;
import me.tuter.tasks.GetSearchResultsTask;
import me.tutor.datastructures.User;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SearchResultsActivity extends BasicShowActivity implements GetSearchResultsTaskActivity{
	private ListView 				mResultsListView;
	private SearchResultListAdapter mAdapter;
	private List<User> 				mResults;
	private Button					mRefreshButton;
	private GetSearchResultsTask	mGetSearchResultsTask;
	
	public static final String USER_JSON = "tutorJSON";
	public static final String TAG = "SearchResultsActivity";
	
	String[] values = new String[] { "Loading" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        
        initViews();
        
        this.mGetSearchResultsTask = new GetSearchResultsTask(this, this.getApplicationContext());
        this.mGetSearchResultsTask.execute();
    }

    private void initViews()
    {
    	//Convert array to ArrayList
    	this.mResultsListView = (ListView) findViewById(R.id.results_list);
    	this.mResultsListView.setOnItemClickListener(new OnItemClickListener()
    	{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent mIntent = new Intent(SearchResultsActivity.this, ShowUserActivity.class);
				Bundle mBundle = new Bundle();
				User t = (User) SearchResultsActivity.this.mResultsListView.getAdapter().getItem(position);
				mBundle.putString(SearchResultsActivity.USER_JSON, t.getJSONString());
				mIntent.putExtras(mBundle);
				
				SearchResultsActivity.this.startActivity(mIntent);
			}
    		
    	});
    	
    	this.mRefreshButton = (Button) findViewById(R.id.button_refresh);
    	this.mRefreshButton.setOnClickListener(new OnClickListener()
    	{

			public void onClick(View v) {
				SearchResultsActivity.this.mGetSearchResultsTask 
					= new GetSearchResultsTask(SearchResultsActivity.this, SearchResultsActivity.this.getApplicationContext());
			
				SearchResultsActivity.this.mGetSearchResultsTask.execute();
			}
    		
    	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_results, menu);
        return true;
    }

	@Override
	public void onGetSearchResultsTaskComplete(List<User> tutors) {
		this.mResults = tutors;
		this.mAdapter = new SearchResultListAdapter(this, android.R.layout.simple_list_item_1, R.layout.list_single_result, this.mResults);
    	this.mResultsListView.setAdapter(mAdapter);
	}

    
}
