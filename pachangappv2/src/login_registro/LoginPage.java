
package login_registro;
import java.awt.*;

import java.awt.event.*;

import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;

import GUI.TriviaVentana;
import base_de_datos.BD;
import dominio.Usuario;
import quiz.Quiz;



public class LoginPage implements ActionListener{

	

	JFrame frame = new JFrame();

	JButton loginButton = new JButton("Login");

	JButton resetButton = new JButton("Reset");

	JTextField userIDField = new JTextField();

	JPasswordField userPasswordField = new JPasswordField();

	JLabel userIDLabel = new JLabel("userID:");

	JLabel userPasswordLabel = new JLabel("password:");

	JLabel messageLabel = new JLabel();

	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	JLabel lblExplicaNombreUsu = new JLabel("Solo usar letras");
	//lblExplicaNombreUsu.setBounds(210, 25, 150, 14);
	//frame.getContentPane().add(lblExplicaNombreUsu);
	JLabel lblExplicaContra = new JLabel("Mínimo 4 caractéres");


	LoginPage(HashMap<String,String> loginInfoOriginal){

		logininfo = loginInfoOriginal;

		userIDLabel.setBounds(50,100,75,25);

		userPasswordLabel.setBounds(50,150,75,25);

		lblExplicaNombreUsu.setBounds(120,70,100,25);
		frame.getContentPane().add(lblExplicaNombreUsu);
		
		lblExplicaContra.setBounds(120,125,150,25);
		frame.getContentPane().add(lblExplicaContra);

		messageLabel.setBounds(125,250,250,35);

		messageLabel.setFont(new Font(null,Font.ITALIC,25));

		

		userIDField.setBounds(125,100,200,25);

		userPasswordField.setBounds(125,150,200,25);

		

		loginButton.setBounds(125,200,100,25);

		loginButton.setFocusable(false);

		loginButton.addActionListener(this);

		

		resetButton.setBounds(225,200,100,25);

		resetButton.setFocusable(false);

		resetButton.addActionListener(this);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				RegistrarVentana.main(null);
				
			}
		});
		btnRegistrar.setBounds(125, 230, 200, 25);
		frame.getContentPane().add(btnRegistrar);
		
		

		frame.add(userIDLabel);

		frame.add(userPasswordLabel);

		frame.add(messageLabel);

		frame.add(userIDField);

		frame.add(userPasswordField);

		frame.add(loginButton);

		frame.add(resetButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(420,420);

		frame.setLayout(null);

		frame.setVisible(true);

		

	}



	@Override

	public void actionPerformed(ActionEvent e) {
		//Se crea la BBDD
		BD.crearBBDD();
		//Se cargan los datos y se inicializa la BBDD
		BD.initilizeFromCSV();
		
		String erContrasenia = "[a-zA-Z0-9?¿!¡]{4,15}";	//expresion regular para la contraseña, entre 4 y 15 letras (mayusculas o minusculas) o numeros o ?¿!¡
		//Ejemplos de contraseña válida: 000!!¿00, 0¿a0a0, A1??b3fq2T, abHc¿djp, po1s¡duebcisd5...
		String erNombreUsu = "[a-zA-Z]";				//expresion regular para el nombre de usuario
		String nombreUsu = userIDField.getText();
		String contrasenia = userPasswordField.getText();
		
		if(Pattern.matches(erNombreUsu, nombreUsu) && Pattern.matches(erContrasenia, contrasenia)) {
			//JOptionPane.showMessageDialog(null, "Bienvenido!");
			//Comprobamos que el usuario esté registrado
			//Da error, lo arreglo luego
			//Usuario u = BD.obtenerDatosUsuario(con, nombreUsu);
			//if(u!=null) {
			//	if(u.getPassword().equals(contrasenia)) {
			//		JOptionPane.showMessageDialog(null, "Bienvenido", "SESIÓN INICIADA", JOptionPane.INFORMATION_MESSAGE);
			//		frame.dispose();
			//		TriviaVentana.main(null);

			//	}else {
			//		JOptionPane.showMessageDialog(null, "La contraseña debe tener mas de 4 pero menos de 15 caracteres", "ERROR", JOptionPane.ERROR_MESSAGE);
			//	}}else {
			//		JOptionPane.showMessageDialog(null, "El nombre de usuario solo deberia contener letras", "ERROR", JOptionPane.ERROR_MESSAGE);
			//	}}else {
			//		JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

		//labelNombreUsu.setText("");
		//labelPass.setText("");

		if(e.getSource()==resetButton) {

			userIDField.setText("");

			userPasswordField.setText("");

		}

		

		if(e.getSource()==loginButton) {

			String userID = userIDField.getText();

			String password = String.valueOf(userPasswordField.getPassword());

			

			if(logininfo.containsKey(userID)) {

				if(logininfo.get(userID).equals(password)) {

					messageLabel.setForeground(Color.green);

					messageLabel.setText("Login successful");

					frame.dispose();
					//Cambiar a la ventana del juego 
					//WelcomePage welcomePage = new WelcomePage(userID);
					Quiz quiz = new Quiz();	

				}

				else {

					messageLabel.setForeground(Color.red);

					messageLabel.setText("Wrong password");

				}



			}

			else {

				messageLabel.setForeground(Color.red);

				messageLabel.setText("username not found");

			}

		}

	}	

}