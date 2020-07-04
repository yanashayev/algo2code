package T6;

import java.util.Arrays;

/**
 * One of version of the gasoline puzzle due to Lovasz.
 * On a very long, circular, isolated driving route there are a number of small
 * gas stations, each with a limited supply of gasoline left.
 * First, check out if in total gas stations contain exactly the amount of gasoline
 * needed to get a car around one full circuit.
 * If it's true, prove that, starting with an empty tank, there is some station
 * we can start at which will allow us to complete one trip around the route.
 * Of course, we will be collecting gasoline whenever we pass a station.
 * Assume a car’s tank is large enough to hold as much as necessary,
 * and that no other cars will be taking any of the available gasoline.
 */
public class PetrolStationBest {

    public static boolean PetrolStation(int a[], int b[]){
        // the amount of gasoline at the pump in the i gas station: i -> a[i]
        // the amount of gasoline needed to get a car a next gas station: i -> b[i]
        // sum(b)<=sum(a)
        boolean ans = true;
        int []c = new int[a.length];
        int sum = 0;
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] - b[i];
            sum = sum + c[i];
        }
        System.out.println("Sum of c[i] = "+sum);
        if (sum < 0){
            System.out.println("Not enough gasoline for this driving route");
            ans = false;
        }
        else{
            int[] res = BestCycle.bestCycle(c);
            int start = res[0], amount = 0;
            for (int i = 0; ans && i < res.length; i++) {
                int ind = (start+i)%c.length;
                amount = amount + c[ind];
                if (amount<0){
                    System.out.println("error");
                    ans = false;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int [] a = {5,10,12,100};
        int [] b = {6,11,13,50};
        /////////
        //int [] a = {8,10,12,100};
        //int [] b = {6,11,13,50};
        ///////
        //int [] a = {6, 0, 0, 6, 7, 0, 7, 8, 5, 5};
        //int [] b = {7, 4, 6, 5, 3, 3, 8, 0, 1, 0};
        //int [] b = {8,10,12,100};
        //int [] a = {6,11,13,50};
        System.out.println("Amount of gasoline in the gas stations: " + Arrays.toString(a));
        System.out.println("Amount of gasoline needed to get a next station: " + Arrays.toString(b));
        System.out.println("Can a car completes one trip around the route? "+
                PetrolStation(a,b));
    }
}
