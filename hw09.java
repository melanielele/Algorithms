package hw09;
import java.util.*;
/* ------------------------------------------------------------
This homework is completed by Zisong Guo and Melanie Zhao without asking and consulting others except TA.
   */
public class hw09 {
	
	private static final int NEITHER     = 0;
	private static final int UP          = 1;
	private static final int LEFT        = 2;
	private static final int DIAG = 3;
	
	public static double rodCut1(int length, List<Double> Prices,  List<Integer> resultCuts) {
		int[] cutStep = new int[length + 1]; // the cutStep is how we trace the resultCut when we print the solution.
		double result = directRecursion(length, Prices, cutStep);
		printSolution(length, cutStep, resultCuts);
		return result;
		
	}
	public static double directRecursion(int length, List<Double> Prices, int[] cutStep) {
    	if(length == 0) {// if length equals n, we have non-profit.
    		return 0;
    	}
    	double result = Double.NEGATIVE_INFINITY; // firstly we set result to be negative infinity
    	cutStep[0] = 0; // in length 0 the step we trace back is also 0
    	int tempCut = 0;  //tempCut is which step we are going to trace back
    	for(int i = 1; i <= length; i++) {
    		double temp = Double.NEGATIVE_INFINITY;
    		if(i < Prices.size()) { // for every step we find the max price we can divide the total length
    		   temp = Prices.get(i) + directRecursion(length - i, Prices, cutStep);
    		}
    			if(temp > result) {
    			result = temp;
    		    tempCut = i; // as we find the max, we record its step to trace back
    	    }
    	}
    	cutStep[length] = tempCut;
    	return result;
   }
    public static void printSolution(int length, int[] cutStep, List<Integer> resultCuts) {
    	int n = length;
    	while(n > 0) { // using cutStep we trace back each step for the resultCuts
    	  resultCuts.add(cutStep[n]);
    	  n = n - cutStep[n];
    	}
    }
    public static double rodCut2(int length, List<Double> Prices, List<Integer> resultCuts) {
    	double[] savedProfit = new double[length + 1]; // we use this array to save all maximum profit we have already find for smaller length.
    	savedProfit[0] = 0.0;
    	for(int i = 0; i < savedProfit.length; i++) {
    		savedProfit[i] = Double.NEGATIVE_INFINITY;
    	}
    	int[] cutStep = new int[length + 1]; // the cutStep is how we trace the resultCut when we print the solution.
    	cutStep[0] = 0;
    	double result = topDownMemoization(length, Prices, cutStep, savedProfit);
    	printSolution(length, cutStep, resultCuts);
    	return result;
    }
    public static double topDownMemoization(int length, List<Double> Prices, int[] cutStep, double[] savedProfit) {
    	if(savedProfit[length] >= 0) {
    		return savedProfit[length];
    	}
    	if(length == 0) {
    		return 0;
    	}
    	double result = Double.NEGATIVE_INFINITY;
    	int tempCut = 0;
    	for(int i = 1; i <= length; i++) {
    		double temp = Double.NEGATIVE_INFINITY;
    		if(i < Prices.size()) { // for every step we find the max price we can divide the total length
     		   temp = Prices.get(i) + topDownMemoization(length - i, Prices, cutStep, savedProfit);
     		}
     			if(temp > result) {
     			result = temp;
     		    tempCut = i; // as we find the max, we record its step to trace back
     	    }
    	}
    	cutStep[length] = tempCut;
    	savedProfit[length] = result;
    	return result;
    }
    public static double rodCut3(int length, List<Double> Prices, List<Integer> resultCuts) {
    	double[] savedProfit = new double[length + 1]; // we use this array to save all maximum profit we have already find for smaller length.
    	savedProfit[0] = 0.0;
    	int[] cutStep = new int[length + 1]; // the cutStep is how we trace the resultCut when we print the solution.
    	cutStep[0] = 0;
    	double result = bottomUp(length, Prices, cutStep, savedProfit);
    	printSolution(length, cutStep, resultCuts);
    	return result;
    }
    public static double bottomUp(int length, List<Double> Prices, int[] cutStep, double[] savedProfit) {
       for(int j = 1; j <= length; j++) { // we find the savedprofit from 1 to the length we need
    		double result = Double.NEGATIVE_INFINITY;
    		int tempCut = 0;
    		for(int i = 1; i <= j; i++) { //we find the max profit for current j.
    			double temp = Double.NEGATIVE_INFINITY;
        		if(i < Prices.size()) { // for every step we find the max price we can divide the total length
         		   temp = Prices.get(i) + savedProfit[j - i];
         		   
         		}
         			if(temp > result) {
         			result = temp;
         		    tempCut = i; // as we find the max, we record its step to trace back
         	    }
        	}
        	cutStep[j] = tempCut;
        	savedProfit[j] = result;
    		}
    	 return savedProfit[length];
    	}
    
