package com.lumaro.twitterfeel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lumaro.twitterfeel.model.Result;
import com.lumaro.twitterfeel.model.SentimentLevel;
import com.lumaro.twitterfeel.model.StatisticsSummary;
import com.lumaro.twitterfeel.service.StatisticsService;
import com.lumaro.twitterfeel.service.TwitterService;

import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.trees.Tree;

@Controller
@RequestMapping("/")
public class TwitterController {

	@Autowired
	protected TwitterService twitterService;
	
	@Autowired
	protected StatisticsService statisticsService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(final ModelMap model) {
		model.addAttribute("welcome", "Welcome to Lumaro Twitter Feel");

		return "home";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String twitter(final ModelMap model, final String topic, final Integer tweetQuantity, final String language, Boolean addRepeated) {

		addRepeated = addRepeated == null ? false : addRepeated;

		model.addAttribute("topic", topic);
		model.addAttribute("tweetQuantity", tweetQuantity);
		model.addAttribute("language", language);
		model.addAttribute("addRepeated", addRepeated ? "Yes" : "No");

		final Result result = this.twitterService.runTweetAnalysis(topic, tweetQuantity, language, addRepeated);
		final StatisticsSummary statisticsSummary = statisticsService.teste(result);
		
		model.addAttribute("mean", statisticsSummary.getMean());
		model.addAttribute("min", statisticsSummary.getMin());
		model.addAttribute("max", statisticsSummary.getMax());
		model.addAttribute("standardDeviation", statisticsSummary.getStandardDeviation());
		model.addAttribute("variance", statisticsSummary.getVariance());
		model.addAttribute("secondMoment", statisticsSummary.getSecondMoment());
		model.addAttribute("geometricMean", statisticsSummary.getGeometricMean());
		
		model.addAttribute("oldestText", result.getOldestTweet().getText() + " at " + result.getOldestTweet().getStatus().getCreatedAt());
		model.addAttribute("oldestOwner", result.getOldestTweet().getStatus().getUser().getScreenName());
		model.addAttribute("oldestTweetSentiment", result.getOldestTweetSentiment().getCanonicalName());

		// final String oldestTreeHTML = "";
		// final String handleTree = this.handleTree(oldestTreeHTML,
		// result.getOldestTweetTree());
		// System.out.println("FINAL " + oldestTreeHTML + " / " + handleTree);
		// model.addAttribute("oldestTree", handleTree);
		model.addAttribute("oldestTree", result.getOldestTweetTree());
		model.addAttribute("newestText", result.getNewestTweet().getText() + " at " + result.getNewestTweet().getStatus().getCreatedAt());
		model.addAttribute("newestOwner", result.getNewestTweet().getStatus().getUser().getScreenName());
		model.addAttribute("newe" + "stTweetSentiment", result.getNewestTweetSentiment().getCanonicalName());
		model.addAttribute("newestTree", result.getOldestTweetTree());
		// this.handleTree(result.getOldestTweetTree());

		String chartData = "";
		int value = 0;
		final Map<SentimentLevel, Integer> allSentimentsLevels = result.getAllSentimentsLevels();
		for (final SentimentLevel sentiment : SentimentLevel.values()) {
			value = 0;

			if (allSentimentsLevels.containsKey(sentiment)) {
				value = allSentimentsLevels.get(sentiment);
			}
			chartData += "['" + sentiment.getCanonicalName() + "'," + value + "],";
		}
		chartData = chartData.substring(0, chartData.length() - 1);
		model.addAttribute("chartData", chartData);

		return "search";
	}

	private String handleTree(String html, final Tree tree) {

		System.out.println(tree.toString());

		final List<Tree> children = tree.getChildrenAsList();

		if (children != null) {
			for (final Tree child : children) {
				final String tag = child.value();
				System.out.println("CHILD: " + tag);
				final List<Tree> childrenAsList = child.getChildrenAsList();
				if (childrenAsList.size() == 1) {
					final List<Tree> leaves = tree.getLeaves();
					for (final Tree leaf : leaves) {
						System.out.println("LEAF: " + leaf.toString());
						final List<Word> words = leaf.yieldWords();
						for (final Word word : words) {
							html += "<span>" + word.value() + "</span>";
							html += "<span class='badge'>" + tag + "</span>";
							System.out.println("[HTML]" + html);
						}
					}
				} else {
					for (final Tree tree2 : childrenAsList) {
						this.handleTree(html, tree2);
					}
				}
			}
		}

		return html;
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(final ModelMap model) {
		model.addAttribute("owners", "Marcos Cardoso, Luiz Felipe Dias e Rodrigo Ney");

		return "about";
	}
}
