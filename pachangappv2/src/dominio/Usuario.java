package dominio;

import java.beans.Statement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nick;
	private String password;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String fechaUltimoLogin; //Formato: yyyy-MM-dd
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String listaEmails;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFechaUltimoLogin() {
		return fechaUltimoLogin;
	}
	public void setFechaUltimoLogin(String string) {
		this.fechaUltimoLogin = string;
	}

	public String getListaEmails() {
		return listaEmails;
	}
	public void setListaEmails(String listaEmails) {
		this.listaEmails = listaEmails;
	}
	//Constructor 
	

	public Usuario(String n, String pass, String nom, String a, String tlf, String fuli) {
		// TODO Auto-generated constructor stub
		this.nick = n;
		this.password = pass;
		this.nombre = nom;
		this.apellidos = a;
		this.telefono = tlf;
		//Convertir de String a Date
		//try {
			//fechaUltimoLogin = sdf.parse(fuli);
		//} catch (ParseException e) {
			//Si no se puede convertir porque el String es incorrecto
			//ponle a la fecha de nacimiento la fecha actual, lo cual es un outlier de manual y se nota
			//fechaUltimoLogin = new Date(System.currentTimeMillis());
		//}
	}
		public Usuario(String nick, String password, String nombre, String apellidos, String telefono,
			String fechaUltimoLogin, String listaEmails) {
		super();
		this.nick = nick;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.fechaUltimoLogin = fechaUltimoLogin;
		this.listaEmails = listaEmails;
	}
		public Usuario() {
		// TODO Auto-generated constructor stub
	}
		// Dos usuarios son iguales si TODOS sus campos son iguales
		public boolean equals( Object o ) {
			Usuario u2 = null;
			if (o instanceof Usuario) u2 = (Usuario) o;
			else return false;  // Si no es de la clase, son diferentes
			return (nick.equals(u2.nick))
				&& (password.equals(u2.password))
				&& (nombre.equals(u2.nombre))
				&& (apellidos.equals(u2.apellidos))
				&& (telefono == u2.telefono)
				&& (fechaUltimoLogin == u2.fechaUltimoLogin)
				&& (listaEmails.equals( u2.listaEmails ));
		}
		
		@Override
		public String toString() {
			return "Usuario [nick=" + getNick() + ", password=" + getPassword() + ", nombre=" + getNombre() + ", apellidos="
					+ getApellidos() + ", telefono=" + getTelefono() + "]";
		}
		
		//Crea un Personaje a partir de una cadena de texto separada por comas.
		public static Usuario parseCSV(String csvString) {
			if (csvString != null && !csvString.isBlank()) {		
				StringTokenizer tokenizer = new StringTokenizer(csvString, ";");
				
				Usuario usuario = new Usuario();
				
				usuario.setNick(tokenizer.nextToken());
				usuario.setPassword(tokenizer.nextToken());
				usuario.setNombre(tokenizer.nextToken());
				usuario.setApellidos(tokenizer.nextToken());
				usuario.setTelefono(tokenizer.nextToken());
				usuario.setFechaUltimoLogin(tokenizer.nextToken());
				usuario.setListaEmails(tokenizer.nextToken());
				
				return usuario;
			} else {
				return null;
			}
		}
		
		// Inicializa una BD SQLITE y devuelve una conexión con ella
		public static Connection initBD( String nombreBD ) {
			try {
			    Class.forName("...");
			    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			    return con;
			} catch (ClassNotFoundException | SQLException e) {
				return null;
			}
		}
		
		// Cierra la base de datos abierta
		public static void cerrarBD( Connection con, Statement st ) {
			try {
				if (st!=null) ((Connection) st).close();
				if (con!=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
