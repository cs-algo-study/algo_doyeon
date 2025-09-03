import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    static Scanner sc = new Scanner(System.in);
    static int table[] = new int[4];
    static int plan[]= new int[12];
    static int dp[] = new int[12];
    
    public static void main(String[] args) {
        int tc = sc.nextInt();

        for (int i=1; i<=tc; i++) {
            System.out.print("#"+i+" ");
            init();
            sol();
        }
    }

    private static void sol() {
        for (int i=0; i<12; i++) {
            //1일
            if(i>0) dp[i] = dp[i-1] + plan[i] * table[0];
            else dp[i] = plan[i] * table[0];

            //1달
            if(i>0) dp[i] = Math.min(dp[i], dp[i-1] + table[1]);
            else dp[i] = Math.min(dp[i], table[1]);

            //3개월
            if(i-2 >= 0) {
                if (i-3 >= 0) dp[i] = Math.min(dp[i], dp[i-3] + table[2]);
                else dp[i] = Math.min(dp[i], table[2]);
            }
        }

        System.out.println(Math.min(dp[11], table[3]));
    }

    private static void init() {
        table[0] = sc.nextInt(); //1일 이용권
        table[1] = sc.nextInt(); //1달 이용권
        table[2] = sc.nextInt(); //3달 이용권
        table[3] = sc.nextInt(); //1년 이용권

        for(int i=0; i<12; i++){
            plan[i] = sc.nextInt();
            dp[i] = 36001;
        }
    }
}
