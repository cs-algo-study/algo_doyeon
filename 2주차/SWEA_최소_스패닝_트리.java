import java.util.*;
import java.lang.*;
import java.io.*;

class Node{
    int a, b, w;

    Node(int a, int b, int w){
        this.a = a;
        this.b = b;
        this.w = w;
    }
}

class Solution {
    static Scanner sc = new Scanner(System.in);
    static int v, e;
    static long sum;
    static int boss[];
    static PriorityQueue<Node> queue = 
        new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
    
    public static void main(String[] args) {
        int tc = sc.nextInt();
        
        for(int i=1; i<=tc; i++){
            System.out.print("#"+i+" ");
            init();
            sol();
        }
    }

    private static void sol(){
        int count = 0;
        Node now;
        
        while(count < v-1){
            now = queue.poll();

            if(find(now.a) == find(now.b)) continue;

            union(now.a, now.b);
            sum += now.w;
            count++;
        }

        System.out.println(sum);
    }

    private static void union(int a, int b){
        boss[find(a)] = find(b);
    }

    private static int find(int a){
        if(boss[a] == a) return a;
        else return boss[a] = find(boss[a]);
    }

    private static void init(){
        queue.clear();
        sum = 0;
        
        v = sc.nextInt();
        e = sc.nextInt();

        boss = new int[v+1];
        for(int i=1; i<v+1; i++) boss[i] = i;
        
        int a, b, c;
        for(int i=0; i<e; i++){
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            queue.add(new Node(a, b, c));
        }
    }
}
