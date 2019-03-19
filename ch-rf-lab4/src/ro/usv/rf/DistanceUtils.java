/**
 * 
 */
package ro.usv.rf;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Student
 *
 */
public class DistanceUtils {

	static public double getEuclidianGeneralizedDistance(double[] learningSet1, double[] learningSet2) {
		double result = 0;

		for (int i = 0; i < learningSet1.length - 1; i++)
		{
			result += Math.pow((learningSet1[i] - learningSet2[i]), 2);
		}
		return Math.floor(Math.sqrt(result) * 100) / 100;
	}

	static public int getClosestNeighbour(int paternNumber, double[] distances) {
		double minValue = distances[0];
		int closestNeighbour = 0;
		for (int i = 0; i < distances.length; i++) {
			if (distances[i] < minValue && i != paternNumber)
			{
				minValue = distances[i];
				closestNeighbour = i;
			}
		}
		return closestNeighbour;
	}
	
	static public int get3NNClass(int paternNumber, double[] distances, double[][] learningSet)
	{
		ArrayList<Neighbour> Neighbours = new ArrayList<Neighbour>();
		for (int i = 0; i < distances.length; i++)
		{
			if(i != paternNumber)
			{
			   Neighbours.add(new Neighbour(i, distances[i], learningSet[i][learningSet.length- 1]));
			}
		}
		Collections.sort(Neighbours, 
                (neighbour_1, neighbour_2) -> (int) Math.round(neighbour_1.getValue() - neighbour_2.getValue()));
		
		for(int i =0; i < Neighbours.size(); i++)
		{
			
			System.out.println(Neighbours.get(i).getValue() + " " + Neighbours.get(i).getPosition() + " " + Neighbours.get(i).getClass_num());
		}
		
		int number_of_classes = 3;
		int[] frecvency_vector = new int[number_of_classes]; // Number of classes
		
		for(int i =0 ; i < number_of_classes; i++)
		{
			frecvency_vector[i] = 0;
		}
		
		int max = 0;
		int class_num = 0;
		for(int i =0 ; i < 3 && i < Neighbours.size(); i++)
		{
			int position = (int)Math.round(Neighbours.get(i).getClass_num());
			frecvency_vector[position]++;
			if(max < frecvency_vector[position])
			{
				max = frecvency_vector[position];
				class_num = (int)Math.round(Neighbours.get(i).getClass_num());
			}
		}
		
		System.out.println("Closest neighbour using 3-nn has class: " + class_num);
		return 1;
	}
	
}
