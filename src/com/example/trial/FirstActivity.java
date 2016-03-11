package com.example.trial;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FirstActivity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	ActionBar actionbar = getActionBar();
	actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	String label1 = getResources().getString(R.string.label1);
	Tab tab = actionbar.newTab();
	tab.setText(label1);
	TabListener<Tab1Fragment> t1=new TabListener<Tab1Fragment>(this, label1, Tab1Fragment.class);
    tab.setTabListener(t1);
    actionbar.addTab(tab);
    String label2 = getResources().getString(R.string.label2);
    tab = actionbar.newTab();
	tab.setText(label2);
	TabListener<Tab2Fragment> t2=new TabListener<Tab2Fragment>(this, label2, Tab2Fragment.class);
    tab.setTabListener(t2);
    actionbar.addTab(tab);
    
}
private class TabListener <T extends Fragment> implements ActionBar.TabListener{
	private Fragment mfragment;
	private final Activity mactivity;
	private final String mtag;
	private Class<T> mclass;
	public TabListener(Activity activity, String tag, Class<T> cls){
		mactivity=activity;
		mtag=tag;
		mclass=cls;
	}
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(mfragment== null){
			mfragment = Fragment.instantiate(mactivity, mclass.getName());
			ft.add(android.R.id.content, mfragment,mtag);
		}else{
			ft.attach(mfragment);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(mfragment != null){
			ft.detach(mfragment);
	}
	
}
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
	super.onOptionsItemSelected(item);
	switch (item.getItemId()) {
	case R.id.m2 :
	{
        Intent intnt = new Intent(this, ProfileActivity.class);
		startActivity(intnt);
        finish();
	}
	case R.id.m3 :
	{
		Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        finish();
        
	}
	case R.id.easy :
	{
		
		Intent intent = new Intent(this, EasyGo.class);
        startActivity(intent);
        finish();
        
	}
	}
	return true;
}

}

