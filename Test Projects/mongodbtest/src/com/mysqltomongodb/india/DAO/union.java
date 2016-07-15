package com.mysqltomongodb.india.DAO;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;
import com.mysqltomongodb.india.DTO.*;



public class union {

	public String[] tableNames={"articles","article_folder_link","article_tags","category_main_index","folder_details","folder_followers","languages","languages_index","social_links_index","social_link_type","subcategory_index","users","website_index","website_index_followers"};
	public ArrayList unionValues(String[] str){
		String tableName = this.getTableName(str[0]);
		if(tableName .equals("articles")){
			articlesDAO dao = new articlesDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<articlesDTO> test = new ArrayList<articlesDTO>();
			ArrayList<articlesDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					articlesDTO abc = new articlesDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("article_id")){
							abc.setarticle_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("name")){
							abc.setname(((value1[i][j])));
						};
						if(foundCols[j] .equals("link")){
							abc.setlink(((value1[i][j])));
						};
						if(foundCols[j] .equals("content")){
							abc.setcontent(((value1[i][j])));
						};
						if(foundCols[j] .equals("thumbnail")){
							abc.setthumbnail(((value1[i][j])));
						};
						if(foundCols[j] .equals("big_image")){
							abc.setbig_image(((value1[i][j])));
						};
						if(foundCols[j] .equals("parent_website_id")){
							abc.setparent_website_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("add_date")){
							abc.setadd_date(StringToTimestamp((value1[i][j])));
						};
						if(foundCols[j] .equals("num_views")){
							abc.setnum_views(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("num_ups")){
							abc.setnum_ups(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("num_downs")){
							abc.setnum_downs(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("num_saved")){
							abc.setnum_saved(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("num_comments")){
							abc.setnum_comments(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("one_page_article")){
							abc.setone_page_article(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("primary_link")){
							abc.setprimary_link(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("tag_id")){
							abc.settag_id(Integer.parseInt((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("article_folder_link")){
			article_folder_linkDAO dao = new article_folder_linkDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<article_folder_linkDTO> test = new ArrayList<article_folder_linkDTO>();
			ArrayList<article_folder_linkDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					article_folder_linkDTO abc = new article_folder_linkDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("article_id")){
							abc.setarticle_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("folder_id")){
							abc.setfolder_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("description")){
							abc.setdescription(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("article_tags")){
			article_tagsDAO dao = new article_tagsDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<article_tagsDTO> test = new ArrayList<article_tagsDTO>();
			ArrayList<article_tagsDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					article_tagsDTO abc = new article_tagsDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("tag_id")){
							abc.settag_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("name")){
							abc.setname(((value1[i][j])));
						};
						if(foundCols[j] .equals("num_followers")){
							abc.setnum_followers(Integer.parseInt((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("category_main_index")){
			category_main_indexDAO dao = new category_main_indexDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<category_main_indexDTO> test = new ArrayList<category_main_indexDTO>();
			ArrayList<category_main_indexDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					category_main_indexDTO abc = new category_main_indexDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("category_main_index_id")){
							abc.setcategory_main_index_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("category_index_name")){
							abc.setcategory_index_name(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("folder_details")){
			folder_detailsDAO dao = new folder_detailsDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<folder_detailsDTO> test = new ArrayList<folder_detailsDTO>();
			ArrayList<folder_detailsDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					folder_detailsDTO abc = new folder_detailsDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("folder_id")){
							abc.setfolder_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("folder_name")){
							abc.setfolder_name(((value1[i][j])));
						};
						if(foundCols[j] .equals("owner_id")){
							abc.setowner_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("parent_folder_id")){
							abc.setparent_folder_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("num_articles")){
							abc.setnum_articles(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("num_followers")){
							abc.setnum_followers(Integer.parseInt((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("folder_followers")){
			folder_followersDAO dao = new folder_followersDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<folder_followersDTO> test = new ArrayList<folder_followersDTO>();
			ArrayList<folder_followersDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					folder_followersDTO abc = new folder_followersDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("folder_id")){
							abc.setfolder_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("user_id")){
							abc.setuser_id(Integer.parseInt((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("languages")){
			languagesDAO dao = new languagesDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<languagesDTO> test = new ArrayList<languagesDTO>();
			ArrayList<languagesDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					languagesDTO abc = new languagesDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("name")){
							abc.setname(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("languages_index")){
			languages_indexDAO dao = new languages_indexDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<languages_indexDTO> test = new ArrayList<languages_indexDTO>();
			ArrayList<languages_indexDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					languages_indexDTO abc = new languages_indexDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("website_id")){
							abc.setwebsite_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("language")){
							abc.setlanguage(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("social_links_index")){
			social_links_indexDAO dao = new social_links_indexDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<social_links_indexDTO> test = new ArrayList<social_links_indexDTO>();
			ArrayList<social_links_indexDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					social_links_indexDTO abc = new social_links_indexDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("site_id")){
							abc.setsite_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("social_link_type_id")){
							abc.setsocial_link_type_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("link")){
							abc.setlink(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("social_link_type")){
			social_link_typeDAO dao = new social_link_typeDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<social_link_typeDTO> test = new ArrayList<social_link_typeDTO>();
			ArrayList<social_link_typeDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					social_link_typeDTO abc = new social_link_typeDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("social_link_type_id")){
							abc.setsocial_link_type_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("link_name")){
							abc.setlink_name(((value1[i][j])));
						};
						if(foundCols[j] .equals("link")){
							abc.setlink(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("subcategory_index")){
			subcategory_indexDAO dao = new subcategory_indexDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<subcategory_indexDTO> test = new ArrayList<subcategory_indexDTO>();
			ArrayList<subcategory_indexDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					subcategory_indexDTO abc = new subcategory_indexDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("subcategory_index_id")){
							abc.setsubcategory_index_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("category_id")){
							abc.setcategory_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("subcategory_name")){
							abc.setsubcategory_name(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("users")){
			usersDAO dao = new usersDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<usersDTO> test = new ArrayList<usersDTO>();
			ArrayList<usersDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					usersDTO abc = new usersDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("user_id")){
							abc.setuser_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("name")){
							abc.setname(((value1[i][j])));
						};
						if(foundCols[j] .equals("username")){
							abc.setusername(((value1[i][j])));
						};
						if(foundCols[j] .equals("email")){
							abc.setemail(((value1[i][j])));
						};
						if(foundCols[j] .equals("password")){
							abc.setpassword(((value1[i][j])));
						};
						if(foundCols[j] .equals("date")){
							abc.setdate(StringToTimestamp((value1[i][j])));
						};
						if(foundCols[j] .equals("ip")){
							abc.setip(((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("website_index")){
			website_indexDAO dao = new website_indexDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<website_indexDTO> test = new ArrayList<website_indexDTO>();
			ArrayList<website_indexDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					website_indexDTO abc = new website_indexDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("site_id")){
							abc.setsite_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("name")){
							abc.setname(((value1[i][j])));
						};
						if(foundCols[j] .equals("title")){
							abc.settitle(((value1[i][j])));
						};
						if(foundCols[j] .equals("link")){
							abc.setlink(((value1[i][j])));
						};
						if(foundCols[j] .equals("description")){
							abc.setdescription(((value1[i][j])));
						};
						if(foundCols[j] .equals("site_logo")){
							abc.setsite_logo(((value1[i][j])));
						};
						if(foundCols[j] .equals("category_main_index_id")){
							abc.setcategory_main_index_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("subcategory_index_id")){
							abc.setsubcategory_index_id(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("add_date")){
							abc.setadd_date(StringToTimestamp((value1[i][j])));
						};
						if(foundCols[j] .equals("num_ups")){
							abc.setnum_ups(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("num_downs")){
							abc.setnum_downs(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("views")){
							abc.setviews(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("no_articles_registered")){
							abc.setno_articles_registered(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("made_in_india")){
							abc.setmade_in_india(Integer.parseInt((value1[i][j])));
						};
						if(foundCols[j] .equals("num_followers")){
							abc.setnum_followers(Integer.parseInt((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
		if(tableName .equals("website_index_followers")){
			website_index_followersDAO dao = new website_index_followersDAO();
			int errorCheckCounter=0;
			String[] values = dao.selectColumnNamesToReturned(str[0]);
			String[] foundDataTypes = new String[values.length];
			String[] foundCols = new String[values.length];
			String[] colNames = dao.colNames;
			String[] dataTypes = dao.colTypes;
			for(int i=0;i<values.length;i++){
				for(int j=0;j<colNames.length;j++){
					if(dao.performOper(values[i]) .equals(colNames[j])){
							foundDataTypes[i] = dataTypes[j];
						foundCols[i] = colNames[j];
						break;
					}
				}
			}
			int numberFieldsToReturn = dao.selectColumnNamesToReturned(str[0]).length;
			for(int i=1;i<str.length;i++){
				int tempNumberOfFieldsToReturn = dao.selectColumnNamesToReturned(str[i]).length;
				if(tempNumberOfFieldsToReturn != numberFieldsToReturn){
					errorCheckCounter=1;
				}
			}
			if(errorCheckCounter ==1){
				return null;
			}
			ArrayList<website_index_followersDTO> test = new ArrayList<website_index_followersDTO>();
			ArrayList<website_index_followersDTO> value = dao.select(str[0]);
			for(int i=0;i<value.size();i++){
				test.add(value.get(i));
			}
			for(int k=1;k<str.length;k++){
				String[][] value1 = this.getValue(str[k]);
				for(int i=0;i<value1.length;i++){
					website_index_followersDTO abc = new website_index_followersDTO();
					for(int j=0;j<value1[i].length;j++){
						if(foundCols[j] .equals("website_index_followers_id")){
							abc.setwebsite_index_followers_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("website_id")){
							abc.setwebsite_id(Long.parseLong((value1[i][j])));
						};
						if(foundCols[j] .equals("user_id")){
							abc.setuser_id(Integer.parseInt((value1[i][j])));
						};
					}
					test.add(abc);
				}
			}
					return test;
				}
				return null;
			}
		public String[][] getValue(String str){
			String tableName = this.getTableName(str);
			if(tableName .equals("articles")){
				articlesDAO dao = new articlesDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("article_folder_link")){
				article_folder_linkDAO dao = new article_folder_linkDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("article_tags")){
				article_tagsDAO dao = new article_tagsDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("category_main_index")){
				category_main_indexDAO dao = new category_main_indexDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("folder_details")){
				folder_detailsDAO dao = new folder_detailsDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("folder_followers")){
				folder_followersDAO dao = new folder_followersDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("languages")){
				languagesDAO dao = new languagesDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("languages_index")){
				languages_indexDAO dao = new languages_indexDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("social_links_index")){
				social_links_indexDAO dao = new social_links_indexDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("social_link_type")){
				social_link_typeDAO dao = new social_link_typeDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("subcategory_index")){
				subcategory_indexDAO dao = new subcategory_indexDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("users")){
				usersDAO dao = new usersDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("website_index")){
				website_indexDAO dao = new website_indexDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			if(tableName .equals("website_index_followers")){
				website_index_followersDAO dao = new website_index_followersDAO();
				String[][] value = dao.selectAsWholeArray(str);
				return value;
			}
			return null;
		}
		public String getTableName(String str){
			int[] subQueryCheck = this.getSubQueryConfirmation(str);
			int fromIndex=-1;
			int tempFromIndex = str.indexOf("FROM ");
			while(tempFromIndex >=0){
				if(subQueryCheck[tempFromIndex] ==0){
					fromIndex = tempFromIndex;
					break;
				}
				tempFromIndex = str.indexOf("FROM ", tempFromIndex+1);
			}
			String[] tokens = str.substring(fromIndex).split(" ", 4);
			String tableName = tokens[1];
			if(tableName .contains(".")){
				tableName = tableName.split(".")[1];
			}
			return tableName;
		}
	public String performOper(String str){
		if(str ==""){
			return str;
		}
		if(str.charAt(0) == '(' || str.charAt(0) =='[' ||str.charAt(0) == '{'){
			str=str.substring(1);
		}
		if(str.charAt(str.length()-1) == ')' || str.charAt(str.length()-1) == ']' ||str.charAt(str.length()-1) == '}'){
			str = str.substring(0,str.length()-1);
		}
		while(str.charAt(0) ==' '){
							str = str.substring(1);
		}
		while(str.charAt(str.length()-1) == ' '){
			str = str.substring(0,str.length()-1);
		}
		if(str ==""){
			return str;
		}
		
		while(str.charAt(0) =='\''||str.charAt(0) =='\t' || str.charAt(0) =='"' ||str.charAt(0) =='`' || str.charAt(0) =='(' ||str.charAt(0) ==')'){
				if(str.length() ==1){
					return "";
				}
				str = str.substring(1,str.length());
			}
		while(str.charAt(str.length()-1) =='\'' || str.charAt(str.length()-1) =='"' || str.charAt(str.length()-1)=='`' || str.charAt(str.length()-1) =='(' ||str.charAt(str.length()-1) ==')'){
				if(str.length() ==1){
					return "";
				}
				str = str.substring(0,str.length()-1);
			}
		return str;
	}


	
	public Timestamp StringToTimestamp(String str){
		try{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedTimeStamp = dateFormat.parse(str);
		Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());
		return timestamp;
		}catch(ParseException e){}
		return null;
	}
	

	public int[] mainClauses(String str, int[] count){
	
		String[] items = {"WHERE ", "ORDER BY ","LIMIT ","OFFSET ","GROUP BY ",";"};
		int[] result=new int[items.length];
		for(int i=0;i<result.length;i++){
			result[i] = -1;
			int indexTemp=str.indexOf(items[i]);
			while(indexTemp >= 0) {
				if(count[indexTemp] ==0){
					result[i] = indexTemp;
					break;
				}
				indexTemp = str.indexOf(items[i], indexTemp+1);
			}	
		}
		if(result[result.length-1] == (-1) ){
			result[result.length-1]  = str.length();
		}
		Arrays.sort(result);
		
		return result;
	}



	public double getGroupbyFunctionValue(String str,int code){
		String[] values = this.performOper(str).split(",");
		double[] val = new double[values.length];
		for(int i=0;i<values.length;i++){
			val[i] = Double.parseDouble(this.performOper(values[i]));
		}
		if(code ==1){
			Arrays.sort(val);
			return val[val.length-1];
		}
		if(code ==2){
			Arrays.sort(val);
			return val[0];
		}
		if(code ==3){
			double avg =0;
			for(int i=0;i<val.length;i++){
				avg+=val[i];
			}
			return avg/val.length;
		}
		if(code ==4){
			double sum =0;
			for(int i=0;i<val.length;i++){
				sum+=val[i];
			}
			return sum;
		}
		
		return (Double) null;
	}

	
public int[] getSubQueryConfirmation(String str){
		
	
	ArrayList<String> EachRow = new ArrayList<String>();
	String valueString = str;
	String test="";
	int[] count = new int[str.length()];
	for(int i=0;i<str.length();i++){
		count[i] =0;
	}
	
	


	Stack<Character> st = new Stack<Character>();
	Stack<Character> st1= new Stack<Character>();
	for(int i=0;i<valueString.length();i++){
		if(valueString.charAt(i) == '('){
			
				st.push('(');
	
		}
		


		if(valueString.charAt(i)==')' ){
			if(!st.empty() && st.peek()=='('){
		         try {
		             st.pop();
		          }
		          catch (EmptyStackException e) {
		          }
			}else{
				test+=String.valueOf(valueString.charAt(i));
			}
		}
		if(valueString.charAt(i) ==','){
			if(st.empty()){
				EachRow.add(test);
				test="";
			}else{
				test+=String.valueOf(valueString.charAt(i));
			}
		}else{
			test+=String.valueOf(valueString.charAt(i));
		}
		if(!st.empty()){
			count[i] =1;
		}
		}

		EachRow.add(test);


		
		
		
	return count;
	}

}