package com.sqltomongodb.essentials;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class DeleteQuery {
	
	public DeleteQuery(){
		
	}
	
	
	/**
	 * generate method to handle sql DELETE queries
	 * 
	 * <p>
	 * This method is used to create 'delete' method in DAO file.
	 * Corresponding to given create table query, this method will create 'delete' method to handle DML select queries.
	 * Filters and sorting are to be appended in this method using 'GetDeleteQueryGenerator' method
	 * <p>
	 * @return 	result		string with 'delete' method
	 */
	public String getDeleteMethod(){
		String result="";
		
		result+="\n\t/*";
		result+="\n\t * <p>";
		result+="\n\t * This method handles MySQL delete Query";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method will take 3 inputs";
		result+="\n\t * 	a) select query in MySQL format with given specifications";
		result+="\n\t * 	b) host name to connect;";
		result+="\n\t * 	c) port number to connect";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * It identifies where condition and on the basis of conditions provided it will interact with MongDB database and perform delete operation;";
		result+="\n\t * To make it more secure we made it in such a way that user must provide a valid condition to make operation"; 
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param		str				input string with sql query to select";
		result+="\n\t * @param		host			host name";
		result+="\n\t * @param		port			port number";
		result+="\n\t *"; 
		result+="\n\t */";
		result+="\n\tpublic void delete(String str,String host,int port){";

		result+="\n\t\ttry{";
		result+="\n\t\t\tMongoClient mongoClient = new MongoClient(host,port);";
		result+="\n\t\t\tDB db = mongoClient.getDB( databaseName );";
		result+="\n\t\t\tSystem.out.println(\"Connect to database successfully\");";
		result+="\n\t\t\tDBCollection coll = db.getCollection(tableName);";
		result+="\n\t\tSystem.out.println(\"Collection mycol selected successfully\");";
		result+="\n\t\tint[] count = getSubQueryConfirmation( str);";
		result+="\n\t\tint[] mainTagsIndex = mainClauses(str,count);";
		result+="\n\t\t\tBasicDBObject whereCondition = WhereStatement(str,mainTagsIndex,host,port);";
		result+="\n\t\t\tif(whereCondition != null){";
		result+="\n\t\t\t\tWriteResult cursor = coll.remove(whereCondition);";
		result+="\n\t\t\t}else{";
		result+="\n\t\t\t\tWriteResult cursor = coll.remove(null);";
		result+="\n\t\t\t}";
		result+="\n\t\t}catch(Exception e){";
		result+="\n\t\t\tSystem.err.println( e.getClass().getName() + \": \" + e.getMessage() );";
		result+="\n\t\t}";
		result+="\n\t}";
	
		
		return result;
	}

}
