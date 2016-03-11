package com.example.trial;



import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity implements OnClickListener{
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.profile_options, menu);

		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.About)
		{
	        Intent intnt = new Intent(this, AboutActivity.class);
			startActivity(intnt);
	        finish();
		}
		else if(item.getItemId()==R.id.Browse)
		{
			Intent intent = new Intent(this, BrowserActivity.class);
	        startActivity(intent);
	        finish();
		}
		else if(item.getItemId()==R.id.easy)
		{
			Intent intent = new Intent(this, EasyGo.class);
	        startActivity(intent);
	        finish();
		}
		return super.onOptionsItemSelected(item);
	}
	TextView shwname,shwmail,shwprof;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_prof);
		shwname = (TextView) findViewById(R.id.rname2);
		shwmail = (TextView) findViewById(R.id.rmail2);
		shwprof = (TextView) findViewById(R.id.rprof2);
		MainActivity.prof = getSharedPreferences(MainActivity.file, 0);
	 	   String res = MainActivity.prof.getString("job", "couldn't load").toString();
	    	shwprof.setText(res);
	    	 res = MainActivity.prof.getString("name", "couldn't load").toString();
	    	shwname.setText(res);
	        res = MainActivity.prof.getString("mail", "couldn't load").toString();
	    	shwmail.setText(res);
		Button edit = (Button) findViewById(R.id.vbtn);
		edit.setOnClickListener(this);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(event.getAction() == KeyEvent.ACTION_DOWN){
	        switch(keyCode)
	        {
	        case KeyEvent.KEYCODE_BACK:	        
	              super.onBackPressed();
	            return true;
	        }

	    }
	    return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intnt = new Intent(this, MainActivity1.class);
        startActivity(intnt);
        
	}

}
