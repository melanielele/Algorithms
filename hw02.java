import java.util.ArrayList;
//This homework is completed by Zisong Guo and Melanie Zhao without asking and consulting others except TA.



public class hw02{
	
	public static void main(String[] args) {
		
		String[] test1 = {"9","7","5","2","3","1","2","1","2"}; //maxheap
		String[] test2 = {"9","7","5","9","3","1","2","1"};// not a max heap
		String[] test3 = {"1","3","2","7","6","4","3","9"};// minheap
		String[] test4 = {null,null,null,null,null,null};//empty heap,n =0,x.length=6 
		String[] test5 = {}; //total empty heap
		String[] test6 = {"1"}; // heap with one element
		String[] test7 = {"8",null};//only one element
		String[] test8 = {"9", "7"};//maxheap with 2 elements 
		String[] test9 = {"1","4"};// minheap with 2 elements
		String[] test10 = {"9","7","5","2","3","1","2","1",null,null,null};//still have space to add elements to heap 
        String[] test11 = {"9","8","7","6",null,"4","3","2"};//with hole,which is not a heap 
        
        /* ------------------------------------------------------------
        Test cases to test all special cases. Swap the name of specific 
        test case and test1 and define n  as the number of elements used in that case. 
        Now we are testing test1 with 7 elements in it.
        eg:for testing test4, we swap the name of test4 and test1 and 
        define n as 0.
        
        NOTE: IF WE SUCCESSFULLY ADD A STRING TO THE HEAP IN ADDTOHEAP, WE NEED
        TO SET N + 1 RATHER THAN N IN THE FOLLOWING PRINTHEAP AND 
        EXTRACTFROMHEAP.
        ------------------------------------------------------------ */
        int n = 7;
        System.out.println(isMaxHeap(test1,n));
        System.out.println(isMinHeap(test1, n));
        buildMinHeap(test1, n);
        printHeap(test1, n);
        buildMaxHeap(test1, n);
        printHeap(test1, n);
        System.out.println(toTreeString(test1,n)) ;
        System.out.println(addToHeap("7",test1,n));
        printHeap(test1, n);
        System.out.println(extractFromHeap(test1, n));
        printHeap(test1, n);
        
        
      
        
        
		
	}
	
	//This method is to check if the heap is empty, if the number of element inside the array x is 0, then the heap is empty, return true;
	public static boolean isempty(String[]x, int n) {
		return n == 0;
	}
	
	//This method is to check if the heap has hole in it, go through the array to check if there is a null type in it 
	//if there is a null when we go through the array, it is not a heap 
	public static boolean hashole(String[]x, int n) {
		int hole=0;
		for(int i =0; i< n;i++) {
			if(x[i]==null) {
				hole++;
			}
		}
		if (hole!=0)
			return true;
		else
			return false;
	}
	
	//This method is to return (key(left tree)(right tree))
	public static String toTreeString(String[] x, int n) {
		return recursion(x,0,n);
	}
	
	//This method uses recursive method to print each parent, left child and right child
	//first we need to make sure the recursion stop after it go through all the element in heap
	//and then we check if this element has left child, if it has, do the same thing again to its left child
	//and then we check if this element has right child, if it has, do the same thing again to its right child
	//include the parenthesis in the recursion method
	public static String recursion(String[] x, int i, int n) {
		return "("+ ((i<n)?x[i]:"") + ((hasleftChild(i,n) == true)?recursion(x,leftChild(i),n):"") + 
				 ((hasrightChild(i,n) == true)?recursion(x,rightChild(i),n):"") +")";
		
		
	}
	
