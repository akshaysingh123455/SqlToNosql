package com.mysqltomongodb.akshay.DTO;


import com.mysqltomongodb.akshay.*;
import java.sql.Date;
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