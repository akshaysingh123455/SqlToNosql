Team Members :

Akshay Prasad Singh (2012csb1005)
Jaya Bharath (2012csb1025)



Including set:

-> Readme file
-> "SqlToMongoDb" project of Eclipse [for mongodb]
-> "SqlToNosql" project of Eclipse [for Google GAE]
-> Java driver for mongodb
-> Test Databases : Sakila,Lahman,world,india
-> Report
-> Test Projects : gaedatastore->To test generated scripts for GAE
				 : mongodbtest-> To test generated scripts for Mongo db.

[Note: test projects are working in developer's system. If there is some error in these projects possible reason is mission required plugins/ external drivers]




Schema translation and bridge from SQL to NoSQL
-----------------------------------------------

The whole project is in two different parts( two separate Java Projects )


Working on MongoDb ( Project Name:: MysqlToMongodb)
===================
[Developed for Mongodb server 3.2]


System should have Eclipse Installed.

Java Plugin installed in eclipse.
[Provided java driver to be used in the project]

For MongoDb MongoDB database should be installed should be installed.

1.Start MongoDbserver, From the Program Files where it is installed (i.e you need to run the Mongod from the folder).
2.Start client from the folder where MongoDb is installed (i.e you need to run Mongo from the folder).
3. when the server is ready and client is connected to it, Open The SqltoNoSql.java file in eclipse .
4.Run the file.
5.The code will ask for 2 inputs: path of sql file which contains DML (Create table statements) and path to save created files.After that follow the inputs asked in program.

6. Import the Generated files to the project location you are working on.
7. For a table user is required to make object of corresponding DAO file and call methods with proper inputs 
8. For union user is required to make object for "Union.java" and call the required method. Union.java is unique for each database


Note: Select query and union will provide result in form of ArrayList of DTO object of corresponding tables
Note: Each generated file will give information which method is to be used by user.

5 Types are supported 
1. SELECT 2. DELETE 3. INSERT 4. UPDATE 5. UNION

Example Queries
1. SELECT lastname, MIN(actorid), COUNT(firstname) from actor where actorid<100 GROUP BY lastname
2. SELECT name FROM category WHERE categoryid<10
3. SELECT address_id FROM address
4. SELECT * from student









Working on GoogleDataStore ( Project Name:: MysqlToNosql)
==========================

System should have Eclipse Installed.

Java Plugin installed in eclipse.

For Google DataStore user need to install GoogleAppEngine Plugin.

1. Open Eclipse and start SqltoNoSql.java.
2. Import the generated files to the project location where we are working on
3. The code will ask for 2 inputs: path of sql file which contains DML (Create table statements) and path to save created files.After that follow the inputs asked in program.
4. From here you need to write the respective queries basing on the data you want to access.
5. Import the Generated files to the project location you are working on.
6. For a table user is required to make object of corresponding DAO file and call methods with proper inputs 


4 Types are supported
1. SELECT 2. DELETE 3. INSERT 4. UPDATE 

Note: Select query  will provide result in form of ArrayList of DTO object of corresponding tables
Note: Each generated file will give information which method is to be used by user.
Note:UNION is not supported for GoogleDataStore.

Example Queries
1. SELECT firstname,lastname from actor where actorid<100 
2. SELECT name FROM category WHERE categoryid<10
3. SELECT address FROM address
