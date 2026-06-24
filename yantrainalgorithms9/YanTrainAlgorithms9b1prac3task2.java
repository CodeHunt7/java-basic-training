package yantrainalgorithms9;
// https://new.contest.yandex.ru/contests/89515/problems?id=30404%2F2020_09_26%2FE0bUCqDLBR

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YanTrainAlgorithms9b1prac3task2 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем входные данные
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        writer.write(solve(a, b, c));
        writer.newLine();
        reader.close();
        writer.close();
    }
    
    public static String solve (int a, int b, int c) {
        // ax+b = c^2, ax = c^2 - b, x = (c^2 - b)/a
        // x = (3^2 - 2)/1

        // Краевые случаи
        if (c < 0) { // отрицательный корень
            return "NO SOLUTION";
        }
        if (a == 0 && b != c * c) { // деление на 0
            return "NO SOLUTION";
        }
        if (a == 0 && b == c * c) { // x*0 это любой x
            return "MANY SOLUTIONS";
        }
        
        if ((c * c - b) % a == 0) { // целое число
            return String.valueOf((c * c - b) / a);
        } else {
            return "NO SOLUTION";
        }
    }
}
