package com.lumaro.twitterfeel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.lumaro.twitterfeel.model.Result;
import com.lumaro.twitterfeel.model.SentimentLevel;

@Service
public class TwitterService {

	private Twitter twitter;

	@Autowired
	protected StanfordNLPService stanfordNLPService;

	@PostConstruct
	protected void postConstruct() {
		this.twitter = new TwitterFactory().getInstance();
	}

	public Result getTweets(final String topic, final int maxTweets, final String language, final boolean addRepeated) {

		final Result finalResult = new Result();
		final List<Status> statusList = new ArrayList<Status>();
		final Map<SentimentLevel, Integer> allSentimentsLevels = new HashMap<SentimentLevel, Integer>();

		try {

			Query query = new Query(topic);
			query.setLang(language);
			QueryResult result;
			do {
				result = this.twitter.search(query);
				finalResult.setTweetList(result.getTweets());
				for (final Status tweet : finalResult.getTweetList()) {
					if (tweet.getRetweetedStatus() != null) {
						boolean alreadyExists = false;
						if (!addRepeated) {
							alreadyExists = checkIfAlreadyExists(statusList, tweet.getRetweetedStatus().getText());
						}
						if (!alreadyExists) {
							statusList.add(tweet);
							if ((finalResult.getOldestTweet() == null) || finalResult.getOldestTweet().getCreatedAt().after(tweet.getCreatedAt())) {
								finalResult.setOldestTweet(tweet);
							}
							if ((finalResult.getNewestTweet() == null) || finalResult.getNewestTweet().getCreatedAt().before(tweet.getCreatedAt())) {
								finalResult.setNewestTweet(tweet);
							}
							if ((finalResult.getMostPopularTweet() == null) || (finalResult.getMostPopularTweet().getRetweetCount() < tweet.getRetweetCount())) {
								finalResult.setMostPopularTweet(tweet);
							}
						}
					}
				}
			} while (((query = result.nextQuery()) != null) && (statusList.size() <= maxTweets));
		} catch (final TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}

		for (final Status tweet : finalResult.getTweetList()) {
			final int sentimentInt = this.stanfordNLPService.findSentiment(tweet.getText());
			final SentimentLevel sentiment = SentimentLevel.getByInt(sentimentInt);
			if (!allSentimentsLevels.containsKey(sentiment)) {
				allSentimentsLevels.put(sentiment, 0);
			}

			Integer sentimentSum = allSentimentsLevels.get(sentiment);
			sentimentSum += 1;
			allSentimentsLevels.put(sentiment, sentimentSum);
		}

		finalResult.setAllSentimentsLevels(allSentimentsLevels);

		return finalResult;
	}

	private static boolean checkIfAlreadyExists(final List<Status> tweetList, final String text) {
		for (final Status status : tweetList) {
			if (status.getRetweetedStatus().getText().equals(text)) {
				return true;
			}
		}
		return false;
	}
}