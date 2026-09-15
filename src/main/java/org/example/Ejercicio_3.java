package org.example;

import java.util.Scanner;

public class Ejercicio_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Lectura del tamaño 'n' para dimensionar ambos vectores
        System.out.print("Ingrese la cantidad de productos : ");
        int n = sc.nextInt();

        // 2. Declaración de vectores paralelos
        int[] cantidades = new int[n];
        double[] costos = new double[n];

        // 3. Carga de datos
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Producto " + (i + 1) + " ---");
            System.out.print("Cantidad: ");
            cantidades[i] = sc.nextInt();
            System.out.print("Costo unitario: ");
            costos[i] = sc.nextDouble();
        }

        // 4. Cálculo del precio total y filtrado de importes > $1000
        double totalGeneral = 0;

        System.out.println("\n--- PRODUCTOS CON IMPORTE TOTAL MAYOR A $1000 ---");
        for (int i = 0; i < n; i++) {
            double importeProducto = cantidades[i] * costos[i];
            totalGeneral += importeProducto;

            if (importeProducto > 1000.0) {
                System.out.println("Producto " + (i + 1) + " -> Subtotal: $" + importeProducto
                        + " (Cantidad: " + cantidades[i] + ", Costo unitario: $" + costos[i] + ")");
            }
        }

        // 5. Mostrar importe total general acumulado
        System.out.println("\n=================================");
        System.out.println("Precio total de todos los productos: $" + totalGeneral);
        System.out.println("=================================");

        sc.close();
    }
}