    public static void main(String[] args) {
    	ArrayList<Double> Prices = new ArrayList<Double> ();
    	Prices.addAll(Arrays.asList(0.0, 1.0, 5.0, 8.0, 9.0, 10.0, 17.0, 17.0, 20.0, 24.0, 30.0)); 
    	ArrayList<Integer> resultCuts = new ArrayList<Integer> ();
    	//System.out.println(rodCut1(14, Prices, resultCuts));
    	//System.out.println(rodCut2(149, Prices, resultCuts));
    	System.out.println(rodCut3(149, Prices, resultCuts));
        System.out.println(resultCuts);
        System.out.println(editDistance("duck","quack",2,3,5,null));
        
        System.out.println(longestCommonSubsequence("flesh", "shuffle"));
    	
    }
    
    

    
    public static int editDistance(String x, String y, int insertCost, int deleteCost, int replaceCost, List<String> resultOperations) {
            
        
//`         if x == y, then word[i][j] == word[i-1][j-1]  as we mentioned in the class, it is the diagonal replacement 
//          if x != y, and we insert y for word1, then word[i][j] = word[i][j-1] + 1
//          if x != y, and we delete x for word1, then word[i][j] = word[i-1][j] + 1
//          if x != y, and we replace x with y for word1, then word[i][j] = word[i-1][j-1] + 1
//          When x!=y, word[i][j] is the min of the three situations.
//          Initial condition:
//          word[i][0] = i, word[0][j] = j  , fill up the first row and column
    
        int x_length = x.length();
        int y_length = y.length();
        
        int[][] word = new int[x_length+1][y_length+1];//leave the first box to empty
        int[][] direction = new int[x_length+1][y_length+1];
        
        //fill up the first row and column 
        for (int i = 0; i < x_length+1; i++) {
            word[i][0] = i*deleteCost;
            direction[i][0]=UP;
          //  System.out.println(word[i][0]);
        }
     
        for (int j = 0; j < y_length+1; j++) {
            word[0][j] = j*insertCost;
            direction[0][j]=LEFT;
          //  System.out.println(word[0][j]);
        }
        
        for (int i = 1;i <x_length+1 ;i++) {
            for (int j = 1;j<y_length+1;j++) {
                char s1 = x.charAt(i-1);
                char s2=y.charAt(j-1);
                if (s1==s2) {
                    word[i][j] = word[i-1][j-1] ;//equal to the diagonal
                    direction[i][j]=NEITHER;
                }
                else {
                    word[i][j]=word[i-1][j-1]+replaceCost;
                    direction[i][j]=DIAG;
                    
                }
                int insert = word[i][j-1] + insertCost;
                int delete = word[i-1][j] + deleteCost; 
//              int min = delete > insert ? insert : delete;    
                word[i][j]=Math.min(word[i][j],insert);
                word[i][j]=Math.min(word[i][j],delete);
                if (word[i][j]==insert) {
                		direction[i][j]=LEFT;
                }
                else if(word[i][j]==delete) {
                		direction[i][j]=UP;
                }
                else if(word[i][j]==word[i-1][j-1]+replaceCost) {
                		direction[i][j]=DIAG;
                }
             //  System.out.println(word[i][j]);
                
            }
        }
        
       
        
        return word[x_length][y_length];    
        }
    
		
    	 
    		
    	
    
    
    public static String longestCommonSubsequence(String x, String y) {
  		int x_length = x.length();
		int y_length = y.length();
		
		int[][] word = new int[x_length+1][y_length+1];
		int[][] direction = new int[x_length+1][y_length+1];
	
		//initialzation first row and column are all 0 
		for (int i =0;i<x_length+1;i++) {
			word[i][0] = 0;
			direction[i][0]=UP;
		}
		
		for (int j = 0; j <y_length+1;j++) {
			word[0][j]=0;
			direction[0][j]=LEFT;
		}

		for (int i = 0;i <x_length;i++) {
			char s1 = x.charAt(i);
			for (int j = 0;j<y_length;j++) {
				char s2=y.charAt(j);
				
				//if they are the same, then add one 
				//direction is from upandleft 
				if (s1==s2) {
					word[i + 1][j + 1] = word[i][j] + 1;//equal to the diagonal
					direction[i+1][j+1]=DIAG;
					
				}
				
				else{
					word[i + 1][j + 1] = word[i][j] + 0;
					direction[i+1][j+1]=NEITHER;
					
				}
				
				if(word[i][j+1]>=word[i+1][j+1]) {
					word[i + 1][j + 1] = word[i][j+1];
					direction[i+1][j+1]=UP;
				}
				
				if (word[i+1][j]>=word[i+1][j+1]) {
					word[i+1][j+1]=word[i+1][j];
					direction[i+1][j+1]=LEFT;
				}
				
				
			}
			
		}
		int m = x_length;
		int n = y_length;
		int pos = word[m][n]-1;
		char sequence[] = new char[pos+1];
		
		//backtrack the arrays
		
		while (m>0||n>0) {
			if (direction[m][n]==DIAG) {//upand left means they are the same, we catch that char 
				m--;
				n--;
				sequence[pos--]=x.charAt(m);
			}
			
			else if (direction[m][n]==UP) {//up or left means we did not find common, 
				m--;
			}
			
			else if (direction[m][n]==LEFT) {
				n--;
			}
		}
		String lcs = new String(sequence);
		return lcs;
		
    }
    
}
