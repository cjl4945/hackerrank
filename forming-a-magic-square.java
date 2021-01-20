import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static final int SIZE = 3;
    static int minCost;

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        minCost = Integer.MAX_VALUE;

        int[] numbers = new int[SIZE * SIZE];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        search(new int[SIZE][SIZE], numbers, 0, 0, s);

        return minCost;
    }
    
    static void search(int[][] matrix, int[] numbers, int row, int col, int[][] s){
        if (row == SIZE) {
            if (isMagic(matrix)) {
                int cost = 0;
                for (int r = 0; r < SIZE; r++) {
                    for (int c = 0; c < SIZE; c++) {
                        cost += Math.abs(matrix[r][c] - s[r][c]);
                    }
                }
                minCost = Math.min(minCost, cost);
            }

            return;
        }

        if (col == SIZE) {
            search(matrix, numbers, row + 1, 0, s);

            return;
        }

        int index = row * SIZE + col;
        for (int i = index; i < numbers.length; i++) {
            swap(numbers, index, i);
            matrix[row][col] = numbers[index];
            search(matrix, numbers, row, col + 1, s);
            swap(numbers, index, i);
        }
    }
        
        static void swap(int[] a, int index1, int index2){
            int temp = a[index1];
            a[index1] = a[index2];
            a[index2] = temp;
        }
        
        static boolean isMagic(int[][] matrix){
            final int SUM = 15;
            return matrix[0][0] + matrix[0][1] + matrix[0][2] == SUM && matrix[1][0] + matrix[1][1] + matrix[1][2] == SUM
                && matrix[2][0] + matrix[2][1] + matrix[2][2] == SUM
                && matrix[0][0] + matrix[1][0] + matrix[2][0] == SUM
                && matrix[0][1] + matrix[1][1] + matrix[2][1] == SUM
                && matrix[0][2] + matrix[1][2] + matrix[2][2] == SUM
                && matrix[0][0] + matrix[1][1] + matrix[2][2] == SUM
                && matrix[0][2] + matrix[1][1] + matrix[2][0] == SUM;
        }
    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
