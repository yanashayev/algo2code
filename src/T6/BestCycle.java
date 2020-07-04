package T6;

import java.util.Arrays;

public class BestCycle {
    /**
     * The maximum sub-array/sub-interval problem:
     * finding the contiguous sub-array within an one-dimensional array of numbers
     * which has the largest sum.
     * Optimal Best solution
     * Complexity: O(n)
     * @param a an one-dimensional array of numbers
     * @return array with 4 parameters: begin index, end index of sub-array,
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
            //printBest(a, ans);
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
            //printBest(a, ans);
            return ans;
        }
    }

    /**
     * Max-Sum subinterval of a Cycle/Circular array:
     * finding the contiguous sub-array within a cycle/circular array of numbers
     * which has the largest sum.
     * Minus Best solution: max-sum is max(Best(A), Sum(A) + Best(-A))
     * Complexity: O(n)
     * @param a a cycle array of numbers
     * @return sub-array with the largest sum
     *
     */
    public static int[] bestCycle(int[] a){
        int[] ans1 = best(a);
        int sum1 = ans1[3]; //max-sum of a sub-interval in the source array
        if (sum1<0){
            return ans1;
        }

        int t[] = new int[a.length]; //an opposite array for the source array
        int sumA = 0; //sum of all elements in the source array
        for (int i = 0; i < t.length; i++) {
            sumA = sumA + a[i];
            t[i] = -a[i];
        }
        int[] ans2 = best(t);
        //System.out.println("-A: " + Arrays.toString(ans2));
        int sum2 = sumA + ans2[3]; //max-sum of a sub-interval in the cycle array

        if (sum1 >= sum2){
            printBest(a, ans1);
            return ans1;
        }
        else{
            int ans[] = {(ans2[1]+1)%a.length, (ans2[0]-1)%a.length, a.length-ans2[2], sum2};
            printBestCycle(a, ans);//ans2={beg, end, count, sum}
            return ans;
        }
    }

    /**
     * Max-Sum subinterval of a Cycle array:
     * finding the contiguous sub-array within a cycle array of numbers
     * which has the largest sum.
     * Minus Best solution: max-sum is max(Best(A), Sum(A) + Best(-A))
     * Complexity: O(n)
     * @param a a cycle array of numbers
     * @return array with 4 parameters: begin index, end index of sub-array,
     * 		   length of sub-interval, max sum of the interval
     */
    public static int[] bestCycle2(int[] a){
        int[] ans1 = best(a);
        int sum1 = ans1[3]; //max-sum of a sub-interval in the source array
        if (sum1 < 0){
            int result[] = {sum1};
            return result;
        }

        int t[] = new int[a.length]; //an opposite array for the source array
        int sumA = 0; //sum of all elements in the source array
        for (int i = 0; i < t.length; i++) {
            sumA = sumA + a[i];
            t[i] = -a[i];
        }
        int[] ans2 = best(t);
        int sum2 = sumA + ans2[3]; //max-sum of a sub-interval in the cycle array

        if (sum1 >= sum2){ //max-sum is a Best(A)
            printBest(a, ans1);
            int beginMax=ans1[0], countmax = ans1[2];
            int result[] = new int[countmax];
            for (int i = 0; i < countmax; i++) {
                result[i] = a[beginMax+i];
            }
            return result;
        }
        else{//max-sum is Sum(A) + Best(-A)
            int ans[] = {(ans2[1]+1)%a.length, (ans2[0]-1)%a.length, a.length-ans2[2], sum2};
            int beginMax=ans[0], countmax = ans[2];
            int result[] = new int[countmax];
            for (int i = 0; i < countmax; i++) {
                result[i] = a[(beginMax+i)%a.length];
            }
            printBestCycle(a, ans);//ans2={beg, end, count, sum}
            return result;
        }
    }

    public static void printBestCycle(int[]a ,int[] res){
        int beginMax=res[0], endMax = res[1], countmax = res[2], sumMax = res[3];
        System.out.println("beginMax = " + beginMax + ", endMax = " + endMax + ", sumMax = "+sumMax + ", lengthMax = "+countmax);
        System.out.print("sub array cycle best: ");
        for (int i = 0; i < countmax; i++) {
            System.out.print(a[(beginMax+i)%a.length] + ", ");
        }
        System.out.println();
    }
    public static void printBest(int[]a ,int[] res){
        int beginMax=res[0], endMax = res[1], countmax = res[2], sumMax = res[3];
        System.out.println("beginMax = " + beginMax + ", endMax = " + endMax + ", sumMax = "+sumMax + ", lengthMax = "+countmax);
        System.out.print("sub array best: ");
        for (int i = 0; i < countmax; i++) {
            System.out.print(a[beginMax+i] + ", ");
        }
        System.out.println();
    }


    public static void checkBestCycle(){
        int[]arr1 = {2, 0, -5, 2};
        System.out.println("\n"+Arrays.toString(arr1));
        bestCycle(arr1);

        int[]arr2 = {-2, 0, +5, -2};
        System.out.println("\n"+Arrays.toString(arr2));
        bestCycle(arr2);

        int arr3[] = {1,10,-15,3,-10};
        System.out.println("\n"+Arrays.toString(arr3));
        bestCycle(arr3);

        int arr4[] = {10,2,-5,8,-100,3,50,-80,1,2,3}; //sum=53
        System.out.println("\n"+Arrays.toString(arr4));
        bestCycle(arr4);

        int arr5[] = {10,2,-5,8,-100,3,50,-80,1,2,3,88}; //sum=94
        System.out.println("\n"+Arrays.toString(arr5));
        bestCycle(arr5);

        int arr6[] = {10,2,-5,8,-100,3,150,-180,1,2,3,88}; //best sum=94, cycle sum = 106
        System.out.println("\n"+Arrays.toString(arr6));
        bestCycle(arr6);

        int arr[] = {90,2,-5,8,-100,3,50,-80,1,2,3}; //best sum=95,cycle sum = 101
        System.out.println("\n"+Arrays.toString(arr));
        bestCycle(arr);

        //best(arr);

		/*
		int arr7[] = {5, 100, -150, 5, 20, 100};
		System.out.println(bestDP2(arr7));
		bestExhaustiveSearchN2(arr7);
		bestExhaustiveSearchN3(arr7);
		*/
    }

