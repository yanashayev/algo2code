package OR;


// Created by Or Kadrawi

/**
 * מציאת תת מטריצה עם סכום מקסימלי:
 *
 * חיפוש שלם n^6
 *
 * בעזרת מערך עזר n^4
 *
 * בעזרת מטריצת עזר n^4
 *
 * אלגוריתם יעיל n^3
 */
public class _07_maxSubmatrixSum {

    static int starti, startj, endk, endl, sum;

    public static void main(String[] args) {

        int[][] mat = {{2,1,-3,-4,5},{0,6,3,4,1},{2,-2,-1,4,-5},{-3,3,1,0,3}};

        Calculate1(mat);//n^6
        //Calculate2(mat);//n^4  uncomment to run
        //Calculate3(mat);//n^4  uncomment to run

        System.out.println("sum = " + sum);
        System.out.println("starti = " + starti);
        System.out.println("startj = " + startj);
        System.out.println("endk = " + endk);
        System.out.println("endl = " + endl);

    }

    private static void Calculate1(int[][] mat) {//n^6
        int currentSum;
        sum = mat[0][0];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                for (int k = i; k < mat.length; k++) {
                    for (int l = j; l < mat.length; l++) {
                        currentSum = 0;
                        for (int x = i; x <= k; x++) {
                            for (int y = j; y <= l; y++) {
                                currentSum+=mat[x][y];
                            }
                        }
                        if (currentSum > sum)
                        {
                            sum = currentSum;
                            starti = i;
                            startj = j;
                            endk = k;
                            endl = l;
                        }
                    }
                }
            }
        }
    }

    private static void Calculate2(int[][] mat) {//n^4
        _06_Best b = new _06_Best();
        sum = mat[0][0];
        int[] arr = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = i; j < mat.length; j++) {
                for (int m = 0; m < arr.length; m++) {
                    arr[m] = 0;
                }
                for (int k = i; k <= j; k++) {
                    for (int l = 0; l < mat[0].length; l++) {
                        arr[l] += mat[k][l];
                    }
                }
                b.Calculate(arr);
                if (b.sum > sum)
                {
                    sum = b.sum;
                    starti = i;
                    startj = b.startIndex;
                    endk = j;
                    endl = b.endIndex;
                }
            }
        }
    }

    private static void Calculate3(int[][] mat) {//n^4
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] help = new int[rows][cols];
        help[0][0] = mat[0][0];
        for (int i = 1; i < rows; i++) {
            help[i][0] = help[i-1][0] + mat[i][0];
        }
        for (int i = 1; i < cols; i++) {
            help[0][i] = help[0][i-1] + mat[0][i];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                help[i][j] = mat[i][j]+help[i-1][j]+help[i][j-1]-help[i-1][j-1];
            }
        }

        int temp;
        sum = help[0][0];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = i; k < rows; k++) {
                    for (int l = j; l < cols; l++) {
                        if (i==0 && j==0)
                        {
                            temp = help[k][l];
                        }
                        else if (i==0)
                        {
                            temp = help[k][l]-help[k][j-1];
                        }
                        else if (j==0)
                        {
                            temp = help[k][l]-help[i-1][l];
                        }
                        else
                            temp = help[k][l] - help[k][j-1] - help[i-1][l]+help[i-1][j-1];

                        if (temp > sum)
                        {
                            sum = temp;
                            starti = i;
                            startj = j;
                            endk = k;
                            endl = l;
                        }
                    }
                }
            }
        }
    }
}