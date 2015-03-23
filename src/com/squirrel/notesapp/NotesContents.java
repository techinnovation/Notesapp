package com.squirrel.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class NotesContents extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notecontent);
		Intent in = getIntent();
		String note_id = in.getStringExtra("id");
		if(note_id== null)
		{
			Toast.makeText(this, "Note(s) empty for course", Toast.LENGTH_LONG).show();
		}
		WebView web = (WebView)findViewById(R.id.NotesContent);
		web.setWebChromeClient(new WebChromeClient());
		web.setWebViewClient(new WebViewClient());
		web.clearCache(true);
		web.clearHistory();
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		web.loadUrl("http://watermark.notesapp.org/Note/NoteWebView?noteId="+note_id);//( "text/html", null);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar actions click
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
}
