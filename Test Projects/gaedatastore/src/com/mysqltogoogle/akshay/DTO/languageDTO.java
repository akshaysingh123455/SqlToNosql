package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class languageDTO { 


	public languageDTO() {
	
	}
	private int language_id;
	private String name;
	private Timestamp last_update;


	public void setlanguage_id( int language_id ) {
		this.language_id= language_id;
	}
	public void setname( String name ) {
		this.name= name;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getlanguage_id() { 
		return language_id;
	}
	public String getname() { 
		return name;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}