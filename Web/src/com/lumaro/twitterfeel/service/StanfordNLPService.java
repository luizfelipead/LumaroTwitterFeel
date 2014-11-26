package com.lumaro.twitterfeel.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

@Service
public class StanfordNLPService {

	private StanfordCoreNLP pipeline;

	@PostConstruct
	protected void init() {
		this.pipeline = new StanfordCoreNLP("tweetfeelNLP.properties");
	}

	public int findSentiment(final String tweet) {
		int mainSentiment = 0;
		if ((tweet != null) && (tweet.length() > 0)) {
			int longest = 0;
			final Annotation annotation = this.pipeline.process(tweet);
			for (final CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				final Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
				final int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				final String partText = sentence.toString();
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
