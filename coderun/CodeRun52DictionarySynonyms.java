package coderun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.HashMap;

public class CodeRun52DictionarySynonyms {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем количество пар
        int numOfPairs = Integer.parseInt(reader.readLine());
        // Читаем сами пары и засовываем в HashMap
        Map<String, String> synonyms = new HashMap<>();
        for (int i = 0; i < numOfPairs; i++) {
            String[] line = reader.readLine().split(" ");
            synonyms.put(line[0], line[1]);
            synonyms.put(line[1], line[0]);

        }
        // Читаем слово для поиска синосима
        String wordToFind = reader.readLine();

        // Ищем слово
        String answer;
        if (synonyms.containsKey(wordToFind)){
            answer = synonyms.get(wordToFind);
        } else {
            answer = "";
        }   

        // Выводим результат
        writer.write(answer);
        writer.newLine();

        reader.close();
        writer.close();
    }
}
