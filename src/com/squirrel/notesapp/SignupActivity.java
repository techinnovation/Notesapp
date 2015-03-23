package com.squirrel.notesapp;


import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.squirrel.notesapp.SignupActivity;
import com.squirrel.notesapp.R;

public class SignupActivity extends Activity {
	String fn, ln, em, u, p;
	String server_response;
	EditText username, password, firstname, lastname, email;

	// Alert Dialog Manager
	//AlertDialogManager alert;
	private ProgressDialog pDialog;

	// Session Manager Class
	//SessionManager session;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	//RequestQueue controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_signup);
		//alert = new AlertDialogManager();
		
		//controller = Volley.newRequestQueue(this);

		/*Typeface font = Typeface.createFromAsset(getAssets(),
				"OpenSans_Regular_webfont.ttf");*/
		TextView txt1 = (TextView) findViewById(R.id.label_signin);
		//txt1.setTypeface(font);

		firstname = (EditText) findViewById(R.id.firstname);
		lastname = (EditText) findViewById(R.id.lastname);

		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		email = (EditText) findViewById(R.id.email);

		findViewById(R.id.sign_up_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						u = username.getText().toString();
						p = password.getText().toString();
						fn = firstname.getText().toString();
						ln = lastname.getText().toString();
						em = username.getText().toString();

						if (u.trim().length() > 0 && p.trim().length() > 0
								&& fn.trim().length() > 0
								&& ln.trim().length() > 0
								&& em.trim().length() > 0) {

							String url = "http://android-apis.firefightersmagazine-nigeria.com/index.php/";

							pDialog = new ProgressDialog(SignupActivity.this);
							pDialog.setMessage("Registering...");
							pDialog.show();
							
							/*StringRequest postRequest = new StringRequest(Request.Method.POST, url, 
								    new Response.Listener<String>() 
								    {
								        @Override
								        public void onResponse(String response) {
								            // response
								            Log.d("Response", response);
								            pDialog.hide();
								            
								            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
								            
								        }
								    }, 
								    new Response.ErrorListener() 
								    {
								         @Override
								         public void onErrorResponse(VolleyError error) {
								             // error
								             Log.d("Error.Response", error.getMessage());
								             
								             pDialog.hide();
								             
								             Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
								       }
								    }
								) {     
								   /* @Override
								    protected Map<String, String> getParams() {
										Map<String, String> params = new HashMap<String, String>();
										params.put("tag", "register");
										params.put("firstname", fn);
										params.put("lastname", ln);
										params.put("email", em);
										params.put("username", u);
										params.put("password", p);

										return params;
									}*/
								};

						

							// Adding request to request queue
							//controller.add(postRequest);

						
					}
				});
		
	}
}