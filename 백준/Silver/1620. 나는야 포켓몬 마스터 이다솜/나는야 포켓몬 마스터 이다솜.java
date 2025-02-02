import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static Map<String, Integer> collectionMap;
    static List<String> collectionList;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        collectionMap = new HashMap<>();
        collectionList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            collectionMap.put(name, i);
            collectionList.add(name);
        }

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            String input = br.readLine();
            if (65 <= input.charAt(0) && input.charAt(0) <= 90) {
                sb.append(collectionMap.get(input)).append("\n");
            } else {
                sb.append(collectionList.get(Integer.parseInt(input) - 1)).append("\n");
            }
        }

        System.out.println(sb);
    }
}