import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    static final int MAX_VAL = 3000000;
    static int n, arr[][];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int tc = sc.nextInt();
        int now;

        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            
            n = sc.nextInt();
            arr = new int[n][n];
            
            for(int r=0; r<n; r++) {
                for(int k=0; k<n; k++){
                    now = sc.nextInt();
                    if(r == k) arr[r][k] = 0;
                    else if(now == 1) arr[r][k] = 1;
                    else arr[r][k] = MAX_VAL;
                }
            }
            
            sol();
            findAnswer();
        }
    }

    private static void sol(){
        for(int i=0; i<n; i++){
            for(int r=0; r<n; r++) {
                for(int k=0; k<n; k++){
                    arr[r][k] = Math.min(arr[r][k], arr[r][i] + arr[i][k]);
                }
            }
        }
    }

    private static void findAnswer(){
        int min = MAX_VAL;
        int now;

        for(int i=0; i<n; i++){
            now = 0;
            for(int r=0; r<n; r++){
                now += arr[i][r];
            }
            min = Math.min(min, now);
        }

        System.out.println(min);
    }

}
