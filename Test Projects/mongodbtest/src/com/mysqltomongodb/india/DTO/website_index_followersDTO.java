package com.mysqltomongodb.india.DTO;


import com.mysqltomongodb.india.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class website_index_followersDTO { 


	public website_index_followersDTO() {
	
	}
	private long website_index_followers_id;
	private long website_id;
	private int user_id;


	public void setwebsite_index_followers_id( long website_index_followers_id ) {
		this.website_index_followers_id= website_index_followers_id;
	}
	public void setwebsite_id( long website_id ) {
		this.website_id= website_id;
	}
	public void setuser_id( int user_id ) {
		this.user_id= user_id;
	}


	public long getwebsite_index_followers_id() { 
		return website_index_followers_id;
	}
	public long getwebsite_id() { 
		return website_id;
	}
	public int getuser_id() { 
		return user_id;
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