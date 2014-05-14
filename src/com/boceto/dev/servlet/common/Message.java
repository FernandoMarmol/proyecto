package com.boceto.dev.servlet.common;


public class Message {

	private String messageType;
	private String description;
	private String relatedField;
	private String nextToDo;
	
	private boolean isError = false;
	
	public Message(){}
	
	public Message(boolean isError){
		this.isError = isError;
	}
	
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getRelatedField() {
		return relatedField;
	}
	public void setRelatedField(String relatedField) {
		this.relatedField = relatedField;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNextToDo() {
		return nextToDo;
	}
	public void setNextToDo(String nextToDo) {
		this.nextToDo = nextToDo;
	}
}