	//This method is to check if the given heap is a Minheap
	//first check if it has hole, if it has hole, it is not a heap at all
	//second check if it is empty
	//third, if we have a heap that only has 1 element, we should regard it as minheap
	public static boolean isMinHeap(String[] x, int n) {
		if(hashole(x, n)==true) {
			return false;
		}
		else if (isempty(x,n) ==true) {
			return true;//it should be true
		}
		else if (n==1) {
			return true;
		}
		else {
			int pos = 0;
			while(pos < (int)(n / 2)) {//keep track of the parent 
				if(leftChild(pos) <= n -1 ) {//make sure the child is not out of array index 
					if(x[pos].compareTo(x[leftChild(pos)]) > 0) {//if parent is bigger than children, it is not 
						return false;
					}
				}
				if(rightChild(pos) <=
						n -1 ) {
					if(x[pos].compareTo(x[rightChild(pos)]) > 0) {
						return false;
					}
				}
				pos++;
			
			}
			return true;
		}
	}
	
	/* -------------------------------------------------------
    Check if the heap is max heap. We need to check if the heap
    has a hole, or is it empty. if it has only one child,
    we regard it as minheap.
    ------------------------------------------------------- */
	public static boolean isMaxHeap(String[] x, int n) {
		if(hashole(x, n)==true) {
			return false;
		}
		else if (isempty(x,n) ==true) {
			return true;//it should be true
		}
		else if (n==1) {//when heap has only 1 element, it should be regarded as minheap 
			return true;
		} 
		else {
			int pos = 0;
			while(pos < (int)(n / 2)) {
				if(leftChild(pos) <= n -1 ) {
					if(x[pos].compareTo(x[leftChild(pos)]) < 0) {
						return false;
					}
				}
				if(rightChild(pos) <= n -1 ) {
					if(x[pos].compareTo(x[rightChild(pos)]) < 0) {
						return false;
					}
				}
				pos++;		
			}
			return true;
		}
		
		}
	
	//This method is to use minheapify to buildminheap
	//from bottom to up
	public static void buildMinHeap(String[] x, int n) {
	    if(hashole(x, n)==true) {
			System.out.println("Cannot build a MinHeap for array with hole.");
		}
	    else {
		for(int i = (int)(n / 2) - 1; i >= 0; i--) {
			MinHeapify(x, n, i);
		}
	}
   }
	
	//This method is to use minheapify to buildminheap
		//from bottom to up
	public static void buildMaxHeap(String[] x, int n) {
		 if(hashole(x, n)==true) {
				System.out.println("Cannot build a MaxHeap for array with hole.");
			}
		  else {
		for(int i = (int)(n / 2) - 1; i >= 0; i--) {
			MaxHeapify(x, n, i);
		}
	  }
	}
	
	
	//This method is to maxheapify the heap,specific comment is same as in minheapify.
	public static void MaxHeapify(String[] x, int n, int pos) {
		if(leftChild(pos) < n  && rightChild(pos) >= n) {
			 if(x[pos].compareTo(x[leftChild(pos)]) < 0) {
			    swap(x, pos, leftChild(pos));
			    MaxHeapify(x, n, leftChild(pos));
			  }
		   }
		else if(rightChild(pos) < n  && leftChild(pos) < n) {
		    if(x[leftChild(pos)].compareTo(x[rightChild(pos)]) >= 0 && x[pos].compareTo(x[leftChild(pos)]) < 0 ) {
		    	swap(x, pos, leftChild(pos));
		    	MaxHeapify(x, n, leftChild(pos));
		    }
		    if(x[leftChild(pos)].compareTo(x[rightChild(pos)]) < 0 && x[pos].compareTo(x[rightChild(pos)]) < 0 ) {
		    	swap(x, pos, rightChild(pos));
		    	MaxHeapify(x, n, rightChild(pos));
		    }
		 }
	}
	
	//This method is to minheapify the heap
	
