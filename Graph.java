/* ------------------------------------------------------------
This homework is completed by Zisong Guo and Melanie Zhao without asking and consulting others except TA.
    Collaboration statement:
    http://www.graphviz.org
------------------------------------------------------------ */
import java.util.*;

public class Graph {

    static Map<String, Vertex> vertices; // include all vertices in the map
    
    static Map<String, ArrayList<Edge>> adjacent; // include all edges connecting vertices
     
    List<String> searchSteps; // record the graph in DOT format of the string for each main step of the algorithm.
    
    List<String > path1; //path using the depthfirst search
    
    List<String> path2; //path using the breathfirst search
    
    List<String> topological;//path store the topological pathway
    
    static double totalweight;
    
    
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
    }

//    public String toString() {
//        // You modify this method
//        StringBuilder s = new StringBuilder();
//        s.append("digraph G{" + "\n"); // in DOT format we first construct a digraph.
//        for (String key : vertices.keySet()) {
//            // if the color is white, we draw a vertex with dotted style
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
//        }
//        // we draw the edges in the language of graphviz.
//        for (String key : vertices.keySet()) {
//            ArrayList<Edge> edges = adjacent.get(key);
//            for (Edge e : edges) {
//                s.append(e.source + " -> " + e.target + "[ label = " + e.weight + "];" + "\n");
//                
//            }
//        }
//        s.append("}");
//        return s.toString();
//    }
    
//    public void breadthFirstSearch(String startVertex) {
//        // You implement this method
//    		
//    	   if (searchSteps.size()!=0) {
//    		   searchSteps.clear();
//    	   }
//       searchSteps.add(this.toString()); // after initialization, we catch a snapshot of graph
//       
//       if(vertices.get(startVertex) == null) { // if we cannot find startvertex, we return.
//    	   return;
//       }
//       //we turn first to be gray, means it has been discovered.
//       path2 = new ArrayList<String>();
//       Vertex first = vertices.get(startVertex);
//       first.color = 1;
//       first.discoverStep = searchSteps.size();
//       path2.add(first.label);
//       first.distance = 0;
//       first.parent = null;
//    
//      
//       searchSteps.add(this.toString()); // we catch a snapshot for the first turning gray.
//      
//       // we add first into the queue.
//       Queue<Vertex> pos = new ArrayDeque<Vertex>();
//       pos.add(first);
//       while(!pos.isEmpty()) {
//    	  Vertex u = pos.remove();
//    	  ArrayList<Edge> edges = adjacent.get(u.label);
//    	  
//    	  for(Edge e : edges) { 
//    		  // if u has a adjacent vertex which has been undiscovered, we discover them.
//    		  if(e.target.color == 0) {
//    			  Vertex v = e.target;
//    			  v.color = 1;
//    			  v.discoverStep = searchSteps.size();
//    			  path2.add(v.label);
//    			  searchSteps.add(this.toString());
//    			  v.distance = u.distance + e.weight;
//    			  v.parent = u;
//    			  pos.add(v);
//    		  }
//    	  }
//    	  // if all adjacent vertex of u has been discovered, u is finish we turn it to black.
//    	  u.color = 2;
//    	  u.finishStep = searchSteps.size();
// 
//    	  
//
//    	  searchSteps.add(this.toString());
//    	  
//       }
//       for(String s : searchSteps) {
//    	   System.out.println(s);
//       }
//    }
    
