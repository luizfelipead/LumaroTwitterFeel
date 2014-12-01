package com.lumaro.twitterfeel.model;

import java.io.Serializable;
import java.util.Date;
import twitter4j.Status;

public class Tweet implements Serializable  {
	
	private static final long serialVersionUID = -6294853371864520473L;
	private Date createdAt;
	private String text;
	private String userName;
	private Integer retweetCount;

	public Tweet() {
		super();
	}

	public Tweet( Status status ) {

		this();
		this.createdAt = status.getCreatedAt();
		this.text = status.getText();
		this.userName = status.getUser().getScreenName();
		this.retweetCount = status.getRetweetCount();
	}
	
	public Integer getRetweetCount() {
	
		return retweetCount;
	}

	
	public void setRetweetCount( Integer retweetCount ) {
	
		this.retweetCount = retweetCount;
	}
	
	public String getUserName() {
	
		return userName;
	}

	
	public void setUserName( String userName ) {
	
		this.userName = userName;
	}

	public Date getCreatedAt() {
	
		return createdAt;
	}

	
	public void setCreatedAt( Date createdAt ) {
	
		this.createdAt = createdAt;
	}

	public String getText() {
	
		return text;
	}
	
	public void setText( String text ) {
	
		this.text = text;
	}

}