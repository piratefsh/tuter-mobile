package me.tuter.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class BasicHTTPPost {
	private DefaultHttpClient client;
	private HttpPost poster;
	
	public BasicHTTPPost(String uri)
	{
		client = new DefaultHttpClient();
		poster = new HttpPost(uri);
	}
	
	/**Sends HTTP post request, waits for response and returns 
	 * InputStream to the content header
	 * 
	 * @param params Map of parameters to send in post request
	 * @return in InputStream to response content
	 * @throws JSONException
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InputStream postRequest(Map params) throws JSONException, IllegalStateException, IOException
	{
		//make JSON out of parameters
		JSONObject jsonParams = new JSONObject();

		Iterator iter = params.entrySet().iterator();
		//iterate through first level params
		while(iter.hasNext())
		{
			Map.Entry pair = (Map.Entry) iter.next();
			jsonParams.put(pair.getKey().toString(), pair.getValue().toString());
		}
		
		StringEntity s = null;
		try {
			s = new StringEntity(jsonParams.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		poster.setEntity(s);
		poster.setHeader("Accept", "application/json");
		poster.setHeader("Content-type", "application/json");
		
		ResponseHandler responseHandler = new BasicResponseHandler();
		HttpResponse response = null;
		try {
			response = client.execute(poster, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream in= response.getEntity().getContent();
		
		return in;
	}
	
}