//    public void depthFirstSearch() {
//        // You implement this method
//
//    //	searchSteps.add(this.toString());//after initiliazation, take a snapshot
//    	
//    	
//    	 if(vertices.isEmpty()) { // if we do not have any vertex in it, return 
//      	   return;
//         }
//    	 
//    	 if (searchSteps.size()!=0) {
//  		   searchSteps.clear();
//  	   }
//    	 
//    	 time = 0;
//    	 path1 = new ArrayList<String>(); //this is the arraylist to store the pathway when we conduct a DFS
//    	 topological= new ArrayList<String>();//this is the arraylist to store the topological sort result 
//    	 searchSteps.add(this.toString());//initilization step!!
//    	 Vertex veryfirst = vertices.get(((TreeMap<String, Vertex>) vertices).firstKey());
//    	 veryfirst.distance=0.0;//set the first entry the distance = 0
//    	  for (Map.Entry<String,Vertex> entry : vertices.entrySet()) {
//    	        Vertex u = entry.getValue();
//    	        
//    	        if (u.color==0) {
//    	        		DFS_visit(u);
//    	        		//System.out.println(u.discoverStep + "/" + u.finishStep);
//    	        }
//    	   }
//    	  
//    	  
//    	  for(String s : searchSteps) {
//       	  System.out.println(s);
//          }
//    	  
//    	  
//    	
//    }
//    public void DFS_visit(Vertex u) {
//    		time = time +1 ;
//    	    //System.out.println("u" + u.label);
//    		u.discoverStep=time;
//    		path1.add(u.label);//once a vertex has been discover, we put it into the pathway 
//    		//System.out.println(u.discoverStep);
//    		u.color=1;
//    		searchSteps.add(this.toString());//after it turn gray, snapshot s
//    		ArrayList<Edge> edges = adjacent.get(u.label);
//    		//System.out.println(adjacent);
//    		for(Edge e : edges) { 
//    			if (e.target.color==0 ) {
//    				
//    				Vertex v = e.target;
//    				//System.out.println("v"+v.label);
//    				v.distance = u.distance + e.weight;
//    				v.parent=u;
//    				DFS_visit(v);		
//    			}	 
//    		 }
//    		u.color=2;
//    		time = time+1;
//    		searchSteps.add(this.toString());//change the color, we upload the graph
//    		u.finishStep=time;
//    		topological.add(u.label);//once it finish, turns to black, we add it to the arraylist in an inverse oder
//    		//System.out.println(u.finishStep);
//    		
//    		
//    		
//    		
//    }
    
    public List<String> path(String startVertex, String endVertex ) {
        // You implement this method
    	
    		if (path1!=null) {
    			List<String> pathway = new ArrayList<String>();
    			List<String> correctpathway = new ArrayList<String>();
    			System.out.println("DepthFirstSearch has been conducted");//if depthfirst, the starting vertex is the first one,
    			Vertex t = vertices.get(endVertex);
    			Vertex a = vertices.get(startVertex);
    			Vertex veryfirst = vertices.get(((TreeMap<String, Vertex>) vertices).firstKey());
    			while(t!=null&&t!=a&&t!=veryfirst) {
    				pathway.add(t.label);
    				t = t.parent;
    				//System.out.println(t.label);
    				
    			}
    			if(a.label==t.label) {
    				pathway.add(a.label);
    			}
    			else {
    				System.out.println("cannot form a path");
    				pathway.clear();
    			}
    			for(int i = pathway.size()-1; i>=0;i--) {
    				correctpathway.add(pathway.get(i));
    				
    			}
    			return correctpathway;
    		}
    		
    		if (path2!=null) {
    			List<String> pathway = new ArrayList<String>();
    			List<String> correctpathway = new ArrayList<String>();
    			System.out.println("BreathFirstSearch has been conducted");//if depthfirst, the starting vertex is the first one,
    			Vertex t = vertices.get(endVertex);
    			Vertex a = vertices.get(startVertex);
    			Vertex veryfirst = vertices.get(((TreeMap<String, Vertex>) vertices).firstKey());
    			while(t!=null&&t!=a&&t!=veryfirst) {
    				pathway.add(t.label);
    				t = t.parent;
    				//System.out.println(t.label);
    				
    			}
    			if(a.label==t.label) {
    				pathway.add(a.label);
    			}
    			else {
    				System.out.println("cannot form a path");
    				pathway.clear();
    			}
    			for(int i = pathway.size()-1; i>=0;i--) {
    				correctpathway.add(pathway.get(i));
    				
    			}
    			return correctpathway;
    		}
    		return null;
   
    }
    
    public double pathWeight(String startVertex, String endVertex) {
        // You implement this method
    		if (path1!=null) {
			System.out.println("DepthFirstSearch has been conducted, and the path weight is : ");
			List<String> pathway = this.path(startVertex, endVertex);
			if (pathway.size()!=0) {
				System.out.println("The pathway is exist");
				double weight2 = vertices.get(endVertex).distance;
				double weight1 = vertices.get(startVertex).distance;
				return weight2-weight1;	
			}
			else {
				return Double.POSITIVE_INFINITY;
			}
			
				
		}
		
		if (path2!=null) {
			System.out.println("BreathFirstSearch has been conducted");
			List<String> pathway = this.path(startVertex, endVertex);
			if (pathway.size()!=0) {
				System.out.println("The pathway is exist");
				double weight2 = vertices.get(endVertex).distance;
				double weight1 = vertices.get(startVertex).distance;
				return weight2-weight1;	
			}
			else {
				return Double.POSITIVE_INFINITY;
			}
		}
		return Double.POSITIVE_INFINITY;
    	
    	
        
    }
    
    //because we have already create an arraylist to store vertex in the increasing order of the finish time,
    //the correct order of topoligicalsort is to inverse the order, 
    //which means put the biggest finish step at front
    
