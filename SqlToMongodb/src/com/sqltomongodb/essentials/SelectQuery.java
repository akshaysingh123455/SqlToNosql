package com.sqltomongodb.essentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * 
 * @author Akshay P. Singh <akshayps@iitrpr.ac.in>
 *
 */
public class SelectQuery{

	public SelectQuery(){
	}

	
	/**
	 * generate method to handle sql select queries
	 * 
	 * <p>
	 * This method is used to create 'select' method in DAO file.
	 * Corresponding to given create table query, this method will create 'select' method to handle DML select queries.
	 * Filters and sorting are to be appended in this method using 'GetSelectQueryGenerator' method
	 * <p>
	 * 
	 * @param 	tableName			table name extracted from query
	 * @param 	colTypes<array>		column data-types extracted to be in Java data-types
	 * @param 	ColNames<array>		Column names
	 * @return 	result				string with complete 'select' method 
	 */
	public String getSelectQuery(String tableName,String[] colTypes,String[] colNames){
		String result="";
		Properties prop = new Properties();
  		InputStream input = null;
  		result+="\n\n\n\n\t/**";
  		result+="\n\t * provide result for select query";
  		result+="\n\t *"; 
  		result+="\n\t * <p>";
  		result+="\n\t * This method will take 3 inputs";
  		result+="\n\t * 	a) select query in MySQL format with given specifications";
  		result+="\n\t * 	b) host name to connect";
  		result+="\n\t * 	c) port number to connect";
  		result+="\n\t * <p>";
  		result+="\n\t *"; 
  		result+="\n\t * <p>";
  		result+="\n\t * This method with extract columns names and conditions, sub-queries and query with provided mongodb database";
  		result+="\n\t * and provide required results in the form Arraylist of DTO objects of corresponding table.";
  		result+="\n\t *"; 
  		result+="\n\t *"; 
  		result+="\n\t * @param		str		input string with sql query to select"; 
  		result+="\n\t * @param		host	host name";
  		result+="\n\t * @param		port	port number";	
  		result+="\n\t *"; 
  		result+="\n\t * @return		test	Arraylist of DTO objects"; 
  		result+="\n\t *"; 
  		result+="\n\t */";
  			result+="\n\tpublic ArrayList<"+tableName +"DTO> select(String str,String host,int port){";
  	 		try {
  	  			input = new FileInputStream("resources\\config\\config.properties");
  	  			prop.load(input);
  	  			
  	  			/**
  	  			 * Java data-types and data-type methods loaded from config file
  	  			 */
  	  			String[] dataTypes = prop.getProperty("JavaDataTypes").split(",");
  	  			String[] dataTypesMethods = prop.getProperty("dataTypesMethods").split(",");
  	  			String[] usedDataTypesMethods = new String[colNames.length];
  	  			for(int i=0;i<usedDataTypesMethods.length;i++){
  	  				for(int j=0;j<dataTypes.length;j++){
  	  					if(colTypes[i] .equals(dataTypes[j])){
  	  					usedDataTypesMethods[i] = dataTypesMethods[j];
  	  					}
  	  				}
  	  			}
  	  			/**
  	  			 * appending required strings to final 'result'
  	  			 */
  	  			result+="\n\t\ttry{";
  	  			result+="\n\t\t\tArrayList<"+tableName+"DTO> test = new ArrayList<"+tableName+"DTO>(); ";
  	  			result+="\n\t\t\tthis.setTempTableName(str);";
  					
  			         // To connect to mongodb server
  	  			result+="\n\t\t\tMongoClient mongoClient = new MongoClient(host,port);";
  						
  			         // Now connect to your databases
  	  			result+="\n\t\t\tDB db = mongoClient.getDB( databaseName );";
  	  			result+="\n\t\t\tSystem.out.println(\"Connect to database successfully\");";
  						
  	  			result+="\n\t\tDBCollection coll = db.getCollection(tableName);";
  	  			result+="\n\t\tint[] count = getSubQueryConfirmation( str);";

  	  		result+="\n\t\tint[] mainTagsIndex = mainClauses(str,count);";

  	  		result+="\n\t\t\tBasicDBObject where = WhereStatement(str,mainTagsIndex,host,port);";

  	  		
  	  		result+="\n\t\t\tBasicDBObject sortQuery = sort(str,mainTagsIndex,host,port);";
  	  		
  	  		result+="\n\t\t\tBasicDBObject whereCondition;";
  	  		result+="\n\t\t\tif(where == null){";
  	  		result+="\n\t\t\t\twhereCondition = new BasicDBObject();";
  	  		result+="\n\t\t\t}else{";
  	  		result+="\n\t\t\t\t	whereCondition = where;";
  	  		result+="\n\t\t\t}";
  	  		
  	  		result+="\n\t\t\tDBObject sort =  new BasicDBObject(\"$sort\",sortQuery);";
	  	  	result+="\n\t\t\tString[] limitOffset = limitOffset(str,host,port).split(\":\");";
  	  		result+="\n\t\t\tint limitValue = Integer.parseInt(limitOffset[0]);";
  	  		result+="\n\t\t\tint offsetValue = Integer.parseInt(limitOffset[1]);";
  	  		result+="\n\t\t\tBasicDBObject limit = new BasicDBObject(\"$limit\",limitValue);";
  	  		result+="\n\t\t\tBasicDBObject offSet = new BasicDBObject(\"$skip\",offsetValue);";
  	  		result+="\n\t\t\tString[] ColumnNamesToReturned = selectColumnNamesToReturned(str,host,port);";

  	  		result+="\n\t\t\tfor(int i=0;i<ColumnNamesToReturned.length;i++){";
  	  		result+="\n\t\t\t\tif(!tableName .equals(tempTableName)){";
  	  		result+="\n\t\t\t\t\tColumnNamesToReturned[i] = ColumnNamesToReturned[i].replace(tempTableName+\".\", \"\");";
  	  		result+="\n\t\t\t\t}";
		  	result+="\n\t\t\t\t	ColumnNamesToReturned[i] = this.performOper(ColumnNamesToReturned[i]);";
		  	result+="\n\t\t\t}";
  	  		
		  	result+="\n\t\t\tboolean groupBy = true;";
		  	result+="\n\t\t\tint[] subQueryCounter = getSubQueryConfirmation(str);";
		  	result+="\n\t\t\tint indexgroupTemp = str.toUpperCase().indexOf(\"GROUP BY \");";
		  	result+="\n\t\t\tif(indexgroupTemp <0){";
		  	result+="\n\t\t\t\tgroupBy = false;";
		  	result+="\n\t\t\t}";
		  	result+="\n\t\t\tint indexOfGroup=0;";
		  	result+="\n\t\t\tif(groupBy == true){";
		
		  	result+="\n\t\t\t\twhile(indexgroupTemp >= 0) {";
		  	result+="\n\t\t\t\t\tif(subQueryCounter[indexgroupTemp] ==0){";
		  	result+="\n\t\t\t\t\t\tindexOfGroup = indexgroupTemp;";
		  	result+="\n\t\t\t\t\t\tbreak;";
		  	result+="\n\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\tindexgroupTemp = str.toUpperCase().indexOf(\"GROUP BY \", indexgroupTemp+1);";
		  	result+="\n\t\t\t\t}";
		  	result+="\n\t\t\t\tif(indexOfGroup ==0){";
		  	result+="\n\t\t\t\t\tgroupBy = false;";
		  	result+="\n\t\t\t\t}";
		  	result+="\n\t\t\t}";
  	  		
		  	result+="\n\t\t\tif(groupBy== false){";
		  	result+="\n\t\t\t\tDBObject forWhere = new BasicDBObject(\"$match\",whereCondition);";
		  	result+="\n\t\t\t\tBasicDBObject dbobjectForGroup = new BasicDBObject();";
		  	result+="\n\t\t\t\tfor(int i=0;i<ColumnNamesToReturned.length;i++){";
		  	result+="\n\t\t\t\t\tString tempColumnToGet =\"$\"+ColumnNamesToReturned[i];";
		  	result+="\n\t\t\t\t\tdbobjectForGroup.append(ColumnNamesToReturned[i], tempColumnToGet);";
		  	result+="\n\t\t\t\t}";
		  	result+="\n\t\t\t\tBasicDBObject idObject = new BasicDBObject(\"_id\",dbobjectForGroup);";
		  	result+="\n\t\t\t\tfor(int i=0;i<ColumnNamesToReturned.length;i++){";
		  	result+="\n\t\t\t\t\tString tempColumnToGet = \"$\"+ColumnNamesToReturned[i];";

		  	result+="\n\t\t\t\t\tidObject.append(ColumnNamesToReturned[i], new BasicDBObject(\"$addToSet\",tempColumnToGet));";
		  	result+="\n\t\t\t\t}";
				
		  	result+="\n\t\t\t\tBasicDBObject groupObject = new BasicDBObject(\"$group\",idObject);";
				
				
				
				
		  	result+="\n\t\t\t\tBasicDBObject objectForProject = new BasicDBObject(\"_id\",0);";
		  	result+="\n\t\t\t\tfor(int i=0;i<ColumnNamesToReturned.length;i++){";
		  	result+="\n\t\t\t\t\tif(";
		  	for(int i=0;i<colNames.length;i++){

		  		if(i ==colNames.length-1){		  		result+="ColumnNamesToReturned[i] .equals(\""+colNames[i]+"\") ){";

		  		}else{
			  		result+="ColumnNamesToReturned[i] .equals(\""+colNames[i]+"\") || ";

		  		}
		  	}
		  	result+="\n\t\t\t\t\t\tString tempColumnToGet = \"$\"+ColumnNamesToReturned[i];";
		  	result+="\n\t\t\t\t\t\tobjectForProject.append(ColumnNamesToReturned[i], tempColumnToGet);";
		  	result+="\n\t\t\t\t\t}";
					
		  	result+="\n\t\t\t\t}";
		  	result+="\n\t\t\t\tDBObject project = new BasicDBObject(\"$project\",objectForProject );";
		  	result+="\n\t\t\t\tAggregationOutput output = coll.aggregate(forWhere,groupObject,project,sort,offSet,limit);";
		  	result+="\n\t\t\t\t\t for (DBObject result : output.results()) {";
		  	result+="\n\t\t\t\t\t\t"+tableName+"DTO temp = new "+tableName+"DTO();";
		  	result+="\n\t\t\t\t\t\t for(int i=0;i<ColumnNamesToReturned.length;i++){";
		  	result+="\n\t\t\t\t\t\t\tint colCounter=0;";
		  	for(int i=0;i<colNames.length;i++){
		  		if(i == 0){
		  			result+="\n\t\t\t\t\t\t\tif(ColumnNamesToReturned[i] .equals(\""+colNames[i]+"\")){";
		  			result+="\n\t\t\t\t\t\t\t\tString "+colNames[i]+" = result.get(\""+colNames[i]+"\").toString();";
		  			result+="\n\t\t\t\t\t\t\t\ttemp.set"+colNames[i]+"("+usedDataTypesMethods[i]+"(performOper("+colNames[i]+")));";
		  			result+="\n\t\t\t\t\t\t\t}";
		  		}else{
		  			result+="\n\t\t\t\t\t\t\telse if(ColumnNamesToReturned[i] .equals(\""+colNames[i]+"\")){";
		  			result+="\n\t\t\t\t\t\t\t\tString "+colNames[i]+" = result.get(\""+colNames[i]+"\").toString();";
		  			result+="\n\t\t\t\t\t\t\t\ttemp.set"+colNames[i]+"("+usedDataTypesMethods[i]+"(performOper("+colNames[i]+")));";
		  			result+="\n\t\t\t\t\t\t\t}";
		  		}
		  	}
		  	result+="\n\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\ttest.add(temp);";
		  	result+="\n\t\t\t\t\t}";
		  	
		  	result+="\n\t\t\t}else{";
		  	result+="\n\t\t\t\tString groupStatement =\"\";";
		  	result+="\n\t\t\t\tint requiredIndex=0;";
		  	result+="\n\t\t\t\tfor(int i=0;i<mainTagsIndex.length;i++){";
		  	result+="\n\t\t\t\t\t	if(indexOfGroup == mainTagsIndex[i]){";
		  	result+="\n\t\t\t\t\t\t	requiredIndex=i;";
		  	result+="\n\t\t\t\t\t\tbreak;";
		  	result+="\n\t\t\t\t\t}";
		  	result+="\n\t\t\t\t}";
		  	result+="\n\t\t\t\tgroupStatement = str.substring(mainTagsIndex[requiredIndex], mainTagsIndex[requiredIndex+1]);";
		  	result+="\n\t\t\t\t	groupStatement = groupStatement.replaceFirst(\"GROUP BY \", \"\");";
		  	result+="\n\t\t\t\tString[] eachColumnForGroup = groupStatement.split(\",\");";
				
		  	result+="\n\t\t\t\tDBObject forWhere = new BasicDBObject(\"$match\",whereCondition);";
		  	result+="\n\t\t\t\t	BasicDBObject dbobjectForGroup = new BasicDBObject();";
		  	result+="\n\t\t\t\tfor(int i=0;i<eachColumnForGroup.length;i++){";
		  	result+="\n\t\t\t\t\tString tempColumnToGet = \"$\"+eachColumnForGroup[i];";
		  	result+="\n\t\t\t\t\tdbobjectForGroup.append(eachColumnForGroup[i], tempColumnToGet);";
		  	result+="\n\t\t\t\t}";
		  	result+="\n\t\t\t\t	BasicDBObject idObject = new BasicDBObject(\"_id\",dbobjectForGroup);";

		  	result+="\n\t\t\t\tfor(int i=0;i<ColumnNamesToReturned.length;i++){";
				//	System.out.println(ColumnNamesToReturned[i]);
		  	result+="\n\t\t\t\t\tfor(int j=0;j<this.colNames.length;j++){";
		  	result+="\n\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(this.colNames[j])){";
		  	result+="\n\t\t\t\t\t\t\tString tempColumnToGet = \"$\"+colNames[j];";
		  	result+="\n\t\t\t\t\t\t\tidObject.append(colNames[j], new BasicDBObject(\"$addToSet\",tempColumnToGet));";

		  	result+="\n\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t}";
		  	result+="\n\t\t\t\t}";
		  	
		  	result+="\n\t\t\t\tBasicDBObject groupObject = new BasicDBObject(\"$group\",idObject);";
		  	result+="\n\t\t\t\tBasicDBObject objectForProject = new BasicDBObject(\"_id\",0);";

		  	result+="\n\t\t\t\tfor(int i=0;i<ColumnNamesToReturned.length;i++){";
		  	result+="\n\t\t\t\t\tif(";
		  	for(int i=0;i<colNames.length;i++){

		  		if(i ==colNames.length-1){
			  		result+="this.performOper(ColumnNamesToReturned[i]) .equals(\""+colNames[i]+"\") ){";

		  		}else{
			  		result+="this.performOper(ColumnNamesToReturned[i]) .equals(\""+colNames[i]+"\") || ";

		  		}
		  	}		  	result+="\n\t\t\t\t\t\tString tempColumnToGet = \"$\"+ColumnNamesToReturned[i];";
		  	result+="\n\t\t\t\t\t\tobjectForProject.append(ColumnNamesToReturned[i], tempColumnToGet);";
		  	result+="\n\t\t\t\t\t}else{";
		  	result+="\n\t\t\t\t\t\t	for(int j=0;j<this.colNames.length;j++){";
		  	result+="\n\t\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(this.colNames[j])){";
		  	result+="\n\t\t\t\t\t\t\t\tString tempColumnToGet = \"$\"+colNames[j];";
		  	result+="\n\t\t\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(\"COUNT\")){";
		  	result+="\n\t\t\t\t\t\t\t\t\tobjectForProject.append(\"count\", new BasicDBObject(\"$size\",tempColumnToGet));";
		  	result+="\n\t\t\t\t\t\t\t\t}else{";
		  	result+="\n\t\t\t\t\t\t\t\t\tobjectForProject.append(this.colNames[j], tempColumnToGet);";

		  	result+="\n\t\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t}";
				
		  	result+="\n\t\t\t\t}";
		  	result+="\n\t\t\t\tDBObject project = new BasicDBObject(\"$project\",objectForProject );";
		  	result+="\n\t\t\t\tAggregationOutput output = coll.aggregate(forWhere,groupObject,project,sort,offSet,limit);";
		  	result+="\n\t\t\t\t\t for (DBObject result : output.results()) {";
		  	result+="\n\t\t\t\t\t\t"+tableName+"DTO temp = new "+tableName+"DTO();";
		  	result+="\n\t\t\t\t\t\t for(int i=0;i<ColumnNamesToReturned.length;i++){";
		  	result+="\n\t\t\t\t\t\t\tint colCounter=0;";
		  	for(int i=0;i<colNames.length;i++){
		  		if(i == 0){
		  			result+="\n\t\t\t\t\t\t\tif(ColumnNamesToReturned[i] .equals(\""+colNames[i]+"\")){";
		  			result+="\n\t\t\t\t\t\t\t\tString "+colNames[i]+" = result.get(\""+colNames[i]+"\").toString();";
		  			result+="\n\t\t\t\t\t\t\t\ttemp.set"+colNames[i]+"("+usedDataTypesMethods[i]+"(performOper("+colNames[i]+")));";
		  			result+="\n\t\t\t\t\t\t\t}";
		  		}else{
		  			result+="\n\t\t\t\t\t\t\telse if(ColumnNamesToReturned[i] .equals(\""+colNames[i]+"\")){";
		  			result+="\n\t\t\t\t\t\t\t\tString "+colNames[i]+" = result.get(\""+colNames[i]+"\").toString();";
		  			result+="\n\t\t\t\t\t\t\t\ttemp.set"+colNames[i]+"("+usedDataTypesMethods[i]+"(performOper("+colNames[i]+")));";
		  			result+="\n\t\t\t\t\t\t\t}";
		  		}
		  	}
		  	
		  	result+="\n\t\t\t\t\t\telse{";
		  	result+="\n\t\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(\"COUNT\")){";
		  	result+="\n\t\t\t\t\t\t\t\tString count1 = result.get(\"count\").toString();";
		  	result+="\n\t\t\t\t\t\t\t\t	temp.setcount(Integer.parseInt(performOper(count1)));";
		  	result+="\n\t\t\t\t\t\t\t}else{";
		  	result+="\n\t\t\t\t\t\t\t\tint colId=-1;";
		  	result+="\n\t\t\t\t\t\t\t\t\tfor(int j=0;j<this.colNames.length;j++){";
		  	result+="\n\t\t\t\t\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(this.colNames[j])){";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\tcolId=j;";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\tbreak;";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t\t\t\tif(colId>=0){";
		  	result+="\n\t\t\t\t\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(\"MAX\")){";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\tString value = result.get(this.colNames[colId]).toString();";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\ttemp.setmax(this.getGroupbyFunctionValue(value, 1));";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(\"MIN\")){";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\tString value = result.get(this.colNames[colId]).toString();";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\ttemp.setmin(this.getGroupbyFunctionValue(value, 2));";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(\"AVG\")){";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\tString value = result.get(this.colNames[colId]).toString();";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\ttemp.setavg(this.getGroupbyFunctionValue(value, 3));";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t\t\t\t\tif(ColumnNamesToReturned[i].contains(\"SUM\")){";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\tString value = result.get(this.colNames[colId]).toString();";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t\ttemp.setsum(this.getGroupbyFunctionValue(value, 4));";
		  	result+="\n\t\t\t\t\t\t\t\t\t\t}";

		  	result+="\n\t\t\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t\t\t}";
				
		  	result+="\n\t\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\t}";
		  	result+="\n\t\t\t\t\t\ttest.add(temp);";
		  	result+="\n\t\t\t\t\t}";
		  	result+="\n\t\t\t\t}";
		  	

  						
  						
  			      }catch(Exception e){
  			         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  			      }
			result+="\n\t\treturn test;";
			result+="\n\t}catch(Exception e){";
			result+="\n\t\t	System.err.println( e.getClass().getName() + \": \" + e.getMessage() );";
			result+="\n\t}";
			result+="\n\treturn null;";
			result+="\n\t}";	
			
			
			


		return result;
	}


	
	
	/**
	 * generate method to handle sql select queries( as selectAsWholeArray)
	 * 
	 * <p>
	 * This method is used to create 'selectAsWholeArray' method in DAO file.
	 * Corresponding to given create table query, this method will create 'select' method to handle DML select queries.
	 * Filters and sorting are to be appended in this method using 'GetSelectQueryGenerator' method
	 * This method provide result in 2-D array
	 * <p>
	 * 
	 * @param 	tableName			table name extracted from query
	 * @param 	colTypes<array>		column data-types extracted to be in Java data-types
	 * @param 	ColNames<array>		Column names
	 * 
	 * @return 	result				string with complete 'select' method 
	 */
	public String getSelectAsWholeArrayQuery(String tableName,String[] colTypes,String[] colNames){
		String result="";
		Properties prop = new Properties();
  		InputStream input = null;
  		result+="\n\t/**";
  		result+="\n\t * provide select query result in 2-D array form";
  		result+="\n\t *"; 
  		result+="\n\t * <p>";
  		result+="\n\t * This method will take 3 inputs";
  		result+="\n\t * 	a) select query in MySQL format with given specifications";
  		result+="\n\t * 	b) host name to connect";
  		result+="\n\t * 	c) port number to connect";
  		result+="\n\t * <p>";
  		result+="\n\t *"; 
  		result+="\n\t * <p>";
  		result+="\n\t * This method with extract columns names and conditions, sub-queries and query with provided mongodb database";
  		result+="\n\t * and provide required results in the form of 2-D array";
  		result+="\n\t * <p>";
  		result+="\n\t *"; 
  		result+="\n\t *"; 
  		result+="\n\t * @param		str				input string with sql query to select"; 
  		result+="\n\t * @param		host			host name";
  		result+="\n\t * @param		port			port number";	
  		result+="\n\t *"; 
  		result+="\n\t * @return		arrayValues		result in the form of 2-D array";
  		result+="\n\t */";
  			result+="\n\tpublic String[][] selectAsWholeArray(String str,String host,int port){";
  	 		try {
  	  			input = new FileInputStream("resources\\config\\config.properties");
  	  			prop.load(input);
  	  			
  	  			/**
  	  			 * Java data-types and data-type methods loaded from config file
  	  			 */
  	  			String[] dataTypes = prop.getProperty("JavaDataTypes").split(",");
  	  			String[] dataTypesMethods = prop.getProperty("dataTypesMethods").split(",");
  	  			
  	  			/**
  	  			 * appending required strings to final 'result'
  	  			 */
  	  			result+="\n\t\ttry{";
  					
  			         // To connect to mongodb server
  	  			result+="\n\t\t\tMongoClient mongoClient = new MongoClient(host,port);";
  						
  			         // Now connect to your databases
  	  			result+="\n\t\t\tDB db = mongoClient.getDB( databaseName );";
  	  			result+="\n\t\t\tSystem.out.println(\"Connect to database successfully\");";
  						
  	  			result+="\n\t\tDBCollection coll = db.getCollection(tableName);";
  	  			result+="\n\t\tint[] count = getSubQueryConfirmation( str);";

  	  		result+="\n\t\tint[] mainTagsIndex = mainClauses(str,count);";

  	  		result+="\n\t\t\tBasicDBObject whereCondition = WhereStatement(str,mainTagsIndex,host,port);";
  	  		result+="\n\t\t\tString[] ColumnNamesToReturned = selectColumnNamesToReturned(str,host,port);";
  	  		result+="\n\t\t\tfor(int i=0;i<ColumnNamesToReturned.length;i++){";
  	  		result+="\n\t\t\t\tif(!tableName .equals(tempTableName)){";
  	  		result+="\n\t\t\t\t\tColumnNamesToReturned[i] = ColumnNamesToReturned[i].replace(tempTableName+\".\", \"\");";
  	  		result+="\n\t\t\t\t}";
  	  		result+="\n\t\t\t\tColumnNamesToReturned[i] = this.performOper(ColumnNamesToReturned[i]);";
  	  		result+="\n\t\t\t}";
  	  		
  	  		result+="\n\t\t\tBasicDBObject sortQuery = sort(str,mainTagsIndex,host,port);";

  	  		result+="\n\t\t\tDBCursor cursor = coll.find(whereCondition);";
  	  		result+="\n\t\t\tcursor.sort(sortQuery);";
  	  		result+="\n\t\t\tString[] limitOffset = limitOffset(str,host,port).split(\":\");";

  	  		result+="\n\t\t\tint limitValue = Integer.parseInt(limitOffset[0]);";
  	  		result+="\n\t\t\tint offsetValue = Integer.parseInt(limitOffset[1]);";

  	  		result+="\n\t\t\tint counter = cursor.count();";
  	  		result+="\n\t\t\tif( limitValue !=0 && offsetValue !=0){";
  	  		result+="\n\t\t\tcursor.skip(offsetValue);";
  	  		result+="\n\t\t\tcursor.limit(limitValue);";
  					
  	  		result+="\n\t\t\tcounter = counter-offsetValue;";
  				

  	  		result+="\n\t\t\tif(counter >limitValue){";
  	  		result+="\n\t\t\tcounter = limitValue;";
  	  		result+="\n\t\t\t}";
			result+="\n\t\t\t}else if(counter > limitValue && limitValue !=0){";
  	  		result+="\n\t\t\tcursor.limit(limitValue);";
  	  		result+="\n\t\t\tcounter = limitValue;";
  	  		result+="\n\t\t\t}else if(offsetValue !=0){";
  	  		result+="\n\t\t\tcursor.skip(offsetValue);";
  	  		result+="\n\t\t\tcounter = counter-offsetValue;";
  	  		result+="\n\t\t\t}";
  	  		result+="\n\t\t\tString[][] arrayValues = new String[counter][ColumnNamesToReturned.length];";
  	  		result+="\n\t\t\tcounter=0;	";
  			result+="\n\t\twhile (cursor.hasNext()){";
  			result+="\n\t\t\tcursor.next();";
  				
  				/**
  				 * for each column is storing its values in DTO object
  				 */
  				result+="\n\t\t\t\tfor(int i=0;i<ColumnNamesToReturned.length;i++){";
  				result+="\n\t\t\t\t\tint colCounter=0;";
  				for(int i=0;i<colNames.length;i++){
  					for(int j=0;j<dataTypes.length;j++){
  						if(colTypes[i] .equals(dataTypes[j])){
  							if(i==0){
  							result+="\n\t\t\t\t\tif(ColumnNamesToReturned[i] .equals(\""+colNames[i]+"\")){";
  							result+="\n\t\t\t\t\t\tarrayValues[counter][i] = cursor.curr().get(\""+colNames[i]+"\").toString();";
  							result+="\n\t\t\t\t\t}";
  							}else{
  	  							result+="\n\t\t\t\t\telse if(ColumnNamesToReturned[i] .equals(\""+colNames[i]+"\")){";
  	  							result+="\n\t\t\t\t\t\tarrayValues[counter][i] = cursor.curr().get(\""+colNames[i]+"\").toString();";
  	  							result+="\n\t\t\t\t\t}";
  							}
  							break;
  						}
  					}
  				}
  				
  				
  				result+="\n\t\t\t\telse{";
  				result+="\n\t\t\t\t\tString tempMysqlFunctionChecker = this.performOper(ColumnNamesToReturned[i]);";
  				result+="\n\t\t\t\t\t\tif(tempMysqlFunctionChecker.contains(\"CONCAT\")){";
  				result+="\n\t\t\t\t\t\t\tString tempValue = tempMysqlFunctionChecker.substring(tempMysqlFunctionChecker.indexOf(\"(\")+1,tempMysqlFunctionChecker.length());";
						
  				result+="\n\t\t\t\t\t\t\tint[] subQueryCounter2 = this.getSubQueryConfirmation(tempValue);";
  				result+="\n\t\t\t\t\t\t\tArrayList<Integer> indexOfComma = new ArrayList<Integer>();";
  				result+="\n\t\t\t\t\t\t\tArrayList<String> eachCommaElement = new ArrayList<String>();";
  				result+="\n\t\t\t\t\t\t\tSystem.out.println(tempValue);";
  				result+="\n\t\t\t\t\t\t\tint tempIndexComma = tempValue.indexOf(\",\");";
  				result+="\n\t\t\t\t\t\t\twhile(tempIndexComma >=0){";
  				result+="\n\t\t\t\t\t\t\t\tif(subQueryCounter2[tempIndexComma] == 0){";
  				result+="\n\t\t\t\t\t\t\t\t\tindexOfComma.add(tempIndexComma);";
  				result+="\n\t\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\t\t\ttempIndexComma = tempValue.indexOf(\",\",tempIndexComma+1);";
  				result+="\n\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\t\tif(indexOfComma.isEmpty()){";
  				result+="\n\t\t\t\t\t\t\t\tSystem.out.println(\"qwerty\");";
  				result+="\n\t\t\t\t\t\t\t}";
						
  				result+="\n\t\t\t\t\t\t\tif(indexOfComma.size() ==0){";
  				result+="\n\t\t\t\t\t\t\t\teachCommaElement.add(tempValue);";
  				result+="\n\t\t\t\t\t\t\t}else{";
  				result+="\n\t\t\t\t\t\t\t\tfor(int j=0;j<indexOfComma.size();j++){";
  				result+="\n\t\t\t\t\t\t\t\t\tif(j==0){";
  				result+="\n\t\t\t\t\t\t\t\t\t\teachCommaElement.add(tempValue.substring(0,indexOfComma.get(0)));";
  				result+="\n\t\t\t\t\t\t\t\t\t}else{";
  				result+="\n\t\t\t\t\t\t\t\t\t\teachCommaElement.add(tempValue.substring(indexOfComma.get(j-1), indexOfComma.get(j)));";
  				result+="\n\t\t\t\t\t\t\t\t\t}";		
  				result+="\n\t\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\t\t\t\teachCommaElement.add(tempValue.substring(indexOfComma.get(indexOfComma.size()-1)));";	
  				result+="\n\t\t\t\t\t\t\t\t}";
						
  				result+="\n\t\t\t\t\t\t\t\tfor(int j=0;j<eachCommaElement.size();j++){";

  				result+="\n\t\t\t\t\t\t\t\t\tif(eachCommaElement.get(j).indexOf(\",\") ==0){";
  				result+="\n\t\t\t\t\t\t\t\t\t\teachCommaElement.set(j, eachCommaElement.get(j).replaceFirst(\",\",\"\"));";
								//	System.out.println(performOper(eachCommaElement.get(i)));
  				result+="\n\t\t\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\t\t\teachCommaElement.set(j,performOper(eachCommaElement.get(j)));";
							//System.out.println(eachOrQuery.get(i));
  				result+="\n\t\t\t\t\t\t\t}";
					
  				result+="\n\t\t\t\t\t\t\tString[] eachElement = new String[eachCommaElement.size()];";
  				result+="\n\t\t\t\t\t\t\tfor(int j=0;j<eachCommaElement.size();j++){";
  				result+="\n\t\t\t\t\t\t\t\teachElement[j] = this.performOper(eachCommaElement.get(j));";
  				result+="\n\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\t\tint[] colIndexId = new int[eachElement.length];";
  				result+="\n\t\t\t\t\t\t\tfor(int j=0;j<eachElement.length;j++){";
  				result+="\n\t\t\t\t\t\t\t\tcolIndexId[j] =-1;";
  				result+="\n\t\t\t\t\t\t\t\tfor(int k=0;k<colNames.length;k++){";
  				result+="\n\t\t\t\t\t\t\t\t\tif(eachElement[j] .equals(colNames[k])){";
  				result+="\n\t\t\t\t\t\t\t\t\t\tcolIndexId[j] = k;";
  				result+="\n\t\t\t\t\t\t\t\t\t\tbreak;";
  				result+="\n\t\t\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\t\t}";
						
  				result+="\n\t\t\t\t\t\t\tString concatValue=\"\";";
  				result+="\n\t\t\t\t\t\t\tfor(int j=0;j<eachElement.length;j++){";
  				result+="\n\t\t\t\t\t\t\t\tif(colIndexId[j] ==-1){";
  				result+="\n\t\t\t\t\t\t\t\t\tconcatValue+=eachElement[j];";
  				result+="\n\t\t\t\t\t\t\t\t}else{";
  				result+="\n\t\t\t\t\t\t\t\t\tconcatValue+=cursor.curr().get(eachElement[j]).toString();";
  				result+="\n\t\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t\tarrayValues[counter][i]=(concatValue);";
  				result+="\n\t\t\t\t\t}";
  				result+="\n\t\t\t\t\t}";
  				
  				result+="\n\t\t\t}";
  				result+="\n\t\t\tcounter++;";
  				result+="\n\t\t}";
  						
  						
  			      }catch(Exception e){
  			         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  			      }
			result+="\n\t\treturn arrayValues;";
			result+="\n\t}catch(Exception e){";
			result+="\n\t\t	System.err.println( e.getClass().getName() + \": \" + e.getMessage() );";
			result+="\n\t}";
			result+="\n\treturn null;";
			result+="\n\t}";	
			
			
			result+="\n\t/**";
			result+="\n\t * provide column list for select query including MySQL functions like COUNT,SUM,etc";
			result+="\n\t *"; 
			result+="\n\t * <p>";
			result+="\n\t * This method will take select Query as input and return identified list of columns as String array";
			result+="\n\t * <p>";
			result+="\n\t *"; 
			result+="\n\t *"; 
			result+="\n\t * @param		str				MySQL select Query";
			result+="\n\t * @return		eachElement		List of column names";
			result+="\n\t */";
			result+="\n\tpublic String[] selectColumnNamesToReturned(String str,String host,int port){";
			result+="\n\tint selectIndex = str.toUpperCase().indexOf(\"SELECT \");";
			result+="\n\tint fromIndex = str.toUpperCase().indexOf(\" FROM\");";
			result+="\n\tString listString = str.substring(selectIndex+6, fromIndex);";
			result+="\n\tif(performOper(listString) .equals( \"*\")){";
			result+="\n\treturn colNames;";
			result+="\n\t}";
			result+="\n\tint[] subQueryCounter2 = this.getSubQueryConfirmation(listString);";
			result+="\n\tArrayList<Integer> indexOfComma = new ArrayList<Integer>();";
			result+="\n\tArrayList<String> eachCommaElement = new ArrayList<String>();";
			//System.out.println(listString);
			result+="\n\tint tempIndexComma = listString.indexOf(\",\");";
			result+="\n\twhile(tempIndexComma >=0){";
			result+="\n\t\tif(subQueryCounter2[tempIndexComma] == 0){";
			result+="\n\t\t\tindexOfComma.add(tempIndexComma);";
			result+="\n\t\t}";
			result+="\n\t\ttempIndexComma = listString.indexOf(\",\",tempIndexComma+1);";
			result+="\n\t}";
			result+="\n\tif(indexOfComma.isEmpty()){";
			//	System.out.println("qwerty");
			result+="\n\t}";
			
			result+="\n\tif(indexOfComma.size() ==0){";
			result+="\n\t\teachCommaElement.add(listString);";
			result+="\n\t}else{";
			result+="\n\t\tfor(int i=0;i<indexOfComma.size();i++){";
			result+="\n\t\t\tif(i==0){";
			result+="\n\t\t\t\teachCommaElement.add(listString.substring(0,indexOfComma.get(0)));";
			result+="\n\t\t\t}else{";
			result+="\n\t\t\t\teachCommaElement.add(listString.substring(indexOfComma.get(i-1), indexOfComma.get(i)));";
			result+="\n\t\t\t}";		
			result+="\n\t\t}";
			result+="\n\t\teachCommaElement.add(listString.substring(indexOfComma.get(indexOfComma.size()-1)));";	
			result+="\n\t}";
			
			result+="\n\tfor(int i=0;i<eachCommaElement.size();i++){";
			//	System.out.println(eachCommaElement.get(i));
			result+="\n\t\tif(eachCommaElement.get(i).indexOf(\",\") ==0){";
			result+="\n\t\t\teachCommaElement.set(i, eachCommaElement.get(i).replaceFirst(\",\",\"\"));";
					//	System.out.println(performOper(eachCommaElement.get(i)));
			result+="\n\t\t}";
			//	eachCommaElement.set(i,performOper(eachCommaElement.get(i)));
				//System.out.println(eachOrQuery.get(i));
			result+="\n\t}";
		
			result+="\n\tString[] eachElement = new String[eachCommaElement.size()];";
			result+="\n\tfor(int i=0;i<eachCommaElement.size();i++){";
			result+="\n\t\teachElement[i] = eachCommaElement.get(i);";
			result+="\n\t}";


			result+="\n\treturn eachElement;";
			result+="\n\t}";

		return result;
	}

	
	/**
	 * generate method to create Google data-store query for select operation
	 * 
	 * <p>
	 * This method create 'select' Google data-store query from corresponding 'SELECT' query
	 * it determines the presence of 'WHERE' clause and sorting conditions.
	 * this method is common in all tables
	 * <p>
	 *  
	 * @return 	result	string of 'GetSelectQueryGenerator' method
	 */
	public String getSelectQueryGenerator(){
		String result="";
		result+="\n\tpublic Query SelectQueryGenerator(String str,String host,int port){";
		result+="\n\t\tQuery q = new Query(tableName);";
		result+="\n\t\tint indexOfWhere = str.toUpperCase().indexOf(\" WHERE \");";
		result+="\n\t\tint indexOfSort = str.toUpperCase().indexOf(\" ORDER BY \");";
		result+="\n\t\tint endIndex = str.indexOf(\";\");";
		result+="\n\t\tif(endIndex ==(-1)){";
		result+="\n\t\tendIndex = str.length();";
		result+="\n\t\t}";
		result+="\n\t\tif(indexOfWhere >0){";
		result+="\n\t\tif(indexOfSort>0){";
		result+="\n\t\tString WhereStatement = str.substring(indexOfWhere,indexOfSort);";
		result+="\n\t\tq = WhereStatement(q,WhereStatement);";
		result+="\n\t\t}else{";
		result+="\n\t\tString WhereStatement = str.substring(indexOfWhere,endIndex);";
		result+="\n\t\tq = WhereStatement(q,WhereStatement);";
		result+="\n\t\t}";
		result+="\n\t\t}";
		result+="\n\t\tif(indexOfSort>0){";
		result+="\n\t\tString sortStatement = str.substring(indexOfSort, endIndex);";
		result+="\n\t\tq = SortStatement(q,sortStatement);";
		result+="\n\t\t}";
		result+="\n\t\treturn q;";
		result+="\n\t}";
		
		return result;
		}
	
	
	public String selectAsArrayQueryGenerator(String tableName,String[] colTypes,String[] colNames){
		String result="";
		result+="\n\t/**";
		result+="\n\t * provide select select query result of one column as ArrayList";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method will take 3 inputs";
		result+="\n\t * 	a) select query in MySQL format with given specifications";
		result+="\n\t * 	b) host name to connect";
		result+="\n\t * 	c) port number to connect";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * If more than one column is asked in query this will result first column found";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t *"; 
		result+="\n\t * @param		str				input string with sql query to select"; 
		result+="\n\t * @param		host			host name";
		result+="\n\t * @param		port			port number";	
		result+="\n\t *"; 
		result+="\n\t *";
		result+="\n\t * @return		returnList		ArrayList of required one column or function";
		result+="\n\t */";
		result+="\n\tpublic ArrayList<String> selectAsArray(String str,String host,int port){";
		result+="\n\t\ttry{";
		result+="\n\t\t\tArrayList<String> returnList = new ArrayList<String>();";
		result+="\n\t\t\tString checkSingleValueMethods = this.singleValueOfMethods(str,host,port);";
		result+="\n\t\t\tif(checkSingleValueMethods.indexOf(\"NO\") ==0){";
					///First part will contain column id and second results as string separated by comma
		result+="\n\t\t\tint colNameToReturn = 0;";
		result+="\n\t\t\tMongoClient mongoClient = new MongoClient( \"localhost\" , 27017 );";
		result+="\n\t\t\tDB db = mongoClient.getDB( databaseName );";
					//System.out.println("Connect to database successfully");

		result+="\n\t\t\tDBCollection coll = db.getCollection(tableName);";
		result+="\n\t\t\tint[] count = getSubQueryConfirmation( str);";
		result+="\n\t\t\tint[] mainTagsIndex = mainClauses(str,count);";
		result+="\n\t\t\tBasicDBObject whereCondition = WhereStatement(str,mainTagsIndex,host,port);";
		result+="\n\t\t\tString[] columnNamesToReturned = selectColumnNamesToReturned(str,host,port);";
		result+="\n\t\t\tfor(int i=0;i<columnNamesToReturned.length;i++){";
		result+="\n\t\t\t\tif(!tableName .equals(tempTableName)){";
		result+="\n\t\t\t\t\tcolumnNamesToReturned[i] = columnNamesToReturned[i].replace(tempTableName+\".\", \"\");";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tcolumnNamesToReturned[i] = this.performOper(columnNamesToReturned[i]);";
		result+="\n\t\t\t}";
		result+="\n\t\t\tBasicDBObject sortQuery = sort(str,mainTagsIndex,host,port);";
		result+="\n\t\t\tDBCursor cursor = coll.find(whereCondition);";
		result+="\n\t\t\tcursor.sort(sortQuery);";
		result+="\n\t\t\tString[] limitOffset = limitOffset(str,host,port).split(\":\");";
		result+="\n\t\t\tint limitValue = Integer.parseInt(limitOffset[0]);";
		result+="\n\t\t\tint offsetValue = Integer.parseInt(limitOffset[1]);";
		result+="\n\t\t\tint counter = cursor.count();";
		result+="\n\t\t\tif( limitValue !=0 && offsetValue !=0){";
		result+="\n\t\t\t\tcursor.skip(offsetValue);";
		result+="\n\t\t\t\tcursor.limit(limitValue);";
		result+="\n\t\t\t\tcounter = counter-offsetValue;";
		result+="\n\t\t\t\tif(counter >limitValue){";
		result+="\n\t\t\t\t\tcounter = limitValue;";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}else if(counter > limitValue && limitValue !=0){";
		result+="\n\t\t\t\t	cursor.limit(limitValue);";
		result+="\n\t\t\t\tcounter = limitValue;";
		result+="\n\t\t\t}else if(offsetValue !=0){";
		result+="\n\t\t\t\tcursor.skip(offsetValue);";
		result+="\n\t\t\t\tcounter = counter-offsetValue;";
		result+="\n\t\t\t}";
		result+="\n\t\t\tcounter=0;";	
		result+="\n\t\t\twhile (cursor.hasNext()){";
		result+="\n\t\t\t\tcursor.next();";
		result+="\n\t\t\t\tString value = cursor.curr().get(columnNamesToReturned[0]).toString();";
		result+="\n\t\t\t\treturnList.add(value);";
						
		result+="\n\t\t\t\t	counter++;";
		result+="\n\t\t\t}";
		result+="\n\t\t}";
		result+="\n\t\t\tif(checkSingleValueMethods.indexOf(\"YES:\") ==0){";	
		result+="\n\t\t\t\tString valuess = checkSingleValueMethods.replaceFirst(\"YES:\", \"\");";
		result+="\n\t\t\t\treturnList.add(valuess);";
		result+="\n\t\t\t}";

		result+="\n\t\t\treturn returnList;";
		result+="\n\t\t\t}catch(Exception e){";
		result+="\n\t\t\t\tSystem.err.println( e.getClass().getName() + \": \" + e.getMessage() );";
		result+="\n\t\t\t}";
		result+="\n\t\t\treturn null;";
		result+="\n\t\t}";
		
		
		return result;
		
	}
	
	public String selectSingleValueQueryGenerator(){
		String result="";
		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method provide result with single value results"; 
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t *<p>";
		result+="\n\t * This method will take 3 inputs";
		result+="\n\t * 	a) select query in MySQL format with given specifications";
		result+="\n\t * 	b) host name to connect;";
				result+="\n\t * 	c) port number to connect";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t *"; 
		result+="\n\t *"; 
		result+="\n\t * @param		str				input string with sql query to select"; 
		result+="\n\t * @param		host			host name";
		result+="\n\t* @param		port			port number";	
		result+="\n\t *"; 
		result+="\n\t * @return		value 			single value output";
		result+="\n\t */";
		result+="\n\tpublic String selectSingleValue(String str,String host,int port){";
		result+="\n\t\tString value =this.singleValueOfMethods(str,host,port);";
		result+="\n\t\t\tif(value.indexOf(\"YES:\") ==0){";
		result+="\n\t\t\t\tvalue= value.replaceFirst(\"YES:\", \"\");";
		result+="\n\t\t\t}";
		result+="\n\t\treturn value;";
		result+="\n\t}";
		
		return result;
	}
	
	public String singleValueOfMethodsQueryGenerator(String tableName,String[] colTypes,String[] colNames){
		String result="";
		
		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method provide value for MySQL functions namely :- COUNT,MAX,MIN,AVG,SUM";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t *<p>";
		result+="\n\t * This method will take 3 inputs";
		result+="\n\t * 	a) select query in MySQL format with given specifications";
		result+="\n\t * 	b) host name to connect;";
		result+="\n\t * 	c) port number to connect";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method check the presence of listed MySQL functions and if function is present it will return \"YES:(Value)\".";
		result+="\n\t * Else it will return \"NO:NO\"";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t *"; 
		result+="\n\t * @param		str				input string with sql query to select";
		result+="\n\t * @param		host			host name";
		result+="\n\t * @param		port			port number";
		result+="\n\t *"; 
		result+="\n\t*"; 
		result+="\n\t * @return						required value";
		result+="\n\t */";
		result+="\n\tpublic String singleValueOfMethods(String str,String host,int port){";
		result+="\n\t\tString[] methodsToDetect = {\"COUNT\",\"MAX\",\"MIN\",\"AVG\",\"SUM\"};";
		result+="\n\t\tString[] columnNames = selectColumnNamesToReturned(str,host,port);";
		result+="\n\t\tint methodId =-1;";
		result+="\n\t\tfor(int i=0;i<methodsToDetect.length;i++){";
		result+="\n\t\t\tif(columnNames[0] .contains(methodsToDetect[i])){";
		result+="\n\t\t\t\t	methodId =i;";
		result+="\n\t\t\t\tbreak;";
		result+="\n\t\t\t}";
		result+="\n\t\t}";
		result+="\n\t\tif(methodId == -1){";
		result+="\n\t\t\treturn \"NO:NO\";";
		result+="\n\t\t}";
			//System.out.println(columnNames[0]);
		result+="\n\t\tString colName = columnNames[0].substring(columnNames[0].indexOf(\"(\")+1,columnNames[0].indexOf(\")\"));";
		result+="\n\t\tint[] subCounter = this.getSubQueryConfirmation(str);";
		result+="\n\t\tint[] clauseIdentified = this.mainClauses(str, subCounter);";
		result+="\n\t\tint indexWhereTemp = str.toUpperCase().indexOf(\"WHERE \");";
		result+="\n\t\tint indexOfWhere=0;";
		result+="\n\t\tString whereForQuery =\"\";";
			
		result+="\n\t\tif(indexWhereTemp <0){";
		result+="\n\t\t\tindexOfWhere=-1;";
		result+="\n\t\t}";
		result+="\n\t\twhile(indexWhereTemp >= 0) {";
		result+="\n\t\t\tif(subCounter[indexWhereTemp] ==0){";
		result+="\n\t\t\t\tindexOfWhere = indexWhereTemp;";
		result+="\n\t\t\t\tbreak;";
		result+="\n\t\t\t}";
		result+="\n\t\t\tindexWhereTemp = str.toUpperCase().indexOf(\"WHERE \", indexWhereTemp+1);";
		result+="\n\t\t}";
		result+="\n\t\tcolName = this.performOper(colName);";
		result+="\n\t\tif(methodId ==0){";
		result+="\n\t\tString query=\"\";";
		result+="\n\t\tif(indexOfWhere == -1){";
		result+="\n\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName;";
		result+="\n\t\t}else{";
		result+="\n\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName+\" \"+str.substring(indexOfWhere);";
		result+="\n\t\t}";
		result+="\n\t\tArrayList<String> values = this.selectAsArray(query,host,port);";
		result+="\n\t\treturn \"YES:\"+values.size();";
		result+="\n\t\t}else if(methodId ==1){";
		result+="\n\t\t\tString query=\"\";";
		result+="\n\t\t\tif(indexOfWhere == -1){";		
		result+="\n\t\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName+\" LIMIT 5 ORDER BY \"+colName+\" DESC \";";

		result+="\n\t\t\t}else{";		
		result+="\n\t\t\t\t	query = \"SELECT \"+colName+\" FROM \"+tableName+\" \"+str.substring(indexOfWhere)+\" LIMIT 5 ORDER BY \"+colName+\" DESC \";";

		result+="\n\t\t\t}";
		result+="\n\t\t\tArrayList<String> values = this.selectAsArray(query,host,port);";
		result+="\n\t\t\treturn \"YES:\"+values.get(0);";
		result+="\n\t\t}else if(methodId ==2){";			
		result+="\n\t\t\tString query=\"\";";
		result+="\n\t\t\tif(indexOfWhere == -1){";
		result+="\n\t\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName;";
		result+="\n\t\t\t}else{";
		result+="\n\t\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName;";
		result+="\n\t\t\t}";
		result+="\n\t\t\tArrayList<String> values = this.selectAsArray(query,host,port);";
		result+="\n\t\t\treturn \"YES:\"+values.get(0);";
		result+="\n\t\t\t}else if(methodId ==3){";			
		result+="\n\t\t\t\tString query=\"\";";
		result+="\n\t\t\t\tif(indexOfWhere == -1){";
		result+="\n\t\t\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName+\" LIMIT 5 ORDER BY \"+colName+\" \";";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName+\" \"+str.substring(indexOfWhere)+\" LIMIT 5 ORDER BY \"+colName+\" \";";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tdouble value=0.0;";
		result+="\n\t\t\t\tint counter=0;";
		result+="\n\t\t\t\tArrayList<String> values = this.selectAsArray(query,host,port);";
		result+="\n\t\t\t\tfor(int i=0;i<values.size();i++){";
		result+="\n\t\t\t\t\tvalue = value + Double.parseDouble(values.get(i));";
		result+="\n\t\t\t\t\tcounter++;";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tvalue = value/counter;";
		result+="\n\t\t\t\tString toString= Double.toString(value);";
		result+="\n\t\t\t\treturn \"YES:\"+toString;";
		result+="\n\t\t\t}else if(methodId ==4){";			
		result+="\n\t\t\t\t	String query=\"\";";
		result+="\n\t\t\t\tif(indexOfWhere == -1){";
		result+="\n\t\t\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName+\" LIMIT 5 ORDER BY \"+colName+\" \";";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\tquery = \"SELECT \"+colName+\" FROM \"+tableName+\" \"+str.substring(indexOfWhere)+\" LIMIT 5 ORDER BY \"+colName+\" \";";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tdouble value=0.0;";
				
		result+="\n\t\t\t\tArrayList<String> values = this.selectAsArray(query,host,port);";
		result+="\n\t\t\t\tfor(int i=0;i<values.size();i++){";
		result+="\n\t\t\t\t\tvalue = value + Double.parseDouble(values.get(i));";
			
		result+="\n\t\t\t\t	}";
			
		result+="\n\t\t\t\tString toString= Double.toString(value);";
		result+="\n\t\t\t\t\treturn \"YES:\"+toString;";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\treturn \"NO:NO\";";
		result+="\n\t\t\t}";
			
		result+="\n\t\t}";
		return result;
	}

	
	
	public String getSetTempTableNameMethod(){
		String result="";
		
		result+="\n\t/*";
		result+="\n\t * <p>";
		result+="\n\t * This method set temporary table name if provided in query";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 		str 	input string with sql query to select";
		result+="\n\t *"; 
		result+="\n\t */";
		result+="\n\tpublic void setTempTableName(String str){";
		result+="\n\t\tint[] subQueryChecker = this.getSubQueryConfirmation(str);";
		result+="\n\t\tint[] clausesIndex = this.mainClauses(str, subQueryChecker);";
			
		result+="\n\t\tint indexOfRequiredClause =0;";
		result+="\n\t\tfor(int i=0;i<clausesIndex.length;i++){";
		result+="\n\t\t\tif(clausesIndex[i] !=(-1) ){";
		result+="\n\t\t\t\tindexOfRequiredClause = clausesIndex[i];";
		result+="\n\t\t\t\tbreak;";
		result+="\n\t\t\t}";
		result+="\n\t\t}";
			
			
		result+="\n\t\tString requiredName = this.performOper(str.substring(str.toUpperCase().indexOf(\"FROM \"),indexOfRequiredClause).replaceFirst(\"FROM \", \"\"));";
		result+="\n\t\tString[] ava = requiredName.split(\" \");";

		result+="\n\t\tif(ava[ava.length-1].equals(tableName)){";
		result+="\n\t\t}else{";
		result+="\n\t\t\tthis.tempTableName = ava[ava.length-1];";
		result+="\n\t\t\tfor(int i=0;i<this.tempColNames.length;i++){";
		result+="\n\t\t\t\tthis.tempColNames[i] = this.tempTableName+\".\"+this.tempColNames[i];"; 
		result+="\n\t\t\t\tSystem.out.println(tempColNames[i]);";
		result+="\n\t\t\t}";
		result+="\n\t\t}";
		result+="\n\t\t}";
		return result;
	}
}