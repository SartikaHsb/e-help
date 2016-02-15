package com.example.gawat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.database.gawat.DataDiri;
import com.database.gawat.db_gawat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddContact extends Activity  implements
OnItemSelectedListener {
	//String
	String filePath,picturePath,PathNull,gender = "";
	String item_golongandarah[] = { "-", "A", "B", "O", "AB" };
	String item_status[] = { "-","Sudah Menikah", "Belum Menikah" };
	
	//spinner
	ArrayAdapter<String> adapter_golda;
	ArrayAdapter<String> adapter_status;
	Spinner spinner_golda;
	
	//radio button
	RadioGroup radio_gender_group;
	RadioButton rb_gender;
	
	//int
	int spinner_position;
	
	//Spinner
	Spinner spinner_golongandarah,spinner_status;
	
	
	//button
		LinearLayout button_simpanprofil;
		
		final Context context = this;
		
		//EditText
		EditText edit_nama_korban;
		public static TabHost tabHost;
		EditText etTempatLahir,etTanggalLahir,ettelepon,edit_Wa,edit_bbm,edit_alamat,edit_penyakit;
		//db
		db_gawat db;
		
		//imageview
		ImageView tambah_foto;
		
		//Bitmap
		Bitmap thumbnailCamera;
		
		DataDiri datadiri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		
		onResume();
		
		db = new db_gawat(this);
		datadiri = new DataDiri();
		
		button_simpanprofil = (LinearLayout) findViewById(R.id.btn_simpan_DataDiri);
        edit_nama_korban = (EditText) findViewById(R.id.edit_nama_korban);
        etTempatLahir = (EditText) findViewById(R.id.etTempatLahir);
        spinner_golongandarah = (Spinner) findViewById(R.id.spinner_golongandarah);
        spinner_status = (Spinner) findViewById(R.id.spinner_status);
        etTanggalLahir = (EditText) findViewById(R.id.etTanggalLahir);
        ettelepon = (EditText) findViewById(R.id.ettelepon);
        edit_Wa = (EditText) findViewById(R.id.edit_Wa);
        edit_bbm = (EditText) findViewById(R.id.edit_bbm);
        edit_alamat = (EditText) findViewById(R.id.edit_alamat);
        edit_penyakit = (EditText) findViewById(R.id.edit_penyakit);
        tambah_foto = (ImageView) findViewById(R.id.tambahfoto);
        etTanggalLahir = (EditText) findViewById(R.id.etTanggalLahir);
        final String date_now = GetTimeNow();
        
        //spinner golongan darah
        spinner_golda = (Spinner) findViewById(R.id.spinner_golongandarah);
		spinner_golda.setOnItemSelectedListener(this);
		adapter_golda = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, item_golongandarah);
		adapter_golda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_golda.setAdapter(adapter_golda);
        
		spinner_position = adapter_golda.getPosition(datadiri.getGolda());
		spinner_golda.setSelection(spinner_position);
		
		//spinner status
        spinner_status = (Spinner) findViewById(R.id.spinner_status);
        spinner_status.setOnItemSelectedListener(this);
        adapter_status = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, item_status);
        adapter_status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(adapter_status);
        
		spinner_position = adapter_status.getPosition(datadiri.getStatus());
		spinner_status.setSelection(spinner_position);

        button_simpanprofil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// lakukan simpan ke tabel tr visit
