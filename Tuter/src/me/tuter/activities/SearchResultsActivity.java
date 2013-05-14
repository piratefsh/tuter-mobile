package me.tuter.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.tuter.R;
import me.tuter.fragments.SearchResultsListFragment;
import me.tuter.fragments.SearchResultsMapFragment;
import me.tuter.fragments.TuterTabListener;
import me.tuter.interfaces.GetSearchResultsTaskActivity;
import me.tuter.tasks.GetSearchResultsTask;
import me.tuter.datastructures.User;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;


public class SearchResultsActivity extends BasicFragmentActivity implements GetSearchResultsTaskActivity{

	private GetSearchResultsTask		mGetSearchResultsTask;
	private List<User> 					mResults;
	private SearchResultsMapFragment	mMapFragment;
	private SearchResultsListFragment	mListFragment;
	private HashMap<String, String> 	mSearchParams;
	private String						mSearchParamsString;
	private String courseParam;
	private String locationParam;
	
	public static final String USER_JSON 			= "tutorJSON";
	public static final String TAG 					= "SearchResultsActivity";
	public static final String MAP_FRAGMENT_TAG 	= "map";
	public static final String LIST_FRAGMENT_TAG 	= "list";
	
	String[] values = new String[] { "Loading" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        
        initTabs();
        this.mGetSearchResultsTask = new GetSearchResultsTask(this, this.getApplicationContext());
        this.mGetSearchResultsTask.execute();
        
        this.mSearchParams = this.getSearchParams();
        this.mSearchParamsString = this.getSearchParamsString();
    }

    private HashMap<String, String> getSearchParams()
    {
    	HashMap<String, String> params = new HashMap<String, String>();
    	Bundle extras = this.getIntent().getExtras();
    	
    	params.put(SearchActivity.COURSE_FIELD, extras.getString(SearchActivity.COURSE_FIELD));
    	params.put(SearchActivity.LOCATION_FIELD, extras.getString(SearchActivity.LOCATION_FIELD));
    	
    	return params;
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
   
    public String getSearchParamsString()
    {
        this.courseParam = this.mSearchParams.get(SearchActivity.COURSE_FIELD);
		this.locationParam = this.mSearchParams.get(SearchActivity.LOCATION_FIELD);
    	String searchParams = courseParam + ", " + locationParam;
    	return searchParams;
    }
	@Override
	public void onGetSearchResultsTaskComplete(List<User> tutors) {
		//Filter out results by search params
		List<User> filteredTutors = new ArrayList<User>();
		
		for(User t : tutors)
		{
			if(t.teaches(courseParam) 
					&& t.isIn(locationParam))
			{
				filteredTutors.add(t);
			}
		}
		this.mResults = filteredTutors;
		
		
		if (this.mListFragment != null)
		{
			this.mListFragment.getList(this.mResults, this.mSearchParamsString);
		}
		else
		{
			this.mListFragment = (SearchResultsListFragment) this.getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
			this.mListFragment.getList(this.mResults, this.mSearchParamsString);
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
