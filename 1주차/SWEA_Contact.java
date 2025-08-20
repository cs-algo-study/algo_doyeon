import java.util.*;
import java.lang.*;
import java.io.*;

class Node{
    int seq, num;

    Node(int seq, int num){
        this.seq = seq;
        this.num = num;
    }
}

class Solution {
    static Scanner sc = new Scanner(System.in);
    static int n, s;
    static boolean visited[] = new boolean[101]; //최대 100
    static LinkedList<Integer> adj[] = new LinkedList[101];
    static Queue<Node> queue = new LinkedList<>();
     
    public static void main(String[] args) throws IOException {
        //가장 나중에 연락 받는 사람 중 번호가 가장 큰 사람
        
        for(int i=1; i<=10; i++){
            System.out.print("#"+i+" ");
            sol();
        }
    }

    private static void sol() throws IOException {
        init();

        Node node;
        Node answer = new Node(-1, 0);
        
        //BFS
        while(!queue.isEmpty()){
            node = queue.poll();

            if(visited[node.num]) continue;
            visited[node.num] = true;

            //더 나중 시퀀스인 경우
            if(node.seq > answer.seq){
                answer = node;
            }
            //같은 시퀀스지만 더 큰 숫자인 경우
            if(node.seq == answer.seq && node.num > answer.num){
                answer = node;
            }

            for(int next : adj[node.num]){
                if(visited[next]) continue;

                queue.add(new Node(node.seq + 1, next));
            }
        }

        System.out.println(answer.num);
    }

    //초기화 + 입력
    private static void init() throws IOException {
        Arrays.fill(visited, false);
        queue.clear();

        n = sc.nextInt(); //데이터 길이
        s = sc.nextInt(); //시작점
        
        queue.add(new Node(0, s));

        for(int i=0; i<101; i++){
            adj[i] = new LinkedList<Integer>();
        }

        int a, b;
        
        for(int i=0; i<n/2; i++){
            a = sc.nextInt();
            b = sc.nextInt();

            adj[a].add(b);
        }
    }
}
