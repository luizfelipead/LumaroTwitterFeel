import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetController {

	public static Result getTweets(String topic, int maxTweets, String language, boolean addRepeated ) {

		Twitter twitter = new TwitterFactory().getInstance();
		Result finalResult = new Result();
		finalResult.tweetList = new ArrayList<Status>();

		try {
			Query query = new Query(topic);
			query.setLang(language);
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					if(tweet.getRetweetedStatus() != null){
						boolean alreadyExists = false;
						if(!addRepeated){
							alreadyExists = checkIfAlreadyExists(finalResult.tweetList, tweet.getRetweetedStatus().getText());
						}
						if(!alreadyExists){
							finalResult.tweetList.add(tweet);				
							if (finalResult.oldestTweet == null || finalResult.oldestTweet.getCreatedAt().after(tweet.getCreatedAt())){
								finalResult.oldestTweet = tweet;
							}
							if (finalResult.newestTweet == null || finalResult.newestTweet.getCreatedAt().before(tweet.getCreatedAt())){
								finalResult.newestTweet = tweet;
							}
							if(finalResult.mostPopularTweet == null || finalResult.mostPopularTweet.getRetweetCount() < tweet.getRetweetCount()){
								finalResult.mostPopularTweet = tweet;
							}
						}
					}
				}
			} while ((query = result.nextQuery()) != null && finalResult.tweetList.size() <= maxTweets);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		return finalResult;
	}
	
	private static boolean checkIfAlreadyExists(List<Status> tweetList, String text) {
		for (Status status : tweetList) {
			if(status.getRetweetedStatus().getText().equals(text)){
				return true;
			}
		}
		return false;
	}

	//Gambiarra para pegar tweet completo
	public static String getFullTweetText(Status tweet){
		if(tweet.getRetweetedStatus() == null){
			return tweet.getText();
		} else {
			return tweet.getRetweetedStatus().getText();
		}
	}
}