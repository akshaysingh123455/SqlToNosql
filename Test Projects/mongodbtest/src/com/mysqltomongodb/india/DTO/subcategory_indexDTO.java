package com.mysqltomongodb.india.DTO;


import com.mysqltomongodb.india.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class subcategory_indexDTO { 


	public subcategory_indexDTO() {
	
	}
	private int subcategory_index_id;
	private int category_id;
	private String subcategory_name;


	public void setsubcategory_index_id( int subcategory_index_id ) {
		this.subcategory_index_id= subcategory_index_id;
	}
	public void setcategory_id( int category_id ) {
		this.category_id= category_id;
	}
	public void setsubcategory_name( String subcategory_name ) {
		this.subcategory_name= subcategory_name;
	}


	public int getsubcategory_index_id() { 
		return subcategory_index_id;
	}
	public int getcategory_id() { 
		return category_id;
	}
	public String getsubcategory_name() { 
		return subcategory_name;
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