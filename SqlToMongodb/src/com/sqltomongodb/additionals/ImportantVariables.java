package com.sqltomongodb.additionals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 
 * @author Akshay P. Singh <akshayps@iitrpr.ac.in>
 *
 */
public class ImportantVariables{

	public ImportantVariables(){

	}
	
	/**
	 * Generate string with required public variables 
	 * 
	 * <p>
	 * Corresponding to each table statement, this method provide public variables required final generated files.
	 * <p>
	 * 
	 * @param tableName			table name extracted from input sql file
	 * @param colNames			column names for the corresponding table
	 * @param colTypes			corresponding data-types of each column 
	 * @param primaryKeyIndex	primary key id extracted from input file
	 * @return	result			string with required public variables for DTO and DAO files 
	 */
	public String generatePublicVariables(String tableName,String[] colNames,String[] colTypes,int primaryKeyIndex,String dbName){
		String result="";
		Properties prop = new Properties();
   		InputStream input = null;
		try {
      		input = new FileInputStream("resources\\config\\config.properties");
      		prop.load(input);
      		
      		/**
      		 * Mongo db comparison operators,Sql comparison operators and Java data-types respectively loaded from config file
      		 */
      		String[] mongodbMysqlComparators = prop.getProperty("MongodbMysqlComparators").split(",");
      		int sizeComparators = mongodbMysqlComparators.length;
      		String[] mongodbComparators = new String[sizeComparators];
      		String[] sqlComparators = new String[sizeComparators];
      		for(int i=0;i<sizeComparators;i++){
      			mongodbComparators[i] = mongodbMysqlComparators[i].split(":")[0];
      			sqlComparators[i] = mongodbMysqlComparators[i].split(":")[1];
      		}
      		String[] javaDataTypes=prop.getProperty("JavaDataTypes").split(",");
      		String[] dataTypesUsed = prop.getProperty("dataTypesUsed").split(",");
      		String[] dataTypesMethods = prop.getProperty("dataTypesMethods").split(",");
      		
      		
      		result+="\n\tpublic String databaseName=\""+dbName+"\";";
      		result+="\n\tpublic String tableName=\""+tableName+"\";";
      		result+="\n\tpublic String tempTableName=\""+tableName+"\";";
      		
      		/**
      		 * Adding Java data-types to result string
      		 */
      		result+="\n\tpublic String[] javaDataTypes={";
      		for(int i=0;i<javaDataTypes.length;i++){
      			if(i== (javaDataTypes.length-1)){
      				result+="\""+javaDataTypes[i]+"\"";
      			}else{
      				result+="\""+javaDataTypes[i]+"\",";
      			}
      		}
      		result+="};";
      		
      		/**
      		 * Adding column names to result string
      		 */
      		result+="\n\tpublic String[] colNames={";
			for(int i=0;i<colNames.length;i++){
				if(i== (colNames.length-1)){
					result+="\""+colNames[i]+"\"";
				}else{
					result+="\""+colNames[i]+"\",";
				}
			}
			result+="};";
      		result+="\n\tpublic String[] tempColNames={";
			for(int i=0;i<colNames.length;i++){
				if(i== (colNames.length-1)){
					result+="\""+colNames[i]+"\"";
				}else{
					result+="\""+colNames[i]+"\",";
				}
			}
			result+="};";
			
			/**
			 * Adding Google DAE comparison operators
			 */
			result+="\n\tpublic String[] daeComparators={";
			for(int i=0;i<mongodbComparators.length;i++){
				if(i==(mongodbComparators.length-1)){
				result+="\""+mongodbComparators[i]+"\"";
				}else{
					result+="\""+mongodbComparators[i]+"\",";
				}
			}
			result+="};";
			
			/**
			 * Adding Sql comparison operators
			 */
			result+="\n\tpublic String[] sqlComparators={";
			for(int i=0;i<sqlComparators.length;i++){
				if(i ==(sqlComparators.length-1)){
					result+="\""+sqlComparators[i]+"\"";
				}else{
					result+="\""+sqlComparators[i]+"\",";
				}
			}
			result+="};";
			
			/**
			 * Adding data-types of corresponding columns
			 */
			result+="\n\tpublic String[] colTypes= {";
			for(int i=0;i<colTypes.length;i++){
				result+="\""+colTypes[i]+"\"";
				if(i==colTypes.length-1){
				}else{
					result+=",";
				}
			}
			result+="};";
			
			/**
			 * Adding primary key id index 
			 */
			result+="\n\tpublic int primaryKeyId="+String.valueOf(primaryKeyIndex)+";";
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
	
	
	
	
	
	
	
	
	public String[] getTableNames(String[] tables){
		String[] tableNames = new String[tables.length];
		for(int jj=0;jj<tables.length;jj++){
			tables[jj] =tables[jj].replace(" IF NOT EXISTS "," ");
			tables[jj] =tables[jj].replace(" if not exists "," ");

			/**
			 * each table query
			 */
			String tempStatement = tables[jj];
			String[] token = tempStatement.split(" ",4);
			tableNames[jj] = token[2];
			
			/**
			 * @see com.sqltomongodb.additionals.BasicOperation
			 */
			BasicOperation stringManipulation = new BasicOperation();
			tableNames[jj]  = stringManipulation.performOper(tableNames[jj] );
	}
		return tableNames;
	}

}