package org.example;

import java.util.Scanner;

public class Ejercicio_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Ingreso de la cantidad de notas
        // Se exige n > 0: con 0 el vector queda vacío y notas[0] lanza ArrayIndexOutOfBoundsException
        int n;
        do {
            System.out.print("Ingrese la cantidad de notas (mayor a 0): ");
            n = sc.nextInt();
        } while (n <= 0);

        // 2. Definición del vector según el tamaño n
        double[] notas = new double[n];
        double suma = 0;

        // 3. Carga de notas y acumulación
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese la nota " + (i + 1) + ": ");
            notas[i] = sc.nextDouble();
            suma += notas[i];
        }

        // 4. Búsqueda de la nota más alta
        double mayor = notas[0];
        for (int i = 1; i < n; i++) {
            if (notas[i] > mayor) {
                mayor = notas[i];
            }
        }

        // 5. Cálculo y muestra de resultados
        double promedio = suma / n;
        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Nota más alta: " + mayor);
        System.out.println("Promedio de notas: " + promedio);

        sc.close();
    }
}