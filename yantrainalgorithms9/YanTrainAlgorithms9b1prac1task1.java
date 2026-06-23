package yantrainalgorithms9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class YanTrainAlgorithms9b1prac1task1 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем данные
        // P и V — номер дерева, где ведро Васи и на сколько может удаляться. Q и M для Маши
        int VasyaStart, VasyaRange, MashaStart, MashaRange;
        String[] lineReader = reader.readLine().split(" ");
        VasyaStart = Integer.parseInt(lineReader[0]);
        VasyaRange = Integer.parseInt(lineReader[1]);
        lineReader = reader.readLine().split(" ");
        MashaStart = Integer.parseInt(lineReader[0]);
        MashaRange = Integer.parseInt(lineReader[1]);

        int vasFrom = VasyaStart - VasyaRange;
        int vasTo = VasyaStart + VasyaRange;
        int mashFrom = MashaStart - MashaRange;
        int mashTo = MashaStart + MashaRange;

        // Посчитай оба отрезка целиком, а потом один раз вычти длину их пересечения.
        int answer = 0;

        // Если отрезки пересекаются
        if (Math.max(vasFrom, mashFrom) <= Math.min(vasTo, mashTo)) {
            answer = Math.max(vasTo, mashTo) - Math.min(vasFrom, mashFrom) + 1;
        } else { // если НЕ пересекаются
            answer = vasTo - vasFrom + 2 + mashTo - mashFrom;
        }

        // Кейс когда не пересекаются - СТАРОЕ РЕШЕНИЕ
        // if ((vasFrom < mashFrom && vasTo < mashTo && vasTo < mashFrom)
        //     || (mashFrom < vasFrom && mashTo < vasTo && mashTo < vasFrom)) {
        //     answer = vasTo - vasFrom + 2 + mashTo - mashFrom;
        // } else {
        //     int[] sortingArray = { vasFrom, vasTo, mashFrom, mashTo };
        //     Arrays.sort(sortingArray);
        //     answer = vasTo - vasFrom + 1 + mashTo - mashFrom - (sortingArray[2]-sortingArray[1]);
        // }

        writer.write(Integer.toString(answer));
        writer.newLine();
        reader.close();
        writer.close();
    }
}
// Пример: 0 7 / 12 5
// Вася ренж от -7 до 7, Маша ренж от 7 до 17
// Второй 0 7 / 10 5 --> -7 7 + 5 15
// Третий 4 2 / 12 2 --> -7 7 -5 5


// -7 7 7 17 ----- 14 + 10 - (7-7) + 1 = 25
// -7 7 5 15 ----- 14 + 10 - (7-5) + 1 = 23
// -7 7 -5 5 ----- 14 + 10 - (5- -5) + 1 = 15
// 2 6 10 14 ----- 4 + 4 - (10-6) + 1 = 10 ??
