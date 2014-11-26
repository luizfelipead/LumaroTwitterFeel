package com.lumaro.twitterfeel.controller;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lumaro.twitterfeel.model.Result;
import com.lumaro.twitterfeel.model.SentimentLevel;
import com.lumaro.twitterfeel.service.TwitterService;

@Controller
@RequestMapping("/twitter")
public class TwitterController {

	private static String LANGUAGE = "en";

	@Autowired
	protected TwitterService twitterService;

	@RequestMapping(method = RequestMethod.GET)
	public String twitter(final ModelMap model, final String topic) {
		final Result result = this.twitterService.getTweets(topic, 5, LANGUAGE, true);

		model.addAttribute("oldestText", "Oldest Tweet: " + result.getOldestTweet().getText() + " at " + result.getOldestTweet().getCreatedAt());
		model.addAttribute("oldestOwner", "Tweeted by: " + result.getOldestTweet().getUser().getScreenName());
		model.addAttribute("newestText", "Most recent Tweet: " + result.getNewestTweet().getText() + " at " + result.getNewestTweet().getCreatedAt());
		model.addAttribute("newestOwner", "Tweeted by: " + result.getNewestTweet().getUser().getScreenName());

		String chartData = "";
		for (final Entry<SentimentLevel, Integer> entry : result.getAllSentimentsLevels().entrySet()) {
			chartData += "['" + entry.getKey().getCanonicalName() + "'," + entry.getValue() + "],";
		}
		chartData = chartData.substring(0, chartData.length() - 1);
		model.addAttribute("chartData", chartData);

		return "twitter";
	}
}
