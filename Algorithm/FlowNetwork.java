package hw08;
import java.util.*;
public class FlowNetwork {
	  static Map<String, Vertex> vertices; // include all vertices in the map
	    
	    static Map<String, ArrayList<Edge>> adjacent; // include all edges connecting vertices
	     
	    List<String> searchSteps; // record the graph in DOT format of the string for each main step of the algorithm.
	    
	    List<String > path1; //path using the depthfirst search
	    
	    List<String> path2; //path using the breathfirst search
	    
	    List<String> topological;//path store the topological pathway
	    
	    static double totalweight;
	    
	    
	    int time;
	   
	    
	    public FlowNetwork() {
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
      public void addVertex(String key, String x) {
	    	if(x .equals("Source")) {
	        Vertex v = new Vertex(key, "Source");
	        vertices.put(key, v);
	        adjacent.put(key, new ArrayList<Edge>());
	    }
	    	if(x .equals("Sink")) {
		        Vertex v = new Vertex(key, "Sink");
		        vertices.put(key, v);
		        adjacent.put(key, new ArrayList<Edge>());
		    }
	    	
    }  	

		/* ------------------------------------------------------------
	      we add an edge including the sources and where it points to
	      and its weight.
	      ------------------------------------------------------------ */
	    public void addEdge(String source, String target, double capacity, double flow) {
	        if (!vertices.containsKey(source)) {
	            addVertex(source);
	        }
	        if (!vertices.containsKey(target)) {
	            addVertex(target);
	        }
	        ArrayList<Edge> edges = adjacent.get(source); // we find the edge list of the source vertex
	        Edge e = new Edge(vertices.get(source), vertices.get(target), capacity, flow );
	        edges.add(e);
	    }
	    public void deleteEdge(String source, String target) {
	    	if(adjacent.get(source) == null) {
	    		return;
	    	}
	    	ArrayList<Edge> edges = adjacent.get(source);
	    	for (Edge e : edges) {
  			if(e.target == vertices.get(target)) {
  			   edges.remove(e);
  			   break;
  			}
           }
	    }
      public void setFlowNetWork() {
      	this.setOneSource();
      	this.setOneSink();
      	this.NoParallel();
      }
      public void setOneSource() {
      	int count = 0;
      	ArrayList<Vertex> sources = new ArrayList<Vertex>();
      	
      	for (String key : vertices.keySet()) { // we check how many sources do we have, if more than one we need
      		                                   // to set it to one artificial source
               Vertex pos = vertices.get(key);
            
      		 if(pos.source == true) {
      			 count ++;
      		 }
      	}
      
      	// we construct a total source S and link it to every source in the flownetwork.
      	if(count > 1) {
      	    
      		for (String key : vertices.keySet()) { 
               Vertex pos = vertices.get(key);
                if(pos.source == true) {
              	    sources.add(pos);
                   }
      	}
      		for(Vertex v: sources) {
      			addEdge("S", v.label, Double.POSITIVE_INFINITY, 0);
      		}
       }
    }
      public void setOneSink(){
      	int count = 0;
      	ArrayList<Vertex> sinks = new ArrayList<Vertex>();
      	for (String key : vertices.keySet()) { // we check how many sinks do we have, if more than one we need
      		                                   // to set it to one artificial sink
               Vertex pos = vertices.get(key);
      		 if(pos.source == true) {
      			 count ++;
      		 }
      	}
      	// we construct a total sink T and link it to every sink in the flownetwork.
      	if(count > 1) {
      		addVertex("T");
      		for (String key : vertices.keySet()) { 
               Vertex pos = vertices.get(key);
                if(pos.sink == true) {
              	    sinks.add(pos);
                  }
      	}
      		for(Vertex v : sinks) {
      			addEdge(v.label, "T", Double.POSITIVE_INFINITY, 0);
      		}
       }
    }
      // we check for parallel edges. We check if two vertexs both in the other one's adjacentedge, we delete that edge.
      // and add a new vertex in between these 2 edges.
      public void NoParallel() {
      	ArrayList<Edge> parallelEdge = new ArrayList<Edge>(); // here we construct the array list containing all edges in the original graph
      	for (String key : vertices.keySet()) {
      		ArrayList<Edge> adjacentEdge = adjacent.get(key);
      		for (Edge e : adjacentEdge) {
      			Vertex source1 = e.source;
      			Vertex target1 = e.target;
      			ArrayList<Edge> adjacentEdge1 = adjacent.get(target1.label);
      			for(Edge d: adjacentEdge1) {
      				if(d.target == source1 && !parallelEdge.contains(e)) {
      					parallelEdge.add(d);
      				}
      			}
               }
      	}
      	int count = 0;
      	for(Edge e: parallelEdge) {
      		Vertex source2 = e.source;
      		Vertex target2 = e.target;
      		double capacity = e.capacity;
      		deleteEdge(source2.label, target2.label);
      		StringBuilder s = new StringBuilder();
      		s.append("sub");
      		s.append(count);
      		String subName = s.toString();
      		addEdge(source2.label, subName, capacity, 0);
      		addEdge(subName, target2.label, capacity, 0);
      		
      	}
      }
	    public String toString() {
	        // You modify this method
	        StringBuilder s = new StringBuilder();
	        s.append("digraph G{" + "\n"); // in DOT format we first construct a digraph.
	        s.append("rankdir = LR;" + "\n");
	        s.append("nodesep = 1.0;" + "\n");
	        for (String key : vertices.keySet()) {
	            // if the color is white, we draw a vertex with dotted style
	        	if(vertices.get(key).color == 0) { 
	        		s.append( "\"" + vertices.get(key)+ "\"" +   "[ style = "+ " \"dotted\" " + "];" + "\n" );
	        		
	        	}
	        	// if the color is gray, we draw a vertex with filled gray.
	        	else if(vertices.get(key).color == 1) {
	        		s.append( "\"" + vertices.get(key)+ "\"" +   "[ style = "+ " \"filled\" " + "];" + "\n" );
	        	}
	        	// if the color is black, we draw a vertex in black.
	        	else {
	        		s.append("\"" + vertices.get(key)+ "\"" +  ";" + "\n" );
	        	}
	        	
	        }
	        // we draw the edges in the language of graphviz.
	        for (String key : vertices.keySet()) {
	            ArrayList<Edge> edges = adjacent.get(key);
	            for (Edge e : edges) {
	                s.append(e.source + " -> " + e.target + "[ label = " + "\"" + e.flow + " / " + e.capacity + "\"" + "];" + "\n");
	                
	            }
	        }
	        s.append("}");
	        return s.toString();
	    }
	    public double maxFlow() {
	    	double maxFlow = 0;
	    	Vertex source = findSource();
	    	Vertex sink = findSink();
	    	ArrayList<Edge> path = findPath(source,sink);
	    	
	    	do{
	        double min = Double.POSITIVE_INFINITY;
	    	for(Edge e: path) {
	    		if(e.asSource && e.residual < min)
	    			min = e.residual;
	    		else if(!e.asSource && e.flow < min)
	    			min = e.flow;
	    	}
	    	for(Edge e: path) {
	    		if(e.asSource) {
	    			if(e.flow + min <= e.capacity) {
	    			e.flow += min;
	    			e.residual -= min;
	    			}	
	    		}else {
	    			e.flow -= min;
	    			e.residual += min;
	    		}
	    	}
	    	maxFlow += min;
	    	path = findPath(source,sink);
	    	
	    }while(!path.isEmpty());
	    	return maxFlow;
	    }
	    // In this method, we find a single available path for going through source to sink.
	    public ArrayList<Edge> findPath(Vertex source,Vertex sink){
	    	ArrayList<Edge> result = new ArrayList<Edge>();
	    	 Stack<Vertex> pos = new Stack<Vertex>();
	    	 ArrayList<Edge> visitedEdge = new ArrayList<Edge>();
	    	 pos.add(source);
	    	
	    	
	    	 while(!pos.isEmpty() && pos.peek() != sink){
	    	 Vertex u = pos.peek();
	    	  int newVertexFound = 0;
	    	  boolean foundSourceEdge = false; // if we have found an edge in source edge, we no longer need to check target edge.
	      	  ArrayList<Edge> asSourceEdge = adjacent.get(u.label); // this is all edges u as the source of it.
	      	  ArrayList<Edge> asTargetEdge = TargetEdge(u); // this is all edges u as the target of it.
	      	 
	      	  for(Edge e: asSourceEdge) {
	        	  if(e.residual > 0 && !visitedEdge.contains(e)) { // if we find an edge with available residual, we add it and break the loop.
	        		 pos.push(e.target);
	        		 result.add(e);
	        		 visitedEdge.add(e);
	        		 foundSourceEdge = true;
	        		 newVertexFound ++;
	                 break;
	        	  }
	          }
	          if(foundSourceEdge == false) { // if we haven't find anything from source edge, we check targetedge.
	        	  
	        	  for(Edge d: asTargetEdge) {
	        		  if(d.flow > 0 && !visitedEdge.contains(d)) {
	        			  pos.push(d.source);
	        			  d.asSource = false;
	        			  result.add(d);
	        			  visitedEdge.add(d);
	        			  newVertexFound ++;
	        			  break;
	        		  }
	        	  }
	          }
	          if(newVertexFound == 0) { // if it reaches a dead end, we go back to the previous vertex. If we
	        	                        // keep going back until pos is empty, it means that no path can be found.
	        	  pos.pop();
	        	  if(!result.isEmpty())
	        	  result.remove(result.size() - 1);
	        	      
	          }
	    	}
	    	return result;
	    }
	    // in this method, we aim to find all edges that u as the target. Since when we check the path
	    // we need to both check edges for u as source and u as target.
	    public ArrayList<Edge> TargetEdge(Vertex u){
	    	ArrayList<Edge> result = new ArrayList<Edge>();
	    	// we find all edges in the graph
	    	for (String key : vertices.keySet()) {
	    		ArrayList<Edge> adjacentEdge = adjacent.get(key);
	    		for (Edge e : adjacentEdge) {
	    			if(e.target == u)
	                result.add(e);
	    	}
	      }
	    	return result;
	    }
	    // In this method we are trying to locate the source for every flow net work
	    public Vertex findSource() {
	    	int count = 0;
      	for (String key : vertices.keySet()) { // we check how many sources do we have, if more than one we need
      		                                   // to set the source to S, only one set source to that only source.
               Vertex pos = vertices.get(key);
            
      		 if(pos.source == true) {
      			 count ++;
      		 }
      	}
         if(count == 0){
      	   return null;
         }else if(count > 1) {
      	  return vertices.get("S");
         }else {
      	   for (String key : vertices.keySet()) { 
             Vertex pos = vertices.get(key);

             if(pos.source == true) {
                return pos; 
             }
         }
        }
         return null;
	    }
	    public Vertex findSink() {
	    	int count = 0;
      	for (String key : vertices.keySet()) { // we check how many sources do we have, if more than one we need
      		                                   // to set the sink to T, only one set sink to that only sink.
               Vertex pos = vertices.get(key);
            
      		 if(pos.sink == true) {
      			 count ++;
      		 }
      	}
         if(count == 0){
      	   return null;
         }else if(count > 1) {
      	  return vertices.get("T");
         }else {
      	   for (String key : vertices.keySet()) { 
             Vertex pos = vertices.get(key);

             if(pos.sink == true) {
                return pos; 
             }
         }
        }
         return null;
	    }
	    public static void main(String[] args) {
	    	/*
	    	FlowNetwork test = new FlowNetwork();
	    	test.addVertex("S", "Source");
	        test.addVertex("A");
	    	test.addVertex("B");
	    	test.addVertex("C");
	    	test.addVertex("D");
	    	test.addVertex("T","Sink");
	    	
	        test.addEdge("S", "A", 16, 0);
	        test.addEdge("S", "C", 13, 0);
	        test.addEdge("C", "A", 4, 0);
	        test.addEdge("A", "B", 12, 0);
	        test.addEdge("B", "C", 9, 0);
	        test.addEdge("C", "D", 14, 0);
	        test.addEdge("D", "B", 7, 0);
	        test.addEdge("D", "T", 4, 0);
	        test.addEdge("B", "T", 20, 0);
	        
	    	test.setFlowNetWork();
	    	
	       System.out.println(test.maxFlow());
	       System.out.println(test);
	       
	       */
	    	FlowNetwork test = new FlowNetwork();
	    	test.addVertex("S1", "Source");
	    	test.addVertex("S2", "Source");
	    	test.addVertex("V1");
	    	test.addVertex("V2");
	    	test.addVertex("V3");
	    	test.addVertex("T1","Sink");
	    	test.addVertex("T2","Sink");
	    	
	    	test.addEdge("S1", "V1", 8, 0);
	    	test.addEdge("S2", "V2", 20, 0);
	    	test.addEdge("V1", "V2", 5, 0);
	    	test.addEdge("V1", "V3", 3, 0);
	    	test.addEdge("V3", "V1", 4, 0);
	    	test.addEdge("V2", "V3", 7, 0);
	    	test.addEdge("V3", "T1", 20, 0);
	    	test.addEdge("V2", "T1", 10, 0);
	    	test.addEdge("V2", "T2", 5, 0);
	    	test.setFlowNetWork();
	    	
	    	 System.out.println(test.maxFlow());
		     System.out.println(test);
	    	
	    	
	    	
	    	
			
	    	   
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
	    
	    boolean source;
	    
	    boolean sink;
	    
	   
	    /* ------------------------------------------------------------
	   Everytime we initialize a vertex, we set its color to be white means
	   it is not discovered yet. the distance to be positive-infinity since we will
	   set each vertex's distance in during searching later. parent to be null,
	    discoverstep and finishstep to be 0.
	    
	    
	    we have also added source and sink to represent this Vertex is a source or sink.
	    ------------------------------------------------------------ */
	    public Vertex(String label) {
	        this.label = label;
	        distance = Double.POSITIVE_INFINITY;
	        color = WHITE;
	        parent = null;
	        discoverStep = 0;
	        finishStep = 0;
	        source = false;
	        sink = false;
	        
	       
	    }
	    // if we construct a vertex to be source vertex, we type in new vertex(label, "Source")
	    // to be sink vertex, type in new vertex(label, "Sink")
	    public Vertex(String label, String x) {
	    	if(x.equals("Source")) {
	        this.label = label;
	        distance = Double.POSITIVE_INFINITY;
	        color = WHITE;
	        parent = null;
	        discoverStep = 0;
	        finishStep = 0;
	        source = true;
	        sink = false;
	    	} 
	    	
	    	if(x.equals("Sink")) {
		        this.label = label;
		        distance = Double.POSITIVE_INFINITY;
		        color = WHITE;
		        parent = null;
		        discoverStep = 0;
		        finishStep = 0;
		        source = false;
		        sink = true;
		    	} 
	        
	       
	    }
	    
	    public String toString() {
	        return label;
	    }

	}


	class Edge{ 
	    
	    Vertex source; // the source of an edge
	    
	    Vertex target; // where the edge is pointing to
	     
	   	double capacity;
	    
	    double residual;
	    
	    double flow;
	    
	    boolean asSource;// this attribute means that the edge in the path for FF method is as source edge or as target edge.
      // we have added flow, capacity, and residual for future implementing flow network and residual network.
	    public Edge(Vertex source, Vertex target, double capacity, double flow){
	        this.source = source;
	        this.target = target;
	        this.flow = flow;
	        this.capacity = capacity;
	        this.residual = capacity - flow;
	        this.asSource = true;
	        //this.finaledge = false;//default, it is final edge is false, this is a variable for 
	    }
	    
	    public String toString() {
	        return "(" + source + " -> " + target + " : " + flow + " / " + capacity + ")";
	    }
		
}

