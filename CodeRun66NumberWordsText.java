import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Set;
import java.util.HashSet;

public class CodeRun66NumberWordsText {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Делаем сет для хранения уникальных слов
        Set<String> uniqueWords = new HashSet<>();

        // Читаем входные данные
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isBlank()) {
                // Разбиваем на массив из отдельных слов
                String[] wordsInLine = line.trim().split("\\s+");
                // Проходим по массиву и добавляем в хэшсет
                for (String word : wordsInLine) {
                    uniqueWords.add(word);
                }
            }
        }
        
        writer.write(String.valueOf(uniqueWords.size()));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
