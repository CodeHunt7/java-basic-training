package coderun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class CodeRun1MiddleElement {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем строку
        String[] parts = reader.readLine().split(" ");

        // Загоняем в массив int
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }

        // Сортируем и выводим
        Arrays.sort(numbers);
        writer.write(String.valueOf(numbers[1]));

        reader.close();
        writer.close();
    }
}