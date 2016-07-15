package com.sqltomongodb.index;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.sqltomongodb.additionals.BasicOperation;
import com.sqltomongodb.additionals.SqlParser;

/**
 * 
 * @author Akshay P. Singh <akshayps@iitrpr.ac.in>
 *
 */
public class DTOGenerator {
	public DTOGenerator(){
		
	}
	
	/**
	 * provide imports required for DTO files
	 * 
	 * <p>
	 * Required DTO files must include some imports.
	 * This method provide those imports
	 * <p>
	 * 
	 * @return String with list of imorts required
	 */
	public String imports(String tableName,String dbName){

		String result="";
		result+="package com.mysqltomongodb."+dbName+".DTO;\n\n\n";

		result+="import com.mysqltomongodb."+dbName+".*;\n";
		result+="import java.sql.Date;\n";
		result+="import java.sql.Timestamp;\n";
		result+="import java.sql.Time;\n";
		result+="import java.util.ArrayList;\n\n";

		return result+"\n";
	}
	
	
	
	/**
	 * remove brackets and content inside brackets from input string
	 * 
	 * @param 	str		input string 	
	 * @return	str		modified input string
	 */
	public String extractDataType(String str){
		if(str.indexOf("(")>=0 && str.indexOf(")")>=0){
			return str.substring(0,str.indexOf("("));
		}else{
			return str;
		}
	}
	
	
	
	
	
	
	/**
	 * find sql data-type in input string
	 * 
	 * <p>
	 * this method identify sql-datatype in given sql query and return corresponding Java data-type
	 * reads previosly stored data-types from config file
	 * <p>
	 * 
	 * @param 	in		input string
	 * @return			Java date-type identified
	 */
	public String findType(String in){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			/**
			 * reading from config file
			 */
			input = getClass().getClassLoader().getResourceAsStream("config/config.properties");
      		prop.load(input);
      		
      		/**
      		 * storing sql data-types and corresponding java data-types from config file
      		 */
      		String[] SqlJavaMongodbDataTypes = prop.getProperty("SqlJavaMongodbDataTypes").split(",");
      		int size = SqlJavaMongodbDataTypes.length;
      		String[] sqlDataTypes = new String[size];
      		String[] javaDataTypesForCorrespondingSql = new String[size];
      		for(int i=0;i<size;i++){
      			String[] singleElement = SqlJavaMongodbDataTypes[i].split(":");
      			sqlDataTypes[i] = singleElement[0];
      			javaDataTypesForCorrespondingSql[i] = singleElement[1];
      		}
   			for(int i=0;i<sqlDataTypes.length;i++){
   				if(extractDataType(in).toUpperCase().equals(sqlDataTypes[i])){
      			return javaDataTypesForCorrespondingSql[i];
      			}
      		}
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
		return "String";
	}


	
	
	
	/**
	 * check presence of sql keys in given input string
	 * 
	 * @param 	str		input string
	 * @return	true	if string contains sql keys like 'primary key'
	 */
	public boolean checkSqlKeys(String str){
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
      		String[] sqlKeys = prop.getProperty("SqlKeys").split(",");
      		
            /**
             * Object for doing basic operations on input string
             * @see com.sqltomongodb.additionals.BasicOperation
             */
      		BasicOperation stringManipulation2 = new BasicOperation();
      		str = stringManipulation2.performOper(str);
      		for(int i=0;i<sqlKeys.length;i++){
      			if(str.toUpperCase().indexOf(sqlKeys[i]) ==0){
      				return true;
      			}
      		}
      		return false;

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
   		return false;
	}
	
	
	/**
	 * Provide string for SetValue method for each column element
	 * 
	 * <p>
	 * Take input each column details and create its set value method with its original sql data-type's corresponding Java data-type
	 * First check for sql KEYS if not found it will create each column's method with name : set"COLUMN_NAME" 
	 * <p>
	 * 
	 * @param 	in 		string from create table statement to add new column e.g., [`name` varchar(300) NOT NULL]
	 * @return	result	string with correspoding method to set value of column
	 */
	public String setValuesFunctions(String in){
		String result="";
		
		/**
		 * check whether input string contain column information or just stating "KEY" information"
		 */
		if(checkSqlKeys(in)){
			return "";
		}

		result+="\tpublic void set";
		String[] tokens = in.split(" ");
		
        /**
         * Object for doing basic operations on input string
         * @see com.sqltomongodb.additionals.BasicOperation
         */
		BasicOperation stringManipulation2 = new BasicOperation();
		tokens[0]=stringManipulation2.performOper(tokens[0]);
		
		result+=tokens[0]+"( "+findType(tokens[1])+" "+tokens[0]+" ) {\n\t\tthis."+tokens[0]+"= "+tokens[0]+";\n\t}\n";
		return result;
		
	}
	
	
	
