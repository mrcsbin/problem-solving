import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[] top = new int[h + 1];
        int[] bottom = new int[h + 1];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) bottom[Integer.parseInt(br.readLine())]++;
            else top[Integer.parseInt(br.readLine())]++;
        }

        for (int i = h - 1; i > 0; i--) {
            top[i] += top[i + 1];
            bottom[i] += bottom[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int answer = 0;
        int[] height = new int[h + 1];
        for (int i = 1; i <= h; i++) {
            height[i] = bottom[i] + top[h - i + 1];
            if (min > height[i]) {
                min = height[i];
                answer = 1;
            } else if (min == height[i]) answer++;
        }
        System.out.println(min + " " + answer);
    }
}