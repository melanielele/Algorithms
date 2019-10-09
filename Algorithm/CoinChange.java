package hw10;

import java.util.ArrayList;
import java.util.Collections;

public class hw10 {
	
	public static ArrayList<Integer> coinChange1(int money, ArrayList<Integer> coins){
		int[] solution = new int[coins.size()]; // we return how many of each coin.
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(money == 0) return result;
		Collections.sort(coins); // make sure coins are in a ascending order.
		solution = exponentialAlg(money, coins);
		for(int i = 0; i < coins.size(); i++) {
			for(int j = 0; j < solution[i]; j++) {
				result.add(coins.get(i));
			}
		}
		return result;
	}
	public static int[] exponentialAlg(int money, ArrayList<Integer> coins) {
		int[] solution = new int[coins.size()];
		int leastNumberOfCoins = Integer.MAX_VALUE;
		for(int k = 0; k < coins.size(); k++) { // if we have found the money equals the money, we only need 1 coin, which must be the smallest and we can use it.
			if(coins.get(k) == money) {
				solution[k] = 1;
				return solution;
			}
		}
		for(int i = 1; i < money; i++) { // we try all combination of i and n - i coins
			int[] solution1 = exponentialAlg(money - i, coins);
			int[] solution2 = exponentialAlg(i, coins);
			int newCount = 0; // here we count the total coin of the sum of solution 1 and 2
			for(int j = 0; j < coins.size(); j++) {
				newCount += solution1[j];
				newCount += solution2[j];
			}
			if(newCount < leastNumberOfCoins) {// here if the count is less, we make the solution as arraysum of solution1 and 2
				leastNumberOfCoins = newCount;
				for(int z = 0; z < coins.size(); z++) {
					solution[z] = solution1[z] + solution2[z];
				}
			}
		}
		return solution;
	}
	public static ArrayList<Integer> coinChange3(int money, ArrayList<Integer> coins){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(money == 0) return result;
		Collections.sort(coins); // make sure coins are in a ascending order.
		boolean foundCoin = false;
		while(money > 0) { // while we still have money remained, we keep searching the maximum fit of money for the result.
			for(int i = coins.size() - 1; i >= 0; i--) {
				if(money >= coins.get(i)) {
					result.add(coins.get(i));
					money -= coins.get(i);
					foundCoin = true;
					break;
				}
			}
			if(foundCoin == false && money != 0) { // if we cannot find a coin with some money remains, it means we cannot find a solution with greedy algorithm
				result.clear();
				break;
			}
			foundCoin = false;
		}
		return result;
		
	}

	public static ArrayList<Integer> coinChange2(int money, ArrayList<Integer> coins){
		
		
		 ArrayList<Integer> result = new ArrayList<Integer>();
		 
		 if(money==0) return result ;

	    int[] dp = new int [money+1];
	    dp[0]=0; // do not need any coin to get 0 amount
	    for(int i=1;i<=money; i++)
	        dp[i]= Integer.MAX_VALUE;
	 
	    for( int j = 1; j <= money; j++){
	        for(int k = 0; k < coins.size(); k++){
	        	 if ( coins.get(k) <= j )
		             {
		                /* ------------------------
		                   Divide step
		                   ------------------------ */
		             int tmp = dp[j - coins.get(k)]; 
	                if(tmp!=Integer.MAX_VALUE &&tmp+1<dp[j]){
	                		dp[j]=tmp+1;
	                }
	            }
	        }
	    }
	    
	    int temp = money;
	    while (temp != 0) {
	     //backtrack from dp[money] to get the final results of coins
	     for (int i = 0; i < coins.size(); i++) {
	      if ((dp[money] - dp[money - coins.get(i)]) == 1) {
	       temp = money - coins.get(i);
	       result.add(coins.get(i));
	       money = temp;
	      }
	     }
	    }


	 
	    return result;
		
		
		
		
	}

	
	
	public static void main (String[] args)  {
		ArrayList<Integer> coins = new ArrayList<Integer>();
		coins.add(1);
		coins.add(5);
	    coins.add(10);
		System.out.println(coinChange2(11,coins));
		
		ArrayList<Integer> coins1 = new ArrayList<Integer>();
		coins1.add(1);
		coins1.add(2);
		coins1.add(6);
		System.out.println(coinChange3(11,coins1));
		System.out.println(coinChange1(11,coins1));
		
		
		ArrayList<Integer> coins2 = new ArrayList<Integer>();
		coins2.add(1);
		coins2.add(5);
		coins2.add(6);
		coins2.add(8);
		System.out.println(coinChange3(11,coins2));
		System.out.println(coinChange1(11,coins2));
		
		ArrayList<Integer> coins3 = new ArrayList<Integer>();
		coins1.add(2);
		coins1.add(6);
		System.out.println(coinChange3(11,coins3));
		System.out.println(coinChange1(11,coins3));
		
		
		
	}
}
