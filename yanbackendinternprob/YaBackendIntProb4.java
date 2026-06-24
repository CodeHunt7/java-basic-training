package yanbackendinternprob;
import java.io.*;

public class YaBackendIntProb4 {
    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] lineReader = reader.readLine().split(" ");
        int nRows = Integer.parseInt(lineReader[0]);
        int mCols = Integer.parseInt(lineReader[1]);

        char[][] qrCode = new char[nRows][mCols];

        for (int row = 0; row < nRows; row++) {
            qrCode[row] = reader.readLine().toCharArray();
        }

        int bestRowShift = 0;
        int bestColShift = 0;

        for (int rowShift = 0; rowShift < nRows; rowShift++) {
            for (int colShift = 0; colShift < mCols; colShift++) {
                if (isBetter(qrCode, nRows, mCols, rowShift, colShift, bestRowShift, bestColShift)) {
                    bestRowShift = rowShift;
                    bestColShift = colShift;
                }
            }
        }

        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < mCols; col++) {
                writer.write(qrCode[(bestRowShift + row) % nRows][(bestColShift + col) % mCols]);
            }
            writer.newLine();
        }

        reader.close();
        writer.close();
    }

    private static boolean isBetter(
            char[][] qrCode,
            int nRows,
            int mCols,
            int rowShiftA,
            int colShiftA,
            int rowShiftB,
            int colShiftB
    ) {
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < mCols; col++) {
                char a = qrCode[(rowShiftA + row) % nRows][(colShiftA + col) % mCols];
                char b = qrCode[(rowShiftB + row) % nRows][(colShiftB + col) % mCols];

                if (a < b) {
                    return true;
                }

                if (a > b) {
                    return false;
                }
            }
        }

        return false;
    }
}