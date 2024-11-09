package org.sam.alurahotel.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Disponibilidad extends JFrame {

    private static final int FILAS = 3;
    private static final int COLUMNAS = 5;
    private static Connection connection;

    public Disponibilidad() {
        // Configurar la ventana
        setTitle("Disponibilidad");
        setSize(750, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiar a DISPOSE_ON_CLOSE
        setLocationRelativeTo(null);

        // Conectar a la base de datos
        conectarBaseDatos();

        // Crear el panel principal con un GridLayout (3 filas, 5 columnas)
        JPanel panelHabitaciones = new JPanel(new GridLayout(FILAS, COLUMNAS));

        // Crear los botones que representan las habitaciones
        JButton[][] habitaciones = new JButton[FILAS][COLUMNAS];

        // Cargar el estado inicial de las habitaciones desde la base de datos
        cargarEstadoHabitaciones(habitaciones, panelHabitaciones);

        // Crear un botón para regresar a la vista de reservas
        JButton btnRegresar = new JButton("Regresar");

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la vista actual (Disponibilidad)
                dispose();
                // Crear una nueva instancia de ReservasView y mostrarla
                ReservasView reservasView = new ReservasView();
                reservasView.setVisible(true);
            }
        });


        // Crear un panel para agregar el botón de regresar
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRegresar);

        // Crear el layout principal
        setLayout(new BorderLayout());
        add(panelHabitaciones, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Hacer visible la ventana
        setVisible(true);

        // Al cerrar la ventana, cerrar la conexión a la base de datos
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cerrarConexion();
            }
        });
    }

    // Método para conectar a la base de datos
    private static void conectarBaseDatos() {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Conexión a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_alura", "root", "debora2020");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para cargar el estado inicial de las habitaciones desde la base de datos
    private static void cargarEstadoHabitaciones(JButton[][] botones, JPanel panel) {
        try {
            String query = "SELECT id, tipo, estado FROM habitaciones";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int i = 0, j = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String tipoHabitacion = rs.getString("tipo");
                boolean estado = rs.getBoolean("estado");

                // Generar el número de la habitación
                String numeroHabitacion = (id <= 5) ? "10" + id : (id <= 10) ? "20" + (id - 5) : "30" + (id - 10);
                String textoBoton = tipoHabitacion + "-" + numeroHabitacion + (estado ? " Ocupado" : " Disponible");

                botones[i][j] = new JButton(textoBoton);
                botones[i][j].setBackground(estado ? Color.RED : Color.WHITE);  // Color según estado
                botones[i][j].setForeground(Color.BLACK); // Color del texto
                panel.add(botones[i][j]);

                // Agregar ActionListener para actualizar el estado en la base de datos
                botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton boton = (JButton) e.getSource();
                        boolean nuevoEstado = boton.getBackground() == Color.WHITE;  // Si está blanco, significa que está disponible
                        actualizarEstadoHabitacion(id, nuevoEstado);
                        boton.setBackground(nuevoEstado ? Color.RED : Color.WHITE);
                        boton.setText(tipoHabitacion + "-" + numeroHabitacion + (nuevoEstado ? " Ocupado" : " Disponible"));
                        boton.setForeground(Color.BLACK); // Asegúrate de que el texto sea negro
                    }
                });

                j++;
                if (j == COLUMNAS) {
                    j = 0;
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar el estado de una habitación en la base de datos
    private static void actualizarEstadoHabitacion(int id, boolean estado) {
        try {
            String query = "UPDATE habitaciones SET estado = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, estado);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar la conexión a la base de datos
    private static void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
