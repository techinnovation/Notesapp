package com.squirrel.notesapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.notesapp.reuse.JSONParser;
import com.squirrel.notesapp.NotesActivity;
import com.squirrel.notesapp.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	//EditText etResponse;
	private ProgressDialog pDialog;
	AlertDialogManager alert = new AlertDialogManager();
	String name, email, myusername, mypassword;
	EditText username, password;
	private static String baseurl = "http://notesapp.org/student/studentlogin";
	  //JSON Node Names
	SessionManager session;  
	  JSONArray user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView txt1 = (TextView) findViewById(R.id.label_signin);
        username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		pDialog = new ProgressDialog(LoginActivity.this);
		
		
		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						/*
						 * Intent i = new Intent(LoginActivity.this,
						 * MainActivity.class); startActivity(i);
						 * 
						 * // close this activity finish();
						 */

						myusername = username.getText().toString();
						mypassword = password.getText().toString();

						if (myusername.trim().length() > 0
								&& mypassword.trim().length() > 0) {
							String RealUrl = baseurl+"?email="+myusername+"&password="+mypassword;
							pDialog.setMessage("Signing In...");
							pDialog.show();
							new HttpAsyncTask().execute(RealUrl);
						}
						    else {
						    	alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is empty", false);
							//alert goes here
						}
					}
				});

    }
    public static String GET(String url){
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
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
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	
        	//Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
        	pDialog.hide();
        	try {
				JSONObject json = new JSONObject(result);

				String fullname = "";
				String email = "";
				String ID = "";
				/*JSONArray articles = json.getJSONArray("articleList");
				str += "articles length = "+json.getJSONArray("articleList").length();
				str += "\n--------\n";
				str += "names: "+articles.getJSONObject(0).names();
				str += "\n--------\n";
				str += "url: "+articles.getJSONObject(0).getString("url");*/
				fullname = json.getString("FirstName")+" "+json.getString("LastName");
				email = json.getString("EmailAddress");
				ID		 = json.getString("Id");
				
				LoginObject.Fullname = fullname;
				LoginObject.Email = email;
				LoginObject.ID = ID;
				
                //session.createLoginSession(ID, fullname, email);
                
                // Staring MainActivity
                Intent i = new Intent(LoginActivity.this, NotesActivity.class);
                startActivity(i);
                //finish();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is not correct", false);
				//e.printStackTrace();
			}
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
