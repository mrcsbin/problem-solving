import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] oChars = br.readLine().toCharArray();
		char[] findChars = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < oChars.length; i++) {
			char ch = oChars[i];
			int fPtr = findChars.length - 1;
			stack.push(ch);
			if (stack.size() >= findChars.length && stack.peek() == findChars[fPtr]) {
				int idx = stack.lastIndexOf(stack.peek());
				boolean flag = true;
				for (int j = idx; j >= idx - findChars.length + 1; j--) {
					if (stack.get(j) != findChars[fPtr--]) {
						flag = false;
					}
				}
				if (flag) {
					for (int j = 0; j < findChars.length; j++) {
						stack.pop();
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb.length() == 0 ? "FRULA" : sb.reverse().toString());
	}
}