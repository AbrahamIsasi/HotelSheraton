package org.sam.alurahotel.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JTextField;
import com.itextpdf.text.xml.XmlToTxt;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.*;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import org.sam.alurahotel.controller.ReservaController;
import org.sam.alurahotel.modelo.Reserva;
import org.sam.alurahotel.view.Disponibilidad;





@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtTipoHabitacion;
	public static JTextField txtNumeroHabitacion;
	public static JComboBox<String> txtFormaPago;
	public static JComboBox<String> txtEstadoReserva;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Long diferenciaDias;
	private String tipoHabitacion;
	private Long valor;
	private String numeroHabitacion;
	private String estadoReserva;


	private ReservaController reservaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					ReservasView frame = new ReservasView();
					frame.setVisible(true);


				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReservasView() {
		super("Reserva");

		this.reservaController = new ReservaController();

		// apariencia JOprionPanel					
		UIManager.put("Button.background", new Color(12, 138, 199));
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.foreground", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.foreground", Color.white);

		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);


		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		// Código que crea los elementos de la interfáz gráfica

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBounds(68, 218, 289, 2);
		separator_1_2.setBackground(new Color(12, 137, 199));
		panel.add(separator_1_2);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(new Color(12, 138, 199));
		separator_1_3.setBackground(new Color(12, 138, 199));
		separator_1_3.setBounds(68, 460, 289, 2);//453
		panel.add(separator_1_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(12, 138, 199));
		separator_1_1.setBounds(68, 138, 289, 11);//Y281
		separator_1_1.setBackground(new Color(12, 138, 199));
		panel.add(separator_1_1);

		JSeparator separator_1_4 = new JSeparator();
		separator_1_4.setForeground(new Color(12, 138, 199));
		separator_1_4.setBounds(68, 300, 289, 11);//Y281
		separator_1_4.setBackground(new Color(12, 138, 199));
		panel.add(separator_1_4);

		JLabel lblCheckIn = new JLabel("FECHA DE INGRESO");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 80, 175, 14);//136
		lblCheckIn.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("FECHA DE SALIDA");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 155, 187, 14);//221
		lblCheckOut.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		JLabel tipoHabitacion = new JLabel("TIPO DE HABITACION");
		tipoHabitacion.setForeground(SystemColor.textInactiveText);
		tipoHabitacion.setBounds(68, 230, 200, 24);
		tipoHabitacion.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		panel.add(tipoHabitacion);

		JLabel numeroHabitacion = new JLabel("N° HAB.");
		numeroHabitacion.setForeground(SystemColor.textInactiveText);
		numeroHabitacion.setBounds(280, 230, 120, 24);
		numeroHabitacion.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		panel.add(numeroHabitacion);

		JLabel lblFormaPago = new JLabel("TIPO DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 130, 24);
		lblFormaPago.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblEstadoReserva = new JLabel("ESTADO DE PAGO");
		lblEstadoReserva.setForeground(SystemColor.textInactiveText);
		lblEstadoReserva.setBounds(220, 382, 130, 24);
		lblEstadoReserva.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		panel.add(lblEstadoReserva);


		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(100, 20, 262, 42);//109x
		lblTitulo.setForeground(new Color(10, 91, 132));
		lblTitulo.setFont(new Font("Ubuntu", Font.PLAIN, 23));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		/*
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));
		*/
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 30, 500, 490);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 314, 205, 14);//196
		lblValor.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		panel.add(lblValor);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(12, 138, 199));
		separator_1.setBounds(68, 362, 289, 2);//362
		separator_1.setBackground(new Color(12, 138, 199));
		panel.add(separator_1);

		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Ubuntu", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Ubuntu", Font.PLAIN, 23));

		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);

		JLabel lblDisponible= new JLabel("VER DISPONIBILIDAD");
		lblDisponible.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisponible.setForeground(Color.WHITE);
		lblDisponible.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblDisponible.setBounds(0, 0, 140, 35);


		//Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(new Color(10, 91, 132));
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Ubuntu", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 100, 289, 35);//161
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("dd-MM-yyyy");
		txtFechaEntrada.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		panel.add(txtFechaEntrada);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Ubuntu", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 178, 289, 35);//Y246
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Ubuntu", Font.PLAIN, 16));

		txtFechaSalida.setDateFormatString("dd-MM-yyyy");
		txtFechaSalida.getCalendarButton().setBackground(new Color(10, 91, 132));
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(50, 328, 100, 33);//y328
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);

		// Inicialización del JComboBox con tipos de habitación
		txtTipoHabitacion = new JComboBox();
		txtTipoHabitacion.setBounds(68, 258, 180, 38);//289
		txtTipoHabitacion.setBackground(SystemColor.text);
		txtTipoHabitacion.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtTipoHabitacion.setFont(new Font("Ubuntu", Font.PLAIN, 16));

// Añadimos las opciones del combo box
		txtTipoHabitacion.setModel(new DefaultComboBoxModel(new String[]{"Habitación Simple", "Habitación Doble", "Habitación Matrimonial"}));

// Añadimos el JComboBox al panel
		panel.add(txtTipoHabitacion);

