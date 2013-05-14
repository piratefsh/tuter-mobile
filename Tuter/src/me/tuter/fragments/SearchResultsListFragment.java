package me.tuter.fragments;

import java.util.List;

import me.tuter.R;
import me.tuter.activities.SearchResultsActivity;
import me.tuter.activities.ShowUserActivity;
import me.tuter.adapters.SearchResultListAdapter;
import me.tutor.datastructures.User;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class SearchResultsListFragment extends SherlockFragment {
	
	private ListView 				mResultsListView;
	private SearchResultListAdapter mAdapter;
	private Button					mRefreshButton;
	private SearchResultsActivity	mActivity;
	private View					mView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v =  inflater.inflate(R.layout.fragment_search_results_list, container, false);
		this.mView = v;
		this.mActivity = (SearchResultsActivity) this.getActivity();
		this.initViews();
		
		this.getList(this.mActivity.getList(), this.mActivity.getSearchParamsString());
		
		return v;
	}
	
	private void initViews()
    {
    	//Convert array to ArrayList
    	this.mResultsListView = (ListView) this.mView.findViewById(R.id.results_list);
    	this.mResultsListView.setOnItemClickListener(new OnItemClickListener()
    	{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent mIntent = new Intent(SearchResultsListFragment.this.mActivity, ShowUserActivity.class);
				Bundle mBundle = new Bundle();
				User t = (User) SearchResultsListFragment.this.mResultsListView.getAdapter().getItem(position);
				mBundle.putString(SearchResultsActivity.USER_JSON, t.getJSONString());
				mIntent.putExtras(mBundle);
				
				SearchResultsListFragment.this.startActivity(mIntent);
			}
    		
    	});
    	
    	this.mRefreshButton = (Button) this.mView.findViewById(R.id.button_refresh);
    	this.mRefreshButton.setOnClickListener(new OnClickListener()
    	{
			public void onClick(View v) {
				SearchResultsListFragment.this.mActivity.refreshResults();
			}
    		
    	});
    }
	 	
 	public void getList(List<User> users, String searchParams)
 	{
 		
 		TextView searchParamsTV = (TextView) this.mActivity.findViewById(R.id.search_params); 
 		if(searchParamsTV != null)
 			searchParamsTV.setText("" + getResources().getString(R.string.search_params) + searchParams);
 		
 		
 		if(users == null) 
 			return;
 		
		this.mAdapter = new SearchResultListAdapter(this.mActivity, android.R.layout.simple_list_item_1, R.layout.list_single_result, users);
    	this.mResultsListView.setAdapter(mAdapter);
    	this.mResultsListView.invalidate();
 	}

}
