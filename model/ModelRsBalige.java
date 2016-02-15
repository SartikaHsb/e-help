package com.model.gawat;

public class ModelRsBalige {
	private  String nama_rs="RSU HKBP Balige";
	private String rs_no_telp="63221043";
	private  String rs_alamat="Jalan Gereja No.17 Balige";
	private  String rs_longitude;
	private  String rs_latitude;
	private  String jumlah_ambulance ;
	
	public String getnama_rs()
	{
		return this.nama_rs;
	}
	
	public String getrs_no_telp()
	{
		return this.rs_no_telp;
	}
	
	public String getrs_alamat()
	{
		return this.rs_alamat;
	}
	public String getrs_longitude()
	{
		return this.rs_longitude;
	}
	public String getrs_latitude()
	{
		return this.rs_latitude;
	}
	public String getjumlah_ambulance()
	{
		return this.jumlah_ambulance;
	}
	
	
	//setter
	public void setnama_rs()
	{
		this.nama_rs =nama_rs;
	}
	
	public void setrs_no_telp()
	{
		this.rs_no_telp =rs_no_telp;
	}
	
	public void setrs_alamat()
	{
		this.rs_alamat=rs_alamat;
	}
	public void setrs_longitude()
	{
		this.rs_longitude=rs_longitude;
	}
	public void setrs_latitude()
	{
		this.rs_latitude=rs_latitude;
	}
	public void setjumlah_ambulance(String jumlah_ambulance)
	{
		this.jumlah_ambulance=jumlah_ambulance;
	}
	
	
	 
}