//   
//    public List<String> topologicalSort() {
//      	depthFirstSearch();
//    		List<String> rightorder = new ArrayList<String>();
//    		for(int i = topological.size()-1; i >=0; i--) {
//        		rightorder.add(topological.get(i));
//        	}
//        return rightorder;
//    		
//    		
//        
//        
//    }
    
    

    
    public Graph kruskal() {
    	
    	ArrayList<Edge> edges = new ArrayList<Edge>(); // here we construct the array list containing all edges in the original graph
    	ArrayList<String> vertexName = new ArrayList<String>(); // we use this list to save all vertex names for final graph
    	ArrayList<Edge> finalEdge = new ArrayList<Edge>(); // we use this list to store minimum spanning edge for kruskal
    	// we find all edges in the graph
    	for (String key : vertices.keySet()) {
    		ArrayList<Edge> adjacentEdge = adjacent.get(key);
    		vertexName.add(key);
    		for (Edge e : adjacentEdge) {
    			edges.add(e);
             }
    	}
    	Collections.sort(edges); // we sort the edges in ascending order
    	
    	// for each edge in ascending order, we check if the source and target are in the same set.
    	// we define setparent to be the parent of target vertex and make them to same set. Then we define setSon
    	// to trace back to make sure all sons are in the same set.
    	for(Edge s: edges) {
    		if(find(s.target) != find(s.source)) {
    			finalEdge.add(s);
    			union(s.target, s.source);
    		}
    	}
    	for(Edge e: finalEdge) {
    		System.out.println(e);
    	}
    	// we construct a new graph for the result of kruskal
    	Graph result = new Graph();
    	// we add all vertexName and finalEdges into it and get the final minimum spanning tree.
    	for(String s: vertexName) {
    		result.addVertex(s);
    	}
    	for(Edge s: finalEdge) {
    		result.addEdge(s.source.label, s.target.label, s.weight);
    	}
    	
        
    	
    	
    	return result;
    }
    
    
    //find is for the disjoint data structure to find the representative of that set
    public Vertex find(Vertex x) {
    	if(x.parent == null) {
    		return x;
    	}
    	else {
    		return find(x.parent);
    	}
    }
    // union is for the disjoint data structure to union two sets
    public void union(Vertex x, Vertex y) {
    	Vertex xrep = find(x);
    	Vertex yrep = find(y);
    	xrep.parent = yrep;
    }
    public boolean bellmanFord(String source) {
    	Vertex first = vertices.get(source);
    	first.distance = 0;
    	ArrayList<Edge> edges = new ArrayList<Edge>(); // here we construct the array list containing all edges in the original graph
    	// we find all edges in the graph
    	for (String key : vertices.keySet()) {
    		ArrayList<Edge> adjacentEdge = adjacent.get(key);
    		for (Edge e : adjacentEdge) {
    			edges.add(e);
             }
    	}
    	for(int i = 0; i < vertices.size();  i++) {
    		for(Edge e: edges) {
    			// if the distance of going this edge is smaller than the target distance, we replace the distance to the 
        		  // smaller one.
        		  if(e.target.distance > e.source.distance + e.weight) {
        			  e.target.distance = e.source.distance + e.weight;
        			  e.target.parent = e.source;
        		  }
    		}
    	}
    	// we detect a negative cycle if the distance of source plus weight is smaller than the pointer.
    	for(Edge s: edges) {
    	   if(s.source.distance + s.weight < s.target.distance) {
    		   return false;
    	   }
    	}
    	for (String key : vertices.keySet()) {
        	Vertex keyVertex = vertices.get(key);
        	System.out.println("The shortest path from " + source + " to " + key + " is " + dijkstra_and_bellmanford_path(keyVertex));
        	System.out.println("The total weight of it is: " +  keyVertex.distance);
        	
        }
    	
    	return true;
    }
    public boolean dijkstra(String source) {
    	// first we check all the edges in the graph, if we have negative edges, return false.
    	for (String key : vertices.keySet()) {
    		ArrayList<Edge> adjacentEdge = adjacent.get(key);
    		for (Edge e : adjacentEdge) {
    			if(e.weight < 0) {
    				return false; // since we don't allow negative edge in dijkstra
    			}
             }
    	}
    	// we check if the source vertex is not in the graph
    	if(!vertices.containsKey(source)) {
    		return true;
    	}
        Vertex first = vertices.get(source);
    	first.color = 1;
    	first.distance = 0;
    	// we construct a queue to make sure every vertex is visited.
    	Queue<Vertex> pos = new ArrayDeque<Vertex>();
        pos.add(first);
        while(!pos.isEmpty()) {
          Vertex u = pos.remove();
      	  ArrayList<Edge> edges = adjacent.get(u.label);
      	  
      	  for(Edge e : edges) { 
      		  // if u has a adjacent vertex which has been undiscovered, we discover them.
      		  // if the distance of going this edge is smaller than the target distance, we replace the distance to the 
      		  // smaller one.
      		  if(e.target.distance > e.source.distance + e.weight) {
      			  e.target.distance = e.source.distance + e.weight;
      			  e.target.parent = e.source;
      			// if we update any distance of "done" black vertex, we need to do that vertex again.
          		  if(e.target.color == 2) {
          			 e.target.color = 1;
          			 pos.add(e.target);
          		  }
      		  }
      		  
      		  // if the target is 0, we will explore the vertex in the future.
      		  if(e.target.color == 0) {
      			e.target.color = 1;
      			pos.add(e.target);
      		  }
      	  }
      	  u.color = 2;
      	  
     }
        for (String key : vertices.keySet()) {
        	Vertex keyVertex = vertices.get(key);
        	System.out.println("The shortest path from " + source + " to " + key + " is " + dijkstra_and_bellmanford_path(keyVertex));
        	System.out.println("The total weight of it is: " +  keyVertex.distance);
        	
        }
    	return true;
    }
    // use this method to create the path for each node's shortest path to its source.
    public String dijkstra_and_bellmanford_path(Vertex x) {
    	 StringBuilder s = new StringBuilder();
    	 ArrayList<String> vertexName = new ArrayList<String>();
    	 Queue<Vertex> pos1 = new ArrayDeque<Vertex>();
    	 pos1.add(x);
    	 
    	for(Vertex i = x; i != null; i = i.parent) {
    		 vertexName.add(i.label);
    	 }
    	 Collections.reverse(vertexName);
    	 for(String k: vertexName) {
    		 s.append(k + " -----> ");
    	 }
    	 s.setLength(s.length() - 8);
    	 String result = s.toString();
    	 
    	 return result;
    	 
    }
    
