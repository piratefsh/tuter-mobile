package me.tuter.activities;

import java.util.List;

import me.tuter.R;
import me.tuter.fragments.SearchResultsListFragment;
import me.tuter.fragments.SearchResultsMapFragment;
import me.tuter.fragments.TuterTabListener;
import me.tuter.interfaces.GetSearchResultsTaskActivity;
import me.tuter.tasks.GetSearchResultsTask;
import me.tutor.datastructures.User;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;


public class SearchResultsActivity extends BasicFragmentActivity implements GetSearchResultsTaskActivity{

	private GetSearchResultsTask		mGetSearchResultsTask;
	private List<User> 					mResults;
	private SearchResultsMapFragment	mMapFragment;
	private SearchResultsListFragment	mListFragment;
	
	public static final String USER_JSON = "tutorJSON";
	public static final String TAG = "SearchResultsActivity";
	public static final String MAP_FRAGMENT_TAG = "map";
	public static final String LIST_FRAGMENT_TAG = "list";
	
	String[] values = new String[] { "Loading" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        
//        initViews();
        initTabs();
        
        this.mGetSearchResultsTask = new GetSearchResultsTask(this, this.getApplicationContext());
        this.mGetSearchResultsTask.execute();
    }

    private void initTabs()
    {
        
        ActionBar actionbar = getSupportActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
  
        Tab tab1 = actionbar.newTab().setText("Map");
        Tab tab2 = actionbar.newTab().setText("List");
        
        tab1.setTabListener(new TuterTabListener<SearchResultsMapFragment>(this, MAP_FRAGMENT_TAG,
        		SearchResultsMapFragment.class));
  
        tab2.setTabListener(new TuterTabListener<SearchResultsListFragment>(this, LIST_FRAGMENT_TAG,
                SearchResultsListFragment.class));
  
        actionbar.addTab(tab2);
        actionbar.addTab(tab1); 
        
        this.mListFragment = (SearchResultsListFragment) this.getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
        this.mMapFragment = (SearchResultsMapFragment) this.getSupportFragmentManager().findFragmentByTag(MAP_FRAGMENT_TAG);
    }
   
	@Override
	public void onGetSearchResultsTaskComplete(List<User> tutors) {
		this.mResults = tutors;
		if (this.mListFragment != null)
		{
			this.mListFragment.getList(this.mResults);
		}
		else
		{
			this.mListFragment = (SearchResultsListFragment) this.getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
			this.mListFragment.getList(this.mResults);
		}
		
		if (this.mMapFragment != null)
		{
			this.mMapFragment.getList(this.mResults);
		}
		else
		{
			this.mMapFragment = (SearchResultsMapFragment) this.getSupportFragmentManager().findFragmentByTag(MAP_FRAGMENT_TAG);
		}
	}
	
	
	public List<User> getList()
	{
		return this.mResults;
	}
	
	public void refreshResults()
	{
		this.mGetSearchResultsTask = new GetSearchResultsTask(this, this.getApplicationContext());
		this.mGetSearchResultsTask.execute();
	}
}
