package com.database.gawat;

import java.util.ArrayList;
import java.util.Collections;

import com.database.gawat.DataDiri;
import com.example.gawat.Register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class db_gawat  extends SQLiteOpenHelper{
	private static final String nama_database = "db_urgent.db";
	private static final int versi_database = 1;
	
	/********************SEQUENCE*****************************/
	private static final String SEQ = "seq";
	
	private static final String seq_number = "seq_num";
	
	/********************SEQUENCE1*****************************/
	private static final String SEQ_KELUHAN = "seq_keluhan";
	
	private static final String seq_number_k = "seq_num_k";
	
	/************* TB DATA DIRI *****************/
	private static final String data_diri = "data_diri";
	
	private static final String id_orang = "id_orang";
	private static final String id_pembuat = "id_pembuat";
	private static final String nama_orang = "nama_orang";
	private static final String tempat_lahir = "tempat_lahir";
	private static final String tanggal_lahir = "tanggal_lahir";
	private static final String jenis_kelamin ="jenis_kelamin";
	private static final String golongan_darah = "golongan_darah";
	private static final String no_telp = "no_telp";
	private static final String wa = "wa";
	private static final String bbm = "bbm";
	private static final String alamat = "alamat";
	private static final String penyakit_id = "penyakit_id";
	private static final String foto = "foto";
	private static final String waktu = "waktu";
	private static final String status = "status";
	
	/****************TB KELUHAN ****************/
	private static final String keluhan = "keluhan";
	
	private static final String id_keluhan= "id_keluhan";
	private static final String jenis_bantuan = "jenis_bantuan";
	private static final String fk_id_korban = "fk_id_orang";
	private static final String id_pelapor = "id_pelapor";
	private static final String fk_kode_rs = "fk_kode_rs";
	private static final String nama_keluhan = "nama_keluhan";
	private static final String foto_keluhan = "foto_keluhan";
	private static final String longitude = "longitude";
	private static final String latitude = "latitude";
	private static final String status_keluhan = "status_keluhan";
	private static final String jumlah_korban = "jumlah_korban";
	private static final String waktu_kejadian = "waktu_kejadian";
	private static final String idpegawai = "idpegawai";
	
	
	/***************TB PENYAKIT ***************/
	private static final String penyakit = "penyakit";
	
	private static final String id_penyakit = "id_penyakit";
	private static final String nama_penyakit = "nama_penyakit";
	
	/***************TB Kode Random ***************/
	private static final String kode_random = "kode_random";
	private static final String device_id = "device_id";
	
	
	
//	/************** TB RUMAH SAKIT **************/
//	private static final String RUMAH_SAKIT = "rumah_sakit";
//	
//	private static final String nama_rs = "nama_rs";
//	private static final String kode_rs = "kode_rs";
//	private static final String rs_no_telp = "rs_no_telp";
//	private static final String rs_email= "rs_email";
//	private static final String rs_alamat= "rs_alamat";
//	private static final String rs_longitude = "longitude";
//	private static final String rs_latitude= "rs_latitude";
//	private static final String jumlah_ambulance = "jumlah_ambulance";
//	private static final String status_ambulance = "status_ambulance";
	
	/************** TB RUMAH REGISTER **************/
	private static final String register = "register";
	private static final String namars_reg= "namars_reg";
	private static final String kode_reg = "kode_reg";
	private static final String id_pegawai = "id_pegawai";
	private static final String rs_no_telp = "rs_no_telp";
	private static final String rs_alamat= "rs_alamat";
	private static final String jumlah_ambulance = "jumlah_ambulance";
	private static final String status_ambulance = "status_ambulance";
	
	
	/*****************PETUGAS************************/
	private static final String petugas = "petugas";
	
	private static final String email = "email";
	private static final String password= "password";
	
	/************** TB ALARM****************/
	private static final String alarm = "alarm";
	/*************** TB NOTIFICATION *************/
	private static final String notification = "notification";
	
	
	/**************************CREATE TABLE**************************************/
	String TABLE_SEQ = "CREATE TABLE " + SEQ  + "("
            + seq_number  + " TEXT PRIMARY KEY  )";
	
	
	
	/**************************CREATE TABLE**************************************/
	String TABLE_SEQ_K = "CREATE TABLE " + SEQ_KELUHAN  + "("
            + seq_number_k  + " TEXT PRIMARY KEY  )";
	
	String CREATE_TABLE_DATADIRI = "CREATE TABLE " + data_diri  + "("
		    + id_orang  + " TEXT PRIMARY KEY," 
			+ id_pembuat  + " TEXT,"
		    + nama_orang  + " TEXT, " 
			+ tempat_lahir  + " TEXT," 
		    + tanggal_lahir  + " TEXT, " 
		    + jenis_kelamin + " TEXT, "
			+ golongan_darah  + " TEXT,"
			+ no_telp  + " TEXT,"
		    + wa  + " TEXT, " 
			+ bbm  + " TEXT," 
		    + alamat  + " TEXT, " 
			+ penyakit_id  + " TEXT,"
			+ foto  + " TEXT,"
			+ waktu  + " TEXT,"
		    + status + " TEXT" + ")";
	
	String CREATE_TABLE_KELUHAN = "CREATE TABLE " + keluhan  + "("
		    + id_keluhan  + " TEXT PRIMARY KEY," 
			+ jenis_bantuan  + " TEXT,"
		    + fk_id_korban  + " TEXT, " 
			+ id_pelapor  + " TEXT," 
		    + fk_kode_rs  + " TEXT, " 
			+ nama_keluhan  + " TEXT,"
			+ foto_keluhan  + " TEXT,"
		    + longitude  + " TEXT, " 
			+ latitude  + " TEXT," 
		    + status_keluhan  + " TEXT, " 
			+ jumlah_korban  + " TEXT,"
			+ waktu_kejadian  + " TEXT,"
		    + idpegawai + " TEXT" + ")";
	
	String CREATE_TABLE_PENYAKIT = "CREATE TABLE " + penyakit  + "("
		    + id_penyakit   + " TEXT PRIMARY KEY," 
			+ nama_penyakit   + " TEXT"+ ")";
	
//	String CREATE_TABLE_RS = "CREATE TABLE " + RUMAH_SAKIT  + "("
//			+ nama_rs  + " TEXT PRIMARY KEY,"
//			+ kode_rs  + " TEXT," 
//			+ rs_no_telp   + " TEXT,"
//		    + rs_email  + " TEXT, " 
//		    + rs_alamat  + " TEXT, " 
//			+ rs_longitude   + " TEXT," 
//		    + rs_latitude  + " TEXT, " 
//			+ jumlah_ambulance   + " TEXT,"
//			+ status_ambulance   + " TEXT" + ")";
	
	String CREATE_TABLE_PETUGAS = "CREATE TABLE " + petugas+ "("
		    + email   + " TEXT PRIMARY KEY," 
			+ password+ " TEXT"+ ")";
	
	String CREATE_TABLE_REGISTER = "CREATE TABLE " + register+ "("
		    + namars_reg  + " TEXT," 
		     + kode_reg  + " TEXT," 
		     + id_pegawai+ " TEXT PRIMARY KEY,"
		     + rs_no_telp+ " TEXT,"
		     + rs_alamat+ " TEXT,"
		     + jumlah_ambulance+ " TEXT,"
			+ status_ambulance+ " TEXT"+ ")";
	
	/**..............................................................**/
	
	public db_gawat(Context context) {
		super(context, nama_database, null, versi_database);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			Log.e("OnCreate ", " ");
			db.beginTransaction();
			
			
			
			db.execSQL(TABLE_SEQ);
			db.execSQL(TABLE_SEQ_K);
			db.execSQL(CREATE_TABLE_DATADIRI);
			db.execSQL(CREATE_TABLE_KELUHAN);
			db.execSQL(CREATE_TABLE_PENYAKIT);
			//db.execSQL(CREATE_TABLE_RS);
			db.execSQL(CREATE_TABLE_PETUGAS);
			db.execSQL(CREATE_TABLE_REGISTER);
			
//			db.execSQL();
			ContentValues values = new ContentValues();
			values.put(seq_number, "0");
			db.insert(SEQ, null, values);
			Log.e("execute seq", "");
			values.clear();
			
			ContentValues values_k = new ContentValues();
			values_k.put(seq_number_k, "0");
			db.insert(SEQ_KELUHAN, null, values_k);
			Log.e("execute seq_k", "");
			values_k.clear();
			
			db.setTransactionSuccessful(); 
		} catch (SQLException se) {
			Log.v(" Oncreate SQLException",
					Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" Oncreate Exception",
					Log.getStackTraceString(e));
		}finally {
            db.endTransaction();
    }
	
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEQ);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEQ_K);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_DATADIRI);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_KELUHAN);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PENYAKIT);
			//db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_RS);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PETUGAS);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_REGISTER);
		} 
		catch (SQLException se) {
			Log.v(" onUpgrade SQLException",Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v(" onUpgrade Exception",Log.getStackTraceString(e));
		}
	}
	
	/************************************************************************************************************/
	/**************************************-CRUD OPERATION-******************************************************/
	/************************************************************************************************************/
	//---------------------------------------SEQUENCE----------------------------------------------------------//
		public void UpdateSeq(String current_num, String update_num)
		{
			Log.e("UpdateSeq", "");
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(seq_number, update_num);
			try{
				db.update(SEQ, values, "seq_num=" + current_num, null);
			}catch (SQLiteException se) {
					 Log.v(" UpdateSeq Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
					Log.v(" UpdateSeq Exception",Log.getStackTraceString(e));
			} finally {
					db.close();
			}
		}
		
		public Integer GetSeq()
		{
			Log.e("execute seq", "");
			String result="";
			SQLiteDatabase db = this.getReadableDatabase();
			try{
				Cursor mCursor = db.rawQuery(
						"SELECT seq_num FROM  SEQ ", null);
				if (mCursor != null) {
					mCursor.moveToFirst();
					result = mCursor.getString(0);
				}
				mCursor.close();
			}catch (SQLiteException se) {
				 Log.v("GetSeq",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v("GetSeq",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			int result_int = Integer.parseInt(result);
			return result_int;
			
		}
		
		public void UpdateSeq_Keluhan(String current_num, String update_num)
		{
			Log.e("UpdateSeq", "");
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(seq_number_k, update_num);
			try{
				db.update(SEQ_KELUHAN, values, "seq_num_k=" + current_num, null);
			}catch (SQLiteException se) {
					 Log.v(" UpdateSeq Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
					Log.v(" UpdateSeq Exception",Log.getStackTraceString(e));
			} finally {
					db.close();
			}
		}
		
		public Integer GetSeq_Keluhan()
		{
			String result="";
			SQLiteDatabase db = this.getReadableDatabase();
			try{
				Cursor mCursor = db.rawQuery(
						"SELECT seq_num_k FROM  SEQ_KELUHAN ", null);
				if (mCursor != null) {
					mCursor.moveToFirst();
					result = mCursor.getString(0);
				}
				mCursor.close();
			}catch (SQLiteException se) {
				 Log.v("GetSeq Keluhan",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v("GetSeq Keluhan",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			int result_int = Integer.parseInt(result);
			return result_int;
			
		}
		
		/**************************data diri *****************************/
		public void InsertDataDiri(String id_org, String id_pbt, String nama_org, String tempat_lhr,
				String tgl_lhr, String jen_kel, String golda, String notelp,
				String wa_, String bbm_, String alamat_, String penyakit_id_, 
				String foto_, String waktu_, String status_) {
			 SQLiteDatabase db = this.getWritableDatabase();
			  try {
					ContentValues values = new ContentValues();
					values.put(id_orang  , id_org);
					values.put(id_pembuat  , id_pbt);
					values.put(nama_orang  , nama_org);
					values.put(tempat_lahir  , tempat_lhr);
					values.put(tanggal_lahir   , tgl_lhr);
					values.put(jenis_kelamin   , jen_kel);
					values.put(golongan_darah   , golda);
					values.put(no_telp   , notelp);
					values.put(wa   , wa_);
					values.put(bbm   , bbm_);
					values.put(alamat   , alamat_);
					values.put(penyakit_id   , penyakit_id_);
					values.put(foto   , foto_);
					values.put(waktu    , waktu_);
					values.put(status    , status_);
					
					// menambahkan nama tabel bila tidak akan error
					// db.delete(NAMA_TABEL, null, null);
					db.insert(data_diri , null, values);
					Log.v("Insert tabel data diri", "id_org"+id_org+ "id_pembuat"+id_pbt+" nama orang"+nama_org+" tmpt lahir tgl lahir golda notelp bbm wa alamat penyakit_id foto waktu");
					db.close();
			  }
			  catch (SQLiteException se) {
					 Log.v("InsertDataDiri",Log.getStackTraceString(se));
			  } catch (Exception e) {
					Log.v("InsertDataDiri",Log.getStackTraceString(e));
			  } finally {
					db.close();
			  }
		}
		
		
		public Cursor getFriends(String id)

		{

		Cursor cursor = getReadableDatabase().rawQuery("select * from data_diri where id_orang = '"
												+ id + "'", null);

		return cursor;

		}
		
		// get caregiver
		public String getName(String id) {
			SQLiteDatabase db = this.getReadableDatabase();
			String name = "";
			try{
				Cursor mCursor = db.rawQuery(
						"SELECT  nama_orang  FROM  data_diri WHERE id_pembuat= '"
								+ id + "'", null);
				if (mCursor != null) {
					mCursor.moveToFirst();
					name = mCursor.getString(mCursor.getColumnIndex(nama_orang));
				}
				mCursor.close();
			}catch (SQLiteException se) {
				 Log.v("getNameCaregiver",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v("getNameCaregiver",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			return name;
		}
		
		public ArrayList<DataDiri> GetAllData(String id)
		{
			ArrayList<DataDiri> arr = new ArrayList<DataDiri>();
			SQLiteDatabase db = this.getReadableDatabase();
			try{
				Cursor mCursor = db.rawQuery(
						"SELECT *  FROM  data_diri WHERE id_pembuat= '"+id+"'", null);
				if (mCursor != null) {
					mCursor.moveToFirst();
					while (!mCursor.isAfterLast()) {
						
						arr.add(parseDataDiri(mCursor));
						mCursor.moveToNext();
					}
				}
				mCursor.close();
			}catch (SQLiteException se) {
				 Log.v(" GetAllData Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" GetAllData Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			return arr;
			
		}
		
		public void UpdateDataDiri(String id_org, String id_pbt, String nama_org, String tempat_lhr,
				String tgl_lhr,String jen_kel, String golda, String notelp,
				String wa_, String bbm_, String alamat_, String penyakit_id_, 
				String foto_, String waktu_, String status_)
		{
			Log.e("update data diri", "");
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(id_pembuat  , id_pbt);
			values.put(nama_orang  , nama_org);
			values.put(tempat_lahir  , tempat_lhr);
			values.put(tanggal_lahir   , tgl_lhr);
			values.put(jenis_kelamin   , jen_kel);
			values.put(golongan_darah   , golda);
			values.put(no_telp   , notelp);
			values.put(wa   , wa_);
			values.put(bbm   , bbm_);
			values.put(alamat   , alamat_);
			values.put(penyakit_id   , penyakit_id_);
			values.put(foto   , foto_);
			values.put(waktu    , waktu_);
			values.put(status    , status_);
			try{
				db.update(data_diri, values, "id_orang = ?", new String[]{id_org});
			}catch (SQLiteException se) {
				 Log.v(" UpdateDataDiri Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" UpdateDataDiri Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
		}
		
		
		
		
		
		private DataDiri parseDataDiri(Cursor c) {
			DataDiri model = new DataDiri();
			model.setIdOrang(c.getString(0));
			model.setIdPembuat(c.getString(1));
			model.setNama(c.getString(2));
			model.setTmpLahir(c.getString(3));
			model.setTglLahir(c.getString(4));
			model.setJenKel(c.getString(5));
			model.setGolda(c.getString(6));
			model.setNotelp(c.getString(7));
			model.setWa(c.getString(8));
			model.setBBM(c.getString(9));
			model.setAlamat(c.getString(10));
			model.setPenyakitId(c.getString(11));
			model.setFoto(c.getString(12));
			model.setWaktu(c.getString(13));
			model.setStatus(c.getString(14));
			return model;
		}
		
		private RegisterModel parseDataRegister(Cursor c) {
			RegisterModel modelreg = new RegisterModel();
			modelreg.setnamars_reg(c.getString(0));
			modelreg.setkode_reg(c.getString(1));
			modelreg.setid_pegawai(c.getString(2));
			modelreg.setrs_no_telp(c.getString(3));
			modelreg.setrs_alamat(c.getString(4));
			modelreg.setjumlah_ambulance(c.getString(5));
			modelreg.setstatus_ambulance(c.getString(6));
			
			return modelreg;
		}
		
		private DataRs parseDataRs(Cursor c) {
			DataRs modelRs = new DataRs();
			modelRs.setnama_rs(c.getString(0));
			modelRs.setkode_rs(c.getString(1));
			modelRs.setrs_no_telp(c.getString(2));
			modelRs.setrs_email(c.getString(3));
			modelRs.setrs_alamat(c.getString(4));
			modelRs.setrs_longitude(c.getString(5));
			modelRs.setrs_latitude(c.getString(6));
			modelRs.setjumlah_ambulance(c.getString(7));
			modelRs.setstatus_ambulance(c.getString(8));
			
			return modelRs;
		}
		
		private DataKeluhan parseDataKeluhan(Cursor c) {
			DataKeluhan modelKeluhan = new DataKeluhan();
			modelKeluhan.setIdKeluhan(c.getString(0));
			modelKeluhan.setJenisBantuan(c.getString(1));
			modelKeluhan.setFkIdOrang(c.getString(2));
			modelKeluhan.setId_pelapor(c.getString(3));
			modelKeluhan.setFk_kode_rs(c.getString(4));
			modelKeluhan.setNamaKeluhan(c.getString(5));
			modelKeluhan.setFotoKeluhan(c.getString(6));
			modelKeluhan.setLongitude(c.getString(7));
			modelKeluhan.setLatitude(c.getString(8));
			modelKeluhan.setStatusKeluhan(c.getString(9));
			modelKeluhan.setJumlahKorban(c.getString(10));
			modelKeluhan.setWaktuKejadian(c.getString(11));
			modelKeluhan.setIdPegawai(c.getString(12));
			return modelKeluhan;
		}
		
		// ambil semua data dari table
				public ArrayList<DataDiri> getSemuaDataPasien() {
					Log.e("Get Semua Data Anak", "");
					SQLiteDatabase db = this.getReadableDatabase();
					Cursor cursor;
					ArrayList<DataDiri> allData = new ArrayList<DataDiri>();
					cursor = null;
					try {
						cursor = db.query(data_diri, new String[] {
								id_orang ,
								id_pembuat ,
								nama_orang ,
								tempat_lahir ,
								tanggal_lahir ,
								jenis_kelamin,
								golongan_darah ,
								no_telp ,
								wa,
								bbm,
								alamat ,
								penyakit_id ,
								foto,
								waktu, 
								status}, null, null, null, null, null);
			
						cursor.moveToFirst();
						while (!cursor.isAfterLast()) {
							allData.add(parseDataDiri(cursor));
							cursor.moveToNext();
					}

					cursor.close();
					}catch (SQLiteException se) {
						 Log.v(" getSemuaDataPasien Exception",Log.getStackTraceString(se));
					} catch (Exception e) {
						Log.v(" getSemuaDataPasien Exception",Log.getStackTraceString(e));
					} finally {
						db.close();
					}
					return allData;
				}
		
		
				
		//get all
		 public ArrayList<DataDiri> getAllDataDiri() {
			 ArrayList<DataDiri> all =  new ArrayList<DataDiri>();
			    SQLiteDatabase db = this.getReadableDatabase();
			    try {
				     Cursor mCursor = db.rawQuery(
				       "SELECT  * FROM  data_diri WHERE id_pembuat = 2", null);
				     
				     mCursor.moveToFirst();
				     while (!mCursor.isAfterLast()) {
				      all.add(parseDataDiri(mCursor));
				      mCursor.moveToNext();
				     }
				     mCursor.close();
				     
				     
			    } catch (Exception e) {
			    	e.printStackTrace();
			    	Log.e("Failed getAllDataDiri", e.toString());
			    }finally{
			    	db.close();
			    }
			    return all;
		 }
		 
		 public DataDiri ViewDataDiri(String id) {
				DataDiri model = new DataDiri();
				SQLiteDatabase db = this.getReadableDatabase();
				Cursor cursor;
				try{
					
					 String Query ="SELECT * FROM data_diri where id_orang = '"
													+ id + "'";
					 cursor = db.rawQuery(Query, null);
					if (cursor != null) {
						cursor.moveToFirst();
						model = parseDataDiri(cursor);
					}
				cursor.close();
				}catch (SQLiteException se) {
					 Log.v(" ViewDataDiri Exception",Log.getStackTraceString(se));
				} catch (Exception e) {
					Log.v(" ViewDataDiri Exception",Log.getStackTraceString(e));
				} finally {
					db.close();
				}
					return model;
			}
		 
		 
		
		/*-----------------------------Create Keluhan ------------------------------*/
		public void InsertKeluhan(String id_keluhan_, String jenis_bantuan_, String fk_id_korban_, String id_pelapor_,
				String fk_kode_rs_,String nama_keluhan_, String foto_keluhan_, String longitude_,
				String latitude_, String status_keluhan_, String jumlah_korban_, String waktu_kejadian_,String id_pegawai_) {
			 SQLiteDatabase db = this.getWritableDatabase();
			  try {
					ContentValues values = new ContentValues();
					values.put(id_keluhan   , id_keluhan_);
					values.put(jenis_bantuan   , jenis_bantuan_);
					values.put(fk_id_korban   , fk_id_korban_);
					values.put(id_pelapor   , id_pelapor_);
					values.put(fk_kode_rs   , fk_kode_rs_);
					values.put(nama_keluhan   , nama_keluhan_);
					values.put(foto_keluhan   , foto_keluhan_);
					values.put(longitude   , longitude_);
					values.put(latitude   , latitude_);
					values.put(status_keluhan   , status_keluhan_);
					values.put(jumlah_korban   , jumlah_korban_);
					values.put(waktu_kejadian    , waktu_kejadian_);
					values.put(idpegawai    , id_pegawai_);
					
					
					// menambahkan nama tabel bila tidak akan error
					// db.delete(NAMA_TABEL, null, null);
					db.insert(keluhan  , null, values);
					db.close();
			  }
			  catch (SQLiteException se) {
					 Log.v("InsertDataKeluhan",Log.getStackTraceString(se));
			  } catch (Exception e) {
					Log.v("InsertDataKeluhan",Log.getStackTraceString(e));
			  } finally {
					db.close();
			  }
		}
		
		public DataKeluhan ViewDataKeluhan(String id_keluhan) {
			DataKeluhan model = new DataKeluhan();
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor;
			try{
				
				 String Query ="SELECT * FROM keluhan where id_keluhan = '"
												+ id_keluhan + "'";
				 cursor = db.rawQuery(Query, null);
				if (cursor != null) {
					cursor.moveToFirst();
					model = parseDataKeluhan(cursor);
				}
			cursor.close();
			}catch (SQLiteException se) {
				 Log.v(" ViewDataDiri Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" ViewDataDiri Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
				return model;
		}
		
		public DataKeluhan ViewDataKeluh(String id_keluh) {
			DataKeluhan model = new DataKeluhan();
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor;
			try{
				
				 String Query ="SELECT * FROM keluhan where id_keluhan = '"
												+ id_keluh + "'";
				 cursor = db.rawQuery(Query, null);
				if (cursor != null) {
					cursor.moveToFirst();
					model = parseDataKeluhan(cursor);
				}
			cursor.close();
			}catch (SQLiteException se) {
				 Log.v(" ViewDataDiri Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" ViewDataDiri Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
				return model;
		}
		
		
		public String ViewIdByIdKeluhan(String caregiver) {
			SQLiteDatabase db = this.getReadableDatabase();
			String id = "";
			try{
				Cursor mCursor = db.rawQuery(
						"SELECT fk_id_orang FROM keluhan where id_keluhan = '"
						+ id_keluhan + "'", null);
				
				if (mCursor != null) {
					mCursor.moveToFirst();
					id = mCursor.getString(0);
				}
				mCursor.close();
			}catch (SQLiteException se) {
				 Log.v("getIdCaregiver",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v("getIdCaregiver",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			return id;
		}

		
		/*-----------------------------Create Register ------------------------------*/
		public void InsertRegister(String namars_reg_, String kode_reg_, String id_pegawai_,String rs_no_telp_, String rs_alamat_, String jumlah_ambulance_,  String status_ambulance_) {
			 SQLiteDatabase db = this.getWritableDatabase();
			  try {
					ContentValues values = new ContentValues();
					values.put(namars_reg, namars_reg_);
					values.put(kode_reg, kode_reg_);
					values.put(id_pegawai, id_pegawai_);
					values.put(rs_no_telp, rs_no_telp_);
					values.put(rs_alamat, rs_alamat_);
					values.put(jumlah_ambulance, jumlah_ambulance_);
					values.put(status_ambulance, status_ambulance_);
					
					db.insert(register , null, values);
					//Log.v("Insert tabel data diri", "id_org"+id_org+ "id_pembuat"+id_pbt+" nama orang"+nama_org+" tmpt lahir tgl lahir golda notelp bbm wa alamat penyakit_id foto waktu");
					db.close();
			  }
			  catch (SQLiteException se) {
					 Log.v("InsertDataRegister",Log.getStackTraceString(se));
			  } catch (Exception e) {
					Log.v("InsertDataRegister",Log.getStackTraceString(e));
			  } finally {
					db.close();
			  }
		}
		
		public ArrayList<RegisterModel> getSemuaDataRegister() {
			Log.e("Get Semua Data Register", "");
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor;
			ArrayList<RegisterModel> allDataReg = new ArrayList<RegisterModel>();
			cursor = null;
			try {
				cursor = db.query(register, new String[] {
						namars_reg ,
						kode_reg ,
						id_pegawai,
						rs_no_telp,
						rs_alamat,
						jumlah_ambulance,
						status_ambulance}, null, null, null, null, null);
	
				cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					allDataReg.add(parseDataRegister(cursor));
					cursor.moveToNext();
			}

			cursor.close();
			}catch (SQLiteException se) {
				 Log.v(" getSemuaDataReg Exception",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v(" getSemuaDataReg Exception",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			return allDataReg;
		}

		public String getKodeNumber(String namaRS) {
			SQLiteDatabase db = this.getReadableDatabase();
			String id = "";
			try{
				Cursor mCursor = db.rawQuery(
						"SELECT kode_reg FROM register where namars_reg ='"
						+ namaRS + "'", null);
				
				if (mCursor != null) {
					mCursor.moveToFirst();
					id = mCursor.getString(0);
				}
				mCursor.close();
			}catch (SQLiteException se) {
				 Log.v("getIdCaregiver",Log.getStackTraceString(se));
			} catch (Exception e) {
				Log.v("getIdCaregiver",Log.getStackTraceString(e));
			} finally {
				db.close();
			}
			return id;
		}
		
		public ArrayList<String> getAllIdPegawaiByKode(String kode_random) {
		    ArrayList<String> district =  new ArrayList<String>();
		    SQLiteDatabase db = this.getReadableDatabase();
		    try {
			     Cursor mCursor = db.rawQuery(
			       "SELECT id_pegawai  FROM  register WHERE kode_reg='"+ kode_random +"'", null);
			     
			     mCursor.moveToFirst();
			     while (!mCursor.isAfterLast()) {
			      district.add(mCursor.getString(mCursor.getColumnIndex(id_pegawai)));
			      mCursor.moveToNext();
			     }
			     mCursor.close();
			     
			     
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	Log.e("Failed to find all name district", e.toString());
		    }finally{
		    	db.close();
		    }
		    return district;
		 }
		
		
//		public void InsertDataRS(String nama_rs_, String kode_rs_, String rs_no_telp_, String rs_email_,
//				String rs_alamat_, String rs_longitude_, String rs_latitude_, String jumlah_ambulance_,
//				String status_ambulance_) {
//			 SQLiteDatabase db = this.getWritableDatabase();
//			  try {
//					ContentValues values = new ContentValues();
//					values.put(nama_rs, nama_rs_);
//					values.put(kode_rs  , kode_rs_);
//					values.put(rs_no_telp  , rs_no_telp_);
//					values.put(rs_email  , rs_email_);
//					values.put(rs_alamat , rs_alamat_);
//					values.put(rs_longitude   , rs_longitude_);
//					values.put(rs_latitude   , rs_latitude_);
//					values.put(jumlah_ambulance , jumlah_ambulance_);
//					values.put(status_ambulance   , status_ambulance_);
//					
//					
//					// menambahkan nama tabel bila tidak akan error
//					// db.delete(NAMA_TABEL, null, null);
//					db.insert(data_diri , null, values);
//					//Log.v("Insert tabel data diri", "id_org"+id_org+ "id_pembuat"+id_pbt+" nama orang"+nama_org+" tmpt lahir tgl lahir golda notelp bbm wa alamat penyakit_id foto waktu");
//					db.close();
//			  }
//			  catch (SQLiteException se) {
//					 Log.v("InsertDataDiri",Log.getStackTraceString(se));
//			  } catch (Exception e) {
//					Log.v("InsertDataDiri",Log.getStackTraceString(e));
//			  } finally {
//					db.close();
//			  }
//		}
//		
//		public ArrayList<DataRs> getSemuaNamaRS() {
//			Log.e("Get Semua Data Rumah Sakit", "");
//			SQLiteDatabase db = this.getReadableDatabase();
//			Cursor cursor;
//			ArrayList<DataRs> allDataRs = new ArrayList<DataRs>();
//			cursor = null;
//			try {
//				cursor = db.query(RUMAH_SAKIT, new String[] {
//						nama_rs,
//						kode_rs ,
//						rs_no_telp ,
//						rs_email ,
//						rs_alamat ,
//						rs_longitude ,
//						rs_latitude,
//						jumlah_ambulance ,
//						status_ambulance}, null, null, null, null, null);
//	
//				cursor.moveToFirst();
//				while (!cursor.isAfterLast()) {
//					allDataRs.add(parseDataRs(cursor));
//					cursor.moveToNext();
//			}
//
//			cursor.close();
//			}catch (SQLiteException se) {
//				 Log.v(" getSemuaDataRS Exception",Log.getStackTraceString(se));
//			} catch (Exception e) {
//				Log.v(" getSemuaDataRS Exception",Log.getStackTraceString(e));
//			} finally {
//				db.close();
//			}
//			return allDataRs;
//		}	
}
