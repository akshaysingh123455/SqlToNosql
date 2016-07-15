package com.mysqltomongodb.akshay.DTO;


import com.mysqltomongodb.akshay.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class paymentDTO { 


	public paymentDTO() {
	
	}
	private int payment_id;
	private int customer_id;
	private int staff_id;
	private int rental_id;
	private double amount;
	private Timestamp payment_date;
	private Timestamp last_update;


	public void setpayment_id( int payment_id ) {
		this.payment_id= payment_id;
	}
	public void setcustomer_id( int customer_id ) {
		this.customer_id= customer_id;
	}
	public void setstaff_id( int staff_id ) {
		this.staff_id= staff_id;
	}
	public void setrental_id( int rental_id ) {
		this.rental_id= rental_id;
	}
	public void setamount( double amount ) {
		this.amount= amount;
	}
	public void setpayment_date( Timestamp payment_date ) {
		this.payment_date= payment_date;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getpayment_id() { 
		return payment_id;
	}
	public int getcustomer_id() { 
		return customer_id;
	}
	public int getstaff_id() { 
		return staff_id;
	}
	public int getrental_id() { 
		return rental_id;
	}
	public double getamount() { 
		return amount;
	}
	public Timestamp getpayment_date() { 
		return payment_date;
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