import java.util.*;
import java.lang.*;
import java.io.*;
 
class Solution {
    static int n, m, k, homeCnt, map[][], serv[][];
    static Scanner sc = new Scanner(System.in);
     
    public static void main(String[] args) {
        int tc = sc.nextInt();
 
        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            init();
            sol();
        }
    }
 
    private static void init(){
        n = sc.nextInt();
        m = sc.nextInt();
 
        k = n+2;
        homeCnt = 0;
        map = new int[n][n];
        serv = new int[n*n][2];
         
        for(int i=0; i<n; i++){
            for(int r=0; r<n; r++){
                map[i][r] = sc.nextInt();
                if(map[i][r] == 1){
                    serv[homeCnt][0] = i;
                    serv[homeCnt][1] = r;
                    homeCnt++;
                }
            }
        }
    }
 
    //운영 비용 = k*k + (k-1) * (k-1)
    //수익 = m*포함 집 수
 
    //손해 안 보고 가장 많은 집에 제공 시에 집 수
    private static void sol(){
        int answer = 0;
 
        while(k-- > 0) {
            for(int i=0; i<n; i++) {
                for(int r=0; r<n; r++) {
                    int cnt = 0;
                    //맨하탄 거리가 k보다 작다면 방범 서비스에 포함
                    for(int q=0; q < homeCnt; q++) {
                        int x, y;
                        x = serv[q][0];
                        y = serv[q][1];
                        if(Math.abs(i-x) + Math.abs(r-y) < k)cnt++;
                    }
                    if(k*k+(k-1)*(k-1) <= cnt * m && answer < cnt)
                        answer = cnt;
                }
            }
             
            if(answer > 0) break;
        }
 
        System.out.println(answer);
    }
}
