/**
 * 
 */
package ro.usv.rf;

/**
 * @author Student
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		double[][] input_data = FileUtils.readLearningSetFromFile("data.txt");
		double[][] output_data = normalizeLearningSet(input_data);
		FileUtils.writeLearningSetToFile("data_out.txt", output_data);
	}

	private static double[][] normalizeLearningSet(double[][] learningSet) {
		double[][] normalizedLearningSet = new double[learningSet.length][learningSet[1].length];
		for(int i = 0; i < learningSet[1].length; i++)
		{
			//double min =  get_min_value_column(learningSet, j);
			double[] min_max = get_min_max_value_column(learningSet, i);
			double min = min_max[0];
			double max = min_max[1];
			for(int j = 0; j < learningSet.length; j++)
			{
				normalizedLearningSet[j][i] = Math.floor((learningSet[j][i] - min)/ (max - min) * 100) / 100;
			}
		}
		
		
		return normalizedLearningSet;
	}
	
	/**
	 * 
	 * @param learningSet Input array
	 * @param column Column on which we will calculate the min and max
	 * @return An array {min, max}
	 */
	private static double[] get_min_max_value_column(double[][] learningSet, int column)
	{
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for(int i = 0; i < learningSet.length; i++)
		{
			if(learningSet[i][column] > max)
			{
				max = learningSet[i][column];
			}
			if(learningSet[i][column] < min)
			{
				min = learningSet[i][column];
			}
		}
		return new double[] {min,max};
	}

}
