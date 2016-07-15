package com.sqltomongodb.essentials;

import com.mongodb.BasicDBObject;


public class SortQuery{

	public SortQuery(){

	}
	
	
	/**
	 * generate sort method
	 * 
	 * <p>
	 * This method create sort method in which sql query is send to indetify sorting paramenters
	 * it add these parameters to already defined datastore queries
	 * this method is common in all tables
	 * <p>
	 *  
	 * @return 	result	string of 'SortStatement' method
	 */
	public String getSortMethod(){
		String result="";
		
		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method is helper function that handles sort queries";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method will take 4 inputs";
		result+="\n\t * 	a) query in MySQL format with given specifications";
		result+="\n\t * 	b) index of required tags found";
		result+="\n\t * 	c) host name to connect;";
		result+="\n\t * 	d) port number to connect";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method determine required conditions for by sort statement and return BasicDBObject including sort conditions";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 		str			input update query";
		result+="\n\t * @param		tagsIndex	required detected tags";
		result+="\n\t * @param		host		host name";
		result+="\n\t * @param		port		port number";
		result+="\n\t *"; 
		result+="\n\t * @return		sortQuery	sort conditions in the form of BasicDBObject";
		result+="\n\t *"; 
		result+="\n\t */";
		result+="\n\tpublic BasicDBObject sort(String str,int[] tagsIndex,String host,int port){";
		result+="\n\tBasicDBObject sortQuery = new BasicDBObject();";

		result+="\n\tString sortStatement =\"\";";
		result+="\n\tint indexOfSort = str.toUpperCase().indexOf(\"ORDER BY \");";
		result+="\n\tif(indexOfSort <0){";
		result+="\n\t\tsortQuery.append(this.colNames[this.primaryKeyId], 1);";
		result+="\n\t\treturn sortQuery;";
		result+="\n\t}";
		result+="\n\tint requiredIndex=0;";
		result+="\n\tfor(int i=0;i<tagsIndex.length;i++){";
		result+="\n\t\tif(indexOfSort == tagsIndex[i]){";
		result+="\n\t\t\trequiredIndex=i;";
		result+="\n\t\t\tbreak;";
		result+="\n\t\t}";
		result+="\n\t}";
		result+="\n\tsortStatement = str.substring(tagsIndex[requiredIndex], tagsIndex[requiredIndex+1]);";
			
		result+="\n\tsortStatement =sortStatement.replaceAll(\"ORDER BY \",\"\");";
		result+="\n\tsortStatement =sortStatement.replaceAll(\"order by \",\"\");";
		result+="\n\tString[] eachElement = sortStatement.split(\",\");";
		result+="\n\t	for(int i=0;i<colNames.length;i++){";
		result+="\n\t\tfor(int j=0;j<eachElement.length;j++){";
		result+="\n\t\t\tif(eachElement[j].contains(colNames[i])){";
		result+="\n\t\t\t\t	if(eachElement[j].contains(\"DESC\")){";
		result+="\n\t\t\t\t\tsortQuery.append(colNames[i],-1);";
		result+="\n\t\t\t}else{";
		result+="\n\t\t\t\tsortQuery.append(colNames[i],1);";
		result+="\n\t\t\t}";
		result+="\n\t\t}";
		result+="\n\t}";
		result+="\n\t}";
		result+="\n\treturn sortQuery;";
		result+="\n\t}";
		return result;

	}
}