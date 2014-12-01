package com.lumaro.twitterfeel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import com.google.gson.GsonBuilder;
import com.lumaro.twitterfeel.model.Result;
import com.lumaro.twitterfeel.model.SentimentLevel;
import com.lumaro.twitterfeel.model.Tweet;
import com.lumaro.twitterfeel.repository.TwitterRepository;
import com.lumaro.twitterfeel.utils.TextUtils;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.Pair;

@Service
public class TwitterService {

	private Twitter twitter;

	@Autowired
	protected StanfordNLPService stanfordNLPService;

	@Autowired
	private TextUtils textUtils;

	@Autowired
	private TwitterRepository twitterRepository;

	@PostConstruct
	protected void postConstruct() {
		this.twitter = new TwitterFactory().getInstance();
	}

	public Result runTweetAnalysis(final String topic, final int maxTweets, final String language, final boolean addRepeated) {

		final Result finalResult = new Result( topic, maxTweets, language, addRepeated);
		final List<Tweet> finalTweetList = new ArrayList<Tweet>();
		final Map<SentimentLevel, Integer> allSentimentsLevels = new HashMap<SentimentLevel, Integer>();
		final List<Integer> allSentimentsValues = new ArrayList<Integer>();

		try {

			Query query = new Query(topic);
			query.setLang(language);
			QueryResult result;
			do {
				result = this.twitter.search(query);

				final List<Tweet> tweetList = new ArrayList<Tweet>();
				for (final Status status : result.getTweets()) {
					final Status retweetedStatus = status.getRetweetedStatus();
					if (retweetedStatus != null) {
						tweetList.add(new Tweet(retweetedStatus));
					}
				}

				for (final Tweet tweet : tweetList) {
					boolean alreadyExists = false;
					if (!addRepeated) {
						alreadyExists = checkIfAlreadyExists(finalTweetList, tweet.getText());
					}
					if (!alreadyExists) {
						finalTweetList.add(tweet);

						final Tweet oldestTweet = finalResult.getOldestTweet();
						if ((oldestTweet == null) || oldestTweet.getCreatedAt().after(tweet.getCreatedAt())) {
							finalResult.setOldestTweet(tweet);
						}

						final Tweet newestTweet = finalResult.getNewestTweet();
						if ((newestTweet == null) || newestTweet.getCreatedAt().before(tweet.getCreatedAt())) {
							finalResult.setNewestTweet(tweet);
						}

						final Tweet mostPopularTweet = finalResult.getMostPopularTweet();
						if ((mostPopularTweet == null) || (mostPopularTweet.getRetweetCount() < tweet.getRetweetCount())) {
							finalResult.setMostPopularTweet(tweet);
						}
					}
				}
			} while (((query = result.nextQuery()) != null) && (finalTweetList.size() <= maxTweets));

		} catch (final TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}

		for (final Tweet tweet : finalTweetList) {
			final Pair<Integer, Tree> pair = this.stanfordNLPService.findSentiment(tweet.getText());
			final SentimentLevel sentiment = SentimentLevel.getByInt(pair.first());
			allSentimentsValues.add(pair.first());

			if (tweet.equals(finalResult.getOldestTweet())) {
				finalResult.setOldestTweetSentiment(sentiment);
				finalResult.setOldestTweetTree(pair.second());
			} else if (tweet.equals(finalResult.getNewestTweet())) {
				finalResult.setNewestTweetSentiment(sentiment);
				finalResult.setNewestTweetTree(pair.second());
			}

			if (!allSentimentsLevels.containsKey(sentiment)) {
				allSentimentsLevels.put(sentiment, 0);
			}

			Integer sentimentSum = allSentimentsLevels.get(sentiment);
			sentimentSum += 1;
			allSentimentsLevels.put(sentiment, sentimentSum);
		}
		finalResult.setTweetList(finalTweetList);
		finalResult.setAllSentimentsLevels(allSentimentsLevels);
		finalResult.setAllSentimentsValues(allSentimentsValues);

		twitterRepository.save( topic, finalResult );

		return finalResult;
	}

	private static boolean checkIfAlreadyExists(final List<Tweet> tweetList, final String text) {
		boolean result = false;
		for (final Tweet status : tweetList) {
			if (status.getText().equals(text)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public Result fetchFromFile( MultipartFile file ) {

		Result result = null;

		if (!file.isEmpty()) {
			try {
				result = new GsonBuilder().create().fromJson( new String(file.getBytes()) , Result.class );
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}

		return result;

	}
}