import java.util.List;

import twitter4j.Status;

public class Result {
	public List<Status> tweetList;
	public float totalSentiment = 0;
	public Status oldestTweet;
	public Status newestTweet;
	public Status mostPopularTweet;
	public List<Status> mostRelevantTweets;

}
