import java.math.BigInteger;

import static java.lang.System.exit;

class LychrelFinder {

    private static StringBuilder sb = new StringBuilder();

    void solution(int numberToCheck, int maxIterations) throws InterruptedException {
        System.out.println("Check for palindrome: " + numberToCheck);
        BigInteger x = BigInteger.valueOf(numberToCheck);
        for (int i = 0; i < maxIterations; i++) {
            BigInteger reversed_x = new BigInteger(sb.delete(0, sb.length()).append(x).reverse().toString());
            if (x.equals(reversed_x)) {
                System.out.println("\rPalindrome: " + x + ", iteration: " + i);
                exit(0);
            }
            printProgress(i + 1, maxIterations);
            x = x.add(reversed_x);
        }
        System.err.println("\n" + x);
    }

    private void printProgress(int i, int maxIterations) throws InterruptedException {
        if (i % 1000 == 0 || i == maxIterations) {
            System.out.print("\r" + i * 100 / maxIterations + "% completed");
            Thread.sleep(1000);
        }
    }
}
