package com.mysqltomongodb.akshay.DAO;

/**
* 
 * @author 		Akshay P. Singh <akshayps@iitrpr.ac.in>
 * @version 	1.0
 *
 * This class is used to get values of Union of sql using mongodb ..
 * Only one method "unionValues" is for use by user. Other methods are helper methods
 */

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;
import com.mysqltomongodb.akshay.DTO.*;



public class Union {

	public String[] tableNames={"actor","address","category","city","country","customer","film","film_actor","film_category","film_text","inventory","language","payment","rental","staff","store"};
	/**
	 * <p>
	 * This method return the union of two or more queries
	 * <p>
	 *
	 * <p>
	 * This method takes 3 inputs:-
	 * 		a) Array of queries we need to make union of
	 * 		b) host name of mongodb database
	 * 		c) Port number of the same
	 * <p>
	 *
	 * <p>
	 * This method identify the name of table of first query and provide result in the form of Arraylist of corresspoding DTO file of that table and append next table result in the same.
	 * <p>
	 *
	 *
	 * @param 		str[]		String array of select queries
	 * @param 		host		host name
	 * @param 		port		port number
	 *
	 * @return					ArrayList of first identified table
	 */
	public ArrayList unionValues(String[] str,String host,int port){
		String tableName = this.getTableName(str[0]);
		if(tableName .equals("actor")){
			actorDAO dao = new actorDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<actorDTO> test = new ArrayList<actorDTO>();
			ArrayList<actorDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					actorDTO abc = new actorDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("actor_id")){
							abc.setactor_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("first_name")){
							abc.setfirst_name(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_name")){
							abc.setlast_name(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("address")){
			addressDAO dao = new addressDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<addressDTO> test = new ArrayList<addressDTO>();
			ArrayList<addressDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					addressDTO abc = new addressDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("address_id")){
							abc.setaddress_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("address")){
							abc.setaddress(((value1[i][j])));
						};
						if(foundCols[j] .equals("address2")){
							abc.setaddress2(((value1[i][j])));
						};
						if(foundCols[j] .equals("district")){
							abc.setdistrict(((value1[i][j])));
						};
						if(foundCols[j] .equals("city_id")){
							abc.setcity_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("postal_code")){
							abc.setpostal_code(((value1[i][j])));
						};
						if(foundCols[j] .equals("phone")){
							abc.setphone(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("category")){
			categoryDAO dao = new categoryDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<categoryDTO> test = new ArrayList<categoryDTO>();
			ArrayList<categoryDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					categoryDTO abc = new categoryDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("category_id")){
							abc.setcategory_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("name")){
							abc.setname(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("city")){
			cityDAO dao = new cityDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<cityDTO> test = new ArrayList<cityDTO>();
			ArrayList<cityDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					cityDTO abc = new cityDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("city_id")){
							abc.setcity_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("city")){
							abc.setcity(((value1[i][j])));
						};
						if(foundCols[j] .equals("country_id")){
							abc.setcountry_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("country")){
			countryDAO dao = new countryDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<countryDTO> test = new ArrayList<countryDTO>();
			ArrayList<countryDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					countryDTO abc = new countryDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("country_id")){
							abc.setcountry_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("country")){
							abc.setcountry(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("customer")){
			customerDAO dao = new customerDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<customerDTO> test = new ArrayList<customerDTO>();
			ArrayList<customerDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					customerDTO abc = new customerDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("customer_id")){
							abc.setcustomer_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("store_id")){
							abc.setstore_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("first_name")){
							abc.setfirst_name(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_name")){
							abc.setlast_name(((value1[i][j])));
						};
						if(foundCols[j] .equals("email")){
							abc.setemail(((value1[i][j])));
						};
						if(foundCols[j] .equals("address_id")){
							abc.setaddress_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("active")){
							abc.setactive(Boolean.parseBoolean((value1[i][j])));
						};
						if(foundCols[j] .equals("create_date")){
							abc.setcreate_date(StringToTimestamp((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("film")){
			filmDAO dao = new filmDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<filmDTO> test = new ArrayList<filmDTO>();
			ArrayList<filmDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					filmDTO abc = new filmDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("film_id")){
							abc.setfilm_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("title")){
							abc.settitle(((value1[i][j])));
						};
						if(foundCols[j] .equals("description")){
							abc.setdescription(((value1[i][j])));
						};
						if(foundCols[j] .equals("release_year")){
							abc.setrelease_year(((value1[i][j])));
						};
						if(foundCols[j] .equals("language_id")){
							abc.setlanguage_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("original_language_id")){
							abc.setoriginal_language_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("rental_duration")){
							abc.setrental_duration(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("rental_rate")){
							abc.setrental_rate(Double.parseDouble((value1[i][j])));
						};
						if(foundCols[j] .equals("length")){
							abc.setlength(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("replacement_cost")){
							abc.setreplacement_cost(Double.parseDouble((value1[i][j])));
						};
						if(foundCols[j] .equals("rating")){
							abc.setrating(((value1[i][j])));
						};
						if(foundCols[j] .equals("special_features")){
							abc.setspecial_features(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("film_actor")){
			film_actorDAO dao = new film_actorDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<film_actorDTO> test = new ArrayList<film_actorDTO>();
			ArrayList<film_actorDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					film_actorDTO abc = new film_actorDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("actor_id")){
							abc.setactor_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("film_id")){
							abc.setfilm_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("film_category")){
			film_categoryDAO dao = new film_categoryDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<film_categoryDTO> test = new ArrayList<film_categoryDTO>();
			ArrayList<film_categoryDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					film_categoryDTO abc = new film_categoryDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("film_id")){
							abc.setfilm_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("category_id")){
							abc.setcategory_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("film_text")){
			film_textDAO dao = new film_textDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<film_textDTO> test = new ArrayList<film_textDTO>();
			ArrayList<film_textDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					film_textDTO abc = new film_textDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("film_id")){
							abc.setfilm_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("title")){
							abc.settitle(((value1[i][j])));
						};
						if(foundCols[j] .equals("description")){
							abc.setdescription(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("inventory")){
			inventoryDAO dao = new inventoryDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<inventoryDTO> test = new ArrayList<inventoryDTO>();
			ArrayList<inventoryDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					inventoryDTO abc = new inventoryDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("inventory_id")){
							abc.setinventory_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("film_id")){
							abc.setfilm_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("store_id")){
							abc.setstore_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("language")){
			languageDAO dao = new languageDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<languageDTO> test = new ArrayList<languageDTO>();
			ArrayList<languageDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					languageDTO abc = new languageDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("language_id")){
							abc.setlanguage_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("name")){
							abc.setname(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("payment")){
			paymentDAO dao = new paymentDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<paymentDTO> test = new ArrayList<paymentDTO>();
			ArrayList<paymentDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					paymentDTO abc = new paymentDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("payment_id")){
							abc.setpayment_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("customer_id")){
							abc.setcustomer_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("staff_id")){
							abc.setstaff_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("rental_id")){
							abc.setrental_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("amount")){
							abc.setamount(Double.parseDouble((value1[i][j])));
						};
						if(foundCols[j] .equals("payment_date")){
							abc.setpayment_date(StringToTimestamp((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("rental")){
			rentalDAO dao = new rentalDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<rentalDTO> test = new ArrayList<rentalDTO>();
			ArrayList<rentalDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					rentalDTO abc = new rentalDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("rental_id")){
							abc.setrental_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("rental_date")){
							abc.setrental_date(StringToTimestamp((value1[i][j])));
						};
						if(foundCols[j] .equals("inventory_id")){
							abc.setinventory_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("customer_id")){
							abc.setcustomer_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("return_date")){
							abc.setreturn_date(StringToTimestamp((value1[i][j])));
						};
						if(foundCols[j] .equals("staff_id")){
							abc.setstaff_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("staff")){
			staffDAO dao = new staffDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<staffDTO> test = new ArrayList<staffDTO>();
			ArrayList<staffDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					staffDTO abc = new staffDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("staff_id")){
							abc.setstaff_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("first_name")){
							abc.setfirst_name(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_name")){
							abc.setlast_name(((value1[i][j])));
						};
						if(foundCols[j] .equals("address_id")){
							abc.setaddress_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("picture")){
							abc.setpicture(((value1[i][j])));
						};
						if(foundCols[j] .equals("email")){
							abc.setemail(((value1[i][j])));
						};
						if(foundCols[j] .equals("store_id")){
							abc.setstore_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("active")){
							abc.setactive(Boolean.parseBoolean((value1[i][j])));
						};
						if(foundCols[j] .equals("username")){
							abc.setusername(((value1[i][j])));
						};
						if(foundCols[j] .equals("password")){
							abc.setpassword(((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("store")){
			storeDAO dao = new storeDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0],host,port);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<storeDTO> test = new ArrayList<storeDTO>();
			ArrayList<storeDTO> value = dao.select(str[0],host,port);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k],host,port);
				for(int i=0;i<value1.length;i++){
					storeDTO abc = new storeDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("store_id")){
							abc.setstore_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("manager_staff_id")){
							abc.setmanager_staff_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("address_id")){
							abc.setaddress_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("last_update")){
							abc.setlast_update(StringToTimestamp((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
				return null;
			}
	/**
	 * <p>
	 * This method identify table name and create object accordingly and provide result in 2-D array.
	 * This method is used from second query
	 * <p>
	 *
	 * @param 		str			select query
	 * @param 		host		host name
	 * @param 		port		port number
	 *
	 * @return					2-D array string result
	 */
		public String[][] getValue(String str,String host,int port){
			String tableName = this.getTableName(str);
			if(tableName .equals("actor")){
				actorDAO dao = new actorDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("address")){
				addressDAO dao = new addressDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("category")){
				categoryDAO dao = new categoryDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("city")){
				cityDAO dao = new cityDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("country")){
				countryDAO dao = new countryDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("customer")){
				customerDAO dao = new customerDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("film")){
				filmDAO dao = new filmDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("film_actor")){
				film_actorDAO dao = new film_actorDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("film_category")){
				film_categoryDAO dao = new film_categoryDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("film_text")){
				film_textDAO dao = new film_textDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("inventory")){
				inventoryDAO dao = new inventoryDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("language")){
				languageDAO dao = new languageDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("payment")){
				paymentDAO dao = new paymentDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("rental")){
				rentalDAO dao = new rentalDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("staff")){
				staffDAO dao = new staffDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			if(tableName .equals("store")){
				storeDAO dao = new storeDAO();
				String[][] value = dao.selectAsWholeArray(str,host,port);
				return value;
			}
			return null;
		}



	/**
	 * <p>
	 * This method is used to identify table name in a query
	 * <p>
	 *
	 * @param 		str			sql select query
	 * @return		tableName	identified table name
	 */
		public String getTableName(String str){
			int[] subQueryCheck = this.getSubQueryConfirmation(str);
			int fromIndex=-1;
			int tempFromIndex = str.toUpperCase().indexOf("FROM ");
			while(tempFromIndex >=0){
				if(subQueryCheck[tempFromIndex] ==0){
					fromIndex = tempFromIndex;
					break;
				}
				tempFromIndex = str.toUpperCase().indexOf("FROM ", tempFromIndex+1);
			}
			String[] tokens = str.substring(fromIndex).split(" ", 4);
			String tableName = tokens[1];
			if(tableName .contains(".")){
				tableName = tableName.split(".")[1];
			}
			return tableName;
		}



		/**
		 * <p>
		 * This method perform basic trimming operation on input string
		 * <p>
		 * 
		 * @param		str		input string
		 * 
		 * @return		str		trimmed string
		 */	
	public String performOper(String str){
		if(str ==""){
			return str;
		}
		if(str.charAt(0) == '(' || str.charAt(0) =='[' ||str.charAt(0) == '{'){
			str=str.substring(1);
		}
		if(str.charAt(str.length()-1) == ')' || str.charAt(str.length()-1) == ']' ||str.charAt(str.length()-1) == '}'){
			str = str.substring(0,str.length()-1);
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
				if(str.length() ==1){
					return "";
				}
				str = str.substring(1,str.length());
			}
		while(str.charAt(str.length()-1) =='\'' || str.charAt(str.length()-1) =='"' || str.charAt(str.length()-1)=='`' || str.charAt(str.length()-1) =='(' ||str.charAt(str.length()-1) ==')'){
				if(str.length() ==1){
					return "";
				}
				str = str.substring(0,str.length()-1);
			}
		return str;
	}


	/**
	 * This method covert input frmo string to Timestamp 
	 * 
	 * @param 	str				input in string
	 * @return	timestamp		Timestamp
	 */
	public Timestamp StringToTimestamp(String str){
		try{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedTimeStamp = dateFormat.parse(str);
		Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());
		return timestamp;
		}catch(ParseException e){}
		return null;
	}
	

	/**
	 * This method identify the locations of different clauses that are acceptable and return their index in given query
	 * 
	 * @param 	str 			input query
	 * @param   count			array of equal length of input string which include information whether string have sub-query or not
	 *
	 * @return 	result 			location index for each clause
	 */
	public int[] mainClauses(String str, int[] count){
		str = str.toUpperCase();
		String[] items = {"WHERE ", "ORDER BY ","LIMIT ","OFFSET ","GROUP BY ",";"};
		int[] result=new int[items.length];
		for(int i=0;i<result.length;i++){
			result[i] = -1;
			int indexTemp=str.indexOf(items[i]);
			while(indexTemp >= 0) {
				if(count[indexTemp] ==0){
					result[i] = indexTemp;
					break;
				}
				indexTemp = str.indexOf(items[i], indexTemp+1);
			}	
		}
		if(result[result.length-1] == (-1) ){
			result[result.length-1]  = str.length();
		}
		Arrays.sort(result);
		
		return result;
	}



	public double getGroupbyFunctionValue(String str,int code){
		String[] values = this.performOper(str).split(",");
		double[] val = new double[values.length];
		for(int i=0;i<values.length;i++){
			val[i] = Double.parseDouble(this.performOper(values[i]));
		}
		if(code ==1){
			Arrays.sort(val);
			return val[val.length-1];
		}
		if(code ==2){
			Arrays.sort(val);
			return val[0];
		}
		if(code ==3){
			double avg =0;
			for(int i=0;i<val.length;i++){
				avg+=val[i];
			}
			return avg/val.length;
		}
		if(code ==4){
			double sum =0;
			for(int i=0;i<val.length;i++){
				sum+=val[i];
			}
			return sum;
		}
		
		return (Double) null;
	}

	
public int[] getSubQueryConfirmation(String str){
		
	
	ArrayList<String> EachRow = new ArrayList<String>();
	String valueString = str;
	String test="";
	int[] count = new int[str.length()];
	for(int i=0;i<str.length();i++){
		count[i] =0;
	}
	
	


	Stack<Character> st = new Stack<Character>();
	Stack<Character> st1= new Stack<Character>();
	for(int i=0;i<valueString.length();i++){
		if(valueString.charAt(i) == '('){
			
				st.push('(');
	
		}
		


		if(valueString.charAt(i)==')' ){
			if(!st.empty() && st.peek()=='('){
		         try {
		             st.pop();
		          }
		          catch (EmptyStackException e) {
		          }
			}else{
				test+=String.valueOf(valueString.charAt(i));
			}
		}
		if(valueString.charAt(i) ==','){
			if(st.empty()){
				EachRow.add(test);
				test="";
			}else{
				test+=String.valueOf(valueString.charAt(i));
			}
		}else{
			test+=String.valueOf(valueString.charAt(i));
		}
		if(!st.empty()){
			count[i] =1;
		}
		}

		EachRow.add(test);


		
		
		
	return count;
	}

}