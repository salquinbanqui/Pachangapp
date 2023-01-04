package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base_de_datos.BD;
import dominio.Usuario;
import login_registro.RegistrarVentana;

public class LogInVentana {

	private JFrame frame;
	private JTextField textFieldNombreUsu;
	private JPasswordField textFieldPass;
	private BD triviaDB;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInVentana window = new LogInVentana();
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
	public LogInVentana() {
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
		HashMap<String,String> logininfo = new HashMap<String,String>();
				
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelNombreUsu = new JLabel("Nombre de Usuario:");
		labelNombreUsu.setBounds(75, 40, 120, 30);
		frame.getContentPane().add(labelNombreUsu);
		
		JLabel labelPass = new JLabel("Contrasenya:");
		labelPass.setBounds(75, 110, 120, 30);
		frame.getContentPane().add(labelPass);
		
		textFieldNombreUsu = new JTextField();
		textFieldNombreUsu.setBounds(210, 40, 130, 30);
		frame.getContentPane().add(textFieldNombreUsu);
		textFieldNombreUsu.setColumns(10);
		
		textFieldPass = new JPasswordField();
		textFieldPass.setName("");
		textFieldPass.setBounds(210, 110, 130, 30);
		frame.getContentPane().add(textFieldPass);
		textFieldPass.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				RegistrarVentana.main(null);
				
			}
		});
		btnRegistrar.setBounds(66, 195, 130, 30);
		frame.getContentPane().add(btnRegistrar);
		
		JButton btnIniciarSes = new JButton("Iniciar sesion");
		btnIniciarSes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String erContrasenia = "[a-zA-Z0-9?¿!¡]{4,15}";	//expresion regular para la contraseña, entre 4 y 15 letras (mayusculas o minusculas) o numeros o ?¿!¡
				//Ejemplos de contraseña válida: 000!!¿00, 0¿a0a0, A1??b3fq2T, abHc¿djp, po1s¡duebcisd5...
				String erNombreUsu = "[a-zA-Z]";				//expresion regular para el nombre de usuario
				String nombreUsu = labelNombreUsu.getText();
				String contrasenia = labelPass.getText();
				
				if(Pattern.matches(erNombreUsu, nombreUsu) && Pattern.matches(erContrasenia, contrasenia)) {
					//JOptionPane.showMessageDialog(null, "Bienvenido!");
					//Comprobamos que el usuario esté registrado
					//Da error, lo arreglo luego
					Usuario u = BD.obtenerDatosUsuario(con, nombreUsu);
					if(u!=null) {
						if(u.getPassword().equals(contrasenia)) {
							JOptionPane.showMessageDialog(null, "Bienvenido", "SESIÓN INICIADA", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
							TriviaVentana.main(null);

						}else {
							JOptionPane.showMessageDialog(null, "La contraseña debe tener mas de 4 pero menos de 15 caracteres", "ERROR", JOptionPane.ERROR_MESSAGE);
						}}else {
							JOptionPane.showMessageDialog(null, "El nombre de usuario solo deberia contener letras", "ERROR", JOptionPane.ERROR_MESSAGE);
						}}else {
							JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos", "ERROR", JOptionPane.ERROR_MESSAGE);
						}

				//labelNombreUsu.setText("");
				//labelPass.setText("");

			}
		});
		btnIniciarSes.setBounds(238, 195, 130, 30);
		frame.getContentPane().add(btnIniciarSes);
		
		JLabel lblExplicaNombreUsu = new JLabel("Solo usar letras");
		lblExplicaNombreUsu.setBounds(210, 25, 150, 14);
		frame.getContentPane().add(lblExplicaNombreUsu);
		
		JLabel lblExplicaContra = new JLabel("Mínimo 4 caractéres");
		lblExplicaContra.setBounds(210, 95, 150, 14);
		frame.getContentPane().add(lblExplicaContra);
	}
}
