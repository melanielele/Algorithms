import java.security.SecureRandom;
import java.util.Stack;

/* ------------------------------------------------------------
This homework is completed by Zisong Guo and Melanie Zhao without asking and consulting others except TA.
    Collaboration statement:
    https://www.geeksforgeeks.org/iterative-quick-sort/
    https://algs4.cs.princeton.edu/22mergesort/
------------------------------------------------------------ */
public class hw03 {
	
	public static void main(String[] args) {
		/*
		String[] test2 = {"9","7","5","9","3","1","2","1"};
		System.out.println("The result of heapsort");
		print(test2,8);
		heapSort(test2,8,true);//should be high to low
		print(test2,8);
		heapSort(test2,8,false);//should be low to high
		print(test2,8);
		
		String[] test3 = {"1","3","2","7","6","4","3","9"};
		System.out.println("The result of heapsort");
		print(test3,8);
		heapSort(test3,8,true);//should be high to low
		print(test3,8);
		heapSort(test3,8,false);//should be low to high
		print(test3,8);
		
		String[] test4 = {"1","9","3","2","7","3","4","6"};
		System.out.println("The result of mergesort2");
		print(test4,8);
		mergeSort2(test4,8,true);//should be high to low
		print(test4,8);
		mergeSort2(test4,8,false);//should be low to high
		print(test4,8);
		
		String[] test5 = {"7","4","1","3","8","4","9","2"};
		System.out.println("The result of mergesort2");
		print(test5,8);
		mergeSort2(test5,8,true);//should be high to low
		print(test5,8);
		mergeSort2(test5,8,false);//should be low to high
		print(test5,8);
		
		String[] test6 = {"2","1","1","2","7","4","9","6"};
		System.out.println("The result of mergesort1");
		print(test6,8);
		mergeSort1(test6,8,true);//should be high to low
		print(test6,8);
		mergeSort1(test6,8,false);//should be low to high
		print(test6,8);
		
		String[] test7 = {"3","5","2","1","6","2","3","9"};
		System.out.println("The result of mergesort1");
		print(test7,8);
		mergeSort1(test7,8,true);//should be high to low
		print(test7,8);
		mergeSort1(test7,8,false);//should be low to high
		print(test7,8);
		
		
		String[] test6 = {null, null, null, null, null};
		System.out.println("The result of mergesort1");
		print(test6,5);
		mergeSort1(test6,5,true);//should be high to low
		print(test6,5);
		mergeSort1(test6,5,false);//should be low to high
		print(test6,5);
		*/
		
//		String[] test5 = {"7","4","1","3","8","4","9","2"};
//		System.out.println("The result of mergesort2");
//		print(test5,8);
//		mergeSort2(test5,8,true);//should be high to low
//		print(test5,8);
//		mergeSort2(test5,8,false);//should be low to high
//		print(test5,8);
		
		System.out.println("Total time for QuickSort");
		System.out.println(measureQuickSort(250));
		System.out.println("Total time for SelectionSort");
		System.out.println(measureSelectionSort(250));
		System.out.println("Total time for HeapSort");
		System.out.println(measureHeapSort(250));
		System.out.println("Total time for MergeSort1");
		System.out.println(measureMergeSort1(250));
		System.out.println("Total time for MergeSort2");
		System.out.println(measureMergeSort2(250));
	}
	
	
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
	
	public static void swap(String[] x, int a1, int a2) {
		String current = x[a2];
		x[a2] = x[a1];
		x[a1] = current;
		
	}
	
	public static int leftChild(int a1) {
		return (a1 + 1) * 2 - 1;
	}
	
	public static int rightChild(int a1) {
		return (a1 + 1) * 2;
	}
	
	public static void buildMaxHeap(String[] x, int n) {
		for(int i = (int)(n / 2) - 1; i >= 0; i--) {
			MaxHeapify(x, n, i);
		} 
	}
	
	public static void buildMinHeap(String[] x, int n) {
		for(int i = (int)(n / 2) - 1; i >= 0; i--) {
			MinHeapify(x, n, i);
		}
   }
	
	//use the method we wrote last week to implement heapSort
	//if it is not descending,we build max heap first and then from the last element to the first one, we swap it with the biggest one
	//to get it ordered from low to high, each time we use maxheapify to maintain the property of the first one is the biggest
	//the same thing for descending = true
	public static void heapSort(String[] x, int n, boolean descending) {
		if(descending==false) {
			buildMaxHeap(x,n);
			for (int i = n-1; i>=1;i--) {
				swap(x,0,i);
				n=n-1;
				MaxHeapify(x, n, 0);
			}
		}
		else {
			buildMinHeap(x,n);
			for (int i = n-1; i>=1;i--) {
				swap(x,0,i);
				n=n-1;
				MinHeapify(x, n, 0);
			}
		}
	}
	
