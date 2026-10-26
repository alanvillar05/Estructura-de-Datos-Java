package org.example.tp4;

public class Contacto {
    // Separador de campos en el .txt (por eso no se permite dentro de los datos)
    public static final String SEPARADOR = ";";

    private final String nombre;
    private final String apellido;
    private final String telefono;
    private final String email;

    public Contacto(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    // true si el texto aparece en alguno de los 4 campos (sin importar mayúsculas)
    public boolean coincide(String texto) {
        String t = texto.toLowerCase();
        return nombre.toLowerCase().contains(t)
                || apellido.toLowerCase().contains(t)
                || telefono.toLowerCase().contains(t)
                || email.toLowerCase().contains(t);
    }

    // Convierte el objeto en una línea del .txt: Nombre;Apellido;Teléfono;Email
    public String aLineaTxt() {
        return String.join(SEPARADOR, nombre, apellido, telefono, email);
    }

    // Operación inversa: arma el objeto a partir de una línea del .txt
    public static Contacto desdeLineaTxt(String linea) {
        String[] campos = linea.split(SEPARADOR, -1);
        if (campos.length != 4) {
            return null; // línea mal formada: se ignora
        }
        return new Contacto(campos[0], campos[1], campos[2], campos[3]);
    }
}
