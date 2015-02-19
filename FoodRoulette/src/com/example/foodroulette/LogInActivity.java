package com.example.foodroulette;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends ActionBarActivity implements OnClickListener{

	private Button SignInButton;
	private EditText  username;
	private EditText  password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		
		SignInButton = (Button) findViewById(R.id.SignInButton);
		SignInButton.setOnClickListener((OnClickListener) this);
		username = (EditText) findViewById(R.id.editTextUsername);
		password = (EditText) findViewById(R.id.editTextPassword);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClick(View arg0) {
//	      if(username.getText().toString().equals("admin") && 
//	    	      password.getText().toString().equals("admin")){
//	    	      Toast.makeText(getApplicationContext(), "Redirecting...", 
//	    	      Toast.LENGTH_SHORT).show();
	    	      Intent intent = new Intent (this, UserAccountActivity.class);
	    	      startActivity(intent);
//	      }
//	      else{
//	    	   Toast.makeText(getApplicationContext(), "Wrong Credentials",
//	    	   Toast.LENGTH_SHORT).show();
	    	   
//	    	   attempts.setBackgroundColor(Color.RED);	
//	    	   attempts.setText(Integer.toString(counter));
//	    	   	if(counter==0){
//	    	   		login.setEnabled(false);
//	    	    }
	      }
	}
	
//}
