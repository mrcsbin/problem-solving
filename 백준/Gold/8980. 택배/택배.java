import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[][] deliveries = new int[m][3];
        while (m-- > 0) {
            deliveries[m] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Arrays.sort(deliveries, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        int[] capacity = new int[n];
        Arrays.fill(capacity, c);

        int answer = 0;
        for (int[] delivery : deliveries) {
            int from = delivery[0];
            int to = delivery[1];
            int box = delivery[2];
            for (int i = from; i < to; i++) {
                box = Math.min(box, capacity[i]);
            }
            for (int i = from; i < to; i++) {
                capacity[i] -= box;
            }
            answer += box;
        }
        System.out.println(answer);
    }
}