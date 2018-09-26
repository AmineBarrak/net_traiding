package com.example.net_traid;

import com.example.net_traid.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	EditText name;
	EditText pass;
	Button login;
	Button cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name =(EditText) findViewById(id.editText1);
		pass=(EditText) findViewById(id.editText2);
		login= (Button) findViewById(id.button1);
		cancel=(Button) findViewById(id.button2);
		login.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}
	

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String na=name.getText().toString();
		String pa=pass.getText().toString();
		switch(v.getId()){
		
		case R.id.button1:
				if(na.equals("amine") && pa.equals("amine")){
					Intent i=new Intent(this,Second.class);
					startActivity(i);	
				}
				else{
					Toast.makeText(getBaseContext(), "error passworld!", Toast.LENGTH_LONG).show();
				}
			break;
		case R.id.button2:
			name.setText("");
			pass.setText("");
			
			break;
		default:
			break;
		}
	}
}
