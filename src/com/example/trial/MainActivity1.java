package com.example.trial;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.provider.Browser;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity1 extends Activity implements OnClickListener {
 EditText ename,email;
 Spinner sprof;
 
 public static String file = "MySharedData";
 public static SharedPreferences prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiile);
        ename = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.mail);
        sprof= (Spinner) findViewById(R.id.profession);
        ImageButton save = (ImageButton) findViewById(R.id.save);
        save.setOnClickListener(this);
        prof=  this.getSharedPreferences("MySharedData",Context.MODE_PRIVATE);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String name = ename.getText().toString().trim();
		String mail = email.getText().toString().trim();
        String data = (String) sprof.getSelectedItem();
		if(name.length()==0||
	    		   mail.length()==0||
	    		  data.length()==0)
	    		{
	    			showMessage("Error", "Please enter all values");
	    			return;
	    		}
		else if(!isEmailValid(mail))
		{
			Toast.makeText(this, "Enter valid email id", Toast.LENGTH_SHORT).show();
		}
		else if(data=="Enter your Stream of study")
		{
			Toast.makeText(this, "Enter your stream of study", Toast.LENGTH_SHORT).show();
		}
		
		else
		{		
			
			SharedPreferences.Editor editor = prof.edit();
			editor.putString("job", data);
			editor.commit();
			editor.putString("name", name);
			editor.commit();
			editor .putString("mail", mail);
			editor.commit();
			
			Toast.makeText(this, "Profile Editted. Start Browsing ", Toast.LENGTH_LONG).show();
		  Intent intnt = new Intent(this, BrowserActivity.class);
          startActivity(intnt);
          finish();		
	    }
	}

	private boolean isEmailValid(String mail) {
		// TODO Auto-generated method stub
		Boolean isValid=false;
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = mail;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
		return isValid;
	}

	private void showMessage(String title, String message) {
		// TODO Auto-generated method stub
		Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
		
	}


    
    
}
