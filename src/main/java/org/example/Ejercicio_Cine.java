package org.example;

import javax.swing.*;
import java.awt.*;

public class Ejercicio_Cine extends JFrame {

    // Clase auxiliar interna para modelar los datos de la reserva
    static class Cliente {
        String nombre;
        String telefono;

        public Cliente(String nombre, String telefono) {
            this.nombre = nombre;
            this.telefono = telefono;
        }
    }

    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;

    // Matriz de estructura de datos: null = libre, objeto Cliente = ocupado
    private final Cliente[][] sala = new Cliente[FILAS][COLUMNAS];
    private final JButton[][] botones = new JButton[FILAS][COLUMNAS];

    // Asiento seleccionado actualmente por el operador
    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;

    // Elementos de la interfaz gráfica
    private JLabel lblEstado;
    private JButton btnReservar;
    private JButton btnEliminar;
    private JButton btnMover;

    // Colores del plano
    private final Color COLOR_LIBRE = new Color(46, 204, 113);       // Verde
    private final Color COLOR_OCUPADO = new Color(231, 76, 60);       // Rojo
    private final Color COLOR_SELECCIONADO = new Color(241, 196, 15); // Amarillo

    // Constructor: coincide exactamente con el nombre de la clase
    public Ejercicio_Cine() {
        setTitle("Sistema de Gestión de Cine - 100 Asientos");
        setSize(850, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // --- 1. ENCABEZADO: Pantalla del Cine ---
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblPantalla = new JLabel("PANTALLA DEL CINE", SwingConstants.CENTER);
        lblPantalla.setFont(new Font("Arial", Font.BOLD, 18));
        lblPantalla.setOpaque(true);
        lblPantalla.setBackground(new Color(52, 73, 94));
        lblPantalla.setForeground(Color.WHITE);
        lblPantalla.setPreferredSize(new Dimension(800, 40));
        panelSuperior.add(lblPantalla, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);

        // --- 2. PLANO INTERACTIVO: Grilla 10x10 de Asientos ---
        JPanel panelSala = new JPanel(new GridLayout(FILAS, COLUMNAS, 5, 5));
        panelSala.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                JButton btn = new JButton("F" + (i + 1) + "-A" + (j + 1));
                btn.setFont(new Font("Arial", Font.PLAIN, 10));
                btn.setFocusPainted(false);
                btn.setBackground(COLOR_LIBRE);
                btn.setOpaque(true);
                btn.setBorderPainted(false);

                final int f = i;
                final int c = j;
                btn.addActionListener(e -> seleccionarAsiento(f, c));

                botones[i][j] = btn;
                panelSala.add(btn);
            }
        }
        add(panelSala, BorderLayout.CENTER);

        // --- 3. PANEL INFERIOR: Acciones ---
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        lblEstado = new JLabel("Haga clic en un asiento de la sala para comenzar.", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 13));
        panelInferior.add(lblEstado, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        btnReservar = new JButton("Dar de Alta Reserva");
        btnReservar.setEnabled(false);
        btnReservar.addActionListener(e -> reservarAsiento());

        btnEliminar = new JButton("Eliminar Reserva");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(e -> eliminarReserva());

        btnMover = new JButton("Mover a Otro Asiento");
        btnMover.setEnabled(false);
        btnMover.addActionListener(e -> moverReserva());

        panelBotones.add(btnReservar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMover);

        panelInferior.add(panelBotones, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void seleccionarAsiento(int f, int c) {
        restaurarColor(filaSeleccionada, colSeleccionada);

        filaSeleccionada = f;
        colSeleccionada = c;
        botones[f][c].setBackground(COLOR_SELECCIONADO);

        Cliente cli = sala[f][c];
        if (cli == null) {
            lblEstado.setText("Asiento [Fila " + (f + 1) + ", Butaca " + (c + 1) + "] DISPONIBLE.");
            btnReservar.setEnabled(true);
            btnEliminar.setEnabled(false);
            btnMover.setEnabled(false);
        } else {
            lblEstado.setText("OCUPADO por: " + cli.nombre + " (Tel: " + cli.telefono + ")");
            JOptionPane.showMessageDialog(this,
                    "Este asiento ya está reservado por:\n" +
                            "• Nombre: " + cli.nombre + "\n" +
                            "• Teléfono: " + cli.telefono,
                    "Asiento Ocupado",
                    JOptionPane.WARNING_MESSAGE);

            btnReservar.setEnabled(false);
            btnEliminar.setEnabled(true);
            btnMover.setEnabled(true);
        }
    }

    private void reservarAsiento() {
        if (filaSeleccionada == -1) return;

        JTextField txtNombre = new JTextField();
        JTextField txtTel = new JTextField();
        Object[] campos = {
                "Nombre del cliente:", txtNombre,
                "Teléfono de contacto:", txtTel
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Registrar Reserva", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            String tel = txtTel.getText().trim();

            if (nombre.isEmpty() || tel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar nombre y teléfono.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            sala[filaSeleccionada][colSeleccionada] = new Cliente(nombre, tel);
            actualizarCuadro(filaSeleccionada, colSeleccionada);
            lblEstado.setText("Reserva guardada con éxito.");
            deseleccionar();
        }
    }

    private void eliminarReserva() {
        if (filaSeleccionada == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desea dar de baja la reserva del cliente " + sala[filaSeleccionada][colSeleccionada].nombre + "?",
                "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            sala[filaSeleccionada][colSeleccionada] = null;
            actualizarCuadro(filaSeleccionada, colSeleccionada);
            lblEstado.setText("Reserva eliminada. El asiento quedó libre.");
            deseleccionar();
        }
    }

    private void moverReserva() {
        if (filaSeleccionada == -1) return;

        String inputFila = JOptionPane.showInputDialog(this, "Mover cliente a -> Fila destino (1 a 10):");
        if (inputFila == null) return;
        String inputCol = JOptionPane.showInputDialog(this, "Mover cliente a -> Butaca destino (1 a 10):");
        if (inputCol == null) return;

        try {
            int nf = Integer.parseInt(inputFila) - 1;
            int nc = Integer.parseInt(inputCol) - 1;

            if (nf < 0 || nf >= FILAS || nc < 0 || nc >= COLUMNAS) {
                JOptionPane.showMessageDialog(this, "Coordenadas fuera de rango (deben ser 1 a 10).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (sala[nf][nc] != null) {
                JOptionPane.showMessageDialog(this, "El asiento de destino está ocupado por: " + sala[nf][nc].nombre, "Conflicto", JOptionPane.WARNING_MESSAGE);
                return;
            }

            sala[nf][nc] = sala[filaSeleccionada][colSeleccionada];
            sala[filaSeleccionada][colSeleccionada] = null;

            actualizarCuadro(filaSeleccionada, colSeleccionada);
            actualizarCuadro(nf, nc);

            JOptionPane.showMessageDialog(this, "Cliente reubicado con éxito.", "Traslado Exitoso", JOptionPane.INFORMATION_MESSAGE);
            deseleccionar();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Debe ingresar números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void restaurarColor(int f, int c) {
        if (f >= 0 && f < FILAS && c >= 0 && c < COLUMNAS) {
            botones[f][c].setBackground(sala[f][c] == null ? COLOR_LIBRE : COLOR_OCUPADO);
        }
    }

    private void actualizarCuadro(int f, int c) {
        botones[f][c].setBackground(sala[f][c] == null ? COLOR_LIBRE : COLOR_OCUPADO);
    }

    private void deseleccionar() {
        filaSeleccionada = -1;
        colSeleccionada = -1;
        btnReservar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnMover.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ejercicio_Cine().setVisible(true);
        });
    }
}