package me.tuter.activities;

import java.util.List;

import me.tuter.R;
import me.tutor.datastructures.User;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class BasicFragmentActivity extends SherlockFragmentActivity
{
	public void onTaskFail() {
		//display toast
		Toast t = Toast.makeText(this.getApplicationContext(), getResources().getString(R.string.network_err_msg), Toast.LENGTH_LONG);
		t.show();
	}
	
	public List<User> getList()
	{
		return null;
	}
}
