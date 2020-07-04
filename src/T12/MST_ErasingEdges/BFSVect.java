package T12.MST_ErasingEdges;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
public class BFSVect {
/** 
 *  @param size: number of vertexes+1 
 *  @param infinity - the big number
 *  @param vertex - the source vertex
 *  @param graph - the graph is represented using adjacency-list
 *  @param q - the queue to manage the set of gray vertices
 *  @param color[] - the color of  vertex u is stored in color[u]
 *  @param pred[] - the predecessor (parent) of vertex u is stored
 *  			    in pred[u]. If u has no predecessor then pred[u]=NIL
 *  @param dist[] - the distance from the source vertex to vertex u
 *  				computed by the algorithm is stored in dist[u].
 *  
 *  The algorithm also uses a first-in first-out ArrayBlockingQueue q
 *  to manage the set of gray vertices
 */                  
	private  int size;
	private final int infinity = 99999;
	int vertex;
	Vector<LinkedList<Integer>>  graph = new Vector<LinkedList<Integer>>();
	Queue<Integer> q;
	int dist[], pred[], color[];
	final int WHITE=1, GRAY=2,  BLACK=3, NIL = -1;
	/** constructor */
	public BFSVect(Vector<LinkedList<Integer>> graph){
		this.graph = graph;
		this.size = graph.size();
		q = new ArrayBlockingQueue<Integer>(size);
		dist = new int[size];
		pred = new int[size];
		color = new int[size];
	}
	/** clear arrays */
	private void clear(){
		for (int i=0; i<size; i++){
			dist[i] = infinity;
			pred[i] = NIL;
			color[i] = WHITE;
		}
	}
	/** The breadth-first-search procedure O(E+V)*/
	public void BFS(int s){
		clear();
		vertex = s;
		color[vertex] = GRAY;
		dist[vertex] = 0;
		pred[vertex] = NIL;
		q.offer(vertex);
		Iterator<Integer> iter;
		LinkedList<Integer> list;
		while (!q.isEmpty()){
			int u = q.poll();
			list  = graph.elementAt(u);
			iter = list.iterator();
			while (iter.hasNext()){
				int v = iter.next();
				if (color[v]==WHITE){
					color[v] = GRAY;
					dist[v] = dist[u]+1;
					pred[v] = u;
					q.offer(v);
				}
			}
			color[u] = BLACK;
		}
	}
	public boolean isConnected(){
		boolean ans = true;
		for (int i=1; ans && i<size; i++){
			if  (dist[i] == infinity) ans = false;
		}
		return ans;
	}
	////////
	/**
	 *  graph with circles
	 */
	@SuppressWarnings("unchecked")
	public static Vector<LinkedList<Integer>> initList1(){
		Vector<LinkedList<Integer>>  graph = new Vector<LinkedList<Integer>>();
		int size = 9;
		LinkedList<Integer>[]  list = new LinkedList[size];
		for (int i=0; i<size; i++){
			list[i] = new LinkedList<Integer>();
		}
		list[0].add(-1);
		graph.add(list[0]);
// first node
		list[1].add(2);list[1].add(5);		
		graph.add(list[1]);
// second node
		list[2].add(1);list[2].add(6);
		graph.add(list[2]);
// third node
		list[3].add(6);list[3].add(7);list[3].add(4);
		graph.add(list[3]);
// fourth node	
		list[4].add(3);list[4].add(7);list[4].add(8);
		graph.add(list[4]);
// fifth node		
		list[5].add(1); 
		graph.add(list[5]);
// sixth mode		
		list[6].add(2);list[6].add(3);list[6].add(7);
		graph.add(list[6]);
// seventh node		
		list[7].add(6);list[7].add(3);list[7].add(4);list[7].add(8);
		graph.add(list[7]);
// eighth node		
		list[8].add(7);list[8].add(4);
		graph.add(list[8]);
		return graph;
	}
	/**
	 *  graph with circles
	 */
	@SuppressWarnings("unchecked")
	public static Vector<LinkedList<Integer>> initList2(){
		Vector<LinkedList<Integer>>  graph = new Vector<LinkedList<Integer>>();
		int size = 9;
		LinkedList<Integer>[]  list = new LinkedList[size];
		for (int i=0; i<size; i++){
			list[i] = new LinkedList<Integer>();
		}
		list[0].add(-1);
		graph.add(list[0]);
// first node
		list[1].add(2);list[1].add(5);		
		graph.add(list[1]);
// second node
		list[2].add(1);list[2].add(6);
		graph.add(list[2]);
// third node
		list[3].add(6);
		graph.add(list[3]);
// fourth node	
		list[4].add(7);
		graph.add(list[4]);
// fifth node		
		list[5].add(1); 
		graph.add(list[5]);
// sixth mode		
		list[6].add(2);list[6].add(3);list[6].add(7);
		graph.add(list[6]);
// seventh node		
		list[7].add(6);list[7].add(4);list[7].add(8);
		graph.add(list[7]);
// eighth node		
		list[8].add(7);
		graph.add(list[8]);
		return graph;
	}
	@SuppressWarnings("unchecked")
	public static Vector<LinkedList<Integer>> initList3(){
		Vector<LinkedList<Integer>>  graph = new Vector<LinkedList<Integer>>();
		int size = 10;
		LinkedList<Integer>[]  list = new LinkedList[size];
		for (int i=0; i<size; i++){
			list[i] = new LinkedList<Integer>();
		}
		list[0].add(-1);
		graph.add(list[0]);
// first node
		list[1].add(2);list[1].add(5);		
		graph.add(list[1]);
// second node
		list[2].add(1);list[2].add(6);
		graph.add(list[2]);
// third node
		list[3].add(6);
		graph.add(list[3]);
// fourth node	
		list[4].add(7);
		graph.add(list[4]);
// fifth node		
		list[5].add(1); 
		graph.add(list[5]);
// sixth mode		
		list[6].add(2);list[6].add(3);list[6].add(7);
		graph.add(list[6]);
// seventh node		
		list[7].add(6);list[7].add(4);list[7].add(8);
		graph.add(list[7]);
// eighth node		
		list[8].add(7); list[8].add(9); 
		graph.add(list[8]);
// ninth node		
		list[9].add(8);
		graph.add(list[9]);
		return graph;
	}
	@SuppressWarnings("unchecked")
	/// disconnected graph
	public static Vector<LinkedList<Integer>> initList4(){
		Vector<LinkedList<Integer>>  graph = new Vector<LinkedList<Integer>>();
		int size = 10;
		LinkedList<Integer>[]  list = new LinkedList[size];
		for (int i=0; i<size; i++){
			list[i] = new LinkedList<Integer>();
		}
		list[0].add(-1);
		graph.add(list[0]);
// first node
		list[1].add(2);   list[1].add(3);		
		graph.add(list[1]);
// second node
		list[2].add(1);  list[2].add(3);		
		graph.add(list[2]);
// third node
		list[3].add(1);  list[3].add(2);		
		graph.add(list[3]);
// 4-th node
		list[4].add(5);		
		graph.add(list[4]);
// 5-th node
		list[5].add(4);		
		graph.add(list[5]);
		return graph;
	}

	public static void main(String[] args) {
		// number of first node is 1
		BFSVect bf= new BFSVect(initList4());
		bf.BFS(1);
		System.out.println("Graf is connected: "+bf.isConnected());
	}
} 
