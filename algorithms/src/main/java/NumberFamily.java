import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * select the biggest family number:
 * 123
 * 321
 * 231
 * 213
 *
 * 321 is the greatest
 */

class NumberFamily {

    private static int THRESHOLD_MAX = 100000000;

    public int solution(int n) {
        if (0 > n) {
            throw new IllegalArgumentException("N could not be less than 0.");
        }
        if (isTooLarge(n)) {
            return -1;
        }

        int result = getDigitsOf(n).stream().sorted(Comparator.reverseOrder()).reduce(0, (a, i) -> (a*10 + i));

        if (isTooLarge(result)) {
            return -1;
        }

        return result;
    }

    private boolean isTooLarge(int n) {
        return n > THRESHOLD_MAX;
    }

    private List<Integer> getDigitsOf(int n) {
        List<Integer> digitsOfNumber = new ArrayList<>();
        while (n != 0) {
            int position_digit = n % 10;
            n = n / 10;
            digitsOfNumber.add(position_digit);
        }
        return digitsOfNumber;
    }
}