	/**
	 * Provide string for ReturnValue method for each column element
	 * 
	 * <p>
	 * Take input each column details and create its value return method with its original sql data-type's corresponding Java data-type
	 * First check for sql KEYS if not found it will create each column's method with name : get"COLUMN_NAME" 
	 * <p>
	 * 
	 * @param 	in 		string from create table statement to add new column e.g., [`name` varchar(300) NOT NULL]
	 * @return	result	string with correspoding method to get value of column
	 */
	public String findReturnFunctions(String in){
		String result="";
		
		/**
		 * check whether input string contain column information or just stating "KEY" information"
		 */
		if(checkSqlKeys(in)){
			return result;
		}
		result+="\tpublic ";
		String[] tokens = in.split(" ");
		
        /**
         * Object for doing basic operations on input string
         * @see com.sqltomongodb.additionals.BasicOperation
         */
		BasicOperation stringManipulation2 = new BasicOperation();
		tokens[0]=stringManipulation2.performOper(tokens[0]);
		result+= findType(tokens[1])+" get"+tokens[0]+"() { \n\t\treturn "+tokens[0]+";\n\t}\n";
		
		return result;
	}
	
	
	
	
	
	/**
	 * Provide all column variables as Java variables and corresponding data-types
	 * 
	 * <p>
	 * This method take string as column details of create table statement and extract column name and
	 * its datatype and store them as in Java variables format and add them to class with table name
	 * <p>
	 * 
	 * @param 	in		string from create table statement to add new column e.g., [`name` varchar(300) NOT NULL]
	 * @return	result	string containing corresponding column as its corresponding java variable	
	 */
	public String typesAttrs(String in){
		/**
		 * return string
		 */
		String result="";
		
		/**
		 * check whether input string contain column information or just stating "KEY" information"
		 */
		if(checkSqlKeys(in)){
			return result;
		}
		
		result+="\tprivate ";
		
        /**
         * Object for doing basic operations on input string
         * @see com.sqltomongodb.additionals.BasicOperation
         */
		BasicOperation stringManipulation1 = new BasicOperation();
		in = stringManipulation1.performOper(in);
		
		/**
		 * break input string into tokens
		 */
		String[] tokens = in.split(" ");
		
		tokens[0]=stringManipulation1.performOper(tokens[0]);
		
		/**
		 * 'findType' method will identify the data-type of column
		 */
		result+= findType(tokens[1])+" "+tokens[0]+";\n";
		return result;
		
	}
	
	
	
	
	
