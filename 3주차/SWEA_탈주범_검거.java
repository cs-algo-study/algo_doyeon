import java.util.*;
import java.lang.*;
import java.io.*;
 
class Point{
    int y, x, depth;
 
    Point(int y, int x, int depth){
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}
 
class Solution {
    static Scanner sc = new Scanner(System.in);
    static int n, m, r, c, time;
    static int[][] map;
    static boolean[][] visited;
     
    static int[] dx = {0, 0, -1, 1}; //아래, 위, 왼, 오
    static int[] dy = {1, -1, 0, 0};
 
    static boolean[][] connect = new boolean[8][4];
    static Queue<Point> queue = new LinkedList<>();
     
    public static void main(String[] args) {
        int tc = sc.nextInt();
 
        baseInfo();
         
        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            init();
            sol();
        }
    }
 
    //일단 연결될수있는 위치를찾아야한다
    private static void sol(){
        queue.add(new Point(r, c, 1));
        BFS();
 
        int answer = 0;
 
        for(int i=0; i<n; i++){
            for(int r=0; r<m; r++){
                if(visited[i][r]) answer++;
            }
        }
         
        System.out.println(answer);
    }
 
    private static void BFS(){
        Point p;
         
        while(!queue.isEmpty()){
            p = queue.poll();
 
            visited[p.y][p.x] = true;
 
            if(p.depth == time) continue;
 
            for(int i=0; i<4; i++){
                if(p.y+dy[i] < 0 || p.x+dx[i] < 0 || 
                   p.y+dy[i] >= n || p.x+dx[i] >= m || visited[p.y+dy[i]][p.x+dx[i]]) continue;
            
                if(connect[map[p.y][p.x]][i]){
                    //이어져 있는지 검증
                    if(i%2 == 0 && connect[map[p.y+dy[i]][p.x+dx[i]]][i+1]) {
                        queue.add(new Point(p.y+dy[i], p.x+dx[i], p.depth+1));
                    }
                    if(i%2 == 1 && connect[map[p.y+dy[i]][p.x+dx[i]]][i-1]) {
                        queue.add(new Point(p.y+dy[i], p.x+dx[i], p.depth+1));
                    }
                }
            }
        }
         
    }
 
    private static void init(){
        n = sc.nextInt();
        m = sc.nextInt();
 
        map = new int[n][m];
        visited = new boolean[n][m];
         
        r = sc.nextInt();
        c = sc.nextInt();
 
        time = sc.nextInt();
 
        queue.clear();
 
        for(int i=0; i<n; i++){
            for(int r=0; r<m; r++){
                map[i][r] = sc.nextInt();
            }
        }
    }
 
    private static void baseInfo(){
        //하, 상, 좌, 우
        inputInConnect(1, true, true, true, true);
        inputInConnect(2, true, true, false, false);
        inputInConnect(3, false, false, true, true);
        inputInConnect(4, false, true, false, true);
        inputInConnect(5, true, false, false, true);
        inputInConnect(6, true, false, true, false);
        inputInConnect(7, false, true, true, false);
    }
 
    private static void inputInConnect(int i, boolean a, boolean b, 
                                      boolean c, boolean d){
        connect[i][0] = a;
        connect[i][1] = b;
        connect[i][2] = c;
        connect[i][3] = d;
    }
}
