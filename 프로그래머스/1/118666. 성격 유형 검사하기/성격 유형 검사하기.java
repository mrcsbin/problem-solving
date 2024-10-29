import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        Map<String, Integer> hm = new HashMap<>();
        String[] alphabets = {"R", "T", "C", "F", "J", "M", "A", "N"};
        for (String alphabet : alphabets) {
            hm.put(alphabet, 0);
        }
        
        for (int i = 0; i < survey.length; i++) {
            String[] terms = survey[i].split("");
            if (choices[i] < 4) { 
                hm.put(terms[0], hm.get(terms[0]) + Math.abs(4 - choices[i]));
            } else if (choices[i] > 4) {
                hm.put(terms[1], hm.get(terms[1]) + Math.abs(4 - choices[i]));
            }
        }
        
        System.out.println(hm.values());
        
        for (int i = 0; i < 4; i++) {
            if (hm.get(alphabets[2 * i]) >= hm.get(alphabets[2 * i + 1])) {
                answer += alphabets[2 * i];
            } else if (hm.get(alphabets[2 * i]) < hm.get(alphabets[2 * i + 1])) {
                answer += alphabets[2 * i + 1];
            }
        }
        
        return answer;
    }
}