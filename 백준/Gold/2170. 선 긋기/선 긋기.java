import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        list = new ArrayList<>();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            list.add(new int[]{x1, y1});
        }
        Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);

        int start = list.get(0)[0];
        int end = list.get(0)[1];
        int answer = 0;
        for (int[] pos : list) {
            if (pos[0] <= end) {
                end = Math.max(end, pos[1]);
            } else {
                answer += Math.abs(end - start);
                start = pos[0];
                end = pos[1];
            }
        }
        answer += Math.abs(end - start);
        System.out.println(answer);
    }
}