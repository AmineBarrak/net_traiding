package com.example.net_traid;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



class Action
{
	public String nomEnt;
	public String id;
	

}
public class Client_plus_actif_achat extends Activity {
	
	private HandleXML obj;
	String URL = "http://192.168.1.2:9999/net/webresources/action/";
    String METHOD = "hauteaction";
	String finalUrl = "http://192.168.1.2:9999/net/webresources/action/hauteaction";
	TextView text_input;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_plus_actif_achat);
		
		text_input = (TextView) findViewById(R.id.result);

	}
	
	public void open(View view){
	      
	     
	      
	      obj = new HandleXML(finalUrl);
	      obj.fetchXML();
	      while(obj.parsingComplete);
	      text_input.setText(obj.getidAction());

	   }
	
	
}
