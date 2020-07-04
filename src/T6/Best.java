package T6;

import java.util.Arrays;

public class Best {
    /**
     * The maximum sub-array/sub-interval problem:
     * finding the contiguous sub-array within an one-dimensional array of numbers
     * which has the largest sum.
     * Optimal Best solution
     * Complexity: O(n)
     * @param a an one-dimensional array of numbers
     * @return array with 4 parameters:
     * 		   begin index, end index of sub-array,
     * 		   length of sub-interval, max sum of the interval
     */
    public static int[] best(int[] a){
        int i=0;
        //check if all numbers in a[] are negative
        while (i<a.length && a[i]<=0) {i++;}
        //extreme case: all numbers in a[] are negative
        if (i == a.length){
            int index = 0;
            for (int j=0; j<a.length; j++){
                if (a[j] > a[index]) index = j;
            }
            int[]ans = {index, index, 1, a[index]};
            printBest(a, ans);
            return ans;
        }
        //normal case: not all elements are negative
        else{
            int sum=0, maxSum=a[i], beginMax = i, endMax = i, count = 0, countMax = 1;
            while(i<a.length){
                sum = sum + a[i];
                count++;
                if (sum < 0){
                    sum = 0;
                    count = 0;
                }
                else if (sum > maxSum){
                    maxSum = sum;
                    endMax = i;
                    countMax = count;
                }
                i++;
            }
            beginMax = endMax + 1 - countMax;
            int[]ans = {beginMax, endMax, countMax, maxSum};
            printBest(a, ans);
            return ans;
        }
    }

    /**
     * Print 4 parameters: begin index, end index of sub-array,
     * 		   max sum of the interval, elements of sub-interval
     * @param a given array
     * @param res result array contains 4 parameters: begin index, end index of sub-array,
     * 		   number of sub-interval, max sum of the interval
     */
    public static void printBest(int[]a ,int[] res){
        int beginMax=res[0], endMax = res[1], countmax = res[2], sumMax = res[3];
        System.out.println("beginMax = " + beginMax + ", endMax = " + endMax + ", sumMax = "+sumMax + ", lengthMax = "+countmax);
        System.out.print("sub array best: ");
        for (int i = 0; i < countmax; i++) {
            System.out.print(a[beginMax+i] + ", ");
        }
        System.out.println();
    }

    public static int bestShortestSubInterval(int[] a){
        int i=0;
        while (i<a.length && a[i]<=0) {i++;}
        if (i == a.length){// a[i]<=0, i=1,...,a.length
            return 1;
        }
        else{
            int endMax = i, count = 0, countMax = 1, bestCount = 1;
            double maxSum=a[i], sum = 0;
            while(i<a.length){
                sum = sum + a[i];
                count++;
                if (sum <= 0){
                    sum = 0;
                    count = 0;
                }
                else if (sum > maxSum){
                    maxSum = sum;
                    endMax = i;
                    countMax = count;
                    bestCount = countMax;
                }
                else if(sum == maxSum){
                    if (count < bestCount){
                        bestCount = count;
                    }
                }
                i++;
            }
            return bestCount;
        }
    }

    public static void checkbestShortestSubInterval(){
        int[]a1 = {1,2,3,-50,2,4,-34,4};//2
        int[]a2 = {1,2,3,-50,2,4,-34,6};// 1
        int[]a3 = {3,3,-50,1,2,3,-34,4};// 2
        int[]a4 = {1,2,2,1,-50,2,4,-34,1,2,3};//2
        int[]a5 = {-1,-2,-2,-1,-50};// 1
        int[]a6 = {6,-50,1,2,3,-34,3,3};//1
        int a7[] = {1,1,-2,3,1,4,2,4,-3,-4,3,-1,2,0,2,3,2,0,3,-2};//16
        System.out.println("len1 = " + bestShortestSubInterval(a1));
        System.out.println("len2 = " + bestShortestSubInterval(a2));
        System.out.println("len3 = " + bestShortestSubInterval(a3));
        System.out.println("len4 = " + bestShortestSubInterval(a4));
        System.out.println("len5 = " + bestShortestSubInterval(a5));
        System.out.println("len6 = " + bestShortestSubInterval(a6));
        System.out.println("len7 = " + bestShortestSubInterval(a7));
    }

    public static void checkBest(){
        int[] arr1 = {5, 100, -150, 5, 20, 100}; // sum = 125
        System.out.println("arr1: " + Arrays.toString(arr1));
        best(arr1);

        int arr2[] = {1,10,-15,3,-10}; // sum = 11
        System.out.println("\narr2: " + Arrays.toString(arr2));
        best(arr2);

        int arr3[] = {10,2,-5,8,-100,3,50,-80,1,2,3}; // sum = 53
        System.out.println("\narr3: " + Arrays.toString(arr3));
        best(arr3);

        int arr4[] = {10,2,-5,8,-100,3,50,-80,1,2,3,88}; //	sum = 94
        System.out.println("\narr4: " + Arrays.toString(arr4));
        best(arr4);

        int arr5[] = {10,2,-5,8,-100,3,150,-180,1,2,3,88}; // sum = 153
        System.out.println("\narr5: " + Arrays.toString(arr5));
        best(arr5);

        int arr6[] = {90,2,-5,8,-100,3,50,-80,1,2,3}; // sum = 95
        System.out.println("\narr6: " + Arrays.toString(arr6));
        best(arr6);

        int arr7[] = {90,2,-5,8,-100,3,50,-80,1,2,3,120}; // sum = 126
        System.out.println("\narr7: " + Arrays.toString(arr7));
        best(arr7);

        int arr8[] = {90,2,-5,-100,-3,500,-800,1,2,3,120}; // sum = 500
        System.out.println("\narr8: " + Arrays.toString(arr8));
        best(arr8);

        int arr9[] = {90,2,-5,-100,-3,500,-800,100,200,30,120};	//	sum = 500
        System.out.println("\narr9: " + Arrays.toString(arr9));
        best(arr9);

        int arr10[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; // sum = 6
        System.out.println("\narr10: " + Arrays.toString(arr10));
        best(arr10);

        int[] arr11 = {-2, -8, -1, -5, -2}; //sum = -1
        System.out.println("\narr11: " + Arrays.toString(arr11));
        best(arr11);

        int[] arr12 = {2, 8, 1, 5, 2}; //sum = 18
        System.out.println("\narr12: " + Arrays.toString(arr12));
        best(arr12);
    }
    public static void main(String[] args) {
        //checkBest();
        checkbestShortestSubInterval();
    }

}