// Añadir ActionListener para que al seleccionar una opción se calcule el valor
		txtTipoHabitacion.addActionListener(e -> {
			// Verificamos que las fechas ya estén seleccionadas antes de calcular el valor
			if (txtFechaEntrada.getDate() != null && txtFechaSalida.getDate() != null) {
				calcularValor();  // Llamamos al método para calcular el valor de la reserva
			}
		});

		// Crear el JTextField para que el usuario ingrese el número de habitación
		txtNumeroHabitacion = new JTextField();
		txtNumeroHabitacion.setBackground(SystemColor.text);
		txtNumeroHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumeroHabitacion.setForeground(Color.BLACK);
		txtNumeroHabitacion.setBounds(270, 258, 80, 33);
		txtNumeroHabitacion.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtNumeroHabitacion.setBorder(BorderFactory.createEmptyBorder());
		txtNumeroHabitacion.setEditable(true);
		panel.add(txtNumeroHabitacion);
		txtNumeroHabitacion.setColumns(10);


		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 417, 145, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(new String[]{"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo","-----------------------"}));
		panel.add(txtFormaPago);

		// Crear el JComboBox
		txtEstadoReserva = new JComboBox<>();
		txtEstadoReserva.setBounds(230, 417, 120, 38);
		txtEstadoReserva.setBackground(SystemColor.text);
		txtEstadoReserva.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtEstadoReserva.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtEstadoReserva.setModel(new DefaultComboBoxModel<>(new String[]{"Pendiente", "Cancelado"}));
		panel.add(txtEstadoReserva);





		JPanel btnsiguiente = new JPanel();
		JPanel btnDisponibilidad = new JPanel();


		JLabel labelGuardar = new JLabel("SIGUIENTE");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 130, 35);
		btnsiguiente.add(labelGuardar);

		JLabel labelDispon = new JLabel("VER DISPONIBILIDAD");
		labelDispon.setHorizontalAlignment(SwingConstants.CENTER);
		labelDispon.setForeground(Color.WHITE);
		labelDispon.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		labelDispon.setBounds(0, 0, 135, 35);
		btnDisponibilidad.add(labelDispon);




		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {
					guardarReserva();
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnsiguiente.setBackground(new Color(10, 91, 132));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnsiguiente.setBackground(new Color(12, 138, 199));
			}

		});

		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(new Color(12, 138, 199));
		btnsiguiente.setBounds(60, 493, 130, 35);//110x
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		btnDisponibilidad.setLayout(null);
		btnDisponibilidad.setBackground(new Color(12, 138, 199));
		btnDisponibilidad.setBounds(205, 493, 145, 35);//110x
		panel.add(btnDisponibilidad);
		btnDisponibilidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));


		btnDisponibilidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDisponibilidad.setBackground(new Color(10, 91, 132));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDisponibilidad.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Lógica para redireccionar a la vista Disponibilidad
				new Disponibilidad(); // Abrir la vista Disponibilidad
				((JFrame) SwingUtilities.getWindowAncestor(btnDisponibilidad)).dispose(); // Cerrar la vista actual
			}



		});


	}

	protected void calcularValor() {
		// Obtén las fechas seleccionadas
		fechaEntrada = convertToLocalDateViaInstant(txtFechaEntrada.getDate());
		fechaSalida = convertToLocalDateViaInstant(txtFechaSalida.getDate());

		// Calcula la diferencia en días
		diferenciaDias = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);

		// Verifica que la fecha de entrada sea anterior o igual a la de salida
		if (fechaEntrada.compareTo(fechaSalida) <= 0) {

			// Obtener el tipo de habitación seleccionado
			String tipoHabitacion = (String) txtTipoHabitacion.getSelectedItem();  // Obtenemos la habitación seleccionada

			// Asignar el valor por tipo de habitación
			long tarifa = 0;
			switch (tipoHabitacion) {
				case "Habitación Simple":
					tarifa = 50;
					break;
				case "Habitación Doble":
					tarifa = 100;
					break;
				case "Habitación Matrimonial":
					tarifa = 200;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Tipo de habitación no válido.");
					return;  // Salimos del método si no hay un tipo válido
			}

			// Calcular el valor total
			valor = diferenciaDias * tarifa;

			// Mostrar el valor calculado en el campo de texto
			txtValor.setText(" S/. " + valor);
		} else {
			// Mostrar mensaje si las fechas no son válidas
			JOptionPane.showMessageDialog(null, "La fecha de salida debe ser mayor que la fecha de registro...");
		}
	}


	// Método guardar Reserva
	private void guardarReserva() {

		Reserva nuevaReserva = new Reserva(fechaEntrada.toString(), fechaSalida.toString(), valor, (String) txtTipoHabitacion.getSelectedItem(),(String) txtNumeroHabitacion.getText(), (String) txtFormaPago.getSelectedItem(), (String) txtEstadoReserva.getSelectedItem());
		this.reservaController.guardar(nuevaReserva);

		dispose();

		JOptionPane.showMessageDialog(this, "Registrado con éxito! \nNúmero de reserva: " + nuevaReserva.getId());

		RegistroHuesped huesped = new RegistroHuesped(nuevaReserva.getId());
		huesped.setVisible(true);

	}


	// Método para convertir Date a LocalDate, 
	// Referencia: https://www.baeldung.com/java-date-to-localdate-and-localdatetime
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}





}





