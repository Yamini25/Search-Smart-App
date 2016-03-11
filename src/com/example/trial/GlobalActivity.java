package com.example.trial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class GlobalActivity extends Activity implements OnClickListener {
	private static final String TAG_RESULTS = "results";
	private static final String TAG_URL = "url";
	private static final String TAG_TITLE = "titleNoFormatting";
	private static final String TAG_CONTENT = "content";

	SharedPreferences prefs;
	String string;
	
	static JSONArray contacts = null;
	JSONArray QuickViewItems = null;
	static ArrayList<HashMap<String, String>> contactList;
	HorizontalScrollView hsv;
	static ArrayList<String> seerchurls, seerchurls2;
	static ArrayList<String> titles, titles2;
	static ArrayList<String> contents, contents2;
	static WebView web1, web2, web3, web4, web5, web6, web7, web8, web9, web10,
			web11, web12, web13, web14, web15, web16;
	Button google, more, show;
	String aa, slink;
	
	static AutoCompleteTextView esearch;
	int j = 1;
	TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12,
			tv13, tv14, tv15, tv16, dtv1, dtv2, dtv3, dtv4, dtv5, dtv6, dtv7,
			dtv8, dtv9, dtv10, dtv11, dtv12, dtv13, dtv14, dtv15, dtv16, ltv1,
			ltv2, ltv3, ltv4, ltv5, ltv6, ltv7, ltv8, ltv9, ltv10, ltv11,
			ltv12, ltv13, ltv14, ltv15, ltv16;
	ProgressDialog pdialog;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	static String Keyword;
	int hides = 0;
	public String link1, link2, link3, link4, link5, link6, link7, link8,
	link9, link10, link11, link12, link13, link14, link15, link16;
public String userentereddata;
Cursor c;

TextView[] tv;

SQLiteDatabase db;
MyDBHelper helper;

ArrayList<String> al = new ArrayList<String>();
ArrayList<String> dbdata = new ArrayList<String>();
ArrayAdapter<String> adp;
//String string=new String(" in computer Science");
LinearLayout myLayout;
WebView wv;
public String getStr() {
	string = prefs.getString("job", "");
	String str = esearch.getText().toString();
	boolean hasUppercase = !str.equals(str.toLowerCase());
	if(str.length()==0)
	{
		pdialog.cancel();
		Toast.makeText(this, "Enter a keyword", Toast.LENGTH_SHORT).show();
	}
	
	else if(hasUppercase)
	{
		str =str.concat(" in ");
		str=str.concat(string);
	}
	return str;
}
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.global);
	// creating views from xml like search box, logo ,
			// full,show and search
			more = (Button) findViewById(R.id.button2);
			contactList = new ArrayList<HashMap<String, String>>();

			hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);          
             prefs=  this.getSharedPreferences("MySharedData",Context.MODE_PRIVATE);
            // Toast.makeText(getApplicationContext(), prefs.getString("job", null), Toast.LENGTH_SHORT).show();
			final LinearLayout hide = (LinearLayout) findViewById(R.id.ful);
			esearch = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

			esearch.setFocusableInTouchMode(true);
			esearch.requestFocus();
			esearch.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

			helper = new MyDBHelper(this);
			db = helper.getWritableDatabase();

			try {
				
				userentereddata = getStr();
				if(userentereddata.length()==0)
				{
					pdialog.dismiss();
					Toast.makeText(this, "Enter a keyword", Toast.LENGTH_SHORT).show();
				}
				else
				{
				  c = db.rawQuery("select data,link from usersearchdata", null);

				/*
				 * "select data,link from usersearchdata where data"+ " = '"
				 * +userentereddata + "'"
				 */
				/* "select data,link from usersearchdata where data LIKE '%+userentereddata+%'" */

				/*
				 * SimpleCursorAdapter adapter=new
				 * SimpleCursorAdapter(this,R.layout.mylist,c,new String[] { "data",
				 * "link" }, new int[] { R.id.text1, R.id.text2 });
				 * esearch.setAdapter(adapter);
				 */
				if (c != null) {
					if (c.moveToFirst()) {

						do {

							String sdata = c.getString(c.getColumnIndex("data"));
							System.out
									.println("elizabeth 1111111111111222222222222"
											+ sdata);
							String slink = c.getString(c.getColumnIndex("link"));

							al.add(sdata + "\n" + slink);

						} while (c.moveToNext());
					}
					adp = new ArrayAdapter<String>(getApplicationContext(),
							android.R.layout.simple_dropdown_item_1line, al);

					esearch.setThreshold(1);

					esearch.setAdapter(adp);

					/* esearch.setTextColor(Color.RED); */
					c.close();

				}
				}
			} catch (Exception e) {

			}

			esearch.setOnEditorActionListener(new OnEditorActionListener() {

				@Override
				public boolean onEditorAction(TextView v, int keyCode,
						KeyEvent event) {
					// TODO Auto-generated method stub

					hide.setVisibility(View.VISIBLE);

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(esearch.getWindowToken(), 0);
					fetch();
					hsv.scrollTo(hsv.getLeft(), hsv.getTop());
					return false;


			}
			});

			// SELECT * FROM [Customers] WHERE Address LIKE '%%';

			// al.add();

			// creatin the contrlos for showing the titles, links and description
			// from xml desing

			tv1 = (TextView) findViewById(R.id.textView1);
			tv2 = (TextView) findViewById(R.id.textView2);
			tv3 = (TextView) findViewById(R.id.textView3);
			tv4 = (TextView) findViewById(R.id.textView4);
			tv5 = (TextView) findViewById(R.id.textView5);
			tv6 = (TextView) findViewById(R.id.textView6);
			tv7 = (TextView) findViewById(R.id.textView7);
			tv8 = (TextView) findViewById(R.id.textView8);
			tv9 = (TextView) findViewById(R.id.textView17);
			tv10 = (TextView) findViewById(R.id.textView18);
			tv11 = (TextView) findViewById(R.id.textView19);
			tv12 = (TextView) findViewById(R.id.textView20);
			tv13 = (TextView) findViewById(R.id.textView21);
			tv14 = (TextView) findViewById(R.id.textView22);
			tv15 = (TextView) findViewById(R.id.textView23);
			tv16 = (TextView) findViewById(R.id.textView24);

			dtv1 = (TextView) findViewById(R.id.textView9);
			dtv2 = (TextView) findViewById(R.id.textView10);
			dtv3 = (TextView) findViewById(R.id.textView11);
			dtv4 = (TextView) findViewById(R.id.textView12);
			dtv5 = (TextView) findViewById(R.id.textView13);
			dtv6 = (TextView) findViewById(R.id.textView14);
			dtv7 = (TextView) findViewById(R.id.textView15);
			dtv8 = (TextView) findViewById(R.id.textView16);
			dtv9 = (TextView) findViewById(R.id.textView25);
			dtv10 = (TextView) findViewById(R.id.textView26);
			dtv11 = (TextView) findViewById(R.id.textView27);
			dtv12 = (TextView) findViewById(R.id.textView28);
			dtv13 = (TextView) findViewById(R.id.textView29);
			dtv14 = (TextView) findViewById(R.id.textView30);
			dtv15 = (TextView) findViewById(R.id.textView31);
			dtv16 = (TextView) findViewById(R.id.textView32);

			ltv1 = (TextView) findViewById(R.id.textView33);
			ltv2 = (TextView) findViewById(R.id.textView34);
			ltv3 = (TextView) findViewById(R.id.textView35);
			ltv4 = (TextView) findViewById(R.id.textView36);
			ltv5 = (TextView) findViewById(R.id.textView37);
			ltv6 = (TextView) findViewById(R.id.textView38);
			ltv7 = (TextView) findViewById(R.id.textView39);
			ltv8 = (TextView) findViewById(R.id.textView40);
			ltv9 = (TextView) findViewById(R.id.textView41);
			ltv10 = (TextView) findViewById(R.id.textView42);
			ltv11 = (TextView) findViewById(R.id.textView43);
			ltv12 = (TextView) findViewById(R.id.textView44);
			ltv13 = (TextView) findViewById(R.id.textView45);
			ltv14 = (TextView) findViewById(R.id.textView46);
			ltv15 = (TextView) findViewById(R.id.textView47);
			ltv16 = (TextView) findViewById(R.id.textView48);

			/*
			 * Intializing the the action listenrers for the contrlos like titles
			 * ,links,description for showing the web views
			 */

			tv1.setOnClickListener(this);
			tv2.setOnClickListener(this);
			tv3.setOnClickListener(this);
			tv4.setOnClickListener(this);
			tv5.setOnClickListener(this);
			tv6.setOnClickListener(this);
			tv7.setOnClickListener(this);
			tv8.setOnClickListener(this);
			tv9.setOnClickListener(this);
			tv10.setOnClickListener(this);
			tv11.setOnClickListener(this);
			tv12.setOnClickListener(this);
			tv13.setOnClickListener(this);
			tv14.setOnClickListener(this);
			tv15.setOnClickListener(this);
			tv16.setOnClickListener(this);
			dtv1.setOnClickListener(this);
			dtv2.setOnClickListener(this);
			dtv3.setOnClickListener(this);
			dtv4.setOnClickListener(this);
			dtv5.setOnClickListener(this);
			dtv6.setOnClickListener(this);
			dtv7.setOnClickListener(this);
			dtv8.setOnClickListener(this);
			dtv9.setOnClickListener(this);
			dtv10.setOnClickListener(this);
			dtv11.setOnClickListener(this);
			dtv12.setOnClickListener(this);
			dtv13.setOnClickListener(this);
			dtv14.setOnClickListener(this);
			dtv15.setOnClickListener(this);
			dtv16.setOnClickListener(this);
			ltv1.setOnClickListener(this);
			ltv2.setOnClickListener(this);
			ltv3.setOnClickListener(this);
			ltv4.setOnClickListener(this);
			ltv5.setOnClickListener(this);
			ltv6.setOnClickListener(this);
			ltv7.setOnClickListener(this);
			ltv8.setOnClickListener(this);
			ltv9.setOnClickListener(this);
			ltv10.setOnClickListener(this);
			ltv11.setOnClickListener(this);
			ltv12.setOnClickListener(this);
			ltv13.setOnClickListener(this);
			ltv14.setOnClickListener(this);
			ltv15.setOnClickListener(this);
			ltv16.setOnClickListener(this);

			google = (Button) findViewById(R.id.button1);

			google.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					/* web1.loadUrl("http://coign.net"); */
					
					hide.setVisibility(View.VISIBLE);
					/*
					 * wv.setVisibility(View.INVISIBLE);
					 * 
					 * myLayout.setVisibility(View.INVISIBLE);
					 */
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(google.getWindowToken(), 0);
					// String aa = esearch.getText().toString();
					// sdata.execSQL("INSERT INTO idata VALUES('" + aa + "')");

					fetch();
					hsv.scrollTo(hsv.getLeft(), hsv.getTop());
					
				}
			});

			show = (Button) findViewById(R.id.button3);
			show.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					hide.setVisibility(View.VISIBLE);
				}
			});
			more.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					show.setVisibility(View.VISIBLE);
					hide.setVisibility(View.GONE);

				}
			});

			// crerating the webviews from the xml design

			web1 = (WebView) findViewById(R.id.webView1);
			web2 = (WebView) findViewById(R.id.webView2);
			web3 = (WebView) findViewById(R.id.webView3);
			web4 = (WebView) findViewById(R.id.webView4);
			web5 = (WebView) findViewById(R.id.webView5);
			web6 = (WebView) findViewById(R.id.webView6);
			web7 = (WebView) findViewById(R.id.webView7);
			web8 = (WebView) findViewById(R.id.webView8);
			web9 = (WebView) findViewById(R.id.webView9);
			web10 = (WebView) findViewById(R.id.webView10);
			web11 = (WebView) findViewById(R.id.webView11);
			web12 = (WebView) findViewById(R.id.webView12);
			web13 = (WebView) findViewById(R.id.webView13);
			web14 = (WebView) findViewById(R.id.webView14);
			web15 = (WebView) findViewById(R.id.webView15);
			web16 = (WebView) findViewById(R.id.webView16);

			/*
			 * Applying properties for webviews properties like enabling
			 * javascript,setting zoom with suppoting view port
			 */

			web1.getSettings().setJavaScriptEnabled(true);
			web1.getSettings().setSupportZoom(true);
			web1.getSettings().setBuiltInZoomControls(true);
			web1.getSettings().setLoadWithOverviewMode(true);
			web1.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web1.getSettings().setUseWideViewPort(true);

			web2.getSettings().setJavaScriptEnabled(true);
			web2.getSettings().setSupportZoom(true);
			web2.getSettings().setBuiltInZoomControls(true);
			web2.getSettings().setLoadWithOverviewMode(true);
			web2.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web2.getSettings().setUseWideViewPort(true);

			web3.getSettings().setJavaScriptEnabled(true);
			web3.getSettings().setSupportZoom(true);
			web3.getSettings().setBuiltInZoomControls(true);
			web3.getSettings().setLoadWithOverviewMode(true);
			web3.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web3.getSettings().setUseWideViewPort(true);

			web4.getSettings().setJavaScriptEnabled(true);
			web4.getSettings().setSupportZoom(true);
			web4.getSettings().setBuiltInZoomControls(true);
			web4.getSettings().setLoadWithOverviewMode(true);
			web4.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web4.getSettings().setUseWideViewPort(true);

			web5.getSettings().setJavaScriptEnabled(true);
			web5.getSettings().setSupportZoom(true);
			web5.getSettings().setBuiltInZoomControls(true);
			web5.getSettings().setLoadWithOverviewMode(true);
			web5.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web5.getSettings().setUseWideViewPort(true);

			web6.getSettings().setJavaScriptEnabled(true);
			web6.getSettings().setSupportZoom(true);
			web6.getSettings().setBuiltInZoomControls(true);
			web6.getSettings().setLoadWithOverviewMode(true);
			web6.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web6.getSettings().setUseWideViewPort(true);

			web7.getSettings().setJavaScriptEnabled(true);
			web7.getSettings().setSupportZoom(true);
			web7.getSettings().setBuiltInZoomControls(true);
			web7.getSettings().setLoadWithOverviewMode(true);
			web7.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web7.getSettings().setUseWideViewPort(true);

			web8.getSettings().setJavaScriptEnabled(true);
			web8.getSettings().setSupportZoom(true);
			web8.getSettings().setBuiltInZoomControls(true);
			web8.getSettings().setLoadWithOverviewMode(true);
			web8.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web8.getSettings().setUseWideViewPort(true);

			web9.getSettings().setJavaScriptEnabled(true);
			web9.getSettings().setSupportZoom(true);
			web9.getSettings().setBuiltInZoomControls(true);
			web9.getSettings().setLoadWithOverviewMode(true);
			web9.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web9.getSettings().setUseWideViewPort(true);

			web10.getSettings().setJavaScriptEnabled(true);
			web10.getSettings().setSupportZoom(true);
			web10.getSettings().setBuiltInZoomControls(true);
			web10.getSettings().setLoadWithOverviewMode(true);
			web10.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web10.getSettings().setUseWideViewPort(true);

			web11.getSettings().setJavaScriptEnabled(true);
			web11.getSettings().setSupportZoom(true);
			web11.getSettings().setBuiltInZoomControls(true);
			web11.getSettings().setLoadWithOverviewMode(true);
			web11.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web11.getSettings().setUseWideViewPort(true);

			web12.getSettings().setJavaScriptEnabled(true);
			web12.getSettings().setSupportZoom(true);
			web12.getSettings().setBuiltInZoomControls(true);
			web12.getSettings().setLoadWithOverviewMode(true);
			web12.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web12.getSettings().setUseWideViewPort(true);

			web13.getSettings().setJavaScriptEnabled(true);
			web13.getSettings().setSupportZoom(true);
			web13.getSettings().setBuiltInZoomControls(true);
			web13.getSettings().setLoadWithOverviewMode(true);
			web13.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web13.getSettings().setUseWideViewPort(true);

			web14.getSettings().setJavaScriptEnabled(true);
			web14.getSettings().setSupportZoom(true);
			web14.getSettings().setBuiltInZoomControls(true);
			web14.getSettings().setLoadWithOverviewMode(true);
			web14.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web14.getSettings().setUseWideViewPort(true);

			web15.getSettings().setJavaScriptEnabled(true);
			web15.getSettings().setSupportZoom(true);
			web15.getSettings().setBuiltInZoomControls(true);
			web15.getSettings().setLoadWithOverviewMode(true);
			web15.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web15.getSettings().setUseWideViewPort(true);

			web16.getSettings().setJavaScriptEnabled(true);
			web16.getSettings().setSupportZoom(true);
			web16.getSettings().setBuiltInZoomControls(true);
			web16.getSettings().setLoadWithOverviewMode(true);
			web16.getSettings().setDefaultZoom(ZoomDensity.FAR);
			web16.getSettings().setUseWideViewPort(true);

			//iweb1.loadUrl("http://techfortit.com");

			web8.setWebChromeClient(new WebChromeClient() {

				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					// TODO Auto-generated method stub
					super.onProgressChanged(view, newProgress);
					if (newProgress > 1) {

						for (int i = 0; i < titles.size(); i++)

						{
							tv1.setText(titles.get(0));
							System.out.println(" testing  222222222222  "
									+ titles.get(0) + "\n" + web1.getUrl());

							tv2.setText(titles.get(1));
							tv3.setText(titles.get(2));
							tv4.setText(titles.get(3));
							tv5.setText(titles.get(4));
							tv6.setText(titles.get(5));
							tv7.setText(titles.get(6));
							tv8.setText(titles.get(7));

							ltv1.setText(seerchurls.get(0));
							System.out.println(" testing  222222222222  "
									+ titles.get(0) + "\n" + web1.getUrl());
							link1 = seerchurls.get(0);
							ltv2.setText(seerchurls.get(1));
							link2 = seerchurls.get(1);
							ltv3.setText(seerchurls.get(2));
							link3 = seerchurls.get(2);
							ltv4.setText(seerchurls.get(3));
							link4 = seerchurls.get(3);
							ltv5.setText(seerchurls.get(4));
							link5 = seerchurls.get(4);
							ltv6.setText(seerchurls.get(5));
							link6 = seerchurls.get(5);

							ltv7.setText(seerchurls.get(6));

							link7 = seerchurls.get(6);
							ltv8.setText(seerchurls.get(7));
							link8 = seerchurls.get(7);

							for (int a = 0; a < contents.size(); a++) {
								contents.get(a).replaceAll("\\<[^>]*>", "");
							}

							dtv1.setText(Html.fromHtml(contents.get(0)));
							dtv2.setText(Html.fromHtml(contents.get(1)));
							dtv3.setText(Html.fromHtml(contents.get(2)));
							dtv4.setText(Html.fromHtml(contents.get(3)));

							dtv5.setText(Html.fromHtml(contents.get(4)));
							dtv6.setText(Html.fromHtml(contents.get(5)));
							dtv7.setText(Html.fromHtml(contents.get(6)));
							dtv8.setText(Html.fromHtml(contents.get(7)));

						}
					}
				}

			});
			web16.setWebChromeClient(new WebChromeClient() {

				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					// TODO Auto-generated method stub
					super.onProgressChanged(view, newProgress);
					if (newProgress > 1) {
						for (int i = 0; i < titles.size(); i++)

						{
							tv9.setText(titles2.get(0));
							System.out.println(" testing  222222222222  "
									+ titles.get(0) + "\n" + web1.getUrl());
							tv10.setText(titles2.get(1));
							tv11.setText(titles2.get(2));
							tv12.setText(titles2.get(3));
							tv13.setText(titles2.get(4));
							tv14.setText(titles2.get(5));
							tv15.setText(titles2.get(6));
							tv16.setText(titles2.get(7));

							ltv9.setText(seerchurls2.get(0));
							System.out.println(" testing  222222222222  "
									+ titles.get(0) + "\n" + web1.getUrl());

							link9 = seerchurls2.get(0);
							ltv10.setText(seerchurls2.get(1));
							link10 = seerchurls2.get(1);
							ltv11.setText(seerchurls2.get(2));
							link11 = seerchurls2.get(2);
							ltv12.setText(seerchurls2.get(3));
							link12 = seerchurls2.get(3);
							ltv13.setText(seerchurls2.get(4));
							link13 = seerchurls2.get(4);
							ltv14.setText(seerchurls2.get(5));
							link14 = seerchurls2.get(5);
							ltv15.setText(seerchurls2.get(6));
							link15 = seerchurls2.get(6);
							ltv16.setText(seerchurls2.get(7));
							link16 = seerchurls2.get(7);

							for (int a = 0; a < contents2.size(); a++) {
								contents2.get(a).replaceAll("<[^>]*>", "");
							}

							dtv9.setText(Html.fromHtml(contents2.get(0)));
							dtv10.setText(Html.fromHtml(contents2.get(1)));
							dtv11.setText(Html.fromHtml(contents2.get(2)));
							dtv12.setText(Html.fromHtml(contents2.get(3)));

							dtv13.setText(Html.fromHtml(contents2.get(4)));
							dtv14.setText(Html.fromHtml(contents2.get(5)));
							dtv15.setText(Html.fromHtml(contents2.get(6)));
							dtv16.setText(Html.fromHtml(contents2.get(7)));

						}
					}
				}

			});

			web1.setWebViewClient(new WebViewClient());
			web2.setWebViewClient(new WebViewClient());
			web3.setWebViewClient(new WebViewClient());
			web4.setWebViewClient(new WebViewClient());
			web5.setWebViewClient(new WebViewClient());
			web6.setWebViewClient(new WebViewClient());
			web7.setWebViewClient(new WebViewClient());
			web8.setWebViewClient(new WebViewClient());
			web9.setWebViewClient(new WebViewClient());
			web10.setWebViewClient(new WebViewClient());
			web11.setWebViewClient(new WebViewClient());
			web12.setWebViewClient(new WebViewClient());
			web13.setWebViewClient(new WebViewClient());
			web14.setWebViewClient(new WebViewClient());
			web15.setWebViewClient(new WebViewClient());
			web16.setWebViewClient(new WebViewClient());

		}

		protected void fetch() {
			// TODO Auto-generated method stub
			Fetchlink fetch = new Fetchlink();
			fetch.execute();
		}

		/*
		 * capturing the event of back button pressing in mobile aas for showing the
		 * navigation through backward option for individual webviews
		 */

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_BACK && j == 1 && web1.canGoBack()) {
				web1.goBack();
				System.out
						.println("   back ward in first web 111111111111111222222222222222222 ");
				// Toast.makeText(getApplicationContext(), "first",
				// Toast.LENGTH_SHORT).show();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 2
					&& web2.canGoBack()) {
				web2.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 3
					&& web3.canGoBack()) {
				web3.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 4
					&& web4.canGoBack()) {
				web4.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 5
					&& web5.canGoBack()) {
				web5.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 6
					&& web6.canGoBack()) {
				web6.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 7
					&& web7.canGoBack()) {
				web7.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 8
					&& web8.canGoBack()) {
				web8.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 9
					&& web9.canGoBack()) {
				web9.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 10
					&& web10.canGoBack()) {
				web10.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 11
					&& web11.canGoBack()) {
				web11.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 12
					&& web12.canGoBack()) {
				web12.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 13
					&& web13.canGoBack()) {
				web13.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 14
					&& web14.canGoBack()) {
				web14.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 15
					&& web15.canGoBack()) {
				web15.goBack();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_BACK && j == 16
					&& web16.canGoBack()) {
				web16.goBack();
				return true;
			}

			else {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							GlobalActivity.this);
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
			}
		
			return super.onKeyDown(keyCode, event);
			}

		// getiin the datafrom json in the background for loading webviews as for
		// the user query

		public class Fetchlink extends AsyncTask<Void, Void, String> {

			ProgressDialog pdialog;

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				pdialog = new ProgressDialog(GlobalActivity.this);
				pdialog.setMessage("searching....");
				pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				pdialog.show();
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub

				// g.load();

				pdialog.dismiss();

				super.onPostExecute(result);
				if (result == null) {
					Toast.makeText(getApplicationContext(),
							"NO DATA FROM THE SERVER", Toast.LENGTH_SHORT).show();
				} else {

					try {

						// retriving the data from json object as for the showing
						// the
						// titles,links,description

						seerchurls = new ArrayList<String>();
						titles = new ArrayList<String>();
						contents = new ArrayList<String>();
						JSONObject json = new JSONObject(result);

						JSONObject js = json.getJSONObject("responseData");
						contacts = js.getJSONArray(TAG_RESULTS);
						System.out.println("araay  jsssssssssss " + contacts);

						for (int v = 0; v < contacts.length(); v++) {
							JSONObject c = contacts.getJSONObject(v);
							System.out.println("teszting   uobject  " + c);

							String search = c.getString(TAG_URL);
							String title = c.getString(TAG_TITLE);
							String content = c.getString(TAG_CONTENT);
							seerchurls.add(search);
							titles.add(title);
							contents.add(content);
							HashMap<String, String> map = new HashMap<String, String>();
							map.put(TAG_TITLE, title);
							map.put(TAG_CONTENT, content);
							contactList.add(map);
							// images(logo);
							System.out.println("1234567890   " + seerchurls);
							System.out.println("1234567890   " + contactList);
						}
						// g.load();
						System.out.println("1234567890   " + contents);
						web1.loadUrl(seerchurls.get(0));
						web2.loadUrl(seerchurls.get(1));
						web3.loadUrl(seerchurls.get(2));
						web4.loadUrl(seerchurls.get(3));
						web5.loadUrl(seerchurls.get(4));
						web6.loadUrl(seerchurls.get(5));
						web7.loadUrl(seerchurls.get(6));
						web8.loadUrl(seerchurls.get(7));

						// background task running for the loading next 8 webviews

						More mr = new More(GlobalActivity.this);
						mr.execute(GlobalActivity.this);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			@Override
			protected String doInBackground(Void... params) {
				// TODO Auto-generated method stub
				try {
					Keyword = getStr();
					// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();

					String urlencode = URLEncoder.encode(Keyword, "UTF-8");
					HttpGet httpPost = new HttpGet(
							"http://ajax.googleapis.com/ajax/services/search/web?rsz=8&v=1.0&q="
									+ urlencode);

					HttpResponse httpResponse = httpClient.execute(httpPost);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(is, "iso-8859-1"), 8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line + "\n");
					}
					is.close();
					json = sb.toString();
				} catch (Exception e) {
					Log.e("Buffer Error", "Error converting result " + e.toString());
				}
				System.out.println(" asdfgbhn   " + json);
				// try parse the string to a JSON object
				try {
					jObj = new JSONObject(json);
				} catch (JSONException e) {
					Log.e("JSON Parser", "Error parsing data " + e.toString());
				}

				// return JSON String
				return json;

			}

		}

		@Override
		public void onStop() {
			super.onStop();
			// prog.dismiss();
			if (pdialog != null && pdialog.isShowing()) {
				pdialog.dismiss();
			}
			// progress.dismiss();

		}

		public void load() {
			// TODO Auto-generated method stub
			web1.loadUrl(seerchurls.get(0));
			web2.loadUrl(seerchurls.get(1));
			web3.loadUrl(seerchurls.get(2));
			web4.loadUrl(seerchurls.get(3));
			web5.loadUrl(seerchurls.get(4));
			web6.loadUrl(seerchurls.get(5));
			web7.loadUrl(seerchurls.get(6));
			web8.loadUrl(seerchurls.get(7));
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			if (pdialog != null && pdialog.isShowing()) {
				pdialog.cancel();
			}
			db.close();

		}

		// implementing action events as for the user choice for showing the
		// particuler selected webview

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			switch (v.getId()) {

			case R.id.textView1:
				j = 1;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link1);

				ContentValues cv = new ContentValues();
				cv.put("data", userentereddata);
				cv.put("link", link1);

				db.insert("usersearchdata", null, cv);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();

				web1.setVisibility(View.VISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;

			case R.id.textView2:
				j = 2;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link2);

				ContentValues cv1 = new ContentValues();
				cv1.put("data", userentereddata);
				cv1.put("link", link2);

				db.insert("usersearchdata", null, cv1);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();
				web1.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView3:
				j = 3;
				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link3);

				ContentValues cv2 = new ContentValues();
				cv2.put("data", userentereddata);
				cv2.put("link", link3);

				db.insert("usersearchdata", null, cv2);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();
				web1.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.VISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView4:
				j = 4;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link4);

				ContentValues cv3 = new ContentValues();
				cv3.put("data", userentereddata);
				cv3.put("link", link4);

				db.insert("usersearchdata", null, cv3);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();
				web1.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView5:
				j = 5;
				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link5);

				ContentValues cv4 = new ContentValues();
				cv4.put("data", userentereddata);
				cv4.put("link", link5);

				db.insert("usersearchdata", null, cv4);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView6:
				j = 6;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link6);

				ContentValues cv5 = new ContentValues();
				cv5.put("data", userentereddata);
				cv5.put("link", link6);

				db.insert("usersearchdata", null, cv5);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.VISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView7:
				j = 7;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link7);

				ContentValues cv6 = new ContentValues();
				cv6.put("data", userentereddata);
				cv6.put("link", link7);

				db.insert("usersearchdata", null, cv6);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.VISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView8:
				j = 8;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link8);

				ContentValues cv7 = new ContentValues();
				cv7.put("data", userentereddata);
				cv7.put("link", link8);

				db.insert("usersearchdata", null, cv7);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();
				// more.setVisibility(View.VISIBLE);
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.VISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView17:

				j = 9;
				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link9);

				ContentValues cv8 = new ContentValues();
				cv8.put("data", userentereddata);
				cv8.put("link", link9);

				db.insert("usersearchdata", null, cv8);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.VISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView18:

				j = 10;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link10);

				ContentValues cv9 = new ContentValues();
				cv9.put("data", userentereddata);
				cv9.put("link", link10);

				db.insert("usersearchdata", null, cv9);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.VISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView19:

				j = 11;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link11);

				ContentValues cv10 = new ContentValues();
				cv10.put("data", userentereddata);
				cv10.put("link", link11);

				db.insert("usersearchdata", null, cv10);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.VISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView20:

				j = 12;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link12);

				ContentValues cv11 = new ContentValues();
				cv11.put("data", userentereddata);
				cv11.put("link", link12);

				db.insert("usersearchdata", null, cv11);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.VISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView21:

				j = 13;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link13);

				ContentValues cv12 = new ContentValues();
				cv12.put("data", userentereddata);
				cv12.put("link", link13);

				db.insert("usersearchdata", null, cv12);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView22:

				j = 14;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link14);

				ContentValues cv13 = new ContentValues();
				cv13.put("data", userentereddata);
				cv13.put("link", link14);

				db.insert("usersearchdata", null, cv13);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.VISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView23:

				j = 15;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link15);

				ContentValues cv14 = new ContentValues();
				cv14.put("data", userentereddata);
				cv14.put("link", link15);

				db.insert("usersearchdata", null, cv14);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.VISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView24:

				j = 16;

				userentereddata = getStr();

				System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii" + userentereddata);

				System.out.println("hellooooooooooooooooooooooooooooo" + link16);

				ContentValues cv15 = new ContentValues();
				cv15.put("data", userentereddata);
				cv15.put("link", link16);

				db.insert("usersearchdata", null, cv15);

				Toast.makeText(getApplicationContext(), "Saved Sucessfully",
						Toast.LENGTH_LONG).show();

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.VISIBLE);
				break;
			case R.id.textView9:
				j = 1;
				web1.setVisibility(View.VISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView10:
				j = 2;
				web1.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView11:
				j = 3;
				web1.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.VISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView12:
				j = 4;
				web1.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView13:
				j = 5;
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView14:
				j = 6;
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.VISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView15:
				j = 7;
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.VISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView16:
				j = 8;

				// more.setVisibility(View.VISIBLE);
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.VISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView25:

				j = 9;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.VISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView26:

				j = 10;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.VISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView27:

				j = 11;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.VISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView28:

				j = 12;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.VISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView29:

				j = 13;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView30:

				j = 14;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.VISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView31:

				j = 15;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.VISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView32:

				j = 16;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.VISIBLE);
				break;

			case R.id.textView33:
				j = 1;
				web1.setVisibility(View.VISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView34:
				j = 2;
				web1.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView35:
				j = 3;
				web1.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.VISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView36:
				j = 4;
				web1.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);

				break;
			case R.id.textView37:
				j = 5;
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.VISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView38:
				j = 6;
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.VISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView39:
				j = 7;
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.VISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView40:
				j = 8;

				// more.setVisibility(View.VISIBLE);
				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.VISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;
			case R.id.textView41:

				j = 9;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.VISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView42:

				j = 10;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.VISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView43:

				j = 11;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.VISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView44:

				j = 12;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.VISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView45:

				j = 13;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView46:

				j = 14;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.INVISIBLE);
				web14.setVisibility(View.VISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView47:

				j = 15;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.VISIBLE);
				web16.setVisibility(View.INVISIBLE);
				break;

			case R.id.textView48:

				j = 16;

				web1.setVisibility(View.INVISIBLE);
				web5.setVisibility(View.INVISIBLE);
				web3.setVisibility(View.INVISIBLE);
				web4.setVisibility(View.INVISIBLE);
				web2.setVisibility(View.INVISIBLE);
				web6.setVisibility(View.INVISIBLE);
				web7.setVisibility(View.INVISIBLE);
				web8.setVisibility(View.INVISIBLE);
				web9.setVisibility(View.INVISIBLE);
				web10.setVisibility(View.INVISIBLE);
				web11.setVisibility(View.INVISIBLE);
				web12.setVisibility(View.INVISIBLE);
				web13.setVisibility(View.VISIBLE);
				web14.setVisibility(View.INVISIBLE);
				web15.setVisibility(View.INVISIBLE);
				web16.setVisibility(View.VISIBLE);
				break;

			}

		}

		public void morelinks(String result) {
			// TODO Auto-generated method stub
			if (result == null) {
				Toast.makeText(getApplicationContext(), "NO DATA FROM THE SERVER",
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					seerchurls2 = new ArrayList<String>();
					titles2 = new ArrayList<String>();
					contents2 = new ArrayList<String>();
					JSONObject json = new JSONObject(result);

					JSONObject js = json.getJSONObject("responseData");
					contacts = js.getJSONArray(TAG_RESULTS);
					System.out.println("araay  jsssssssssss " + contacts);

					for (int v = 0; v < contacts.length(); v++) {
						JSONObject c = contacts.getJSONObject(v);
						System.out.println("teszting   uobject  " + c);

						String search = c.getString(TAG_URL);
						String title = c.getString(TAG_TITLE);
						String content = c.getString(TAG_CONTENT);
						seerchurls2.add(search);
						titles2.add(title);
						contents2.add(content);
						HashMap<String, String> map = new HashMap<String, String>();
						map.put(TAG_TITLE, title);
						map.put(TAG_CONTENT, content);
						contactList.add(map);
						// images(logo);
						System.out.println("1234567890   " + seerchurls);
						System.out.println("1234567890   " + contactList);
					}
					// g.load();
					System.out.println("1234567890   in moreeeeeeeeeeeeeeeeeeee   "
							+ contents);
					web9.loadUrl(seerchurls2.get(0));
					web10.loadUrl(seerchurls2.get(1));
					web11.loadUrl(seerchurls2.get(2));
					web12.loadUrl(seerchurls2.get(3));
					web13.loadUrl(seerchurls2.get(4));
					web14.loadUrl(seerchurls2.get(5));
					web15.loadUrl(seerchurls2.get(6));
					web16.loadUrl(seerchurls2.get(7));
					// tv9.setText(titles.get(0));

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


}
