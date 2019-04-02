/**
 * 
 */
package ro.usv.rf;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Student
 *
 */
public class DistanceUtils
{
	static public double[][] getWMatrix(double[][] learningSet, HashMap<Double, Integer> map_classes)
	{
		// Whithout the last element, which is the class name
		int nr_of_columns = learningSet[0].length - 1;
		int nr_of_classes = map_classes.size();
		System.out.println(nr_of_classes);
		double[][] w = new double[nr_of_classes+1][nr_of_columns+1]; // One column extra for the -1/2
		// One line extra because we are starting from 1 not from 0
		for(int i = 0; i < w.length; i++)
		{
			Arrays.fill(w[i], 0);
		}
		
		for (int i = 0; i < learningSet.length; i++)
		{
			for (int j =0; j < nr_of_columns; j++)
			{
				// This is not generic, if the classname is a string
				Double value_to_be_added = learningSet[i][j]/map_classes.get(learningSet[i][2]);
				Integer class_number = (int) learningSet[i][2];
				w[class_number][j] += value_to_be_added;

			}
		}
		
		for (int i = 1; i < w.length; i++)
		{
			for (int j =0; j < w[0].length-1; j++)
			{
				w[i][nr_of_columns] -= 1/2.0*(Math.pow(w[i][j], 2));
			}
		}
		
		for (int i = 1; i < w.length; i++)
		{
			for (int j =0; j < w[0].length; j++)
			{
				System.out.print(w[i][j] + " ");
			}
			System.out.println();
		}
		return w;
	}
	
	static public double getThreeClasifier(double[][] w, double[] feature)
	{
		Double min = Double.MAX_VALUE;
		int class_number = 0;
		System.out.println(w.length);
		for(int i=0; i < w.length; i++)
		{
			Double result = (double) 0;
			for(int j = 1; j < w[0].length; j++)
			{
				Double current_element = null;
				if(j >= feature.length)
				{
					current_element = (double) 1;
				}
				else
				{
					current_element = feature[j];
				}
				
				result += w[i][j]*current_element;
			}
			if(result <= min)
			{
				result = min;
				class_number = i+1; // classes start from 1
			}
		}
		System.out.println("Class = " + class_number);
		return class_number;
	}
}
