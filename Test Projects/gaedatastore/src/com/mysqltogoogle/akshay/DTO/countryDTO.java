package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class countryDTO { 


	public countryDTO() {
	
	}
	private int country_id;
	private String country;
	private Timestamp last_update;


	public void setcountry_id( int country_id ) {
		this.country_id= country_id;
	}
	public void setcountry( String country ) {
		this.country= country;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getcountry_id() { 
		return country_id;
	}
	public String getcountry() { 
		return country;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}