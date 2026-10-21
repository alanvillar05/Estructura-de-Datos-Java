package org.example.practico3;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaTareas {

    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<String> tareas = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== LISTA DE TAREAS ===");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Insertar tarea en una posición (inicio, medio o final)");
            System.out.println("3. Eliminar tarea por nombre");
            System.out.println("4. Mostrar tareas en orden de carga");
            System.out.println("5. Mostrar tareas ordenadas alfabéticamente");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> agregarTarea();
                case 2 -> insertarTarea();
                case 3 -> eliminarTarea();
                case 4 -> mostrarTareas();
                case 5 -> mostrarOrdenadas();
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static void agregarTarea() {
        tareas.add(leerTexto("Tarea: "));
        System.out.println("Tarea agregada.");
    }

    private static void insertarTarea() {
        String tarea = leerTexto("Tarea: ");

        // Posiciones válidas para el usuario: 1 (inicio) hasta size + 1 (final)
        int posicion;
        do {
            posicion = leerEntero("Posición (1 a " + (tareas.size() + 1) + "): ");
        } while (posicion < 1 || posicion > tareas.size() + 1);

        // add(índice, elemento) corre un lugar a la derecha a todas las tareas desde ese índice
        tareas.add(posicion - 1, tarea);
        System.out.println("Tarea insertada en la posición " + posicion + ".");
    }

    private static void eliminarTarea() {
        String nombre = leerTexto("Nombre de la tarea a eliminar: ");

        // Se busca ignorando mayúsculas para que "Estudiar" y "estudiar" sean la misma tarea
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).equalsIgnoreCase(nombre)) {
                tareas.remove(i);
                System.out.println("Tarea eliminada.");
                return;
            }
        }
        System.out.println("No existe la tarea \"" + nombre + "\".");
    }

    private static void mostrarTareas() {
        System.out.println("\n--- Tareas en orden de carga (" + tareas.size() + ") ---");
        imprimir(tareas);
    }

    private static void mostrarOrdenadas() {
        // Se ordena una copia para no perder el orden original de la lista
        ArrayList<String> ordenadas = new ArrayList<>(tareas);
        ordenadas.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("\n--- Tareas pendientes ordenadas (" + ordenadas.size() + ") ---");
        imprimir(ordenadas);
    }

    private static void imprimir(ArrayList<String> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay tareas pendientes.");
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
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
