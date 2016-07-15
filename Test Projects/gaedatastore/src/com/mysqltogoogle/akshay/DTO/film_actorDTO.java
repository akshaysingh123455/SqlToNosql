package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class film_actorDTO { 


	public film_actorDTO() {
	
	}
	private int actor_id;
	private int film_id;
	private Timestamp last_update;


	public void setactor_id( int actor_id ) {
		this.actor_id= actor_id;
	}
	public void setfilm_id( int film_id ) {
		this.film_id= film_id;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getactor_id() { 
		return actor_id;
	}
	public int getfilm_id() { 
		return film_id;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}