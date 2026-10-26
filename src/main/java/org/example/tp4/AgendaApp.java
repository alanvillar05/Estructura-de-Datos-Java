package org.example.tp4;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AgendaApp extends JFrame {

    // Paleta minimalista: blanco, grises y negro
    private static final Color FONDO = Color.WHITE;
    private static final Color GRIS_CLARO = new Color(245, 245, 245);
    private static final Color GRIS_BORDE = new Color(220, 220, 220);
    private static final Color GRIS_TEXTO = new Color(110, 110, 110);
    private static final Color NEGRO = new Color(30, 30, 30);
    private static final Font FUENTE = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font FUENTE_TITULO = new Font("SansSerif", Font.BOLD, 20);

    private final Agenda agenda;

    // Contactos que se ven en la tabla ahora (todos o los que coinciden con la búsqueda).
    // La fila i de la tabla es visibles.get(i): así se elimina el contacto correcto aunque haya filtro
    private final ArrayList<Contacto> visibles = new ArrayList<>();

    private final JTextField txtNombre = crearCampo();
    private final JTextField txtApellido = crearCampo();
    private final JTextField txtTelefono = crearCampo();
    private final JTextField txtEmail = crearCampo();
    private final JTextField txtBuscar = crearCampo();
    private final JLabel lblContador = new JLabel();

    private final DefaultTableModel modelo =
            new DefaultTableModel(new String[]{"Apellido", "Nombre", "Teléfono", "Email"}, 0) {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false; // la tabla solo muestra; se carga con el formulario
                }
            };
    private final JTable tabla = new JTable(modelo);

    public AgendaApp() {
        agenda = new Agenda(ubicarArchivo());

        setTitle("Agenda de Contactos");
        setSize(820, 560);
        setMinimumSize(new Dimension(640, 420));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel raiz = new JPanel(new BorderLayout(0, 14));
        raiz.setBackground(FONDO);
        raiz.setBorder(new EmptyBorder(20, 24, 20, 24));
        raiz.add(crearEncabezado(), BorderLayout.NORTH);
        raiz.add(crearCentro(), BorderLayout.CENTER);
        raiz.add(crearBotones(), BorderLayout.SOUTH);
        setContentPane(raiz);

        try {
            agenda.cargar();
        } catch (IOException e) {
            mostrarError("No se pudo leer " + agenda.getArchivo().getName() + ": " + e.getMessage());
        }
        actualizarTabla();
    }

    // ---------- Construcción de la interfaz ----------

    private JPanel crearEncabezado() {
        JLabel titulo = new JLabel("Agenda");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(NEGRO);

        JPanel formulario = new JPanel(new GridLayout(2, 4, 10, 4));
        formulario.setBackground(FONDO);
        for (String etiqueta : new String[]{"Nombre", "Apellido", "Teléfono", "Email"}) {
            formulario.add(crearEtiqueta(etiqueta));
        }
        formulario.add(txtNombre);
        formulario.add(txtApellido);
        formulario.add(txtTelefono);
        formulario.add(txtEmail);

        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(FONDO);
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(formulario, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearCentro() {
        // Buscador: filtra la tabla mientras se escribe
        JPanel buscador = new JPanel(new BorderLayout(10, 0));
        buscador.setBackground(FONDO);
        buscador.add(crearEtiqueta("Buscar"), BorderLayout.WEST);
        buscador.add(txtBuscar, BorderLayout.CENTER);
        lblContador.setFont(FUENTE);
        lblContador.setForeground(GRIS_TEXTO);
        buscador.add(lblContador, BorderLayout.EAST);

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarTabla(); }
            public void removeUpdate(DocumentEvent e) { actualizarTabla(); }
            public void changedUpdate(DocumentEvent e) { actualizarTabla(); }
        });

        estilizarTabla();
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(new MatteBorder(1, 1, 1, 1, GRIS_BORDE));
        scroll.getViewport().setBackground(FONDO);

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(FONDO);
        panel.add(buscador, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearBotones() {
        JButton btnAgregar = crearBoton("Agregar", true);
        JButton btnEliminar = crearBoton("Eliminar seleccionado", false);
        JButton btnGuardar = crearBoton("Guardar en .txt", false);

        btnAgregar.addActionListener(e -> agregarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
        btnGuardar.addActionListener(e -> guardarTxt());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panel.setBackground(FONDO);
        panel.add(btnEliminar);
        panel.add(btnGuardar);
        panel.add(btnAgregar);
        return panel;
    }

    private void estilizarTabla() {
        tabla.setFont(FUENTE);
        tabla.setForeground(NEGRO);
        tabla.setBackground(FONDO);
        tabla.setRowHeight(28);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(GRIS_BORDE);
        tabla.setSelectionBackground(new Color(225, 225, 225));
        tabla.setSelectionForeground(NEGRO);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setFillsViewportHeight(true);

        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setFont(FUENTE.deriveFont(Font.BOLD));
        encabezado.setBackground(GRIS_CLARO);
        encabezado.setForeground(NEGRO);
        encabezado.setReorderingAllowed(false);
    }

    private static JTextField crearCampo() {
        JTextField campo = new JTextField();
        campo.setFont(FUENTE);
        campo.setForeground(NEGRO);
        campo.setCaretColor(NEGRO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 1, 1, 1, GRIS_BORDE), new EmptyBorder(5, 8, 5, 8)));
        return campo;
    }

    private static JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(FUENTE);
        etiqueta.setForeground(GRIS_TEXTO);
        return etiqueta;
    }

    // Botón plano: el principal en negro, los secundarios en gris claro
    private static JButton crearBoton(String texto, boolean principal) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setBackground(principal ? NEGRO : GRIS_CLARO);
        boton.setForeground(principal ? Color.WHITE : NEGRO);
        boton.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 1, 1, 1, principal ? NEGRO : GRIS_BORDE), new EmptyBorder(8, 16, 8, 16)));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return boton;
    }

    // ---------- Acciones ----------

    private void agregarContacto() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            mostrarError("Nombre, apellido y teléfono son obligatorios.");
            return;
        }
        if ((nombre + apellido + telefono + email).contains(Contacto.SEPARADOR)) {
            mostrarError("Los datos no pueden contener ';' (se usa para separar campos en el .txt).");
            return;
        }
        if (!telefono.matches("[0-9 +()-]+")) {
            mostrarError("El teléfono solo puede tener números, espacios, +, ( ) o -.");
            return;
        }

        agenda.agregar(new Contacto(nombre, apellido, telefono, email));
        txtBuscar.setText(""); // se limpia el filtro para que se vea el contacto nuevo
        actualizarTabla();
        limpiarFormulario();
    }

    private void eliminarContacto() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            mostrarError("Seleccioná un contacto de la tabla.");
            return;
        }
        Contacto c = visibles.get(fila);
        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Eliminar a " + c.getNombre() + " " + c.getApellido() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            agenda.eliminar(c);
            actualizarTabla();
        }
    }

    // Abre la ventana "Guardar como" para que el usuario elija carpeta y nombre del .txt
    private void guardarTxt() {
        JFileChooser selector = new JFileChooser(agenda.getArchivo().getParentFile());
        selector.setDialogTitle("Guardar agenda como .txt");
        selector.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
        selector.setSelectedFile(agenda.getArchivo());

        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return; // el usuario canceló
        }

        File destino = selector.getSelectedFile();
        if (!destino.getName().toLowerCase().endsWith(".txt")) {
            destino = new File(destino.getParentFile(), destino.getName() + ".txt");
        }
        if (destino.exists() && !destino.equals(agenda.getArchivo())) {
            int reemplazar = JOptionPane.showConfirmDialog(this,
                    destino.getName() + " ya existe. ¿Querés reemplazarlo?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (reemplazar != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            agenda.guardarEn(destino);
            JOptionPane.showMessageDialog(this,
                    agenda.getContactos().size() + " contactos guardados en:\n" + destino.getAbsolutePath(),
                    "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            mostrarError("No se pudo guardar: " + e.getMessage());
        }
    }

    // La tabla se vuelve a dibujar a partir de la lista, aplicando el texto del buscador
    private void actualizarTabla() {
        String filtro = txtBuscar.getText().trim();
        visibles.clear();
        modelo.setRowCount(0);
        for (Contacto c : agenda.getContactos()) {
            if (filtro.isEmpty() || c.coincide(filtro)) {
                visibles.add(c);
                modelo.addRow(new Object[]{c.getApellido(), c.getNombre(), c.getTelefono(), c.getEmail()});
            }
        }
        lblContador.setText(visibles.size() + " de " + agenda.getContactos().size());
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtNombre.requestFocus();
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // agenda.txt va en la misma carpeta que el .jar, así el programa es portable
    private static File ubicarArchivo() {
        try {
            File origen = new File(AgendaApp.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File carpeta = origen.isFile() ? origen.getParentFile() : origen;
            return new File(carpeta, "agenda.txt");
        } catch (Exception e) {
            return new File(System.getProperty("user.home"), "agenda.txt");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaApp().setVisible(true));
    }
}
