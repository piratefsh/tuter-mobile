package me.tuter.adapters;

import java.util.ArrayList;

import me.tuter.R;
import me.tuter.datastructures.Group;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GroupsListAdapter extends BasicListAdapter<Group> {
	
	public GroupsListAdapter(Context context, int textViewResourceId, ArrayList<Group> groupsList) {
		super(context, textViewResourceId, groupsList);
	}
	
	/**
	 * Set up custom ListRow view. Based of deprecated TwoListItemView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout view = (LinearLayout) View.inflate(this.mContext, R.layout.list_single_result, null);
		TextView text1 = (TextView) view.findViewById(R.id.text1);
		TextView text2 = (TextView) view.findViewById(R.id.text2);
		
		Group curr = getItem(position);
		text1.setText(curr.getCourse().getCourseCode() + " - " + curr.getName());
		text2.setText(curr.getDesc());
		
		return view;
	}

}
