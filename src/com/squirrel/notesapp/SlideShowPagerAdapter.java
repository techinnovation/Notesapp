package com.squirrel.notesapp;

import slideshow.fragments.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SlideShowPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 4;
	Fragment[] fragments = new Fragment[]{
			new slide1Fragment(), new slide2Fragment(),  new slide3Fragment(),
			new slide4Fragment()
			
	};
	/** Constructor of the class */
	public SlideShowPagerAdapter(FragmentManager fm) {
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
}
