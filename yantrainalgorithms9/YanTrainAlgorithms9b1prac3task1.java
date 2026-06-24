package yantrainalgorithms9;
// https://new.contest.yandex.ru/contests/89515/problems?id=30404%2F2021_08_20%2FivtVjWoF0V

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// для тестов
// import java.util.Random;
// import java.util.Arrays;

public class YanTrainAlgorithms9b1prac3task1 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] lineReader = reader.readLine().split(" ");
        int[] input = new int[10];
        for (int i = 0; i < 10; i++) {
            input[i] = Integer.parseInt(lineReader[i]);
        }

        // Тестируем
        // Random random = new Random();
        // for (int i = 0; i < 10; i++) {
        //     input[i] = random.nextInt(3); // 0, 1 или 2
        // }
        // System.out.println(Arrays.toString(input));
        

        writer.write(String.valueOf(solve(input)));
        writer.newLine();
        reader.close();
        writer.close();
    }
    
    public static int solve (int[] input) {
        int answer = -1;
        int lastMag = -1;
        // lm = 2 nm=7 i=9
        for (int i = 0; i < 10; i++) {
            if (input[i] == 2) {
                lastMag = i;
            }
            if (input[i] == 1) {
                int nextMag = -1;
                for (int j = i; j < 10; j++) {
                    if (input[j] == 2) {
                        nextMag = j;
                        break;
                    }
                }
                if (lastMag != -1 && nextMag != -1) {
                    if (i - lastMag > nextMag - i) {
                        if (nextMag - i >= answer) {
                            answer = Math.max(answer, nextMag - i);
                        }
                    } else {
                        if (i - lastMag >= answer) {
                            answer = Math.max(answer, i - lastMag);
                        }
                    }
                } else if (lastMag != -1) {
                    answer = Math.max(answer, i - lastMag);
                } else if (nextMag != -1) {
                    answer = Math.max(answer, nextMag - i);
                }
            }
        }

        return answer;
    }
}
