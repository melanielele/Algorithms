/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS. Melanie Zhao
*/
import java.util.Stack;

public class NQueens {
 
  //***** fill in your code here *****
  //feel free to add additional methods as necessary
	static Stack<Integer> s;
 
  //finds and prints out all solutions to the n-queens problem
  
	
  public static boolean issafe(int row, int col) {
	  for (int i = 0; i < row; i++) {
		  if ((s.get(i) == col) // they are in the same column
			 || (Math.abs(i-row) == Math.abs(s.get(i)-col))) {//in the same diagonal
			 return false;	  
		  }	
	  }
	  return true;
  }
  
  
  public static int solve(int n) {

    //***** fill in your code here ****
	 s = new Stack<Integer>();
	 int solution = 0;
	 for (int i = 0; i < n; i ++) {
         for (int j = 0; j < n; j++) {
             if (issafe(i, j)) {
                 s.push(j);
                 if(i == n-1) {
                     solution ++;
                     printSolution(s);
                     s.pop();
                 }
                 else {
                     break;
                 }
             }
             if (j >= n-1) {
                 if(s.isEmpty()) {
                     break;
                 }
                 else {
                     i--;
                     j = s.get(i);
                     s.pop();
                     while(j >= n-1) {
                         if(i == 0) {
                             return solution;
                         }
                         i--;
                         j = s.get(i);
                         s.pop();
                     }
                 }
             }
         }
     }
  	 
    
    //update the following statement to return the number of solutions found
    return solution;
    
  }//solve()
  
  //this method prints out a solution from the current stack
  //(you should not need to modify this method)
  private static void printSolution(Stack<Integer> s) {
    for (int i = 0; i < s.size(); i ++) {
      for (int j = 0; j < s.size(); j ++) {
        if (j == s.get(i))
          System.out.print("Q ");
        else
          System.out.print("* ");
      }//for
      System.out.println();
    }//for
    System.out.println();  
  }//printSolution()
  
  // ----- the main method -----
  // (you shouldn't need to change this method)
  public static void main(String[] args) {
  
  int n = 8;
  
  // pass in parameter n from command line
  if (args.length == 1) {
    n = Integer.parseInt(args[0].trim());
    if (n < 1) {
      System.out.println("Incorrect parameter");
      System.exit(-1);
    }//if   
  }//if
  
  int number = solve(n);
  System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
 }//main()
  
}
