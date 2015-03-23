package com.squirrel.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.squirrel.notesapp.LoginActivity;
import com.squirrel.notesapp.SignupActivity;
import com.squirrel.notesapp.R;

public class LandingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.activity_landing);
		
		findViewById(R.id.login).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						//attemptLogin();
						
						Intent i = new Intent(LandingActivity.this, LoginActivity.class);
		                startActivity(i);
					}
				});
		
		/*findViewById(R.id.signup).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						//attemptLogin();
						
						Intent i = new Intent(LandingActivity.this, SignupActivity.class);
		                startActivity(i);
					}
				});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

