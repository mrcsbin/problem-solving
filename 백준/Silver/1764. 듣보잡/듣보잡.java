import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static Set<String> dp, bp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new HashSet<>();
        bp = new HashSet<>();

        List<String> dbpList = new ArrayList<>();
        while (n-- > 0) {
            dp.add(br.readLine());
        }

        while (m-- > 0) {
            String name = br.readLine();
            if (dp.contains(name)) {
                dbpList.add(name);
            }
        }

        Collections.sort(dbpList);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dbpList.size(); i++) {
            sb.append(dbpList.get(i)).append("\n");
        }

        System.out.println(dbpList.size());
        System.out.println(sb);
    }
}