package com.sqltomongodb.additionals;

import java.io.File;

/**
 * 
 * @author Akshay P. Singh <akshayps@iitrpr.ac.in>
 *
 */
public class BasicOperation {
	public BasicOperation(){

	}
	
	/**
	 * Remove unwanted characters
	 * 
	 * <p>
	 * Pass string with unwanted spaces and/or special characters in begining and in end.
	 * Method will remove it and provide required trimmed string
	 * <p>
	 * 
	 * @param 	str		String with unwanted characters
	 * @return	str 	trimmed version of string
	 */
	public String performOper(String str){
		if(str ==""){
			return str;
		}
		while(str.charAt(0) ==' '){
			str = str.substring(1);
		}
		while(str.charAt(str.length()-1) == ' '){
			str = str.substring(0,str.length()-1);
		}
		if(str ==""){
			return str;
		}
		while(str.charAt(0) =='\''||str.charAt(0) =='\t' || str.charAt(0) =='"' ||str.charAt(0) =='`' || str.charAt(0) =='(' ||str.charAt(0) ==')'){
			str = str.substring(1,str.length());
		}
		while(str.charAt(str.length()-1) =='\'' || str.charAt(str.length()-1) =='"' || str.charAt(str.length()-1)=='`' || str.charAt(str.length()-1) =='(' ||str.charAt(str.length()-1) ==')'){
			str = str.substring(0,str.length()-1);
		}
		return str;
	}
	
	
	
	/**
	 * check and create required directories
	 * 
	 * @param 	path	path provided by user
	 * @param 	dbNAme  Database name
	 * @return	type	type to create directory
	 */
	public String checkAndCreateDirectory(String path,String dbName,String type){
		String path1=path+"\\com\\mysqltomongodb\\"+dbName+"\\"+type;
		File theFile = new File(path1);
		theFile.mkdirs();
		return path1;
		
	}

}
