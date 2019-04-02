/**
 * 
 */
package ro.usv.rf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Student
 *
 */
public class DistanceUtils {

	static public double getEuclidianGeneralizedDistance(double[] learningSet1, String[] learningSet2) {
		double result = 0;
		result = Math.sqrt(Math.pow((learningSet1[0] - Double.valueOf(learningSet2[0])), 2)
			+ Math.pow((learningSet1[1] - Double.valueOf(learningSet2[1])), 2));
		return result;
	}
	
	static public String getNNClass(int k, double[] distances, String[][] learningSet)
	{
		ArrayList<Neighbour> Neighbours = new ArrayList<Neighbour>();
		for (int i = 0; i < learningSet.length; i++)
		{
			Neighbours.add(new Neighbour(i, distances[i], learningSet[i][learningSet[0].length- 1]));
		}

		Collections.sort(Neighbours, 
                (neighbour_1, neighbour_2) -> {
		if (neighbour_1.getValue() < neighbour_2.getValue())
			  return -1;
			else if (neighbour_1.getValue() >= neighbour_2.getValue())
			  return 1;
			else
			  return 0;
                });
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		
		int max = 0;
		String class_num = null;
		for(int i = 0 ; i < k && i < Neighbours.size(); i++)
		{
			String current_class = Neighbours.get(i).getClass_num();
			Integer cur_value = hmap.get(current_class);
			if (cur_value != null) {
			    hmap.put(current_class, cur_value+1);
			} else {
				 hmap.put(current_class, 1);
			}
			cur_value = hmap.get(current_class);
			if(max < cur_value)
			{
				max = cur_value;
				class_num = current_class;
			}
		}
		
		return class_num;
	}
	
	// Use divide etimpera for getting the max k for 90% accouracy? We already know the result, and we should see the max k that we get that result.
	// 
	// 
	
	static int get_max_knn(double[] distances, String[][] learningSet, String class_name)
	{
		int max_knn = learningSet.length;
		int left = 0;
		int right = max_knn;
		int result = 0;
		while(left+3 < right)
		{
			int mid = (left+right)/2;
			String class_name_result = DistanceUtils.getNNClass(mid, distances, learningSet);
			//System.out.println("Left: " + left + " Right: " + right +" Mid: " + mid + "   " + class_name_result);
			if( class_name_result.compareTo(class_name) == 0)
			{
				left = mid-1;
			}
			else
			{
				right = mid+1;
			}
			result = mid;
		}
		//System.out.println("Max knn = "  + result);
		
		return result;
	}
}