	//merge from low to high, we need to first have an auxiliary array to copy the original order
	//use lo mid hi to distinguish two groups of strings, 
	//if second part of the string is smaller than the first part, than it should be placed in the first part
	 private static void merge1(String[] a, String[] aux, int lo, int mid, int hi) {//from low to high merge

	        // copy to aux[]
	        for (int k = lo; k <= hi; k++) {
	            aux[k] = a[k]; 
	        }

	        // merge back to a[]
	        int i = lo, j = mid+1;
	        for (int k = lo; k <= hi; k++) {
	            if      (i > mid)           a[k] = aux[j++];
	            else if (j > hi)            a[k] = aux[i++];
	            else if (aux[j].compareTo(aux[i])<0) 	a[k] = aux[j++];
	            else                        a[k] = aux[i++];
	        }
	    }
	 
	 //follow the same procedure as I described in the merge1 method, only change a sign because this is high to low 
	 private static void merge2(String[] a, String[] aux, int lo, int mid, int hi) {//from high to low merge

	        // copy to aux[]
	        for (int k = lo; k <= hi; k++) {
	            aux[k] = a[k]; 
	        }

	        // merge back to a[]
	        int i = lo, j = mid+1;
	        for (int k = lo; k <= hi; k++) {
	            if      (i > mid)           a[k] = aux[j++];
	            else if (j > hi)            a[k] = aux[i++];
	            else if (aux[j].compareTo(aux[i])>0) 	a[k] = aux[j++];
	            else                        a[k] = aux[i++];
	        }
	    }
	 
	 //use recursive method to sort each part, and then return 
	 private static void sort1(String[] a, String[] aux, int lo, int hi) {//from low to high sort
	        if (hi <= lo) return;
	        int mid = lo + (hi - lo) / 2;
	        sort1(a, aux, lo, mid);
	        sort1(a, aux, mid + 1, hi);
	        merge1(a, aux, lo, mid, hi);
	    }
	 
	//use recursive method to sort each part, and then return 
	 private static void sort2(String[] a, String[] aux, int lo, int hi) {//from high to low sort
	        if (hi <= lo) return;
	        int mid = lo + (hi - lo) / 2;
	        sort2(a, aux, lo, mid);
	        sort2(a, aux, mid + 1, hi);
	        merge2(a, aux, lo, mid, hi);
	    }
	 
	 //implement the top-down algorithm
	 //each time add the part we have already sorted 
	public static void mergeSort1(String[] x, int n, boolean descending) {//top-down
		if(descending==false) {
			String[] aux = new String[n];
	        sort1(x, aux, 0, n-1);
			
		}
		else {
			String[] aux = new String[n];
	        sort2(x, aux, 0, n-1);
			
		}
		
		
		
		
		
	}
	
	//implement the bottom-up algorithm
	//change the size of merging each time by multiply 2
	//reach the end when high = n
	public static void mergeSort2(String[] x, int n, boolean descending) {//bottom-up
		if(descending==false) {
			String[] aux = new String[n];
			for (int i = 1; i < n; i *= 2 ) {       
				for (int left=0; left + i < n; left += i*2 ) {
					int high = left+2*i-1;
					if (high >= n) high = n-1;
				merge1(x, aux,left,left+i-1,high);
				}
			}
		}
		else {
			String[] aux = new String[n];
			for (int i = 1; i < n; i *= 2 ) {       
				for (int left=0; left + i < n; left += i*2 ) {
					int high = left+2*i-1;
					if (high >= n) high = n-1;
				merge2(x, aux,left,left+i-1,high );
				}
			}
			
			
		}
	}
	
