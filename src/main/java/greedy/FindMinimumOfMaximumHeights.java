package greedy;

import java.util.Arrays;

/*Given heights of n towers and a value k. We need to either increase or decrease the height of every tower by k (only once) where k > 0. 
The task is to minimize the difference between the heights of the longest and the shortest tower after modifications and output this difference.
Examples: 

Input  : arr[] = {1, 15, 10}, k = 6
Output :  Maximum difference is 5.
Explanation : We change 1 to 7, 15 to 
9 and 10 to 4. Maximum difference is 5
(between 4 and 9). We can't get a lower
difference.

Input : arr[] = {1, 5, 15, 10} 
        k = 3   
Output : Maximum difference is 8
arr[] = {4, 8, 12, 7}

Input : arr[] = {4, 6} 
        k = 10
Output : Maximum difference is 2
arr[] = {14, 16} OR {-6, -4}

Input : arr[] = {6, 10} 
        k = 3
Output : Maximum difference is 2
arr[] = {9, 7} 

Input : arr[] = {1, 10, 14, 14, 14, 15}
        k = 6 
Output: Maximum difference is 5
arr[] = {7, 4, 8, 8, 8, 9} 

Input : arr[] = {1, 2, 3}
        k = 2 
Output: Maximum difference is 2
arr[] = {3, 4, 5} 
*/

public class FindMinimumOfMaximumHeights {
public static void main(String[] args) {
	 int[] heightArray = {1, 5, 15, 10};
	 int k=3;
	 
	 System.out.println(getMinimumOfMaximumHeight(heightArray, k));
}

private static int getMinimumOfMaximumHeight(int[] heightArray, int k) {
	int len= heightArray.length;
	//Sort input array 
	Arrays.sort(heightArray);
	//let initial answer be difference between heights of shortest and tallest building
	int ans=heightArray[len-1] - heightArray[0];
	
	//we know that shortest building height will be incremented by k and tallest building height should be decremented in order to minimize the difference
	int shortest= heightArray[0]+k;
	int tallest= heightArray[len-1]-k;
	
	int min, max;
	
	//for each iteration, i'll increment shorter tower by k and decrease it's next tower by k so that we can minimize the height difference
	for(int i=0; i<len-1; i++) {
		min = Math.min(shortest, heightArray[i+1]-k);
		max= Math.max(tallest, heightArray[i]+k);
		
		if(min<0) {continue;}
		
		ans= Math.min(ans, max-min);
	}
		
	return ans;	
}
}
