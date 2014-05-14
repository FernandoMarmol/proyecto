package com.boceto.lang;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageWorker {
	
	private static ResourceBundle language = null;
	
	public static void init(){
		try{
			language = ResourceBundle.getBundle(LanguageWorker.class.getPackage().getName() + ".translation", Locale.getDefault());
		}
		catch(Exception e){
			language = ResourceBundle.getBundle(LanguageWorker.class.getPackage().getName() + ".translation", Locale.ENGLISH);
		}
	}

	public static String getMessage(String key){
		
		if(language == null)
			init();
			
		try{
			return language.getString(key);
		}
		catch(Exception e){
			return key + " - Does not exist for language " + language.getLocale();
		}
	}
	
}
