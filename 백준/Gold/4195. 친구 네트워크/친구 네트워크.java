import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int t, f, idx;
    static int[] p, count;
    static Map<String, Integer> fNetworkMap;
    static String[] fList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            f = Integer.parseInt(br.readLine());
            fNetworkMap = new HashMap<>();
            idx = 0;
            fList = new String[f];
            for (int i = 0; i < f; i++) {
                fList[i] = br.readLine();
                String[] friends = fList[i].split(" ");
                if (!fNetworkMap.containsKey(friends[0])) {
                    fNetworkMap.put(friends[0], idx++);
                }
                if (!fNetworkMap.containsKey(friends[1])) {
                    fNetworkMap.put(friends[1], idx++);
                }
            }
            makeSet();
            for (String friends : fList) {
                String[] friend = friends.split(" ");
                union(fNetworkMap.get(friend[0]), fNetworkMap.get(friend[1]));
                sb.append(count[find(fNetworkMap.get(friend[0]))]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void makeSet() {
        p = new int[idx];
        count = new int[idx];
        for (int i = 0; i < idx; i++) {
            p[i] = i;
            count[i] = 1;
        }
    }

    private static int find(int a) {
        if (a == p[a]) return a;
        return p[a] = find(p[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        p[b] = a;
        count[a] += count[b];
    }
}