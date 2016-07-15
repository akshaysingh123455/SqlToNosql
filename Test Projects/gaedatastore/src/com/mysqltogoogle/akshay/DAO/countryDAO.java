package com.mysqltogoogle.akshay.DAO;




/**
* 
 * @author 		Akshay P. Singh <akshayps@iitrpr.ac.in>
 * @version 	1.0
 *
 * This code is generated to help users to migrate from MySQL to Mongodb
 * Only four methods of this class are for user. Others are helper methods
 * 1) insert
 * 2) select
 * 3) delete
 * 4) update
 */


import com.mysqltogoogle.akshay.DTO.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;




public class countryDAO { 

	public   String tableName="country";
	public String[] JavaDataTypes={"int","boolean","long","float","double","String","Date","Timestamp","Time"};
	public String[] colNames={"country_id","country","last_update"};
	public String[] daeComparators={"EQUAL","NOT_EQUAL","GREATER_THAN","LESS_THAN","GREATER_THAN_OR_EQUAL","LESS_THAN_OR_EQUAL"};
	public String[] sqlComparators={"=","!=",">","<",">=","<="};
	public String[] colTypes= {"int","String","Timestamp"};
	public int primaryKeyId=0;

	public countryDAO() {
	
	}
	/**
	 * handle insert values in given Google database
	 *
	 * <p>
	 * This methods take input sql query to insert values in database and identify values and insert it into corresponding database.
	 * It uses helper methods to insert values
	 * <p>
	 *
	 * @param 	str		input string with sql query
	 *
	 */
	public void insert(String str){
	int indexInsert = str.indexOf("INSERT INTO ");
	Stack<Character> st = new Stack<Character>();
	Stack<Character> st1= new Stack<Character>();
	while(indexInsert != (-1)){
		int indexC = str.indexOf(";",indexInsert);
		String tempStatement = str.substring(indexInsert,indexC+1);
		String[] tokens = tempStatement.split(" ",4);
		if(!performOper(tokens[2]) .equals(tableName)){
			System.out.println("Syntax Error in Table name\nRequired Name not found");
			return;
		}
		int indexValueStart = tempStatement.indexOf("(",str.indexOf("VALUES")+1);
		String valueString = tempStatement.substring(indexValueStart,tempStatement.indexOf(";"));
		ArrayList<String> EachRow = new ArrayList<String>();
		String test="";
		for(int i=0;i<valueString.length();i++){
			if(valueString.charAt(i) == '('){
				if(st.empty()){
					st.push('(');
				}else{
					test+=String.valueOf(valueString.charAt(i));
				}
			}
			if(valueString.charAt(i)== '"'){
				if(!st.empty() && st.peek()=='"'){
					st.pop();
				}else{
					if(st.peek() =='('){
						st.push('"');
					}
				}
			}
			if(valueString.charAt(i)== '`'){
				if(!st.empty() && st.peek()=='`'){
					st.pop();
				}else{
					if(st.peek() =='('){
						st.push('`');
					}
				}
			}
			if(valueString.charAt(i)== '\''){
				if(!st.empty() && st.peek()=='\''){
					st.pop();
				}else{
					if(st.peek() =='('){
						st.push('\'');
					}
				}
			}
			if(valueString.charAt(i)==')' ){
				if(!st.empty() && st.peek()=='('){
					st.pop();
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
			}
			EachRow.add(test);
			while(!st.empty()){
				st.pop();
			}
			String[] columnsNames = getColumnNames(tempStatement.substring(0,tempStatement.indexOf("VALUES")));
			for(int i=0;i<EachRow.size();i++){
				String temp="";
				ArrayList<String> EachValue = new ArrayList<String>();
				for(int j=0;j<EachRow.get(i).length();j++){
					if(EachRow.get(i).charAt(j)== '"'){
						if(!st.empty() && st.peek()=='"'){
							st.pop();
					}else{
					if(st.empty()){
						st.push('"');
					}
				}
			}
			if(EachRow.get(i).charAt(j)== '`'){
				if(!st.empty() && st.peek()=='`'){
					st.pop();
				}else{
					if(st.empty()){
						st.push('`');
					}
				}
			}
			if(EachRow.get(i).charAt(j)== '\''){
				if(!st.empty() && st.peek()=='\''){
					st.pop();
				}else{
					if(st.empty()){
						st.push('\'');
					}
				}
			}
			if(EachRow.get(i).charAt(j) ==','){
				if(st.empty()){
					EachValue.add(temp);
					temp="";
				}else{
					temp+=String.valueOf(EachRow.get(i).charAt(j));
				}
			}else{
					temp+=String.valueOf(EachRow.get(i).charAt(j));
				}
			}
			EachValue.add(temp);
			String[] eachValue = EachRow.get(i).split(",");
			InsertQueryGenerator(columnsNames,EachValue);
			}
			indexInsert = str.indexOf("INSERT INTO ",indexC+1);
			}
			}
			public String[] getColumnNames(String str){
				if(str.indexOf("(") <0 && str.indexOf(")")<0){
					return colNames;
				}
				String listCols = str.substring(str.indexOf("(")+1, str.indexOf(")"));
				String[] eachCol = listCols.split(",");
				for(int i=0;i<eachCol.length;i++){
					eachCol[i] = performOper(eachCol[i]);
				}
				return eachCol;
			}
	/**
	 * provide result for select query
	 *
	 * <p>
	 * This method will take 3 inputs
	 * 	a) select query in MySQL format with given specifications
	 * <p>
	 *
	 * <p>
	 * This method with extract columns names and conditions, sub-queries and query with provided google database
	 * and provide required results in the form array of DTO objects of corresponding table.
	 *
	 *
	 * @param		str		input string with sql query to select
	 *
	 * @return		test	Array of DTO objects
	 *
	 */
	public countryDTO[] select(String str){
		com.google.appengine.api.datastore.DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = SelectQueryGenerator(str);
		PreparedQuery pq = ds.prepare(q);
		int counter=0;
		for(Entity u1:pq.asIterable()){
			counter++;
		}
		countryDTO[] countryValues = new countryDTO[counter];
		counter=0;
		for(Entity u1:pq.asIterable()){
			countryValues[counter]= new countryDTO();
			String country_id = u1.getProperty("country_id").toString();
			countryValues[counter].setcountry_id(Integer.parseInt(performOper(country_id)));
			String country = u1.getProperty("country").toString();
			countryValues[counter].setcountry((performOper(country)));
			String last_update = u1.getProperty("last_update").toString();
			countryValues[counter].setlast_update(StringToTimestamp(performOper(last_update)));
			counter++;
		}
		return countryValues;
	}
	/*
	 * <p>
	 * This method handles MySQL delete Query
	 * <p>
	 *
	 * <p>
	 * This method will take 1input
	 * 	a) select query in MySQL format with given specifications
	 * <p>
	 *
	 * <p>
	 * It identifies where condition and on the basis of conditions provided it will interact with google database and perform delete operation;
	 * To make it more secure we made it in such a way that user must provide a valid condition to make operation
	 * <p>
	 *
	 * @param		str				input string with sql query to select
	 *
	 */


	public void delete(String str){
		com.google.appengine.api.datastore.DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = DeleteQueryGenerator(str);
		PreparedQuery pq = ds.prepare(q);
			for(Entity u1:pq.asIterable()){
			ds.delete(u1.getKey());
		}
	}



	/*
	 * <p>
	 * This method handles MySQL delete Query
	 * <p>
	 *
	 * <p>
	 * This method will take 1 input
	 * 	a) select query in MySQL format with given specifications
	 * <p>
	 *
	 * <p>
	 * It identifies where condition and on the basis of conditions provided it will interact with google database and perform delete operation;
	 * To make it more secure we made it in such a way that user must provide a valid condition to make operation
	 * <p>
	 *
	 * @param		str				input string with sql query to select
	 *
	 */
	public void update(String str){
		com.google.appengine.api.datastore.DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = updateQueryGenerator(str);
		PreparedQuery pq = ds.prepare(q);
		for(Entity u1:pq.asIterable()){
			Key k = u1.getKey();
			String t= k.getName();
			Entity country = new Entity(tableName,t);
			country.setProperty("country_id",u1.getProperty("country_id"));
			country.setProperty("country",u1.getProperty("country"));
			country.setProperty("last_update",u1.getProperty("last_update"));
			country = UpdateSetQuery(str,country);
			ds.put(country);
		}
	}



	/*
	 * <p>
	 * This method is helper function of insert method
	 * <p>
	 *
	 * <p>
	 * This method will take 2 inputs
	 * 	a) Column names to be inserted
	 * 	b) each value for each time insert
	 * <p>
	 *
	 * <p>
	 * This method do the main inserting operation and insert each row value of MySQL table in corresponding google collection
	 * <p>
	 *
	 * @param		colsNames		column names in which data to be inserted
	 * @param		eachValue		Value of each row
	 *
	 */

	public void InsertQueryGenerator(String[] colsNames,ArrayList<String> eachValue){

	int matchPrimary=0;

	com.google.appengine.api.datastore.DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	for(int i=0;i<colsNames.length;i++){

	if(colsNames[i] .equals(colNames[primaryKeyId])){

	matchPrimary = i;

	break;

	}

	}

	Entity country = new Entity("country",performOper(eachValue.get(matchPrimary)));

	for(int j=0;j<colsNames.length;j++){

	int i=0;

	for(int k=0;k<colNames.length;k++){

	if(colsNames[j] .equals(colNames[k])){

	i=k;

	break;

	}

	}
			if(colTypes[i] .equals("int")){
				country.setProperty(colNames[i], Integer.parseInt(performOper(eachValue.get(i))));
			}
			if(colTypes[i] .equals("boolean")){
				country.setProperty(colNames[i], Boolean.parseBoolean(performOper(eachValue.get(i))));
			}
			if(colTypes[i] .equals("long")){
				country.setProperty(colNames[i], Long.parseLong(performOper(eachValue.get(i))));
			}
			if(colTypes[i] .equals("float")){
				country.setProperty(colNames[i], Float.parseFloat(performOper(eachValue.get(i))));
			}
			if(colTypes[i] .equals("double")){
				country.setProperty(colNames[i], Double.parseDouble(performOper(eachValue.get(i))));
			}
			if(colTypes[i] .equals("String")){
				country.setProperty(colNames[i], performOper(eachValue.get(i)));
			}
			if(colTypes[i] .equals("long")){
				country.setProperty(colNames[i], Long.parseLong(performOper(eachValue.get(i))));
			}
			if(colTypes[i] .equals("Date")){
				country.setProperty(colNames[i], performOper(eachValue.get(i)));
			}
			if(colTypes[i] .equals("Timestamp")){
				country.setProperty(colNames[i], performOper(eachValue.get(i)));
			}
			if(colTypes[i] .equals("Time")){
				country.setProperty(colNames[i], performOper(eachValue.get(i)));
			}
		}
		datastore.put(country);
	}
	public Query SelectQueryGenerator(String str){
		Query q = new Query(tableName);
		int indexOfWhere = str.toUpperCase().indexOf(" WHERE ");
		int indexOfSort = str.toUpperCase().indexOf(" ORDER BY ");
		int endIndex = str.indexOf(";");
		if(endIndex ==(-1)){
		endIndex = str.length();
		}
		if(indexOfWhere >0){
		if(indexOfSort>0){
		String WhereStatement = str.substring(indexOfWhere,indexOfSort);
		q = WhereStatement(q,WhereStatement);
		}else{
		String WhereStatement = str.substring(indexOfWhere,endIndex);
		q = WhereStatement(q,WhereStatement);
		}
		}
		if(indexOfSort>0){
		String sortStatement = str.substring(indexOfSort, endIndex);
		q = SortStatement(q,sortStatement);
		}
		return q;
	}
	public Query DeleteQueryGenerator(String str){
		Query q = new Query(tableName);
		int indexOfWhere = str.toUpperCase().indexOf(" WHERE ");
		int endIndex = str.indexOf(";");
		if(endIndex==(-1)){
			endIndex = str.length();
		}
		if(indexOfWhere >0){
			String test = str.substring(indexOfWhere,endIndex);
			q = WhereStatement(q,test);
		}
		return q;
	}
	/**
	 * <p>
	 * This method is helper function of update method
	 * <p>
	 *
	 * <p>
	 * This method will take 2 inputs
	 * 	a) update query in MySQL format with given specifications
	 * 	b) google datastore entity
	 * <p>
	 *
	 * <p>
	 * This method determine required conditions for update Query and return updated google GAE entity including conditions
	 * <p>
	 *
	 * @param 		str			input update query
	 * @param		entity		google datastore entity
	 *
	 * @return		entity		updated google GAE entity
	 *
	 */
	public Entity UpdateSetQuery(String str,Entity country){
		int indexSet = str.toUpperCase().indexOf(" SET ");
		int indexOfWhere = str.toUpperCase().indexOf(" WHERE ");
		String test ="";
		if(indexOfWhere >0){
			test = str.substring(indexSet, indexOfWhere);
		}else{
			int indexEnd = str.indexOf(";");
			if(indexEnd==(-1)){
				indexEnd = str.length();
			}
			test = str.substring(indexSet, indexEnd);
		}
		String[] eachSet = test.split(",");
		for(int i=0;i<eachSet.length;i++){
			String[] nameValue = eachSet[i].split("=");
			int indexFoundColumn = 0;
			for(int j=0;j<colNames.length;j++){
				if(eachSet[i].contains(colNames[j])){
					indexFoundColumn=j;
					break;
				}
			}
			if(colTypes[indexFoundColumn] .equals("int")){
				country.setProperty(colNames[indexFoundColumn], Integer.parseInt(performOper(nameValue[1])));
			}
			if(colTypes[indexFoundColumn] .equals("boolean")){
				country.setProperty(colNames[indexFoundColumn], Boolean.parseBoolean(performOper(nameValue[1])));
			}
			if(colTypes[indexFoundColumn] .equals("long")){
				country.setProperty(colNames[indexFoundColumn], Long.parseLong(performOper(nameValue[1])));
			}
			if(colTypes[indexFoundColumn] .equals("float")){
				country.setProperty(colNames[indexFoundColumn], Float.parseFloat(performOper(nameValue[1])));
			}
			if(colTypes[indexFoundColumn] .equals("double")){
				country.setProperty(colNames[indexFoundColumn], Double.parseDouble(performOper(nameValue[1])));
			}
			if(colTypes[indexFoundColumn] .equals("String")){
				country.setProperty(colNames[indexFoundColumn], (performOper(nameValue[1])));
			}
			if(colTypes[indexFoundColumn] .equals("Date")){
				country.setProperty(colNames[indexFoundColumn], (performOper(nameValue[1])));
			}
			if(colTypes[indexFoundColumn] .equals("Timestamp")){
				country.setProperty(colNames[indexFoundColumn], StringToTimestamp(performOper(nameValue[1])));
			}
			if(colTypes[indexFoundColumn] .equals("Time")){
				country.setProperty(colNames[indexFoundColumn],  (performOper(nameValue[1])));
			}
		}
		return country;
	}
		public Query updateQueryGenerator(String str){
		Query q = new Query(tableName);
		int indexOfWhere = str.toUpperCase().indexOf(" WHERE ");
		int endIndex = str.indexOf(";");
		if(endIndex ==(-1)){
			endIndex = str.length();
		}
		if(indexOfWhere >0){
			String test = str.substring(indexOfWhere,endIndex);
			q = WhereStatement(q,test);
		}
		return q;
		}


	/**
	 * <p>
	 * This methods handles all the where related conditions and user various helper methods to get exact condition/value query need
	 * <p>
	 *
	 * <p>
	 * This method takes 2 inputs
	 * 	a) Google query 
	 * 	b) provided sql query
	 * <p>
	 *
	 * <p>
	 * It will return where condition in BasicDBObject which can later be queried with mongodb database
	 * <p>
	 *
	 * @param 		q					Google query
	 * @param		str					provided sql query
	 *
	 * @return		q					updated Query
	 */
	public Query WhereStatement(Query q,String str){
		str =str.replaceAll(" WHERE ","");
		str =str.replaceAll(" where ","");
		String[] eachElement = str.split(" AND ");
		for(int j=0;j<eachElement.length;j++){
		String test = eachElement[j];
		int colIdInWhere=0,sqlComparatorId=0;
		for(int i=0;i<colNames.length;i++){
			if(test.indexOf(colNames[i]) <5 && test.indexOf(colNames[i]) !=(-1)){
				colIdInWhere = i;
				break;
			}
		}
		for(int i=0;i<sqlComparators.length;i++){
			if(test.contains(sqlComparators[i])){
				sqlComparatorId=i;
			}
		}
		String[] br = test.split(sqlComparators[sqlComparatorId]);
		if(colTypes[colIdInWhere] .equals("int")){
			int value = Integer.parseInt(performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		if(colTypes[colIdInWhere] .equals("boolean")){
			boolean value = Boolean.parseBoolean(performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		if(colTypes[colIdInWhere] .equals("long")){
			long value = Long.parseLong(performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		if(colTypes[colIdInWhere] .equals("float")){
			float value = Float.parseFloat(performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		if(colTypes[colIdInWhere] .equals("double")){
			double value = Double.parseDouble(performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		if(colTypes[colIdInWhere] .equals("String")){
			String value = (performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		if(colTypes[colIdInWhere] .equals("Date")){
			String value = (performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		if(colTypes[colIdInWhere] .equals("Timestamp")){
			Timestamp value = StringToTimestamp(performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		if(colTypes[colIdInWhere] .equals("Time")){
			String value =  (performOper(br[1]));
			if(sqlComparatorId == 0){
				q.addFilter(colNames[colIdInWhere],FilterOperator.EQUAL,value);
			}
			if(sqlComparatorId == 1){
				q.addFilter(colNames[colIdInWhere],FilterOperator.NOT_EQUAL,value);
			}
			if(sqlComparatorId == 2){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN,value);
			}
			if(sqlComparatorId == 3){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN,value);
			}
			if(sqlComparatorId == 4){
				q.addFilter(colNames[colIdInWhere],FilterOperator.GREATER_THAN_OR_EQUAL,value);
			}
			if(sqlComparatorId == 5){
				q.addFilter(colNames[colIdInWhere],FilterOperator.LESS_THAN_OR_EQUAL,value);
			}
		}
		}
		return q;
	}
		public Query SortStatement(Query q,String test){
		test =test.replaceAll(" order by ","");
		test =test.replaceAll(" ORDER BY ","");
		boolean forAsc = true;
		String col="";
		if(test.toUpperCase().indexOf(" DESC") >=0){
		forAsc = false;
		col = test.substring(0,test.toUpperCase().indexOf(" DESC"));
		}else{
		col = test.substring(0,test.toUpperCase().indexOf(" ASC"));
		}
		String[] columnNames = col.split(",");
		for(int i=0;i<columnNames.length;i++){
		if(forAsc == true){
		System.out.println(performOper(columnNames[i]));
		q.addSort(performOper(columnNames[i]),SortDirection.ASCENDING);
		}else{
			q.addSort(performOper(columnNames[i]),SortDirection.DESCENDING);
		}
		}
		return q;
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