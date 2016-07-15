package com.sqltomongodb.essentials;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;





import java.util.regex.Pattern;





import com.mongodb.BasicDBObject;



public class WhereQuery{

	public WhereQuery(){

	}
	
	
	/**
	 * create method to handle where clause of sql queries
	 * 
	 * <p>
	 * this method create method to handle where clause of sql queries like select,delete or update 
	 * It add required parameters to already created Data-store queries
	 * <p>
	 *  
	 * @param dataTypes<array>			Java data-types available
	 * @param dataTypesUsed<array>		Java data-types used by current table 
	 * @param dataTypesMethods<array>	methods to handle Java data-type to Datastore
	 * @param daeComparators<array>		Google app engine comparision operators
	 * @return result					string with complete 'WhereStatement' method
	 */
	public String getWhereQuery(String[] dataTypes,String[] dataTypesUsed,String[] dataTypesMethods,String[] mongodbComparators){
		String result="";
		
		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This methods handles all the where related conditions and user various helper methods to get exact condition/value query need";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method takes 4 inputs";
		result+="\n\t * 	a) query in MySQL format with given specifications";
		result+="\n\t * 	b) index of required tags found";
		result+="\n\t * 	c) host name to connect;";
		result+="\n\t * 	d) port number to connect";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * It will return where condition in BasicDBObject which can later be queried with mongodb database";
		result+="\n\t * <p>"; 
		result+="\n\t *"; 
		result+="\n\t * @param 		str					input update query";
		result+="\n\t * @param		tagsIndex			required detected tags";
		result+="\n\t * @param		host				host name";
		result+="\n\t * @param		port				port number";
		result+="\n\t *"; 
		result+="\n\t * @return		whereQueryFinal		where conditions in form of BasicDBObject";
		result+="\n\t */";
		result+="\n\n\n\tpublic BasicDBObject WhereStatement(String str,int[] tagsIndex,String host,int port){";
		
		result+="\n\t\tif(!tableName .equals(tempTableName)){";
		result+="\n\t\t\tfor(int i=0;i<colNames.length;i++){";
		result+="\n\t\t\t\ttempColNames[i] = tempTableName+\".\"+colNames[i];";
		result+="\n\t\t\t}";
		result+="\n\t\t}";
		result+="\n\t\tString WhereStatement =\"\";";
		result+="\n\t\tBasicDBObject whereQueryFinal = new BasicDBObject();";
		
		result+="\n\n\t\tint[] subQueryCounter = getSubQueryConfirmation(str);";
		result+="\n\t\tint indexWhereTemp = str.toUpperCase().indexOf(\"WHERE \");";
		result+="\n\t\tif(indexWhereTemp <0){";
		result+="\n\t\t\treturn null;";
		result+="\n\t\t}";
		result+="\n\t\tint indexOfWhere=0;";
		result+="\n\t\twhile(indexWhereTemp >= 0) {";
		result+="\n\t\t\tif(subQueryCounter[indexWhereTemp] ==0){";
		result+="\n\t\t\t\tindexOfWhere = indexWhereTemp;";
		result+="\n\t\t\t\tbreak;";
		result+="\n\t\t\t}";
		result+="\n\t\t\tindexWhereTemp = str.toUpperCase().indexOf(\"WHERE \", indexWhereTemp+1);";
		result+="\n\t\t}";
		
		result+="\n\t\tif(indexOfWhere ==0){";
		result+="\n\t\t\treturn null;";
		result+="\n\t\t}";
		
		result+="\n\t\tint requiredIndex=0;";
		result+="\n\t\tfor(int i=0;i<tagsIndex.length;i++){";
		result+="\n\t\t\tif(indexOfWhere == tagsIndex[i]){";
		result+="\n\t\t\t\trequiredIndex=i;";
		result+="\n\t\t\t\tbreak;";
		result+="\n\t\t\t}";
		result+="\n\t\t}";
		
		result+="\n\t\tWhereStatement = str.substring(tagsIndex[requiredIndex], tagsIndex[requiredIndex+1]);";
		result+="\n\t\tWhereStatement =WhereStatement.replaceFirst(\"WHERE \",\"\");";
		result+="\n\t\tWhereStatement =WhereStatement.replaceFirst(\"where \",\"\");";
		
		
		result+="\n\t\tint[] subQueryCounter2 = getSubQueryConfirmation(WhereStatement);";

		result+="\n\t\tArrayList<Integer> indexOfOr = new ArrayList<Integer>();";
		result+="\n\t\tArrayList<String> eachOrQuery = new ArrayList<String>();";
		result+="\n\t\tint tempIndexOr = WhereStatement.toUpperCase().indexOf(\"OR \");";
		result+="\n\t\twhile(tempIndexOr >=0){";
		

		result+="\n\t\t\tif(subQueryCounter2[tempIndexOr] == 0){";
		result+="\n\t\t\t\tindexOfOr.add(tempIndexOr);";
		result+="\n\t\t\t}";
		result+="\n\t\ttempIndexOr = WhereStatement.toUpperCase().indexOf(\"OR \",tempIndexOr+1);";
		result+="\n\t\t}";
		
		
		result+="\n\t\tList<BasicDBObject> orLoopObject = new ArrayList<BasicDBObject>();";
		result+="\n\t\tif(indexOfOr.size() ==0){";
		result+="\n\t\t\teachOrQuery.add(WhereStatement);";
			
		result+="\n\t\t}else{";
		result+="\n\t\t\tfor(int i=0;i<indexOfOr.size();i++){";
		result+="\n\t\t\t\tif(i==0){";
		result+="\n\t\t\t\t\teachOrQuery.add(WhereStatement.substring(0,indexOfOr.get(0)));";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\teachOrQuery.add(WhereStatement.substring(indexOfOr.get(i-1), indexOfOr.get(i)));";
		result+="\n\t\t\t\t}";		
		result+="\n\t\t\t}";
		result+="\n\t\t\teachOrQuery.add(WhereStatement.substring(indexOfOr.get(indexOfOr.size()-1)));";
		result+="\n\t\t}";
		
		result+="\n\t\tfor(int i=0;i<eachOrQuery.size();i++){";

		result+="\n\t\t\tif(eachOrQuery.get(i).toUpperCase().indexOf(\"OR \") ==0){";
		result+="\n\t\t\t\teachOrQuery.set(i, eachOrQuery.get(i).replaceFirst(\"OR \",\"\"));";
		result+="\n\t\t\t}";
		result+="\n\t\t\teachOrQuery.set(i,performOper(eachOrQuery.get(i)));";
		result+="\n\t\t}";
		result+="\n\t\tfor(int kk=0;kk<eachOrQuery.size();kk++){";


		result+="\n\t\t\tBasicDBObject testQuery = new BasicDBObject();";
		
		result+="\n\t\t\tint[] subQueryCounterEachColumn = getSubQueryConfirmation(eachOrQuery.get(kk));";
		result+="\n\t\t\tArrayList<String> indexOfEachColumn = new ArrayList<String>();";
		result+="\n\t\t\tArrayList<String> eachColumnQuery = new ArrayList<String>();";
		
		result+="\n\t\t\tfor(int i=0;i<tempColNames.length;i++){";
		result+="\n\t\t\t\tint tempIndexColumn = eachOrQuery.get(kk).indexOf(tempColNames[i]);";
		result+="\n\t\t\t\twhile(tempIndexColumn >=0){";

		result+="\n\t\t\t\t\tif(subQueryCounterEachColumn[tempIndexColumn] == 0){";
		result+="\n\t\t\t\t\t\tindexOfEachColumn.add(Integer.toString(tempIndexColumn)+\":\"+Integer.toString(i));";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t\ttempIndexColumn = eachOrQuery.get(kk).indexOf(tempColNames[i],tempIndexColumn+1);";
		result+="\n\t\t\t\t}";		
		result+="\n\t\t\t}";
		
		result+="\n\t\t\tfor(int i=0;i<indexOfEachColumn.size();i++){";

		result+="\n\t\t\t\tString[] tempSplit = indexOfEachColumn.get(i).split(\":\");";
		result+="\n\t\t\t\tint tempIndexOfCol = Integer.parseInt(tempSplit[0]);";
		result+="\n\t\t\t\tif(i ==(indexOfEachColumn.size()-1)){";
		result+="\n\t\t\t\t\teachColumnQuery.add(eachOrQuery.get(kk).substring(tempIndexOfCol));";
		result+="\n\t\t\t\t}else{";
		result+="\n\t\t\t\t\tString[] tempSplit1 = indexOfEachColumn.get(i+1).split(\":\");";
		result+="\n\t\t\t\t\tint tempIndexOfCol1 = Integer.parseInt(tempSplit1[0]);";
					
		result+="\n\t\t\t\t\teachColumnQuery.add(performOper(eachOrQuery.get(kk).substring(tempIndexOfCol,tempIndexOfCol1)));";
		result+="\n\t\t\t\t}";	
		result+="\n\t\t\t}";
		
		
		result+="\n\t\t\tfor(int i=0;i<eachColumnQuery.size();i++){";

		result+="\n\t\t\t\tif(eachColumnQuery.get(i).toUpperCase().indexOf(\"AND\") ==(eachColumnQuery.get(i).length()-3)){";
		result+="\n\t\t\t\t\teachColumnQuery.set(i,eachColumnQuery.get(i).substring(0, eachColumnQuery.get(i).length()-3));";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tint[] indexOfEachColumnInWhere = new int[colNames.length];";
		result+="\n\t\t\tint[] indexOfEachColumnNumber = new int[colNames.length];";
		result+="\n\t\t\tint[] subQueryCounter3 = getSubQueryConfirmation(eachOrQuery.get(kk));";

		result+="\n\t\t\tfor(int i=0;i<tempColNames.length;i++){";
		result+="\n\t\t\t\tindexOfEachColumnInWhere[i] = -1;";
		result+="\n\t\t\t\tint tempIndexOfCol = eachOrQuery.get(kk).indexOf(tempColNames[i]);";
		result+="\n\t\t\t\twhile(tempIndexOfCol >= 0) {";
		result+="\n\t\t\t\t\tif(subQueryCounter3[tempIndexOfCol] ==0){";
		result+="\n\t\t\t\t\t\tindexOfEachColumnInWhere[i] = tempIndexOfCol;";
		result+="\n\t\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t\ttempIndexOfCol = str.indexOf(tempColNames[i], tempIndexOfCol+1);";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
		result+="\n\t\t\tint aa=0;";
		
		
		result+="\n\t\t\twhile(aa<eachColumnQuery.size()){";

		result+="\n\t\t\t\tint clauseIdentified = findClause(eachColumnQuery.get(aa));";
			
		result+="\n\t\t\t\tif(clauseIdentified ==1){";

		result+="\n\t\t\t\t\tString test = eachColumnQuery.get(aa);";
		result+="\n\t\t\t\t\tint colIdInWhere=0,sqlComparatorId=0;";
		result+="\n\t\t\t\t\tfor(int i=0;i<tempColNames.length;i++){";
		result+="\n\t\t\t\t\t\tif(test.indexOf(tempColNames[i]) <5 && test.indexOf(tempColNames[i]) !=(-1)){";
		result+="\n\t\t\t\t\t\t\tcolIdInWhere = i;";
		result+="\n\t\t\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t\t\t}";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t\tfor(int i=0;i<sqlComparators.length;i++){";
		result+="\n\t\t\t\t\t\tif(test.contains(sqlComparators[i])){";
		result+="\n\t\t\t\t\t\t\tsqlComparatorId=i;";
		result+="\n\t\t\t\t\t\t}";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t\tString columnTypeIdentified = colTypes[colIdInWhere];";

		for(int i=0;i<dataTypesUsed.length;i++){
			result+="\n\t\t\tif(columnTypeIdentified .equals(\""+dataTypesUsed[i]+"\")){";
			
		
		result+="\n\t\t\tString[] br = test.split(sqlComparators[sqlComparatorId],2);";
		result+="\n\t\t\t"+dataTypesUsed[i]+" value = "+dataTypesMethods[i]+"(performOper(getValue(br[1],host,port).get(0)));";
		result+="\n\t\t\tif(sqlComparators[sqlComparatorId] == \"=\"){";
		result+="\n\t\t\t\ttestQuery.put(colNames[colIdInWhere],value);";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(sqlComparators[sqlComparatorId] == \"!=\"){";
		result+="\n\t\t\t\ttestQuery.put(colNames[colIdInWhere], new BasicDBObject(\"$ne\",value));";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(sqlComparators[sqlComparatorId] == \"<\"){";
		result+="\n\t\t\t\ttestQuery.put(colNames[colIdInWhere], new BasicDBObject(\"$lt\",value));";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(sqlComparators[sqlComparatorId] == \">\"){";
		result+="\n\t\t\t\ttestQuery.put(colNames[colIdInWhere], new BasicDBObject(\"$gt\",value));";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(sqlComparators[sqlComparatorId] == \"<=\"){";
		result+="\n\t\t\t\ttestQuery.put(colNames[colIdInWhere], new BasicDBObject(\"$lte\",value));";
		result+="\n\t\t\t}";
		result+="\n\t\t\tif(sqlComparators[sqlComparatorId] == \">=\"){";
		result+="\n\t\t\t\ttestQuery.put(colNames[colIdInWhere], new BasicDBObject(\"$gte\",value));";
		result+="\n\t\t\t}";
		result+="\n\t\t\t}";
		}
		
		result+="\n\t\t\t\taa++;";
		
		result+="\n\t\t\t}else if(clauseIdentified ==2){";
		
		result+="\n\t\t\t\t\tString test = eachColumnQuery.get(aa);";
		result+="\n\t\t\t\t\tint colIdInWhere=0,sqlComparatorId=0;";
		result+="\n\t\t\t\t\tfor(int i=0;i<tempColNames.length;i++){";
		result+="\n\t\t\t\t\t\tif(test.indexOf(tempColNames[i]) <5 && test.indexOf(tempColNames[i]) !=(-1)){";
		result+="\n\t\t\t\t\t\t\tcolIdInWhere = i;";
		result+="\n\t\t\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t\t\t}";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t\tfor(int i=0;i<sqlComparators.length;i++){";
		result+="\n\t\t\t\t\t\tif(test.contains(sqlComparators[i])){";
		result+="\n\t\t\t\t\t\t\tsqlComparatorId=i;";
		result+="\n\t\t\t\t\t\t}";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t\tString columnTypeIdentified = colTypes[colIdInWhere];";
		result+="\n\t\t\t\t\ttest = test.replaceFirst(tempColNames[colIdInWhere], \"\");";
		result+="\n\t\t\t\t\ttest = test.replaceFirst(\"IN \", \"\");";
		
		result+="\n\t\t\t\t\tString[] aaaa = getInClauseValue(test,host,port);"; 
		for(int i=0;i<dataTypesUsed.length;i++){
			
			result+="\n\t\t\t\t\tif(columnTypeIdentified .equals(\""+dataTypesUsed[i]+"\")){";
			result+="\n\t\t\t\t\t"+dataTypesUsed[i]+"[] values = new "+dataTypesUsed[i]+"[aaaa.length];";
			result+="\n\t\t\t\t\t\tfor(int i=0;i<values.length;i++){";
			result+="\n\t\t\t\t\t\t\tvalues[i] ="+dataTypesMethods[i]+"(performOper(aaaa[i]));";
			result+="\n\t\t\t\t\t\t}";
			result+="\n\t\t\t\t\t\ttestQuery.put(colNames[colIdInWhere], new BasicDBObject(\"$in\",values));";
			result+="\n\t\t\t\t\t}";
		}
		
		result+="\n\t\t\t\taa++;";
		
		result+="\n\t\t\t}else if(clauseIdentified == 3){";
		result+="\n\t\t\t\tString test = eachColumnQuery.get(aa);";
		result+="\n\t\t\t\tint colIdInWhere=0,sqlComparatorId=0;";
		result+="\n\t\t\t\tfor(int i=0;i<tempColNames.length;i++){";
		result+="\n\t\t\t\t\tif(test.indexOf(tempColNames[i]) <5 && test.indexOf(tempColNames[i]) !=(-1)){";
		result+="\n\t\t\t\t\t\tcolIdInWhere = i;";
		result+="\n\t\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tfor(int i=0;i<sqlComparators.length;i++){";
		result+="\n\t\t\t\t\tif(test.contains(sqlComparators[i])){";
		result+="\n\t\t\t\t\t\tsqlComparatorId=i;";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tString columnTypeIdentified = colTypes[colIdInWhere];";
		result+="\n\t\t\t\ttest = test.replaceFirst(tempColNames[colIdInWhere], \"\");";
		result+="\n\t\t\t\ttest = test.replaceFirst(\"BETWEEN \", \"\");";
		result+="\n\t\t\t\tint[] subQueryCounterTemp = this.getSubQueryConfirmation(test);";
		result+="\n\t\t\t\tint indexOfAnd = test.toUpperCase().indexOf(\" AND \");";
		result+="\n\t\t\t\tString query1=\"\",query2=\"\";";
		result+="\n\t\t\t\twhile(indexOfAnd >=0){";
		result+="\n\t\t\t\t\tif(subQueryCounterTemp[indexOfAnd] == 0){";
		result+="\n\t\t\t\t\t\tquery1 = test.substring(0, indexOfAnd);";
		result+="\n\t\t\t\t\t\tquery2 = test.substring(indexOfAnd+5);";
		result+="\n\t\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t\tindexOfAnd = test.toUpperCase().indexOf(\" AND \",indexOfAnd+1);";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\teachOrQuery.add(tempColNames[colIdInWhere]+\">=\"+query1);";
		result+="\n\t\t\t\teachOrQuery.add(tempColNames[colIdInWhere]+\"<=\"+query2);";
		result+="\n\t\t\t\taa++;";
		
		result+="\n\t\t\t}else if(clauseIdentified == 4){";
		result+="\n\t\t\t\tString test = eachColumnQuery.get(aa);";
		result+="\n\t\t\t\tint colIdInWhere=0,sqlComparatorId=0;";
		result+="\n\t\t\t\tfor(int i=0;i<tempColNames.length;i++){";
		result+="\n\t\t\t\t\tif(test.indexOf(tempColNames[i]) <5 && test.indexOf(tempColNames[i]) !=(-1)){";
		result+="\n\t\t\t\t\t\tcolIdInWhere = i;";
		result+="\n\t\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tfor(int i=0;i<sqlComparators.length;i++){";
		result+="\n\t\t\t\t\tif(test.contains(sqlComparators[i])){";
		result+="\n\t\t\t\t\t\tsqlComparatorId=i;";
		result+="\n\t\t\t\t\t}";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tString columnTypeIdentified = colTypes[colIdInWhere];";
		result+="\n\t\t\t\ttest = test.replaceFirst(tempColNames[colIdInWhere], \"\");";
		result+="\n\t\t\t\ttest = test.replaceFirst(\"LIKE \", \"\");";
		result+="\n\t\t\t\tString aaaa = getLikeClauseValue(test,host,port);"; 
		result+="\n\t\t\t\tPattern regex = Pattern.compile(aaaa); ";
		result+="\n\t\t\t\ttestQuery.put(colNames[colIdInWhere], regex);";
		
		result+="\n\t\t\t\taa++;";
		result+="\n\t\t\t}";
		
		result+="\n\t\t\torLoopObject.add(testQuery);";
		result+="\n\t\t\t}";

		result+="\n\t\t\twhereQueryFinal.put(\"$or\", orLoopObject);";
		result+="\n\t\t\t}";


		result+="\n\t\t\treturn whereQueryFinal;";
		result+="\n\t\t}";


		return result;
	}
	
	
	public String getValueMethodGenerator(String tableName){
		String result="";
		
		
		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method returns actual value to be used in where condition.";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method takes 3 inputs:-";
		result+="\n\t * 	a) query which basically contains value portion";
		result+="\n\t * 	b) hostname of required mongodb";
		result+="\n\t * 	c) port number of the same";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method checks the whether the input contains actual value or subquery to return set of values";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 		str		query portion containing where values";
		result+="\n\t * @param 		host	host name";
		result+="\n\t * @param 		port	port number";
		result+="\n\t *"; 
		result+="\n\t * @return		value  	values in the form of arraylist";
		result+="\n\t */";
		result+="\n\t\tpublic ArrayList<String> getValue(String str,String host,int port){";
		result+="\n\t\t\tArrayList<String> value = new ArrayList<String>();";
		result+="\n\t\t\tint counter=0;";
		result+="\n\t\t\tint[] subQueryCounter = getSubQueryConfirmation(str);";
		result+="\n\t\t\tint indexOfSelect = str.toUpperCase().indexOf(\"SELECT \");";
		result+="\n\t\t\tint selectCounter=0;";
		result+="\n\t\t\twhile(indexOfSelect >=0){";
		result+="\n\t\t\t\tif(subQueryCounter[indexOfSelect] ==0){";
		result+="\n\t\t\t\t\tselectCounter++;";
		result+="\n\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\tindexOfSelect = str.toUpperCase().indexOf(\"SELECT \",indexOfSelect+1);";
		result+="\n\t\t\t}";
			
		result+="\n\t\t\tfor(int i=0;i<subQueryCounter.length;i++){";
		result+="\n\t\t\t\tif(subQueryCounter[i] ==0){";
		result+="\n\t\t\t\t\tcounter++;";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t}";
			
		result+="\n\t\t\tif(counter ==subQueryCounter.length && selectCounter==0){";
				
		result+="\n\t\t\t\tvalue.add(str);";
		result+="\n\t\t\t\t	return value;";
		result+="\n\t\t\t}";
			
		result+="\n\t\t\tstr = performOper(str);";
		result+="\n\t\t\t"+tableName+"DAO test = new "+tableName+"DAO();";
			
		result+="\n\t\t\tArrayList<String> a = test.selectAsArray(str,host,port);";
			
		result+="\n\t\t\t\treturn a;";
		result+="\n\t\t\t}";
		
		return result;
	}
	
	
	
	public String getInClauseValueAndOtherMethods(){
		
  		String fileName1="resources\\sideMethods.txt";
		String sideMethods="",line1=null;
		try{
			FileReader fileReader1 = new FileReader(fileName1);
			BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
			while((line1 = bufferedReader1.readLine()) != null) {
				sideMethods +=line1+"\n";
			}
			bufferedReader1.close();         
		}
		catch(FileNotFoundException ex) {
        System.out.println("Unable to open file '" + fileName1 + "'");                
		}
		catch(IOException ex) {
        System.out.println("Error reading file '" + fileName1 + "'");                  
		}
		
		return sideMethods;
		
	}
	
}