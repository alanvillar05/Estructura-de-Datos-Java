package org.example.practico3;

public class Estudiante {
    // Atributos privados (encapsulamiento) y final: no cambian después del constructor
    private final String nombre;
    private final int edad;
    private final double notaPromedio;

    public Estudiante(String nombre, int edad, double notaPromedio) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaPromedio = notaPromedio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getNotaPromedio() {
        return notaPromedio;
    }

    @Override
    public String toString() {
        return String.format("%s, %d años, promedio %.2f", nombre, edad, notaPromedio);
    }
}
