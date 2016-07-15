package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class addressDTO { 


	public addressDTO() {
	
	}
	private int address_id;
	private String address;
	private String address2;
	private String district;
	private int city_id;
	private String postal_code;
	private String phone;
	private Timestamp last_update;


	public void setaddress_id( int address_id ) {
		this.address_id= address_id;
	}
	public void setaddress( String address ) {
		this.address= address;
	}
	public void setaddress2( String address2 ) {
		this.address2= address2;
	}
	public void setdistrict( String district ) {
		this.district= district;
	}
	public void setcity_id( int city_id ) {
		this.city_id= city_id;
	}
	public void setpostal_code( String postal_code ) {
		this.postal_code= postal_code;
	}
	public void setphone( String phone ) {
		this.phone= phone;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getaddress_id() { 
		return address_id;
	}
	public String getaddress() { 
		return address;
	}
	public String getaddress2() { 
		return address2;
	}
	public String getdistrict() { 
		return district;
	}
	public int getcity_id() { 
		return city_id;
	}
	public String getpostal_code() { 
		return postal_code;
	}
	public String getphone() { 
		return phone;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}