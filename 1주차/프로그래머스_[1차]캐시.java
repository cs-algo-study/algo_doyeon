import java.util.*;

class Solution {
    Map<String, Integer> map;
    
    private String getMinCity() {
        int minCount = Integer.MAX_VALUE;
        String city = "";
        
        for(String key : map.keySet()){
            if(map.get(key) < minCount){
                minCount = map.get(key);
                city = key;
            }
        }
        
        return city;
    }
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        int nowCacheSize = 0;
        map = new HashMap<>();
        
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        for(int i=0; i<cities.length; i++){
            //없는 경우
            if (!map.containsKey(cities[i].toLowerCase())) {
                //넣을 수 있는 경우
                if (nowCacheSize < cacheSize) {
                    nowCacheSize++;
                    map.put(cities[i].toLowerCase(), i);
                }
                //넣을 수 없는 경우
                else {
                    map.remove(getMinCity());
                    map.put(cities[i].toLowerCase(), i);
                }
                
                answer += 5;
            }
            //있는 경우
            else {
                map.put(cities[i].toLowerCase(), i);
                answer += 1;
            }
        }
        
        return answer;
    }
}
