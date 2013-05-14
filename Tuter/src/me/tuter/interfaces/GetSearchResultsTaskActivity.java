package me.tuter.interfaces;

import java.util.List;

import me.tutor.datastructures.User;

public interface GetSearchResultsTaskActivity extends BasicAsyncTaskActivity{
	void onGetSearchResultsTaskComplete(List<User> tutors);
}
