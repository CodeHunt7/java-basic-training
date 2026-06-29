// https://new.contest.yandex.ru/contests/89515/problems?id=30404%2F2024_10_19%2FGleLWiHsN0
package yantrainalgorithms9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
// import java.util.Random;

public class YanTrainAlgorithms9b1prac3task3 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем входные данные
        long blueTees = Long.parseLong(reader.readLine());
        long redTees = Long.parseLong(reader.readLine());
        long blueSocks = Long.parseLong(reader.readLine());
        long redSocks = Long.parseLong(reader.readLine());

        // перебор тестов
        // Random random = new Random();
        // blueTees = random.nextLong(20);
        // redTees = random.nextLong(20);
        // blueSocks = random.nextLong(20);
        // redSocks = random.nextLong(20);
        // writer.write(Long.toString(blueTees) + " " + Long.toString(redTees) + " " +Long.toString(blueSocks) + " " +Long.toString(redSocks));
        // writer.newLine();
        
        writer.write(solve(blueTees, redTees, blueSocks, redSocks));
        writer.newLine();
        reader.close();
        writer.close();
    }

    public static String solve (long blueTees, long redTees, long blueSocks, long redSocks) {
        long needTees = Long.MAX_VALUE/2, needSocks = Long.MAX_VALUE/2;

        // логика решения
        // нужно забрать все противополжного цвета +1, потом попробовать другой цвет
        // И сумму вариантов
        // 6 2 7 3 - 3 + 4 = 7 против 7 + 8 = 15, побеждает 3 4

        if (blueSocks > 0 && blueTees > 0) { // Есть синие, значит красные +1
            needTees = redTees + 1;
            needSocks = redSocks + 1;
        }
        if (redSocks > 0 && redTees > 0) { // Есть красные, значит синие +1
            if (blueTees + 2 + blueSocks < needTees + needSocks) { // Новый ответ лучше
                needTees = blueTees + 1;
                needSocks = blueSocks + 1;
            }
        }
        if (redTees > 0 && blueTees > 0) { // Кейс когда набрать оба футболки + любой носок
            if (Math.max(redTees, blueTees) + 1 < needTees + needSocks) { // Новый ответ лучше
                needTees = Math.max(redTees, blueTees) + 1;
                needSocks = 1;
            }
        }
        if (blueSocks > 0 && redSocks > 0) { // Кейс когда набрать оба носка + любая футболка
            if (Math.max(blueSocks, redSocks) + 1 < needTees + needSocks) { // Новый ответ лучше
                needTees = 1;
                needSocks = Math.max(blueSocks, redSocks) + 1;
            }
        }

        return needTees + " " + needSocks;
    }
}
