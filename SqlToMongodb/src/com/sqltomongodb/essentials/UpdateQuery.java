package com.sqltomongodb.essentials;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;



public class UpdateQuery{

	public UpdateQuery(){

	}
	
	
	
	/**
	 * generate method to handle sql UPDATE queries
	 * 
	 * <p>
	 * This method is used to create 'update' method in DAO file.
	 * Corresponding to given create table query, this method will create 'update' method to handle DML select queries.
	 * Filters and sorting are to be appended in this method using 'GetUpdateQueryGenerator' method and 'set' values are handled by method 'GetUpdateSetQuery'
	 * <p>
	 * 
	 * @return 	result		string with 'update' method
	 */
	public String getUpdateMethod(String tableName,String[] colNames){
		String result="";
		
		
		result+="\n\t/*";
		result+="\n\t * <p>";
		result+="\n\t * This method handles MySQL update Query";
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
		result+="\n\t * It identifies where condition and on the basis of conditions provided it will interact with MongDB database and perform delete operation";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param		str				input string with sql query to select";
		result+="\n\t * @param		host			host name";
		result+="\n\t * @param		port			port number";
		result+="\n\t *"; 
		result+="\n\t */";
		result+="\n\tpublic void update(String str,String host,int port){";
		result+="\n\t\ttry{";  
		result+="\n\t\t\tMongoClient mongoClient = new MongoClient( host,port );";
	         // To connect to mongodb server
		result+="\n\t\t\tDB db = mongoClient.getDB( databaseName );";
		result+="\n\t\t\tSystem.out.println(\"Connect to database successfully\");";
		result+="\n\t\t\t DBCollection coll = db.getCollection(tableName);";
		result+="\n\t\t\tSystem.out.println(\"Collection mycol selected successfully\");";
		result+="\n\t\tint[] count = getSubQueryConfirmation( str);";
		result+="\n\t\tint[] mainTagsIndex = mainClauses(str,count);";
		result+="\n\t\t\tBasicDBObject whereQuery = WhereStatement(str,mainTagsIndex,host,port);";
		result+="\n\t\t\tBasicDBObject setQuery = UpdateSetQuery(str,host,port);";
		result+="\n\t\t\tBasicDBObject updateQuery = new BasicDBObject();";
		result+="\n\t\t\tupdateQuery.append(\"$set\",setQuery);";
		result+="\n\t\t\tcoll.updateMulti(whereQuery, updateQuery);";	
		result+="\n\t\t\t     DBCursor cursor = coll.find();";		
		result+="\n\t\t\tSystem.out.println(\"Document updated successfully\");";
		result+="\n\t\t\t     int i = 1;";
		result+="\n\t\t\twhile (cursor.hasNext()) {"; 
		result+="\n\t\t\t\tSystem.out.println(\"Updated Document: \"+i);"; 
		result+="\n\t\t\t\tSystem.out.println(cursor.next());"; 
		result+="\n\t\t\t\t        i++;";
		result+="\n\t\t\t}";
				
		result+="\n\t\t }catch(Exception e){";
		result+="\n\t\t\tSystem.err.println( e.getClass().getName() + \": \" + e.getMessage() );";
		result+="\n\t\t  }";
		result+="\n\t}";


			return result;

	}

	
	
	
	/**
	 * generate method to handle set values in sql update 
	 * 
	 * <p>
	 * this method is extension of previous method 'GetUpdateMethod'
	 * is handles set value parameters in sql update to corresponding update query in Google data-store.
	 * <p>
	 * 
	 * @param 	tableName				name of the table extracted from sql file
	 * @param 	dataTypes				Java data-types available
	 * @param 	dataTypesMethods		Data-type conversion methods(internal methods as well as external
	 * @return	result					string with UpdateSetQuery method
	 */
	public String getUpdateSetQuery(String tableName,String[] dataTypes,String[] dataTypesMethods){
		String result="";
		
		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method is helper function of update method";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method will take 3 inputs";
		result+="\n\t * 	a) update query in MySQL format with given specifications";
		result+="\n\t * 	b) host name to connect;";
		result+="\n\t * 	c) port number to connect";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method determine required conditions for update Query and return BasicDBObject including conditions";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 		str			input update query";
		result+="\n\t * @param		host		host name";
		result+="\n\t * @param		port		port number";
		result+="\n\t *"; 
		result+="\n\t * @return		setQuery	Update conditions in the form of BasicDBObject";
		result+="\n\t *"; 
		result+="\n\t */";
		result+="\n\t	public BasicDBObject UpdateSetQuery(String str,String host,int port){";
		result+="\n\t\tBasicDBObject setQuery = new BasicDBObject();";
		result+="\n\t\tint indexSet = str.toUpperCase().toUpperCase().indexOf(\" SET \");";
		result+="\n\t\tint indexOfWhere = str.toUpperCase().toUpperCase().indexOf(\" WHERE \");";
		result+="\n\t\tString test =\"\";";
		result+="\n\t\tif(indexOfWhere >0){";
		result+="\n\t\t\ttest = str.substring(indexSet+4, indexOfWhere);";
		result+="\n\t\t}else{";
		result+="\n\t\t\t	int indexEnd = str.toUpperCase().indexOf(\";\");";
		result+="\n\t\t\tif(indexEnd==(-1)){";
		result+="\n\t\t\t\tindexEnd = str.length();";
		result+="\n\t\t\t}";
		result+="\n\t\t\ttest = str.substring(indexSet, indexEnd);";
		result+="\n\t\t}";
		result+="\n\t\tString[] eachSet = test.split(\",\");";
		result+="\n\t\tfor(int i=0;i<eachSet.length;i++){";
		result+="\n\t\t\tString[] nameValue = eachSet[i].split(\"=\");";
		result+="\n\t\t\tint colTypeIdentifiedId = 0;";
		result+="\n\t\t\tfor(int k=0;k<colTypes.length;k++){";
		result+="\n\t\t\t\tif(performOper(nameValue[0]) .equals(colNames[k])){";
		result+="\n\t\t\t\t\tcolTypeIdentifiedId = k;";
		result+="\n\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tString colType = colTypes[colTypeIdentifiedId];";
		for(int i=0;i<dataTypes.length;i++){
		result+="\n\t\t\tif(colType == \""+dataTypes[i]+"\"){";
		result+="\n\t\t\t\tsetQuery.append(performOper(nameValue[0]),"+dataTypesMethods[i]+"(performOper(nameValue[1])));";

		result+="\n\t\t\t}";
		}

		result+="\n\t\t}";
		result+="\n\t\treturn setQuery;";
		result+="\n\t}";

		return result;
	}

	
	
	
	/**
	 * generate method to update Google datastore query for update operations
	 * 
	 * <p>
	 * This method create 'delete' Google datastore query from corresponding 'DELETE' query
	 * it determines the presence of 'WHERE' clause and sorting conditions.
	 * <p>
	 *  
	 * @return 	result	string of 'GetDeleteQueryGenerator' method
	 */
	public String getUpdateQueryGenerator(){
		String result="";
		
		result+="\n\t\tpublic Query updateQueryGenerator(String str,String host,int port){";
		result+="\n\t\tQuery q = new Query(tableName);";
		result+="\n\t\tint indexOfWhere = str.toUpperCase().toUpperCase().indexOf(\" WHERE \");";
		result+="\n\t\tint endIndex = str.indexOf(\";\");";
		result+="\n\t\tif(endIndex ==(-1)){";
		result+="\n\t\t\tendIndex = str.length();";
		result+="\n\t\t}";
		result+="\n\t\tif(indexOfWhere >0){";
		result+="\n\t\t\tString test = str.substring(indexOfWhere,endIndex);";
		result+="\n\t\t\tq = WhereStatement(q,test,host,port);";
		result+="\n\t\t}";
		result+="\n\t\treturn q;";
		result+="\n\t\t}";

	return result;
	}
}