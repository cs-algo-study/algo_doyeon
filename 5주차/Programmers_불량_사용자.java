import java.util.*;
import java.lang.Math;

class Solution {
    Set<Integer> set = new HashSet<>();
    
    boolean visited[] = new boolean[8];
    LinkedList<Integer> mapping[];
   
    //경우의 수 몇가지인지 겹치는 경우 제하고
    //비트마스킹해서 저장해놓고 검사
   
    //완전히 일치되는 경우, 포함되는 경우를 모두 고려해야할 것 같다
    //1, 2, 3 해당되고 1, 3 해당되는 경우라면 마찬가지로 경우의 수는
   
    public int solution(String[] user_id, String[] banned_id) {   
        mapping = new LinkedList[banned_id.length];
        for(int i=0; i<banned_id.length; i++) mapping[i] = new LinkedList<>();
       
        boolean flag = false;
        
        //불량 사용자
        for(int i=0; i<banned_id.length; i++){
            //신청자
            for(int r=0; r<user_id.length; r++){
                if(banned_id[i].length() != user_id[r].length()) continue;
                
                flag = true;
                
                for(int q=0; q<banned_id[i].length(); q++){
                    if(banned_id[i].charAt(q) == '*') continue;
                    else if(banned_id[i].charAt(q) != user_id[r].charAt(q)){
                        flag = false;
                        break;
                    }
                }
                
                if(flag) mapping[i].add(r);
            }
        }
        
        //dfs로 계산
        dfs(0, banned_id.length, 0);
       
        return set.size();
    }
    
    private void dfs(int depth, int n, int now){
        if(depth == n) {
            set.add(now);
            return;
        }
        
        for(int r=0; r<mapping[depth].size(); r++){
            if(!visited[mapping[depth].get(r)]){
                visited[mapping[depth].get(r)] = true;
                dfs(depth+1, n, now + (int) Math.pow(2, mapping[depth].get(r)));
                visited[mapping[depth].get(r)] = false;
            }
        }
    }
}
