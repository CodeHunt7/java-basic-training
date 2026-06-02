import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CodeRun3PrintRouteOfTheMaximumCost {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Читаем размеры таблицы
        String[] readParts = reader.readLine().split(" ");
        int n = Integer.parseInt(readParts[0]);
        int m = Integer.parseInt(readParts[1]);

        // Читаем саму таблицу
        int[][] originalTable = new int[n][m];
        for (int i = 0; i < n; i++) {
            readParts = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                originalTable[i][j] = Integer.parseInt(readParts[j]);
            }
        }

        // Создаем таблицу стоимости путей
        // pathCosts[i][j] = максимальная стоимость, чтобы добраться до клетки i,j
        // paretn[][] = откуда пришел в эту клетку D или R
        int[][] pathCosts = new int[n][m];
        char[][] parent = new char[n][m];
        
        // Идея: стоимость текущей клетки + максимум из двух путей: сверху или слева + откуда
        
        // Заполняем первую клетку и верхний ряд
        pathCosts[0][0] = originalTable[0][0];
        parent[0][0] = 'S';
        for (int j = 1; j < m; j++) {
            pathCosts[0][j] = originalTable[0][j] + pathCosts[0][j - 1];
            parent[0][j] = 'R';
        }
        // Заполняем первый столбец
        for (int i = 1; i < n; i++) {
            pathCosts[i][0] = originalTable[i][0] + pathCosts[i - 1][0];
            parent[i][0] = 'D';
        }
        // Заполняем остальную часть таблицы
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (pathCosts[i - 1][j] > pathCosts[i][j - 1]) {
                    pathCosts[i][j] = originalTable[i][j] + pathCosts[i - 1][j];
                    parent[i][j] = 'D';
                } else {
                    pathCosts[i][j] = originalTable[i][j] + pathCosts[i][j - 1];
                    parent[i][j] = 'R';
                }
            }
        }

        // Весь путь для проверки
        // writer.write(Arrays.deepToString(pathCosts));
        // writer.write(Arrays.deepToString(parent));

        // Ищем путь в обратном направлении от правой нижней клетки
        StringBuilder maxPathLetters = new StringBuilder();

        int currI = n - 1;
        int currJ = m - 1;
        for (int i = n + m - 2; i > 0; i--) {
            maxPathLetters.append(parent[currI][currJ]);
            maxPathLetters.append(" ");
            if (parent[currI][currJ] == 'D') {
                currI--;
            } else if (parent[currI][currJ] == 'R') {
                currJ--;
            } else {
                break;
            }
        }
        maxPathLetters.reverse();

        // Ответ 1 - стоимость пути до правой нижней клетки  
        writer.write(String.valueOf(pathCosts[n - 1][m - 1]));
        writer.newLine();
        // Ответ 2 - буквы самого пути
        writer.write(maxPathLetters.toString());
        writer.newLine();

        reader.close();
        writer.close();
    }
}
