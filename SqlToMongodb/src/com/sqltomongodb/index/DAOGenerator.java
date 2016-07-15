package com.sqltomongodb.index;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.TreeSet;

import com.sqltomongodb.additionals.BasicOperation;
import com.sqltomongodb.additionals.SqlParser;
import com.sqltomongodb.additionals.ImportantVariables;
import com.sqltomongodb.essentials.InsertQuery;
import com.sqltomongodb.essentials.SelectQuery;
import com.sqltomongodb.essentials.UnionQuery;
import com.sqltomongodb.essentials.WhereQuery;
import com.sqltomongodb.essentials.DeleteQuery;
import com.sqltomongodb.essentials.UpdateQuery;
import com.sqltomongodb.essentials.LimitOffsetQuery;
import com.sqltomongodb.essentials.SortQuery;

/**
 * 
 * @author 		Akshay P. Singh <akshayps@iitrpr.ac.in>
 * @version 	1.0
 *
 */


public class DAOGenerator {
	
	public DAOGenerator(){
		
	}
	
	
	/**
	 * identify column names in given sql query
	 * 
	 * @param 	in	input statement for new column e.g, "film_id SMALLINT UNSIGNED NOT NULL"
	 * @return		identified name for column
	 */
	public String findColNames(String in){
		
		/**
		 * check whether input string contain column information or just stating "KEY" information"
		 */
		if(checkSqlKeys(in)){
			return "";
		}
		
        /**
         * Object for doing basic operations on input string
         * @see com.sqltomongodb.additionals.BasicOperation
         */
		BasicOperation stringManipulation30 = new BasicOperation();
		in=stringManipulation30.performOper(in);

		/**
		 * creating tokens of input string
		 */
		String[] colValues = in.split(" ");
		colValues[0]=stringManipulation30.performOper(colValues[0]);
		return colValues[0];
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
	 * find primary key substring if present in input string
	 * 
	 * @param 	in	input string
	 * @return	substring with primary key 
	 */
	public String findPrimaryKey(String in){
		if((in.indexOf("PRIMARY KEY ")<5 && in.indexOf("PRIMARY KEY ")!= (-1))){
			String str=in.substring(in.indexOf("(")+1,in.lastIndexOf(")"));
			return str;
		}
		return null;	
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
		result+= findType(tokens[1]);
		return result;
		
	}
	
	
	
	public String[] removeDuplicates(String arr[]){
		String[] array = new HashSet<String>(Arrays.asList(arr)).toArray(new String[0]);
		return array;

	}
	
	
	/**
	 * Generate DAO file
	 * 
	 * <p>
	 * This method generate DAO file for each "Create table" statement provided.
	 * It reads sql "Create table" and extract columns and their corresponding datatypes and 
	 * create Java method corresponding to each type of sql queries and their handlers and name it as  as "TABLE NAME"_DAO.java
	 * and save it in required location.
	 * <p>
	 * 
	 * @param str[]		array of create table query for each table
	 * @param path		Location where the generated files need to be saved.
	 */
	public void generateDAO(String str[],String path,String[] dbNames) throws IOException{
		Properties prop1 = new Properties();
		InputStream input1 = null;
		try	{
			/**
			 * reading config file
			 */
			input1 = getClass().getClassLoader().getResourceAsStream("config/config.properties");
      		prop1.load(input1);
      		
      		/**
      		 * Mongo db comparison operators,java data-type available,java-datatypes used and corresponding methods
      		 */
      		String[] mongodbMysqlComparators = prop1.getProperty("MongodbMysqlComparators").split(",");
      		int sizeComparators = mongodbMysqlComparators.length;
      		String[] mongodbComparators = new String[sizeComparators];
      		for(int i=0;i<sizeComparators;i++){
      			mongodbComparators[i] = mongodbMysqlComparators[i].split(":")[0];
      		}
      		String[] dataTypes=prop1.getProperty("JavaDataTypes").split(",");
      		String[] dataTypesUsed = prop1.getProperty("dataTypesUsed").split(",");
      		String[] dataTypesMethods = prop1.getProperty("dataTypesMethods").split(",");
      		String fileName1="resources\\EssentialMethods.txt";
			
      		/**
      		 * string to store essential methods and temp line
      		 */
			String essentialMethods="",line1=null;
			try{
				FileReader fileReader1 = new FileReader(fileName1);
				BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
				while((line1 = bufferedReader1.readLine()) != null) {
					essentialMethods +=line1+"\n";
				}
				bufferedReader1.close();         
			}
			catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName1 + "'");                
			}
			catch(IOException ex) {
            System.out.println("Error reading file '" + fileName1 + "'");                  
			}
			
			/**
	         * Object for doing basic operations on input string
	         * @see com.sqltomongodb.additionals.BasicOperation
	         */
			BasicOperation stringManipulation = new BasicOperation();
			
	        /**
	         * Object for parsing sql queries from input provided.
	         * @see com.sqltomongodb.additionals.SqlParser
	         */
			SqlParser par = new SqlParser();
			
			/**
			 * 'jj' counter for each "Create table" query
			 */
			
			ArrayList<String[]> tablesDescription = new ArrayList<String[]>();
			ArrayList<String[]> tableDataTypesUsed = new ArrayList<String[]>();
			
			for(int jj=0;jj<str.length;jj++){
				
				
				str[jj] =str[jj].replace(" IF NOT EXISTS "," ");
				str[jj] =str[jj].replace(" if not exists "," ");
				
				/**
				 * final string to save
				 */
				String result="";
				

				
				/**
				 * each table query
				 */
				String tempStatement = str[jj];
				String[] token = tempStatement.split(" ",4);
				String tableName = token[2];
				
				/**
				 * @see com.sqltomongodb.additionals.BasicOperation
				 */
				tableName = stringManipulation.performOper(tableName);
				
				/**
				 * getting imports for DAO file
				 * @see com.sqltomongodb.additionals.SqlParser
				 */
				String requiredImports = par.getImports(1,dbNames[jj],tableName);
				result+= requiredImports;
				
				
				/**
				 * geting columns from query
				 * @see com.sqltomongodb.additionals.SqlParser
				 */
				String[] columns = par.extractingColumns(tempStatement);
				
				/**
				 * extracting proper column statements
				 * removing key statements
				 */
				String colTy="";
				for(int i=0;i<columns.length;i++){
					colTy+=typesAttrs(columns[i]);
					if(i==columns.length-1){
						
					}else{
						colTy+=",";
					}
				}
				String[] colTypes = colTy.split(",");
				tableDataTypesUsed.add(colTypes);
				String coll="";
				for(int i=0;i<columns.length;i++){
					coll+=findColNames(columns[i]);
					if(i==columns.length-1){
						
					}else{
						coll+=",";
					}
				}
				
				/**
				 * final columns list
				 */
				String[] ColNames = coll.split(",");
				int k=0;
				
				
				String[] tableInfo = new String[ColNames.length+1];
				tableInfo[0]= tableName;
				for(int i=0;i<ColNames.length;i++){
					tableInfo[i+1] = ColNames[i];
				}
				tablesDescription.add(tableInfo);
				
				/**
				 * primary key identification
				 */
				String primaryKeyName="";
				while(k<columns.length){
					String test = findPrimaryKey(columns[k]);
					if(test != null){
						primaryKeyName=stringManipulation.performOper(test);
						break;
					}
					k++;
				}
				
				/**
				 * primary key index identification
				 */
				int primaryKeyIndex=0;
				for(int i=0;i<ColNames.length;i++){
					if(ColNames[i] .equals(primaryKeyName)){
						primaryKeyIndex =i;
						break;
					}
				}
				/**
				 * object to get list of important variables
				 * @see com.sqltomongodb.additionals.ImportantVariables
				 */
				ImportantVariables var = new ImportantVariables();
				String publicVariables = var.generatePublicVariables(tableName,ColNames,colTypes,primaryKeyIndex,dbNames[jj]);
				result+="\n\n\npublic class "+tableName+"DAO { \n";
				
				/**
				 * appending public variables in result
				 */
				result+= publicVariables;
				result+="\n\n\tpublic "+tableName+"DAO() {\n\t\n\t}";
				
				/**
				 * object to handle insert methods
				 * @see com.sqltomongodb.essentials.InsertQuery
				 */
				InsertQuery in = new InsertQuery();
				String insertMethod= in.getInsertQuery();
				result+=insertMethod;
				
			
				
				
				/**
				 * object to handle select methods
				 * @see com.sqltomongodb.essentials.SelectQuery
				 */
				
				
				
				SelectQuery sel = new SelectQuery();
				String selectMethod = sel.getSelectQuery(tableName,colTypes,ColNames);
				result+= selectMethod;	
				/**
				 * handle method to create GAE insert query
				 * @see com.sqltomongodb.essentials.InsertQuery
				 */
				
				
				String selectAsWholeArray = sel.getSelectAsWholeArrayQuery(tableName, colTypes, ColNames);
				result+=selectAsWholeArray;
				
				
				String selectAsArrayQuery = sel.selectAsArrayQueryGenerator(tableName, colTypes, ColNames);
				result+=selectAsArrayQuery;
				
				String selectSingle = sel.selectSingleValueQueryGenerator();
				result+=selectSingle;
				
				String singleValueOfMethods = sel.singleValueOfMethodsQueryGenerator(tableName, colTypes, ColNames);
				result+=singleValueOfMethods;
				
				String getSetTempTableName= sel.getSetTempTableNameMethod();
				result+=getSetTempTableName;
				
				
				
				/**
				 * object to handle delete methods
				 * @see com.sqltomongodb.essentials.DeleteQuery
				 */
				DeleteQuery del = new DeleteQuery();
				String deleteMethod = del.getDeleteMethod();
				result+= deleteMethod;
			
				
				/**
				 * object to handle update methods
				 * @see com.sqltomongodb.essentials.UpdateQuery
				 */
				UpdateQuery up = new UpdateQuery();
				String updateMethod = up.getUpdateMethod(tableName,ColNames);
				result+= updateMethod; 
				
				
				
				String insert1Method= in.getInsertQueryGenerator(tableName,primaryKeyIndex); 
				result+= insert1Method;
				
				
				LimitOffsetQuery loq = new LimitOffsetQuery();
				String limitOffsetMethod = loq.getLimitOffsetMethod();
				result+=limitOffsetMethod;
				
				
				/**
				 * create method to handle GAE update set conditions
				 */
				String updateSetQueryMethod = up.getUpdateSetQuery(tableName,dataTypes,dataTypesMethods);
				result+= updateSetQueryMethod;
				
				
				/**
				 * create method to handle sort conditions
				 * @see com.sqltonosql.essentials.SortQuery
				 */
				SortQuery sort = new SortQuery();
				String sortMethod = sort.getSortMethod();
				result+=sortMethod;
				
				
				/**
				 * create method to handle where conditions
				 * @see com.sqltomongodb.essentials.WhereQuery
				 */
				WhereQuery where = new WhereQuery();
				String whereMethod = where.getWhereQuery(dataTypes,dataTypesUsed,dataTypesMethods,mongodbComparators);
				result+=whereMethod;
				
				
				String getValueMethod = where.getValueMethodGenerator(tableName);
				String otherWhereSideMethods = where.getInClauseValueAndOtherMethods();
				
				result+="\n\n"+getValueMethod;
				result+="\n\n"+otherWhereSideMethods;
				
				
				result+="\n\n"+essentialMethods;
				result+="\n\n\n";
				
				/**
				 * @see com.sqltomongodb.additionals.BasicOperation
				 */
				String daoPath = stringManipulation.checkAndCreateDirectory(path,dbNames[jj],"DAO");
				/**
				 * final path for the script along with the name.
				 */
				String fileName = daoPath+"\\"+tableName+"DAO.java";
				
				try
				{
				    FileWriter writer = new FileWriter(fileName);
					writer.append(result);
					writer.append("\n}");
					writer.flush();
				   	writer.close();
				}
				catch(IOException e)
				{
		     		e.printStackTrace();
				}
				
			}
			
			UnionQuery union = new UnionQuery();
			String[] dbNamesRemoved = this.removeDuplicates(dbNames);
			for(int i=0;i<dbNamesRemoved.length;i++){
				String unionValues = union.getUnionClass(tablesDescription, tableDataTypesUsed, dataTypesUsed, dataTypesMethods,dbNames[i]);
				unionValues+=essentialMethods;
				
				union.saveUnionFile(path+"\\com\\mysqltomongodb\\"+dbNamesRemoved[i]+"\\DAO", unionValues);
			}

			
	   	} finally {
	   		if (input1 != null) {
	   			try {
	   				input1.close();
	   			} catch (IOException e) {
	   				e.printStackTrace();
         	}
      	}
   	}
		
	}
	

}
