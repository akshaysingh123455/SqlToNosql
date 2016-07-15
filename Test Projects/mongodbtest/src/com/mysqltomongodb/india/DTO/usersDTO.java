package com.mysqltomongodb.india.DTO;


import com.mysqltomongodb.india.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class usersDTO { 


	public usersDTO() {
	
	}
	private int user_id;
	private String name;
	private String username;
	private String email;
	private String password;
	private Timestamp date;
	private String ip;


	public void setuser_id( int user_id ) {
		this.user_id= user_id;
	}
	public void setname( String name ) {
		this.name= name;
	}
	public void setusername( String username ) {
		this.username= username;
	}
	public void setemail( String email ) {
		this.email= email;
	}
	public void setpassword( String password ) {
		this.password= password;
	}
	public void setdate( Timestamp date ) {
		this.date= date;
	}
	public void setip( String ip ) {
		this.ip= ip;
	}


	public int getuser_id() { 
		return user_id;
	}
	public String getname() { 
		return name;
	}
	public String getusername() { 
		return username;
	}
	public String getemail() { 
		return email;
	}
	public String getpassword() { 
		return password;
	}
	public Timestamp getdate() { 
		return date;
	}
	public String getip() { 
		return ip;
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