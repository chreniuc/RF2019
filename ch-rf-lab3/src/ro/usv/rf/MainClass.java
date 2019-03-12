package ro.usv.rf;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			System.out.println("--------------------------------------------------");
			for(int i = 1 ; i < learningSet.length; i++)
			{
				System.out.println("Euclidian Distance between pattern 0 and pattern " + i + " : " + DistanceUtils.getEuclidianDistance(learningSet[0], learningSet[i]));
				System.out.println("Mahalanobis Distance between pattern 0 and pattern " + i + " : " + DistanceUtils.getMahalanobis(learningSet[0], learningSet[i], learningSet.length));
				System.out.println("Cebisev Distance between pattern 0 and pattern " + i + " : " + DistanceUtils.getCebisev(learningSet[0], learningSet[i]));
				System.out.println("CityBlock Distance  between pattern 0 and pattern " + i + " : " + DistanceUtils.getCityBlock(learningSet[0], learningSet[i]));
				Distances distantele = DistanceUtils.getAllDistances(learningSet[0], learningSet[i], learningSet.length);
				
				System.out.println("Optimized: ");
				System.out.println("Euclidian Distance between pattern 0 and pattern " + i + " : " + distantele.getEuclidian());
				System.out.println("Mahalanobis Distance between pattern 0 and pattern " + i + " : " + distantele.getMahalanobis());
				System.out.println("Cebisev Distance between pattern 0 and pattern " + i + " : " + distantele.getCebisev());
				System.out.println("CityBlock Distance  between pattern 0 and pattern " + i + " : " +  distantele.getCityBlock());
			}
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
