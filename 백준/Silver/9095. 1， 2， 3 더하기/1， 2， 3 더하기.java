import java.util.*;
import java.io.*;
/*
중복순열
 */

public class Main {
    static int N;
    static int cnt;
    static void findCaseWith123(int sum){
        if(sum > N)
            return;
        if(sum == N) {
            cnt++;
            return;
        }

        for(int i = 1; i < 4; i++) {
            findCaseWith123(sum + i);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            cnt = 0;
            N = Integer.parseInt(br.readLine());
            findCaseWith123(0);
            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }
}
