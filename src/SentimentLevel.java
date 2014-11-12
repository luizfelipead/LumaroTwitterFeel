
public enum SentimentLevel {
	
	LEVEL_0("Level 0"), LEVEL_1("Level 1"), LEVEL_2("Level 2"), LEVEL_3("Level 3");
	
	private String canonicalName;
	
	private SentimentLevel(String canonicalName) {
		this.canonicalName = canonicalName;
	}

	public static SentimentLevel getByInt(int sentiment) {
		SentimentLevel level;
		if( sentiment == 0 ) {
			level = LEVEL_0;
		} else if ( sentiment == 1 ) {
			level = LEVEL_1;
		} else if ( sentiment == 2 ) {
			level = LEVEL_2;
		} else {
			level = LEVEL_3;
		}
		return level;
	}

	public String getCanonicalName() {
		return this.canonicalName;
	}

}
