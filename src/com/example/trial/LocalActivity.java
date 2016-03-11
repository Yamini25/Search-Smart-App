package com.example.trial;

import java.util.ArrayList;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LocalActivity extends Activity {
	String sdata=new String("");
	SQLiteDatabase db;
	MyDBHelper helper;
	String userentereddata;
	Cursor c;
	ArrayList<String> al = new ArrayList<String>();
	ArrayAdapter<String> adp;
	AutoCompleteTextView esearch;
	ArrayList<String> dbdata = new ArrayList<String>();
	String slink;
	TextView[] tv;
	ImageView[] im;
	LinearLayout myLayout;
	WebView wv;
	TextView tv1;
	int size;
	String s;
	public String url;
	int i;
	
	StringBuilder builder = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.local);
		wv = (WebView) findViewById(R.id.dbwebview);

		helper = new MyDBHelper(this);
		db = helper.getWritableDatabase();
		esearch = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		esearch.setFocusableInTouchMode(true);
		esearch.requestFocus();
		esearch.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

		wv.getSettings().setSupportZoom(true);
		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.getSettings().setDefaultZoom(ZoomDensity.FAR);
		wv.getSettings().setUseWideViewPort(true);
		try {
			userentereddata = esearch.getText().toString().trim();
			c = db.rawQuery("select data,link from usersearchdata", null);

			if (c != null) {
				if (c.moveToFirst()) {

					do {
                       
						String cdata = c.getString(c.getColumnIndex("data"));
						if(!(cdata.equalsIgnoreCase(sdata))){
							sdata=cdata;
						System.out
								.println("elizabeth 1111111111111222222222222"
										+ sdata);
						//String slink = c.getString(c.getColumnIndex("link"));

						al.add(sdata + "\n" );
						}
					} while (c.moveToNext());
				}
				adp = new ArrayAdapter<String>(getApplicationContext(),
						android.R.layout.simple_dropdown_item_1line, al);

				esearch.setThreshold(1);

				esearch.setAdapter(adp);

				esearch.setTextColor(Color.WHITE); 
				c.close();

			}

		} catch (Exception e) {

		}
	}

	public void dbsearch(View v) {

		try {

			fetch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fetch() {

		/* builder.delete(0, builder.length()); */

		myLayout = (LinearLayout) findViewById(R.id.my_layout);
		myLayout.removeAllViews();

		dbdata.clear();

		// tv[dbdata.size()].setText(null);

		userentereddata = esearch.getText().toString().trim();
		if (userentereddata.equals("")) {
			Toast.makeText(getApplicationContext(), "please enter data", 30)
					.show();
		} else {

			c = db.rawQuery(
					"select link from usersearchdata where data like '%"
							+ userentereddata + "%'", null);
			System.out.println("cursorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"
					+ c.getCount());

			if (c.getCount() == 0) {

				Toast.makeText(getApplicationContext(), "No data", 90).show();
			}

			else {
				c.moveToFirst();
				do {

					slink = c.getString(c.getColumnIndex("link"));
					System.out.println("5675" + slink);
					dbdata.add(slink);

				} while (c.moveToNext());

				tv = new TextView[dbdata.size()];
               im = new ImageView[dbdata.size()];
				// tv1=(TextView)findViewById(R.id.textView1);
               LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
               myLayout.setVisibility(View.VISIBLE);
				for (i = 0; i < dbdata.size(); i++)

				{
					/*
					 * builder.append(dbdata.get(i)); builder.append("    ");
					 */
					im[i] = new ImageView(this);					
                    im[i].setPadding(5, 5, 5, 5);					
                    im[i].setImageResource(R.drawable.bullet);					
					im[i].setLayoutParams(layoutParams);
					
					tv[i] = new TextView(this);
					tv[i].setPadding(5, 5, 5, 5);
					tv[i].setText(dbdata.get(i));
					tv[i].setTextColor(Color.BLUE);
					// s=builder.toString();
					// tv1.setText(s);
					size  = dbdata.size();

					/*
					 * String url=dbdata.get(i);
					 */
					// System.out.println("builder lengthhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh      "+builder.length());
					System.out
							.println("Array sizeeeeeeeeeeeeeeeeeeeeeeeeeeee        "
									+ size);
					//myLayout.setVisibility(View.VISIBLE);
					myLayout.addView(im[i]);
					
					myLayout.addView(tv[i]);
					

					tv[i].setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {

							String text = null;
							if (v instanceof TextView) {
								TextView t = (TextView) v;
								text = t.getText().toString();
								wv = (WebView) findViewById(R.id.dbwebview);

								wv.setVisibility(View.VISIBLE);
								
								wv.loadUrl(text);

								Toast.makeText(getApplicationContext(), text,
										Toast.LENGTH_LONG).show();

								c.close();

							}
						}

					});

				}
			}

		}

	}

	
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
	if (keyCode == KeyEvent.KEYCODE_BACK) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(
				LocalActivity.this);
		dialog.setTitle("Are you Sure! Do you want exit?");
		dialog.setPositiveButton("yes",
				new android.content.DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						finish();
					}

				});
		dialog.setNegativeButton("No",
				new android.content.DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub

					}

				});
		dialog.show();
	}
	return super.onKeyDown(keyCode, event);
}
}
