package com.mysqltomongodb.akshay.DAO;




/**
* 
 * @author 		Akshay P. Singh <akshayps@iitrpr.ac.in>
 * @version 	1.0
 *
 * This code is generated to help users to migrate from MySQL to Mongodb
 * Only Five methods of this class are for user. Others are helper methods
 * 1) insert
 * 2) select
 * 3) delete
 * 4) update
 * 5) selectSingleValue
 */


import com.mysqltomongodb.akshay.DTO.*;
import java.util.List;
import java.util.regex.Pattern;import java.util.Set;
import java.math.*;
import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;




public class languageDAO { 

	public String databaseName="akshay";
	public String tableName="language";
	public String tempTableName="language";
	public String[] javaDataTypes={"int","boolean","long","float","double","String","Date","Timestamp","Time"};
	public String[] colNames={"language_id","name","last_update"};
	public String[] tempColNames={"language_id","name","last_update"};
	public String[] daeComparators={"Equality","Less Than","Less Than Equals","Greater Than","Greater Than Equals","Not Equals"};
	public String[] sqlComparators={"=","<","<=",">",">=","!="};
	public String[] colTypes= {"int","String","Timestamp"};
	public int primaryKeyId=0;

	public languageDAO() {
	
	}
	/**
	 * handle insert values in given Mongodb database
	 *
	 * <p>
	 * This methods take input sql query to insert values in database and identify values and insert it into corresponding database.
	 * It uses helper methods to insert values
	 * <p>
	 *
	 * @param 	str		input string with sql query
	 *
	 */
	public void insert(String str,String host,int port){
	int indexInsert = str.toUpperCase().indexOf("INSERT INTO ");
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
		int indexValueStart = tempStatement.toUpperCase().indexOf("(",str.toUpperCase().indexOf("VALUES")+1);
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
			String[] columnsNames = getColumnNames(tempStatement.substring(0,tempStatement.toUpperCase().indexOf("VALUES")));
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
			InsertQueryGenerator(columnsNames,EachValue,host,port);
			}
			indexInsert = str.toUpperCase().indexOf("INSERT INTO ",indexC+1);
			}
			}



