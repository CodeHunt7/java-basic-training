package yantrainalgorithms9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YanTrainAlgorithms9b1prac2task1 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] lineReader = reader.readLine().split(" ");
        int tRoom = Integer.parseInt(lineReader[0]);
        int tCond = Integer.parseInt(lineReader[1]);
        String mode = reader.readLine();

        int answer = 0;

        if (mode.equals("freeze")) {
            if (tRoom >= tCond) { // Охлаждаем
                answer = tCond;
            } else { // Ничего не делаем
                answer = tRoom;
            }
        }
        if (mode.equals("heat")) {
            if (tRoom <= tCond) { // Нагреваем
                answer = tCond;
            } else { // Ничего не делаем
                answer = tRoom;
            }
        }
        if (mode.equals("auto")) {
            answer = tCond; // Он всегда приведет t к нужному значению
        }
        if (mode.equals("fan")) {
            answer = tRoom; // Не меняем температуру
        }

        //writer.write(mode);
        writer.write(String.valueOf(answer));
        writer.newLine();
        reader.close();
        writer.close();
    }
}