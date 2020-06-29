package processor;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int choice;
    static int rows;
    static int rows1;
    static int columns;
    static int columns1;
    static double[][] matrix1;
    static double[][] matrix;
    static double[][] newMatrix;
    static boolean state = true;

    public static void main(String[] args) {

        while (state) {
            System.out.print("1. Add matrices\n" +
                    "2. Multiply matrix to a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "0. Exit\n" +
                    "Your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addMatrices();
                    break;
                case 2:
                    multiplyMatrixToAConstant();
                    break;
                case 3:
                    multiplyMatrix();
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 0:
                    state = false;
                    break;
                default:
                    System.out.println("Invalid input.");

            }
        }
    }

    private static void transposeMatrix() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");

        int transpose = sc.nextInt();

        switch (transpose) {
            case 1:
                mainDiagonal();
                break;
            case 2:
                sideDiagonal();
                break;
            case 3:
                verticalLine();
                break;
            case 4:
                horizontalLine();
                break;
        }

    }

    private static void sideDiagonal() {
        System.out.println("Enter matrix size: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter matrix:");
        matrix = matrixFilling(rows, columns);
        matrix1 = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i + j == matrix[0].length - 1) {
                    matrix1[i][j] = matrix[i][j];
                } else {
                    matrix1[i][j] = matrix[matrix.length - 1 - j][matrix[0].length - 1 - i];
                }
            }
        }
        out(matrix1);
    }

    private static void horizontalLine() {
        System.out.println("Enter matrix size: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter matrix:");
        matrix = matrixFilling(rows, columns);
        matrix1 = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix1[i][j] = matrix[matrix.length - 1 - i][j];
            }
        }
        out(matrix1);
    }

    private static void verticalLine() {
        System.out.println("Enter matrix size: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter matrix:");
        matrix = matrixFilling(rows, columns);
        matrix1 = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix1[i][j] = matrix[i][matrix[0].length - 1 - j];
            }
        }
        out(matrix1);

    }

    private static void mainDiagonal() {
        System.out.println("Enter matrix size: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter matrix:");
        matrix = matrixFilling(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < columns; j++) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        out(matrix);
    }

    private static void multiplyMatrix() {

        System.out.println("Enter size of first matrix: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter first matrix: ");
        matrix = matrixFilling(rows, columns);
        System.out.println("Enter size of second matrix: ");
        rows1 = sc.nextInt();
        columns1 = sc.nextInt();
        System.out.println("Enter second matrix: ");
        matrix1 = matrixFilling(rows1, columns1);

        newMatrix = new double[rows][columns1];
        if (columns == rows1) {
            System.out.println("The multiplication result is: ");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns1; j++) {
                    for (int k = 0; k < rows1; k++) {
                        newMatrix[i][j] += matrix[i][k] * matrix1[k][j];
                    }

                }
            }
            out(newMatrix);
        } else if (rows1 == columns) {
            System.out.println("The multiplication result is: ");
            for (int i = 0; i < rows1; i++) {
                for (int j = 0; j < columns; j++) {
                    for (int k = 0; k < rows; k++) {
                        newMatrix[i][j] += matrix[i][k] * matrix1[k][j];
                    }

                }
            }
            out(newMatrix);
        } else {
            System.out.println("ERROR");
        }
    }

    private static void addMatrices() {
        System.out.println("Enter size of first matrix: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter first matrix: ");
        matrix = matrixFilling(rows, columns);
        System.out.println("Enter size of second matrix: ");
        rows1 = sc.nextInt();
        columns1 = sc.nextInt();
        System.out.println("Enter second matrix: ");
        matrix1 = matrixFilling(rows1, columns1);

        if (rows != rows1 || columns != columns1) {
            System.out.println("ERROR");
        } else {
            System.out.println("The multiplication result is:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(matrix[i][j] + matrix1[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static void multiplyMatrixToAConstant() {
        System.out.println("Enter size of matrix: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter matrix: ");
        matrix = matrixFilling(rows, columns);
        int cons = sc.nextInt();
        matrix = multyConst(matrix, cons);
        System.out.println("The multiplication result is: ");
        out(matrix);
    }

    private static double[][] multyConst(double[][] matrix, int cons) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] * cons;
            }
        }
        return matrix;
    }

    private static double[][] matrixFilling(int rows, int columns) {
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }
        return matrix;
    }

    private static void out(double[][] matrix) {
        System.out.println("The result is:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
