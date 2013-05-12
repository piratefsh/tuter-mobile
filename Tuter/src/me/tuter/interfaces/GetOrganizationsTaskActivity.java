package me.tuter.interfaces;

import java.util.List;

import me.tutor.datastructures.Organization;

public interface GetOrganizationsTaskActivity {
	void onGetOrganizationsTaskComplete(List<Organization> organizations);
}
