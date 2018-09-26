package com.example.net_traid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;


public class Action_5_fort_hausse extends Activity {


    String URL = "http://192.168.0.236:9999/net/webresources/action/";
    String METHOD = "hauteaction";
	
	TextView textView,display;
	//private String resp;

	class Action
	{

		public String date;
		public String id;
		public String nomEnt;
		public String Ent;
		public String idEnt;
		public String telephoneEnt;
		public String adresseEnt;
		public String valeur;
		public String nbrtot;

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_5_fort_hausse);
		textView = (TextView) findViewById(R.id.editView);
		display  = (TextView)findViewById(R.id.info);
		        
        new HttpAsyncTask().execute(URL+METHOD);
	
        XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

			    InputStream in_s = getApplicationContext().getAssets().open("test.xml");
		        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in_s, null);

	            parseXML(parser);

		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
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
    private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
		ArrayList<Action> actions = null;
        int eventType = parser.getEventType();
        Action currentAction = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	actions = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name == "action"){
                        currentAction = new Action();
                    } else if (currentAction != null){
                        if (name == "dateAction"){
                            currentAction.date = parser.nextText();
                        } else if (name == "idAction"){
                        	currentAction.id = parser.nextText();
                        } else 	if(name=="adresseEntreprise"){
                            currentAction.adresseEnt= parser.nextText();
                        }else if(name=="nomEntreprise"){
                        	currentAction.nomEnt= parser.nextText();
                        }else if(name=="teleEntreprise"){
                        	currentAction.telephoneEnt= parser.nextText();
                        }else if (name == "nbrot"){
	                        currentAction.nbrtot= parser.nextText();
	                    }  else if(name=="valeurAction"){
                    		currentAction.valeur= parser.nextText();
                    	}
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("action")&& name.equalsIgnoreCase("idEntreprise") && currentAction != null){
                    	actions.add(currentAction);
                    } 
            }
            eventType = parser.next();
        }

        PrintProducts(actions);
        
	}
    private void PrintProducts(ArrayList<Action> actions)
	{
		String content = "";
		Iterator<Action> it = actions.iterator();
		while(it.hasNext())
		{
			Action currAction  = it.next();
			content = content + "Id Action :" +  currAction.id + "n";
			content = content + "nom entreprise :" +  currAction.nomEnt + "n";

		}

		display.setText(content);
	}

	
	
}
        