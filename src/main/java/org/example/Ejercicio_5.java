package org.example;

import java.util.Scanner;

public class Ejercicio_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int CANTIDAD_CLIENTES = 5;

        // Vectores paralelos para guardar la información de los 5 clientes
        String[] dnis = new String[CANTIDAD_CLIENTES];
        int[] tiposServicio = new int[CANTIDAD_CLIENTES];
        double[] montosPagar = new double[CANTIDAD_CLIENTES];

        // 1. Carga y procesamiento de cada cliente
        for (int i = 0; i < CANTIDAD_CLIENTES; i++) {
            System.out.println("\n--- Cliente " + (i + 1) + " de " + CANTIDAD_CLIENTES + " ---");

            System.out.print("Ingrese DNI del cliente: ");
            dnis[i] = sc.next();

            System.out.println("Tipos de servicio disponibles:");
            System.out.println("  1. Internet 30 megas ($750)");
            System.out.println("  2. Internet 50 megas ($1100)");
            System.out.println("  3. Internet 100 megas ($1500 con 5% desc.)");
            // Se vuelve a pedir hasta que la opción sea válida, así nunca queda un monto en 0
            do {
                System.out.print("Seleccione el servicio (1, 2 o 3): ");
                tiposServicio[i] = sc.nextInt();
            } while (tiposServicio[i] < 1 || tiposServicio[i] > 3);

            // 2. Cálculo del monto según el plan contratado
            switch (tiposServicio[i]) {
                case 1:
                    montosPagar[i] = 750.0;
                    break;
                case 2:
                    montosPagar[i] = 1100.0;
                    break;
                case 3:
                    // 5% de descuento sobre $1500: 1500 * 0.95 = 1425.0
                    montosPagar[i] = 1500.0 * 0.95;
                    break;
            }
        }

        // 3. Informar por pantalla los datos de cada cliente
        System.out.println("\n==================================================");
        System.out.println("             FACTURACIÓN DE CLIENTES              ");
        System.out.println("==================================================");

        for (int i = 0; i < CANTIDAD_CLIENTES; i++) {
            System.out.println("Cliente " + (i + 1) + ":");
            System.out.println("  • DNI:               " + dnis[i]);
            System.out.println("  • Tipo de servicio:  " + tiposServicio[i]);
            System.out.println("  • Monto a pagar:     $" + montosPagar[i]);
            System.out.println("--------------------------------------------------");
        }

        sc.close();
    }
}