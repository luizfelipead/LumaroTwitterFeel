package com.lumaro.twitterfeel.service;

import javax.annotation.PostConstruct;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.springframework.stereotype.Service;
import com.lumaro.twitterfeel.model.Result;
import com.lumaro.twitterfeel.model.StatisticsSummary;


@Service
public class StatisticsService {

	private SummaryStatistics stats;
	
	@PostConstruct
	protected void postConstruct() {
		 stats = new SummaryStatistics();
	}
	
	public StatisticsSummary teste(Result twitterResult){
		StatisticsSummary result = new StatisticsSummary();
		
		for (Integer sentimentValue : twitterResult.getAllSentimentsValues()) {
			stats.addValue(sentimentValue);
		}
		
		result.setMin(stats.getMin()); // Valor minimo
		result.setMax(stats.getMax()); // Valor maximo
		result.setMean(stats.getMean()); // Media Aritimetica
		result.setGeometricMean(stats.getGeometricMean()); // Media Geometrica
		result.setStandardDeviation(stats.getStandardDeviation()); // Desvio padrao
		result.setVariance(stats.getVariance()); // Varianca
		result.setSecondMoment(stats.getSecondMoment()); //Segundo momento
		
		return result;
	}
}
