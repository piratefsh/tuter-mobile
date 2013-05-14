package me.tuter;

public class TuterConstants {
	public static final String DOMAIN = "https://tuter.herokuapp.com";
//	public static final String DOMAIN = "http://72.33.236.32:3000";
	public static final String NAME		= "tuter.me";
//	public static final String DOMAIN 	= "http://192.168.1.102:3000";

	public static final String URI_SEARCH= "/search.json";
	public static final String URI_GROUP= "/groups/";
	public static final String URI_USER= "/user/";
	public static final String JSON_EXT= ".json";
	
	public static final String TASKS_URL = DOMAIN + "/api/v1/tasks.json";
    public static final String LOGIN_API_ENDPOINT_URL = DOMAIN + "/api/v1/sessions.json";
	public static final String ORGS_LIST= "/organizations.json";

	public static final int READ_TIMEOUT 	= 20000;
	public static final int CONN_TIMEOUT 	= 20000;
}
 
