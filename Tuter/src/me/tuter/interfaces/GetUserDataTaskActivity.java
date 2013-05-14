package me.tuter.interfaces;

import me.tuter.datastructures.User;

public interface GetUserDataTaskActivity extends BasicAsyncTaskActivity{
	void onGetUserDataTaskComplete(User u);
}
