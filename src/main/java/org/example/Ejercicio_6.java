package org.example;

import java.util.Scanner;

public class Ejercicio_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int TOTAL_AUTOS = 12; // Podés cambiarlo a 3 para testear rápido

        // Declaración de vectores paralelos para guardar vehículos y tiempos
        int[] vehiculos = new int[TOTAL_AUTOS];
        double[] tiempos = new double[TOTAL_AUTOS];

        // 1. Carga de datos de los 12 participantes
        for (int i = 0; i < TOTAL_AUTOS; i++) {
            System.out.println("\n--- Participante " + (i + 1) + " de " + TOTAL_AUTOS + " ---");
            System.out.print("Número de vehículo: ");
            vehiculos[i] = sc.nextInt();

            System.out.print("Tiempo realizado (en segundos): ");
            tiempos[i] = sc.nextDouble();
        }

        // 2. Búsqueda del menor tiempo
        double menorTiempo = tiempos[0];
        int vehiculoGanador = vehiculos[0];

        for (int i = 1; i < TOTAL_AUTOS; i++) {
            if (tiempos[i] < menorTiempo) {
                menorTiempo = tiempos[i];
                vehiculoGanador = vehiculos[i];
            }
        }

        // 3. Mostrar el competidor con mejor tiempo
        System.out.println("\n==========================================");
        System.out.println("           GANADOR DE LA CARRERA          ");
        System.out.println("==========================================");
        System.out.println("Vehículo N°:  " + vehiculoGanador);
        System.out.println("Mejor tiempo: " + menorTiempo + " segundos");

        sc.close();
    }
}