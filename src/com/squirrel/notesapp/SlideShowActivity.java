package com.squirrel.notesapp;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squirrel.notesapp.R;

 
public class SlideShowActivity extends FragmentActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);
        
        /** Getting a reference to the ViewPager defined the layout file */
		ViewPager pager = (ViewPager) findViewById(R.id.slideshow_pager);
		
		/** Getting fragment manager */
		FragmentManager fm = getSupportFragmentManager();

		/** Instantiating FragmentPagerAdapter */
		SlideShowPagerAdapter pagerAdapter = new SlideShowPagerAdapter(fm);
		

		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);
		
		pager.setOnPageChangeListener(new OnPageChangeListener(){

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
				ImageView toogle = (ImageView)findViewById(R.id.toogle);
				switch (position){
				case 0:
					toogle.setImageResource(R.drawable.toogle1);				
					
					break;
					
				case 1:
					toogle.setImageResource(R.drawable.toogle2);					
					
					break;
					
				case 2:
					toogle.setImageResource(R.drawable.toogle3);					
					
					break;
					
				case 3:
					toogle.setImageResource(R.drawable.toogle4);					
					break;
				}
			}
			
		});
        
        
        
        
        findViewById(R.id.start).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						//attemptLogin();
						
						Intent i = new Intent(SlideShowActivity.this, LandingActivity.class);
		                startActivity(i);	
		                
		             // close this activity
		                finish();
					}
				});
        
        
        
        
        
        
    }
 
}
