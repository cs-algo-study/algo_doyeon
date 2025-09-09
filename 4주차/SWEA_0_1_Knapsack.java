import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    static Scanner sc = new Scanner(System.in);
    static int n, k, info[][], dp[][];
    
    public static void main(String[] args) {
        int tc = sc.nextInt();
        
        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            init();
            sol();
        }
    }
    
    private static void sol(){
        //무게
        for(int i=0; i<k+1; i++){
            //각 물건
            for(int r=1; r<n+1; r++){
                if(i - info[r][0] < 0) dp[i][r] = dp[i][r-1];
                else dp[i][r] = Math.max(dp[i][r-1], dp[i-info[r][0]][r-1] + info[r][1]);
            }
        }

        System.out.println(dp[k][n]);
    }

    private static void init(){
        n = sc.nextInt();
        k = sc.nextInt();

        info = new int[n+1][2];
        dp = new int[k+1][n+1];

        for(int i=1; i<=n; i++){
            info[i][0] = sc.nextInt();
            info[i][1] = sc.nextInt();
        }
    }
}
