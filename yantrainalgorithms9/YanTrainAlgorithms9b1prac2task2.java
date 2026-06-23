package yantrainalgorithms9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YanTrainAlgorithms9b1prac2task2 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] lineReader = reader.readLine().split(" ");
        int[] input = new int[lineReader.length];
        for (int i=0; i < lineReader.length; i++) {
            input[i] = Integer.parseInt(lineReader[i]);
        }

        int ans1 = 0, ans2 = 0; // ans 1 <= ans 2

        // Пограничный случай 2 числа всего
        if (input.length == 2) {
            ans1 = input[0];
            ans2 = input[1];   
        }

        // Основной алгоритм
        int neg1 = 0, neg2 = 0, pos1 = 0, pos2 = 0;
        boolean isZero = false;

        // ищем 2 негативных и 2 позитивных
        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0) {
                if (neg1 == 0) {
                    neg1 = input[i];
                } else if (input[i] <= neg1) {
                    neg2 = neg1;
                    neg1 = input[i];
                } else if (neg2 == 0 || input[i] < neg2) {
                    neg2 = input[i];
                }
            }
            if (input[i] > 0) {
                if (pos2 == 0) {
                    pos2 = input[i];
                } else if (input[i] >= pos2) {
                    pos1 = pos2;
                    pos2 = input[i];
                } else if (pos1 == 0 || input[i] > pos1) {
                    pos1 = input[i];
                }
            }
            if (input[i] == 0) {
                isZero = true;
            }
        }

        if ((long)neg1*neg2 > (long)pos1*pos2) {
            ans1 = neg1;
            ans2 = neg2;
        } else if ((long)neg1*neg2 < (long)pos1*pos2) {
            ans1 = pos1;
            ans2 = pos2;
        }

        if (ans1 > ans2) {
            int temp = ans1;
            ans1 = ans2;
            ans2 = temp;
        }

        writer.write(String.valueOf(ans1) + " " + String.valueOf(ans2));
        writer.newLine();
        reader.close();
        writer.close();
    }
}