//    public static Graph prim(String root) {
//    	Graph graph2 = new Graph();
//    	this.prim1(root);
//   // 	graph2.vertices = vertices;
//    for (Map.Entry<String,Vertex> entry : vertices.entrySet()) {
//	   		//System.out.println(entry.getValue().label);
//  	   graph2.addVertex(entry.getValue().label);
//	}
//    
//     System.out.println(graph2.vertices.get("LAX").distance);
//    
//    
//    
//    return graph2;
//    	
//    		
//    	
//    }
    public double totalWeight() {
    		return totalweight;
    }

	public static Graph prim(String root) {
		//System.out.println(vertices);
		vertices.get(root).distance=0;
		ArrayList<Vertex> xlist = new ArrayList<Vertex>();
	   	for (Map.Entry<String,Vertex> entry : vertices.entrySet()) {
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
		//String y = toTreeString(x,n);
		//System.out.println(y);
		while (n!=0) {
			Vertex u=extractFromHeap(x,n);
			//System.out.println("next" + u.label);
			n--;
			PriorityQueue = buildMinHeap(x,n);
			//System.out.println(PriorityQueue);
		//	y = toTreeString(x,n);
			//System.out.println(y);
			
			ArrayList<Edge> edges = adjacent.get(u.label);
			//System.out.println(edges);
			
			for(Edge e : edges) {
				Vertex v = e.target;
				//System.out.println(v.label);
				
				if (PriorityQueue.contains(v) && e.weight <v.distance) {
					v.parent=u;
				    //System.out.println("1"+v.label);
					v.distance=e.weight;
					
					
				}
			}
			
			
		}
		totalweight = 0;
//		System.out.println(vertices.get("LAX").distance);
//	 	System.out.println(vertices.get("ORD").distance);
		for (Map.Entry<String,Vertex> entry : vertices.entrySet()) {
			totalweight = totalweight+entry.getValue().distance;
		}
		//System.out.println(totalweight);
		
    		ArrayList<Edge> finalEdge = new ArrayList<Edge>(); // we use this list to store minimum spanning edge for prim
		for (Map.Entry<String,Vertex> entry : vertices.entrySet()) {
			ArrayList<Edge> adjacentEdge = adjacent.get(entry.getValue().label);
			double needvalue = entry.getValue().distance;
			for (Edge e:adjacentEdge) {
				if (e.weight==needvalue){
					finalEdge.add(e);
				}
			}
		}
		
		//System.out.println(finalEdge);
		
		
		
		ArrayList<String> vertexName = new ArrayList<String>();//use this list to save all vertex names for final graph
		for (Map.Entry<String,Vertex> entry : vertices.entrySet()) {
			vertexName.add(entry.getValue().label);
		}
		
		
		Graph graph2 = new Graph();
		
		
	 	for(String s:vertexName) {
	 		graph2.addVertex(s);
	 	}
	 	
	 	for(Edge s: finalEdge) {
    		graph2.addEdge(s.source.label, s.target.label, s.weight);
	 	}
	 	
//	 	System.out.println(graph2.vertices);
//	 	System.out.println(graph2.adjacent);
	 	
		
		return graph2;
		
		
		
		
		
		
		
	
	
		
		
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
		
		prim("LAX");
		System.out.println(Airline.totalWeight());
		
//		
//		Graph test_bellmanford = new Graph();
//	    	test_bellmanford.addVertex("A");
//	        test_bellmanford.addVertex("B");
//	    	test_bellmanford.addVertex("C");
//	    	test_bellmanford.addVertex("D");
//	    	test_bellmanford.addVertex("E");
//	    	test_bellmanford.addVertex("S");
//	    	
//	    	test_bellmanford.addEdge("E", "D", 1);
//	    	test_bellmanford.addEdge("D", "C", -1);
//	    	test_bellmanford.addEdge("D", "A", -4);
//	    	test_bellmanford.addEdge("C", "B", -2);
//	    	test_bellmanford.addEdge("A", "C", 2);
//	    	test_bellmanford.addEdge("B", "A", 1);
//	    	test_bellmanford.addEdge("S", "A", 10);
//	    	test_bellmanford.addEdge("S", "E", 8);
//	    	
//	    	System.out.println(test_bellmanford.bellmanFord("S"));
	    	
	    	/*
	    	Graph test_kruskal = new Graph();
	    	test_kruskal.addVertex("A");
	    	test_kruskal.addVertex("B");
	    	test_kruskal.addVertex("C");
	    	test_kruskal.addVertex("D");
	    	test_kruskal.addVertex("E");
	    	test_kruskal.addVertex("F");
	    	test_kruskal.addVertex("G");
	    	
	    	
	    	test_kruskal.addEdge("A", "B", 2);
	    	test_kruskal.addEdge("A", "C", 3);
	    	test_kruskal.addEdge("A", "D", 3);
	    	test_kruskal.addEdge("B", "C", 4);
	    	test_kruskal.addEdge("B", "E", 3);
	    	test_kruskal.addEdge("C", "D", 5);
	    	test_kruskal.addEdge("C", "E", 1);
	    	test_kruskal.addEdge("D", "F", 7);
	    	test_kruskal.addEdge("E", "F", 8);
	    	test_kruskal.addEdge("F", "G", 9);
	    	
	    	
	    	System.out.println(test_kruskal);
	    	System.out.println(test_kruskal.kruskal());
	    	*/
	    	
	    	
	    	/*
	    	Graph test_dijkstra = new Graph();
	    	test_dijkstra.addVertex("A");
	        test_dijkstra.addVertex("B");
	    	test_dijkstra.addVertex("C");
	    	test_dijkstra.addVertex("D");
	    	test_dijkstra.addVertex("E");
	    	
	    	test_dijkstra.addEdge("A", "B", 4);
	    	test_dijkstra.addEdge("A", "C", 2);
	    	test_dijkstra.addEdge("B", "C", 3);
	    	test_dijkstra.addEdge("C", "B", 1);
	    	test_dijkstra.addEdge("B", "D", 2);
	    	test_dijkstra.addEdge("B", "E", 3);
	    	test_dijkstra.addEdge("C", "D", 4);
	    	test_dijkstra.addEdge("C", "E", 5);
	    	test_dijkstra.addEdge("E", "D", 1);
	    	
	    	System.out.println(test_dijkstra);
	    	System.out.println(test_dijkstra.dijkstra("A"));
	    	*/
	    	
	    	
	    	
			
   
    }
    
}

