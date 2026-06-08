import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class YaBackendIntProb2 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем ввод
        int n = Integer.parseInt(reader.readLine());
        String[] lineReader = reader.readLine().split(" ");
        int[] inputLogs = new int[n];
        for (int i = 0; i < n; i++) {
            inputLogs[i] = Integer.parseInt(lineReader[i]);
        }
        // Сразу сортируем массив
        Arrays.sort(inputLogs);

        // Пробуем 3 варианта среди первых трех чисел
        // if (!tryBuild(inputLogs, 0, 1, writer)) {
        //     if (!tryBuild(inputLogs, 0, 2, writer)) {
        //         if (!tryBuild(inputLogs, 1, 2, writer)) {
        //             writer.write("-1");
        //             writer.newLine();
        //         }
        //     }
        // }

        tryBuild(inputLogs, 0, 1, 2, writer);// Отладка
        
        // writer.write(String.valueOf(n));
        // writer.newLine();
        // for (int i = 0; i < n; i++) {
        //     writer.write(String.valueOf(inputLogs[i]));
        // }
        // writer.newLine();

        reader.close();
        writer.close();
    }
    
    // Функция проверки на прогрессию
    public static boolean isProgression(List<Integer> log) {
        // Не может быть пустой
        if (log.size() == 0) {
            return false;
        }
        // Одно число всегда подойдет
        if (log.size() == 1) {
            return true;
        }
        // 2 и более чисел - чекаем разницу
        int step = log.get(1) - log.get(0);
        for (int i = log.size() - 1; i > 0; i--) {
            if (log.get(i) - log.get(i - 1) != step) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean tryBuild(int[] inputLogs, int firstIndex, int secondIndex, int maxLog1Size,
                BufferedWriter writer) throws IOException {
        // Будем хранить прикидки логов
        List<Integer> log1 = new ArrayList<>();
        List<Integer> log2 = new ArrayList<>();

        int start = inputLogs[firstIndex];
        int step = inputLogs[secondIndex] - inputLogs[firstIndex];
        int nextExpected = start;
        // Все с подходящим шагом в первый лог, остальные во второй
        for (int number : inputLogs) {
            if (number == nextExpected && log1.size() < maxLog1Size) {
                log1.add(number);
                nextExpected += step;
            } else {
                log2.add(number);
            }
        }
        
        // Если оба - прогрессии, то выводи результат, иначе возвращаем false
        if (isProgression(log1) && isProgression(log2)) {
            writer.write(String.valueOf(log1.size()));
            writer.newLine();
            for (int number : log1) {
                writer.write(String.valueOf(number) + " ");
            }
            writer.newLine();

            writer.write(String.valueOf(log2.size()));
            writer.newLine();
            for (int number : log2) {
                writer.write(String.valueOf(number) + " ");
            }
            writer.newLine();
            return true;
        } else {
            return false;
        }
    }
}
