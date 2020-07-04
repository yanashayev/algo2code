package T9;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Fire3 {

    public static void fire3(ArrayList<Integer>[] t){
        int n = t.length, nVert = n;
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < t.length; i++) { //O(2*|E|)=O(n)
            tree[i] = new ArrayList<Integer>(t[i]);
        }
        Queue<Integer> leaves = new ArrayBlockingQueue<>(n);
        int radius = 0, diameter = 0, numCenters = 0;
        int[] degrees = new int[n];
        int[] levels = new int[n];
        // queue initialization, n - number of vertices
        for (int i = 0; i<n; i++){//O(n)
            degrees[i] = tree[i].size();
            if (degrees[i] == 1) leaves.add(i);
        }
        int maxLevel = 0;
        while (nVert > 2){//O(n)
            Integer leaf = leaves.poll();
            degrees[leaf] = 0;
            Integer v = tree[leaf].get(0);
            degrees[v]--;
            tree[v].remove(leaf);
            nVert--;
            if (degrees[v] == 1) {
                leaves.add(v);
                levels[v] = levels[leaf] + 1;
                if (levels[v] > maxLevel) maxLevel = levels[v];
            }
        }
        ArrayList<Integer> centers = new ArrayList<>(2);
        for (int i = 0; i < n; i++) {
            if (levels[i] == maxLevel) centers.add(i);
        }
        numCenters = centers.size();
        if (numCenters == 2) {
            radius = maxLevel + 1;
            diameter = 2*radius - 1;
        }
        else {
            radius = maxLevel;
            diameter = 2*radius;
        }

        System.out.println("radius = " + radius + ", diameter = " + diameter + ", centers: " + centers
                +", numCenters = "+numCenters);
        //System.out.println("levels: " + Arrays.toString(levels));
    }

    public static void main(String[] args) {
        fire3(InitTreesAl.initTree1());//radius = 3, diameter = 5, centers: 1, 4
        fire3(InitTreesAl.initTree2());//radius = 2, diameter = 4, centers: 2
        fire3(InitTreesAl.initTree3());//radius = 4, diameter = 7, centers: 3, 4
        fire3(InitTreesAl.initTree4());//radius = 3, diameter = 5, centers: 2, 5
        fire3(InitTreesAl.initTree5());//radius = 3, diameter = 5, centers: 2, 4
        fire3(InitTreesAl.initTree6());//radius = 1, diameter = 2, centers: 0
        fire3(InitTreesAl.initTree7());//radius = 2, diameter = 3, centers: 1, 2
    }
}

