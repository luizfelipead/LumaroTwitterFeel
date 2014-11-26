package com.lumaro.twitterfeel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import twitter4j.Status;
import twitter4j.TweetEntity;

public class Tweet  {
	
	private Status status;
	private String text;
	
	public Tweet() {
		super();
	}

	public Tweet( Status status ) {

		this();
		this.status = status;
		this.text = this.status.getText();
		
//		List<TweetEntity> entities = new ArrayList<TweetEntity>();
//		entities.addAll( Arrays.asList( this.status.getExtendedMediaEntities() ) );
//		entities.addAll( Arrays.asList( this.status.getHashtagEntities() ) );
//		entities.addAll( Arrays.asList( this.status.getMediaEntities() ) );
//		entities.addAll( Arrays.asList( this.status.getSymbolEntities() ) );
//		entities.addAll( Arrays.asList( this.status.getURLEntities() ) );
//		entities.addAll( Arrays.asList( this.status.getUserMentionEntities() ) );
//		
//		System.out.println("############################ ENTITIES FOR TWEET ["+this.text+"]");
//		for ( TweetEntity tweetEntity : entities ) {
//			System.out.println(tweetEntity.getText());
//			this.text = this.text.replace( tweetEntity.getText(), "" );
//		}
//		System.out.println("############################ END");
	}

	public Status getStatus() {
	
		return status;
	}
	
	public void setStatus( Status status ) {
	
		this.status = status;
	}
	
	public String getText() {
	
		return text;
	}
	
	public void setText( String text ) {
	
		this.text = text;
	}

}