    /////////////////////////////////////////////////////////
    /**
     * The maximum subarray problem in a circular array:
     * finding the contiguous subarray within a circular array of numbers
     * which has the largest sum.
     * Exhaustive Search (Full Search)
     * Complexity: O(n^3)
     * @param arr an array of numbers
     */
    public static void bestExhaustiveSearchN3(int arr[]){
        int sum = 0, sumMax = arr[0], start = 0, end = 0, length=0;
        for (int i=0; i<arr.length; i++){
            for (int j = i; j < arr.length; j++) {
                sum = 0;
                for (int k=i; k < arr.length+j; k++){
                    sum = sum + arr[(k%arr.length)];
                    if (sum > sumMax){
                        sumMax = sum;
                        start = i;
                        end = (k%arr.length);
                    }
                }
            }
        }
        if(start<end)
            length = end + 1 - start;
        else
            length = arr.length -start + 1 + end;
        System.out.println(Arrays.toString(arr) + "\nbestExhaustiveSearchN3: sumMax = "+sumMax+", interval: ["+start + ","+end+"], length: "+length);
    }

    /**
     * The maximum subarray problem in a circular array:
     * finding the contiguous subarray within a circular array of numbers
     * which has the largest sum.
     * Exhaustive Improved Search (Full Search)
     * Complexity: O(n^2)
     * @param arr an array of numbers
     */
    public static void bestExhaustiveSearchN2(int arr[]){
        int sum = 0, sumMax = arr[0], start = 0, end = 0, length=0;
        for (int i=0; i<arr.length; i++){
            sum = 0;
            for (int j=i; j < arr.length+i; j++){
                sum = sum + arr[(j%arr.length)];
                if (sum > sumMax){
                    sumMax = sum;
                    start = i;
                    end = (j%arr.length);
                }
            }
        }
        if(start<end)
            length = end + 1 - start;
        else
            length = arr.length -start + 1 + end;
        System.out.println(Arrays.toString(arr) + "\nbestExhaustiveSearchN2: sumMax = "+sumMax+", interval: ["+start + ","+end+"], length: "+length);
    }

    /**
     * The maximum subarray problem in a circular array:
     * finding the contiguous subarray within in a circular array of numbers
     * which has the largest sum.
     * Dynamic programming:
     * first, for an original array calculation at an upper triangular by bottom up: mat[i][j] = mat[i+1][j] + a[i]
     * second, for a circular array calculation at a lower triangular: mat[i][j] = sumA - mat[j+1][i-1]
     * Complexity: O(n^2)
     * @param a an array of numbers
     * @return a max sum of the interval
     */
    public static int bestDP2(int[]a){
        int n = a.length;
        int [][]mat = new int[n][n];
        int sumMax=a[0], beginMax=0, endMax=0, length=0;
        int sumA = 0;
        for (int i=0; i<n; i++){
            mat[i][i] = a[i];
            sumA = sumA + a[i];
            if (sumMax < a[i]){
                sumMax = a[i];
                beginMax = endMax = i;
            }
        }
        for (int i=n-1; i>=0; i--){
            for (int j=i+1; j<n; j++){
                mat[i][j] = mat[i+1][j] + a[i];
                if (sumMax < mat[i][j]){
                    sumMax = mat[i][j];
                    beginMax = i;
                    endMax = j;
                }
            }
        }
        //for a cycle array
        for (int i=n-1; i>=0; i--){
            for (int j=0; j<n;j++){
                if(i>j) {
                    mat[i][j] = sumA - mat[j+1][i-1];
                    if (sumMax < mat[i][j]){
                        sumMax = mat[i][j];
                        beginMax = i;
                        endMax = j;
                    }
                }
            }
        }
        if(beginMax<endMax)
            length = endMax + 1 - beginMax;
        else
            length = n-beginMax + 1 + endMax;//for a cycle array
        System.out.println(Arrays.toString(a) + " sumMax = "+sumMax+", interval: ["+beginMax + ","+endMax+"], length: "+length);

        return sumMax;
    }

    /////////////////////////////////////////////////////////
    public static int bestShortestSubInterval(int[] a){
        int i=0;
        while (i<a.length && a[i]<=0) {i++;}
        if (i == a.length){// a[i]<=0, i=1,...,a.length
            return 1;
        }
        else{
            int  endMax = i+1, count = 0, countMax = 1, bestCount = 1;
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
                    endMax = i+1;
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
        System.out.println(Arrays.toString(bestCycle2(a1)));
        System.out.println("len2 = " + bestShortestSubInterval(a2));
        System.out.println("len3 = " + bestShortestSubInterval(a3));
        System.out.println("len4 = " + bestShortestSubInterval(a4));
        System.out.println("len5 = " + bestShortestSubInterval(a5));
        System.out.println("len6 = " + bestShortestSubInterval(a6));
        System.out.println("len7 = " + bestShortestSubInterval(a7));
    }
    /////////////////////////////////////////////////////////
    public static void main(String[] args) {
        //checkBestCycle();
        checkbestShortestSubInterval();
    }
}
