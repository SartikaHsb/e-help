package com.example.gawat;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.database.gawat.DataDiri;
import com.database.gawat.db_gawat;
import com.example.gawat.R.drawable;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Contact extends TabActivity  {
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	CustomGridViewAdapter customGridAdapter;
	//button
	LinearLayout button_simpanprofil;
	
	//EditText
	EditText edit_nama_korban;
	public static TabHost tabHost;
	EditText etTempatLahir,etTanggalLahir,spinner_golongandarah,edit_telepon,edit_Wa,edit_bbm,edit_alamat,edit_penyakit;
	//db
	db_gawat db;
	
	//imageview
	ImageView tambahfoto;
//	ArrayList<DataDiri> datadiri = new ArrayList<DataDiri>();
//	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_contact);
	        
	        db = new db_gawat(this);
	       
	        tabHost = getTabHost();
//	        this.setNewTab(this, tabHost, "tab1", R.string.textTabTitle1, drawable.add_contact3, R.id.tab1);
//	        this.setNewTab(this, tabHost, "tab2", R.string.textTabTitle2, drawable.icon_people, R.id.tab2);
//	        
	        TabSpec tabAdd = tabHost.newTabSpec("Contact");
	        tabAdd.setIndicator(getTabIndicator(tabHost.getContext(), R.string.textTabTitle1, drawable.add_contact));
	        Intent intent5 = new Intent().setClass(this, AddContact.class);
	        //intent4.putExtra("id_pembuat", "7");
	        tabAdd.setContent(intent5);
	        tabHost.addTab(tabAdd);
	        
	    
	        TabSpec tabHome = tabHost.newTabSpec("home");
	        tabHome.setIndicator(getTabIndicator(tabHost.getContext(), R.string.textTabTitle2, drawable.icon_people));
	        Intent intent4 = new Intent().setClass(this, Grid.class);
	        intent4.putExtra("id_pembuat", "8");
	        tabHome.setContent(intent4);
	        tabHost.addTab(tabHome);
	        	 }	

	 

	 public void switchTab(int tab){
         tabHost.setCurrentTab(tab);
	 }
	 public void switchTabInActivity(int indexTabToSwitchTo){
         Contact parentActivity;
         parentActivity = (Contact) this.getParent();
         parentActivity.switchTab(indexTabToSwitchTo);
}
	 private TabSpec createTab(TabHost tabHost, String tag, String label, Class<?> intent) {
		 
		 	        TabSpec tab = tabHost.newTabSpec(tag);
		 	        tab.setIndicator(label);
		 	        tab.setContent(new Intent(this, intent));
		 	        return tab;
		 	    }   
	 
	    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int icon, int contentID ){
	        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
	        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title, icon)); // new function to inject our own tab layout
	        tabSpec.setContent(contentID);
	        tabHost.addTab(tabSpec);
	    }

	    private View getTabIndicator(Context context, int title, int icon) {
	        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
	        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
	        iv.setImageResource(icon);
	        TextView tv = (TextView) view.findViewById(R.id.textViewTab);
	        tv.setText(title);
	        return view;
	    }
	    
//	  
	    @Override
	    public void onResume() {
	        super.onResume();
	        // Update your UI here.
	    }
	    
	    public TabHost getMyTabHost() { return tabHost; }
	}