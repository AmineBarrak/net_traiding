package com.example.net_traid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;




import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Action_5_fort_baisse extends Activity {

	
	String URL = "http://192.168.0.236:9999/net/webresources/action/";
    String METHOD = "basseaction";
	
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_5_fort_baisse);
		textView = (TextView) findViewById(R.id.editView);
		new HttpAsyncTask().execute(URL+METHOD);
		
	}
	
	public static String GET(String url){
	InputStream inputStream = null;
	String result = "";
	try {
	
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
	    inputStream = httpResponse.getEntity().getContent();
	    if(inputStream != null)
	        result = convertInputStreamToString(inputStream);
	    else
	        result = "Did not work!";
	
	} catch (Exception e) {
	    Log.d("InputStream", e.getLocalizedMessage());
	}
	
	return result;
	}
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null)
		    result += line;
		
			inputStream.close();
		return result;
	
	}
	
	public boolean isConnected(){
	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) 
	        return true;
	    else
	        return false;   
	}
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
	@Override
	protected String doInBackground(String... urls) {
	
	    return GET(urls[0]);
	}
	@Override
	protected void onPostExecute(String result) {
	    Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
	    textView.setText(result);
	}
	}
}
