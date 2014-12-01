package com.lumaro.twitterfeel.model;


public enum SentimentLevel {

	LEVEL_0("Very negative"), LEVEL_1("Negative"), LEVEL_2("Neutral"), LEVEL_3("Positive"), LEVEL_4("Very Positive");

	private String canonicalName;

	private SentimentLevel(final String canonicalName) {
		this.canonicalName = canonicalName;
	}

	public static SentimentLevel getByInt(final int sentiment) {
		SentimentLevel level;
		if (sentiment == 0) {
			level = LEVEL_0;
		} else if (sentiment == 1) {
			level = LEVEL_1;
		} else if (sentiment == 2) {
			level = LEVEL_2;
		} else if (sentiment == 3) {
			level = LEVEL_3;
		} else {
			level = LEVEL_4;
		}
		return level;
	}

	public String getCanonicalName() {
		return this.canonicalName;
	}

}
