import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

class Solution {
    static Map<String, Integer> hm;
    static List<String> list;
    
    static void dfs(int depth, String order, String str) {
        if (depth == order.length()) {
            if (str.length() >= 2) {
                hm.put(str, hm.getOrDefault(str, 0) + 1);
            }
            return;
        }
        dfs(depth + 1, order, str + order.charAt(depth));
        dfs(depth + 1, order, str);
    }
    
    public String[] solution(String[] orders, int[] course) {
        hm = new HashMap<>();
        for (String order : orders) {
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            order = new String(chars);
            dfs(0, order, "");
        }
        
        list = new ArrayList<>();
        for (int count : course) {
            int max = 0;
            for (Map.Entry<String, Integer> entry : hm.entrySet()) {
                if (entry.getKey().length() == count) {
                    if (entry.getValue() >= 2 && max < entry.getValue()) {
                        max = entry.getValue();
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : hm.entrySet()) {
                if (entry.getKey().length() == count && entry.getValue() == max) {
                    list.add(entry.getKey());
                }
            }
        }
        
        Collections.sort(list);
        
        String[] answer = list.toArray(new String[list.size()]);
        return answer;
    }
}