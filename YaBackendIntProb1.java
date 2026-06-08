import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class YaBackendIntProb1 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем ввод
        int n = Integer.parseInt(reader.readLine());
        String[] lineReader = reader.readLine().split(" ");
        int[] inputSequence = new int[n];
        for (int i = 0; i < n; i++) {
            inputSequence[i] = Integer.parseInt(lineReader[i]);
        }
        
        // Мапа сколько раз встретилось число
        Map<Integer, Integer> countMap = new HashMap<>();
        // Мапа с первым индексом числа
        Map<Integer, Integer> indexMap = new HashMap<>();

        // Расставляем числа в мапы
        for (int i = 0; i < n; i++) {
            if (!countMap.containsKey(inputSequence[i])) {
                countMap.put(inputSequence[i], 1);
                indexMap.put(inputSequence[i], i);
            } else {
                countMap.put(inputSequence[i], countMap.get(inputSequence[i]) + 1);
            }
        }

        // Лист с уникальными числами
        List<Integer> uniqueNumList = new ArrayList<>(countMap.keySet());
        // Сортируем его
        uniqueNumList.sort((a, b) -> {
            int countA = countMap.get(a);
            int countB = countMap.get(b);
            if (countA != countB) {
                return countB - countA;
            } else {
                return indexMap.get(a) - indexMap.get(b);
            }
            
        });

        // Выводим ответ
        for (int number : uniqueNumList) {
            for (int i = 0; i < countMap.get(number); i++) {
                writer.write(String.valueOf(number) + " ");
            }
        }
        writer.newLine();

        // Отладка
        // writer.write(String.valueOf(n));
        // writer.newLine();
        // for (int i=0; i<n; i++){
        // writer.write(String.valueOf(inputSequence[i]));}
        // writer.newLine();
        // writer.write(countMap.toString());
        // writer.newLine();
        // writer.write(indexMap.toString());
        // writer.newLine();
        // writer.write(uniqueNumList.toString());
        // writer.newLine();

        reader.close();
        writer.close();
    }
}
