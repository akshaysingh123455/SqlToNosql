package com.sqltomongodb.essentials;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;





public class UnionQuery {
	
	public UnionQuery(){
		
	}
	
	
	public String getUnionClass(ArrayList<String[]> tableDescription,ArrayList<String[]> tableDataTypesUsed,String[] dataTypesUsed,String[] dataTypesMethods,String dbName){
		
		String result="";
		result+="package com.mysqltomongodb."+dbName+".DAO;\n\n\n";
		
		result+="\n\n\n\n";
		result+="\n/**";
		result+="\n	*"; 
		result+="\n * @author 		Akshay P. Singh <akshayps@iitrpr.ac.in>";
		result+="\n * @version 	1.0";
		result+="\n	*";
		result+="\n * This class is used to get values of Union of sql using mongodb ..";
		result+="\n * Only one method \"unionValues\" is for use by user. Other methods are helper methods";
		result+="\n */";
		result+="\n\n\n\n";

		result+="\nimport java.sql.Timestamp;";
		result+="\nimport java.text.ParseException;";
		result+="\nimport java.text.SimpleDateFormat;";
		result+="\nimport java.util.ArrayList;";
		result+="\nimport java.util.Date;";
		result+="\nimport java.util.Arrays;";
		result+="\nimport java.util.EmptyStackException;";
		result+="\nimport java.util.Stack;";
		result+="\nimport com.mysqltomongodb."+dbName+".DTO.*;";

		
		result+="\n\n\n";
		
		result+="\npublic class Union {\n";
		result+="\n\tpublic String[] tableNames={";
		for(int i=0;i<tableDescription.size();i++){
			String value=tableDescription.get(i)[0];
			if(i == (tableDescription.size()-1)){
				result+="\""+value+"\"";
			}else{
				result+="\""+value+"\",";
			}
		}
		result+="};";
		
		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method return the union of two or more queries";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method takes 3 inputs:-";
		result+="\n\t * 		a) Array of queries we need to make union of";
		result+="\n\t * 		b) host name of mongodb database";
		result+="\n\t * 		c) Port number of the same";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method identify the name of table of first query and provide result in the form of Arraylist of corresspoding DTO file of that table and append next table result in the same.";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t *"; 
		result+="\n\t * @param 		str[]		String array of select queries";
		result+="\n\t * @param 		host		host name";
		result+="\n\t * @param 		port		port number";
		result+="\n\t *"; 
		result+="\n\t * @return					ArrayList of first identified table";
		result+="\n\t */";
		result+="\n\tpublic ArrayList unionValues(String[] str,String host,int port){";
			
		result+="\n\t\tString tableName = this.getTableName(str[0]);";
		for(int i=0;i<tableDescription.size();i++){
			String value=tableDescription.get(i)[0];
			result+="\n\t\tif(tableName .equals(\""+value+"\")){";
				
			result+="\n\t\t\t"+value+"DAO dao = new "+value+"DAO();";
			result+="\n\t\t\tint errorCheckCounter=0;";
			result+="\n\t\t\tString[] values = dao.selectColumnNamesToReturned(str[0],host,port);";
			result+="\n\t\t\tString[] foundDataTypes = new String[values.length];";
			result+="\n\t\t\tString[] foundCols = new String[values.length];";
			result+="\n\t\t\tString[] colNames = dao.colNames;";
			result+="\n\t\t\tString[] dataTypes = dao.colTypes;";
			result+="\n\t\t\tfor(int i=0;i<values.length;i++){";
			result+="\n\t\t\t\tfor(int j=0;j<colNames.length;j++){";
			result+="\n\t\t\t\t\tif(dao.performOper(values[i]) .equals(colNames[j])){";
			result+="\n\t\t\t\t\t\t	foundDataTypes[i] = dataTypes[j];";
			result+="\n\t\t\t\t\t\tfoundCols[i] = colNames[j];";
			result+="\n\t\t\t\t\t\tbreak;";
			result+="\n\t\t\t\t\t}";
			result+="\n\t\t\t\t}";
			result+="\n\t\t\t}";


			result+="\n\t\t\tint numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0],host,port).length;";
			result+="\n\t\t\tfor(int i=1;i<str.length;i++){";
			result+="\n\t\t\t\tint tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i],host,port).length;";
			result+="\n\t\t\t\tif(tempNumberOfFieldsToReturn != numberFieldsToReturn){";
			result+="\n\t\t\t\t\terrorCheckCounter=1;";
			result+="\n\t\t\t\t}";
			result+="\n\t\t\t}";
			
			result+="\n\t\t\tif(errorCheckCounter ==1){";
			result+="\n\t\t\t\treturn null;";
			result+="\n\t\t\t}";
			result+="\n\t\t\tArrayList<"+value+"DTO> test = new ArrayList<"+value+"DTO>();";
			result+="\n\t\t\tArrayList<"+value+"DTO> value = dao.select(str[0],host,port);";
			result+="\n\t\t\tfor(int i=0;i<value.size();i++){";
			result+="\n\t\t\t\ttest.add(value.get(i));";
			result+="\n\t\t\t}";
			result+="\n\t\t\tfor(int k=1;k<str.length;k++){";
			result+="\n\t\t\t\tString[][] value1 = this.getValue(str[k],host,port);";
//				System.out.println(value1.length);
			result+="\n\t\t\t\tfor(int i=0;i<value1.length;i++){";
			result+="\n\t\t\t\t\t"+value+"DTO abc = new "+value+"DTO();";
			result+="\n\t\t\t\t\tfor(int j=0;j<value1[i].length;j++){";
			String[] colNames =tableDescription.get(i);
			for(int j=1;j<colNames.length;j++){
				int dataTypeIndex =0;
				for(int k=0;k<dataTypesUsed.length;k++){
					if(tableDataTypesUsed.get(i)[j-1] .equals(dataTypesUsed[k])){
						dataTypeIndex = k;
						break;
					}
				}
				result+="\n\t\t\t\t\t\tif(foundCols[j] .equals(\""+colNames[j] +"\")){";
				result+="\n\t\t\t\t\t\t\tabc.set"+colNames[j] +"("+dataTypesMethods[dataTypeIndex]+"((value1[i][j])));";
				result+="\n\t\t\t\t\t\t};";
			}

			result+="\n\t\t\t\t\t}";
			result+="\n\t\t\t\t\ttest.add(abc);";
					
			result+="\n\t\t\t\t}";
			result+="\n\t\t\t}";

			result+="\n\t\t\t\t\treturn test;";
			result+="\n\t\t\t\t}";
		}
		result+="\n\t\t\t\treturn null;";
		result+="\n\t\t\t}";

		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method identify table name and create object accordingly and provide result in 2-D array.";
		result+="\n\t * This method is used from second query"; 
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 		str			select query";
		result+="\n\t * @param 		host		host name";
		result+="\n\t * @param 		port		port number";
		result+="\n\t *"; 
		result+="\n\t * @return					2-D array string result";
		result+="\n\t */";
		result+="\n\t\tpublic String[][] getValue(String str,String host,int port){";
		result+="\n\t\t	String tableName = this.getTableName(str);";
		for(int i=0;i<tableDescription.size();i++){
			String value=tableDescription.get(i)[0];
			result+="\n\t\t\tif(tableName .equals(\""+value+"\")){";
			result+="\n\t\t\t\t"+value+"DAO dao = new "+value+"DAO();";
			result+="\n\t\t\t\tString[][] value = dao.selectAsWholeArray(str,host,port);";
				
			result+="\n\t\t\t\treturn value;";
			result+="\n\t\t\t}";
			
		}
		result+="\n\t\t\treturn null;";
		result+="\n\t\t}";
		
		
		result+="\n\n\n\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method is used to identify table name in a query";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 		str			sql select query";
		result+="\n\t * @return		tableName	identified table name";
		result+="\n\t */";
		result+="\n\t\tpublic String getTableName(String str){";
		result+="\n\t\t\tint[] subQueryCheck = this.getSubQueryConfirmation(str);";
			
		result+="\n\t\t\tint fromIndex=-1;";
		result+="\n\t\t\tint tempFromIndex = str.toUpperCase().indexOf(\"FROM \");";
		result+="\n\t\t\twhile(tempFromIndex >=0){";
		result+="\n\t\t\t\tif(subQueryCheck[tempFromIndex] ==0){";
		result+="\n\t\t\t\t\tfromIndex = tempFromIndex;";
		result+="\n\t\t\t\t\tbreak;";
		result+="\n\t\t\t\t}";
		result+="\n\t\t\t\ttempFromIndex = str.toUpperCase().indexOf(\"FROM \", tempFromIndex+1);";
		result+="\n\t\t\t}";
		result+="\n\t\t\tString[] tokens = str.substring(fromIndex).split(\" \", 4);";
		result+="\n\t\t\tString tableName = tokens[1];";
		result+="\n\t\t\tif(tableName .contains(\".\")){";
		result+="\n\t\t\t\ttableName = tableName.split(\".\")[1];";
		result+="\n\t\t\t}";
		result+="\n\t\t\treturn tableName;";
		result+="\n\t\t}";
		result+="\n\n\n\n";
		

		
		return result;
		
	}
	
	
	public void saveUnionFile(String path,String value){
		try
		{
		    FileWriter writer = new FileWriter(path+"\\Union.java");
			writer.append(value);
			writer.append("\n}");
			writer.flush();
		   	writer.close();
		}
		catch(IOException e)
		{
     		e.printStackTrace();
		}
		
	}

}
