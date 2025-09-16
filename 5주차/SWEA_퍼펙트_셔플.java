import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());
        String[] arr = new String[1000];
        
        for(int i=1; i<=tc; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int r=0; r<n; r++) arr[r] = st.nextToken();

            //n이 홀수면 /2+1 부터
            //짝수면 /2부터

            int inx = n/2;
            if(n%2 == 1) inx++;

            System.out.print("#"+i+" ");
            for(int r=0; r<n/2; r++){
                System.out.print(arr[r]+" "+arr[inx++]+" ");
            }
            if(n%2 == 1) System.out.println(arr[n/2]);
            else System.out.println();
        }
    }
}
