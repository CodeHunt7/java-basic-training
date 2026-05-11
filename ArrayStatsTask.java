import java.util.Arrays;

public class ArrayStatsTask {
    public static void printStats() {
        // Данный массив
        int[] scores = {120, 85, 200, 150, 95};

        // Вычисляем общую сумму очков
        int totalScore = 0;
        for (int i = 0; i < scores.length; i++) {
            totalScore += scores[i];
        }
        // Вычисляем среднее всех очков
        double averageScore = (double) totalScore / scores.length;
        
        // Считаем лучшую и худшую игру
        int bestScore = 0;
        int worstScore = 2_147_483_647;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > bestScore) {
                bestScore = scores[i];
            }
            if (scores[i] < worstScore) {
                worstScore = scores[i];
            }
        }
        
        // Делаем массив из 3 лучших игр
        int[] top3Scores = new int[3];
        Arrays.sort(scores);
        top3Scores = Arrays.copyOfRange(scores, scores.length-3, scores.length);

        System.out.println("Scores: " + Arrays.toString(scores));
        System.out.println("Games count: " + scores.length);
        System.out.println("First score: " + scores[0]);
        System.out.println("Last score: " + scores[scores.length-1]);
        System.out.println("Total score: " + totalScore);
        System.out.println("Average score: " + averageScore);
        System.out.println("Best score: " + bestScore);
        System.out.println("Worst score: " + worstScore);
        System.out.println("Top 3 scores: " + Arrays.toString(top3Scores));
    }
}