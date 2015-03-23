package com.squirrel.notesapp;

import com.squirrel.notesapp.AddDemoActivity;
import com.squirrel.notesapp.DiscoverActivity;
import com.squirrel.notesapp.HelpActivity;
import com.squirrel.notesapp.MyDiscussionsActivity;
import com.squirrel.notesapp.ProfileActivity;
import com.squirrel.notesapp.SettingsActivity;
import com.squirrel.notesapp.R;
import com.squirrel.notesapp.SessionManager;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.squirrel.notesapp.NavDrawerItem;
import com.squirrel.notesapp.NavDrawerListAdapter;

public class NotesActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	String videoPath;
	int No = 0;

	// Session Manager Class
	SessionManager session;
	//public AlertDialog.Builder builder = new AlertDialog.Builder(this);
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Inflate the layout
		setContentView(R.layout.activity_notes);
		//session = new SessionManager(getApplicationContext());
		
		ProgressBar bar = (ProgressBar)findViewById(R.id.ProgressBar1);
		bar.setVisibility(ProgressBar.GONE);
		
		/** Getting a reference to the ViewPager defined the layout file */
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		
		pager.setOffscreenPageLimit(3);

		getActionBar().setTitle("Current Courses");
		
		//session.checkLogin();
        
        // get user data from session
        //HashMap<String, String> user = session.getUserDetails();
         
        // name
        //String name = user.get(SessionManager.KEY_NAME);
        
        //String id = user.get(SessionManager.KEY_ID);
        // email
        //String email = user.get(SessionManager.KEY_EMAIL);
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@SuppressWarnings("deprecation")
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					getActionBar().setTitle("All Courses");

					break;

				case 1:
					getActionBar().setTitle("My Courses");				
					
					break;

				}
			}

		});

		/** Getting fragment manager */
		FragmentManager fm = getSupportFragmentManager();

		/** Instantiating FragmentPagerAdapter */
		NotesPagerAdapter pagerAdapter = new NotesPagerAdapter(fm);

		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);

		PagerTabStrip tab = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
		tab.setDrawFullUnderline(true);
		
		

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		navDrawerItems.add(new NavDrawerItem("Hello "+LoginObject.Fullname, navMenuIcons
				.getResourceId(0, -1), "Welcome to NotesApp"));

		/*navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1),
				"Interact with instructors/fellow students"));*/

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1), "View available courses/enrolled"));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1), "View your notes"));

		

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1), "Profiles and Settings"));

		/*navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(6, -1), ""));*/

		// Recycle the typed array
		navMenuIcons.recycle();
		
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button

		// ColorDrawable color = new ColorDrawable(Color.parseColor("#ee9120"));
		// getActionBar().setBackgroundDrawable(color);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				// getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			mDrawerList.setItemChecked(3, true);
	        mDrawerList.setSelection(3);

		}
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			TextView textView = (TextView) view.findViewById(R.id.title);
		    textView.setTextColor(getResources().getColor(R.color.white));
		    
		    TextView textView2 = (TextView) view.findViewById(R.id.desc);
		    textView2.setTextColor(getResources().getColor(R.color.white));
		    
			Intent i;
			switch (position) {
			
			case 1:

				mDrawerLayout.closeDrawer(mDrawerList);

				break;

			case 2:

				mDrawerLayout.closeDrawer(mDrawerList);

				break;

			

			case 3:

				i = new Intent(NotesActivity.this, SettingsActivity.class);
				startActivity(i);
				finish();

				break;

			/*case 6:

				i = new Intent(NotesActivity.this, AddDemoActivity.class);
				startActivity(i);
				finish();

				break;
*/
			default:
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_actions, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_search:

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		return super.onPrepareOptionsMenu(menu);
	}

}
