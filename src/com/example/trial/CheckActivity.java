package com.example.trial;

import java.io.ObjectOutputStream.PutField;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class CheckActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.check_set);
	    SharedPreferences settings = getSharedPreferences("prefs", 0);
	    boolean firstRun = settings.getBoolean("firstRun", true);
	    if ( firstRun )
	    {
	        // here run your first-time instructions, for example :
	        startActivityForResult(
	             new Intent(this, MainActivity.class),
	             2);

	    }
	    else
	    {
	    	
		 Intent in =  new Intent(this, BrowserActivity.class);
	     startActivity(in);
         finish();        
	    }
	 }



	// when your InstructionsActivity ends, do not forget to set the firstRun boolean
	 protected void onActivityResult(int requestCode, int resultCode,
	         Intent data) {
	     if (requestCode == 2) {
	         SharedPreferences settings = getSharedPreferences("prefs", 0);
	         SharedPreferences.Editor editor = settings.edit();
	         editor.putBoolean("firstRun",false);
	         editor.commit();
	     }
	 }
}
