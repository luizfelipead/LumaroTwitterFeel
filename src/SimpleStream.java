import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class SimpleStream {
    public static void main(String[] args) {

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

        Lumaro.init("en");
        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
               
            	if(status.getLang().equals(Lumaro.LANGUAGE)){
	                String username = status.getUser().getScreenName();
	                String content = status.getText();
	                
	                System.out.println("Tweet: "+content);
	                System.out.println("Tweeted by: "+username);
	                System.out.println("Sentiment Level:  "+Lumaro.findSentiment(content));
            	}

            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        FilterQuery fq = new FilterQuery();
    
        String keywords[] = {"Dilma"};

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);  

    }
}