	public static void MinHeapify(String[] x, int n, int pos) {
		if(leftChild(pos) < n  && rightChild(pos) >= n) {//if it only has left child, compare parent and left child 
			 if(x[pos].compareTo(x[leftChild(pos)]) >0) {
			    swap(x, pos, leftChild(pos));
			    MinHeapify(x, n, leftChild(pos));//call recursive method to do it to every element 
			  }
		   }
		else if(rightChild(pos) < n  && leftChild(pos) < n) {//if it has left child and right child 
		    if(x[leftChild(pos)].compareTo(x[rightChild(pos)]) <= 0 && x[pos].compareTo(x[leftChild(pos)]) > 0 ) {//left child is smaller
		    	swap(x, pos, leftChild(pos));
		    	MinHeapify(x, n, leftChild(pos));
		    }
		    if(x[leftChild(pos)].compareTo(x[rightChild(pos)]) > 0 && x[pos].compareTo(x[rightChild(pos)]) > 0 ) {//right child is smaller 
		    	swap(x, pos, rightChild(pos));
		    	MinHeapify(x, n, rightChild(pos));
		    }
		 }
		
	}
	
	//This method is to add element to heap
	//if it is a maxheap, add the element and then maxheapify it
	//if it is a minheap, add the element and then minheapify it
	//in each case, if it does not have enough space, such that n>=x.length, return false because insertion is not successful
	//if it is not a heap, return false 
	public static boolean addToHeap(String s, String[] x, int n) {
		
	//if the heap is empty n= 0 and n<x.length  we can add to heap.
		if(n==0) {
			if(n<x.length) {
				x[n]=s;
				return true;
			}
			else 
				return false;		
		}
		if(n==1) {
			if(n<x.length) {
				x[n]=s;
				buildMinHeap(x, n+1);
				return true;
			}
			else 
				return false;
		}
		
		else if(isMaxHeap(x, n) == true) {
			if(n<x.length) {
					x[n]=s;
					buildMaxHeap(x, n+1);
					return true;
			}
			else 
				return false;
		}
		else if(isMinHeap(x, n) == true) {
			if(n<x.length) {
					x[n]=s;
					buildMinHeap(x, n+1);
					return true;
			}
			else 
				return false;
		}
		else {
			return false;
	
		}
		
	}
	
	 /* ------------------------------------------------------------
    If the array is empty or has a hole, we cannot extract anything
    from it. If it is maxheap, we extract the largest one,if it is 
    minheap, we extract the smallest one. In other situations,we 
    return null.
    ------------------------------------------------------------ */
	public static String extractFromHeap(String[] x, int n) {
		if(isempty(x,n) ==true) {
			return null;
		}
		 if(hashole(x, n)==true) {
			return null;
	    }
		    
	
		if(isMaxHeap(x, n) == true) {
			String result = x[0]; // save root for later return
			swap(x, 0, n - 1); // we swap leaf to the root and delete the leaf.
			x[n - 1] = null;
			buildMaxHeap(x, n - 1);
			return result;
			
		}
		else if(isMinHeap(x, n) == true) {
			String result = x[0];
			swap(x, 0, n - 1);
			x[n - 1] = null;
			buildMinHeap(x, n - 1);
			return result;
		}
		else {
			
			return null;
		}
		
	}
		
		
	
	public static void swap(String[] x, int a1, int a2) {
		String current = x[a2];
		x[a2] = x[a1];
		x[a1] = current;
		
	}
	public static int leftChild(int a1) {
		return (a1 + 1) * 2 - 1;
	}
	
	//This method is to use the index to check if the certain element has left child 
	public static boolean hasleftChild(int a1,int n) {
		if ((a1+1)*2 -1 >n)
			return false;
		else 
			return true;
		
	}
	
	
	public static int rightChild(int a1) {
		return (a1 + 1) * 2;
	}
	
	//This method is to use the index to check if the certain element has right child 
	public static boolean hasrightChild(int a1,int n) {
		if ((a1 + 1) * 2 > n)
			return false;
		else 
			return true;
		
	}
	public static void printHeap(String[] x , int n) {
		System.out.print("{");
		for(int i = 0; i < n; i++)
			System.out.print(x[i] + " ");
		System.out.print("}");
		System.out.println("");
	}
	    
	   

}