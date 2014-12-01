package com.lumaro.twitterfeel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import edu.stanford.nlp.trees.Tree;

public class Result implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3511295040551395147L;
	private Tweet oldestTweet;
	private Tweet newestTweet;
	private Tweet mostPopularTweet;
	private SentimentLevel oldestTweetSentiment;
	private SentimentLevel newestTweetSentiment;
	private String oldestTweetTree;
	private String newestTweetTree;
	private List<Tweet> tweetList;
	private Map<SentimentLevel, Integer> allSentimentsLevels;
	private List<Integer> allSentimentsValues;
	private String topic;
	private Integer maxTweets;
	private String language;
	private Boolean addRepeated;

	public Result(String topic , int maxTweets , String language , boolean addRepeated ) {
		super();
		this.topic = topic;
		this.maxTweets = maxTweets;
		this.language = language;
		this.addRepeated = addRepeated;
		this.tweetList = new ArrayList<Tweet>();
	}

	public String getTopic() {

		return topic;
	}


	public void setTopic( String topic ) {

		this.topic = topic;
	}


	public int getMaxTweets() {

		return maxTweets;
	}


	public void setMaxTweets( int maxTweets ) {

		this.maxTweets = maxTweets;
	}


	public String getLanguage() {

		return language;
	}


	public void setLanguage( String language ) {

		this.language = language;
	}


	public boolean isAddRepeated() {

		return addRepeated;
	}


	public void setAddRepeated( boolean addRepeated ) {

		this.addRepeated = addRepeated;
	}

	public List<Tweet> getTweetList() {
		return this.tweetList;
	}

	public void setTweetList(final List<Tweet> tweetList) {
		this.tweetList = tweetList;
	}

	public Tweet getOldestTweet() {
		return this.oldestTweet;
	}

	public void setOldestTweet(final Tweet oldestTweet) {
		this.oldestTweet = oldestTweet;
	}

	public Tweet getNewestTweet() {
		return this.newestTweet;
	}

	public void setNewestTweet(final Tweet newestTweet) {
		this.newestTweet = newestTweet;
	}

	public Tweet getMostPopularTweet() {
		return this.mostPopularTweet;
	}

	public void setMostPopularTweet(final Tweet mostPopularTweet) {
		this.mostPopularTweet = mostPopularTweet;
	}

	public Map<SentimentLevel, Integer> getAllSentimentsLevels() {
		return this.allSentimentsLevels;
	}

	public void setAllSentimentsLevels(final Map<SentimentLevel, Integer> allSentimentsLevels) {
		this.allSentimentsLevels = allSentimentsLevels;
	}

	public SentimentLevel getOldestTweetSentiment() {
		return this.oldestTweetSentiment;
	}

	public void setOldestTweetSentiment(final SentimentLevel oldestTweetSentiment) {
		this.oldestTweetSentiment = oldestTweetSentiment;
	}

	public SentimentLevel getNewestTweetSentiment() {
		return this.newestTweetSentiment;
	}

	public void setNewestTweetSentiment(final SentimentLevel newestTweetSentiment) {
		this.newestTweetSentiment = newestTweetSentiment;
	}

	public String getOldestTweetTree() {
		return oldestTweetTree;
	}

	public void setOldestTweetTree(Tree oldestTweetTree) {
		this.oldestTweetTree = oldestTweetTree.toString();
	}

	public String getNewestTweetTree() {
		return newestTweetTree;
	}

	public void setNewestTweetTree(Tree newestTweetTree) {
		this.newestTweetTree = newestTweetTree.toString();
	}

	public List<Integer> getAllSentimentsValues() {
		return allSentimentsValues;
	}

	public void setAllSentimentsValues(List<Integer> allSentimentsValues) {
		this.allSentimentsValues = allSentimentsValues;
	}

}