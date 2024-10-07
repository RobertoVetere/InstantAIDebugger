package org.robedev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Intentar leer un archivo inexistente
            File file = new File("nonexistentfile.txt");
            Scanner scanner = new Scanner(file);

            // Intentar acceder a un índice inválido de un array
            int[] numbers = {1, 2, 3};
            int number = numbers[5];  // Esto causará ArrayIndexOutOfBoundsException

            // Operación aritmética con división por cero
            int result = 10 / 0;  // Esto causará ArithmeticException

        } catch (Throwable e) {
            // Manejar el error usando InstantAIDebugger y luego imprimir el stack trace completo
            InstantAIDebugger.handleError(e, "Español");
            e.printStackTrace();  // Imprimir el stack trace para verlo en consola
        }
    }
}