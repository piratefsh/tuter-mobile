package me.tuter.adapters;

import java.util.HashMap;
import java.util.List;

import me.tuter.R;
import me.tuter.datastructures.Organization;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrganizationsListAdapter extends ArrayAdapter<Organization>
{
	public static final String TAG ="OrganizationsListAdapter";
	
	private Context mContext;
	private int mCustomRowLayoutId;
	private List<Organization> mOrganizations;
	
	
	HashMap<Organization, Integer> mIdMap = new HashMap<Organization, Integer>();

	
	public OrganizationsListAdapter(Context context, int textViewResourceId, int customRowLayoutId, List<Organization> organizations) {
		super(context, textViewResourceId, organizations);
		
		this.mContext = context;
		this.mCustomRowLayoutId = customRowLayoutId;
		this.mOrganizations = organizations;
		
		for (int i = 0; i < organizations.size(); ++i) 
		{
			mIdMap.put(organizations.get(i), i);
		}
	}

	
	@Override
	public long getItemId(int position)
	{
		Organization item = getItem(position);
		return mIdMap.get(item);
	}
	
	
	@Override
	public boolean hasStableIds()
	{
		return true;
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
		
		Organization curr = getItem(position);
		
		text1.setTextColor(Color.WHITE);
		text2.setTextColor(Color.WHITE);
		
		text1.setTextSize(20);
		
		text1.setText(curr.getName());
		text2.setText(curr.getDescription());
		
		return view;
	}
}
