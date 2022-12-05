package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import base_de_datos.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;

public class RegistrarVentana {

	private JFrame frame;
	private JTextField textNick;
	private JTextField textContra;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;
	private BD triviaDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarVentana window = new RegistrarVentana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrarVentana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Nos conectamos con la base de datos
		Connection con = BD.initBD("DBTrivia.db");
		//Creamos las tablas
		//BD.crearTablas(con);
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textNick = new JTextField();
		textNick.setBounds(200, 41, 200, 20);
		frame.getContentPane().add(textNick);
		textNick.setColumns(10);
		
		textContra = new JTextField();
		textContra.setColumns(10);
		textContra.setBounds(200, 81, 200, 20);
		frame.getContentPane().add(textContra);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(200, 121, 200, 20);
		frame.getContentPane().add(textNombre);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(200, 161, 200, 20);
		frame.getContentPane().add(textApellido);
		
		JLabel lblNick = new JLabel("Nick");
		lblNick.setBounds(40, 41, 90, 20);
		frame.getContentPane().add(lblNick);
		
		JLabel lblContra = new JLabel("Contraseña");
		lblContra.setBounds(40, 81, 90, 20);
		frame.getContentPane().add(lblContra);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(40, 121, 90, 20);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(40, 161, 90, 20);
		frame.getContentPane().add(lblApellido);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(200, 201, 200, 20);
		frame.getContentPane().add(textTelefono);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(40, 201, 90, 20);
		frame.getContentPane().add(lblTelefono);
		
		JLabel lblNewLabel = new JLabel("Registrar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(150, 10, 100, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nick = lblNick.getText();
				String contra = lblContra.getText();
				String nombre = lblNombre.getText();
				String apellido = lblApellido.getText();
				String telefono = lblTelefono.getText();
				Date fecha = new Date();
				String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
				
				//HABRIA QUE COMPROBAR QUE LOS DATOS SON CORRECTOS (QUE EL TELEFONO SON NUMEROS POR EJEMPLO)
				
				BD.insertarUsuario(con, nick, contra, nombre, apellido, telefono, fechaActual);
				
				//HABRIA QUE COMPROBAR QUE EL USUARIO SE HA CREADO CORRECTAMENTE
				
				JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
				
				frame.dispose();
				LogInVentana.main(null);
				
				
			}
		});
		btnRegistrar.setBounds(260, 230, 120, 25);
		frame.getContentPane().add(btnRegistrar);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LogInVentana.main(null);
				
			}
		});
		btnIniciarSesion.setBounds(65, 230, 120, 25);
		frame.getContentPane().add(btnIniciarSesion);
	}
}
