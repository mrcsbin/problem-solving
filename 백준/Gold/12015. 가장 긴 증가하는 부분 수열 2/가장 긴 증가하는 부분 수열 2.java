import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[] arr, memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        memo = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        int len = 0;
        memo[0] = arr[0];

        for (int i = 1; i < n; i++) {
            if (memo[len] < arr[i]) {
                memo[++len] = arr[i];
            } else {
                memo[lowerBound(len, arr[i])] = arr[i];
            }
        }

        System.out.println(len + 1);
    }

    private static int lowerBound(int len, int target) {
        int left = 0;
        int right = len;
        int mid;
        
        while (left < right) {
            mid = left + (right - left) / 2;
            
            if (memo[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}