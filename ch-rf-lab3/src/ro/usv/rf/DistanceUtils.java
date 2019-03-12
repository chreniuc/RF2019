/**
 * 
 */
package ro.usv.rf;

/**
 * @author Student
 *
 */
public class DistanceUtils {
	
	static public double getEuclidianDistance(double[] learningSet1, double[] learningSet2)
	{
		return Math.floor(Math.sqrt(Math.pow((learningSet1[0] - learningSet2[0]), 2) + Math.pow((learningSet1[1] - learningSet2[1]), 2)) * 100) / 100;
	}
	
	static public double getMahalanobis(double[] learningSet1, double[] learningSet2, int length)
	{
		double result = 0;
		for(int i = 0 ; i < learningSet1.length; i++)
		{
			result += Math.pow(learningSet1[i] - learningSet2[i], length);
		}
		result = Math.floor(Math.pow(result, 1.0/length) * 100) / 100;
		return result;
	}
	
	static public double getCebisev(double[] learningSet1, double[] learningSet2)
	{
		double result = 0;
		for(int i = 0 ; i < learningSet1.length; i++)
		{
			double result_temp = Math.abs(learningSet1[i] - learningSet2[i]);
			if(result < result_temp)
			{
				result = result_temp;
			}
		}
		return result;
	}
	
	static public double getCityBlock(double[] learningSet1, double[] learningSet2)
	{
		double result = 0;
		for(int i = 0 ; i < learningSet1.length; i++)
		{
			result += Math.abs(learningSet1[i] - learningSet2[i]);
		}
		return result;
	}
	
	static public Distances getAllDistances(double[] learningSet1, double[] learningSet2, int length)
	{
		Distances result = new Distances();
		
		// EuclidianDistance
		result.setEuclidian(getEuclidianDistance(learningSet1, learningSet2));
		for(int i = 0 ; i < learningSet1.length; i++)
		{
			// Mahalanobis
			result.setMahalanobis(result.getMahalanobis() + Math.pow(learningSet1[i] - learningSet2[i], length));
			// Cebisev
			double temp_abs =  Math.abs(learningSet1[i] - learningSet2[i]);
			if(result.getCebisev() < temp_abs)
			{
				result.setCebisev(temp_abs);
			}
			// CityBlock
			result.setCityBlock(result.getCityBlock() + temp_abs);
		}
		
		result.setMahalanobis(Math.floor(Math.pow(result.getMahalanobis(), 1.0/length) * 100) / 100);
		return result;
	}
	

}
