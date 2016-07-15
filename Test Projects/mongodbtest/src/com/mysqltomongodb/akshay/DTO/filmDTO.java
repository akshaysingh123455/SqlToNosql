package com.mysqltomongodb.akshay.DTO;


import com.mysqltomongodb.akshay.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class filmDTO { 


	public filmDTO() {
	
	}
	private int film_id;
	private String title;
	private String description;
	private String release_year;
	private int language_id;
	private int original_language_id;
	private int rental_duration;
	private double rental_rate;
	private int length;
	private double replacement_cost;
	private String rating;
	private String special_features;
	private Timestamp last_update;


	public void setfilm_id( int film_id ) {
		this.film_id= film_id;
	}
	public void settitle( String title ) {
		this.title= title;
	}
	public void setdescription( String description ) {
		this.description= description;
	}
	public void setrelease_year( String release_year ) {
		this.release_year= release_year;
	}
	public void setlanguage_id( int language_id ) {
		this.language_id= language_id;
	}
	public void setoriginal_language_id( int original_language_id ) {
		this.original_language_id= original_language_id;
	}
	public void setrental_duration( int rental_duration ) {
		this.rental_duration= rental_duration;
	}
	public void setrental_rate( double rental_rate ) {
		this.rental_rate= rental_rate;
	}
	public void setlength( int length ) {
		this.length= length;
	}
	public void setreplacement_cost( double replacement_cost ) {
		this.replacement_cost= replacement_cost;
	}
	public void setrating( String rating ) {
		this.rating= rating;
	}
	public void setspecial_features( String special_features ) {
		this.special_features= special_features;
	}
	public void setlast_update( Timestamp last_update ) {
		this.last_update= last_update;
	}


	public int getfilm_id() { 
		return film_id;
	}
	public String gettitle() { 
		return title;
	}
	public String getdescription() { 
		return description;
	}
	public String getrelease_year() { 
		return release_year;
	}
	public int getlanguage_id() { 
		return language_id;
	}
	public int getoriginal_language_id() { 
		return original_language_id;
	}
	public int getrental_duration() { 
		return rental_duration;
	}
	public double getrental_rate() { 
		return rental_rate;
	}
	public int getlength() { 
		return length;
	}
	public double getreplacement_cost() { 
		return replacement_cost;
	}
	public String getrating() { 
		return rating;
	}
	public String getspecial_features() { 
		return special_features;
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