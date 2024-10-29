import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] splitTerm = term.split(" ");
            termMap.put(splitTerm[0], Integer.parseInt(splitTerm[1]) * 28);
        }
        
        int todayDay = getDay(today);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            int privacyDay = getDay(privacy[0]);
            int dayDiff = todayDay - privacyDay;
            
            if (dayDiff >= termMap.get(privacy[1])) {
                list.add(i + 1);
            }
        }
        answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
    
    private int getDay(String stringDate) {
        int[] intDate = Arrays.stream(stringDate.split("\\.")).mapToInt(Integer::parseInt).toArray();
        return intDate[0] * 12 * 28 + intDate[1] * 28 + intDate[2];
    }
}