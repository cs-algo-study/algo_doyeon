import java.util.*;
import java.lang.*;
import java.io.*;
 
class Solution {
    static int[] boss;
    static boolean[] visited;
    static int group;
    static Scanner sc = new Scanner(System.in);
     
    public static void main(String[] args) {
        int t = sc.nextInt();
         
        for(int i=1; i<=t; i++){
            System.out.print("#"+i+" ");
            sol();
        }
    }
 
    private static void sol(){
        int n = sc.nextInt(); //인원
        int m = sc.nextInt(); //아는 사이 수
 
        init(n);
         
        int a, b;
        for(int i=0; i<m; i++){
            a = sc.nextInt();
            b = sc.nextInt();
 
            union(a, b);
        }
 
        findGroup(n);
 
        System.out.println(group);
    }
 
    private static void init(int n){
        group = 0;
        boss = new int[n+1];
        visited = new boolean[n+1];
         
        for(int i=0; i<=n; i++){
            boss[i] = i;
        }
    }
 
    private static void union(int a, int b){
        boss[find(a)] = find(b);
    }
 
    private static int find(int now){
        if(boss[now] == now) return now;
        else return boss[now] = find(boss[now]);
    }
 
    private static void findGroup(int n){
        for(int i=1; i<=n; i++){
            if(!visited[find(i)] && !visited[i]) {
                group++;
            }
 
            visited[i] = true;
            visited[find(i)] = true;
        }
    }
}
