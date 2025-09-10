import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");

            //init
            int n = sc.nextInt();
            int arr[][] = new int[n][2];

            for(int r=0; r<n; r++) arr[r][0] = sc.nextInt();

            //1000+999+...+1 1001 * 500 = 500000
            //sol
            arr[0][1] = 1;
            int max;
            for(int r=1; r<n; r++){
                max = 0;
                for(int k=0; k<r; k++){
                    if(arr[k][0] < arr[r][0] && arr[k][1] > max) max = arr[k][1];
                }

                arr[r][1] = max+1;
            }

            max = 0;
            for(int r=0; r<n; r++) max = Math.max(max, arr[r][1]);

            System.out.println(max);
        }
    }
}
