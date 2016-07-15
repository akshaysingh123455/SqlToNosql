package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class categoryDTO { 


	public categoryDTO() {
	
	}
	private int category_id;
	private String name;
	private Timestamp last_update;


	public void setcategory_id( int category_id ) {
		this.category_id= category_id;
	}
	public void setname( String name ) {
		this.name= name;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getcategory_id() { 
		return category_id;
	}
	public String getname() { 
		return name;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}