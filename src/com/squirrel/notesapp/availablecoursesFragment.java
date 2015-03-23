package com.squirrel.notesapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squirrel.notesapp.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint({ "ValidFragment", "NewApi" })
public class availablecoursesFragment extends Fragment  {
	private ArrayList<Courses1> courseItems;
	private CourseAdapter2 adapter;
	private availablecoursesFragment availablecoursesFragment = this;
	ListView list;
	View view;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setRetainInstance(true);
		MyAsyncTask2 task = new MyAsyncTask2();
        task.setListener(this);
        task.execute();
		view = inflater.inflate(R.layout.fragment__availablecourses, container,	false);
		
		return view;
	}
	public void handleResponse(String resp) {
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
                String lecturer = c.getString("LectureName");
				Courses1 model = new Courses1();
				model.setCourseCode(code);
				model.setCourseTitle(title);
				model.setID(id);
				model.setLecturerName(lecturer);
				courseItems.add(model);  
			}
			list = (ListView) getActivity().findViewById(R.id.availablecourse_list);
			adapter = new CourseAdapter2(getActivity().getApplicationContext(), courseItems);
			list.setAdapter(adapter);
			
			list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Courses1 getmyCourseId = (Courses1)courseItems.get(position);
					String selected = getmyCourseId.getCourseID()+" ";
					CourseObject.CourseId = selected;
					Context mContext = parent.getContext();
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
			        alertDialogBuilder.setTitle("Really?");
			        alertDialogBuilder.setMessage("Are you sure you want to follow this course?");
			        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			        	
			        	

						public void onClick(DialogInterface dialog, int id) {
			                //do things
			        		
			        		MyAsyncTask3 task = new MyAsyncTask3();
			                task.setListener(availablecoursesFragment );
			                task.execute();
			        		//Log.d("Result", MyAsyncTask3.result);
			           }
			        
			        });
			        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			            @Override
			            public void onClick(DialogInterface dialog, int which) {
			                dialog.dismiss();
			            }
			        });
			        AlertDialog alert11 = alertDialogBuilder.create();
			        alert11.show();
				}
			
		});
						
			
        }catch(JSONException ex){
        	Log.d("TAG ERROR", ex.getMessage());
        }
        
         
	}
	
	
	public void handleResp(String result) {
		// TODO Auto-generated method stub
		Log.d("TAG", result);
        try {
        	//Context mContext = parent.getContext();
        	Toast.makeText(this.getActivity(), "Course followed", Toast.LENGTH_LONG).show();
        	/*mycoursesFragment frag = new mycoursesFragment();
            final FragmentTransaction ft = frag.getFragmentManager().beginTransaction();
            //Fragment frg = getFragmentManager().findFragmentById(mycoursesFragment);
            //final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(frag);
            ft.attach(frag);
            ft.commit();*/
        	MyAsyncTask2 task = new MyAsyncTask2();
            task.setListener(this);
            task.execute();
            
			//Toast.makeText(this.getActivity(), "Course followed", 5);
        }
        catch(Exception es){
        	Log.d("TAG My ERROR", es.getMessage());
        }
	}
}
