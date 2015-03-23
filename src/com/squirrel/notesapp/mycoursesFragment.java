package com.squirrel.notesapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class mycoursesFragment extends Fragment {
	private ArrayList<Courses1> courseItems;
	private CourseAdapter1 adapter;
	ListView list;
	View view;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setRetainInstance(true);
		MyAsyncTask task = new MyAsyncTask();
        task.setListener(this);
        task.execute();
				
		view = inflater.inflate(R.layout.fragment_mycourses, container,	false);
		return view;
			
		
	}
		
	public void handleResponse(String resp) {
        //Do something with response
		courseItems = new ArrayList<Courses1>();	
        Log.d("TAG", resp);
        try {
			//JSONArray items = response.getJSONArray(resp);
			//JSONObject mainObject = new JSONObject(resp);
			JSONArray items = new JSONArray(resp);
			//Log.d("TAG ERROR", items.length()+"");
			courseItems.clear();
			
			for (int i = 0; i < items.length(); i++) {
                JSONObject c = items.getJSONObject(i);
                
                String id = c.getString("Id");                    
                String title = c.getString("CourseTitle");                    
                String code = c.getString("CourseCode"); 
                //String lecturer = c.getString("LectureName");
               // Log.d("TAG ERROR"+ i, lecturer+"");
                //String imageUrl = "http://android-apis.firefightersmagazine-nigeria.com/images/"+logo;
				Courses1 model = new Courses1();
				model.setCourseCode(code);
				model.setCourseTitle(title);
				model.setID(id);
				//model.setLecturerName(lecturer);
				courseItems.add(model);  
			}
			list = (ListView) getActivity().findViewById(R.id.ongoingcourse_list);
			adapter = new CourseAdapter1(getActivity().getApplicationContext(), courseItems);
			list.setAdapter(adapter);
			
			list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// getting values from selected ListItem

					// Starting single contact activity
					Intent in = new Intent(getActivity().getApplicationContext(),
							CoursenotesActivity.class);
					in.putExtra("title", courseItems.get(position).getCourseTitle());
					in.putExtra("code", courseItems.get(position).getCourseCode());
					in.putExtra("id", courseItems.get(position).getCourseID());
					CourseObject.CourseCode = courseItems.get(position).getCourseCode();
					CourseObject.CourseId = courseItems.get(position).getCourseID();
					CourseObject.CourseTitle = courseItems.get(position).getCourseTitle();
					startActivity(in);

				}
			});
			
        }catch(JSONException ex){
        	Log.d("TAG ERROR", ex.getMessage());
        }
    }
	
}

