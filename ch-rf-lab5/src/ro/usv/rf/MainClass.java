package ro.usv.rf;

public class MainClass {
	
	
	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("data.csv");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			int numberOfFeatures_without_class = learningSet[0].length - 1;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			System.out.println("--------------------------------------------------");
			System.out.println("Euclidian Generalized Distancearray: ");
			
			/*
			 * 25.89 47.56 Judet 1
			 * 24 45.15 Judet 2
			 * 25.33 45.44 Judet 3
			 * 
			 */
			
			double[][] new_cities ={{25.89, 47.56}, {24, 45.15}, {25.33, 45.44}};
			int[] kn_cases ={9, 11, 17, 31};
			double[][] distance_array = new double[new_cities.length][learningSet.length];
			
			for(int i = 0; i < numberOfPatterns; i++)
			{
				for (int j = 0; j < new_cities.length; j++)
				{
					distance_array[j][i] = DistanceUtils.getEuclidianGeneralizedDistance(new_cities[j], learningSet[i]);
				}
			}
			
			String first_set = null;
			for(int i=0; i < kn_cases.length; i++)
			{
				first_set = DistanceUtils.getNNClass(kn_cases[i], distance_array[0], learningSet);
				System.out.println("First set,  k =  " + kn_cases[i] + " has class: " + first_set); 
			}
			System.out.println("Max knn: " + DistanceUtils.get_max_knn(distance_array[0], learningSet, "Suceava"));
			// Suceava ^
			System.out.println();
			
			
			for(int i=0; i < kn_cases.length; i++)
			{
				first_set = DistanceUtils.getNNClass(kn_cases[i], distance_array[1], learningSet);
				System.out.println("Second set,  k =  " + kn_cases[i] + " has class: " + first_set); 
			}
			System.out.println("Max knn: " + DistanceUtils.get_max_knn(distance_array[1], learningSet, "Valcea"));
			// Valcea ^
			
			System.out.println();
			for(int i=0; i < kn_cases.length; i++)
			{
				first_set = DistanceUtils.getNNClass(kn_cases[i], distance_array[2], learningSet);
				System.out.println("Third set, k = " + kn_cases[i] + " has class: " + first_set); 
			}
			System.out.println("Max knn: " + DistanceUtils.get_max_knn(distance_array[2], learningSet, "Brasov"));
			System.out.println("Done"); 
			// Brasov ^
			
			
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
