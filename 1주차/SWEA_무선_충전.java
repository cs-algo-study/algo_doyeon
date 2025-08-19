import java.util.*;
import java.lang.*;
import java.io.*;

class Point{
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution{
static Scanner sc = new Scanner(System.in);
    static int map[][][] = new int[10][10][2]; //각 좌표에 존재하는 배터리의 효율 기록
    static Point ap, bp;
    static int sum = 0;
    static int[] infoBC;
    
    public static void main(String[] args) {
        int t = sc.nextInt();
        
        //충전한 양의 합의 최댓값
        //-> 따라서 여러 충전기가 겹친 공간이라면
                //혼자라면 가장 효율이 좋은 곳으로,
                //여러명이라면 서로다른 것에 접속

        for(int i=0; i<t; i++){
            System.out.print("#" + (i+1) + " ");
            sum = 0;
            
            sol();
        }
    }

    private static void sol(){
        int m = sc.nextInt(); //이동 시간
        int a = sc.nextInt(); //배터리 개수 

        initMap(a);
        
        infoBC = new int[a + 1];
        infoBC[a] = 0;
        int moveA[] = new int[m];
        int moveB[] = new int[m];

        for(int i=0; i<m; i++){
            moveA[i] = sc.nextInt();
        }
        for(int i=0; i<m; i++){
            moveB[i] = sc.nextInt();
        }

        //배터리 info
        for(int i=0; i<a; i++){
            drawBC(i);
        }
        
        //유저 이동 + 계산
        userMove(m, moveA, moveB);

        System.out.println(sum);
     }

    private static void userMove(int m, int[] moveA, int[] moveB){
        ap = new Point(0, 0);
        bp = new Point(9, 9);

        //초기 위치에서 충전
        sum += calcGetCharge();
        
        //이동 시작
        for(int i=0; i<m; i++){
            //a
            checkNowPoint(true, moveA[i]);
            
            //b
            checkNowPoint(false, moveB[i]);

            //calc
            sum += calcGetCharge();
        }
    }

    private static void checkNowPoint(boolean isA, int dirc){
        if(dirc == 1){
            if(isA) ap.y--;
            if(!isA) bp.y--;
        }
        if(dirc == 2){
            if(isA) ap.x++;
            if(!isA) bp.x++;
        }
        if(dirc == 3){
            if(isA) ap.y++;
            if(!isA) bp.y++;
        }
        if(dirc == 4){
            if(isA) ap.x--;
            if(!isA) bp.x--;
        }
    }

    //a, b 한꺼번에 이동하고 한꺼번에 계산
    //동일 위치여야 나누는 게 아니라 같은 공유기 연결이라면 나누는 것
    private static int calcGetCharge(){
        //1순위가 같다면
        if(map[ap.y][ap.x][0] == map[bp.y][bp.x][0]){
            //2순위도 동일하다면 1+2순위 조합
            if(map[ap.y][ap.x][1] == map[bp.y][bp.x][1]){
                return infoBC[map[ap.y][ap.x][0]] + infoBC[map[bp.y][bp.x][1]];
            }
            //2순위가 다르다면 a의 2순위와 b의 2순위 중 값이 더 큰 걸로
            else{
                if(infoBC[map[ap.y][ap.x][1]] > infoBC[map[bp.y][bp.x][1]]){
                    return infoBC[map[ap.y][ap.x][1]] + infoBC[map[bp.y][bp.x][0]];
                }
                else return infoBC[map[ap.y][ap.x][0]] + infoBC[map[bp.y][bp.x][1]];
            }        
        }
        else{
            return infoBC[map[ap.y][ap.x][0]] + infoBC[map[bp.y][bp.x][0]];
        }
    }

    private static void drawBC(int index){
        int x = sc.nextInt() - 1; 
        int y = sc.nextInt() - 1;
        int c = sc.nextInt(); //충전 범위
        int p = sc.nextInt(); //처리량

        infoBC[index] = p;

        for(int i = c*-1; i<=c; i++){
            if(y + i < 0 || y + i >= 10) continue;

            for(int r = -1*c; r<=c; r++){
                if(Math.abs(i) + Math.abs(r) > c) continue;
                if(x + r < 0 || x + r >= 10) continue;

                //해당 위치 충전량
                for(int q=0; q<2; q++){
                    if(infoBC[map[y+i][x+r][q]] < p){
                        if(q == 1){
                            map[y+i][x+r][1] = index;
                        }
                        
                        if(q == 0){
                            map[y+i][x+r][1] = map[y+i][x+r][0];
                            map[y+i][x+r][0] = index;
                        }
                        
                        break;
                    }
                }
            }
        }
    }

    private static void initMap(int a){
        for(int i=0; i<10; i++){
            for(int r=0; r<10; r++){
                for(int q=0; q<2; q++){
                    map[i][r][q] = a;
                }
            }
        }
    }
}
