package com.sqltomongodb.essentials;

public class LimitOffsetQuery {
	
	public String getLimitOffsetMethod(){
		String result ="";
		
		result+="\n\t/**";
		result+="\n\t * <p>";
		result+="\n\t * This method read input sql query for select and identify \"LIMIT\" and \"OFFSET\" value";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * <p>";
		result+="\n\t * This method will take 3 inputs";
		result+="\n\t * 	a) select query in MySQL format with given specifications";
		result+="\n\t * 	b) host name to connect;";
		result+="\n\t * 	c) port number to connect";
		result+="\n\t * <p>";
		result+="\n\t *"; 
		result+="\n\t * @param 		str		input select query";
		result+="\n\t * @param		host	host name";
		result+="\n\t * @param		port	port number";
		result+="\n\t *"; 
		result+="\n\t *"; 	
		result+="\n\t * @return				return values of limit and offset separated by \":\"";
		result+="\n\t */";
		result+="\n\tpublic String limitOffset(String str,String host,int port){";
		result+="\n\t\tint indexLimit = 0;";
		result+="\n\t\tint indexOffset = 0;";
		result+="\n\t\tString[] tokens = str.split(\" \");";
		result+="\n\t\tfor(int i=0;i<tokens.length;i++){";
		result+="\n\t\t	if(tokens[i] .equals(\"LIMIT\")){";
		result+="\n\t\tindexLimit = i;";
		result+="\n\t\t}";
				
		result+="\n\t\tif(tokens[i] .equals(\"OFFSET\")){";
		result+="\n\t\tindexOffset = i;";
		result+="\n\t\t	}";
		result+="\n\t\t	}";

		result+="\n\t\tif(indexLimit >0 && indexOffset >0){";
		result+="\n\t\treturn tokens[indexLimit+1]+\":\"+tokens[indexOffset+1];";
		result+="\n\t\t}";
		result+="\n\t\tif(indexLimit >0){";
		result+="\n\t\treturn tokens[indexLimit+1]+\":0\";";
		result+="\n\t\t	}";
		result+="\n\t\tif(indexOffset >0){";
		result+="\n\t\treturn \"0:\"+tokens[indexOffset+1];";
		result+="\n\t\t}";
		result+="\n\t\t	return \"1000000:0\";";
			
		result+="\n\t\t}";
		
		return result;
	}

}
