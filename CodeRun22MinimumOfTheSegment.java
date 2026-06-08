import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.ArrayDeque;

public class CodeRun22MinimumOfTheSegment {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем: n - длина массива и k - окно движения
        String[] partsReader = reader.readLine().split(" ");
        int n = Integer.parseInt(partsReader[0]);
        int k = Integer.parseInt(partsReader[1]);
        // Читаем сам массив
        int[] sequence = new int[n];
        partsReader = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(partsReader[i]);
        }

        // Дек: двойная очередь для индексов кандидатов на минимум в окне
        Deque<Integer> deque = new ArrayDeque<>();

        // Решение: наполнение дека. Алгоритм такой:
        // 1. удалить из начала индексы, которые вышли из окна
        // 2. удалить с конца индексы, у которых значение больше текущего sequence[i]
        // 3. добавить текущий индекс i в конец
        // 4. если первое окно уже собрано, минимум лежит тут: sequence[deque.peekFirst()]
        
        for (int i = 0; i < n; i++) {
            // Удаляем вышедшие из окна
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.removeFirst();
            }
            // Удаляем с конца бОльшие
            while (!deque.isEmpty() && sequence[deque.peekLast()] > sequence[i]) {
                deque.removeLast();
            }
            // Добавляем текущий индекс
            deque.addLast(i);

            // записываем в ответ
            if (i >= k - 1) {
                writer.write(String.valueOf(sequence[deque.peekFirst()]));
                writer.newLine();
            }
        }

        reader.close();
        writer.close();
    }
}
