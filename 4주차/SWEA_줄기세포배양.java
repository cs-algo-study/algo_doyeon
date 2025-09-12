import java.util.*;
import java.lang.*;
import java.io.*;

class Node{
    int x, y, time;

    Node(int y, int x, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

class Solution {
    static int n, m, k;
    static int map[][][] = new int[550][550][2];
    static LinkedList<Node> newSell = new LinkedList<>();
    static Queue<Node> notActiveSell = new LinkedList<>();
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {1, -1, 0, 0};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for(int i=1; i<=tc; i++){
            System.out.print("#" + i + " ");

            map = new int[550][550][2];
            newSell.clear();
            notActiveSell.clear();
           
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();

            int now;
            for(int r=250; r<250+n; r++){
                for(int q=250; q<250+m; q++){
                    now = sc.nextInt();

                    if(now == 0){
                        map[r][q][0] = 0;
                    } 
                    
                    else{
                        map[r][q][0] = 1;
                        map[r][q][1] = now;
                        notActiveSell.add(new Node(r, q, map[r][q][1]));
                    }

                    //50*50이고 최대 300시간 걸리니까
                    //최대 650 * 650의 크기인 것 같으니
                    //1000*1000 짜리 배열로 하고 중간에 얘를 배치하는 게 좋을듯
                }
            }
            sol();
        }
    }
       
    //0 - 빈 공간
    private static void sol(){
        for(int i=0; i<k; i++){
            notActiveSell.add(new Node(-1, -1, -1));
            newSell.add(new Node(-1, -1, -1));
            
            active();
            noActive();
        }

        System.out.println(notActiveSell.size() + newSell.size());
    }

    //활성 상태가 되어야 넓힐 수 있다.
    //비활성상태면 계속 대기.

    //비활성상태 
    private static void noActive(){
        Node node;
        
        while(!notActiveSell.isEmpty()){
            node = notActiveSell.poll();
            if(node.time == -1) break;

            if(node.time == 1) {
                newSell.add(new Node(node.y, node.x, map[node.y][node.x][1]));
            }
            else notActiveSell.add(new Node(node.y, node.x, node.time-1));
        }
    }

    //감소시키는 건 상관없지만 증가하는 건 우선순위 고려해서 뻗어나가야 한다.
    //활성상태
    private static void active(){
        Collections.sort(newSell, new Comparator<Node>(){
            public int compare(Node o1, Node o2){
                return o2.time - o1.time;
            }
        });
        
        Node node;
        
        while(!newSell.isEmpty()){
            node = newSell.poll();
            if(node.time == -1) break;

            //아직 살아있는 경우 
            if(node.time > 1){
                newSell.add(new Node(node.y, node.x, node.time - 1));
            }

            //이미 퍼졌던 것인지 확인
            if(map[node.y][node.x][0] == 2) continue;
            
            //퍼져나감
            map[node.y][node.x][0] = 2;
            for(int i=0; i<4; i++) {
                //빈 공간이면 넓힌다
                if(map[node.y+dy[i]][node.x+dx[i]][0] == 0) {
                    notActiveSell.add(new Node(node.y+dy[i], node.x+dx[i], node.time));
                    map[node.y+dy[i]][node.x+dx[i]][0] = 1;
                    map[node.y+dy[i]][node.x+dx[i]][1] = map[node.y][node.x][1];
                }
            }
        }
    }
}
