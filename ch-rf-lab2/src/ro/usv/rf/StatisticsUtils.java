package ro.usv.rf;

import java.util.HashMap;
import java.util.Map;

public class StatisticsUtils 
{

	protected static double calculateFeatureAverage(Double[] feature) {
		Map<Double, Integer> counterMap = getFeatureDistincElementsCounterMap(feature);
		double featureAverage = 0;

		double sum1 = 0;
		double sum2 = 0;

		sum1 = counterMap.keySet().stream()
				.mapToDouble(x -> calculateSum1(x, counterMap.get(x))).sum();
		sum2 = counterMap.values().stream()
				.mapToInt(x -> x).sum();
		featureAverage = sum1 / sum2;
		//System.out.println("The feature average is: " +  featureAverage);
		return featureAverage;
}
	
	public static Map<Double, Integer> getFeatureDistincElementsCounterMap(Double feature[])
	{
		Map<Double, Integer> counterMap = new HashMap<Double, Integer>();
		for (int j = 0; j < feature.length; j++) {
			if (counterMap.containsKey(feature[j])) {
				int count = counterMap.get(feature[j]);
				counterMap.put((feature[j]), ++count);
			} else {
				counterMap.put((feature[j]), 1);
			}
		}
		return counterMap;
	}
	
	protected static Map<Double, Double> getFeatureWeightElementsMap(Double feature[], Double[] weights)
	{
		Map<Double, Double> weightMap = new HashMap<Double, Double>();
		for (int j = 0; j < feature.length; j++) {
			if (weightMap.containsKey(feature[j])) {
				Double count = weightMap.get(feature[j]) + weights[j];
				weightMap.put((feature[j]), count);
			} else {
				weightMap.put((feature[j]), weights[j]);
			}
		}
		return weightMap;
	}
	
	private static Double calculateSum1(double value, int count)
	{
		return count * value;
	}

	private static Double calculateSumWeight(double value, double weight)
	{
		return weight * value;
	}
	
	protected static double calculateFeatureWeightedAverage(Double[] feature, Double[] weights) {
		double featureWeightedAverage = 0.0;
		// your code here
		Map<Double, Double> counterMap = getFeatureWeightElementsMap(feature, weights);

		double sum1 = 0;
		double sum2 = 0;

		sum1 = counterMap.keySet().stream()
				.mapToDouble(x -> calculateSumWeight(x, counterMap.get(x))).sum();
		sum2 = counterMap.values().stream()
				.mapToDouble(x -> x).sum();
		featureWeightedAverage = Math.floor(sum1 / sum2 * 100) / 100;
		return featureWeightedAverage;
	}
	
	protected static double calculateFrequencyOfOccurence(Map<Double, Integer> counterMap, double featureElement) {
		double frequencyOfOccurence = 0.0;
		frequencyOfOccurence = counterMap.get(featureElement) / counterMap.values().stream()
				.mapToDouble(x -> x).sum();
		return frequencyOfOccurence;
	}
	
	protected static double calculateFeatureDispersion(Double[] feature, double featureAverage) {
		double featureDispersion = 0.0;
		// your code here
		for (int i = 0; i < feature.length; i++)
		{
			featureDispersion += (feature[i] - featureAverage)*(feature[i] - featureAverage);
		}
		featureDispersion /= (feature.length - 1);
		return Math.floor(featureDispersion * 100)/100;
	}
	
	protected static double calculateCovariance (Double[] feature1, Double[] feature2,
			double feature1WeightedAverage,double feature2WeightedAverage) {
		double covariance = 0.0;
		// your code here
		for (int i = 0; i < feature1.length; i++)
		{
			covariance += (feature1[i] - feature1WeightedAverage)*(feature2[i] - feature2WeightedAverage);
		}
		covariance /= (feature1.length - 1);
		return Math.floor(covariance * 100)/100;
	}
	
	protected static double calculateCorrelationCoefficient  (double covariance, double feature1Dispersion, 
			double feature2Dispersion ) {
		double correlationCoefficient = 0.0;
		// your code here
		return correlationCoefficient;
	}
	
	protected static double calculateAverageSquareDeviation (double featureDispersion ) {
		double averageSquareDeviation = 0.0;
		// your code here 
		averageSquareDeviation = Math.floor(Math.sqrt(featureDispersion)*100) /100;
		return averageSquareDeviation;
	}
}
