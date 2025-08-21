import java.util.*;

class Solution {
    List<Integer> ck = new ArrayList<>();
    int row, col;
    String[][] table;

    public int solution(String[][] relation) {
        table = relation;
        row = relation.length;
        col = relation[0].length;

        // 모든 컬럼 조합을 비트마스크로 탐색
        for (int bitmask = 1; bitmask < (1 << col); bitmask++) {
            if (isUnique(bitmask) && isMinimal(bitmask)) {
                ck.add(bitmask);
            }
        }

        return ck.size();
    }

    // 유일성 검사
    private boolean isUnique(int bitmask) {
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < row; i++) {
            StringBuilder key = new StringBuilder();
            for (int j = 0; j < col; j++) {
                if ((bitmask & (1 << j)) != 0) {
                    key.append(table[i][j]).append(",");
                }
            }
            if (!seen.add(key.toString())) {
                return false;
            }
        }

        return true;
    }

    // 최소성 검사
    private boolean isMinimal(int bitmask) {
        for (int key : ck) {
            if ((key & bitmask) == key) {
                return false;
            }
        }
        return true;
    }
}
