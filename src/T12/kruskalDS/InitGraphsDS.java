package T12.kruskalDS;

import java.util.ArrayList;
import java.util.Arrays;
public class InitGraphsDS {

	public static ArrayList<Node>[] init1(){
		int n = 8;
		ArrayList<Node>[] graph = new ArrayList[n];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Node>();
		}
		graph[0].add(new Node(1, 19)); graph[0].add(new Node(7,6));  graph[0].add(new Node(3,25));
		graph[1].add(new Node(0,19));  graph[1].add(new Node(2,9));
		graph[2].add(new Node(1,9));   graph[2].add(new Node(3,14));
		graph[3].add(new Node(2,14));  graph[3].add(new Node(4,21)); graph[3].add(new Node(0,25)); 
		graph[3].add(new Node(5,2));   graph[3].add(new Node(7,11));
		graph[4].add(new Node(3,21));
		graph[5].add(new Node(3,2));   graph[5].add(new Node(6,8));
		graph[6].add(new Node(5,8));   graph[6].add(new Node(7,17));
		graph[7].add(new Node(0,6));   graph[7].add(new Node(6,17));  graph[7].add(new Node(3,11));
		return graph;
	}
	public static ArrayList<Node>[] init2(){
		int n = 4;
		ArrayList<Node>[] graph = new ArrayList[n];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Node>();
		}
		graph[0].add(new Node(1,12));
		graph[1].add(new Node(0,12)); graph[1].add(new Node(2,3)); graph[1].add(new Node(3,5)); 
		graph[2].add(new Node(1,3));  graph[2].add(new Node(3,1)); 
		graph[3].add(new Node(1,5));  graph[3].add(new Node(2,1));  
		return graph;
	}
	public static Edge[] getEdges(ArrayList<Node>[] graph){
		int numOfEdges = 0, n = graph.length;
		for (int i = 0; i < graph.length; i++) {
			numOfEdges = numOfEdges + graph[i].size();
		}
		numOfEdges = numOfEdges/2;
		Edge[] edges  = new Edge[numOfEdges];
		for (int i=0, k=0; k<numOfEdges && i<n; i++){
			for (int j = 0; j < graph[i].size(); j++) {
				Node nn = graph[i].get(j);
				Edge e = new Edge(i, nn.node, nn.weight);
				if (!contains(edges, e, k)) edges[k++] = e;
			}
		}
		return edges;
	}
	private static boolean contains(Edge[] edges, Edge e, int k){
		boolean ans = false;
		for (int i = 0; !ans && i < k; i++) {
			if (edges[i].equals(e)) ans = true;
		}
		return ans;
	}
	public static void main(String[] args) {
		Edge edges[] = getEdges(init1());
		System.out.println(Arrays.toString(edges));
		edges = getEdges(init2());
		System.out.println(Arrays.toString(edges));
	}

}
