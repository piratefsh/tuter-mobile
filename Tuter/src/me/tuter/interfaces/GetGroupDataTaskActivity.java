package me.tuter.interfaces;

import me.tuter.datastructures.Group;

public interface GetGroupDataTaskActivity extends BasicAsyncTaskActivity{
	void onGetGroupDataTaskComplete(Group g);
}
