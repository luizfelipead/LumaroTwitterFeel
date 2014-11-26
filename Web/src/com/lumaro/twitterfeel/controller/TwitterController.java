package com.lumaro.twitterfeel.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lumaro.twitterfeel.model.Result;
import com.lumaro.twitterfeel.model.SentimentLevel;
import com.lumaro.twitterfeel.service.TwitterService;

@Controller
@RequestMapping("/")
public class TwitterController {

	private static String LANGUAGE = "en";

	@Autowired
	protected TwitterService twitterService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(final ModelMap model) {
		model.addAttribute("welcome", "Welcome to Lumaro Twitter Feel");

		return "home";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String twitter(final ModelMap model, final String topic) {
		final Result result = this.twitterService.getTweets(topic, 5, LANGUAGE, false);

		model.addAttribute("oldestText", result.getOldestTweet().getText() + " at " + result.getOldestTweet().getCreatedAt());
		model.addAttribute("oldestOwner", result.getOldestTweet().getUser().getScreenName());
		model.addAttribute("newestText", result.getNewestTweet().getText() + " at " + result.getNewestTweet().getCreatedAt());
		model.addAttribute("newestOwner", result.getNewestTweet().getUser().getScreenName());

		String chartData = "";
		int value = 0;
		Map<SentimentLevel, Integer> allSentimentsLevels = result.getAllSentimentsLevels();
		for ( SentimentLevel sentiment : SentimentLevel.values() ) {
			value = 0;
			
			if( allSentimentsLevels.containsKey( sentiment ) ) {
				value = allSentimentsLevels.get( sentiment );
			}
			chartData += "['" + sentiment.getCanonicalName() + "'," + value + "],";
		}
		chartData = chartData.substring(0, chartData.length() - 1);
		model.addAttribute("chartData", chartData);

		return "search";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(final ModelMap model) {
		model.addAttribute("owners", "Marcos Cardoso, Luiz Felipe Dias e Rodrigo Ney");

		return "about";
	}
}
