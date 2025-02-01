import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int n;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        while (n-- > 0) {
            char[] nodes = br.readLine().toCharArray();
            int node = nodes[0] - 'A';
            int leftNode = nodes[2] - 'A';
            int rightNode = nodes[4] - 'A';


            tree[node].add(leftNode);
            tree[node].add(rightNode);
        }

        preorder(0);
        System.out.println();
        inorder(0);
        System.out.println();
        postorder(0);
    }

    private static void preorder(int current) {
        List<Integer> node = tree[current];

        int leftNode = node.get(0);
        int rightNode = node.get(1);

        System.out.print((char) (current + 'A'));

        if (leftNode >= 0) preorder(leftNode);
        if (rightNode >= 0) preorder(rightNode);
    }

    private static void inorder(int current) {
        List<Integer> node = tree[current];

        int leftNode = node.get(0);
        int rightNode = node.get(1);

        if (leftNode >= 0) inorder(leftNode);
        System.out.print((char) (current + 'A'));
        if (rightNode >= 0) inorder(rightNode);
    }

    private static void postorder(int current) {
        List<Integer> node = tree[current];

        int leftNode = node.get(0);
        int rightNode = node.get(1);

        if (leftNode >= 0) postorder(leftNode);
        if (rightNode >= 0) postorder(rightNode);
        System.out.print((char) (current + 'A'));
    }
}