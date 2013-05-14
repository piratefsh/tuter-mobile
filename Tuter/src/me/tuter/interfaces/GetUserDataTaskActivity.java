package me.tuter.interfaces;

import me.tutor.datastructures.User;

public interface GetUserDataTaskActivity extends BasicAsyncTaskActivity{
	void onGetUserDataTaskComplete(User u);
}
