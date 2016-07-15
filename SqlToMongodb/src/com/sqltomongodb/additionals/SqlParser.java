package com.sqltomongodb.additionals;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author Akshay P. Singh <akshayps@iitrpr.ac.in>
 *
 */
public class SqlParser{

	public SqlParser(){
	}
	
	/**
	 * Extract string containing only "CREATE TABLE" query
	 * 
	 * <p>
	 * This method trim input file's data and provide required create table statement only. 
	 * <p>
	 * 
	 * @param 	str 			input string as read from provided sql file
	 * @return	TableQueries	extracted create table queries 
	 */
	public String[] extractCreateTableQuery(String str){
		/**
		 * index of "create table" found in string
		 */
		int indexCreate =str.toUpperCase().indexOf("CREATE TABLE ");
		if(indexCreate <0){
			return null;
		}
		
		/**
		 * Storing individual queries as Arraylist.
		 */
		ArrayList<String> mainQueryStatement = new ArrayList<String>();
		
		/**
		 * Loop for more than one CREATE TABLE statment
		 */
		while(indexCreate != (-1)){
			int indexQueryEnd = str.indexOf(";",indexCreate);
			String CreateTableStatement = str.substring(indexCreate,indexQueryEnd+1);
			mainQueryStatement.add(CreateTableStatement);
			indexCreate = str.toUpperCase().indexOf("CREATE TABLE ",indexQueryEnd+1);
		}
		
		/**
		 * Conversion into string from Arraylist
		 */
		String[] tableQueries = new String[mainQueryStatement.size()];
		for(int i=0;i<mainQueryStatement.size();i++){
			tableQueries[i] = mainQueryStatement.get(i);
		}
		return tableQueries;
	}


	
	
	/**
	 * Extract column query from complete query
	 * @param 	str 			input query string
	 * @return	result<array>	new column's query string
	 */
	public String[] extractingColumns(String str){
		
		/**
		 * include string only of columns details
		 */
		String cols = str.substring(str.indexOf("(")+1,str.lastIndexOf(")"));
		
		/**
		 * to check opening/closing brackets as well as special characters
		 */
		Stack<Character> st = new Stack<Character>();
		
		/**
		 * temporary store string after reading char-by-char
		 */
		String temp="";
		/**
		 * store each column query, added by using temp
		 */
		ArrayList<String> eachValue = new ArrayList<String>();
		for(int j=0;j<cols.length();j++){
			
			/**
			 * checking for special character : "
			 */
			if(cols.charAt(j)== '"'){
				if(!st.empty() && st.peek()=='"'){
					st.pop();
				}else{
					if(st.empty()){
						st.push('"');
					}
				}
			}
			
			/**
			 * checking for special character: `
			 */
			if(cols.charAt(j)== '`'){
				if(!st.empty() && st.peek()=='`'){
					st.pop();
				}else{
					if(st.empty()){
						st.push('`');
					}
				}
			}
			
			/**
			 * checking for closing bracket
			 */
			if(cols.charAt(j)== ')'){
				if(!st.empty() && st.peek()=='('){
					st.pop();
				}else{

				}
			}
			
			/**
			 * checking for opening bracket
			 */
			if(cols.charAt(j)== '('){
				if(!st.empty() && st.peek()=='('){

				}else{
					if(st.empty()){
						st.push('(');
					}

				}
			}
			
			/**
			 * checking for special character: '
			 */
			if(cols.charAt(j)== '\''){
				if(!st.empty() && st.peek()=='\''){
					st.pop();
				}else{
					if(st.empty()){
						st.push('\'');
					}
				}
			}
			
			/**
			 * checking for ','
			 */
			if(cols.charAt(j) ==','){
				if(st.empty()){
					eachValue.add(temp);
					temp="";
				}else{
					temp+=String.valueOf(cols.charAt(j));
				}
			}else{
				temp+=String.valueOf(cols.charAt(j));
			}
		}
		eachValue.add(temp);
		
		/**
		 * converting arraylist to array
		 */
		String[] result = new String[eachValue.size()];
		for(int i=0;i<eachValue.size();i++){
			result[i] = eachValue.get(i);
		}
		return result;	
	}

	
	
	
	
	
	
	/**
	 * provide imports for out files
	 * @param 	i		counter to indentify imports to DAO or DTO	
	 * @return	result	string with desired imports required
	 */
	public String getImports(int i,String dbName,String tableName){
		String result="";
		if(i==0){
			result+="import com.mysqltomongodb."+dbName+".*";
			result+="import java.sql.Date;\n";
			result+="import java.sql.Timestamp;\n";
			result+="import java.sql.Time;\n";
			result+="import java.util.ArrayList;\n";

		}else if(i==1){
			result+="package com.mysqltomongodb."+dbName+".DAO;\n\n\n";
			
			result+="\n\n/**";
			result+="\n* ";
			result+="\n * @author 		Akshay P. Singh <akshayps@iitrpr.ac.in>";
			result+="\n * @version 	1.0";
			result+="\n *"; 
			result+="\n * This code is generated to help users to migrate from MySQL to Mongodb"; 
			result+="\n * Only Five methods of this class are for user. Others are helper methods";
			result+="\n * 1) insert";
			result+="\n * 2) select";
			result+="\n * 3) delete";
			result+="\n * 4) update";
			result+="\n * 5) selectSingleValue";
			result+="\n */\n\n\n";
			
			result+="import com.mysqltomongodb."+dbName+".DTO.*;\n";
			result+="import java.util.List;\n";
			result+="import java.util.regex.Pattern;";
			result+="import java.util.Set;\n";
			result+="import java.math.*;\n";
			result+="import com.mongodb.*;\n";
			result+="import com.mongodb.BasicDBObject;\n";
			result+="import com.mongodb.DB;\n";
			result+="import com.mongodb.DBCollection;\n";
			result+="import com.mongodb.MongoClient;\n";
			result+="import java.text.ParseException;\n";
			result+="import java.text.SimpleDateFormat;\n";
			result+="import java.util.*;\n";
			result+="import java.sql.Timestamp;\n";
			

		}
		return result+"\n";
	}
}