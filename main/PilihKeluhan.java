package com.example.gawat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.database.gawat.DataKeluhan;
import com.database.gawat.RegisterModel;
import com.database.gawat.db_gawat;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PilihKeluhan extends Activity {

	private static final int Image_take = 1;
	ImageButton addKeluhan;
	final Context context = this;
	GPSTracker gps;
//	selected keluhan
	String msg = ""; 
	ArrayList<String> selectedItems = new ArrayList<String>(); 
	ArrayList<Integer> selList = new ArrayList();
	
	//database and model
	DataKeluhan datakeluhan;
	RegisterModel registermodel;
	db_gawat db;
	
	//integer
	int count;
	
	//Linear Layout to save keluhan
	LinearLayout lin_button_simpan;
	
	ImageView image, btn_back, foto_kejadian1, foto_kejadian2, foto_kejadian3,foto_kejadian4;
	Button btn_foto_keluhan1, btn_foto_keluhan2, btn_foto_keluhan3,btn_foto_keluhan4;
	Button btn_pilih;
	Bitmap bitmap1, thumbnail, thumbnail1;
	Boolean foto1 = false, foto2 = false, foto3 = false,foto4 = false;
	
	//Integer
	int banyak;
	
	//double
	double latitude;
	double longitude;
	
	//Araylist
	ArrayList<RegisterModel> idpeg= new ArrayList<RegisterModel>();
	
	//TextView
	TextView kel1;
	TextView kel2;
	TextView kel3,viewNamaRS,viewAlamatRS,viewJarakRS,tvKel1;
	
	//EditText
	EditText etKelLain;
	// String
		String picturePath, Path, PathNull;
		String filePath;
		String path_gambar = "";
		
//		String[] namaRS = {"RSU HKBP Balige","RS.Harapan","RS.Horas Insani","RSU Porsea", "RS.Tentara", "RSU.Parapat", "RSU.Pematang Siantar","RS.Vita Insani"};
//		String[] jarak = {"2km","2km","2km","2km","2km","2km","2km","2km"};
//		String[] alamatRS = {"Jalan Gereja No.17 Balige","Jalan Farel Pasaribu","Jalan Medan Km.25Pem","Jalan Raja Sipakko N","Jalan Gn Simanuk Man","Jalan Kolonel Tpr Sisingamaangaraja","Jalan Sutomo No.3","Jalan Merdeka No.3"};
//		
		
		String[] namaRS = {"RSU HKBP Balige","RS.Harapan","RS.Horas Insani"};
		String[] jarak = {"800 m","820 m","850 m"};
		String[] alamatRS = {"Jalan Gereja No.17 Balige","Jalan Farel Pasaribu No.23","Jalan Raja Sipakko No.10"};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pilih_keluhan);
		
		//database and model
		datakeluhan = new DataKeluhan();
		registermodel = new RegisterModel();
		db = new db_gawat(this);
		
		
		PathNull = "kosong";
		if (picturePath != null) {
		     path_gambar = picturePath;
		}else if (filePath != null) {
		     path_gambar = filePath;
		} else if (PathNull != null) {
			path_gambar = PathNull;
		    }
		
		//choose keluhan with alert dialog
		ImageButton btnAddKeluhan = (ImageButton)findViewById(R.id.btnAddKeluhan);
		btn_foto_keluhan1 = (Button) findViewById(R.id.btn_foto_rumah1);
		btn_foto_keluhan2 = (Button) findViewById(R.id.btn_foto_rumah2);
		btn_foto_keluhan3 = (Button) findViewById(R.id.btn_foto_keluhan3);
		btn_foto_keluhan4 = (Button) findViewById(R.id.btn_foto_keluhan4);
		btn_pilih = (Button) findViewById(R.id.btn_pilih);
		
		//Edittext
		etKelLain = (EditText) findViewById(R.id.etKelLain);
		//TextView
		viewNamaRS = (TextView) findViewById(R.id.viewNamaRS);
		viewAlamatRS = (TextView) findViewById(R.id.viewAlamatRS);
		viewJarakRS = (TextView) findViewById(R.id.viewJarakRS);
		//tvKel1 = (TextView) findViewById(R.id.tvKel1);
		
		image = (ImageView) findViewById(R.id.image);

		foto_kejadian1 = (ImageView) findViewById(R.id.foto_kejadian1);
		foto_kejadian2 = (ImageView) findViewById(R.id.foto_kejadian2);
		foto_kejadian3 = (ImageView) findViewById(R.id.foto_kejadian3);
		foto_kejadian4 = (ImageView) findViewById(R.id.foto_kejadian4);
		
		
		//to choose rumah sakit
