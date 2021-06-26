package greedy;

import java.util.Arrays;
import java.util.Comparator;

//Given weights and values of n items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
//In Fractional Knapsack, we can break items for maximizing the total value of knapsack. This problem in which we can break an item is also called the fractional knapsack problem. 
//Input: 
//Items as (value, weight) pairs 
//arr[] = {{60, 10}, {100, 20}, {120, 30}} 
//Knapsack Capacity, W = 50; 
//
//Output: 
//Maximum possible value = 240 
//by taking items of weight 10 and 20 kg and 2/3 fraction 
//of 30 kg. Hence total price will be 60+100+(2/3)(120) = 240

public class KnapSackFractionalProblem {

	public static void main(String[] args) {
		int[] values = {60,100,120};
		int[] weights = {10,20,30};
		
		int capacity= 50;
		
		findMaxPossibleValue(values, weights, capacity);
	}

	private static void findMaxPossibleValue(int[] values, int[] weights, int capacity) {
		int itemListLen= values.length;
		//Mapping input arrays to List of Items
		Item[] itemList = new Item[itemListLen];
		for(int i=0; i<itemListLen; i++) {
			itemList[i] = new Item(values[i], weights[i]);
		}
		
		//sorting the List of Items based on cost
		Arrays.sort(itemList, new Comparator<Item>() {

			public int compare(Item o1, Item o2) {
				if(o2.getCost()>o1.getCost()) {
					return 1;
				}
				if(o2.getCost()<o1.getCost()) {
					return -1;
				}else
					return 0;
			}
			
		});
		
		int maxValue=0;
		
		//adding item value to maxValue from sorted list
		for(Item currentItem: itemList) {
			if(currentItem.getWeight()<=capacity) {
				maxValue= maxValue+ currentItem.getValue();
				capacity = capacity- currentItem.getWeight();
			}else {
				if(capacity>0) {
					maxValue = maxValue+ capacity*currentItem.getValue()/currentItem.getWeight();
				}
			}
		}
		
		System.out.println(maxValue);
	}
	
	static class Item{
		int value;
		int weight;
		double cost;
		
		Item(int value, int weight){
			this.value= value;
			this.weight= weight;
			this.cost= value/weight;
		}
		
		int getValue() {
			return this.value;
		}
				
		int getWeight() {
			return this.weight;
		} 
		
		double getCost() {
			return this.cost;
		}
	}
}
