package com.example.net_traid;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class HandleXML {

   private String idAction = "idAction";
   private String nomEntreprise = "nomEntreprise";

   private String urlString = null;
   private XmlPullParserFactory xmlFactoryObject;
   public volatile boolean parsingComplete = true;
   public HandleXML(String url){
      this.urlString = url;
   }
   public String getidAction(){
      return idAction;
   }
   public String getnomEntreprise(){
      return nomEntreprise;
   }


   public void parseXMLAndStoreIt(XmlPullParser myParser) {
      int event;
      String text=null;
      try {
         event = myParser.getEventType();
         while (event != XmlPullParser.END_DOCUMENT) {
            String name=myParser.getName();
            switch (event){
               case XmlPullParser.START_TAG:
               break;
               case XmlPullParser.TEXT:
               text = myParser.getText();
               break;

               case XmlPullParser.END_TAG:
                  if(name.equals("idAction")){
                	  idAction = text;
                  }
                  else if(name.equals("nomEntreprise")){ 	
                	  nomEntreprise = myParser.getAttributeValue(null,"value");
                  }
                  else{
                  }
                  break;
                  }		 
                  event = myParser.next(); 

              }
                 parsingComplete = false;
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
   public void fetchXML(){
      Thread thread = new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               URL url = new URL(urlString);
               HttpURLConnection conn = (HttpURLConnection) 
               url.openConnection();
                  conn.setReadTimeout(10000 /* milliseconds */);
                  conn.setConnectTimeout(15000 /* milliseconds */);
                  conn.setRequestMethod("GET");
                  conn.setDoInput(true);
                  conn.connect();
            InputStream stream = conn.getInputStream();

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
            , false);
            myparser.setInput(stream, null);
            parseXMLAndStoreIt(myparser);
            stream.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    });

    thread.start(); 


   }

}