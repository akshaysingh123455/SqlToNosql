package com.sqltomongodb.index;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import com.sqltomongodb.additionals.*;

/**
 * 
 * @author 		Akshay P. Singh <akshayps@iitrpr.ac.in>
 * @version 	1.0
 *
 */

public class SqlToMongodb{

	/**
	 * reads provided sql file and generate corresponding Java source code.
	 * 
	 * <p>
	 * This method will take two inputs.,
	 * 		a)Required sql file location.
	 * 		b)Location/path where user want to save generated files.
	 * <p>
	 * 
	 * <p>
	 * Corresponding to each table structure(DDL), provided from input file, two Java classes will be generated
	 * 		a)DAO class:- Data Access Object (structure of DML queries corresponding of that of SQL)
	 * 		b)DTO class:- Data Transfer Object ( structure of corresponding table)
	 * <p> 
	 * 
	 * 
	 *@param FilePath location of input of sql file
	 *@param SavePath location of the path to save generated files
	 */
	public static void main(String[] args) {
		String filePath = "C:\\Users\\aaryan\\Desktop\\sakila.sql";
		String savePath ="C:\\Users\\aaryan\\Desktop\\test\\";
		//String FilePath = args[0];
		// String SavePath = args[1];
		//String filePath="",savePath="";
       	if(filePath == null || filePath.isEmpty() || filePath == " "){
       		Scanner in = new Scanner(System.in);
       		System.out.println("Please enter the path of input sql file");
       		filePath = in.nextLine();
       		System.out.println("Please enter the path to save generating files");
       		savePath = in.nextLine();
       	}
       	
        String line = null;
        /**
         * Object for DTO generator
         * @see com.sqltomongodb.index.DTOGenerator 
         */
        DTOGenerator dtoGen = new DTOGenerator();
        
        /**
         * Object for DAO generator
         * @see com.sqltomongodb.index.DAOGenerator
         */
        DAOGenerator daoGen = new DAOGenerator();
        
        /**
         * Object for parsing sql queries from input provided.
         * @see com.sqltomongodb.additionals.SqlParser
         */
        
        ImportantVariables var = new ImportantVariables();
        SqlParser par = new SqlParser();
        
        try {
        	FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String fileData="";
            while((line = bufferedReader.readLine()) != null) {
                fileData+= line;
            }
			
            /**
             *query for every table in input file
             */
            String[] tableQueries = par.extractCreateTableQuery(fileData);
            
            
            String[] tables = var.getTableNames(tableQueries);
            String[] dbNames = new String[tables.length];
       		Scanner in = new Scanner(System.in);
       		System.out.println("List of table names identified:-");
            for(int i=0;i<tables.length;i++){
            	try {
            	    Thread.sleep(100);                 
            	} catch(InterruptedException ex) {
            	    Thread.currentThread().interrupt();
            	}
            	System.out.println(tables[i]+" ");
            }	
            System.out.println("These tables belong to same database/ you want them in same database in mongodb?Answer with 'yes' or 'no'");
            String check = in.nextLine();
            if(check .equals("yes")){
                System.out.println("Provide database name: \n");
                String db = in.nextLine();
                for(int i=0;i<tables.length;i++){
                	dbNames[i] = db;
                }
            }else{
                System.out.println("Provide database name for each Corresponding Table>\n");
                for(int i=0;i<tables.length;i++){
                	System.out.print(tables[i]+": ");
                	dbNames[i] = in.nextLine();
                }
            }

            
            /**
             * @see com.sqltomongodb.index.DTOGenerator
             */
            dtoGen.createModel(tableQueries,savePath,dbNames);
            
            /**
             * @see com.sqltomongodb.index.DAOGenerator
             */
            daoGen.generateDAO(tableQueries,savePath,dbNames);
            bufferedReader.close();         
        }catch(FileNotFoundException ex) {
            System.out.println(
                 "Unable to open file '" + 
                filePath + "'");                
        }catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filePath + "'");                  

        }
        


	}
}