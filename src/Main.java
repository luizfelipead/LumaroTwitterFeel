import twitter4j.Status;

public class Main {

	public static void main(String[] args) {
		String topic = "Dilma";
		Lumaro.init("en");
		Result result = TweetController.getTweets(topic, 100, Lumaro.LANGUAGE, true);
		for(Status tweet : result.tweetList) {
			int sentiment = Lumaro.findSentiment(tweet.getRetweetedStatus().getText());
			System.out.println(tweet.getRetweetedStatus().getText() + " : " + sentiment);
			result.totalSentiment+=sentiment;
		}
		System.out.println("Avg: "+(float)result.totalSentiment/(float)result.tweetList.size());
		System.out.println("-------------------------------------------");
		System.out.println("Oldest Tweet: "+result.oldestTweet.getText()+" at "+result.oldestTweet.getCreatedAt());
		System.out.println("Tweeted by: "+result.oldestTweet.getUser().getScreenName());
		System.out.println("-------------------------------------------");
		System.out.println("Most recent Tweet: "+result.oldestTweet.getText()+" at "+result.newestTweet.getCreatedAt());
		System.out.println("Tweeted by: "+result.newestTweet.getUser().getScreenName());
	}
}