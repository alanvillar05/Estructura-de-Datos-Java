package org.example;

import java.util.Scanner;

public class Ejercicio_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Constante de cantidad de camiones (cambiá a 2 para probar rápido si querés)
        final int CANTIDAD = 30;

        // Vectores paralelos de tipo String para almacenar los datos
        String[] patentes = new String[CANTIDAD];
        String[] choferes = new String[CANTIDAD];
        String[] cargas = new String[CANTIDAD];
        String[] horas = new String[CANTIDAD];

        int contadorTe = 0;

        // 1. Carga de datos
        for (int i = 0; i < CANTIDAD; i++) {
            System.out.println("\n--- Datos del Camión " + (i + 1) + " ---");

            System.out.print("Patente: ");
            patentes[i] = sc.nextLine().trim();

            System.out.print("Nombre y Apellido del chofer: ");
            choferes[i] = sc.nextLine().trim();

            System.out.print("Tipo de carga (madera / yerba / té): ");
            cargas[i] = sc.nextLine().trim().toLowerCase();

            System.out.print("Hora de egreso (ej. 14:30): ");
            horas[i] = sc.nextLine().trim();

            // Verificamos si la carga es "té" (con o sin tilde)
            if (cargas[i].equals("té") || cargas[i].equals("te")) {
                contadorTe++;
            }
        }

        // 2. Mostrar todos los datos registrados
        System.out.println("\n==========================================");
        System.out.println("          REGISTRO DE EGRESOS             ");
        System.out.println("==========================================");

        for (int i = 0; i < CANTIDAD; i++) {
            System.out.println("Camión " + (i + 1) + ":");
            System.out.println("  • Patente: " + patentes[i]);
            System.out.println("  • Chofer:  " + choferes[i]);
            System.out.println("  • Carga:   " + cargas[i]);
            System.out.println("  • Egreso:  " + horas[i]);
            System.out.println("------------------------------------------");
        }

        // 3. Resultado final del conteo
        System.out.println("Cantidad de camiones que cargaron té: " + contadorTe);

        sc.close();
    }
}