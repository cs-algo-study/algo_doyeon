import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    static int n;
    static int dist;
    static boolean visited[];
    static int point[][];
    static int home[] = new int[2];
    static int company[] = new int[2];
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
        int tc = sc.nextInt();

        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            //회사에서 출발, 모든 고객 방문 후 집 최소 경로
            //n <= 10이면 10! 해도 범위 내
            sol();
        }
    }

    private static void sol(){
        dist = Integer.MAX_VALUE;
        n = sc.nextInt();

        point = new int[n][2];
        visited = new boolean[n];

        company[0] = sc.nextInt();
        company[1] = sc.nextInt();

        home[0] = sc.nextInt();
        home[1] = sc.nextInt();
        
        for(int i=0; i<n; i++){
            point[i][0] = sc.nextInt();
            point[i][1] = sc.nextInt();
        }

        dfs(0, -1, 0);

        System.out.println(dist);
    }

    private static void dfs(int depth, int now, int sum){
        if(depth == n){
            dist = Math.min(dist, sum + calc(now, n));
            return;
        }
        
        for(int i=0; i<n; i++){
            if(visited[i]) continue;

            visited[i] = true;
            dfs(depth+1, i, sum + calc(now, i));
            visited[i] = false;
        }
    }

    private static int calc(int a, int b){
        int p[][] = new int[2][2];
        
        if(a == -1) {
            p[0][0] = company[0];
            p[0][1] = company[1];
        }
        else{
            p[0][0] = point[a][0];
            p[0][1] = point[a][1];
        }
        
        if(b == n) {
            p[1][0] = home[0];
            p[1][1] = home[1];
        }
        else{
            p[1][0] = point[b][0];
            p[1][1] = point[b][1];
        }

        return Math.abs(p[0][0] - p[1][0]) + Math.abs(p[0][1] - p[1][1]);
    }
}
