package com.mysqltomongodb.india.DTO;


import com.mysqltomongodb.india.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class folder_detailsDTO { 


	public folder_detailsDTO() {
	
	}
	private long folder_id;
	private String folder_name;
	private int owner_id;
	private long parent_folder_id;
	private int num_articles;
	private int num_followers;


	public void setfolder_id( long folder_id ) {
		this.folder_id= folder_id;
	}
	public void setfolder_name( String folder_name ) {
		this.folder_name= folder_name;
	}
	public void setowner_id( int owner_id ) {
		this.owner_id= owner_id;
	}
	public void setparent_folder_id( long parent_folder_id ) {
		this.parent_folder_id= parent_folder_id;
	}
	public void setnum_articles( int num_articles ) {
		this.num_articles= num_articles;
	}
	public void setnum_followers( int num_followers ) {
		this.num_followers= num_followers;
	}


	public long getfolder_id() { 
		return folder_id;
	}
	public String getfolder_name() { 
		return folder_name;
	}
	public int getowner_id() { 
		return owner_id;
	}
	public long getparent_folder_id() { 
		return parent_folder_id;
	}
	public int getnum_articles() { 
		return num_articles;
	}
	public int getnum_followers() { 
		return num_followers;
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