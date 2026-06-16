import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CodeRun85TreeHeight {

    // Класс для ячейки дерева
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public static int insertAndGetHeight(TreeNode root, int number) {
        TreeNode current = root;
        int height = 1;

        while (true) {
            if (number < current.value) {
                if (current.left == null) {
                    current.left = new TreeNode(number);
                    return height + 1;
                } else {
                    current = current.left;
                    height++;
                }
            } else if (number > current.value) {
                if (current.right == null) {
                    current.right = new TreeNode(number);
                    return height + 1;
                } else {
                    current = current.right;
                    height++;
                }
            } else {
                // Если число уже существует в дереве, не увеличиваем высоту
                return -1;
            }
        }
    }

    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Создаем заготовку под объект корня и ответа задачи
        TreeNode root = null;
        int maxHeight = 0;

        // Читаем ввод
        String[] partReader = reader.readLine().split(" ");

        for (String part : partReader) {
            int number = Integer.parseInt(part);

            // Останавливаем чтение на нуле
            if (number == 0) {
                break;
            }
            
            // Добавляем корень
            if (root == null) {
                root = new TreeNode(number);
                maxHeight = 1;
            } else {
                int height = insertAndGetHeight(root, number);
                if (height > maxHeight) {
                    maxHeight = height;
                }
            }
        }

        writer.write(String.valueOf(maxHeight));
        writer.newLine();

        reader.close();
        writer.close();
    }
}