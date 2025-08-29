import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    static Scanner sc = new Scanner(System.in);
    static int n, w, h, min;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) {
        int tc = sc.nextInt();

        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            init();
            dfs(0, map);

            System.out.println(min);
        }
    }

    private static void dfs(int depth, int[][] nowMap){
        if(depth == n){
            min = Math.min(min, countBricks(nowMap));
            return;
        }

        for(int i=0; i<w; i++){
            int[][] newMap = copyMap(nowMap);
            dropBall(i, newMap);
            dfs(depth + 1, newMap);
        }
    }

    static void dropBall(int col, int[][] map){
        int row = -1;
        for(int r=0; r<h; r++){
            if(map[r][col] != 0){
                row = r;
                break;
            }
        }

        if(row == -1) return;

        boom(row, col, map);

        deleteBlank(map);
    }

    static void boom(int r, int c, int[][] map){
        Queue<int[]> q = new LinkedList<>();
        if(map[r][c] > 1) q.add(new int[]{r, c, map[r][c]});
        else q.add(new int[]{r, c, 1});
        map[r][c] = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int range = cur[2];

            for(int d=0; d<4; d++){
                int nr = cur[0];
                int nc = cur[1];

                for(int k=1; k<range; k++){
                    nr += dr[d];
                    nc += dc[d];
                    
                    if(nr < 0 || nr >= h || nc < 0 || nc >= w) break;
                    if(map[nr][nc] == 0) continue;

                    if(map[nr][nc] > 1){
                        q.add(new int[]{nr, nc, map[nr][nc]});
                    }

                    map[nr][nc] = 0;
                }
            }
        }
    }

    static void deleteBlank(int[][] map){
        for(int r=0; r<w; r++){
            int[] temp = new int[h];
            int idx = h-1;

            for(int c=h-1; c>=0; c--){
                if(map[c][r] != 0){
                    temp[idx--] = map[c][r];
                }
            }

            for(int c=0; c<h; c++){
                map[c][r] = temp[c];
            }
        }
    }

    static int[][] copyMap(int[][] src) {
        int[][] dest = new int[h][w];
        for (int i = 0; i < h; i++) {
            dest[i] = src[i].clone();
        }
        return dest;
    }

    static int countBricks(int[][] map) {
        int cnt = 0;
        for (int i=0; i<h; i++) {
            for (int r=0; r<w; r++) {
                if (map[i][r] != 0) cnt++;
            }
        }
        return cnt;
    }


    //n<=4, w<=12이므로 최대의 경우의 수는 12^4 = 20736
    //브루트포스 돌려도 될 것 같다
    private static void init(){
        n = sc.nextInt();
        w = sc.nextInt();
        h = sc.nextInt();

        map = new int[h][w];
        
        for(int i=0; i<h; i++){
            for(int r=0; r<w; r++){
                map[i][r] = sc.nextInt();
            }
        }

        min = Integer.MAX_VALUE;
    }
}
