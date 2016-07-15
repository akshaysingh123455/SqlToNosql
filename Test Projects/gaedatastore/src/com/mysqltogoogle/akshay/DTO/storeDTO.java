package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class storeDTO { 


	public storeDTO() {
	
	}
	private int store_id;
	private int manager_staff_id;
	private int address_id;
	private Timestamp last_update;


	public void setstore_id( int store_id ) {
		this.store_id= store_id;
	}
	public void setmanager_staff_id( int manager_staff_id ) {
		this.manager_staff_id= manager_staff_id;
	}
	public void setaddress_id( int address_id ) {
		this.address_id= address_id;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getstore_id() { 
		return store_id;
	}
	public int getmanager_staff_id() { 
		return manager_staff_id;
	}
	public int getaddress_id() { 
		return address_id;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}