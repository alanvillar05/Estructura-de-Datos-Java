package org.example;

import java.util.Scanner;

public class Ejercicio_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Variables estadísticas y contadores
        int totalCensados = 0;
        int varones = 0;
        int mujeres = 0;
        int varonesEntre16y65 = 0;

        // Variables para registrar los datos de la persona de mayor edad
        int mayorEdad = -1;
        String dniMayor = "";
        char sexoMayor = ' ';

        System.out.println("=== SISTEMA DE CENSO PROVINCIAL ===");
        System.out.println("(Para finalizar la carga, ingrese DNI = 0)\n");

        while (true) {
            System.out.print("Ingrese número de documento (0 para salir): ");
            String dni = sc.next().trim();

            // Condición de corte: si ingresa "0", finaliza el ciclo
            if (dni.equals("0")) {
                break;
            }

            System.out.print("Ingrese edad: ");
            int edad = sc.nextInt();

            // Se valida el sexo para que total = varones + mujeres
            char sexo;
            do {
                System.out.print("Ingrese sexo ('F' o 'M'): ");
                sexo = sc.next().toUpperCase().charAt(0);
            } while (sexo != 'F' && sexo != 'M');

            // 1. Contador global
            totalCensados++;

            // 2. Procesamiento por sexo y franja etaria
            if (sexo == 'M') {
                varones++;
                if (edad >= 16 && edad <= 65) {
                    varonesEntre16y65++;
                }
            } else {
                mujeres++;
            }

            // 3. Registro de la persona con mayor edad
            if (edad > mayorEdad) {
                mayorEdad = edad;
                dniMayor = dni;
                sexoMayor = sexo;
            }

            System.out.println("-----------------------------------");
        }

        // 4. Muestra de resultados finales
        System.out.println("\n==========================================");
        System.out.println("         RESULTADOS DEL CENSO             ");
        System.out.println("==========================================");

        if (totalCensados > 0) {
            System.out.println("Cantidad total de personas censadas: " + totalCensados);
            System.out.println("Cantidad de varones: " + varones);
            System.out.println("Cantidad de mujeres: " + mujeres);

            // Cálculo del porcentaje sobre el total de varones
            if (varones > 0) {
                double porcentaje = ((double) varonesEntre16y65 / varones) * 100.0;
                System.out.printf("Porcentaje de varones entre 16 y 65 años: %.2f%%%n", porcentaje);
            } else {
                System.out.println("Porcentaje de varones entre 16 y 65 años: No se registraron varones.");
            }

            // Datos de la persona de mayor edad
            System.out.println("\n--- Persona de mayor edad ---");
            System.out.println("  • DNI:  " + dniMayor);
            System.out.println("  • Edad: " + mayorEdad + " años");
            System.out.println("  • Sexo: " + sexoMayor);
        } else {
            System.out.println("No se ingresaron registros en el censo.");
        }

        sc.close();
    }
}