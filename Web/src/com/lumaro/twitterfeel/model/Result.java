package com.lumaro.twitterfeel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import twitter4j.Status;

public class Result {
	private Status oldestTweet;
	private Status newestTweet;
	private Status mostPopularTweet;
	private List<Status> tweetList;
	private Map<SentimentLevel, Integer> allSentimentsLevels;

	public Result() {
		super();
		this.tweetList = new ArrayList<Status>();
	}

	public List<Status> getTweetList() {
		return this.tweetList;
	}

	public void setTweetList(final List<Status> tweetList) {
		this.tweetList = tweetList;
	}

	public Status getOldestTweet() {
		return this.oldestTweet;
	}

	public void setOldestTweet(final Status oldestTweet) {
		this.oldestTweet = oldestTweet;
	}

	public Status getNewestTweet() {
		return this.newestTweet;
	}

	public void setNewestTweet(final Status newestTweet) {
		this.newestTweet = newestTweet;
	}

	public Status getMostPopularTweet() {
		return this.mostPopularTweet;
	}

	public void setMostPopularTweet(final Status mostPopularTweet) {
		this.mostPopularTweet = mostPopularTweet;
	}

	public Map<SentimentLevel, Integer> getAllSentimentsLevels() {
	    return allSentimentsLevels;
    }

	public void setAllSentimentsLevels(Map<SentimentLevel, Integer> allSentimentsLevels) {
	    this.allSentimentsLevels = allSentimentsLevels;
    }

}