package com.example.gawat;

import com.example.gawat.R.drawable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class RSMainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsmain);
		
		//tab notification
		TabHost tabHost = getTabHost();
		TabSpec tabNotif = createTab(tabHost, "tabNotif", "PEMBERITAHUAN", LvNotif.class);
		tabNotif.setIndicator(getTabIndicator(tabHost.getContext(),
				R.string.textTabTitle2, drawable.icon_people));
		tabHost.addTab(tabNotif);
		
		//tab history
		TabSpec tabHistory = createTab(tabHost, "tabHistory", "HISTORY", LvAmbulance.class);
		tabHistory.setIndicator(getTabIndicator(tabHost.getContext(),
				R.string.textTabTitle2, drawable.icon_people));
		tabHost.addTab(tabHistory);
		
		//tab to make status of ambulance
		TabSpec tabStatAmbu = createTab(tabHost, "tabStatAmbu", "STATUS AMBULANCE", LvHistory.class);
		tabStatAmbu.setIndicator(getTabIndicator(tabHost.getContext(),
				R.string.textTabTitle2, drawable.icon_people));
		tabHost.addTab(tabStatAmbu);

	}

	private TabSpec createTab(TabHost tabHost, String tag, String label,
			Class<?> intent) {

		TabSpec tab = tabHost.newTabSpec(tag);
		tab.setIndicator(label);
		tab.setContent(new Intent(this, intent));
		return tab;
	}

	
	private View getTabIndicator(Context context, int title, int icon) {
		View view = LayoutInflater.from(context).inflate(R.layout.tab_layout,
				null);
		ImageView iv = (ImageView) view.findViewById(R.id.imageView);
		iv.setImageResource(icon);
		TextView tv = (TextView) view.findViewById(R.id.textViewTab);
		tv.setText(title);
		return view;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rsmain, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
