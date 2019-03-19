package ro.usv.rf;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			int numberOfFeatures_without_class = learningSet[0].length - 1;
			double[][] distance_array = new double[learningSet.length][learningSet.length];
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			System.out.println("--------------------------------------------------");
			System.out.println("Euclidian Generalized Distancearray: ");
			for(int i = 0 ; i < learningSet.length; i++)
			{
				distance_array[i][i] = 0;
				for(int j = i+1; j < learningSet.length; j++)
				{
					distance_array[i][j] = DistanceUtils.getEuclidianGeneralizedDistance(learningSet[i], learningSet[j]);
					distance_array[j][i] = distance_array[i][j]; 
				}
			}
			
			for(int i = 0 ; i < learningSet.length; i++)
			{
				for(int j = 0; j < learningSet.length; j++)
				{
					System.out.print(distance_array[i][j] + " ");
				}
				System.out.println();
			}
			
			
			FileUtils.writeLearningSetToFile("out.txt", distance_array);
			
			int closestNeighbouur = DistanceUtils.getClosestNeighbour(learningSet.length-1, distance_array[learningSet.length-1]);
			
			System.out.println("Class of pattern 4 is: " + learningSet[closestNeighbouur][learningSet.length-1]);
			
			DistanceUtils.get3NNClass(learningSet.length-1, distance_array[learningSet.length-1], learningSet);
			
			System.out.println("Done");
			
			
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
