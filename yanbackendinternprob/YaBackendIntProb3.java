package yanbackendinternprob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YaBackendIntProb3 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    
        // Читаем ввод
        String[] lineReader = reader.readLine().split(" ");
        int nHomeworkCount = Integer.parseInt(lineReader[0]);
        int mTasksInHomework = Integer.parseInt(lineReader[1]);
        int kExtraTasksCapableOf = Integer.parseInt(lineReader[2]);
        
        lineReader = reader.readLine().split(" ");
        int[] doneTasks = new int[nHomeworkCount];
        for (int i = 0; i < nHomeworkCount; i++) {
            doneTasks[i] = Integer.parseInt(lineReader[i]);
        }
        
        lineReader = reader.readLine().split(" ");
        int[] scoresForNumberOfTasks = new int[mTasksInHomework + 1];
        for (int i = 0; i <= mTasksInHomework; i++) {
            scoresForNumberOfTasks[i] = Integer.parseInt(lineReader[i]);
        }

        // Считаем баллы, которые Вася уже точно получил
        long baseScore = 0;
        for (int i = 0; i < nHomeworkCount; i++) {
            baseScore += scoresForNumberOfTasks[doneTasks[i]];
        }

        // dp[used] — максимальная прибавка к баллам,
        // если мы потратили used дополнительных задач
        long[] dp = new long[kExtraTasksCapableOf + 1];

        for (int homework = 0; homework < nHomeworkCount; homework++) {
            long[] nextDp = new long[kExtraTasksCapableOf + 1];

            int alreadyDone = doneTasks[homework];
            int maxCanAdd = mTasksInHomework - alreadyDone;

            for (int used = 0; used <= kExtraTasksCapableOf; used++) {
                for (int add = 0; add <= maxCanAdd; add++) {
                    if (used + add > kExtraTasksCapableOf) {
                        break;
                    }

                    long extraScore = scoresForNumberOfTasks[alreadyDone + add] - scoresForNumberOfTasks[alreadyDone];

                    nextDp[used + add] = Math.max(
                            nextDp[used + add],
                            dp[used] + extraScore);
                }
            }

            dp = nextDp;
        }

        // Лучшая прибавка к баллам
        long bestExtraScore = 0;

        for (int used = 0; used <= kExtraTasksCapableOf; used++) {
            bestExtraScore = Math.max(bestExtraScore, dp[used]);
        }

        // Выводим ответ
        writer.write(String.valueOf(baseScore + bestExtraScore));
        writer.newLine();

        // Отладка
        // writer.write(String.valueOf(baseScore));
        // writer.newLine();
        // writer.write(Arrays.toString(dp));
        // writer.newLine();

        reader.close();
        writer.close();
    }
}