/**
 * provide column names for insert query
 *
 * <p>
	 * This method take input insert query string and identify list of columns which are required
	 * <p>
	 *
	 * @param 	str		input string with sql query
	 *
	 * @return	eachCol	String array with name of required columns
	 *
	 */
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
	 * 	b) host name to connect
	 * 	c) port number to connect
	 * <p>
	 *
	 * <p>
	 * This method with extract columns names and conditions, sub-queries and query with provided mongodb database
	 * and provide required results in the form Arraylist of DTO objects of corresponding table.
	 *
	 *
	 * @param		str		input string with sql query to select
	 * @param		host	host name
	 * @param		port	port number
	 *
	 * @return		test	Arraylist of DTO objects
	 *
	 */
	public ArrayList<languageDTO> select(String str,String host,int port){
		try{
			ArrayList<languageDTO> test = new ArrayList<languageDTO>(); 
			this.setTempTableName(str);
			MongoClient mongoClient = new MongoClient(host,port);
			DB db = mongoClient.getDB( databaseName );
			System.out.println("Connect to database successfully");
		DBCollection coll = db.getCollection(tableName);
		int[] count = getSubQueryConfirmation( str);
		int[] mainTagsIndex = mainClauses(str,count);
			BasicDBObject where = WhereStatement(str,mainTagsIndex,host,port);
			BasicDBObject sortQuery = sort(str,mainTagsIndex,host,port);
			BasicDBObject whereCondition;
			if(where == null){
				whereCondition = new BasicDBObject();
			}else{
					whereCondition = where;
			}
			DBObject sort =  new BasicDBObject("$sort",sortQuery);
			String[] limitOffset = limitOffset(str,host,port).split(":");
			int limitValue = Integer.parseInt(limitOffset[0]);
			int offsetValue = Integer.parseInt(limitOffset[1]);
			BasicDBObject limit = new BasicDBObject("$limit",limitValue);
			BasicDBObject offSet = new BasicDBObject("$skip",offsetValue);
			String[] ColumnNamesToReturned = selectColumnNamesToReturned(str,host,port);
			for(int i=0;i<ColumnNamesToReturned.length;i++){
				if(!tableName .equals(tempTableName)){
					ColumnNamesToReturned[i] = ColumnNamesToReturned[i].replace(tempTableName+".", "");
				}
					ColumnNamesToReturned[i] = this.performOper(ColumnNamesToReturned[i]);
			}
			boolean groupBy = true;
			int[] subQueryCounter = getSubQueryConfirmation(str);
			int indexgroupTemp = str.toUpperCase().indexOf("GROUP BY ");
			if(indexgroupTemp <0){
				groupBy = false;
			}
			int indexOfGroup=0;
			if(groupBy == true){
				while(indexgroupTemp >= 0) {
					if(subQueryCounter[indexgroupTemp] ==0){
						indexOfGroup = indexgroupTemp;
						break;
					}
					indexgroupTemp = str.toUpperCase().indexOf("GROUP BY ", indexgroupTemp+1);
				}
				if(indexOfGroup ==0){
					groupBy = false;
				}
			}
			if(groupBy== false){
				DBObject forWhere = new BasicDBObject("$match",whereCondition);
				BasicDBObject dbobjectForGroup = new BasicDBObject();
				for(int i=0;i<ColumnNamesToReturned.length;i++){
					String tempColumnToGet ="$"+ColumnNamesToReturned[i];
					dbobjectForGroup.append(ColumnNamesToReturned[i], tempColumnToGet);
				}
				BasicDBObject idObject = new BasicDBObject("_id",dbobjectForGroup);
				for(int i=0;i<ColumnNamesToReturned.length;i++){
					String tempColumnToGet = "$"+ColumnNamesToReturned[i];
					idObject.append(ColumnNamesToReturned[i], new BasicDBObject("$addToSet",tempColumnToGet));
				}
				BasicDBObject groupObject = new BasicDBObject("$group",idObject);
				BasicDBObject objectForProject = new BasicDBObject("_id",0);
				for(int i=0;i<ColumnNamesToReturned.length;i++){
					if(ColumnNamesToReturned[i] .equals("language_id") || ColumnNamesToReturned[i] .equals("name") || ColumnNamesToReturned[i] .equals("last_update") ){
						String tempColumnToGet = "$"+ColumnNamesToReturned[i];
						objectForProject.append(ColumnNamesToReturned[i], tempColumnToGet);
					}
				}
				DBObject project = new BasicDBObject("$project",objectForProject );
				AggregationOutput output = coll.aggregate(forWhere,groupObject,project,sort,offSet,limit);
					 for (DBObject result : output.results()) {
						languageDTO temp = new languageDTO();
						 for(int i=0;i<ColumnNamesToReturned.length;i++){
							int colCounter=0;
							if(ColumnNamesToReturned[i] .equals("language_id")){
								String language_id = result.get("language_id").toString();
								temp.setlanguage_id(Integer.parseInt(performOper(language_id)));
							}
							else if(ColumnNamesToReturned[i] .equals("name")){
								String name = result.get("name").toString();
								temp.setname((performOper(name)));
							}
							else if(ColumnNamesToReturned[i] .equals("last_update")){
								String last_update = result.get("last_update").toString();
								temp.setlast_update(StringToTimestamp(performOper(last_update)));
							}
						}
						test.add(temp);
					}
			}else{
				String groupStatement ="";
				int requiredIndex=0;
				for(int i=0;i<mainTagsIndex.length;i++){
						if(indexOfGroup == mainTagsIndex[i]){
							requiredIndex=i;
						break;
					}
				}
				groupStatement = str.substring(mainTagsIndex[requiredIndex], mainTagsIndex[requiredIndex+1]);
					groupStatement = groupStatement.replaceFirst("GROUP BY ", "");
				String[] eachColumnForGroup = groupStatement.split(",");
				DBObject forWhere = new BasicDBObject("$match",whereCondition);
					BasicDBObject dbobjectForGroup = new BasicDBObject();
				for(int i=0;i<eachColumnForGroup.length;i++){
					String tempColumnToGet = "$"+eachColumnForGroup[i];
					dbobjectForGroup.append(eachColumnForGroup[i], tempColumnToGet);
				}
					BasicDBObject idObject = new BasicDBObject("_id",dbobjectForGroup);
				for(int i=0;i<ColumnNamesToReturned.length;i++){
					for(int j=0;j<this.colNames.length;j++){
						if(ColumnNamesToReturned[i].contains(this.colNames[j])){
							String tempColumnToGet = "$"+colNames[j];
							idObject.append(colNames[j], new BasicDBObject("$addToSet",tempColumnToGet));
						}
					}
				}
				BasicDBObject groupObject = new BasicDBObject("$group",idObject);
				BasicDBObject objectForProject = new BasicDBObject("_id",0);
				for(int i=0;i<ColumnNamesToReturned.length;i++){
					if(this.performOper(ColumnNamesToReturned[i]) .equals("language_id") || this.performOper(ColumnNamesToReturned[i]) .equals("name") || this.performOper(ColumnNamesToReturned[i]) .equals("last_update") ){
						String tempColumnToGet = "$"+ColumnNamesToReturned[i];
						objectForProject.append(ColumnNamesToReturned[i], tempColumnToGet);
					}else{
							for(int j=0;j<this.colNames.length;j++){
							if(ColumnNamesToReturned[i].contains(this.colNames[j])){
								String tempColumnToGet = "$"+colNames[j];
								if(ColumnNamesToReturned[i].contains("COUNT")){
									objectForProject.append("count", new BasicDBObject("$size",tempColumnToGet));
								}else{
									objectForProject.append(this.colNames[j], tempColumnToGet);
								}
							}
						}
					}
				}
				DBObject project = new BasicDBObject("$project",objectForProject );
				AggregationOutput output = coll.aggregate(forWhere,groupObject,project,sort,offSet,limit);
					 for (DBObject result : output.results()) {
						languageDTO temp = new languageDTO();
						 for(int i=0;i<ColumnNamesToReturned.length;i++){
							int colCounter=0;
							if(ColumnNamesToReturned[i] .equals("language_id")){
								String language_id = result.get("language_id").toString();
								temp.setlanguage_id(Integer.parseInt(performOper(language_id)));
							}
							else if(ColumnNamesToReturned[i] .equals("name")){
								String name = result.get("name").toString();
								temp.setname((performOper(name)));
							}
							else if(ColumnNamesToReturned[i] .equals("last_update")){
								String last_update = result.get("last_update").toString();
								temp.setlast_update(StringToTimestamp(performOper(last_update)));
							}
						else{
							if(ColumnNamesToReturned[i].contains("COUNT")){
								String count1 = result.get("count").toString();
									temp.setcount(Integer.parseInt(performOper(count1)));
							}else{
								int colId=-1;
									for(int j=0;j<this.colNames.length;j++){
										if(ColumnNamesToReturned[i].contains(this.colNames[j])){
											colId=j;
											break;
										}
									}
									if(colId>=0){
										if(ColumnNamesToReturned[i].contains("MAX")){
											String value = result.get(this.colNames[colId]).toString();
											temp.setmax(this.getGroupbyFunctionValue(value, 1));
										}
										if(ColumnNamesToReturned[i].contains("MIN")){
											String value = result.get(this.colNames[colId]).toString();
											temp.setmin(this.getGroupbyFunctionValue(value, 2));
										}
										if(ColumnNamesToReturned[i].contains("AVG")){
											String value = result.get(this.colNames[colId]).toString();
											temp.setavg(this.getGroupbyFunctionValue(value, 3));
										}
										if(ColumnNamesToReturned[i].contains("SUM")){
											String value = result.get(this.colNames[colId]).toString();
											temp.setsum(this.getGroupbyFunctionValue(value, 4));
										}
									}
								}
							}
						}
						test.add(temp);
					}
				}
		return test;
	}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
	return null;
	}
	/**
	 * provide select query result in 2-D array form
	 *
	 * <p>
	 * This method will take 3 inputs
	 * 	a) select query in MySQL format with given specifications
	 * 	b) host name to connect
	 * 	c) port number to connect
	 * <p>
	 *
	 * <p>
	 * This method with extract columns names and conditions, sub-queries and query with provided mongodb database
	 * and provide required results in the form of 2-D array
	 * <p>
	 *
	 *
	 * @param		str				input string with sql query to select
	 * @param		host			host name
	 * @param		port			port number
	 *
	 * @return		arrayValues		result in the form of 2-D array
	 */
	public String[][] selectAsWholeArray(String str,String host,int port){
		try{
			MongoClient mongoClient = new MongoClient(host,port);
			DB db = mongoClient.getDB( databaseName );
			System.out.println("Connect to database successfully");
		DBCollection coll = db.getCollection(tableName);
		int[] count = getSubQueryConfirmation( str);
		int[] mainTagsIndex = mainClauses(str,count);
			BasicDBObject whereCondition = WhereStatement(str,mainTagsIndex,host,port);
			String[] ColumnNamesToReturned = selectColumnNamesToReturned(str,host,port);
			for(int i=0;i<ColumnNamesToReturned.length;i++){
				if(!tableName .equals(tempTableName)){
					ColumnNamesToReturned[i] = ColumnNamesToReturned[i].replace(tempTableName+".", "");
				}
				ColumnNamesToReturned[i] = this.performOper(ColumnNamesToReturned[i]);
			}
			BasicDBObject sortQuery = sort(str,mainTagsIndex,host,port);
			DBCursor cursor = coll.find(whereCondition);
			cursor.sort(sortQuery);
			String[] limitOffset = limitOffset(str,host,port).split(":");
			int limitValue = Integer.parseInt(limitOffset[0]);
			int offsetValue = Integer.parseInt(limitOffset[1]);
			int counter = cursor.count();
			if( limitValue !=0 && offsetValue !=0){
			cursor.skip(offsetValue);
			cursor.limit(limitValue);
			counter = counter-offsetValue;
			if(counter >limitValue){
			counter = limitValue;
			}
			}else if(counter > limitValue && limitValue !=0){
			cursor.limit(limitValue);
			counter = limitValue;
			}else if(offsetValue !=0){
			cursor.skip(offsetValue);
			counter = counter-offsetValue;
			}
			String[][] arrayValues = new String[counter][ColumnNamesToReturned.length];
			counter=0;	
		while (cursor.hasNext()){
			cursor.next();
				for(int i=0;i<ColumnNamesToReturned.length;i++){
					int colCounter=0;
					if(ColumnNamesToReturned[i] .equals("language_id")){
						arrayValues[counter][i] = cursor.curr().get("language_id").toString();
					}
					else if(ColumnNamesToReturned[i] .equals("name")){
						arrayValues[counter][i] = cursor.curr().get("name").toString();
					}
					else if(ColumnNamesToReturned[i] .equals("last_update")){
						arrayValues[counter][i] = cursor.curr().get("last_update").toString();
					}
				else{
					String tempMysqlFunctionChecker = this.performOper(ColumnNamesToReturned[i]);
						if(tempMysqlFunctionChecker.contains("CONCAT")){
							String tempValue = tempMysqlFunctionChecker.substring(tempMysqlFunctionChecker.indexOf("(")+1,tempMysqlFunctionChecker.length());
							int[] subQueryCounter2 = this.getSubQueryConfirmation(tempValue);
							ArrayList<Integer> indexOfComma = new ArrayList<Integer>();
							ArrayList<String> eachCommaElement = new ArrayList<String>();
							System.out.println(tempValue);
							int tempIndexComma = tempValue.indexOf(",");
							while(tempIndexComma >=0){
								if(subQueryCounter2[tempIndexComma] == 0){
									indexOfComma.add(tempIndexComma);
								}
								tempIndexComma = tempValue.indexOf(",",tempIndexComma+1);
							}
							if(indexOfComma.isEmpty()){
								System.out.println("qwerty");
							}
							if(indexOfComma.size() ==0){
								eachCommaElement.add(tempValue);
							}else{
								for(int j=0;j<indexOfComma.size();j++){
									if(j==0){
										eachCommaElement.add(tempValue.substring(0,indexOfComma.get(0)));
									}else{
										eachCommaElement.add(tempValue.substring(indexOfComma.get(j-1), indexOfComma.get(j)));
									}
								}
									eachCommaElement.add(tempValue.substring(indexOfComma.get(indexOfComma.size()-1)));
								}
								for(int j=0;j<eachCommaElement.size();j++){
									if(eachCommaElement.get(j).indexOf(",") ==0){
										eachCommaElement.set(j, eachCommaElement.get(j).replaceFirst(",",""));
									}
								eachCommaElement.set(j,performOper(eachCommaElement.get(j)));
							}
							String[] eachElement = new String[eachCommaElement.size()];
							for(int j=0;j<eachCommaElement.size();j++){
								eachElement[j] = this.performOper(eachCommaElement.get(j));
							}
							int[] colIndexId = new int[eachElement.length];
							for(int j=0;j<eachElement.length;j++){
								colIndexId[j] =-1;
								for(int k=0;k<colNames.length;k++){
									if(eachElement[j] .equals(colNames[k])){
										colIndexId[j] = k;
										break;
									}
								}
							}
							String concatValue="";
							for(int j=0;j<eachElement.length;j++){
								if(colIndexId[j] ==-1){
									concatValue+=eachElement[j];
								}else{
									concatValue+=cursor.curr().get(eachElement[j]).toString();
								}
							}
						arrayValues[counter][i]=(concatValue);
					}
					}
			}
			counter++;
		}
		return arrayValues;
	}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
	return null;
	}
	/**
	 * provide column list for select query including MySQL functions like COUNT,SUM,etc
	 *
	 * <p>
	 * This method will take select Query as input and return identified list of columns as String array
	 * <p>
	 *
	 *
	 * @param		str				MySQL select Query
	 * @return		eachElement		List of column names
	 */
	public String[] selectColumnNamesToReturned(String str,String host,int port){
	int selectIndex = str.toUpperCase().indexOf("SELECT ");
	int fromIndex = str.toUpperCase().indexOf(" FROM");
	String listString = str.substring(selectIndex+6, fromIndex);
	if(performOper(listString) .equals( "*")){
	return colNames;
	}
	int[] subQueryCounter2 = this.getSubQueryConfirmation(listString);
	ArrayList<Integer> indexOfComma = new ArrayList<Integer>();
	ArrayList<String> eachCommaElement = new ArrayList<String>();
	int tempIndexComma = listString.indexOf(",");
	while(tempIndexComma >=0){
		if(subQueryCounter2[tempIndexComma] == 0){
			indexOfComma.add(tempIndexComma);
		}
		tempIndexComma = listString.indexOf(",",tempIndexComma+1);
	}
	if(indexOfComma.isEmpty()){
	}
	if(indexOfComma.size() ==0){
		eachCommaElement.add(listString);
	}else{
		for(int i=0;i<indexOfComma.size();i++){
			if(i==0){
				eachCommaElement.add(listString.substring(0,indexOfComma.get(0)));
			}else{
				eachCommaElement.add(listString.substring(indexOfComma.get(i-1), indexOfComma.get(i)));
			}
		}
		eachCommaElement.add(listString.substring(indexOfComma.get(indexOfComma.size()-1)));
	}
	for(int i=0;i<eachCommaElement.size();i++){
		if(eachCommaElement.get(i).indexOf(",") ==0){
			eachCommaElement.set(i, eachCommaElement.get(i).replaceFirst(",",""));
		}
	}
	String[] eachElement = new String[eachCommaElement.size()];
	for(int i=0;i<eachCommaElement.size();i++){
		eachElement[i] = eachCommaElement.get(i);
	}
	return eachElement;
	}
	/**
	 * provide select select query result of one column as ArrayList
	 *
	 * <p>
	 * This method will take 3 inputs
	 * 	a) select query in MySQL format with given specifications
	 * 	b) host name to connect
	 * 	c) port number to connect
	 * <p>
	 *
	 * <p>
	 * If more than one column is asked in query this will result first column found
	 * <p>
	 *
	 *
	 * @param		str				input string with sql query to select
	 * @param		host			host name
	 * @param		port			port number
	 *
	 *
	 * @return		returnList		ArrayList of required one column or function
	 */
	public ArrayList<String> selectAsArray(String str,String host,int port){
		try{
			ArrayList<String> returnList = new ArrayList<String>();
			String checkSingleValueMethods = this.singleValueOfMethods(str,host,port);
			if(checkSingleValueMethods.indexOf("NO") ==0){
			int colNameToReturn = 0;
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( databaseName );
			DBCollection coll = db.getCollection(tableName);
			int[] count = getSubQueryConfirmation( str);
			int[] mainTagsIndex = mainClauses(str,count);
			BasicDBObject whereCondition = WhereStatement(str,mainTagsIndex,host,port);
			String[] columnNamesToReturned = selectColumnNamesToReturned(str,host,port);
			for(int i=0;i<columnNamesToReturned.length;i++){
				if(!tableName .equals(tempTableName)){
					columnNamesToReturned[i] = columnNamesToReturned[i].replace(tempTableName+".", "");
				}
				columnNamesToReturned[i] = this.performOper(columnNamesToReturned[i]);
			}
			BasicDBObject sortQuery = sort(str,mainTagsIndex,host,port);
			DBCursor cursor = coll.find(whereCondition);
			cursor.sort(sortQuery);
			String[] limitOffset = limitOffset(str,host,port).split(":");
			int limitValue = Integer.parseInt(limitOffset[0]);
			int offsetValue = Integer.parseInt(limitOffset[1]);
			int counter = cursor.count();
			if( limitValue !=0 && offsetValue !=0){
				cursor.skip(offsetValue);
				cursor.limit(limitValue);
				counter = counter-offsetValue;
				if(counter >limitValue){
					counter = limitValue;
				}
			}else if(counter > limitValue && limitValue !=0){
					cursor.limit(limitValue);
				counter = limitValue;
			}else if(offsetValue !=0){
				cursor.skip(offsetValue);
				counter = counter-offsetValue;
			}
			counter=0;
			while (cursor.hasNext()){
				cursor.next();
				String value = cursor.curr().get(columnNamesToReturned[0]).toString();
				returnList.add(value);
					counter++;
			}
		}
			if(checkSingleValueMethods.indexOf("YES:") ==0){
				String valuess = checkSingleValueMethods.replaceFirst("YES:", "");
				returnList.add(valuess);
			}
			return returnList;
			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}
			return null;
		}
	/**
	 * <p>
	 * This method provide result with single value results
	 * <p>
	 *
	 *<p>
	 * This method will take 3 inputs
	 * 	a) select query in MySQL format with given specifications
	 * 	b) host name to connect;
	 * 	c) port number to connect
	 * <p>
	 *
	 *
	 *
	 * @param		str				input string with sql query to select
	 * @param		host			host name
	* @param		port			port number
	 *
	 * @return		value 			single value output
	 */
	public String selectSingleValue(String str,String host,int port){
		String value =this.singleValueOfMethods(str,host,port);
			if(value.indexOf("YES:") ==0){
				value= value.replaceFirst("YES:", "");
			}
		return value;
	}
	/**
	 * <p>
	 * This method provide value for MySQL functions namely :- COUNT,MAX,MIN,AVG,SUM
	 * <p>
	 *
	 *<p>
	 * This method will take 3 inputs
	 * 	a) select query in MySQL format with given specifications
	 * 	b) host name to connect;
	 * 	c) port number to connect
	 * <p>
	 *
	 * <p>
	 * This method check the presence of listed MySQL functions and if function is present it will return "YES:(Value)".
	 * Else it will return "NO:NO"
	 * <p>
	 *
	 *
	 * @param		str				input string with sql query to select
	 * @param		host			host name
	 * @param		port			port number
	 *
	*
	 * @return						required value
	 */
	public String singleValueOfMethods(String str,String host,int port){
		String[] methodsToDetect = {"COUNT","MAX","MIN","AVG","SUM"};
		String[] columnNames = selectColumnNamesToReturned(str,host,port);
		int methodId =-1;
		for(int i=0;i<methodsToDetect.length;i++){
			if(columnNames[0] .contains(methodsToDetect[i])){
					methodId =i;
				break;
			}
		}
		if(methodId == -1){
			return "NO:NO";
		}
		String colName = columnNames[0].substring(columnNames[0].indexOf("(")+1,columnNames[0].indexOf(")"));
		int[] subCounter = this.getSubQueryConfirmation(str);
		int[] clauseIdentified = this.mainClauses(str, subCounter);
		int indexWhereTemp = str.toUpperCase().indexOf("WHERE ");
		int indexOfWhere=0;
		String whereForQuery ="";
		if(indexWhereTemp <0){
			indexOfWhere=-1;
		}
		while(indexWhereTemp >= 0) {
			if(subCounter[indexWhereTemp] ==0){
				indexOfWhere = indexWhereTemp;
				break;
			}
			indexWhereTemp = str.toUpperCase().indexOf("WHERE ", indexWhereTemp+1);
		}
		colName = this.performOper(colName);
		if(methodId ==0){
		String query="";
		if(indexOfWhere == -1){
			query = "SELECT "+colName+" FROM "+tableName;
		}else{
			query = "SELECT "+colName+" FROM "+tableName+" "+str.substring(indexOfWhere);
		}
		ArrayList<String> values = this.selectAsArray(query,host,port);
		return "YES:"+values.size();
		}else if(methodId ==1){
			String query="";
			if(indexOfWhere == -1){
				query = "SELECT "+colName+" FROM "+tableName+" LIMIT 5 ORDER BY "+colName+" DESC ";
			}else{
					query = "SELECT "+colName+" FROM "+tableName+" "+str.substring(indexOfWhere)+" LIMIT 5 ORDER BY "+colName+" DESC ";
			}
			ArrayList<String> values = this.selectAsArray(query,host,port);
			return "YES:"+values.get(0);
		}else if(methodId ==2){
			String query="";
			if(indexOfWhere == -1){
				query = "SELECT "+colName+" FROM "+tableName;
			}else{
				query = "SELECT "+colName+" FROM "+tableName;
			}
			ArrayList<String> values = this.selectAsArray(query,host,port);
			return "YES:"+values.get(0);
			}else if(methodId ==3){
				String query="";
				if(indexOfWhere == -1){
					query = "SELECT "+colName+" FROM "+tableName+" LIMIT 5 ORDER BY "+colName+" ";
				}else{
					query = "SELECT "+colName+" FROM "+tableName+" "+str.substring(indexOfWhere)+" LIMIT 5 ORDER BY "+colName+" ";
				}
				double value=0.0;
				int counter=0;
				ArrayList<String> values = this.selectAsArray(query,host,port);
				for(int i=0;i<values.size();i++){
					value = value + Double.parseDouble(values.get(i));
					counter++;
				}
				value = value/counter;
				String toString= Double.toString(value);
				return "YES:"+toString;
			}else if(methodId ==4){
					String query="";
				if(indexOfWhere == -1){
					query = "SELECT "+colName+" FROM "+tableName+" LIMIT 5 ORDER BY "+colName+" ";
				}else{
					query = "SELECT "+colName+" FROM "+tableName+" "+str.substring(indexOfWhere)+" LIMIT 5 ORDER BY "+colName+" ";
				}
				double value=0.0;
				ArrayList<String> values = this.selectAsArray(query,host,port);
				for(int i=0;i<values.size();i++){
					value = value + Double.parseDouble(values.get(i));
					}
				String toString= Double.toString(value);
					return "YES:"+toString;
				}else{
				return "NO:NO";
			}
		}
	/*
	 * <p>
	 * This method set temporary table name if provided in query
	 * <p>
	 *
	 * @param 		str 	input string with sql query to select
	 *
	 */
	public void setTempTableName(String str){
		int[] subQueryChecker = this.getSubQueryConfirmation(str);
		int[] clausesIndex = this.mainClauses(str, subQueryChecker);
		int indexOfRequiredClause =0;
		for(int i=0;i<clausesIndex.length;i++){
			if(clausesIndex[i] !=(-1) ){
				indexOfRequiredClause = clausesIndex[i];
				break;
			}
		}
		String requiredName = this.performOper(str.substring(str.toUpperCase().indexOf("FROM "),indexOfRequiredClause).replaceFirst("FROM ", ""));
		String[] ava = requiredName.split(" ");
		if(ava[ava.length-1].equals(tableName)){
		}else{
			this.tempTableName = ava[ava.length-1];
			for(int i=0;i<this.tempColNames.length;i++){
				this.tempColNames[i] = this.tempTableName+"."+this.tempColNames[i];
				System.out.println(tempColNames[i]);
			}
		}
		}
	/*
	 * <p>
	 * This method handles MySQL delete Query
	 * <p>
	 *
	 * <p>
	 * This method will take 3 inputs
	 * 	a) select query in MySQL format with given specifications
	 * 	b) host name to connect;
	 * 	c) port number to connect
	 * <p>
	 *
	 * <p>
	 * It identifies where condition and on the basis of conditions provided it will interact with MongDB database and perform delete operation;
	 * To make it more secure we made it in such a way that user must provide a valid condition to make operation
	 * <p>
	 *
	 * @param		str				input string with sql query to select
	 * @param		host			host name
	 * @param		port			port number
	 *
	 */
	public void delete(String str,String host,int port){
		try{
			MongoClient mongoClient = new MongoClient(host,port);
			DB db = mongoClient.getDB( databaseName );
			System.out.println("Connect to database successfully");
			DBCollection coll = db.getCollection(tableName);
		System.out.println("Collection mycol selected successfully");
		int[] count = getSubQueryConfirmation( str);
		int[] mainTagsIndex = mainClauses(str,count);
			BasicDBObject whereCondition = WhereStatement(str,mainTagsIndex,host,port);
			if(whereCondition != null){
				WriteResult cursor = coll.remove(whereCondition);
			}else{
				WriteResult cursor = coll.remove(null);
			}
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	/*
	 * <p>
	 * This method handles MySQL update Query
	 * <p>
	 *
	 * <p>
	 * This method will take 3 inputs
	 * 	a) select query in MySQL format with given specifications
	 * 	b) host name to connect;
	 * 	c) port number to connect
	 * <p>
	 *
	 * <p>
	 * It identifies where condition and on the basis of conditions provided it will interact with MongDB database and perform delete operation
	 * <p>
	 *
	 * @param		str				input string with sql query to select
	 * @param		host			host name
	 * @param		port			port number
	 *
	 */
	public void update(String str,String host,int port){
		try{
			MongoClient mongoClient = new MongoClient( host,port );
			DB db = mongoClient.getDB( databaseName );
			System.out.println("Connect to database successfully");
			 DBCollection coll = db.getCollection(tableName);
			System.out.println("Collection mycol selected successfully");
		int[] count = getSubQueryConfirmation( str);
		int[] mainTagsIndex = mainClauses(str,count);
			BasicDBObject whereQuery = WhereStatement(str,mainTagsIndex,host,port);
			BasicDBObject setQuery = UpdateSetQuery(str,host,port);
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set",setQuery);
			coll.updateMulti(whereQuery, updateQuery);
			     DBCursor cursor = coll.find();
			System.out.println("Document updated successfully");
			     int i = 1;
			while (cursor.hasNext()) {
				System.out.println("Updated Document: "+i);
				System.out.println(cursor.next());
				        i++;
			}
		 }catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	}
	/*
	 * <p>
	 * This method is helper function of insert method
	 * <p>
	 *
	 * <p>
	 * This method will take 4 inputs
	 * 	a) Column names to be inserted
	 * 	b) each value for each time insert
	 * 	c) host name to connect;
	 * 	d) port number to connect
	 * <p>
	 *
	 * <p>
	 * This method do the main inserting operation and insert each row value of MySQL table in corresponding Mongodb collection
	 * <p>
	 *
	 * @param		colsNames		column names in which data to be inserted
	 * @param		eachValue		Value of each row
	 * @param		host			host name
	 * @param		port			port number
	 *
	 */

	public void InsertQueryGenerator(String[] colsNames,ArrayList<String> eachValue,String host,int port){
		try{
			MongoClient mongoClient = new MongoClient( host,port);
			DB db = mongoClient.getDB( databaseName );
			System.out.println("Connect to database successfully");
			DBCollection coll = db.getCollection(tableName);
			System.out.println("Collection mycol selected successfully");
			BasicDBObject doc = new BasicDBObject();
			for(int j=0;j<colsNames.length;j++){
				int i=0;
				for(int k=0;k<colNames.length;k++){
					if(colsNames[j] .equals(colsNames[k])){
						i=k;
						break;
					}
				}
				if(colTypes[i] .equals("int")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i],Integer.parseInt(performOper(eachValue.get(i))));
					}
				}
				if(colTypes[i] .equals("boolean")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i],Boolean.parseBoolean(performOper(eachValue.get(i))));
					}
				}
				if(colTypes[i] .equals("long")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i],Long.parseLong(performOper(eachValue.get(i))));
					}
				}
				if(colTypes[i] .equals("float")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i],Float.parseFloat(performOper(eachValue.get(i))));
					}
				}
				if(colTypes[i] .equals("double")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i],Double.parseDouble(performOper(eachValue.get(i))));
					}
				}
				if(colTypes[i] .equals("String")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i],(performOper(eachValue.get(i))));
					}
				}
				if(colTypes[i] .equals("Date")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i],(performOper(eachValue.get(i))));
					}
				}
				if(colTypes[i] .equals("Timestamp")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i],StringToTimestamp(performOper(eachValue.get(i))));
					}
				}
				if(colTypes[i] .equals("Time")){
					if(eachValue.get(i) .equals("NULL") || eachValue.get(i) .equals("null")){
						doc.append(colNames[i],null);
					}else{
						doc.append(colNames[i], (performOper(eachValue.get(i))));
					}
				}
			}
			coll.insert(doc);
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	/**
	 * <p>
	 * This method read input sql query for select and identify "LIMIT" and "OFFSET" value
	 * <p>
	 *
	 * <p>
	 * This method will take 3 inputs
	 * 	a) select query in MySQL format with given specifications
	 * 	b) host name to connect;
	 * 	c) port number to connect
	 * <p>
	 *
	 * @param 		str		input select query
	 * @param		host	host name
	 * @param		port	port number
	 *
	 *
	 * @return				return values of limit and offset separated by ":"
	 */
	public String limitOffset(String str,String host,int port){
		int indexLimit = 0;
		int indexOffset = 0;
		String[] tokens = str.split(" ");
		for(int i=0;i<tokens.length;i++){
			if(tokens[i] .equals("LIMIT")){
		indexLimit = i;
		}
		if(tokens[i] .equals("OFFSET")){
		indexOffset = i;
			}
			}
		if(indexLimit >0 && indexOffset >0){
		return tokens[indexLimit+1]+":"+tokens[indexOffset+1];
		}
		if(indexLimit >0){
		return tokens[indexLimit+1]+":0";
			}
		if(indexOffset >0){
		return "0:"+tokens[indexOffset+1];
		}
			return "1000000:0";
		}
	/**
	 * <p>
	 * This method is helper function of update method
	 * <p>
	 *
	 * <p>
	 * This method will take 3 inputs
	 * 	a) update query in MySQL format with given specifications
	 * 	b) host name to connect;
	 * 	c) port number to connect
	 * <p>
	 *
	 * <p>
	 * This method determine required conditions for update Query and return BasicDBObject including conditions
	 * <p>
	 *
	 * @param 		str			input update query
	 * @param		host		host name
	 * @param		port		port number
	 *
	 * @return		setQuery	Update conditions in the form of BasicDBObject
	 *
	 */
		public BasicDBObject UpdateSetQuery(String str,String host,int port){
		BasicDBObject setQuery = new BasicDBObject();
		int indexSet = str.toUpperCase().toUpperCase().indexOf(" SET ");
		int indexOfWhere = str.toUpperCase().toUpperCase().indexOf(" WHERE ");
		String test ="";
		if(indexOfWhere >0){
			test = str.substring(indexSet+4, indexOfWhere);
		}else{
				int indexEnd = str.toUpperCase().indexOf(";");
			if(indexEnd==(-1)){
				indexEnd = str.length();
			}
			test = str.substring(indexSet, indexEnd);
		}
		String[] eachSet = test.split(",");
		for(int i=0;i<eachSet.length;i++){
			String[] nameValue = eachSet[i].split("=");
			int colTypeIdentifiedId = 0;
			for(int k=0;k<colTypes.length;k++){
				if(performOper(nameValue[0]) .equals(colNames[k])){
					colTypeIdentifiedId = k;
					break;
				}
			}
			String colType = colTypes[colTypeIdentifiedId];
			if(colType == "int"){
				setQuery.append(performOper(nameValue[0]),Integer.parseInt(performOper(nameValue[1])));
			}
			if(colType == "boolean"){
				setQuery.append(performOper(nameValue[0]),Boolean.parseBoolean(performOper(nameValue[1])));
			}
			if(colType == "long"){
				setQuery.append(performOper(nameValue[0]),Long.parseLong(performOper(nameValue[1])));
			}
			if(colType == "float"){
				setQuery.append(performOper(nameValue[0]),Float.parseFloat(performOper(nameValue[1])));
			}
			if(colType == "double"){
				setQuery.append(performOper(nameValue[0]),Double.parseDouble(performOper(nameValue[1])));
			}
			if(colType == "String"){
				setQuery.append(performOper(nameValue[0]),(performOper(nameValue[1])));
			}
			if(colType == "Date"){
				setQuery.append(performOper(nameValue[0]),(performOper(nameValue[1])));
			}
			if(colType == "Timestamp"){
				setQuery.append(performOper(nameValue[0]),StringToTimestamp(performOper(nameValue[1])));
			}
			if(colType == "Time"){
				setQuery.append(performOper(nameValue[0]), (performOper(nameValue[1])));
			}
		}
		return setQuery;
	}
	/**
	 * <p>
	 * This method is helper function that handles sort queries
	 * <p>
	 *
	 * <p>
	 * This method will take 4 inputs
	 * 	a) query in MySQL format with given specifications
	 * 	b) index of required tags found
	 * 	c) host name to connect;
	 * 	d) port number to connect
	 * <p>
	 *
	 * <p>
	 * This method determine required conditions for by sort statement and return BasicDBObject including sort conditions
	 * <p>
	 *
	 * @param 		str			input update query
	 * @param		tagsIndex	required detected tags
	 * @param		host		host name
	 * @param		port		port number
	 *
	 * @return		sortQuery	sort conditions in the form of BasicDBObject
	 *
	 */
	public BasicDBObject sort(String str,int[] tagsIndex,String host,int port){
	BasicDBObject sortQuery = new BasicDBObject();
	String sortStatement ="";
	int indexOfSort = str.toUpperCase().indexOf("ORDER BY ");
	if(indexOfSort <0){
		sortQuery.append(this.colNames[this.primaryKeyId], 1);
		return sortQuery;
	}
	int requiredIndex=0;
	for(int i=0;i<tagsIndex.length;i++){
		if(indexOfSort == tagsIndex[i]){
			requiredIndex=i;
			break;
		}
	}
	sortStatement = str.substring(tagsIndex[requiredIndex], tagsIndex[requiredIndex+1]);
	sortStatement =sortStatement.replaceAll("ORDER BY ","");
	sortStatement =sortStatement.replaceAll("order by ","");
	String[] eachElement = sortStatement.split(",");
		for(int i=0;i<colNames.length;i++){
		for(int j=0;j<eachElement.length;j++){
			if(eachElement[j].contains(colNames[i])){
					if(eachElement[j].contains("DESC")){
					sortQuery.append(colNames[i],-1);
			}else{
				sortQuery.append(colNames[i],1);
			}
		}
	}
	}
	return sortQuery;
	}
	/**
	 * <p>
	 * This methods handles all the where related conditions and user various helper methods to get exact condition/value query need
	 * <p>
	 *
	 * <p>
	 * This method takes 4 inputs
	 * 	a) query in MySQL format with given specifications
	 * 	b) index of required tags found
	 * 	c) host name to connect;
	 * 	d) port number to connect
	 * <p>
	 *
	 * <p>
	 * It will return where condition in BasicDBObject which can later be queried with mongodb database
	 * <p>
	 *
	 * @param 		str					input update query
	 * @param		tagsIndex			required detected tags
	 * @param		host				host name
	 * @param		port				port number
	 *
	 * @return		whereQueryFinal		where conditions in form of BasicDBObject
	 */


	public BasicDBObject WhereStatement(String str,int[] tagsIndex,String host,int port){
		if(!tableName .equals(tempTableName)){
			for(int i=0;i<colNames.length;i++){
				tempColNames[i] = tempTableName+"."+colNames[i];
			}
		}
		String WhereStatement ="";
		BasicDBObject whereQueryFinal = new BasicDBObject();

		int[] subQueryCounter = getSubQueryConfirmation(str);
		int indexWhereTemp = str.toUpperCase().indexOf("WHERE ");
		if(indexWhereTemp <0){
			return null;
		}
		int indexOfWhere=0;
		while(indexWhereTemp >= 0) {
			if(subQueryCounter[indexWhereTemp] ==0){
				indexOfWhere = indexWhereTemp;
				break;
			}
			indexWhereTemp = str.toUpperCase().indexOf("WHERE ", indexWhereTemp+1);
		}
		if(indexOfWhere ==0){
			return null;
		}
		int requiredIndex=0;
		for(int i=0;i<tagsIndex.length;i++){
			if(indexOfWhere == tagsIndex[i]){
				requiredIndex=i;
				break;
			}
		}
		WhereStatement = str.substring(tagsIndex[requiredIndex], tagsIndex[requiredIndex+1]);
		WhereStatement =WhereStatement.replaceFirst("WHERE ","");
		WhereStatement =WhereStatement.replaceFirst("where ","");
		int[] subQueryCounter2 = getSubQueryConfirmation(WhereStatement);
		ArrayList<Integer> indexOfOr = new ArrayList<Integer>();
		ArrayList<String> eachOrQuery = new ArrayList<String>();
		int tempIndexOr = WhereStatement.toUpperCase().indexOf("OR ");
		while(tempIndexOr >=0){
			if(subQueryCounter2[tempIndexOr] == 0){
				indexOfOr.add(tempIndexOr);
			}
		tempIndexOr = WhereStatement.toUpperCase().indexOf("OR ",tempIndexOr+1);
		}
		List<BasicDBObject> orLoopObject = new ArrayList<BasicDBObject>();
		if(indexOfOr.size() ==0){
			eachOrQuery.add(WhereStatement);
		}else{
			for(int i=0;i<indexOfOr.size();i++){
				if(i==0){
					eachOrQuery.add(WhereStatement.substring(0,indexOfOr.get(0)));
				}else{
					eachOrQuery.add(WhereStatement.substring(indexOfOr.get(i-1), indexOfOr.get(i)));
				}
			}
			eachOrQuery.add(WhereStatement.substring(indexOfOr.get(indexOfOr.size()-1)));
		}
		for(int i=0;i<eachOrQuery.size();i++){
			if(eachOrQuery.get(i).toUpperCase().indexOf("OR ") ==0){
				eachOrQuery.set(i, eachOrQuery.get(i).replaceFirst("OR ",""));
			}
			eachOrQuery.set(i,performOper(eachOrQuery.get(i)));
		}
		for(int kk=0;kk<eachOrQuery.size();kk++){
			BasicDBObject testQuery = new BasicDBObject();
			int[] subQueryCounterEachColumn = getSubQueryConfirmation(eachOrQuery.get(kk));
			ArrayList<String> indexOfEachColumn = new ArrayList<String>();
			ArrayList<String> eachColumnQuery = new ArrayList<String>();
			for(int i=0;i<tempColNames.length;i++){
				int tempIndexColumn = eachOrQuery.get(kk).indexOf(tempColNames[i]);
				while(tempIndexColumn >=0){
					if(subQueryCounterEachColumn[tempIndexColumn] == 0){
						indexOfEachColumn.add(Integer.toString(tempIndexColumn)+":"+Integer.toString(i));
					}
					tempIndexColumn = eachOrQuery.get(kk).indexOf(tempColNames[i],tempIndexColumn+1);
				}
			}
			for(int i=0;i<indexOfEachColumn.size();i++){
				String[] tempSplit = indexOfEachColumn.get(i).split(":");
				int tempIndexOfCol = Integer.parseInt(tempSplit[0]);
				if(i ==(indexOfEachColumn.size()-1)){
					eachColumnQuery.add(eachOrQuery.get(kk).substring(tempIndexOfCol));
				}else{
					String[] tempSplit1 = indexOfEachColumn.get(i+1).split(":");
					int tempIndexOfCol1 = Integer.parseInt(tempSplit1[0]);
					eachColumnQuery.add(performOper(eachOrQuery.get(kk).substring(tempIndexOfCol,tempIndexOfCol1)));
				}
			}
			for(int i=0;i<eachColumnQuery.size();i++){
				if(eachColumnQuery.get(i).toUpperCase().indexOf("AND") ==(eachColumnQuery.get(i).length()-3)){
					eachColumnQuery.set(i,eachColumnQuery.get(i).substring(0, eachColumnQuery.get(i).length()-3));
				}
			}
			int[] indexOfEachColumnInWhere = new int[colNames.length];
			int[] indexOfEachColumnNumber = new int[colNames.length];
			int[] subQueryCounter3 = getSubQueryConfirmation(eachOrQuery.get(kk));
			for(int i=0;i<tempColNames.length;i++){
				indexOfEachColumnInWhere[i] = -1;
				int tempIndexOfCol = eachOrQuery.get(kk).indexOf(tempColNames[i]);
				while(tempIndexOfCol >= 0) {
					if(subQueryCounter3[tempIndexOfCol] ==0){
						indexOfEachColumnInWhere[i] = tempIndexOfCol;
						break;
					}
					tempIndexOfCol = str.indexOf(tempColNames[i], tempIndexOfCol+1);
				}
			}
			int aa=0;
			while(aa<eachColumnQuery.size()){
				int clauseIdentified = findClause(eachColumnQuery.get(aa));
				if(clauseIdentified ==1){
					String test = eachColumnQuery.get(aa);
					int colIdInWhere=0,sqlComparatorId=0;
					for(int i=0;i<tempColNames.length;i++){
						if(test.indexOf(tempColNames[i]) <5 && test.indexOf(tempColNames[i]) !=(-1)){
							colIdInWhere = i;
							break;
						}
					}
					for(int i=0;i<sqlComparators.length;i++){
						if(test.contains(sqlComparators[i])){
							sqlComparatorId=i;
						}
					}
					String columnTypeIdentified = colTypes[colIdInWhere];
			if(columnTypeIdentified .equals("int")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			int value = Integer.parseInt(performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
			if(columnTypeIdentified .equals("boolean")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			boolean value = Boolean.parseBoolean(performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
			if(columnTypeIdentified .equals("long")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			long value = Long.parseLong(performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
			if(columnTypeIdentified .equals("float")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			float value = Float.parseFloat(performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
			if(columnTypeIdentified .equals("double")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			double value = Double.parseDouble(performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
			if(columnTypeIdentified .equals("String")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			String value = (performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
			if(columnTypeIdentified .equals("String")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			String value = (performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
			if(columnTypeIdentified .equals("Timestamp")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			Timestamp value = StringToTimestamp(performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
			if(columnTypeIdentified .equals("String")){
			String[] br = test.split(sqlComparators[sqlComparatorId],2);
			String value =  (performOper(getValue(br[1],host,port).get(0)));
			if(sqlComparators[sqlComparatorId] == "="){
				testQuery.put(colNames[colIdInWhere],value);
			}
			if(sqlComparators[sqlComparatorId] == "!="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$ne",value));
			}
			if(sqlComparators[sqlComparatorId] == "<"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lt",value));
			}
			if(sqlComparators[sqlComparatorId] == ">"){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gt",value));
			}
			if(sqlComparators[sqlComparatorId] == "<="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$lte",value));
			}
			if(sqlComparators[sqlComparatorId] == ">="){
				testQuery.put(colNames[colIdInWhere], new BasicDBObject("$gte",value));
			}
			}
				aa++;
			}else if(clauseIdentified ==2){
					String test = eachColumnQuery.get(aa);
					int colIdInWhere=0,sqlComparatorId=0;
					for(int i=0;i<tempColNames.length;i++){
						if(test.indexOf(tempColNames[i]) <5 && test.indexOf(tempColNames[i]) !=(-1)){
							colIdInWhere = i;
							break;
						}
					}
					for(int i=0;i<sqlComparators.length;i++){
						if(test.contains(sqlComparators[i])){
							sqlComparatorId=i;
						}
					}
					String columnTypeIdentified = colTypes[colIdInWhere];
					test = test.replaceFirst(tempColNames[colIdInWhere], "");
					test = test.replaceFirst("IN ", "");
					String[] aaaa = getInClauseValue(test,host,port);
					if(columnTypeIdentified .equals("int")){
					int[] values = new int[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] =Integer.parseInt(performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
					if(columnTypeIdentified .equals("boolean")){
					boolean[] values = new boolean[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] =Boolean.parseBoolean(performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
					if(columnTypeIdentified .equals("long")){
					long[] values = new long[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] =Long.parseLong(performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
					if(columnTypeIdentified .equals("float")){
					float[] values = new float[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] =Float.parseFloat(performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
					if(columnTypeIdentified .equals("double")){
					double[] values = new double[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] =Double.parseDouble(performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
					if(columnTypeIdentified .equals("String")){
					String[] values = new String[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] =(performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
					if(columnTypeIdentified .equals("String")){
					String[] values = new String[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] =(performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
					if(columnTypeIdentified .equals("Timestamp")){
					Timestamp[] values = new Timestamp[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] =StringToTimestamp(performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
					if(columnTypeIdentified .equals("String")){
					String[] values = new String[aaaa.length];
						for(int i=0;i<values.length;i++){
							values[i] = (performOper(aaaa[i]));
						}
						testQuery.put(colNames[colIdInWhere], new BasicDBObject("$in",values));
					}
				aa++;
			}else if(clauseIdentified == 3){
				String test = eachColumnQuery.get(aa);
				int colIdInWhere=0,sqlComparatorId=0;
				for(int i=0;i<tempColNames.length;i++){
					if(test.indexOf(tempColNames[i]) <5 && test.indexOf(tempColNames[i]) !=(-1)){
						colIdInWhere = i;
						break;
					}
				}
				for(int i=0;i<sqlComparators.length;i++){
					if(test.contains(sqlComparators[i])){
						sqlComparatorId=i;
					}
				}
				String columnTypeIdentified = colTypes[colIdInWhere];
				test = test.replaceFirst(tempColNames[colIdInWhere], "");
				test = test.replaceFirst("BETWEEN ", "");
				int[] subQueryCounterTemp = this.getSubQueryConfirmation(test);
				int indexOfAnd = test.toUpperCase().indexOf(" AND ");
				String query1="",query2="";
				while(indexOfAnd >=0){
					if(subQueryCounterTemp[indexOfAnd] == 0){
						query1 = test.substring(0, indexOfAnd);
						query2 = test.substring(indexOfAnd+5);
						break;
					}
					indexOfAnd = test.toUpperCase().indexOf(" AND ",indexOfAnd+1);
				}
				eachOrQuery.add(tempColNames[colIdInWhere]+">="+query1);
				eachOrQuery.add(tempColNames[colIdInWhere]+"<="+query2);
				aa++;
			}else if(clauseIdentified == 4){
				String test = eachColumnQuery.get(aa);
				int colIdInWhere=0,sqlComparatorId=0;
				for(int i=0;i<tempColNames.length;i++){
					if(test.indexOf(tempColNames[i]) <5 && test.indexOf(tempColNames[i]) !=(-1)){
						colIdInWhere = i;
						break;
					}
				}
				for(int i=0;i<sqlComparators.length;i++){
					if(test.contains(sqlComparators[i])){
						sqlComparatorId=i;
					}
				}
				String columnTypeIdentified = colTypes[colIdInWhere];
				test = test.replaceFirst(tempColNames[colIdInWhere], "");
				test = test.replaceFirst("LIKE ", "");
				String aaaa = getLikeClauseValue(test,host,port);
				Pattern regex = Pattern.compile(aaaa); 
				testQuery.put(colNames[colIdInWhere], regex);
				aa++;
			}
			orLoopObject.add(testQuery);
			}
			whereQueryFinal.put("$or", orLoopObject);
			}
			return whereQueryFinal;
		}


	/**
	 * <p>
	 * This method returns actual value to be used in where condition.
	 * <p>
	 *
	 * <p>
	 * This method takes 3 inputs:-
	 * 	a) query which basically contains value portion
	 * 	b) hostname of required mongodb
	 * 	c) port number of the same
	 * <p>
	 *
	 * <p>
	 * This method checks the whether the input contains actual value or subquery to return set of values
	 * <p>
	 *
	 * @param 		str		query portion containing where values
	 * @param 		host	host name
	 * @param 		port	port number
	 *
	 * @return		value  	values in the form of arraylist
	 */
		public ArrayList<String> getValue(String str,String host,int port){
			ArrayList<String> value = new ArrayList<String>();
			int counter=0;
			int[] subQueryCounter = getSubQueryConfirmation(str);
			int indexOfSelect = str.toUpperCase().indexOf("SELECT ");
			int selectCounter=0;
			while(indexOfSelect >=0){
				if(subQueryCounter[indexOfSelect] ==0){
					selectCounter++;
					break;
				}
				indexOfSelect = str.toUpperCase().indexOf("SELECT ",indexOfSelect+1);
			}
			for(int i=0;i<subQueryCounter.length;i++){
				if(subQueryCounter[i] ==0){
					counter++;
				}
			}
			if(counter ==subQueryCounter.length && selectCounter==0){
				value.add(str);
					return value;
			}
			str = performOper(str);
			languageDAO test = new languageDAO();
			ArrayList<String> a = test.selectAsArray(str,host,port);
				return a;
			}

		
		/**
		 * <p>
		 * This method provide the values in IN clause is detected
		 * <p>
		 * 
		 * <p>
		 * This method takes 3 inputs:-
		 * 	a) query which basically contains value portion
		 * 	b) hostname of required mongodb
		 * 	c) port number of the same
		 * <p>
		 * 
		 * <p>
		 * This method checks the whether the input contains 'IN' clause regarding conditions and use helper method 'getValue'
		 * <p>
		 * 
		 * @param 		str		query portion containing where values
		 * @param 		host	host name
		 * @param 		port	port number
		 * 
		 * @return		finalValuesString  	values of IN clause
		 */
		public String[] getInClauseValue(String str,String host,int port){
			System.out.println(str);
			str = performOper(str);
			int[] subQueryCheck = getSubQueryConfirmation(str);
			
			for(int i=0;i<subQueryCheck.length;i++){
				System.out.println(subQueryCheck[i] +"  "+ str.charAt(i));
			}
			ArrayList<Integer> indexComma = new ArrayList<Integer>();
			ArrayList<String> inClauseValues = new ArrayList<String>();
			ArrayList<String> finalValues = new ArrayList<String>();
	
			ArrayList<String> finalValuesToReturn = new ArrayList<String>();
			int tempCommaIndex = str.indexOf(",");
			
			while(tempCommaIndex >=0){
				if(subQueryCheck[tempCommaIndex] ==0){
					indexComma.add(tempCommaIndex);
				}
				tempCommaIndex = str.indexOf(",", tempCommaIndex+1);
			}
			if(indexComma.size() ==0){
				
				finalValues = this.getValue(str,host,port);
				
			}else{
				for(int i=0;i<indexComma.size();i++){
					if(i==0){
						finalValues.add(str.substring(0,indexComma.get(0)));
						System.out.println(finalValues);
						
	
						
					}else{
						finalValues.add(str.substring(indexComma.get(i-1)+1, indexComma.get(i)));
	
					}
					
				}
				finalValues.add(str.substring(indexComma.get(indexComma.size()-1)+1));
			}
			
			for(int i=0;i<finalValues.size();i++){
				ArrayList<String> valueTemp = this.getValue(finalValues.get(i),host,port);
				for(int j=0;j<valueTemp.size();j++){
					finalValuesToReturn.add(valueTemp.get(j));
				}
	
			}
			String[] finalValuesString = new String[finalValuesToReturn.size()];
			finalValuesString = finalValuesToReturn.toArray(finalValuesString);
	
			return finalValuesString;
		}
		


		/**
		 * <p>
		 * This method provide the values in LIKE clause is detected
		 * <p>
		 * 
		 * <p>
		 * This method takes 3 inputs:-
		 * 	a) query which basically contains value portion
		 * 	b) hostname of required mongodb
		 * 	c) port number of the same
		 * <p>
		 * 
		 * <p>
		 * This method checks the whether the input contains 'LIKE' clause regarding conditions and use helper method 'getValue'
		 * <p>
		 * 
		 * @param 		str		query portion containing where values
		 * @param 		host	host name
		 * @param 		port	port number
		 * 
		 * @return		finalValuesString  	values of LIKE clause
		 */
		public String getLikeClauseValue(String str,String host,int port){
			System.out.println(str);
			ArrayList<String> test = this.getValue(str,host,port);
			String value = test.get(0);
			value = this.performOper(value);
			if(value.charAt(0) == '%'){
				value = value.substring(1);
			}
			if(value.charAt(value.length()-1) == '%'){
				value = value.substring(0, value.length()-1);
			}
			System.out.println(value);
			return value;
		}
		
		
		/**
		 * This method identify clause present in query
		 * 
		 * <p>
		 * This method take sql query as input and identify the clause identify
		 * Only four clauses are supported in MongoDB are 'IN','BETWEEN','LIKE','AND'. 'OR' clause is also supported but it detected earlier in select method
		 * <p>
		 * 
		 * @param 		str			sql select Query already trimmed by 'OR' clause	
		 * @return		clauseId	clause id indetified 
		 */
		public int findClause(String str){
			
			int clauseId = 1;
			
			int[] subQueryConfirmation = getSubQueryConfirmation(str);
			int inId= str.indexOf(" IN");
			while(inId >=0){
				if(subQueryConfirmation[inId] == 0){
					 clauseId = 2;
					 break;
				}
				inId = str.indexOf(str,inId+1);
			}
			
			int betweenId= str.indexOf(" BETWEEN");
			while(betweenId >=0){
				if(subQueryConfirmation[betweenId] == 0){
					clauseId = 3;
					break;
				}
				betweenId = str.indexOf(str,betweenId+1);
			}
			
			int likeId = str.indexOf(" LIKE");
			while(likeId >=0){
				if(subQueryConfirmation[likeId] == 0){
					clauseId = 4;
					break;
				}
			}
			
			
			return clauseId;
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