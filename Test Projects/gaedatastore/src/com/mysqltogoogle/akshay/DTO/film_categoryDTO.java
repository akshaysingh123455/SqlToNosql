package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class film_categoryDTO { 


	public film_categoryDTO() {
	
	}
	private int film_id;
	private int category_id;
	private Timestamp last_update;


	public void setfilm_id( int film_id ) {
		this.film_id= film_id;
	}
	public void setcategory_id( int category_id ) {
		this.category_id= category_id;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getfilm_id() { 
		return film_id;
	}
	public int getcategory_id() { 
		return category_id;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}