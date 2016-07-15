package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class cityDTO { 


	public cityDTO() {
	
	}
	private int city_id;
	private String city;
	private int country_id;
	private Timestamp last_update;


	public void setcity_id( int city_id ) {
		this.city_id= city_id;
	}
	public void setcity( String city ) {
		this.city= city;
	}
	public void setcountry_id( int country_id ) {
		this.country_id= country_id;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getcity_id() { 
		return city_id;
	}
	public String getcity() { 
		return city;
	}
	public int getcountry_id() { 
		return country_id;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}