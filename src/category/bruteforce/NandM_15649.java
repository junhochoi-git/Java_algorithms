package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NandM_15649 {

    static int N, M;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    static void appendNextDigit(int digit) {
        /* base case */
        if (digit == M + 1) {
            for (int i = 1; i < numbers.length; i++) sb.append(numbers[i]).append(" ");
            sb.append("\n");
        } else {
            for (int i = 1; i <= N; i++) {
                if (digit >= 2 && containsKey(i, digit - 1))
                    continue;
                numbers[digit] = i;
                appendNextDigit(digit + 1);
                numbers[digit] = 0; // 현재 자릿수의 값을 명시적으로 0으로 초기화 해줌

            }
        }
    }

    static boolean containsKey(int key, int to) {
        for (int i = 1; i <= to; i++) {
            if (numbers[i] == key) return true;
        }
        return false;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M + 1];
    }

    public static void main(String[] args) throws IOException {
        input();
        appendNextDigit(1);
        System.out.println(sb);
    }
}
