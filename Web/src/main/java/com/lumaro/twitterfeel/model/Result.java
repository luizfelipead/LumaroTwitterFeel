package com.lumaro.twitterfeel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.stanford.nlp.trees.Tree;

public class Result {
	private Tweet oldestTweet;
	private Tweet newestTweet;
	private Tweet mostPopularTweet;
	private SentimentLevel oldestTweetSentiment;
	private SentimentLevel newestTweetSentiment;
	private Tree oldestTweetTree;
	private Tree newestTweetTree;
	private List<Tweet> tweetList;
	private Map<SentimentLevel, Integer> allSentimentsLevels;
	private List<Integer> allSentimentsValues;
	
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

	public Tree getOldestTweetTree() {
	    return oldestTweetTree;
    }

	public void setOldestTweetTree(Tree oldestTweetTree) {
	    this.oldestTweetTree = oldestTweetTree;
    }

	public Tree getNewestTweetTree() {
	    return newestTweetTree;
    }

	public void setNewestTweetTree(Tree newestTweetTree) {
	    this.newestTweetTree = newestTweetTree;
    }

	public List<Integer> getAllSentimentsValues() {
		return allSentimentsValues;
	}

	public void setAllSentimentsValues(List<Integer> allSentimentsValues) {
		this.allSentimentsValues = allSentimentsValues;
	}

}