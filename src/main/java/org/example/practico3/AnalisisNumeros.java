package org.example.practico3;

import java.util.ArrayList;
import java.util.Scanner;

public class AnalisisNumeros {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Integer> numeros = cargarNumeros();

        if (numeros.isEmpty()) {
            System.out.println("No se ingresaron números.");
            sc.close();
            return;
        }

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Números ingresados: " + numeros);
        System.out.println("Mayor: " + mayor(numeros));
        System.out.println("Menor: " + menor(numeros));
        System.out.printf("Promedio: %.2f%n", promedio(numeros));
        System.out.println("Sin duplicados: " + sinDuplicados(numeros));

        sc.close();
    }

    // Lee hasta que se ingrese un negativo, que funciona como corte y no se guarda
    private static ArrayList<Integer> cargarNumeros() {
        ArrayList<Integer> numeros = new ArrayList<>();
        System.out.println("Ingresá números enteros (un negativo para terminar):");
        while (true) {
            int numero = leerEntero("Número: ");
            if (numero < 0) {
                return numeros;
            }
            numeros.add(numero);
        }
    }

    private static int mayor(ArrayList<Integer> numeros) {
        int mayor = numeros.get(0);
        for (int n : numeros) {
            if (n > mayor) {
                mayor = n;
            }
        }
        return mayor;
    }

    private static int menor(ArrayList<Integer> numeros) {
        int menor = numeros.get(0);
        for (int n : numeros) {
            if (n < menor) {
                menor = n;
            }
        }
        return menor;
    }

    private static double promedio(ArrayList<Integer> numeros) {
        int suma = 0;
        for (int n : numeros) {
            suma += n;
        }
        // Se castea a double para que la división no se trunque (7 / 2 daría 3)
        return (double) suma / numeros.size();
    }

    // Se arma una lista nueva y se agrega cada número solo si todavía no está: mantiene el orden
    private static ArrayList<Integer> sinDuplicados(ArrayList<Integer> numeros) {
        ArrayList<Integer> resultado = new ArrayList<>();
        for (Integer n : numeros) {
            if (!resultado.contains(n)) {
                resultado.add(n);
            }
        }
        return resultado;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Ingresá un número entero.");
            }
        }
    }
}
