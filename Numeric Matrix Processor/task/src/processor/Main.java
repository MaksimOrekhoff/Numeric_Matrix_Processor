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
        int cons = sc.nextInt();
        matrix = multyArray(matrix, cons);
        out(matrix);
        /*
        int rows1 = sc.nextInt();
        int columns1 = sc.nextInt();

        int[][] matrix1 = new int[rows1][columns1];
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
        } */
    }

    private static int[][] multyArray(int[][] matrix, int cons) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] * cons;
            }
        }
        return matrix;
    }

    private static void out(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
