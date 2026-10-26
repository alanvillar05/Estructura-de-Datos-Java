package org.example.tp4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

// Lógica de la agenda, separada de la ventana: la lista dinámica y el archivo .txt
public class Agenda {

    private final ArrayList<Contacto> contactos = new ArrayList<>();
    private final File archivo;

    public Agenda(File archivo) {
        this.archivo = archivo;
    }

    public void agregar(Contacto c) {
        contactos.add(c);
        contactos.sort(Comparator.comparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER));
    }

    // Se elimina por objeto (no por índice) porque la tabla puede estar filtrada por el buscador
    public void eliminar(Contacto c) {
        contactos.remove(c);
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public File getArchivo() {
        return archivo;
    }

    // Guarda en el archivo de siempre (el que se carga al abrir el programa)
    public void guardar() throws IOException {
        guardarEn(archivo);
    }

    // Guarda todos los registros, uno por línea, en el archivo indicado.
    // try-with-resources cierra el archivo solo
    public void guardarEn(File destino) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(destino))) {
            for (Contacto c : contactos) {
                out.println(c.aLineaTxt());
            }
        }
    }

    // Si el .txt existe, carga los registros guardados la vez anterior
    public void cargar() throws IOException {
        contactos.clear();
        if (!archivo.exists()) {
            return;
        }
        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                Contacto c = Contacto.desdeLineaTxt(linea);
                if (c != null) {
                    agregar(c);
                }
            }
        }
    }
}
