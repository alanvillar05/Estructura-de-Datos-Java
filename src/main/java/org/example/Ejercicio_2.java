package org.example;

import java.util.Scanner;

public class Ejercicio_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Pedir la cantidad de notas para dimensionar el vector
        System.out.print("Ingrese la cantidad de notas: ");
        int n = sc.nextInt();

        // 2. Declarar el vector y los contadores
        double[] notas = new double[n];
        int aprobados = 0;
        int desaprobados = 0;

        // 3. Cargar las notas y evaluar la condición en cada una
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese la nota " + (i + 1) + ": ");
            notas[i] = sc.nextDouble();

            if (notas[i] >= 6.0) {
                aprobados++;
            } else {
                desaprobados++;
            }
        }

        // 4. Mostrar resultados finales
        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Cantidad de aprobados : " + aprobados);
        System.out.println("Cantidad de desaprobados : " + desaprobados);

        sc.close();
    }
}