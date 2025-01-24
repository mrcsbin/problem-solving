import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static long a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<long[]> queue = new ArrayDeque<>();
        queue.add(new long[]{a, 1});

        while (!queue.isEmpty()) {
            long[] current = queue.poll();
            long value = current[0];
            int count = (int) current[1];

            if (value == b) return count;
            if (value * 2 <= b) queue.add(new long[]{value * 2, count + 1});
            if (value * 10 + 1 <= b) queue.add(new long[]{value * 10 + 1, count + 1});
        }

        return -1;
    }
}