package com.mysqltomongodb.india.DTO;


import com.mysqltomongodb.india.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class social_link_typeDTO { 


	public social_link_typeDTO() {
	
	}
	private int social_link_type_id;
	private String link_name;
	private String link;


	public void setsocial_link_type_id( int social_link_type_id ) {
		this.social_link_type_id= social_link_type_id;
	}
	public void setlink_name( String link_name ) {
		this.link_name= link_name;
	}
	public void setlink( String link ) {
		this.link= link;
	}


	public int getsocial_link_type_id() { 
		return social_link_type_id;
	}
	public String getlink_name() { 
		return link_name;
	}
	public String getlink() { 
		return link;
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