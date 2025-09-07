import java.util.*;
import java.lang.*;
import java.io.*;

class Info{
    int num, index, usedDesk;

    Info(int num, int index, int usedDesk){
        this.num = num;
        this.index = index;
        this.usedDesk = usedDesk;
    }
}

class Solution {
    static Scanner sc = new Scanner(System.in);
    static int n, m, k, a, b, ai[], bj[], tk[], time;
    static int timea[][], timeb[][];
    static boolean flag;
    static Set<Integer> set = new HashSet<>();
    static PriorityQueue<Info> queueb = 
        new PriorityQueue<>((o1, o2) -> {
            if(o1.index == o2.index) return o1.usedDesk - o2.usedDesk;
            else return o1.index - o2.index;
        });
   
    public static void main(String[] args) {
        int tc = sc.nextInt();

        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            init();
            sol();
        }
    }

    private static void sol(){
        int index = 0;
        time = 0;
       
        while(true){
            if(index == k && queueb.isEmpty() && flag) break;

            for (int i=index; i<k; i++) {
                if(tk[i] > time || !assign(i, true)) {
                    index = i;
                    break;
                }
                else index = i+1;
            }

            while(!queueb.isEmpty()) {
                if(assign(queueb.peek().num, false)){
                    queueb.poll();
                }
                else break;
            }

            time++;
            deductTime();
        }

        System.out.println(calcSum());
    }

    //창구 배정 여부 반환
    private static boolean assign(int userNum, boolean isA){
        //접수 창구 배정
        if(isA){
            for(int i=0; i<n; i++) {
                if(timea[i][0] == 0) {
                    if(i == a-1) set.add(userNum);
                    timea[i][0] = ai[i];
                    timea[i][1] = userNum;
                    return true;
                }
            }

            return false;
        }

        //정비 창구 배정
        else{
            for(int i=0; i<m; i++) {
                if(timeb[i][0] == 0) {
                    //System.out.println("put in set: "+userNum);
                    if(i != b-1) set.remove(userNum);
                    timeb[i][0] = bj[i];
                    timeb[i][1] = userNum;
                    return true;
                }
            }

            return false;
        }
    }

    private static int calcSum() {
        int answer = 0;

        if(set.size() == 0) return -1;
        
        for(int i : set) {
            answer += i+1;
        }

        return answer;
    }

    private static void deductTime() {
        flag = true;
        for(int i=0; i<n; i++) {
            if(timea[i][0] > 0) {
                if(--timea[i][0] == 0) {
                    queueb.add(new Info(timea[i][1], time, i));
                }
                else flag = false;
            }
        }

        for(int i=0; i<m; i++) {
            if(timeb[i][0] > 0) {
                timeb[i][0]--;
            }
        }
    }

    private static void init(){
        set.clear();
       
        n = sc.nextInt(); //접수 창구 수
        m = sc.nextInt(); //정비 창구 수
        k = sc.nextInt(); //고객 수
        a = sc.nextInt(); //지갑 놓고 간 고객 접수 창구 번호
        b = sc.nextInt(); //정비 창구 번호

        ai = new int[n];
        bj = new int[m];
        tk = new int[k];

        timea = new int[n][2];
        timeb = new int[m][2];
       
        for(int i=0; i<n; i++) ai[i] = sc.nextInt();
        for(int i=0; i<m; i++) bj[i] = sc.nextInt();
        for(int i=0; i<k; i++) tk[i] = sc.nextInt();
    }
}
