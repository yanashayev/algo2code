package T12.kruskalDS;
public class Node{
	int node;
	int weight;
	public Node(int node, int weight){
		this.node = node;
		this.weight = weight;
	}
	public String toString(){
		return "node: " + node + ", weight: " + weight;
	}
}
