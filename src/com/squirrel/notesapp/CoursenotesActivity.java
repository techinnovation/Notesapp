package com.squirrel.notesapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CoursenotesActivity extends Activity{
	private ArrayList<NotesList> noteLists;
	private NoteAdapter adapter;
	ListView list;
	View view;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_coursenotes);
        
        Intent in = getIntent();
        
        String course_code = in.getStringExtra("code");
       // setRetainInstance(true);
		MyAsyncTask4 task = new MyAsyncTask4();
        task.setListener(this);
        task.execute();
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
	}
	public void handleResponse(String resp) {
        //Do something with response
		noteLists = new ArrayList<NotesList>();	
        Log.d("TAG", resp);
        try {
			//JSONArray items = response.getJSONArray(resp);
			//JSONObject mainObject = new JSONObject(resp);
        	String parsedData = "";
        	SimpleDateFormat df=new SimpleDateFormat("dd/MMM/yyyy hh:mm a");
			JSONArray items = new JSONArray(resp);
			//Log.d("TAG ERROR", items.length()+"");
			for (int i = 0; i < items.length(); i++) {
                JSONObject c = items.getJSONObject(i);
                
                String id = c.getString("Id");                    
                String title = c.getString("Topic");                    
                String date = c.getString("CreatedAt"); 
                date=date.replace("/Date(", "").replace(")/", "");
    		     long time = Long.parseLong(date);
    		     Date d= new Date(time);
    		     parsedData=df.format(d);
                //String lecturer = c.getString("LectureName");
               // Log.d("TAG ERROR"+ i, lecturer+"");
                //String imageUrl = "http://android-apis.firefightersmagazine-nigeria.com/images/"+logo;
				NotesList model = new NotesList();
				model.setNoteId(id);
				model.setNoteTitle(title);
				model.setNoteDate(parsedData);
				//Log.d("Title", title);
				//model.setLecturerName(lecturer);
				noteLists.add(model);  
			}
			list = (ListView) this.findViewById(R.id.ongoingcoursenote_list);
			adapter = new NoteAdapter(this.getApplicationContext(), noteLists);
			list.setAdapter(adapter);
			
			list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// getting values from selected ListItem
					CourseObject.CourseId = null;
					// Starting single contact activity
					Intent in = new Intent(getApplicationContext(),
							NotesContents.class);
					//in.putExtra("title", noteLists.get(position).getNoteTitle());*/
					//in.putExtra("code", noteLists.get(position).getCourseCode());
					in.putExtra("id", noteLists.get(position).getNoteId());
					startActivity(in);
				}
			});
			
        }catch(JSONException ex){
        	Log.d("TAG ERROR", ex.getMessage());
        }
    }
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar actions click
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
