package com.example.gawat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;


import com.database.gawat.DataDiri;
import com.database.gawat.DataRs;
import com.database.gawat.RegisterModel;
import com.database.gawat.db_gawat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity implements OnItemSelectedListener {

	static String KEY_ANIM = "TARGET_ANIM";
	static String Target_Translate = "Translate";
	static String Target_Alpha = "Alpha";
	static String Target_Scale = "Scale";
	static String Target_Rotate = "Rotate";
	String target_op = Target_Translate; // dummy default

	final Context context = this;
	DataDiri datadiri;
	DataRs datars;

	ArrayList<RegisterModel> dataReg = new ArrayList<RegisterModel>();
	db_gawat db;

	ArrayAdapter<String> adapter;
	ListView lv;
	ArrayList<String> arrSt = new ArrayList<String>();
	ImageButton help;
	private String[] daftarnama;

	String[] names = { "ambulance", "pemadam kebakaran", "tim sar" };

	// String
	String notelp, alamat;

	// listview help
	Integer img[] = { R.drawable.pemkeb, R.drawable.ambulance_icon,
			R.drawable.pemkeb };

	// listview notif
	Integer imgNotif[] = { R.drawable.pemkeb, R.drawable.ambulance_icon,
			R.drawable.pemkeb };

	// login
	Button btnLogin;
	Button login;
	Button register;

	// spinner
	Spinner spinner_namaRS;
	ArrayAdapter<String> adapter_namaRS;
	int spinner_position;

	// String namaRS [];
	String[] namaRS = { "RSU HKBP Balige", "RS.Harapan", "RS.Horas Insani",
			"RSU Porsea", "RS.Tentara", "RSU.Parapat", "RSU.Pematang Siantar",
			"RS.Vita Insani" };
	String[] teleponRS = { "63221043", "622211622", "433444", "63241084",
			"62221071", "62541332", "62223824", "62222520" };
	String[] alamatRS = { "Jalan Gereja No.17 Balige", "Jalan Farel Pasaribu",
			"Jalan Medan Km.25Pem", "Jalan Raja Sipakko N",
			"Jalan Gn Simanuk Man", "Jalan Kolonel Tpr Sisingamaangaraja",
			"Jalan Sutomo No.3", "Jalan Merdeka No.3" };

	// Textview
	TextView rs_alamat, rs_notelp, tvDetail;

	// EditText
	EditText jlh_ambu, rs_email;

	// Linear Layout
	LinearLayout linRsHide;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// listview help
		db = new db_gawat(this);
		datadiri = new DataDiri();
		datars = new DataRs();
		// namaRS.add("RS Horas Insani");
		// namaRS.add("Jalan Medan Km.25Pem");
		// namaRS.add("H");
		// namaRS.add("jj");
		// namaRS.add("hk");
		// namaRS.add("gh");
		// namaRS.add("g");
		// namaRS.add("h");

		// teleponRS.add("433444");
		// teleponRS.add("12");
		// teleponRS.add("13");
		// teleponRS.add("14");
		// teleponRS.add("15");
		// teleponRS.add("16");
		// teleponRS.add("17");
		// teleponRS.add("18");
		//
		// alamatRS.add("Jalan Medan Km.25Pem");
		// alamatRS.add("Jalan Medan Km.25Pem");
		// alamatRS.add("Jalan Medan Km.25Pem");
		// alamatRS.add("Jalan Medan Km.25Pem");
		// alamatRS.add("Jalan Medan Km.25Pem");
		// alamatRS.add("Jalan Medan Km.25Pem");
		// alamatRS.add("Jalan Medan Km.25Pem");
		// alamatRS.add("Jalan Medan Km.25Pem");

		// jenis pertolongan
		// names.add("ambulance");
		// names.add("Pemadam Kebakaran");
		// names.add("Tim Sar");

		final Animation animTranslate = AnimationUtils.loadAnimation(this,
				R.anim.anim_translate);
		final Animation animAlpha = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		final Animation animHelp = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		final Animation animRotate = AnimationUtils.loadAnimation(this,
				R.anim.anim_rotate);

		ImageButton btnNotif = (ImageButton) findViewById(R.id.notif);
		ImageButton btnContact = (ImageButton) findViewById(R.id.contact);
		ImageButton btnHelp = (ImageButton) findViewById(R.id.help);
		ImageButton btnRotate = (ImageButton) findViewById(R.id.rotate);

		btnNotif.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				target_op = Target_Translate;
				arg0.startAnimation(animAlpha);
			}
		});

		btnContact.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				target_op = Target_Alpha;
				arg0.startAnimation(animAlpha);
			}
		});

		btnHelp.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				target_op = Target_Scale;
				arg0.startAnimation(animHelp);
				// Toast.makeText(getBaseContext(), "clicked",
				// Toast.LENGTH_SHORT).show();

			}
		});

		btnRotate.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				target_op = Target_Rotate;
				arg0.startAnimation(animAlpha);
			}
		});

		animTranslate.setAnimationListener(animationListener);
		animAlpha.setAnimationListener(animationListener);
		animHelp.setAnimationListener(animationListenerHelp);
		animRotate.setAnimationListener(animationListener);

		// login
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				popUpLogin();
			}
		});

		// register
		register = (Button) findViewById(R.id.register);
		register.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				popUpRegister();
			}
		});
	}

	AnimationListener animationListenerHelp = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			alertListRs(names);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}
	};

	AnimationListener animationListener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			Intent intent = new Intent(MainActivity.this, Contact.class);
			intent.putExtra(KEY_ANIM, target_op);
			startActivity(intent);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}
	};

	public void popUpLogin() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				MainActivity.this);
		LayoutInflater inflater = getLayoutInflater();

		View convertView = (View) inflater.inflate(R.layout.activity_login,
				null);
		alertDialog.setView(convertView);
		final AlertDialog show = alertDialog.show();

		final Button btnLogin = (Button) convertView
				.findViewById(R.id.btn_login);
		Button Login = (Button) findViewById(R.id.login);
		final EditText editlogingrup = (EditText) convertView
				.findViewById(R.id.editlogingrup);
		dataReg = db.getSemuaDataRegister();
		btnLogin.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				for (RegisterModel dd : dataReg) {
					if (editlogingrup.getText().toString().equals(dd.getkode_reg().toString())) {
						Intent intent3 = new Intent(MainActivity.this,RSMainActivity.class);
						intent3.putExtra("kode_rand_rs", dd.getkode_reg().toString());
						startActivity(intent3);
						login.setVisibility(View.INVISIBLE);

						show.dismiss();
						Toast.makeText(
								getBaseContext(),"Anda Berhasil Masuk ke Aplikasi E-Help"
										+ dd.getkode_reg(), Toast.LENGTH_SHORT)
								.show();
					} else {

					}
				}

			}
		});
	}

	private boolean isValidEmaillId(String email) {

		return Pattern
				.compile(
						"^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
								+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
								+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
								+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
								+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
								+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
				.matcher(email).matches();
	}

	public void popUpRegister() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				MainActivity.this);
		LayoutInflater inflater = getLayoutInflater();

		final View convertView = (View) inflater.inflate(R.layout.main, null);
		alertDialog.setView(convertView);
		final AlertDialog dialog = alertDialog.show();

		// spinner golongan darah
		spinner_namaRS = (Spinner) convertView.findViewById(R.id.spinNamaRS);
		rs_alamat = (TextView) convertView.findViewById(R.id.tvalamatReg);
		rs_notelp = (TextView) convertView.findViewById(R.id.tvnotelpReg);
		rs_email = (EditText) convertView.findViewById(R.id.etEmailRSReg);
		jlh_ambu = (EditText) convertView.findViewById(R.id.etJlhAmbulance);
		linRsHide = (LinearLayout) convertView.findViewById(R.id.linRsHide);
		//tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
		
		spinner_namaRS.setOnItemSelectedListener(this);
		adapter_namaRS = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, namaRS);
		adapter_namaRS
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner_namaRS.setAdapter(adapter_namaRS);

		spinner_position = adapter_namaRS.getPosition(datars.getnama_rs());
		spinner_namaRS.setSelection(spinner_position);
		
		LinearLayout addImage = (LinearLayout) convertView
				.findViewById(R.id.button_register);
		for (RegisterModel dd : dataReg) {
			if (spinner_namaRS.getSelectedItem().toString().equals(dd.getnamars_reg())) {

				Toast.makeText(getBaseContext(),
						"Anda gagalll Masuk ke Aplikasi E-Help",
						Toast.LENGTH_SHORT).show();
			} else {
				addImage.setVisibility(View.INVISIBLE);
			}
		}
		
		
		addImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				if(validasi()==true)
				{
					
				GMailSender mailsender = new GMailSender(
						"sartikasarihasibuan@gmail.com", "(*sartika*)");

				EditText to = (EditText) convertView
						.findViewById(R.id.etEmailRSReg);
				
				for(int i=0; i<=namaRS.length-1; i++)
				{
					if(spinner_namaRS.getSelectedItem().toString().equals(namaRS[i]))
					{
						notelp = teleponRS[i];
						alamat = alamatRS[i];
						linRsHide.setVisibility(View.VISIBLE);
						rs_notelp.setText(notelp);
						rs_alamat.setText(alamat);
					}
				}
				
//				Toast.makeText(MainActivity.this,
//						"nama: "+spinner_namaRS.getSelectedItem().toString()
//						+",telp: "+notelp
//						+",alamat: "+alamat,
//						Toast.LENGTH_LONG).show();
				
				String[] toArr = { rs_email.getText().toString() };
				mailsender.set_to(toArr);
				mailsender.set_from("sartikasarihasibuan@gmail.com");
				mailsender.set_subject("Verifikasi Akun Aplikasi E-Help");
				String idpeg = GetIDPegawai();
				String random = random();
				mailsender
						.setBody("Selamat anda berhasil terdaftar sebagai petugas Rumah Sakit pada Aplikasi E-Help. Masukkan Kode berikut "
								+ random + " pada form Login");

				try {
					// mailsender.addAttachment("/sdcard/filelocation");

					if (mailsender.send()) {
						Toast.makeText(MainActivity.this,
								"Email was sent successfully.",
								Toast.LENGTH_LONG).show();
						
						db.InsertRegister(spinner_namaRS.getSelectedItem()
								.toString(), random, idpeg, notelp, alamat, jlh_ambu.getText().toString(),"Available" );
						dialog.dismiss();
						Log.e("random dan id", spinner_namaRS.getSelectedItem()
								.toString()+random+idpeg+notelp+ alamat+jlh_ambu.getText().toString());

					} else {
						Toast.makeText(MainActivity.this,
								"Email was not sent.", Toast.LENGTH_LONG)
								.show();
					}
				} catch (Exception e) {

					Log.e("MailApp", "Could not send email", e);
				}
			}
			}
		});

	}
	// Get Device ID
	public String getUniqueID() {
		String AndroidDeviceId = "";
		TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		if (mTelephony.getDeviceId() != null) {
			AndroidDeviceId = mTelephony.getDeviceId();
		} else {
			AndroidDeviceId = Secure.getString(getApplicationContext()
					.getContentResolver(), Secure.ANDROID_ID);
		}
		return AndroidDeviceId;
	}

	public String GetIDPegawai() {
		String pegawai_id;
		Integer current_id = db.GetSeq();
		pegawai_id = getUniqueID() + "_" + (current_id + 1);
		// update tabel seq
		db.UpdateSeq("" + current_id, "" + (current_id + 1));
		return pegawai_id;

	}

	public static String random() {
		int MAX_LENGTH = 20;
		Random generator = new Random();
		StringBuilder randomStringBuilder = new StringBuilder();
		int randomLength = generator.nextInt(MAX_LENGTH);
		char tempChar;
		for (int i = 0; i < 7; i++) {
			tempChar = (char) (generator.nextInt(96) + 32);
			randomStringBuilder.append(tempChar);
		}
		return randomStringBuilder.toString();
	}

	public void alertImage(String[] input) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				MainActivity.this);
		LayoutInflater inflater = getLayoutInflater();

		View convertView = (View) inflater
				.inflate(R.layout.custom_dialog, null);
		alertDialog.setView(convertView);
		alertDialog.show();
		CustomListKeluhan adap = new CustomListKeluhan(this, input);

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

				// String iniId = db.SearchChild(str).getChild_id()
				// .toString();

				// Toast.makeText(getApplicationContext(), ""+str,
				// Toast.LENGTH_LONG).show();

				// Intent intent3 = new Intent(MainActivity.this,
				// GridKontak.class);
				// //intent3.putExtra("id_anak_k", child_id);
				// startActivity(intent3);
				alertListRs(names);
				// i.putExtra("id_child", "" + iniId);

			}
		});

	}

	public void alertListRs(String[] input) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				MainActivity.this);
		LayoutInflater inflater = getLayoutInflater();

		View convertView = (View) inflater
				.inflate(R.layout.custom_dialog, null);
		alertDialog.setView(convertView);
		alertDialog.show();
		CustomListKeluhan adap = new CustomListKeluhan(this, input);

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

				// String iniId = db.SearchChild(str).getChild_id()
				// .toString();

				// Toast.makeText(getApplicationContext(), ""+str,
				// Toast.LENGTH_LONG).show();

				Intent intent3 = new Intent(MainActivity.this, GridKontak.class);
				// intent3.putExtra("id_anak_k", child_id);
				startActivity(intent3);
				// i.putExtra("id_child", "" + iniId);

			}
		});

	}

	public Boolean validasi() {
		Boolean a = false;
		if (!isValidEmaillId(rs_email.getText().toString().trim())) {

			Toast.makeText(getApplicationContext(), "Alamat Email Tidak Valid",
					Toast.LENGTH_SHORT).show();
			a = false;
		} else if (rs_email.getText().toString().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Alamat Email Tidak Boleh Kosong", Toast.LENGTH_SHORT)
					.show();
			a = false;
		} else if (jlh_ambu.getText().toString().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Jumlah Ambulance Tidak Boleh Kosong", Toast.LENGTH_SHORT)
					.show();
			a = false;
		}

		else {
			a = true;
		}
		return a;

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