	/**
	 * Generate DTO file
	 * 
	 * <p>
	 * This method generate DTO file for each "Create table" statement provided.
	 * It reads sql "Create table" and extract columns and their corresponding datatypes and 
	 * create Java variables correspoding to each column with name as "TABLE NAME"_DTO.java
	 * and save it in required location.
	 * <p>
	 * 
	 * @param str[]		array of create table query for each table
	 * @param path		Location where the generated files need to ne saved.
	 */
	public void createModel(String str[],String path,String[] dbNames){

        /**
         * Object for parsing sql queries from input provided.
         * @see com.sqltomongodb.additionals.SqlParser
         */
		SqlParser par = new SqlParser();
		
        /**
         * Object for doing basic operations on input string
         * @see com.sqltomongodb.additionals.BasicOperation
         */
		BasicOperation stringManipulation = new BasicOperation();
		
		/**
		 * 'k' act as counter when we operate on each statement 
		 */
		for(int k=0;k<str.length;k++){
			str[k] =str[k].replace(" IF NOT EXISTS "," ");
			
			/**
			 * 'result' is final string which will contain the text to be generated.
			 */
			String result="";
			
			/**
			 * individual statement/query
			 */
			String tempStatement = str[k];
			/**
			 * break down into token for names and other details.
			 */
			String[] tokens = str[k].split(" ",4);
			String tableName = tokens[2];
			
			/**
			 * @see com.sqltomongodb.additionals.BasicOperation
			 */
			tableName = stringManipulation.performOper(tableName);
			
			/**
			 * required imports for DTO code
			 */
			String imports = imports(tableName,dbNames[k]);
			
			/**
			 * Extract columns from table query
			 * @see com.sqltomongodb.additionals.SqlParser
			 */
			String[] columns = par.extractingColumns(tempStatement);

			/**
			 * Store variable string for each column and its data-type
			 */
			String variablesForeachColumn="";
			for(int i=0;i<columns.length;i++){
				columns[i] = stringManipulation.performOper(columns[i]);
				variablesForeachColumn+= typesAttrs(columns[i]);
			}
			
			/**
			 * store method string, for each column, to set values  
			 */
			String variableSetValueMethods="";
			for(int i=0;i<columns.length;i++){
				columns[i]= stringManipulation.performOper(columns[i]);
				variableSetValueMethods+= setValuesFunctions(columns[i]);
			}

			/**
			 * store method string, for each column, to get value
			 */
			String variableGetValueMethods="";
			for(int i=0;i<columns.length;i++){
				columns[i] = stringManipulation.performOper(columns[i]);
				variableGetValueMethods+= findReturnFunctions(columns[i]);
			}

			/**
			 * Append all the string variables into 'result' to make final script to generate
			 */
			result+= imports;
			result+="public class "+tableName+"DTO { \n";
			result+="\n\n\tpublic "+tableName+"DTO() {\n\t\n\t}\n";
			result+= variablesForeachColumn+"\n\n"+variableSetValueMethods+"\n\n"+variableGetValueMethods;

			String otherVariables = otherVariables();
			result+="\n\n"+otherVariables;
			/**
			 * @see com.sqltomongodb.additionals.BasicOperation
			 */
			String daoPath = stringManipulation.checkAndCreateDirectory(path,dbNames[k],"DTO");
			/**
			 * final path for the script along with the name.
			 */
			String fileName = daoPath+"\\"+tableName+"DTO.java";

			try
			{
			    FileWriter writer = new FileWriter(fileName);
			    writer.append(result);
				writer.append("}");
				writer.flush();
			    writer.close();
			}
			catch(IOException e)
			{
	     		e.printStackTrace();
			}
			
		}

	
		
	}
	
	
	public String otherVariables(){
		String result="";
		result+="\n\tprivate int count;";
		result+="\n\tprivate double max;";
		result+="\n\tprivate double min;";
		result+="\n\tprivate double avg;";
		result+="\n\tprivate double sum;";
		result+="\n\tprivate double sqrt;";
		result+="\n\tprivate String nullVaue=\"\";";

		result+="\n\tArrayList<String> concatValues = new ArrayList<String>();";
		result+="\n\tpublic void setcount(int count){";
		result+="\n\t\tthis.count = count;";
		result+="\n\t}";
		result+="\n\tpublic void setmax(double max){";
		result+="\n\t\tthis.max  =max;";
		result+="\n\t}";
		result+="\n\tpublic void setmin(double min){";
		result+="\n\t\tthis.min = min;";
		result+="\n\t}";
		result+="\n\tpublic void setavg(double avg){";
		result+="\n\t\tthis.avg = avg;";
		result+="\n\t}";
		result+="\n\tpublic void setsum(double sum){";
		result+="\n\t	this.sum = sum;";
		result+="\n\t}";
		result+="\n\tpublic void setsqrt(double sqrt){";
		result+="\n\t	this.sqrt = sqrt;";
		result+="\n\t}";
		
		result+="\n\tpublic void setconcatValues(String value){";
		result+="\n\t	this.concatValues.add(value);";
		result+="\n\t}";
		result+="\n\tpublic int getcount(){";
		result+="\n\t	return this.count;";
		result+="\n\t}";
		result+="\n\tpublic double getmax(){";
		result+="\n\t	return this.max;";
		result+="\n\t}";
		result+="\n\tpublic double getmin(){";
		result+="\n\t	return this.min;";
		result+="\n\t}";
		result+="\n\tpublic double getavg(){";
		result+="\n\t	return this.avg;";
		result+="\n\t}";
		result+="\n\tpublic double getsum(){";
		result+="\n\t	return this.sum;";
		result+="\n\t}";
		result+="\n\tpublic double getsqrt(){";
		result+="\n\t	return this.sqrt;";
		result+="\n\t}";
		result+="\n\tpublic ArrayList<String> getconcatValue(){";
		result+="\n\t	return this.concatValues;";
		result+="\n\t}";
		result+="\n\tpublic String getnullValue(){";
		result+="\n\t	return this.nullVaue;";
		result+="\n\t}";
		return result;
	}

}
