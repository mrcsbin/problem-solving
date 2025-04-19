import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        List<Long> banOrders = new ArrayList<>();
        for (String ban : bans) {
            banOrders.add(toOrderNumber(ban));
        }
        
        Collections.sort(banOrders);
        
        for (long banOrder : banOrders) {
            if (banOrder <= n) n++;
            else break;
        }
        
        return nToString(n); 
    }
    
    private static long toOrderNumber(String ban) {
        long n = 0;
        for (int i = 0; i < ban.length(); i++) {
            n = (n * 26) + (ban.charAt(i) - 'a' + 1);
        }
        
        return n;
    }
    
    private static String nToString(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) ('a' + (n % 26)));
            n /= 26;
        }

        return sb.reverse().toString();
    }
}