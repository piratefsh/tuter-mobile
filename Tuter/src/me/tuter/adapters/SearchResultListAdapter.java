package me.tuter.adapters;

import java.util.HashMap;
import java.util.List;

import me.tuter.R;
import me.tutor.datastructures.User;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SearchResultListAdapter extends BasicListAdapter<User> {
	public static final String TAG ="SearchResultListAdapter";
	
	private int mCustomRowLayoutId;
	
	HashMap<User, Integer> mIdMap = new HashMap<User, Integer>();

	public SearchResultListAdapter(Context context, int textViewResourceId, int customRowLayoutId, List<User> results) {
		super(context, textViewResourceId, results);
		
		this.mCustomRowLayoutId = customRowLayoutId;
	}


	/**
	 * Set up custom ListRow view. Based of deprecated TwoListItemView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout view = (LinearLayout) View.inflate(this.mContext, this.mCustomRowLayoutId, null);
		TextView text1 = (TextView) view.findViewById(R.id.text1);
		TextView text2 = (TextView) view.findViewById(R.id.text2);
		
		User curr = getItem(position);
		text1.setText(curr.getFullName());
		text2.setText(curr.getCoursesString());
		
		return view;
	}
}
