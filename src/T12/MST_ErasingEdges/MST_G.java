package T12.MST_ErasingEdges;
/*
 * Minimum Spanning Tree � Reverse Kruskal Algorithm
 */
import java.util.*;
public class MST_G {
	Vector<Edge> graph;	
	Vector<LinkedList<Integer>> BFSGraph;
	int numOgEdges, numOfVertexes;
	// constructor
	public MST_G(Vector<Edge> graph){
		BFSGraph = new Vector<LinkedList<Integer>>();
		this.graph = graph;
		numOgEdges = graph.size();
		sortGraph();
		createBFSGraph(numOgEdges+1);
		int count = 1;
		// number of vertexes calculation
		while(!BFSGraph.elementAt(count).isEmpty()){ 
			count++;
		}
		numOfVertexes = count-1;
		BFSGraph.setSize(count);
	}
	public void createBFSGraph(int length){
		//initialization
		BFSGraph.clear();
		for (int i=0; i<length+1; i++){
			BFSGraph.add(new LinkedList<Integer>());
		}
		// creation
		for (int i=0; i<numOgEdges; i++){
			int a = graph.elementAt(i).vertexA;
			int b = graph.elementAt(i).vertexB;
			if (!BFSGraph.elementAt(a).contains(b)){
				BFSGraph.elementAt(a).add(b);
			}		 
			if (!BFSGraph.elementAt(b).contains(a)){
				BFSGraph.elementAt(b).add(a);
			}		 
		}
	}
	public void createMst(){
		// count = number of edges in minimum spanning tree
		// count = numOfVertexes-1
		// number of edges to remove = numOgEdges - (numOfVertexes-1)
		int index=0, edgeIndex=0;
		createBFSGraph(numOfVertexes+1);
		BFSGraph.setSize(numOfVertexes+1); 
		int count=numOgEdges-(numOfVertexes-1);
		while (index < count){
			Edge e = graph.elementAt(edgeIndex);
			graph.remove(edgeIndex);
			numOgEdges--;
			createBFSGraph(numOfVertexes+1);
			BFSGraph.setSize(numOfVertexes+1); 
			BFSVect bfs = new BFSVect(BFSGraph);
			bfs.BFS(e.vertexA);
			if (bfs.isConnected()){
				index++;
			}
			else{
				graph.add(edgeIndex, e);
				edgeIndex++;
				numOgEdges++;
			}
		}
	}
	public double calcSummWieight(){
		double w = 0;
		for (int i=0; i<graph.size(); i++){
			w = w + graph.elementAt(i).weight;
		}
		return w;
	}
	public void sortGraph(){
		boolean flag = true;
		for (int i=0; flag && i<numOgEdges; i++){
			flag = false;
			for (int j=0; j<numOgEdges-1; j++){
				if (graph.elementAt(j).compareTo(graph.elementAt(j+1))==-1){
					Edge t = graph.elementAt(j);
					graph.set(j, graph.elementAt(j+1));
					graph.set(j+1,t);
					flag = true;
				}
			}
		}
	}
	public static Vector<Edge> init1(){
		Vector<Edge> graph  = new Vector<Edge>();	
		graph.add(new Edge(1,2,4));// 1-st edge
		graph.add(new Edge(2,3,8));// 2-st edge
		graph.add(new Edge(3,4,7));// 3-st edge
		graph.add(new Edge(4,5,9));// 4-st edge
		graph.add(new Edge(5,6,10));// 5-st edge
		graph.add(new Edge(6,7,2));// 6-st edge
		graph.add(new Edge(7,8,1));// 7-st edge
		graph.add(new Edge(8,1,8));// 8-st edge
		graph.add(new Edge(2,8,11));// 9-st edge
		graph.add(new Edge(3,9,2));// 10-st edge
		graph.add(new Edge(3,6,4));// 11-st edge
		graph.add(new Edge(4,6,14));// 12-st edge
		graph.add(new Edge(7,9,6));// 13-st edge
		graph.add(new Edge(8,9,7));// 14-st edge
		return graph;
	}

	public static void main(String[] args) {
		MST_G mst = new MST_G(init1());
		mst.createMst();
		System.out.println("Graph : "+mst.graph.toString());
		System.out.println("MST Weight: "+mst.calcSummWieight());
	}
}
/**
Graph : [(4,5,w:9), (8,1,w:8), (3,4,w:7), (1,2,w:4), 
         (3,6,w:4), (6,7,w:2), (3,9,w:2), (7,8,w:1)]

 **/