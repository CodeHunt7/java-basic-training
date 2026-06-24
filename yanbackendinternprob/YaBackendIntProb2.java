package yanbackendinternprob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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

        boolean rWeThereYet = false;
        for (int first = 0; first < Math.min(3, n) && !rWeThereYet; first++) {
            for (int second = first + 1; second < Math.min(3, n) && !rWeThereYet; second++) {
                rWeThereYet = tryBuildFast(inputLogs, first, second, writer);
            }
        }

        if (!rWeThereYet) {
            writer.write("-1");
            writer.newLine();
        }

        // Пробуем 3 варианта среди первых трех чисел
        // if (!tryBuild(inputLogs, 0, 1, writer)) {
        //     if (!tryBuild(inputLogs, 0, 2, writer)) {
        //         if (!tryBuild(inputLogs, 1, 2, writer)) {
        //             writer.write("-1");
        //             writer.newLine();
        //         }
        //     }
        // }

        // Отладка
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
    
    public static boolean tryBuildFast(int[] inputLogs, int firstIndex, int secondIndex, BufferedWriter writer)
            throws IOException {
        int n = inputLogs.length;

        long start = inputLogs[firstIndex];
        long step = (long) inputLogs[secondIndex] - inputLogs[firstIndex];
        long nextExpected = start;
        
        // Индексы чисел, которые могут идти в первый лог по выбранному шагу
        List<Integer> selectedIndexes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if ((long) inputLogs[i] == nextExpected) {
                selectedIndexes.add(i);
                nextExpected += step;
            }
        }
        
        // Связный список на массивах: быстро выкидываем элементы из второго лога
        int[] prev = new int[n];
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        // Сколько раз встречается каждая разница между соседями во втором логе
        Map<Integer, Integer> diffMap = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            addDiff(diffMap, inputLogs[i + 1] - inputLogs[i]);
        }

        boolean[] removed = new boolean[n];
        int remaining = n;
        int head = 0;

        for (int take = 0; take < selectedIndexes.size(); take++) {
            int index = selectedIndexes.get(take);
            removed[index] = true;

            // Убираем число из второго лога и пересобираем разницы вокруг него
            int left = prev[index];
            int right = next[index];

            if (left != -1) {
                removeDiff(diffMap, inputLogs[index] - inputLogs[left]);
            }

            if (right != -1) {
                removeDiff(diffMap, inputLogs[right] - inputLogs[index]);
            }

            if (left != -1 && right != -1) {
                addDiff(diffMap, inputLogs[right] - inputLogs[left]);
            }

            if (left != -1) {
                next[left] = right;
            } else {
                head = right;
            }

            if (right != -1) {
                prev[right] = left;
            }

            remaining--;

            // Если во втором логе все разницы одинаковые, значит это прогрессия
            if (remaining > 0 && (remaining <= 2 || diffMap.size() == 1)) {
                writer.write(String.valueOf(take + 1));
                writer.newLine();

                for (int i = 0; i <= take; i++) {
                    writer.write(inputLogs[selectedIndexes.get(i)] + " ");
                }
                writer.newLine();

                writer.write(String.valueOf(remaining));
                writer.newLine();

                int current = head;
                while (current != -1) {
                    writer.write(inputLogs[current] + " ");
                    current = next[current];
                }
                writer.newLine();

                return true;
            }
        }

        return false;
    }
    
    public static void addDiff(Map<Integer, Integer> diffMap, int diff) {
        diffMap.put(diff, diffMap.getOrDefault(diff, 0) + 1);
    }

    public static void removeDiff(Map<Integer, Integer> diffMap, int diff) {
        int count = diffMap.get(diff);

        if (count == 1) {
            diffMap.remove(diff);
        } else {
            diffMap.put(diff, count - 1);
        }
    }
}
