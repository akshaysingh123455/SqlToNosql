package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class rentalDTO { 


	public rentalDTO() {
	
	}
	private int rental_id;
	private Timestamp rental_date;
	private long inventory_id;
	private int customer_id;
	private Timestamp return_date;
	private int staff_id;
	private Timestamp last_update;


	public void setrental_id( int rental_id ) {
		this.rental_id= rental_id;
	}
	public void setrental_date( Timestamp rental_date ) {
		this.rental_date= rental_date;
	}
	public void setinventory_id( long inventory_id ) {
		this.inventory_id= inventory_id;
	}
	public void setcustomer_id( int customer_id ) {
		this.customer_id= customer_id;
	}
	public void setreturn_date( Timestamp return_date ) {
		this.return_date= return_date;
	}
	public void setstaff_id( int staff_id ) {
		this.staff_id= staff_id;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getrental_id() { 
		return rental_id;
	}
	public Timestamp getrental_date() { 
		return rental_date;
	}
	public long getinventory_id() { 
		return inventory_id;
	}
	public int getcustomer_id() { 
		return customer_id;
	}
	public Timestamp getreturn_date() { 
		return return_date;
	}
	public int getstaff_id() { 
		return staff_id;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}