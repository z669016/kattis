package com.putoet.kattis;

import java.io.InputStream;
import java.util.stream.IntStream;

public class OnesForAll {
    public static void main(String[] args) {
        final var n = InputFactory.get(System.in);

        System.out.println(onesComplexity(n));
    }

    static long onesComplexity(int n) {
        return asOnes(n)[n];
    }

    static int[] asOnes(int n) {
        // The array ones, contains the number of ones needed to combine a
        // certain value (the value equals the index of the array). Fot starters
        // you need as many ones as the value itself.
        final var ones = IntStream.rangeClosed(0, n).toArray();

        for (var k = 2; k <= n; k++) {
            // if two lower values added up, also get you the new value with fewer ones,
            // then adjust the number of ones needed for the value. E.g. 9 can be created
            // by adding 7 and 2
            for (var l = 1; l < k / 2 + 1; l++)
                ones[k] = Math.min(ones[k], ones[k - l] + ones[l]);

            // check if there is a multiplication of two values, that also get you the new value
            // with fewer ones. E.g. 12 can be created by multiplying 3 and 4 (together requiring 7 ones)
            for (var l = 2; l < Math.sqrt(k) + 1; l++)
                if (k % l == 0)
                    ones[k] = Math.min(ones[k], ones[k / l] + ones[l]);

            // check if there are two substring that together get you the new value with fewer ones.
            // E.g. 12 can be created by concatenating from 1 and 2 (together requiring 3 ones)
            final var s = String.valueOf(k);
            for (var l = 0; l < s.length() - 1; l++) {
                if (s.charAt(l + 1 ) != '0') {
                    final var a = Integer.parseInt(s.substring(0, l+1));
                    final var b = Integer.parseInt(s.substring(l+1));
                    ones[k] = Math.min(ones[k], ones[a] + ones[b]);
                }
            }
        }

        return ones;
    }
}

class InputFactory {
    public static int get(InputStream in) {
        final FastReader scanner = new FastReader(in);
        return scanner.nextInt();
    }
}
