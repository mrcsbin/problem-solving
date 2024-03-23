import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, l, k;
    static List<Pos> stars;

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        stars = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Pos(x, y));
        }

        int answer = 0;
        for (Pos s1 : stars) {
            for (Pos s2 : stars) {
                int count = 0;
                for (Pos star : stars) {
                    if ((s1.x <= star.x && star.x <= s1.x + l) && (s2.y <= star.y && star.y <= s2.y + l)) count++;
                }
                answer = Math.max(answer, count);
            }
        }
        System.out.println(k - answer);
    }
}