//		 CustomListNamaRS adap = new CustomListNamaRS(this, namaRS,alamatRS,jarak);
//		  
//		  ListView lv = (ListView) findViewById(R.id.listviewNotif);
//		  lv.setAdapter(adap);
		btn_pilih.setOnClickListener(new Button.OnClickListener(){

			   @Override
			   public void onClick(View arg0) {
				   alertListRs(namaRS,alamatRS,jarak);
			   }});
		
		btnAddKeluhan.setOnClickListener(new Button.OnClickListener(){

			   @Override
			   public void onClick(View arg0) {
				   String[] keluhan = {"MELAHIRKAN","PENDAHARAN","ASMA"};
				   pilihArv(keluhan,"Pilih Keluhan");
			   }});
		
		//after save the keluhan and then display the keluhan that just created
		lin_button_simpan = (LinearLayout)findViewById(R.id.button_simpan);
		final String  id_keluhan = GetIDKeluhan();

		final String date_now = GetTimeNow();
		final String longt =""+longitude;
		final String lat =""+latitude;
		
		Intent intent = getIntent();
		final String id_grid = intent.getStringExtra("id_grid");
		
		lin_button_simpan.setOnClickListener(new Button.OnClickListener(){

			   @Override
			   public void onClick(View arg0) {
				   count++;
				   String selectedRS = viewNamaRS.getText().toString();
				   String kode_random = db.getKodeNumber(selectedRS);
				   
				   
				   ArrayList<String> id_peg = new ArrayList<String>();
				   
				  
				   
				   idpeg=db.getSemuaDataRegister();
				   String longi = null,lat=null;
					for(int y=0;y<=idpeg.size()-1;y++)
					{
						if(idpeg.get(y).getkode_reg().equals(kode_random.toString()))
						{
							String gh = idpeg.get(y).getnamars_reg();
							
//String[] namaRS = {"RSU HKBP Balige","RS.Harapan","RS.Horas Insani","RSU Porsea", "RS.Tentara", "RSU.Parapat", "RSU.Pematang Siantar","RS.Vita Insani"};
							
							if(gh.equals("RSU HKBP Balige"))
							{
								longi="99.066860";
								lat="2.330470";
							}
							else if(gh.equals("RS.Vita Insani"))
							{
								longi="99.158069";
								lat="2.435193";
							}
							else if(gh.equals("RSU.Pematang Siantar"))
							{
								longi="99.158069";
								lat="2.435193";
							}
							else if(gh.equals("RSU.Parapat"))
							{
								longi="99.158069";
								lat="2.435193";
							}
							else if(gh.equals("RS.Tentara"))
							{
								longi="99.158069";
								lat="2.435193";
							}
							else if(gh.equals("RSU Porsea"))
							{
								longi="99.158069";
								lat="2.435193";
							}
							else if(gh.equals("RS.Harapan"))
							{
								longi="99.065732";
								lat="2.942602";
							}
							else if(gh.equals("RS.Horas Insani"))
							{
								longi="99.079520";
								lat="2.977900";
							}
						String keluhan = null;
						 String keluhan1 =kel1.getText().toString();
						 String keluhan2=kel2.getText().toString();
						 String keluhan3=kel3.getText().toString();
						 String keluhan4=etKelLain.getText().toString();
						 
						 keluhan = keluhan1+"\n"+keluhan2+"\n"+keluhan3+"\n"+keluhan4;
//					
						 
						 db.InsertKeluhan(id_keluhan, "Ambulance", id_grid.toString(), "8", idpeg.get(y).getnamars_reg(), keluhan.trim().concat("\n"), "filePath", longi, lat, "status_keluhan_", "1",date_now, idpeg.get(y).getid_pegawai());
						   Log.e(
								     "iniiiiiiiiiiiiiiiiiiiii"+id_keluhan+ "Ambulance"+ id_grid.toString()+ "8"+ "koders"+ kel1.getText().toString()+"\n"+kel2.getText().toString()+ "filePath"+ longi+ lat+ "status_keluhan_"+ "1"+date_now+","+idpeg.get(y).getid_pegawai(), gh);
						   
						}
						}
					
				   
				   Intent Keluhan = new Intent(getApplication(),Keluhan.class);
				   Keluhan.putExtra("id_keluhan", id_keluhan);
				   Keluhan.putExtra("id_grid", id_grid);
				   Keluhan.putExtra("date_now",date_now);
				   //Keluhan.putExtra("count", count);
					startActivity(Keluhan);
					
					
			   }});
		
		/***********************************FOTO KEJADIAN***************************************/
		bitmap1 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.new_button_camera);
		foto_kejadian1.setImageBitmap(bitmap1);
		foto_kejadian2.setImageBitmap(bitmap1);
		foto_kejadian3.setImageBitmap(bitmap1);
		foto_kejadian4.setImageBitmap(bitmap1);
		btn_foto_keluhan1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (getDrawableImage(foto_kejadian1) == bitmap1) {

					image();
					foto1 = true;
					// foto_anak1.setImageBitmap(thumbnail);
				} else {
					alertImage(foto_kejadian1);
				}
			}
		});
		btn_foto_keluhan2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (getDrawableImage(foto_kejadian2) == bitmap1) {

					image();
					foto2 = true;
					// foto_anak1.setImageBitmap(thumbnail);
				} else {
					alertImage(foto_kejadian2);
				}
			}
		});
		btn_foto_keluhan3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (getDrawableImage(foto_kejadian3) == bitmap1) {

					image();
					foto3 = true;
					// foto_anak1.setImageBitmap(thumbnail);
				} else {
					alertImage(foto_kejadian3);
				}
			}
		});
		btn_foto_keluhan4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (getDrawableImage(foto_kejadian4) == bitmap1) {

					image();
					foto4 = true;
					// foto_anak1.setImageBitmap(thumbnail);
				} else {
					alertImage(foto_kejadian4);
				}
			}
		});
	}
	
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  if (resultCode == RESULT_OK) {
	   if (requestCode == 1) {
//	       Uri selectedImageUri = data.getData();
//	       String selectedImagePath = getRealPathFromURI(selectedImageUri);
	    
	    
	             
	    thumbnail = (Bitmap) data.getExtras().get("data");
	    thumbnail = Bitmap
	      .createScaledBitmap(thumbnail, 132, 105, true);
	    thumbnail1 = (Bitmap) data.getExtras().get("data");
	    thumbnail1 = Bitmap.createScaledBitmap(thumbnail1, 258, 150,
	      true);
	    //
	    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
	    thumbnail1.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

	    File destination = new File(
	      Environment.getExternalStorageDirectory(),
	      System.currentTimeMillis() + ".jpg");
	    FileOutputStream fo;
	    
	    //Uri selectedImage = data.getData();

	             String[] filePathColumn = {MediaStore.Images.Media.DATA};
	             //Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
	             final String imageOrderBy = MediaStore.Images.Media._ID
	         + " DESC";
	             Cursor cursor = managedQuery(
	            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	         filePathColumn, null, null, imageOrderBy);
	             
	             cursor.moveToFirst();
	             int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	             filePath = cursor.getString(columnIndex);
	             Log.v("log","filePath is : "+filePath); 
	             
//	    Toast.makeText(getApplicationContext(),
//	       "yuhuuuuu " + filePath, Toast.LENGTH_LONG).show();
//

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

	    // looping imageview to set image bitmap
	    ImageView ivrumah1, ivrumah2, ivrumah3,ivrumah4;

	    ivrumah1 = (ImageView) findViewById(R.id.foto_kejadian1);
	    ivrumah2 = (ImageView) findViewById(R.id.foto_kejadian2);
	    ivrumah3 = (ImageView) findViewById(R.id.foto_kejadian3);
	    ivrumah4 = (ImageView) findViewById(R.id.foto_kejadian4);

	    if (getDrawableImage(ivrumah1) == bitmap1 && foto1 == true) {

	     ivrumah1.setImageBitmap(thumbnail);
	    }

	    if (getDrawableImage(ivrumah2) == bitmap1 && foto2 == true) {
	     ivrumah2.setImageBitmap(thumbnail);
	    }

	    if (getDrawableImage(ivrumah3) == bitmap1 && foto3 == true) {
	     ivrumah3.setImageBitmap(thumbnail);
	    }
	    if (getDrawableImage(ivrumah3) == bitmap1 && foto4 == true) {
		     ivrumah4.setImageBitmap(thumbnail);
		    }
	   }
	  }
	 }
	 
	 

	public void image() {
		  // create class object
		  gps = new GPSTracker(PilihKeluhan.this);

		  // check if GPS enabled
		  if (gps.canGetLocation()) {

		   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		   startActivityForResult(intent, 1);

		   // }
		   // else if (gps.canGetLocation())
		   // {
		   latitude = gps.getLatitude();
		   longitude = gps.getLongitude();

		   // \n is for new line
		   Toast.makeText(
		     getApplicationContext(),
		     "Lokasi Anda - \nLatitude : " + latitude + "\nLongitude : "
		       + longitude, Toast.LENGTH_LONG).show();

		  }

		  else {
		   gps.showSettingsAlert();
		  }
		 }
	
	
	 public void alertImage(final ImageView input) {
		  final Dialog dialog = new Dialog(context);

		  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  dialog.setContentView(R.layout.custom_dialog_image);

		  final Dialog dialogConfirm = new Dialog(context);

		  dialogConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE);

		  ImageView imageDialog = (ImageView) dialog.findViewById(R.id.image);
		  imageDialog.setImageBitmap(getDrawableImage(input).createScaledBitmap(
		    getDrawableImage(input), 258, 150, true));
		  LinearLayout linHapus = (LinearLayout) dialog
		    .findViewById(R.id.btnHapus);

		  linHapus.setOnClickListener(new OnClickListener() {

		   public void onClick(View v) {
		    // TODO Auto-generated method stub

		    dialogConfirm.setContentView(R.layout.confirm_delete);

		    LinearLayout linYakin = (LinearLayout) dialogConfirm
		      .findViewById(R.id.btnYakinHapus);
		    LinearLayout linKembali = (LinearLayout) dialogConfirm
		      .findViewById(R.id.button_kembali);

		    linYakin.setOnClickListener(new OnClickListener() {

		     public void onClick(View v) {
		      // TODO Auto-generated method stub
		      input.setImageBitmap(bitmap1);
		      foto1 = false;
		      foto2 = false;
		      foto3 = false;
		      foto4 = false;

		      dialogConfirm.dismiss();
		      dialog.dismiss();
		     }
		    });
		    linKembali.setOnClickListener(new OnClickListener() {

		     public void onClick(View v) {

		      dialogConfirm.dismiss();
		      dialog.dismiss();
		     }
		    });
		    dialogConfirm.show();
		   }
		  });
		  // input.setImageBitmap(thumbnail);
		  dialog.show();
		 }
	 
	 Bitmap bitmap56;
	 public Bitmap getDrawableImage(ImageView input) {
		  input.buildDrawingCache(true);
		  Bitmap bitmap = input.getDrawingCache(true);

		  BitmapDrawable drawable = (BitmapDrawable) input.getDrawable();
		  bitmap56 = drawable.getBitmap();

		  return bitmap56;
		 }
	 
	private void pilihArv(final String[] arv, String Title) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(Title);
		builder.setMultiChoiceItems(arv, null,new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1,
							boolean arg2) {
						// TODO Auto-generated method stub

						if (arg2) {
							selList.add(arg1);
							banyak = selList.size();
							selectedItems.add(arv[arg1]);

						} else if (selList.contains(arg1)) {
							// if the item is already selected then remove it
							selList.remove(Integer.valueOf(arg1));
						}
						
						if (banyak >= 4) {
							//alert
							Toast.makeText(context,"Anda Tidak Dapat Memilih Keluhan Lebih Dari 3 ",Toast.LENGTH_LONG).show();
							((AlertDialog) arg0).getListView().setItemChecked(arg1, false);
							banyak--;
							// ((AlertDialog)arg0).getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.INVISIBLE);
						}

					}
				});

		builder.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.setPositiveButton("SIMPAN",new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						msg = "";
						for (int i = 0; i < selList.size(); i++) {
							msg = msg + arv[selList.get(i)] + "\n";
						}
						
						String msg_list[] = msg.split("\n");
						int lngth = msg_list.length;
						
						 kel1 = (TextView) findViewById(R.id.tvKel1);
						 kel2 = (TextView) findViewById(R.id.tvKel2);
						 kel3 = (TextView) findViewById(R.id.tvKel3);
						if (banyak == 1) {
							kel1.setVisibility(View.VISIBLE);
							kel1.setText(msg_list[0]);
						}
						else if (banyak == 2) {
							kel1.setVisibility(View.VISIBLE);
							kel2.setVisibility(View.VISIBLE);
							kel1.setText(msg_list[0]);
							kel2.setText(msg_list[1]);
						}
						else{
							kel1.setVisibility(View.VISIBLE);
							kel2.setVisibility(View.VISIBLE);
							kel3.setVisibility(View.VISIBLE);
							kel1.setText(msg_list[0]);
							kel2.setText(msg_list[1]);
							kel3.setText(msg_list[2]);
						}
						
					}
				});

		AlertDialog alert = builder.create();
		alert.show();

		Button bq_pos = alert.getButton(DialogInterface.BUTTON_POSITIVE);
		Button bq_neg = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
		bq_pos.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
		bq_pos.setTextColor(Color.WHITE);
		bq_neg.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
		bq_neg.setTextColor(Color.WHITE);

	}
	
	public void alertListRs(String[] namaRS,String[] alamat,String[] jarak) {
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				PilihKeluhan.this);
		
	
		LayoutInflater inflater = getLayoutInflater();

		View convertView = (View) inflater
				.inflate(R.layout.custom_dialog, null);
		alertDialog.setView(convertView);
		final AlertDialog dialog = alertDialog.show();
		
		CustomListNamaRS adap = new CustomListNamaRS(this, namaRS,alamat,jarak);

		final ListView lv = (ListView) convertView
				.findViewById(R.id.listviewHelp);
		lv.setAdapter(adap);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {

				String id_child;
				// Child_Model model_anak = new Child_Model();

				// TODO Auto-generated method stub
				final View selectedView = arg1;
				String str = lv.getItemAtPosition(arg2).toString();
				String str1 = lv.getItemAtPosition(arg2).toString();
				String str2 = lv.getItemAtPosition(arg2).toString();
				
				TextView textview1 = (TextView) arg1.findViewById(R.id.TVnamaRSRekom);
			    TextView textview2 = (TextView) arg1.findViewById(R.id.TVAlamatRSRekom);
			    TextView textview3 = (TextView) arg1.findViewById(R.id.TVJarakRSRekom);
			    
			   
				viewNamaRS.setVisibility(View.VISIBLE);
				viewNamaRS.setText(textview1.getText().toString());
				viewAlamatRS.setVisibility(View.VISIBLE);
				viewAlamatRS.setText(textview2.getText().toString());
				viewJarakRS.setVisibility(View.VISIBLE);
				viewJarakRS.setText(textview3.getText().toString());
				btn_pilih.setVisibility(View.INVISIBLE);
				dialog.dismiss();
				

			}
		});
		
		
	}
		
	
	
	public String GetTimeNow() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(c.getTime());
	}

	
	//getIDKeluhan
	public String GetIDKeluhan()
	{
		String keluhan_id;
		Integer current_id = db.GetSeq_Keluhan();
		keluhan_id ="Kel"+current_id+1;
		//update tabel seq
		db.UpdateSeq_Keluhan(""+current_id, ""+(current_id+1));
		return keluhan_id;
		
	}

	  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pilih_keluhan, menu);
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
