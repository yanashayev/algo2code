package OR;


// Created by Or Kadrawi

/**
 * אלגוריתם אופטימלי ל BEST
 *
 * אלגוריתם BEST מעגלי בעזרת מטריצה O(n^3) - חיפוש שלם
 *
 * אלגוריתם BEST מעגלי בעזרת מטריצה O(n^2) - חיפוש שלם חכם תכנון דינאמי
 *
 * אלגוריתם BEST בעזרת מערך כפול O(n^2)
 *
 * אלגוריתם BEST אופטימלי max{best(a),S+best(-A)}
 *
 * בעית תחנות הדלק
 */
public class _06_Best {

   static int startIndex;
   static int endIndex;
    static int sum;

    public static void Calculate(int[] a)
    {
        int max = a[0],
                temp_max = 0,start = 0,
                end = 0, s = 0;

        for (int i = 0; i < a.length; i++)
        {
            temp_max += a[i];

            if (max < temp_max)
            {
                max = temp_max;
                start = s;
                end = i;
            }

            if (temp_max < 0)
            {
                temp_max = 0;
                s = i + 1;
            }
        }
        sum =  max;
        startIndex =  start;
        endIndex = end;
        System.out.println("sum: "+sum+ "start index : "+start+ "end index: "+endIndex);
    }

    public static void main(String[] args) {
        int []a={1,2,-1,200,12,-100};
        Calculate(a);
    }

}