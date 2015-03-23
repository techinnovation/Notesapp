package com.squirrel.notesapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
 

public class CourseAdapter1 extends BaseAdapter{

    private Context context;
    private ArrayList<Courses1> courseItems;
     
    public CourseAdapter1(Context context, ArrayList<Courses1> courseItems){
        this.context = context;
        this.courseItems = courseItems;
    }
 
    @Override
    public int getCount() {
        return courseItems.size();
    }
 
    @Override
    public Object getItem(int position) {      
        return courseItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
    
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_courses_item, null);
        }
          
        //ImageView img = (ImageView) convertView.findViewById(R.id.course_image);
        TextView title = (TextView) convertView.findViewById(R.id.course_title);
        TextView code = (TextView) convertView.findViewById(R.id.course_code);
        TextView id = (TextView) convertView.findViewById(R.id.course_id);	
        TextView lecturer = (TextView)convertView.findViewById(R.id.course_lecturer);
          
        //img.setImageResource(courseItems.get(position).getImage());
        title.setText(courseItems.get(position).getCourseTitle());
        code.setText(courseItems.get(position).getCourseCode());
        id.setText(courseItems.get(position).getCourseID());
        lecturer.setText(courseItems.get(position).getLecturerName());
         
        return convertView;
    }
}
