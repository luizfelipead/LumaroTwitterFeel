import java.util.Map.Entry;

import twitter4j.Status;

public class Main {

	public static void main(final String[] args) {
		final String topic = "Dilma";
		Lumaro.init("en");
		final Result result = TweetController.getTweets(topic, 1, Lumaro.LANGUAGE, true);
		for (final Status tweet : result.tweetList) {
			final int sentiment = Lumaro.findSentiment("love");
			// System.out.println(tweet.getRetweetedStatus().getText() + " : " + sentiment);
			result.sumSentiment(sentiment);
			result.totalSentiment += sentiment;
		}
		System.out.println("Avg: " + result.totalSentiment / result.tweetList.size());
		System.out.println("-------------------------------------------");
		System.out.println("Oldest Tweet: " + result.oldestTweet.getText() + " at " + result.oldestTweet.getCreatedAt());
		System.out.println("Tweeted by: " + result.oldestTweet.getUser().getScreenName());
		System.out.println("-------------------------------------------");
		System.out.println("Most recent Tweet: " + result.newestTweet.getText() + " at " + result.newestTweet.getCreatedAt());
		System.out.println("Tweeted by: " + result.newestTweet.getUser().getScreenName());
		System.out.println("-------------------------------------------");
		String chartData = "";
		for (Entry<SentimentLevel, Integer> entry : result.sentimentLevel.entrySet()) {
			chartData += "['"+entry.getKey().getCanonicalName()+"',"+entry.getValue()+"],";
		}
		chartData = chartData.substring(0, chartData.length()-1);
		System.out.println("Data: "+chartData);
		
	}
}
