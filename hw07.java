package hw07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class hw07 {
	
//	public Graph prim(String root) {
//		 prim(this.graph,root);
//		 Graph prim = new Graph();
//		 
//			 
//		
//	}
  	


	public static void prim(Graph graph, String root) {
		graph.vertices.get(root).distance=0;
		ArrayList<Vertex> xlist = new ArrayList<Vertex>();
	   	for (Map.Entry<String,Vertex> entry : graph.vertices.entrySet()) {
	   		//System.out.println(entry.getValue().label);
  	        xlist.add(entry.getValue());
		}
	   	int n = xlist.size();
	   	Vertex[] x = new Vertex[n];
	   	for (int i = 0;i<n;i++) {
	   		x[i]=xlist.get(i);
	   		//System.out.println(x[i]);
	   		
	   	}
	   //	System.out.println(xlist);
	   	ArrayList<Vertex> PriorityQueue= new ArrayList<Vertex>();
	   	PriorityQueue = buildMinHeap(x,n);
	   	//System.out.println(PriorityQueue);
		String y = toTreeString(x,n);
		System.out.println(y);
		while (n!=0) {
			Vertex u=extractFromHeap(x,n);
			System.out.println("next" + u.label);
			n--;
			PriorityQueue = buildMinHeap(x,n);
			//System.out.println(PriorityQueue);
			y = toTreeString(x,n);
			System.out.println(y);
			
			ArrayList<Edge> edges = graph.adjacent.get(u.label);
			//System.out.println(edges);
			for(Edge e : edges) {
				Vertex v = e.target;
				System.out.println(v.label);
				
				if (PriorityQueue.contains(v) && e.weight <v.distance) {
					v.parent=u;
				    System.out.println("1"+v.label);
					v.distance=e.weight;
					
				}
			}
		}
//		String y = toTreeString(x,n);
//		System.out.println(y);
		
		
		
		
		
		
		
	
	
		
		
	}
	
	
	public static Vertex extractFromHeap(Vertex[] x, int n) {
			Vertex result = x[0];
			swap(x, 0, n - 1);
			x[n - 1] = null;
			return result;
	
	}
		
	
	public static ArrayList<Vertex> buildMinHeap(Vertex[] x, int n) {
		for(int i = (int)(n / 2) - 1; i >= 0; i--) {
			MinHeapify(x, n, i);	
			//System.out.println(2);
		}
		ArrayList<Vertex> PriorityQueue= new ArrayList<Vertex>();
		for (int i=0;i<n;i++) {
			PriorityQueue.add(x[i]);
		}
		return PriorityQueue;
		
	}
	
	
	public static void MinHeapify(Vertex[] x, int n, int pos) {
	
		if(leftChild(pos) < n  && rightChild(pos) >= n) {//if it only has left child, compare parent and left child 
			 if(x[pos].distance >x[leftChild(pos)].distance) {
			    swap(x, pos, leftChild(pos));
			    MinHeapify(x, n, leftChild(pos));//call recursive method to do it to every element 
			  //  System.out.println(1);
			  }
		   }
		else if(rightChild(pos) < n  && leftChild(pos) < n) {//if it has left child and right child 
		    if(x[leftChild(pos)].distance<= x[rightChild(pos)].distance && x[pos].distance > x[leftChild(pos)].distance ) {//left child is smaller
		    	swap(x, pos, leftChild(pos));
		    	MinHeapify(x, n, leftChild(pos));
		   // 	System.out.println(3);
		    }
		    if(x[leftChild(pos)].distance> x[rightChild(pos)].distance && x[pos].distance > x[rightChild(pos)].distance)  {//right child is smaller 
		    	swap(x, pos, rightChild(pos));
		    	MinHeapify(x, n, rightChild(pos));
		   // 	System.out.println(4);
		    }
		 }
		//System.out.println(5);
		
	}
	
	public static int leftChild(int a1) {
		return (a1 + 1) * 2 - 1;
	}
	

	public static int rightChild(int a1) {
		return (a1 + 1) * 2;
	}
	
	
	public static void swap(Vertex[] x, int a1, int a2) {
		Vertex current = x[a2];
		x[a2] = x[a1];
		x[a1] = current;
		
	}
	
	public static String toTreeString(Vertex[] x, int n) {
		return recursion(x,0,n);
	}
	
	//This method uses recursive method to print each parent, left child and right child
	//first we need to make sure the recursion stop after it go through all the element in heap
	//and then we check if this element has left child, if it has, do the same thing again to its left child
	//and then we check if this element has right child, if it has, do the same thing again to its right child
	//include the parenthesis in the recursion method
	public static String recursion(Vertex[] x, int i, int n) {
		return "("+ ((i<n)?x[i]:"") + ((hasleftChild(i,n) == true)?recursion(x,leftChild(i),n):"") + 
				 ((hasrightChild(i,n) == true)?recursion(x,rightChild(i),n):"") +")";
		
		
	}
	
	public static boolean hasrightChild(int a1,int n) {
		if ((a1 + 1) * 2 > n)
			return false;
		else 
			return true;
		
	}
	
	public static boolean hasleftChild(int a1,int n) {
		if ((a1+1)*2 -1 >n)
			return false;
		else 
			return true;
		
	}
	
	
	
	
	public static void main(String[] args) {
		Graph Airline = new Graph();
		Airline.addVertex("JFK");
		Airline.addVertex("ATL");
		Airline.addVertex("SFO");
		
		Airline.addVertex("LAX");

		
		Airline.addVertex("ORD");//chicago
	
		
		//System.out.println(Airline.vertices.size());
		System.out.println();
		
		Airline.addEdge("JFK","ATL", 1946);
		Airline.addEdge("ATL","JFK", 1946);
		
		Airline.addEdge("SFO", "ATL", 2586);
		Airline.addEdge("ATL", "SFO", 2586);
		
		Airline.addEdge("ORD","JFK",945.82);
		Airline.addEdge("JFK","ORD",945.82);
		
		Airline.addEdge("LAX", "ORD",1729.87);
		Airline.addEdge("ORD", "LAX",1729.87);
		
		Airline.addEdge("ORD","SFO",1846);
		Airline.addEdge("SFO","ORD",1846);
		
		Airline.addEdge("ORD", "ATL", 591);
		Airline.addEdge("ATL", "ORD", 591);
		
		Airline.addEdge("LAX", "SFO",338);
		Airline.addEdge("SFO", "LAX",338);
		
		prim(Airline,"LAX");
		
		
		
	}

	

}

