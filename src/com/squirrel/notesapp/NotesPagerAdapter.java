package com.squirrel.notesapp;

//import mycourses.fragments.*;
import android.support.v4.app.*;

public class NotesPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 2;
	Fragment[] fragments = new Fragment[]{
			new availablecoursesFragment(), new mycoursesFragment()
			
	};
	
	String FragmentsTitle[] = new String[]{
			"AVAILABLE COURSES","MY COURSES"
	};

	/** Constructor of the class */
	public NotesPagerAdapter(FragmentManager fm) {
		super(fm);
		
		
		
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int index) {
				
		return fragments[index];
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {		
		return FragmentsTitle[position];
	}
	
	
	
	
}
