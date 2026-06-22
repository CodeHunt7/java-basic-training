package coderun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CodeRun2СheapestWay {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем размеры таблицы
        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        // Читаем саму таблицу
        int[][] pathTable = new int[n][m];
        for (int i = 0; i < n; i++) {
            parts = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                pathTable[i][j] = Integer.parseInt(parts[j]);
            }
        }

        // Создаем таблицу стоимости путей
        // pathCosts[i][j] = минимальная стоимость, чтобы добраться до клетки i,j
        // Идея такая: стоимость текущей клетки + минимум из двух путей: сверху или слева
        int[][] pathCosts = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Кейс первой клетки
                if (i == 0 && j == 0) {
                    pathCosts[i][j] = pathTable[i][j];
                } else if (i == 0){
                    pathCosts[i][j] = pathTable[i][j] + pathCosts[i][j - 1];
                } else if (j == 0) {
                    pathCosts[i][j] = pathTable[i][j] + pathCosts[i-1][j];
                } else {
                    if (pathCosts[i - 1][j] < pathCosts[i][j - 1]) {
                        pathCosts[i][j] = pathTable[i][j] + pathCosts[i-1][j];
                    } else {
                        pathCosts[i][j] = pathTable[i][j] + pathCosts[i][j-1];
                    }
                }
            }
        }

        // Весь путь для проверки
        // writer.write(Arrays.deepToString(pathCosts));

        // Ответ - стоимость пути до правой нижней клетки
        writer.write(String.valueOf(pathCosts[n - 1][m - 1]));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
