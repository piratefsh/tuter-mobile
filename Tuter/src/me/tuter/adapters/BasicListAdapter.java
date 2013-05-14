package me.tuter.adapters;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class BasicListAdapter<T> extends ArrayAdapter<T> {
	
	protected HashMap<T, Integer> mIdMap = new HashMap<T, Integer>();
	protected Context mContext;
	protected List<T> mList;

	public BasicListAdapter(Context context, int textViewResourceId, List<T> results) {
		super(context, textViewResourceId, results);
		this.mContext = context;
		this.mList = results;
		
		for (int i = 0; i < results.size(); ++i) 
		{
			mIdMap.put(results.get(i), i);
			//Log.d(TAG, "Put: " + results.get(i));
		}
	}
	
	@Override
	public long getItemId(int position)
	{
		T item = getItem(position);
		return mIdMap.get(item);
	}

	@Override
	public boolean hasStableIds()
	{
		return true;
	}


}
