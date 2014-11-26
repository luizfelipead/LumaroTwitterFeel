package com.lumaro.twitterfeel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Result {
	private Tweet oldestTweet;
	private Tweet newestTweet;
	private Tweet mostPopularTweet;
	private List<Tweet> tweetList;
	private Map<SentimentLevel, Integer> allSentimentsLevels;

	public Result() {
		super();
		this.tweetList = new ArrayList<Tweet>();
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
	    return allSentimentsLevels;
    }

	public void setAllSentimentsLevels(Map<SentimentLevel, Integer> allSentimentsLevels) {
	    this.allSentimentsLevels = allSentimentsLevels;
    }

}