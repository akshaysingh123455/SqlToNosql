package com.mysqltomongodb.india.DTO;


import com.mysqltomongodb.india.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class website_indexDTO { 


	public website_indexDTO() {
	
	}
	private long site_id;
	private String name;
	private String title;
	private String link;
	private String description;
	private String site_logo;
	private int category_main_index_id;
	private int subcategory_index_id;
	private Timestamp add_date;
	private int num_ups;
	private int num_downs;
	private long views;
	private int no_articles_registered;
	private int made_in_india;
	private int num_followers;


	public void setsite_id( long site_id ) {
		this.site_id= site_id;
	}
	public void setname( String name ) {
		this.name= name;
	}
	public void settitle( String title ) {
		this.title= title;
	}
	public void setlink( String link ) {
		this.link= link;
	}
	public void setdescription( String description ) {
		this.description= description;
	}
	public void setsite_logo( String site_logo ) {
		this.site_logo= site_logo;
	}
	public void setcategory_main_index_id( int category_main_index_id ) {
		this.category_main_index_id= category_main_index_id;
	}
	public void setsubcategory_index_id( int subcategory_index_id ) {
		this.subcategory_index_id= subcategory_index_id;
	}
	public void setadd_date( Timestamp add_date ) {
		this.add_date= add_date;
	}
	public void setnum_ups( int num_ups ) {
		this.num_ups= num_ups;
	}
	public void setnum_downs( int num_downs ) {
		this.num_downs= num_downs;
	}
	public void setviews( long views ) {
		this.views= views;
	}
	public void setno_articles_registered( int no_articles_registered ) {
		this.no_articles_registered= no_articles_registered;
	}
	public void setmade_in_india( int made_in_india ) {
		this.made_in_india= made_in_india;
	}
	public void setnum_followers( int num_followers ) {
		this.num_followers= num_followers;
	}


	public long getsite_id() { 
		return site_id;
	}
	public String getname() { 
		return name;
	}
	public String gettitle() { 
		return title;
	}
	public String getlink() { 
		return link;
	}
	public String getdescription() { 
		return description;
	}
	public String getsite_logo() { 
		return site_logo;
	}
	public int getcategory_main_index_id() { 
		return category_main_index_id;
	}
	public int getsubcategory_index_id() { 
		return subcategory_index_id;
	}
	public Timestamp getadd_date() { 
		return add_date;
	}
	public int getnum_ups() { 
		return num_ups;
	}
	public int getnum_downs() { 
		return num_downs;
	}
	public long getviews() { 
		return views;
	}
	public int getno_articles_registered() { 
		return no_articles_registered;
	}
	public int getmade_in_india() { 
		return made_in_india;
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