package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class inventoryDTO { 


	public inventoryDTO() {
	
	}
	private long inventory_id;
	private int film_id;
	private int store_id;
	private Timestamp last_update;


	public void setinventory_id( long inventory_id ) {
		this.inventory_id= inventory_id;
	}
	public void setfilm_id( int film_id ) {
		this.film_id= film_id;
	}
	public void setstore_id( int store_id ) {
		this.store_id= store_id;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public long getinventory_id() { 
		return inventory_id;
	}
	public int getfilm_id() { 
		return film_id;
	}
	public int getstore_id() { 
		return store_id;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}