class Graph {

    static Map<String, Vertex> vertices; // include all vertices in the map
    
    Map<String, ArrayList<Edge>> adjacent; // include all edges connecting vertices
     
    List<String> searchSteps; // record the graph in DOT format of the string for each main step of the algorithm.
    
    List<String > path1; //path using the depthfirst search
    
    List<String> path2; //path using the breathfirst search
    
    List<String> topological;//path store the topological pathway
    
    
    int time;
   
    
    public Graph() {
        vertices = new TreeMap<String, Vertex>();
        adjacent = new HashMap<String, ArrayList<Edge>>();
        searchSteps = new ArrayList<String>(); // 
    }

	/* ------------------------------------------------------------
     We add a vertex by adding it to the vertices map and also initialize 
     a new edge list for it to record its potential adjacent vertices.
      ------------------------------------------------------------ */
    public void addVertex(String key) {
        Vertex v = new Vertex(key);
        vertices.put(key, v);
        adjacent.put(key, new ArrayList<Edge>());
        //System.out.println(adjacent);
    }

	/* ------------------------------------------------------------
      we add an edge including the sources and where it points to
      and its weight.
      ------------------------------------------------------------ */
    public void addEdge(String source, String target, double weight) {
        if (!vertices.containsKey(source)) {
            addVertex(source);
        }
        if (!vertices.containsKey(target)) {
            addVertex(target);
        }
        ArrayList<Edge> edges = adjacent.get(source); // we find the edge list of the source vertex
        Edge e = new Edge(vertices.get(source), vertices.get(target), weight);
        edges.add(e);
        //System.out.println(edges);
    }

    public String toString() {
        // You modify this method
        StringBuilder s = new StringBuilder();
        s.append("digraph G{" + "\n"); // in DOT format we first construct a digraph.
        for (String key : vertices.keySet()) {
            // if the color is white, we draw a vertex with dotted style
//        	if(vertices.get(key).color == 0) { 
//        		s.append( "\"" + vertices.get(key)+ "\"" +   "[ style = "+ " \"dotted\" " + "];" + "\n" );
//        		
//        	}
//        	// if the color is gray, we draw a vertex with filled gray.
//        	else if(vertices.get(key).color == 1) {
//        		s.append( "\"" + vertices.get(key)+ "\"" +   "[ style = "+ " \"filled\" " + "];" + "\n" );
//        	}
//        	// if the color is black, we draw a vertex in black.
//        	else {
//        		s.append("\"" + vertices.get(key)+ "\"" +  ";" + "\n" );
//        	}
//        	
        }
        // we draw the edges in the language of graphviz.
        for (String key : vertices.keySet()) {
            ArrayList<Edge> edges = adjacent.get(key);
            for (Edge e : edges) {
                s.append(e.source + " -> " + e.target + "[ label = " + e.weight + "];" + "\n");
                
            }
        }
        s.append("}");
        return s.toString();
    }
    
}
    
class Vertex { // this is each vertex in the graph
    
    String label; // label of the vertex
    
     
    double distance; // either 0 or infinity, reachable or not !
    
    Vertex parent; // the parent of the vertex
    
    
   
    /* ------------------------------------------------------------
   Everytime we initialize a vertex, we set its color to be white means
   it is not discovered yet. the distance to be positive-infinity since we will
   set each vertex's distance in during searching later. parent to be null,
    discoverstep and finishstep to be 0.
    ------------------------------------------------------------ */
    public Vertex(String label) {
        this.label = label;;
        distance = Double.POSITIVE_INFINITY;
        parent = null;
    }
    
    public String toString() {
        return label;
    }

}

class Edge {
    
    Vertex source; // the source of an edge
    
    Vertex target; // where the edge is pointing to
     
    double weight; // specific weight of given edge.

    public Edge(Vertex source, Vertex target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String toString() {
        return "(" + source + " -> " + target + " : " + weight + ")";
    }
    
}


