package com.squirrel.notesapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<NotesList> notesList;
     
    public NoteAdapter(Context context, ArrayList<NotesList> notesList){
        this.context = context;
        this.notesList = notesList;
    }
 
    @Override
    public int getCount() {
        return notesList.size();
    }
 
    @Override
    public Object getItem(int position) {      
        return notesList.get(position);
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
            convertView = mInflater.inflate(R.layout.list_notes_item, null);
        }
          
        //ImageView img = (ImageView) convertView.findViewById(R.id.course_image);
        TextView title = (TextView) convertView.findViewById(R.id.notes_title);
        TextView date = (TextView) convertView.findViewById(R.id.notes_date);
        TextView id = (TextView) convertView.findViewById(R.id.notes_id);	
          
        //img.setImageResource(courseItems.get(position).getImage());
        title.setText(notesList.get(position).getNoteTitle());
        date.setText(notesList.get(position).getNoteDate());
        id.setText(notesList.get(position).getNoteId());
         
        return convertView;
    }
}
