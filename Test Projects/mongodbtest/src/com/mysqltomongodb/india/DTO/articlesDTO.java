package com.mysqltomongodb.india.DTO;


import com.mysqltomongodb.india.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;


public class articlesDTO { 


	public articlesDTO() {
	
	}
	private long article_id;
	private String name;
	private String link;
	private String content;
	private String thumbnail;
	private String big_image;
	private long parent_website_id;
	private Timestamp add_date;
	private int num_views;
	private int num_ups;
	private int num_downs;
	private int num_saved;
	private int num_comments;
	private int one_page_article;
	private long primary_link;
	private int tag_id;


	public void setarticle_id( long article_id ) {
		this.article_id= article_id;
	}
	public void setname( String name ) {
		this.name= name;
	}
	public void setlink( String link ) {
		this.link= link;
	}
	public void setcontent( String content ) {
		this.content= content;
	}
	public void setthumbnail( String thumbnail ) {
		this.thumbnail= thumbnail;
	}
	public void setbig_image( String big_image ) {
		this.big_image= big_image;
	}
	public void setparent_website_id( long parent_website_id ) {
		this.parent_website_id= parent_website_id;
	}
	public void setadd_date( Timestamp add_date ) {
		this.add_date= add_date;
	}
	public void setnum_views( int num_views ) {
		this.num_views= num_views;
	}
	public void setnum_ups( int num_ups ) {
		this.num_ups= num_ups;
	}
	public void setnum_downs( int num_downs ) {
		this.num_downs= num_downs;
	}
	public void setnum_saved( int num_saved ) {
		this.num_saved= num_saved;
	}
	public void setnum_comments( int num_comments ) {
		this.num_comments= num_comments;
	}
	public void setone_page_article( int one_page_article ) {
		this.one_page_article= one_page_article;
	}
	public void setprimary_link( long primary_link ) {
		this.primary_link= primary_link;
	}
	public void settag_id( int tag_id ) {
		this.tag_id= tag_id;
	}


	public long getarticle_id() { 
		return article_id;
	}
	public String getname() { 
		return name;
	}
	public String getlink() { 
		return link;
	}
	public String getcontent() { 
		return content;
	}
	public String getthumbnail() { 
		return thumbnail;
	}
	public String getbig_image() { 
		return big_image;
	}
	public long getparent_website_id() { 
		return parent_website_id;
	}
	public Timestamp getadd_date() { 
		return add_date;
	}
	public int getnum_views() { 
		return num_views;
	}
	public int getnum_ups() { 
		return num_ups;
	}
	public int getnum_downs() { 
		return num_downs;
	}
	public int getnum_saved() { 
		return num_saved;
	}
	public int getnum_comments() { 
		return num_comments;
	}
	public int getone_page_article() { 
		return one_page_article;
	}
	public long getprimary_link() { 
		return primary_link;
	}
	public int gettag_id() { 
		return tag_id;
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