import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentUtils;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class Lumaro {

	public static String LANGUAGE = "en";

	static StanfordCoreNLP pipeline;

	public static void init(final String lang) {
		LANGUAGE = lang;
		pipeline = new StanfordCoreNLP("tweetfeelNLP.properties");
	}

	public static int findSentiment(final String tweet) {
		int mainSentiment = 0;
		if (tweet != null && tweet.length() > 0) {
			int longest = 0;
			final Annotation annotation = pipeline.process(tweet);
			for (final CoreMap sentence : annotation
					.get(CoreAnnotations.SentencesAnnotation.class)) {
				final Tree tree = sentence
						.get(SentimentCoreAnnotations.AnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				System.out.println(partText);
				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}

			}
		}
		return mainSentiment;
	}
}