class Vertex { // this is each vertex in the graph
    
    public static final int WHITE = 0; // we color the node in 3 different colors to do the depthfirst and breathfirst search. 
    public static final int GRAY = 1;
    public static final int BLACK = 2;

    String label; // label of the vertex
    
    int color;//color of the vertex
    
    double distance; // its distance from source s to vertex u computed by algorithm.
    
    Vertex parent; // the parent of the vertex
    
    int discoverStep; // the step when vertex becomes gray
    
    int finishStep; // the step when vertex becomes black
    
   
    /* ------------------------------------------------------------
   Everytime we initialize a vertex, we set its color to be white means
   it is not discovered yet. the distance to be positive-infinity since we will
   set each vertex's distance in during searching later. parent to be null,
    discoverstep and finishstep to be 0.
    ------------------------------------------------------------ */
    public Vertex(String label) {
        this.label = label;
        distance = Double.POSITIVE_INFINITY;
        color = WHITE;
        parent = null;
        discoverStep = 0;
        finishStep = 0;
       
    }
    
    public String toString() {
        return label;
    }

}


class Edge implements Comparable<Edge> {
    
    Vertex source; // the source of an edge
    
    Vertex target; // where the edge is pointing to
     
    double weight; // specific weight of given edge.
    
    boolean finaledge;

    public Edge(Vertex source, Vertex target, double weight){
        this.source = source;
        this.target = target;
        this.weight = weight;
        //this.finaledge = false;//default, it is final edge is false, this is a variable for 
    }
    public double getWeight() {
    	return weight;
    }

    public String toString() {
        return "(" + source + " -> " + target + " : " + weight + ")";
    }
	
	@Override
	public int compareTo(Edge compareEdge) {
		int compareQuantity = (int)((Edge) compareEdge).getWeight(); 
    	
    	//ascending order
    	return (int)this.weight - compareQuantity;
    	
	}
 
   
	
    
}
