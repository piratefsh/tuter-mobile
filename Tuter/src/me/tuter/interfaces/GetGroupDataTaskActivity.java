package me.tuter.interfaces;

import me.tutor.datastructures.Group;

public interface GetGroupDataTaskActivity extends BasicAsyncTaskActivity{
	void onGetGroupDataTaskComplete(Group g);
}
