package yantrainalgorithms9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class YanTrainAlgorithms9b1prac1task2 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем вход
        // В доме M этажей, на каждой лестничной клетке одинаковое количество
        // k1 и k2 - номера квартир, p2 - подъезд, n2 - этаж
        String[] lineReader = reader.readLine().split(" ");
        int k1 = Integer.parseInt(lineReader[0]), m = Integer.parseInt(lineReader[1]);
        int k2 = Integer.parseInt(lineReader[2]), p2 = Integer.parseInt(lineReader[3]);
        int n2 = Integer.parseInt(lineReader[4]);

        // Искомый ответ: p1 = answer[0], n1 = answer[1]
        int[] answer = calculateFlats(k1, m, k2, p2, n2);

        writer.write(Integer.toString(answer[0]) + " " + Integer.toString(answer[1]));
        writer.newLine();
        reader.close();
        writer.close();
    }
    
    public static int[] calculateFlats(int k1, int m, int k2, int p2, int n2) {
        // Ответ пишем сюда
        int[] answer = new int[2];

        // Проверяем противоречие данных
        if (m < n2) { // k2 на несуществующем этаже
            Arrays.fill(answer, -1);
            return answer;
        }
        
        // Перменные для пробега
        boolean found = false; 
        long possibleP = -1;
        long possibleN = -1;
        boolean pAmbiguous = false;
        boolean nAmbiguous = false;

        // Считаем номер площадки от начала дома для k2
        int k2Landing = (p2 - 1) * m + n2;

        // Должно выполняться (k2Landing-1)i < k2 <= k2Landing*i где i - это квартир на этаже
        for (int i = 1; i <= 1_000_000; i++) {
            if ((long) (k2Landing - 1) * i < k2 && k2 <= (long) k2Landing * i) { // Не противоречит k2
                long block = (long) m * i;
                possibleP = (k1 - 1) / block + 1;
                possibleN = ((k1 - 1 - (m * i * (possibleP - 1))) / i) + 1;

                if (!found) { // Нашли первое решение
                    found = true;
                    answer[0] = (int)possibleP;
                    answer[1] = (int)possibleN;
                } else { // Нашли ещё решение
                    if (possibleP != answer[0]) { // Подъъезд неоднозначен
                        answer[0] = 0;
                        pAmbiguous = true;
                    }
                    if (possibleN != answer[1]) { // Этаж неоднозначен
                        answer[1] = 0;
                        nAmbiguous = true;
                    }
                }
            }
        }
        
        // Возвращаем ответ
        if (!found) { // Нет решений по противоречию
            Arrays.fill(answer, -1);
            return answer;
        }
        if (pAmbiguous) { // Падик неоднозначен
            answer[0] = 0;
        }
        if (nAmbiguous) { // Этаж не однозначен
            answer[1] = 0;
        }
        return answer;
        
        // if (k2 <= m && p2 > 1) { // M - количество этажей то есть и минимально квартир в подъезде
        //     Arrays.fill(answer, -1);
        //     return answer;
        // }
        // // Найдем количество квартир на этаже
        // if (k2 % n2 == 0) { // Четко делится, например 6/2 = 3
        //     flatsInFloor = k2 / n2;
        // } else { // Нечетко, например 5/2 = 2
        //     flatsInFloor = k2 / n2 + 1;
        // }

        // // Найдем количество квартир в подъезде m * flatsInFloor
        // int flatsInPadik = m * flatsInFloor;

        // // Найдем номер подъезда p1 где квартира flatsInPadik / k1
        // answer[0] = k1 / flatsInPadik + 1;

        // // Найдем этаж n1
        // if (m == 1) { // Если этаж всего один   
        //     answer[1] = 1; // То и искомый один
        // } else {
        //     answer[1] = ((k1 - (flatsInPadik * (answer[0] - 1))) / flatsInFloor) + 1;
        // }

        // System.out.println("На этаже: " + Integer.toString(flatsInFloor) + " кв. В падике: " + Integer.toString(flatsInPadik)
        //         + " кв.");

        // return answer;
    }
}

// 89 20 41 1 11 
// Ищем подъезд и этаж для 89 кв, в 20-этажном доме. Для 41 кв - 1 подъезд и 11 этаж
// Ответ: 2 подъезд и 3 этаж

// 11 1 1 1 1
// Ищем подъезд и этаж для 11 кв, в 1-этажном доме. Для 1 кв - 1 подъезд и 1 этаж