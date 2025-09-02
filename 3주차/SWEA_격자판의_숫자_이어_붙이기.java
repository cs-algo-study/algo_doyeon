import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    static Scanner sc = new Scanner(System.in);
    static int[][] map = new int[4][4];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static Set<Integer> set = new HashSet<>();
    
    public static void main(String[] args) {
        int tc = sc.nextInt();

        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            init();
            sol();
        }
    }

    private static void sol(){
        //시작점 16개
        for (int i=0; i<4; i++) {
            for (int r=0; r<4; r++) {
                dfs(0, map[i][r], r, i);
            }
        }

        System.out.println(set.size());
    }


    //16개의 시작점* 4^6 = 65,536
    private static void dfs(int depth, int num, int x, int y){
        if(depth == 7){
            if(!set.contains(num)) set.add(num);
            return;
        }
        
        for(int i=0; i<4; i++){
            if(x+dx[i] < 0 || y+dy[i] < 0 || y+dy[i] >= 4 || x+dx[i] >= 4) continue;
            dfs(depth+1, num*10 + map[y][x], x+dx[i], y+dy[i]);
        }
    }

    private static void init(){
        set.clear();
        
        for (int i=0; i<4; i++) {
            for (int r=0; r<4; r++) {
                map[i][r] = sc.nextInt();
            }
        }
    }
}
