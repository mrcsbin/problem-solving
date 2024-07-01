import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int j = Integer.parseInt(br.readLine());

        int left = 1;
        int right = m;
        int answer = 0;
        while (j-- > 0) {
            int l = Integer.parseInt(br.readLine());
            if (left > l) {
                answer += left - l;
                right -= left - l;
                left = l;
            } else if (right < l) {
                answer += l - right;
                left += l - right;
                right = l;
            }
        }
        System.out.println(answer);
    }
}