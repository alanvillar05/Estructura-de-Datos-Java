package org.example.practico3;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionEstudiantes {

    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE ESTUDIANTES ===");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Mostrar estudiantes con nota mayor a 7");
            System.out.println("3. Eliminar estudiantes con nota menor a 4");
            System.out.println("4. Mostrar todos los estudiantes");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> agregarEstudiante();
                case 2 -> mostrarDestacados();
                case 3 -> eliminarDesaprobados();
                case 4 -> mostrarLista("Lista de estudiantes");
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        mostrarLista("Lista final");
        sc.close();
    }

    private static void agregarEstudiante() {
        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = sc.nextLine().trim();
        } while (nombre.isEmpty());

        int edad;
        do {
            edad = leerEntero("Edad: ");
        } while (edad <= 0);

        double nota;
        do {
            nota = leerDecimal("Nota promedio (0 a 10): ");
        } while (nota < 0 || nota > 10);

        estudiantes.add(new Estudiante(nombre, edad, nota));
        System.out.println("Estudiante agregado. Total: " + estudiantes.size());
    }

    private static void mostrarDestacados() {
        System.out.println("\n--- Estudiantes con nota mayor a 7 ---");
        boolean hayAlguno = false;
        for (Estudiante e : estudiantes) {
            if (e.getNotaPromedio() > 7) {
                System.out.println(e);
                hayAlguno = true;
            }
        }
        if (!hayAlguno) {
            System.out.println("No hay estudiantes con nota mayor a 7.");
        }
    }

    private static void eliminarDesaprobados() {
        int antes = estudiantes.size();
        // removeIf recorre la lista y borra los que cumplen la condición sin saltear ninguno
        estudiantes.removeIf(e -> e.getNotaPromedio() < 4);
        System.out.println("Se eliminaron " + (antes - estudiantes.size()) + " estudiantes.");
    }

    private static void mostrarLista(String titulo) {
        System.out.println("\n--- " + titulo + " (" + estudiantes.size() + ") ---");
        if (estudiantes.isEmpty()) {
            System.out.println("La lista está vacía.");
        }
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println((i + 1) + ". " + estudiantes.get(i));
        }
    }

    // Se lee siempre la línea completa para no dejar el Enter pendiente en el Scanner
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

    private static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                // Acepta coma o punto: "7,5" y "7.5"
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException ex) {
                System.out.println("Ingresá un número (por ejemplo 7.5).");
            }
        }
    }
}
