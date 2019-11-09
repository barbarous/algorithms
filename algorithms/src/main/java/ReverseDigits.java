import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseDigits {

    public static void main(String[] args) {
        int x = 4231;
        System.out.println(new StringBuilder(String.valueOf(x)).reverse().toString());

        Deque<Integer> stack = new ArrayDeque<>();
        while (x != 0) {
            int position_digit = x % 10;
            System.out.println(position_digit);
            x = x / 10;
            stack.push(position_digit);
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result = result * 10;
            x = stack.pop();
            result += x;
        }
        System.out.println(result);
    }
}
