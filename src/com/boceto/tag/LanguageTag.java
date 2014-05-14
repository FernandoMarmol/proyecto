package com.boceto.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.boceto.Constants;

public class LanguageTag extends TagSupport {

	private static ResourceBundle language = null;
	
	private String key = null;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	public static void initLanguage(PageContext pageContext){
		try{
			language = ResourceBundle.getBundle(pageContext.getServletContext().getAttribute(Constants.LANG_PACKAGE) + ".translation", Locale.getDefault());
		}
		catch(Exception e){
			language = ResourceBundle.getBundle(pageContext.getServletContext().getAttribute(Constants.LANG_PACKAGE) + ".translation", Locale.ENGLISH);
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		
		String message = getMessage(key, pageContext);
		
		try {
			pageContext.getOut().print(message);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}
	
	public static String getMessage(String key, PageContext pageContext){
		
		if(language == null)
			initLanguage(pageContext);
			
		try{
			return language.getString(key);
		}
		catch(Exception e){
			return key + " - Does not exist for language " + language.getLocale();
		}
	}

}
