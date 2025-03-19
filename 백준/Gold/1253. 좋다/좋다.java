import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        int count = 0;
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = arr.length - 1;
            while (l < r) {
                int sum = arr[l] + arr[r];
                if (sum == arr[i]) {
                    if (i == l) l++;
                    else if (i == r) r--;
                    else {
                        count++;
                        break;
                    }
                } else if (sum > arr[i]) r--;
                else if (sum < arr[i]) l++;
            }
        }

        System.out.println(count);
    }
}