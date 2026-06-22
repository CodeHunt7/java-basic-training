// Условие "Олигополия"
// У каждой компании есть начальный капитал. Компания может поглотить компанию
// с меньшим капиталом и забрать ее капитал себе. Побеждает единственная оставшаяся компания.
// Определить, кто может победить.
// Дается: число N компаний и потом через пробел их капиталы. Капиталы отсортированы
// Ответ: массив где 1 у компаний которые могут победить
import java.util.Scanner;
import java.util.Arrays;

public class YanTrainAlgorithms9b1test2 {
    public static void run ()
    {
        // Читаем ввод
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] capitalsArray = new int[n];

        String[] lineReader = scanner.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            capitalsArray[i] = Integer.parseInt(lineReader[i]);
        }

        // Вывод ответа, закрываем сканнер
        System.out.println(Arrays.toString(oligopoly3(n, capitalsArray)));
        scanner.close();
    }
    
    // Функция решения v1
    public static int[] oligopoly(int n, int[] capitalsArray) {
        int[] answer = new int[n];

        // Кейс когда одна компания
        if (capitalsArray.length == 1) {
            answer[0] = 1; // она победила
            return answer;
        }

        // Идем с конца, ищем первую которая не может победить
        for (int i = n - 1; i > -1; i--) {
            // Обработаем первое значение чтобы не выйти за границы
            if (i == 0 && capitalsArray[i] <= capitalsArray[i + 1]) {
                answer[i] = 0;
                break;
            }
            // Обработаем последнее значение
            if (i == n - 1) {
                if (capitalsArray[i] > capitalsArray[i - 1]) {
                    answer[i] = 1;
                    continue;
                } else {
                    answer[i] = 0;
                    continue;
                }
            }
            // Обрабатываем остальные
            int thisCompCapital = capitalsArray[i];
            for (int j = i - 1; j > -1; j--) {
                // Складываем если может поглотить
                if (capitalsArray[i] > capitalsArray[j]) {
                    thisCompCapital += capitalsArray[j];
                }
                // Уже больше? Выходим из цикла
                if (thisCompCapital > capitalsArray[i + 1]) {
                    answer[i] = 1;
                    break;
                }
            }
            // Все ещё меньше-равно? Значит не может поглотить, нашли точку решения
            if (thisCompCapital <= capitalsArray[i + 1]) {
                answer[i] = 0;
                Arrays.fill(answer, 0, i, 0); // занулить массив
                Arrays.fill(answer, i, n, 1); // 1 в остальной массив
                break;
            }

        }
        // 1 2 2 не пройдет, доделывать не буду
        return answer;
    }
    
    // Функция решения v2
    public static int[] oligopoly2(int n, int[] capitalsArray) {
        int[] answer = new int[n];

        // Кейс когда одна компания
        if (n == 1) {
            answer[0] = 1; // она победила
            return answer;
        }

        // Кейс когда все одинаковые
        boolean allEqual = true;
        // Кейс когда несколько одинаковых подряд
        int firstCanStart = n;

        // prefixSum[i] - это сумма всех capitalsArray от [0] до [i]
        long[] prefixSum = new long[n];
        long currSum = 0;
        for (int i = 0; i < n; i++) {
            currSum += capitalsArray[i];
            prefixSum[i] = currSum;
            // Заодно проверяем на все одинаковые
            if (capitalsArray[i] != capitalsArray[0]) {
                allEqual = false;
            }
            // 
            if (i > 0 && firstCanStart == n && capitalsArray[i] > capitalsArray[i - 1]) {
                firstCanStart = i;
            }
        }

        // Если все одинаковые
        if (allEqual) {
            return answer; // Он у нас нули по умолчанию
        }

        // Идем с конца и ищем когда суммы не хватит на перекрытие
        for (int i = n - 2; i >= 0; i--) {
            if (prefixSum[i] <= capitalsArray[i + 1]) {
                Arrays.fill(answer, 0, i + 1, 0);
                Arrays.fill(answer, i + 1, n, 1);
                break;
            } else if (i < firstCanStart) {
                Arrays.fill(answer, 0, i + 1, 0);
                Arrays.fill(answer, i + 1, n, 1);
                break;
            }
        }

        //System.out.println(Arrays.toString(prefixSum));
        return answer;
    }
    
    // Функция решения v3
    public static int[] oligopoly3(int n, int[] capitalsArray) {
        int[] answer = new int[n];
        
        // Кейс когда 1 компания
        if (n == 1) {
            answer[0] = 1; // она победила
            return answer;
        }

        long nowSum = capitalsArray[0];
        int firstWinner = n;
        int lastLoser = 0;

        for (int i = 1; i < n - 1; i++) { // Идем со второго до предпоследнего
            nowSum += capitalsArray[i];
            if (capitalsArray[i] > capitalsArray[i - 1] && nowSum > capitalsArray[i + 1]) {
                // Когда текущая > предыдущей а сумма всех до нее включительно > следующей
                if (firstWinner == n) {
                    firstWinner = i; // Нашли 1 победителя
                }
            }
            if (nowSum <= capitalsArray[i + 1]) {
                lastLoser = i; // Перезаписываем последнего лузера
            }
        }

        int winnerPos = Math.max(firstWinner, lastLoser + 1);
        Arrays.fill(answer, 0, winnerPos, 0);
        Arrays.fill(answer, winnerPos, n, 1);
        
        //Проверяем последний
        if (capitalsArray[n-1] > capitalsArray[n-2]) {
            answer[n - 1] = 1;
        }

        // 1 1 1 2 10
        return answer;
    }

}
