package me.tuter.interfaces;

import java.util.List;

import me.tutor.datastructures.User;

public interface GetSearchResultsTaskActivity {
	void onGetSearchResultsTaskComplete(List<User> tutors);
}
