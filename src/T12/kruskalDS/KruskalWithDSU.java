package T12.kruskalDS;

import java.util.ArrayList;
import java.util.Arrays;

public class KruskalWithDSU {
	Edge [] graph;	
	Edge [] tree;
	DisjointSets vertexGroup;
	int numOfEdges, numOfVertices, numEdgesInMST;
	// n - number of vertices, m - number of edges
	public KruskalWithDSU(Edge[] graph, int n){
		numOfEdges = graph.length;
		numEdgesInMST = 0;
		this.graph = graph;
		Arrays.sort(this.graph);//O(mlogm)=O(mlogn^2)=O(mlogn)
		numOfVertices = n;
		tree = new Edge [numOfVertices-1];
		vertexGroup = new DisjointSets(numOfVertices);
		for (int i = 0; i < numOfVertices; i++) { //O(n)
			vertexGroup.makeSet(i);
		}
	}
	public void CreateMSP(){
		int i=0;
		while(numEdgesInMST < numOfVertices-1 && i < numOfEdges){
			if(vertexGroup.union(graph[i].getVertexA(), graph[i].getVertexB())){//O(mlogn)
				tree[numEdgesInMST++] = graph[i];	
			}
			i++;
		}		
	}
	public double calcSummWieight(){
		double w = 0;
		for (int i=0; i<numEdgesInMST; i++){
			w = w + tree[i].getWeight();
		}
		return w;
	}
	public void printTree(){
		for (int i=0; i<numEdgesInMST; i++){
			System.out.println(tree[i].toString());
		}
	}
	public static void main(String[] args) {
		ArrayList<Node> graph[] = InitGraphsDS.init2();
		int n = graph.length;
		KruskalWithDSU kruskal = new KruskalWithDSU(InitGraphsDS.getEdges(graph), n);
		kruskal.CreateMSP();
		kruskal.printTree();
		System.out.println("sum weight = "+kruskal.calcSummWieight());
	}

}
/*init3
(5,7,w:2)
(1,2,w:6)
(7,8,w:8)
(3,4,w:9)
(7,1,w:11)
(4,5,w:14)
(5,6,w:21)
sum weight = 71.0
 */
