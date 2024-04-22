import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isAlphabetic(ch)) {
                sb.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop();
            } else if (ch == '+' || ch == '-') {
                while (!stack.isEmpty() && "+-*/".contains(String.valueOf(stack.peek()))) {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            } else if (ch == '*' || ch == '/') {
                while (!stack.isEmpty() && "*/".contains(String.valueOf(stack.peek()))) {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}
