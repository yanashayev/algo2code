package T10;


import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;

public class EulerPath {
    ArrayList<Integer>[] graph;//input graph
    ArrayList<Integer> ec; // Euler path of vertexes
    Stack<Integer> st;
    int degrees[];
    int numOfVertexes, source;

    public EulerPath(ArrayList<Integer>[] graph){
        st = new Stack<Integer>();
        ec = new ArrayList<Integer>();
        this.graph = graph;
        numOfVertexes = this.graph.length;
        degrees = new int[numOfVertexes];
        for (int i = 0; i < graph.length; i++) {
            degrees[i] = graph[i].size();
        }
    }

    public boolean checkIfEulerPath(){
        int odd = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].size()%2 != 0) {
                odd++;
                source = i;
            }
        }
        return odd==2;
    }

    public boolean buildEulerPath(){
        boolean ans = checkIfEulerPath();
        if (!ans){
            System.out.println("The Graph has no Euler Path");
        }
        else{
            int v = source, u;
            st.push(v);
            while(!st.empty()){//O(|E|)
                v = st.peek();//returns the object at the top of this
                // stack without removing it from the stack.
                if (degrees[v] == 0){
                    ec.add(v);//O(1)
                    st.pop();//O(1)
                }
                else{
                    u = graph[v].get(0);
                    st.add(u);
                    degrees[v]--;
                    degrees[u]--;
                    graph[v].remove((Integer)u);//O(|V|)
                    graph[u].remove((Integer)v);//O(|V|)
                }
            }
        }
        return ans;
    }

    public void printEulerPath(){
        System.out.println("The Euler Path:");
        System.out.println(ec.toString());
    }

    public void printGraph(){
        System.out.println("The Graph:");
        System.out.println(Arrays.toString(graph));
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Integer>[] init1(){//cycle: [0, 2, 4, 3, 2, 1, 0]
        int numVert = 5;
        ArrayList<Integer>[] graph = new ArrayList[numVert];
        for (int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<Integer>();
        }
        graph[0].add(1); graph[0].add(2);
        graph[1].add(0); graph[1].add(2); graph[1].add(4);
        graph[2].add(0); graph[2].add(1); graph[2].add(3); graph[2].add(4);
        graph[3].add(2); graph[3].add(4);
        graph[4].add(3); graph[4].add(2); graph[4].add(1);
        return graph;
    }

    public static void main(String[] args) {
        EulerPath p = new EulerPath(init1());
        p.buildEulerPath();
        p.printEulerPath();
    }
}

