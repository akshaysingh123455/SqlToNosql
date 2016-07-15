package com.mysqltomongodb.akshay.DTO;


import com.mysqltomongodb.akshay.*;
import java.sql.Date;
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