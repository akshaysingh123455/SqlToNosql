package com.mysqltomongodb.akshay.DTO;


import com.mysqltomongodb.akshay.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class customerDTO { 


	public customerDTO() {
	
	}
	private int customer_id;
	private int store_id;
	private String first_name;
	private String last_name;
	private String email;
	private int address_id;
	private boolean active;
	private Timestamp create_date;
	private Timestamp last_update;


	public void setcustomer_id( int customer_id ) {
		this.customer_id= customer_id;
	}
	public void setstore_id( int store_id ) {
		this.store_id= store_id;
	}
	public void setfirst_name( String first_name ) {
		this.first_name= first_name;
	}
	public void setlast_name( String last_name ) {
		this.last_name= last_name;
	}
	public void setemail( String email ) {
		this.email= email;
	}
	public void setaddress_id( int address_id ) {
		this.address_id= address_id;
	}
	public void setactive( boolean active ) {
		this.active= active;
	}
	public void setcreate_date( Timestamp create_date ) {
		this.create_date= create_date;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getcustomer_id() { 
		return customer_id;
	}
	public int getstore_id() { 
		return store_id;
	}
	public String getfirst_name() { 
		return first_name;
	}
	public String getlast_name() { 
		return last_name;
	}
	public String getemail() { 
		return email;
	}
	public int getaddress_id() { 
		return address_id;
	}
	public boolean getactive() { 
		return active;
	}
	public Timestamp getcreate_date() { 
		return create_date;
	}
	public Timestamp getlast_update() { 
		return last_update;
	}



	private int count;
	private double max;
	private double min;
	private double avg;
	private double sum;
	private double sqrt;
	private String nullVaue="";
	ArrayList<String> concatValues = new ArrayList<String>();
	public void setcount(int count){
		this.count = count;
	}
	public void setmax(double max){
		this.max  =max;
	}
	public void setmin(double min){
		this.min = min;
	}
	public void setavg(double avg){
		this.avg = avg;
	}
	public void setsum(double sum){
		this.sum = sum;
	}
	public void setsqrt(double sqrt){
		this.sqrt = sqrt;
	}
	public void setconcatValues(String value){
		this.concatValues.add(value);
	}
	public int getcount(){
		return this.count;
	}
	public double getmax(){
		return this.max;
	}
	public double getmin(){
		return this.min;
	}
	public double getavg(){
		return this.avg;
	}
	public double getsum(){
		return this.sum;
	}
	public double getsqrt(){
		return this.sqrt;
	}
	public ArrayList<String> getconcatValue(){
		return this.concatValues;
	}
	public String getnullValue(){
		return this.nullVaue;
	}}