	public static void selectionSort(String[] x, int n, boolean descending) {
		if(x.length == 0 || x.length == 1) {// we check the array is empty or has only one element
			return;
		}
		for(int i = 0; i < x.length; i++) {// we check if there is a hole in the array
			if(x[i] == null) {
				return;
			}
		}
		/* ------------------------------------------------------------
	      In selection sort we find the minimum and swap it to the first.
	      then find the second minimum and swap it the the second in
	      transcending sequence. Same logic applies to descending sequence.
	      ------------------------------------------------------------ */
		if(!descending == true) {
			for(int i = 0; i < n; i++) {
				int min = i; // find the slot for the minimum number except swapped ones.
				for(int j = i + 1; j < n; j++) {
					if(x[j].compareTo(x[min]) < 0) {
						min = j; // find the value of that minimum number
					}
				}
				swap(x,i,min); // put the minimum number at its slot
			
			}
		}
		else {
			for(int i = 0; i < n; i++) {
				int max = i;
				for(int j = i + 1; j < n; j++) {
					if(x[j].compareTo(x[max]) > 0) {
						max = j;
					}
				}
				swap(x,i,max);
				
			}
		}
	}
	public static void quickSort(String[] x, int n, boolean descending) {
		if(x.length == 0 || x.length == 1) {// we check the array is empty or has only one element
			return;
		}
		for(int i = 0; i < x.length; i++) {// we check if there is a hole in the array
			if(x[i] == null) {
				return;
			}
		}
	         /* ------------------------------------------------------------
		      we construct a stack saving low and high range we need to partition
		      in the future
		      ------------------------------------------------------------ */
		  Stack<Integer> range = new Stack<Integer>(); 
		  int pivot = 0;
		  range.push(n - 1);
		  range.push(0); // we first push the largest range, 0 and n-1 to the stack.
		  while(!range.isEmpty() == true) {
			  int low = range.pop();
			  int high = range.pop();
			  /* ------------------------------------------------------------
		      if we need descending sequence we use partitionDescened
		      if we need transcending sequence we use partitionTranscend
		      ------------------------------------------------------------ */
			  if(descending == true) {
			      pivot = partitionDescend(x, low, high);// we partition the range we pop from stacks.
			  }
			  else {
				  pivot = partitionTranscend(x, low, high);
			  }
			  /* ------------------------------------------------------------
		      If pivot is bigger than low, it means we still have elements
		      at left of pivot needs to be partitioned. In the same way, if pivot
		      is smaller than high, it means we still have elements at right 
		      of pivot needs to be partitioned.
		      ------------------------------------------------------------ */
			  if(pivot > low) { 
				range.push(pivot - 1);
				range.push(low);
			  }
			  if(pivot < high) {
				 range.push(high);
				 range.push(pivot + 1);
			  }
				  
			  }
		  }
		
		
		  
		  
		  
		
	/* ------------------------------------------------------------
    In partitioning transcend, we use last index as pivot of partitions is, 
    given an array and an element x of array as pivot, put x at its correct 
    position in sorted array and put all smaller elements (smaller than x) before x, 
    and put all greater elements (greater than x) after x.
    ------------------------------------------------------------ */
	public static int partitionTranscend(String[] x, int low, int high) {
		int i = low - 1;
		String pivot = x[high];
		for(int j = low; j < high; j++) {
			if(x[j].compareTo(pivot) < 0) {
			  swap(x, j, i+1);
			  i++;
			}
		}
		swap(x, high, i + 1);
		return i + 1;
	}
	
	public static int partitionDescend(String[] x, int low, int high) {
		int i = low - 1;
		String pivot = x[high];
		for(int j = low; j < high; j++) {
			if(x[j].compareTo(pivot) > 0) {
			  swap(x, j, i+1);
			  i++;
			}
		}
		swap(x, high, i + 1);
		return i + 1;
	}
	
	public static String[] randomArray(int n, int m) { // import every random string to form a randomarray
	    String[] rand = new String[n];
	    for(int i = 0; i < n; i++) {
	    	rand[i] = randomString(m);
	    }
	    return rand;
	}
	
	public static String randomString(int m) { // we use secureRandom to generate a random string
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(m);
		   for( int j = 0; j < m; j++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		  return sb.toString();	
	}
	
	
	/* ------------------------------------------------------------
    We run 1000 times sorting and only calculate the time of sorting 
    excluding the time building random array. return that total 
    number.
    ------------------------------------------------------------ */
	public static double measureHeapSort(int n) {
		double total = 0;
		for(int i = 0; i < 1000; i++) {
		   String[] test = randomArray(n, 5);
		   double startTime = System.currentTimeMillis();
	       heapSort(test,n, true);
	       total += System.currentTimeMillis() - startTime;
	    }
		return total;
	    
	}
	
	public static double measureMergeSort1(int n) {
		double total = 0;
		for(int i = 0; i < 1000; i++) {
		   String[] test = randomArray(n, 5);
		   double startTime = System.currentTimeMillis();
		   mergeSort1(test,n, true);
	       total += System.currentTimeMillis() - startTime;
	    }
		return total;
	    
	}
	
	public static double measureMergeSort2(int n) {
		double total = 0;
		for(int i = 0; i < 1000; i++) {
		   String[] test = randomArray(n, 5);
		   double startTime = System.currentTimeMillis();
		   mergeSort2(test,n, true);
	       total += System.currentTimeMillis() - startTime;
	    }
		return total;
	    
	}
	
	public static double measureSelectionSort(int n) {
		double total = 0;
		for(int i = 0; i < 1000; i++) {
		   String[] test = randomArray(n, 5);
		   double startTime = System.currentTimeMillis();
	       selectionSort(test,n, true);
	       total += System.currentTimeMillis() - startTime;
	    }
		return total;
	    
	}
	
	public static double measureQuickSort(int n) {
		double total = 0;
		for(int i = 0; i < 1000; i++) {
		   String[] test = randomArray(n, 5);
		   double startTime = System.currentTimeMillis();
	       quickSort(test,n, true);
	       total += System.currentTimeMillis() - startTime;
	    }
		return total;
	    
	}
	
	public static void print(String[] x , int n) {
		System.out.print("{");
		for(int i = 0; i < n; i++)
			System.out.print(x[i] + " ");
		System.out.print("}");
		System.out.println("");
	}
	
}
