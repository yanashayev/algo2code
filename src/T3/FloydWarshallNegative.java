package T3;
//page 10
public class FloydWarshallNegative {
    //static int inf = Integer.MAX_VALUE; // not good because if we add to this 1 its very large
static int inf =1000000000;
    public static void FWNeg(int [][] mat){
        int n = mat.length;
        // matrix building
        for (int k = 0; k < n; k++){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (mat[i][k]!=inf && mat[k][j]!=inf){
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                        //System.out.println("k="+k+", i="+i+", j="+j+", mat["+i+","+j+"]="+mat[i][j]);
                    }
                }
            }
        }
    }

    //**************************************************

    public static void printMatrix(int[][] mat){
        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat[0].length; j++){
                if (mat[i][j] ==inf)
                    System.out.print("*, ");
                else
                    System.out.print(mat[i][j]+", ");
            }
            System.out.println();
        }
        System.out.println("****************************************");
    }

    //**************************************************
    public static void main(String[] args) {
        int[][]mat1={
                {inf,3,-10},
                {3,inf,-1},
                {-10,-1,inf}
        };

        int[][]mat2={
                {inf,5,-2},
                {5,inf,-1},
                {-2,-1,inf}
        };

        int[][] mat3={
                {0,-1,10},
                {-1,0,2},
                {10,2,0}
        };

        int[][]mat4={
                {0,inf},
                {-5,0}
        };

        int[][]mat5={
                {0,-5},
                {-5,0}
        };

        int[][]mat6={
                {inf,5,2},
                {5,inf,-1},
                {2,-1,inf}
        };

        int[][]mat7={
                {0,5,2},
                {5,0,1},
                {2,1,0}
        };
        int[][]mat8={
                {0,5,inf},
                {inf,0,-10},
                {2,inf,0}
        };

        int[][]mat9={
                {0,5,inf},
                {inf,0,inf},
                {2,-10,0}
        };

        int[][] mat = {{0,1,inf},
                {inf,0,-5},
                {2,inf,0}};

        System.out.println("Before: Directed graph with negative-weight edges");
        printMatrix(mat);
        FWNeg(mat);
        System.out.println("After FW");
        printMatrix(mat);

        System.out.println("\nBefore: Undirected graph with negative-weight edges");
        printMatrix(mat1);
        FWNeg(mat1);
        System.out.println("After FW");
        printMatrix(mat1);

        System.out.println("\nBefore: Undirected graph with negative-weight edges");
        printMatrix(mat2);
        FWNeg(mat2);
        System.out.println("After FW");
        printMatrix(mat2);

        System.out.println("\nBefore: Undirected graph with negative-weight edges");
        printMatrix(mat3);
        FWNeg(mat3);
        System.out.println("After FW");
        printMatrix(mat3);

        System.out.println("\nBefore: Directed graph with negative-weight edges");
        printMatrix(mat4);
        FWNeg(mat4);
        System.out.println("After FW");
        printMatrix(mat4);

        System.out.println("\nBefore: Undirected graph with negative-weight edges");
        printMatrix(mat5);
        FWNeg(mat5);
        System.out.println("After FW");
        printMatrix(mat5);

        System.out.println("\nBefore: Undirected graph with negative-weight edges");
        printMatrix(mat6);
        FWNeg(mat6);
        System.out.println("After FW");
        printMatrix(mat6);

        System.out.println("\nBefore: Undirected graph with non-negative-weight edges");
        printMatrix(mat7);
        FWNeg(mat7);
        System.out.println("After FW");
        printMatrix(mat7);

        System.out.println("\nBefore: Directed graph with negative-weight edges");
        printMatrix(mat8);
        FWNeg(mat8);
        System.out.println("After FW");
        printMatrix(mat8);

        System.out.println("\nBefore: Directed graph with negative-weight edges");
        printMatrix(mat9);
        FWNeg(mat9);
        System.out.println("After FW");
        printMatrix(mat9);
    }

}
