package com.example.gawat;

import java.io.File;
import java.util.ArrayList;

import com.database.gawat.DataDiri;
import com.database.gawat.db_gawat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GridKontak extends Activity {
	ArrayAdapter<String> adapter;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	final Context context = this;
	GridView gridView;
	CustomGridViewAdapter customGridAdapter;
	Button orngLain;
	//GPSTracker
	GPSTracker gps;
	
	db_gawat db;
	ArrayList<DataDiri> datadiri;
	
	String id_grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_kontak);
		
		db = new db_gawat(this);
		datadiri = new ArrayList<DataDiri>();
		
		ArrayList<DataDiri> arr = new ArrayList<DataDiri>();
		arr = db.getSemuaDataPasien();
		
		orngLain = (Button) findViewById(R.id.orngLain);
		orngLain.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(GridKontak.this,
						OrangLain.class);
			  
			   startActivity(intent);
			}
		});
		// set grid view item
		for (DataDiri dd : arr) {
			String as = "kosong";
			if (dd.getIdPembuat().equals("8")) {
			if (dd.getFoto().equals(as)) {
				Bitmap bitmap1 = BitmapFactory.decodeResource(
						context.getResources(), R.drawable.icon);
				
		gridArray.add(new Item(dd.getIdOrang(), dd.getNama(),
						bitmap1.createScaledBitmap(bitmap1, 120, 120, false)));
			
			}
			else {
				File image = new File(dd.getFoto());

				BitmapFactory.Options bmOptions = new BitmapFactory.Options();

				Bitmap bitmap = BitmapFactory.decodeFile(
						image.getAbsolutePath(), bmOptions);

				bitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);

				gridArray.add(new Item(dd.getIdOrang(), dd.getNama(),
						bitmap.createScaledBitmap(bitmap, 120, 120, false)));
			}
		}
				gridView = (GridView) findViewById(R.id.gridViewKeluh);
				customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
						gridArray);
				gridView.setAdapter(customGridAdapter);

				gridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v, int position,
							long arg3) {
						id_grid = gridArray.get(position).getId();
						
image();
					}
				});
				
				}
			   
		}
	
	public void image() {
		  // create class object
		  gps = new GPSTracker(GridKontak.this);

		  // check if GPS enabled
		  if (gps.canGetLocation()) {

		   Intent intent = new Intent(GridKontak.this,
					PilihKeluhan.class);
		   intent.putExtra("id_grid",id_grid);
		   
		   startActivity(intent);

		   // }
		   // else if (gps.canGetLocation())
		   // {
		   double latitude = gps.getLatitude();
		   double longitude = gps.getLongitude();

		   // \n is for new line
		   Toast.makeText(
		     getApplicationContext(),
		     "Lokasi Anda - \nLatitude : " + latitude + "\nLongitude : "
		       + longitude, Toast.LENGTH_LONG).show();

		  }

		  else {
		  // gps.showSettingsAlert();
			  Intent intent = new Intent(GridKontak.this,
						PilihKeluhan.class);
			   startActivity(intent);

		  }
		 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid_kontak, menu);
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
