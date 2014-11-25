import java.util.HashMap;
import java.util.List;
import java.util.Map;

import twitter4j.Status;

public class Result {
	public List<Status> tweetList;
	public float totalSentiment = 0;
	public Status oldestTweet;
	public Status newestTweet;
	public Status mostPopularTweet;
	public List<Status> mostRelevantTweets;
	public Map<SentimentLevel, Integer> sentimentLevel;
	
	public Result() {
		super();
		sentimentLevel = new HashMap<SentimentLevel, Integer>();
		for (SentimentLevel level : SentimentLevel.values()) {
			sentimentLevel.put(level, 0);
		}
	}

	public void sumSentiment(int sentiment) {
		SentimentLevel level = SentimentLevel.getByInt( sentiment );
		Integer sum = sentimentLevel.get(level);
		sum += 1;
		sentimentLevel.put(level, sum);
	}

}
