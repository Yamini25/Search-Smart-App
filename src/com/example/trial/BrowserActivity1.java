package com.example.trial;


import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class BrowserActivity1 extends TabActivity{
	SQLiteDatabase gdata;
	TabHost tabHost;
	int flag=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tabHost = getTabHost();
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("TAB 1");
       
		spec1.setContent(new Intent(this, GlobalActivity.class));
		spec1.setIndicator("Global Search");

		TabSpec spec2 = tabHost.newTabSpec("TAB 2");
		spec2.setIndicator("Local Search");
		
		spec2.setContent(new Intent(this, LocalActivity.class));

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
       tabHost.setBackgroundColor(Color.BLACK);
       final Dialog dialog1 = new Dialog(BrowserActivity1.this);
       // Include dialog.xml file
       dialog1.setContentView(R.layout.dialog1);
       // Set dialog title
       dialog1.setTitle("Remember !!!");

       // set values for custom dialog components - text, image and button

       dialog1.show();
        
       Button declineButton1 = (Button) dialog1.findViewById(R.id.declineButton1);
       // if decline button is clicked, close the custom dialog
       declineButton1.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               // Close dialog
               dialog1.dismiss();
              callnext();
           }

		private void callnext() {
			// TODO Auto-generated method stub
			final Dialog dialog = new Dialog(BrowserActivity1.this);
		       // Include dialog.xml file
		       dialog.setContentView(R.layout.dialog);
		       // Set dialog title
		       dialog.setTitle("Scroll left for links");

		       // set values for custom dialog components - text, image and button
		       
		       ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
		       image.setImageResource(R.drawable.scroll);
		       dialog.show();
		        
		       Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
		       // if decline button is clicked, close the custom dialog
		       declineButton.setOnClickListener(new OnClickListener() {
		           @Override
		           public void onClick(View v) {
		               // Close dialog
		               dialog.dismiss();               
		           }		
		       });       
		}		
       });
       
       
       
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.options_menu, menu);
		return super.onCreateOptionsMenu(menu);		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 if(item.getItemId()==R.id.m2)
		{
	        Intent intnt = new Intent(this, ProfileActivity.class);
			startActivity(intnt);
	     
		}
		else if(item.getItemId()==R.id.m3)
		{
			Intent intent = new Intent(this, AboutActivity.class);
	        startActivity(intent);
	        
	        
		}
		else if(item.getItemId()==R.id.easy)
		{
			Intent intent = new Intent(this, EasyGo.class);
	        startActivity(intent);
	        
	        
		}
		return super.onOptionsItemSelected(item);
	}

}
