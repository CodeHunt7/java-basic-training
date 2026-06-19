// Условие:
// Нужно раскодировать строку: 'a' по 'i' - 1 до 9, с 'j' по 'z' - 10# до 26#.
// Пример: hello будет закодирована как «8512#12#15#». На вход кодировка, на выходе слово.
import java.util.Scanner;

public class YanTrainAlgorithms9b1test1 {

    public static void run() {
        // читаем ввод
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Добавляем в конец звезды, чтобы можно было смореть i+2 без ошибок
        input += "**";
        String answer = "";

        for (int i = 0; i < input.length() - 2; i++) {
            if (input.charAt(i+2) == '#') {
                answer += (char) ('a' + Integer.parseInt(input.substring(i, i + 2)) - 1);
                i += 2;
            } else {
                answer += (char) ('a' + input.charAt(i) - '0' - 1);
            }
        }

        System.out.println(answer);
        scanner.close();
    }
    
}