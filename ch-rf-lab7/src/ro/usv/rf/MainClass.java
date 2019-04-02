package ro.usv.rf;

import java.util.HashMap;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("date.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			System.out.println("--------------------------------------------------");
			// Get all clases
			HashMap<Double, Integer> classes = new HashMap<Double, Integer>();
			for(int i = 0; i < learningSet.length; i++)
			{
				Integer cur_value = classes.get(learningSet[i][2]);
				if (cur_value == null) {
					classes.put( learningSet[i][2],1);
				}
				else
				{
					classes.put( learningSet[i][2],classes.get( learningSet[i][2]) +1);
				}
			}
			
			double[][] w = DistanceUtils.getWMatrix(learningSet, classes);
			double[] feature = {4, 5};
			DistanceUtils.getThreeClasifier(learningSet, feature);
			
			System.out.println("Map size: " + classes.size());
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
