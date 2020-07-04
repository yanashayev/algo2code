package T12.MST_ErasingEdges;

/**
    The Edge class represents an edge: (A,B) with the weight 
*/
class Edge implements Comparable<Edge>{
    int vertexA, vertexB;
    int weight;
// Constructor
    public Edge(int vertexA, int vertexB, int weight){
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
    }
//get vertex A
    public int getVertexA(){
        return vertexA;
    }
//get vertex B
    public int getVertexB(){
        return vertexB;
    }
//get weight
    public int getWeight(){
        return weight;
    }

    public String toString(){
        return "(" + vertexA + "," + vertexB + ",w:" + weight+")";
    }
// implements compareTo()function of Comparable Interface     
    public int compareTo(Edge edge){
    	int ans = 0;
    	if (this.weight < edge.weight) ans = -1;
    	else  ans = 1;  //this.weight >= edge.weight
    	return ans;
    }
}
