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
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
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
                case 5:
                    calculateADeterminant();
                    break;
                case 6:
                    out(performInverseMatrix());
                    break;
                case 0:
                    state = false;
                    break;
                default:
                    System.out.println("Invalid input.");

            }
        }
    }

    public static double performDeterminant(double[][] matrix) {
        double determinant = 0;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        for (int i = 0; i < matrix[0].length; i++) {
            double[][] nm = performMinor(matrix, 0, i);
            determinant += Math.pow(-1, i + 2) * matrix[0][i] * performDeterminant(nm);
        }
        return determinant;
    }

    public static double[][] performInverseMatrix() {
        System.out.println("Enter matrix size: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter matrix:");
        matrix = matrixFilling(rows, columns);
        double[][] inverseMatrix = new double[matrix.length][matrix[0].length];
        double det = performDeterminant(matrix);
        double[][] tMatrix = transposeMainDiagonal(matrix);
        for (int i = 0; i < inverseMatrix.length; i++) {
            for (int j = 0; j < inverseMatrix[i].length; j++) {
                inverseMatrix[i][j] = Math.pow(-1, i + j) * performDeterminant(performMinor(tMatrix, i, j)) / det;
            }
        }
        return inverseMatrix;
    }

    private static double[][] performMinor(double[][] matrix, int row, int column) {
        double[][] newMatrix = new double[matrix.length - 1][matrix[0].length - 1];
        int ni = 0, nj = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (row == i) {
                continue;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (column == j) {
                    continue;
                }
                newMatrix[ni][nj++] = matrix[i][j];
            }
            ni++;
            nj = 0;
        }
        return newMatrix;
    }

    private static void calculateADeterminant() {
        System.out.println("Enter matrix size: ");
        rows = sc.nextInt();
        columns = sc.nextInt();
        System.out.println("Enter matrix:");
        matrix = matrixFilling(rows, columns);
        System.out.println(performDeterminant(matrix));

    }

    private static void transposeMatrix() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");

        int transpose = sc.nextInt();

        switch (transpose) {
            case 1:
                System.out.println("Enter matrix size: ");
                int rows = sc.nextInt();
                int columns = sc.nextInt();
                System.out.println("Enter matrix:");
                out(transposeMainDiagonal(matrixFilling(rows, columns)));
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

    private static double[][] transposeMainDiagonal(double[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
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
        double cons = sc.nextInt();
        matrix = multiConst(matrix, cons);
        System.out.println("The multiplication result is: ");
        out(matrix);
    }

    private static double[][] multiConst(double[][] matrix, double cons) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] * cons;
            }
        }
        return matrix;
    }

    private static double[][] matrixFilling(int rows, int columns) {
        Scanner scanner = new Scanner(System.in);
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            String[] row = scanner.nextLine().split(" ");
            for (int j = 0; j < row.length && j < columns; j++) {
                matrix[i][j] = Double.parseDouble(row[j]);
            }
        }
        return matrix;
    }

    private static void out(double[][] matrix) {
        System.out.println("The result is:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String s = String.format("%6s", String.format("%.3f", matrix[i][j]));
                System.out.print(s + " ");
            }
            System.out.println();

        }
    }

}
