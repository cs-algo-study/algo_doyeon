class Solution {
    int[] apeach;
    int maxVal = 0;
    int[] now = new int[11];
    int[] answer = new int[11];
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        
        dfs(10, n, 0);
        
        if(maxVal == 0) answer = new int[]{-1};
        
        return answer;
    }
    
    private void copyAnswer(){
        for(int i=0; i<10; i++){
            answer[i] = now[i];
        }
    }
    
    private boolean isInCondition(){
        for(int i=9; i>=0; i--){
            if(answer[i] < now[i]) return true;
            if(answer[i] > now[i]) return false;
        }
        
        return true;
    }
    
    private void dfs(int depth, int rest, int score){
        if(depth == 0){
            if(score > maxVal || (score == maxVal && isInCondition())) {
                if(rest > 0) answer[10] = rest;
                else answer[10] = 0;
                copyAnswer();
                maxVal = score;
            }
            return;
        }
        
        //어피치+1
        if(rest > apeach[10 - depth]){
            now[10 - depth] = apeach[10 - depth] + 1;
            dfs(depth - 1, rest - now[10 - depth], score + depth);
        }
        
        //0
        now[10 - depth] = 0;
        //어피치가 점수 먹은 경우
        if(apeach[10-depth] > 0) dfs(depth - 1, rest, score - depth);
        //아무도 먹지 못한 경우
        else dfs(depth - 1, rest, score);
    }
}
