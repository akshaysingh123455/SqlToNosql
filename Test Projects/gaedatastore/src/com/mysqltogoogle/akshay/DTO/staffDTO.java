package com.mysqltogoogle.akshay.DTO;


import com.mysqltogoogle.akshay.*;
import java.math.BigDecimal;import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class staffDTO { 


	public staffDTO() {
	
	}
	private int staff_id;
	private String first_name;
	private String last_name;
	private int address_id;
	private String picture;
	private String email;
	private int store_id;
	private boolean active;
	private String username;
	private String password;
	private Timestamp last_update;


	public void setstaff_id( int staff_id ) {
		this.staff_id= staff_id;
	}
	public void setfirst_name( String first_name ) {
		this.first_name= first_name;
	}
	public void setlast_name( String last_name ) {
		this.last_name= last_name;
	}
	public void setaddress_id( int address_id ) {
		this.address_id= address_id;
	}
	public void setpicture( String picture ) {
		this.picture= picture;
	}
	public void setemail( String email ) {
		this.email= email;
	}
	public void setstore_id( int store_id ) {
		this.store_id= store_id;
	}
	public void setactive( boolean active ) {
		this.active= active;
	}
	public void setusername( String username ) {
		this.username= username;
	}
	public void setpassword( String password ) {
		this.password= password;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getstaff_id() { 
		return staff_id;
	}
	public String getfirst_name() { 
		return first_name;
	}
	public String getlast_name() { 
		return last_name;
	}
	public int getaddress_id() { 
		return address_id;
	}
	public String getpicture() { 
		return picture;
	}
	public String getemail() { 
		return email;
	}
	public int getstore_id() { 
		return store_id;
	}
	public boolean getactive() { 
		return active;
	}
	public String getusername() { 
		return username;
	}
	public String getpassword() { 
		return password;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}
}