//				Log.e("Simpan data diri", "" + true);
//				/**********************SAVE KUNJUNGAN**************************/
//				
				String path_gambar = "";
				Intent i = getIntent();
				PathNull = "kosong";
				if (picturePath != null) {
				     path_gambar = picturePath;
				}else if (filePath != null) {
				     path_gambar = filePath;
				} else if (PathNull != null) {
					path_gambar = PathNull;
				    }
				if(validasi()==true)
				{
				db.InsertDataDiri(GetChildID(), 
						"8",
						edit_nama_korban.getText().toString() , 
						etTempatLahir.getText().toString(), 
						etTanggalLahir.getText().toString(),
						spinner_golda.getSelectedItem().toString(),
						gender,
						ettelepon.getText().toString(), 
						edit_Wa.getText().toString(), 
						edit_bbm.getText().toString(), 
						edit_alamat.getText().toString(), 
						edit_penyakit.getText().toString(),
						//tambahfoto.getText().toString(),
						path_gambar,
						date_now,
						spinner_status.getSelectedItem().toString());
				
				
				Intent intent3 = new Intent(AddContact.this,
						Grid.class);
				intent3.putExtra("id_pembuat", "8");
				startActivity(intent3);
				}
				else
				{
//				Toast.makeText(getApplicationContext(),
//						"path_gambar :"+path_gambar+" golo: "+spinner_golda.getSelectedItem().toString()+"gender: "+gender+" Status" + spinner_status.getSelectedItem().toString(),
//						Toast.LENGTH_SHORT).show();
				}
				Log.e("pathnyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa:", path_gambar);
				Bitmap bitmap1 = BitmapFactory.decodeResource(
						context.getResources(), R.drawable.tambah_foto);
				tambah_foto.setImageBitmap(bitmap1);
				
				
				
				
				
				
				
			}
		});
		

        
        //add foto
         tambah_foto = (ImageView) findViewById(R.id.tambahfotoPasien);
		tambah_foto.setOnClickListener(new View.OnClickListener() {
			   // button tambah foto
			   @Override
			   public void onClick(View v) {
			    // TODO Auto-generated method stub
			    LayoutInflater layoutinflater = LayoutInflater.from(context);
			    View promptView = layoutinflater.inflate(R.layout.activity_foto_dialog, null);

			    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			    alertDialogBuilder.setView(promptView);

			    final Button btn_kamera = (Button) promptView.findViewById(R.id.button_kamera);
			    final Button btn_galeri = (Button) promptView.findViewById(R.id.button_galeri);

			    filePath=null;
			    btn_kamera.setOnClickListener(new OnClickListener() {

			     public void onClick(View v) {
			      // TODO Auto-generated method stub
			      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			      startActivityForResult(intent, 1);
			      alertDialogBuilder.create().dismiss();
			     }
			    });

			    btn_galeri.setOnClickListener(new OnClickListener() {

			     public void onClick(View v) {
			      // TODO Auto-generated method stub
			      Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			      startActivityForResult(intent, 2);
			      alertDialogBuilder.create().dismiss();
			     }
			    });

			    AlertDialog alertD = alertDialogBuilder.create();
			    alertD.show();
			   }
			  });
		
		
		// this is for gender
		
		// checked any gender
					
				radio_gender_group = (RadioGroup) findViewById(R.id.radioGenderGroup);
				radio_gender_group.clearCheck();
				radio_gender_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(RadioGroup group, int checkedId) {
								rb_gender = (RadioButton) group.findViewById(checkedId);
								if (null != rb_gender && checkedId > -1) {
									gender = rb_gender.getText().toString();
								}
							}
						});
				
				
	}
	
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  super.onActivityResult(requestCode, resultCode, data);

		  if (resultCode == RESULT_OK) {
		   if (requestCode == 1) {

		    thumbnailCamera = (Bitmap) data.getExtras().get("data");
		    thumbnailCamera = Bitmap.createScaledBitmap(thumbnailCamera, 132, 105, true);
		    
		    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		    thumbnailCamera.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
		    
		    File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
		    FileOutputStream fo;
		    //Uri selectedImage = data.getData();

		    String[] filePathColumn = {MediaStore.Images.Media.DATA};
		    //Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
		    final String imageOrderBy = MediaStore.Images.Media._ID+ " DESC";
		    Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,filePathColumn, null, null, imageOrderBy);
		             
		    cursor.moveToFirst();
		    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		    filePath = cursor.getString(columnIndex);
		    Log.v("log","filePath is : "+filePath); 
		    
		    try {
		    	destination.createNewFile();
		    	fo = new FileOutputStream(destination);
		    	fo.write(bytes.toByteArray());
		     
		    	fo.close();
		    } catch (FileNotFoundException e) {
		    	e.printStackTrace();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }

		    tambah_foto.setImageBitmap(thumbnailCamera);

		   } else if (requestCode == 2) {

		    Uri selectedImage = data.getData();
		    String[] filePath = { MediaStore.Images.Media.DATA };
		    Cursor c = getContentResolver().query(selectedImage, filePath,null, null, null);
		    c.moveToFirst();
		    int columnIndex = c.getColumnIndex(filePath[0]);
		    picturePath = c.getString(columnIndex);
		    c.close();
		    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
		    Log.w("path of image from gallery......******************.........",picturePath + "");
		    tambah_foto.setImageBitmap(thumbnail);
		   }
		  }
		 }

	
	//Get Device ID
			public String getUniqueID(){    
			    String AndroidDeviceId = "";
			    TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			    if (mTelephony.getDeviceId() != null){
			        AndroidDeviceId = mTelephony.getDeviceId(); 
			    }else{
			         AndroidDeviceId = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID); 
			    }
			    return AndroidDeviceId;
			}
		    
		    public String GetChildID()
			{
				String child_id;
				Integer current_id = db.GetSeq();
				child_id = getUniqueID() + "_"+(current_id+1);
				//update tabel seq
				db.UpdateSeq(""+current_id, ""+(current_id+1));
				return child_id;
				
			}
		    
	
	public String GetTimeNow() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(c.getTime());
	}
	public Boolean validasi()
	{
		Boolean a = false;
		if(edit_nama_korban.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(),"Nama Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
			a=false;
		}
		else if(etTempatLahir.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(),"Tanggal Lahir Anak Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
			a=false;
		}
		else if(etTanggalLahir.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(),"Jenis Kelamin Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
			a=false;
		}
		else if(ettelepon.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(),"Nomor Telepon Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
			a=false;
		}
		else if(edit_alamat.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(),"Alamat Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
			a=false;
		}
		
		else
		{
			a= true;
		}
		return a;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
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
	
	@Override
    public void onResume() {
        super.onResume();
        // Update your UI here.
    }


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
