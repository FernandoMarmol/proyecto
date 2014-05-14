package com.boceto.dev.servlet.common;

public class ValidationError {

	private String description;
	private String relatedField;
	private String errorType;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRelatedField() {
		return relatedField;
	}

	public void setRelatedField(String relatedField) {
		this.relatedField = relatedField;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
}
