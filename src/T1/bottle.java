package T1;

public class bottle {

        // the matrix initialization
        public static boolean[][] initBooleMatrBottle(int m, int n){
            int dim = (m+1)*(n+1); // matrix dimension
            boolean [][]mat = new boolean[dim][dim];
            for (int i=0; i<dim; i++){
                for (int j=0; j<dim; j++){
                    mat[i][j]= false;
                }
            }

            //with 2 fors !!!
            for (int i=0; i<=m; i++){
                for (int j=0; j<=n; j++){
                    int ind = index(i,j,n); // which line in the matrix
                    //i,3=0

                    mat[ind][index(0,j,n)]=true;//empty left (i, j)  --> (0, j)
                    mat[ind][index(i,0,n)]=true;//empty right (i, j) -->  (i, 0)
                    mat[ind][index(i,n,n)]=true; //fill right (i, j) -->  (i, n)
                    mat[ind][index(m,j,n)]=true;//fill left (i, j) -->  (m, j)
                    int i1=index(Math.max(0,i+j-n),Math.min(n,i+j) ,n); //to pour from left to right
                    mat[ind][i1]=true; //(i, j) --> (max(0, i + j - n), min(n, i + j))
                    i1 = index(Math.min(m,i+j),Math.max(0,j+i-m) ,n); // pour from right to left
                    mat[ind][i1]=true; //(i, j) --> (min(m, i + j), max(0, i + j - m))
                }
            }
            //with one for:
            int i,j;
            for(int k=0; k<mat.length;k++){
                i=getI(k,n);
                j=getJ(k,n);
                mat[k][index(0,j,n)]=true;
                mat[k][index(i,0,n)]=true;
                mat[k][index(m,j,n)]=true;
                mat[k][index(i,n,n)]=true;
                mat[k][index(Math.max(0,i+j-0),Math.min(n,i+j),n)]=true;
                mat[k][index(Math.min(m,i+j),Math.max(0,j+i-m) ,n)]=true;

            }
            return mat;
        }
    public static int getI(int k, int n){
        return k/(n+1);
    }
    public static int getJ(int k, int n){
        return k%(n+1);
    }
        //the index calculation
        private static int index(int i, int j, int n){
            return (n+1)*i + j; // culculate k by i,j and n
        }
        public static void printBoolMatrix(boolean [][] b){
            for(int i=0; i<b.length; i++){
                for(int j=0; j<b[0].length; j++){

                    System.out.print(b[i][j]+"\t");
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {
            boolean m[][] = initBooleMatrBottle(2,1);
            printBoolMatrix(m);

        }

    }

