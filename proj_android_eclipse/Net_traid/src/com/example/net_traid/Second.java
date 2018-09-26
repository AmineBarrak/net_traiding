package com.example.net_traid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class Second extends Activity implements OnClickListener {
	
	Button button1,button2,button3,button4,button5,button6,button7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		button6 = (Button)findViewById(R.id.button6);
		button7 = (Button)findViewById(R.id.button7);
		
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
			case R.id.button1:
	
				Intent i=new Intent(this,Action_5_fort_hausse.class);
				startActivity(i);	
			
				break;
			case R.id.button2:
				Intent f=new Intent(this,Action_5_fort_baisse.class);
				startActivity(f);	
				
				break;
			case R.id.button3:
				Intent g=new Intent(this,Top_action_plus_vendu.class);
				startActivity(g);	
				
				break;
			case R.id.button4:
				Intent h=new Intent(this,Top_action_plus_achete.class);
				startActivity(h);	
		
				break;
			case R.id.button5:
				Intent j=new Intent(this,Client_plus_actif_vente.class);
				startActivity(j);	
		
				break;
			case R.id.button6:
				Intent k=new Intent(this,Client_plus_actif_achat.class);
				startActivity(k);	
				
				break;
			case R.id.button7:
				Intent l=new Intent(this,Portefeuil_plus_varie.class);
				startActivity(l);	
				
				break;
			default:
				break;
		}
		
	}
}
