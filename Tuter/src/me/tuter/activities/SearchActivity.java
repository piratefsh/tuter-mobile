package me.tuter.activities;

import me.tuter.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class SearchActivity extends SherlockFragmentActivity {
	public final static String TAG = "SearchActivity";
	
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
				SearchActivity.this.startActivity(i);
			}
		});
    }
}
