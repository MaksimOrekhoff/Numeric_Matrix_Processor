package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int rows1 = sc.nextInt();
        int columns1 = sc.nextInt();

        int[][] matrix1 = new int[rows][columns];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns1; j++) {
                matrix1[i][j] = sc.nextInt();
            }
        }
        if (rows != rows1 || columns != columns1) {
            System.out.println("ERROR");
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(matrix[i][j] + matrix1[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
