import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int partSum[]; // 100,000
    static int len;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        partSum = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            int temp = Integer.parseInt(st.nextToken());
            partSum[i] = partSum[i - 1] + temp;
        }

        int left = 1, right = 1;
        len = N + 1;
        while(right <= N) {
            if (partSum[right] - partSum[left - 1] >= S) {
                len = Math.min(right - left + 1, len);
                left++;
            } else {
                right++;
            }
            if (left > right) {
                right++;
            }
        }

        if(len == N+1)
            System.out.println(0);
        else
            System.out.println(len);
    }
}
