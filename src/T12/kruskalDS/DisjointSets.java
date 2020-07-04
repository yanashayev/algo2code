package T12.kruskalDS;

// simple union-find based on int[] arrays
// for  "parent" and "rank"
// implements the "disjoint-set forests" described at
// http://en.wikipedia.org/wiki/Disjoint-set_data_structure
// which have almost constant "amortized" cost per operation
// (actually O(inverse Ackermann function))

import java.util.*;

public class DisjointSets {

	private int[] parent;
	private int[] rank;//rank[k]>=height of tree number k

	public DisjointSets(int max) {
		parent = new int[max];
		rank = new int[max];
	}
	// Makes a set containing only a given element (a singleton). 
	public void makeSet(int k){ // O(1)
		parent[k] = k;
		rank[k] = 0;
	}
	// Determine which subset a particular element is in. 
	// Find typically returns an item from this set that serves as its "representative". 
	// Also, by comparing the result of two Find operations, one can determine whether 
	// two elements are in the same subset.
	// (Using path compression)
	public int find(int v) { // O(log(n))
		int p = parent[v];
		if (v == p) {
			return v;
		}
		return parent[v] = find(parent[p]);
	}
	//Join two subsets into a single subset.
	// returns true if rootU != rootV
	public boolean union(int u, int v) {  // O(log(n))
		boolean ans = false;
		int rootU = find(u);
		int rootV = find(v);
		if (rootV == rootU) ans = false;
		else{
			ans = true;
			if (rank[rootU] > rank[rootV]) {
				parent[rootV] = rootU;
			} 
			else if (rank[rootV] > rank[rootU]) {
				parent[rootU] = rootV;
			} 
			else {//rank[root2] == rank[root1]
				parent[rootV] = rootU;
				rank[rootU]++;
			}
		}
		return ans;
	}

	public String toString() {
		return "<DisjointSets\nparent " + Arrays.toString(parent) + "\nrank " + Arrays.toString(rank) + "\n>";
	}
    /**
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return true if the two sites p and q are in the same component;
     *         false otherwise
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

	public static void main(String[] args) {
		int numSets = 5;
		DisjointSets ds = new DisjointSets(numSets);
		for (int i = 0; i < numSets; i++) {
			ds.makeSet(i);
		}
		System.out.println(ds);
		ds.union(1,2);
		System.out.println("union 1 & 2");
		System.out.println("find for 1  - root is "+ds.find(1) + ", find for 2 - root is " + ds.find(2));
		System.out.println(ds);
		
		ds.union(3,4);
		System.out.println("union 3 & 4");
		System.out.println(ds);
		
		ds.union(1,3);
		System.out.println("union 1 & 3");
		System.out.println(ds);
		
		ds.union(1,4);
		System.out.println("union 1 & 4");
		System.out.println(ds);
		System.out.println("find for 4  - root is "+ds.find(4));

		/*		ds.union(1,2);
		System.out.println("union 1 2");
		System.out.println(ds);

		ds.union(1,2);
		System.out.println("union 1 2");
		System.out.println(ds);

		ds.union(3,4);
		System.out.println("union 3 4");
		System.out.println(ds);

		ds.union(1,0);
		System.out.println("union 1 0");
		System.out.println(ds);

		ds.union(1,3);
		System.out.println("union 1 3");
		System.out.println(ds);

		ds.find(4);
		System.out.println("find 4");
		System.out.println(ds);

		 */	}

	/*

  The tests in main should produce:

<DisjointSets
parent [0, 1, 2, 3, 4]
rank [0, 0, 0, 0, 0]
>
union 1 & 2
find for 1  - root is 1, find for 2 - root is 1
<DisjointSets
parent [0, 1, 1, 3, 4]
rank [0, 1, 0, 0, 0]
>
union 3 & 4
<DisjointSets
parent [0, 1, 1, 3, 3]
rank [0, 1, 0, 1, 0]
>
union 1 & 3
<DisjointSets
parent [0, 1, 1, 1, 3]
rank [0, 2, 0, 1, 0]
>
union 1 & 4
<DisjointSets
parent [0, 1, 1, 1, 1]
rank [0, 2, 0, 1, 0]
>

	 */

}

