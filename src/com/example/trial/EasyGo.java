package com.example.trial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class EasyGo extends Activity{
GridView gv;
WebView myWebView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.easy);
		gv=(GridView) findViewById(R.id.gview);
		gv.setAdapter(new ImageAdapter(this));
		
		myWebView = (WebView) findViewById(R.id.gweb);
		
		gv.setOnItemClickListener(new OnItemClickListener() {
              
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				myWebView.setVisibility(View.VISIBLE);
				myWebView.setBackgroundColor(0);
				myWebView.setBackgroundResource(R.drawable.bb);
			    myWebView.setWebViewClient(new WebViewClient());
			    
				if(arg2==0){
				    myWebView.loadUrl("http://www.amazon.com/gp/registry/wishlist/ref=nav_wishlist_btn");
				    
				}
				else if(arg2==1){
					myWebView.loadUrl("http://developer.android.com/index.html");
				
					}
				else if(arg2==2){
					myWebView.loadUrl("https://www.coursera.org/courses");
					
					}
				else if(arg2==3){
					myWebView.loadUrl("https://www.google.com/drive/");
					
					
					}
				else if(arg2==4){
					myWebView.loadUrl("http://www.ebay.in/rts/events/Ethnic%20Jewellery/53a42d616279cbd9d551539d");
		
					
					}
				else if(arg2==5){
					myWebView.loadUrl("https://www.facebook.com/");
					
				
					}
				else if(arg2==6){
					myWebView.loadUrl("http://www.engineering.com/");
					
				
					}
				else if(arg2==7){
					myWebView.loadUrl("https://github.com/");
					
					}
			
				else if(arg2==8){
					myWebView.loadUrl("https://instagram.com/");
					
			
					}
				else if(arg2==9){
					myWebView.loadUrl("https://www.linkedin.com/uas/login?goback=%2Ebzc_*1_*1_*1_*1_*1_hb*4ft*4work_*1_*1_*1_*1_linkedin&trk=hb_signin");
					
				
					}
				else if(arg2==10){
					myWebView.loadUrl("https://en.wordpress.com/wp-login.php?redirect_to=http%3A%2F%2Ftheme.wordpress.com%2F");

					}
				else if(arg2==11){
					myWebView.loadUrl("https://www.eventbrite.com/");
			
					}
				else if(arg2==12){
					myWebView.loadUrl("http://stackoverflow.com/questions/tagged/android");
				
					}
				else if(arg2==13){
					myWebView.loadUrl("https://www.quora.com/India");
					
			
					}
				else if(arg2==14){
					myWebView.loadUrl("https://www.slideshare.net/create");
					
	
					}
				else if(arg2==15){
					myWebView.loadUrl("http://en.wikipedia.org/wiki/Main_Page");
					

					}
				else if(arg2==16){
					myWebView.loadUrl("http://www.knowafest.com/search/label/Hyderabad");
					
	
					}
				else if(arg2==17){
					myWebView.loadUrl("https://www.ieee.org/index.html");
					

					}
			}
		});
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2==0){
				Toast.makeText(getBaseContext(), "It's AMAZON", Toast.LENGTH_SHORT).show();
				return true;
				}
				else if(arg2==1){
					Toast.makeText(getBaseContext(), "It's androiddevelopers.com", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==2){
					Toast.makeText(getBaseContext(), "It's COURSERA", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==3){
					Toast.makeText(getBaseContext(), "It's GOOGLE DRIVE", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==4){
					Toast.makeText(getBaseContext(), "It's EBAY", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==5){
					Toast.makeText(getBaseContext(), "It's FACEBOOK", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==6){
					Toast.makeText(getBaseContext(), "It's ENGINEERING.COM", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==7){
					Toast.makeText(getBaseContext(), "It's GITHUB", Toast.LENGTH_SHORT).show();
					return true;
					}
				
				else if(arg2==8){
					Toast.makeText(getBaseContext(), "It's INSTAGRAM", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==9){
					Toast.makeText(getBaseContext(), "It's LINKEDIN", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==10){
					Toast.makeText(getBaseContext(), "It's WORDPRESS", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==11){
					Toast.makeText(getBaseContext(), "It's EVENT BRITE", Toast.LENGTH_SHORT).show();

					return true;
					}
				else if(arg2==12){
					Toast.makeText(getBaseContext(), "It's STACKOVERFLOW", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==13){
					Toast.makeText(getBaseContext(), "It's QUORA", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==14){
					Toast.makeText(getBaseContext(), "It's SLIDESHARE", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==15){
					Toast.makeText(getBaseContext(), "It's WIKIPEDIA", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==16){
					Toast.makeText(getBaseContext(), "It's KNOWAFEST", Toast.LENGTH_SHORT).show();
					return true;
					}
				else if(arg2==17){
					
					Toast.makeText(getBaseContext(), "It's IEEE.ORG", Toast.LENGTH_SHORT).show();
					return true;
					}
				
				else{
					Toast.makeText(getBaseContext(), "undefined", Toast.LENGTH_SHORT).show();
					return true;
				}
			}
			
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.go_easy, menu);

		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.abt)
		{
	        Intent intnt = new Intent(this, AboutActivity.class);
			startActivity(intnt);
	        
		}
		else if(item.getItemId()==R.id.brow)
		{
			Intent intent = new Intent(this, BrowserActivity.class);
	        startActivity(intent);
	      
		}
		else if(item.getItemId()==R.id.prof)
		{
			Intent intent = new Intent(this, ProfileActivity.class);
	        startActivity(intent);
	        
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(event.getAction() == KeyEvent.ACTION_DOWN){
	        switch(keyCode)
	        {
	        case KeyEvent.KEYCODE_BACK:
	            if(myWebView.canGoBack()){
	                myWebView.goBack();
	            }else if(myWebView.getVisibility() == View.VISIBLE){
					myWebView.loadUrl("");

	                myWebView.setVisibility(View.INVISIBLE);
	            }
	            else
	              super.onBackPressed();
	            return true;
	        }

	    }
	    return super.onKeyDown(keyCode, event);
	}
}
