import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, room[][];

    static class Class {
        int s;
        int e;

        Class(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        room = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            room[i][0] = s;
            room[i][1] = e;
        }
        Arrays.sort(room, (o1, o2) -> o1[0] - o2[0]);

        int answer = 0;
        PriorityQueue<Class> pq = new PriorityQueue<>((o1, o2) -> o1.e - o2.e);

        for (int i = 0; i < n; i++) {
            if (pq.isEmpty()) {
                pq.offer(new Class(room[i][0], room[i][1]));
                answer++;
                continue;
            }
            if (room[i][0] < pq.peek().e) {
                pq.offer(new Class(room[i][0], room[i][1]));
                answer++;
            } else {
                pq.offer(new Class(room[i][0], room[i][1]));
                pq.poll();
            }
        }
        System.out.println(answer);
    }
}