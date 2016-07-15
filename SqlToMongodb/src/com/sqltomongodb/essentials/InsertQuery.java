package com.sqltomongodb.essentials;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.sqltomongodb.additionals.BasicOperation;

/**
 * 
 * @author Akshay P. Singh <akshayps@iitrpr.ac.in>
 *
 */
public class InsertQuery{
	public InsertQuery(){

	}

	
	/**
	 * generate method to handle insert query in sql
	 * 
	 * <p>
	 * This method is used to create 'insert' method in DAO file.
	 * Corresponding to given create table query, this method will create 'insert' method to handle DML select queries.
	 * Filters and sorting are to be appended in this method using 'GetSelectQueryGenerator' method
	 * <p>
	 *  
	 * @return	result		String with insert method 
	 */
	public String getInsertQuery(){
		String result="";
		result+="\n\t/**";
		result+="\n\t * handle insert values in given Mongodb database";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This methods take input sql query to insert values in database and identify values and insert it into corresponding database.";
		result+="\n\t * It uses helper methods to insert values"; 
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 	str		input string with sql query";
		result+="\n\t * @param  host	host Name";
		result+="\n\t * @param  port	port number";
		result+="\n\t *"; 
		result+="\n\t */";
		result+="\n\tpublic void insert(String str,String host,int port){";
		result+="\n\tint indexInsert = str.toUpperCase().indexOf(\"INSERT INTO \");";
	    result+="\n\tStack<Character> st = new Stack<Character>();";
	    result+="\n\tStack<Character> st1= new Stack<Character>();";
	    result+="\n\twhile(indexInsert != (-1)){";
		result+="\n\t\tint indexC = str.indexOf(\";\",indexInsert);";
		result+="\n\t\tString tempStatement = str.substring(indexInsert,indexC+1);";
		result+="\n\t\tString[] tokens = tempStatement.split(\" \",4);";
		result+="\n\t\tif(!performOper(tokens[2]) .equals(tableName)){";
		result+="\n\t\t\tSystem.out.println(\"Syntax Error in Table name\\nRequired Name not found\");";
		result+="\n\t\t\treturn;";
		result+="\n\t\t}";
		result+="\n\t\tint indexValueStart = tempStatement.toUpperCase().indexOf(\"(\",str.toUpperCase().indexOf(\"VALUES\")+1);";
		result+="\n\t\tString valueString = tempStatement.substring(indexValueStart,tempStatement.indexOf(\";\"));";
		result+="\n\t\tArrayList<String> EachRow = new ArrayList<String>();";
		result+="\n\t\tString test=\"\";";
		result+="\n\t\tfor(int i=0;i<valueString.length();i++){";
		result+="\n\t\t\tif(valueString.charAt(i) == '('){";
		result+="\n\t\t\t\tif(st.empty()){";
		result+="\n\t\t\t\t\tst.push('(');";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\ttest+=String.valueOf(valueString.charAt(i));";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(valueString.charAt(i)== '\"'){";	
		result+="\n\t\t\t\tif(!st.empty() && st.peek()=='\"'){";
		result+="\n\t\t\t\t\tst.pop();";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\tif(st.peek() =='('){";
		result+="\n\t\t\t\t\t\tst.push('\"');";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(valueString.charAt(i)== '`'){";
		result+="\n\t\t\t\tif(!st.empty() && st.peek()=='`'){";
		result+="\n\t\t\t\t\tst.pop();";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\tif(st.peek() =='('){";
		result+="\n\t\t\t\t\t\tst.push('`');";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(valueString.charAt(i)== '\\''){";
		result+="\n\t\t\t\tif(!st.empty() && st.peek()=='\\''){";
		result+="\n\t\t\t\t\tst.pop();";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\tif(st.peek() =='('){";
		result+="\n\t\t\t\t\t\tst.push('\\'');";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(valueString.charAt(i)==')' ){";
		result+="\n\t\t\t\tif(!st.empty() && st.peek()=='('){";
		result+="\n\t\t\t\t\tst.pop();";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\ttest+=String.valueOf(valueString.charAt(i));";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(valueString.charAt(i) ==','){";
		result+="\n\t\t\t\tif(st.empty()){";
		result+="\n\t\t\t\t\tEachRow.add(test);";
		result+="\n\t\t\t\t\ttest=\"\";";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\ttest+=String.valueOf(valueString.charAt(i));";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}else{";
		result+="\n\t\t\t\ttest+=String.valueOf(valueString.charAt(i));";
		result+="\n\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tEachRow.add(test);";
		result+="\n\t\t\twhile(!st.empty()){";
		result+="\n\t\t\t\tst.pop();";
		result+="\n\t\t\t}";
		result+="\n\t\t\tString[] columnsNames = getColumnNames(tempStatement.substring(0,tempStatement.toUpperCase().indexOf(\"VALUES\")));";
		result+="\n\t\t\tfor(int i=0;i<EachRow.size();i++){";
		result+="\n\t\t\t\tString temp=\"\";";
		result+="\n\t\t\t\tArrayList<String> EachValue = new ArrayList<String>();";
		result+="\n\t\t\t\tfor(int j=0;j<EachRow.get(i).length();j++){";
		result+="\n\t\t\t\t\tif(EachRow.get(i).charAt(j)== '\"'){";
		result+="\n\t\t\t\t\t\tif(!st.empty() && st.peek()=='\"'){";
		result+="\n\t\t\t\t\t\t\tst.pop();";
		result+="\n\t\t\t\t\t}else{";
		result+="\n\t\t\t\t\tif(st.empty()){";
		result+="\n\t\t\t\t\t\tst.push('\"');";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(EachRow.get(i).charAt(j)== '`'){";
		result+="\n\t\t\t\tif(!st.empty() && st.peek()=='`'){";
		result+="\n\t\t\t\t\tst.pop();";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\tif(st.empty()){";
		result+="\n\t\t\t\t\t\tst.push('`');";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(EachRow.get(i).charAt(j)== '\\''){";
		result+="\n\t\t\t\tif(!st.empty() && st.peek()=='\\''){";
		result+="\n\t\t\t\t\tst.pop();";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\tif(st.empty()){";
		result+="\n\t\t\t\t\t\tst.push('\\'');";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(EachRow.get(i).charAt(j) ==','){";
		result+="\n\t\t\t\tif(st.empty()){";
		result+="\n\t\t\t\t\tEachValue.add(temp);";
		result+="\n\t\t\t\t\ttemp=\"\";";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\ttemp+=String.valueOf(EachRow.get(i).charAt(j));";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}else{";
		result+="\n\t\t\t\t\ttemp+=String.valueOf(EachRow.get(i).charAt(j));";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tEachValue.add(temp);";
		result+="\n\t\t\tString[] eachValue = EachRow.get(i).split(\",\");";
		result+="\n\t\t\tInsertQueryGenerator(columnsNames,EachValue,host,port);";
		result+="\n\t\t\t}";
		result+="\n\t\t\tindexInsert = str.toUpperCase().indexOf(\"INSERT INTO \",indexC+1);";
		result+="\n\t\t\t}";
		result+="\n\t\t\t}\n\n\n";	
		
		result+="\n/**";
		result+="\n * provide column names for insert query";
		result+="\n *"; 
		result+="\n * <p>";
		result+="\n\t * This method take input insert query string and identify list of columns which are required";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 	str		input string with sql query";
		result+="\n\t *"; 
		result+="\n\t * @return	eachCol	String array with name of required columns";
		result+="\n\t *"; 
		result+="\n\t */";
		result+="\n\t\t\tpublic String[] getColumnNames(String str){";
		result+="\n\t\t\t\tif(str.indexOf(\"(\") <0 && str.indexOf(\")\")<0){";
		result+="\n\t\t\t\t\treturn colNames;";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tString listCols = str.substring(str.indexOf(\"(\")+1, str.indexOf(\")\"));";
		result+="\n\t\t\t\tString[] eachCol = listCols.split(\",\");";
		result+="\n\t\t\t\tfor(int i=0;i<eachCol.length;i++){";
		result+="\n\t\t\t\t\teachCol[i] = performOper(eachCol[i]);";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\treturn eachCol;";
		result+="\n\t\t\t}";

		return result;
	}
	
	
	
	/**
	 * generate method to create Google datastore query for insert 
	 * 
	 * <p>
	 * This method create 'insert' Google datastore query from corresponding 'INSERT' query
	 * it determines the presence of 'WHERE' clause and sorting conditions.
	 * <p>
	 *  
	 * @return 	result	string of 'GetSelectQueryGenerator' method
	 */
	public String getInsertQueryGenerator(String tableName,int primaryKeyIndex){

		String result="";
		Properties prop = new Properties();
		InputStream input = null;
		try {
			/**
			 * reading from config file
			 */
			input = getClass().getClassLoader().getResourceAsStream("config/config.properties");
      		prop.load(input);
      		
      		/**
      		 * storing type of sql keys 
      		 */
      		String[] JavaDataTypes = prop.getProperty("JavaDataTypes").split(",");
      		String[] dataTypesMethods = prop.getProperty("dataTypesMethods").split(",");
      		
      		result+="\n\t/*";
      		result+="\n\t * <p>";
      		result+="\n\t * This method is helper function of insert method";
      		result+="\n\t * <p>";
      		result+="\n\t *"; 
      		result+="\n\t * <p>";
      		result+="\n\t * This method will take 4 inputs";
      		result+="\n\t * 	a) Column names to be inserted";
      		result+="\n\t * 	b) each value for each time insert";
      		result+="\n\t * 	c) host name to connect;";
      		result+="\n\t * 	d) port number to connect";
      		result+="\n\t * <p>";
      		result+="\n\t *"; 
      		result+="\n\t * <p>";
      		result+="\n\t * This method do the main inserting operation and insert each row value of MySQL table in corresponding Mongodb collection";
      		result+="\n\t * <p>";
      		result+="\n\t *"; 
      		result+="\n\t * @param		colsNames		column names in which data to be inserted";
      		result+="\n\t * @param		eachValue		Value of each row";
      		result+="\n\t * @param		host			host name";
      		result+="\n\t * @param		port			port number";
      		result+="\n\t *"; 
      		result+="\n\t */";
    		result+="\n\n\tpublic void InsertQueryGenerator(String[] colsNames,ArrayList<String> eachValue,String host,int port){";
    		result+="\n\t\ttry{";   
    		result+="\n\t\t\tMongoClient mongoClient = new MongoClient( host,port);";
    	    result+="\n\t\t\tDB db = mongoClient.getDB( databaseName );";
    	    result+="\n\t\t\tSystem.out.println(\"Connect to database successfully\");";
    	    result+="\n\t\t\tDBCollection coll = db.getCollection(tableName);";
    	    result+="\n\t\t\tSystem.out.println(\"Collection mycol selected successfully\");";		
    	    result+="\n\t\t\tBasicDBObject doc = new BasicDBObject();";
    		
    	    result+="\n\t\t\tfor(int j=0;j<colsNames.length;j++){";

    	    result+="\n\t\t\t\tint i=0;";

    	    result+="\n\t\t\t\tfor(int k=0;k<colNames.length;k++){";

    	    result+="\n\t\t\t\t\tif(colsNames[j] .equals(colsNames[k])){";

    	    result+="\n\t\t\t\t\t\ti=k;";

    	    result+="\n\t\t\t\t\t\tbreak;";
    					
    	    result+="\n\t\t\t\t\t}";

    	    result+="\n\t\t\t\t}";
    	    for(int i=0;i<JavaDataTypes.length;i++){

    	    	result+="\n\t\t\t\tif(colTypes[i] .equals(\""+JavaDataTypes[i]+"\")){";
    	    	result+="\n\t\t\t\t\tif(eachValue.get(i) .equals(\"NULL\") || eachValue.get(i) .equals(\"null\")){";
    	    	result+="\n\t\t\t\t\t\tdoc.append(colNames[i],null);";
    	    	result+="\n\t\t\t\t\t}else{";
    	    	result+="\n\t\t\t\t\t\tdoc.append(colNames[i],"+dataTypesMethods[i]+"(performOper(eachValue.get(i))));";
    	    	result+="\n\t\t\t\t\t}";
    	    	result+="\n\t\t\t\t}";
    	    }
    	    result+="\n\t\t\t}";
    	    result+="\n\t\t\tcoll.insert(doc);";
    	    result+="\n\t\t}catch(Exception e){";
    	    result+="\n\t\t\tSystem.err.println( e.getClass().getName() + \": \" + e.getMessage() );";
    	    result+="\n\t\t}";
    		result+="\n\t}";

   		} catch (IOException ex) {
      		ex.printStackTrace();
   		} finally {
      		if (input != null) {
         		try {
            		input.close();
         		} catch (IOException e) {
            		e.printStackTrace();
         		}
      		}
   		}
		



		
		return result;
	}


}