package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class actorDTO { 


	public actorDTO() {
	
	}
	private int actor_id;
	private String first_name;
	private String last_name;
	private Timestamp last_update;


	public void setactor_id( int actor_id ) {
		this.actor_id= actor_id;
	}
	public void setfirst_name( String first_name ) {
		this.first_name= first_name;
	}
	public void setlast_name( String last_name ) {
		this.last_name= last_name;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getactor_id() { 
		return actor_id;
	}
	public String getfirst_name() { 
		return first_name;
	}
	public String getlast_name() { 
		return last_name;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}