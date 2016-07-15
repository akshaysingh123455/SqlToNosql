package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class film_textDTO { 


	public film_textDTO() {
	
	}
	private int film_id;
	private String title;
	private String description;


	public void setfilm_id( int film_id ) {
		this.film_id= film_id;
	}
	public void settitle( String title ) {
		this.title= title;
	}
	public void setdescription( String description ) {
		this.description= description;
	}


	public int getfilm_id() { 
		return film_id;
	}
	public String gettitle() { 
		return title;
	}
	public String getdescription() { 
		return description;
	}
}