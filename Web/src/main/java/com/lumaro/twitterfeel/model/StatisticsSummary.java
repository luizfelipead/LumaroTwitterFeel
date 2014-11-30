package com.lumaro.twitterfeel.model;

public class StatisticsSummary {
	
	private double min;
	private double max;
	private double mean;
	private double geometricMean;
	private double standardDeviation;
	private double variance;
	private double secondMoment;
	
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMean() {
		return mean;
	}
	public void setMean(double mean) {
		this.mean = mean;
	}
	public double getGeometricMean() {
		return geometricMean;
	}
	public void setGeometricMean(double geometricMean) {
		this.geometricMean = geometricMean;
	}
	public double getStandardDeviation() {
		return standardDeviation;
	}
	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	public double getVariance() {
		return variance;
	}
	public void setVariance(double variance) {
		this.variance = variance;
	}
	public double getSecondMoment() {
		return secondMoment;
	}
	public void setSecondMoment(double secondMoment) {
		this.secondMoment = secondMoment;
	}
	
}
