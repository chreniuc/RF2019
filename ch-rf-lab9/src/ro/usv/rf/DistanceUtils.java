/**
 * 
 */
package ro.usv.rf;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author Student
 *
 */
public class DistanceUtils {
	
	public class class_distance{
		private String class_name = null;
		private Double distance;
		public String getClass_name() {
			return class_name;
		}
		public void setClass_name(String class_name) {
			this.class_name = class_name;
		}
		public Double getDistance() {
			return distance;
		}
		public void setDistance(Double distance) {
			this.distance = distance;
		}
		public class_distance(Double distance, String class_name)
		{
			this.class_name = class_name;
			this.distance = distance;
		}
	}

	static public double getEuclidianGeneralizedDistance(String[] learningSet1, String[] learningSet2) {
		double result = 0;
		result = Math.sqrt(Math.pow((Double.valueOf(learningSet1[0]) - Double.valueOf(learningSet2[0])), 2)
			+ Math.pow((Double.valueOf(learningSet1[1]) - Double.valueOf(learningSet2[1])), 2));
		return result;
	}
	
	static public double getEuclidianDistance(double x, double y, String[] learningSet2) {
		double result = 0;
		result = Math.sqrt(Math.pow(x - Double.valueOf(learningSet2[0]), 2)
			+ Math.pow(y - Double.valueOf(learningSet2[1]), 2));
		return result;
	}
	
	static public double getEuclidianGeneralizedDistance(double []coords, String[] learningSet2) {
		double result = 0;
		for(int i = 0; i < coords.length; i++)
		{
			result += Math.pow(coords[i] - Double.valueOf(learningSet2[i]), 2);
		}
		result = Math.sqrt(result);
		return result;
	}
	
	/**
	 * 
	 * @param M - Number of classes
	 * @param learningSet
	 * @return
	 */
	static public String dynamic_kernels(int M, String[][] learningSet, double[][] distances )
	{
		// Initialize iClass array
		int []iClass = new int[learningSet.length];
		int []last_iClass = new int[learningSet.length];
		Arrays.fill(iClass, 0);
		int []kernels = new int[M];
		int nr_of_elements = learningSet.length;
		int []counteClass = new int[M]; // Counter of elements for each class
		
		// Initialize kernels
		for(int i =0 ; i < M; i++)
		{
			kernels[i] = i;
		}
		
		boolean not_done= true;
		// Fist step
		while(not_done)
		{
			for(int i = 0; i < nr_of_elements; i++)// each element
			{
				double min = Double.MAX_VALUE;
				for(int j = 0; j < kernels.length; j++) // distance from each kernel
				{	
					if(min > distances[i][kernels[j]])
					{
						min = distances[i][kernels[j]];
						iClass[i] = j; // The classes will start from 0
					}
				}
			}
			
			// new kernel
			for(int i = 0; i < M; i++) //Reinitialize counter
			{
				counteClass[i] =0;
			}
			for(int i = 0; i < iClass.length; i++) // Number of elements per class
			{
				counteClass[iClass[i]]++;
			}
			
			for(int i = 0; i < M; i++)
			{
				double x = 0; // coordonatele centrului de greutate
				double y = 0;
				double []coords = new double[learningSet[0].length]; //Generalizing for multiple features
				Arrays.fill(coords, 0);
				
				for(int j = 0; j < iClass.length; j++)
				{
					if (iClass[j] == i)
					{
						for(int k = 0; k < learningSet[0].length; i++)
						{
							coords[k] += (Double.valueOf(learningSet[j][k])*1.0)/counteClass[i];
						}
						//x += (Double.valueOf(learningSet[j][0])*1.0)/counteClass[i]; // Generalize this
						//y += (Double.valueOf(learningSet[j][1])*1.0)/counteClass[i];
					}
				}
				// We now got the coordinates of center of the class i in x and y
				// Get the closest element to the center
				int closest_element = 0;
				for(int j = 0; j < nr_of_elements; j++)// each element
				{
					double min = Double.MAX_VALUE;
					if(iClass[j] == i)
					{
						double distance = getEuclidianDistance(x,y, learningSet[j]);
						if(min > distance)
						{
							min = distance;
							closest_element = j;
						}
					}
				}
				kernels[i] = closest_element;
			}
			// Check the new iClass with the old iClass
			not_done = false;
			for(int i= 0; i < iClass.length; i++)
			{
				if(iClass[i] != last_iClass[i])
				{
					not_done = true;
				}
			}
			
			for(int i= 0; i < iClass.length; i++)
			{
				last_iClass[i] =iClass[i];
			}
		}
		
		
		for(int i = 0; i < iClass.length; i++)
		{
			System.out.println(iClass[i]);
		}
		
		return "ds";
	}
}
