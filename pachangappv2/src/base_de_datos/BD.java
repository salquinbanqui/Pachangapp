package base_de_datos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.TriviaVentana;
import dominio.Usuario;
import dominioConHerencia.Carta;
import dominioConHerencia.Jugador;
import dominioConHerencia.Portero;
import dominioConHerencia.Portero;

public class BD {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private final static String CSV_USUARIOS = "data/usuarios.csv";
	
	private static Properties properties;
	private static String driverName;
	private static String databaseFile;
	private static String connectionString;
	
	private static Logger logger = Logger.getLogger(BD.class.getName());
	public BD() {
		try (FileInputStream fis = new FileInputStream("conf/logger.properties")) {
			//Inicialización del Logger
			//LogManager.getLogManager().readConfiguration(fis);
			
			//Lectura del fichero properties
			properties = new Properties();
			properties.load(new FileReader(PROPERTIES_FILE));
			
			driverName = properties.getProperty("driver");
			databaseFile = properties.getProperty("file");
			connectionString = properties.getProperty("connection");
			
			//Cargar el diver SQLite
			Class.forName(driverName);
		} catch (Exception ex) {
			logger.warning(String.format("Error al cargar el driver de BBDD: %s", ex.getMessage()));
		}
	}
/////////////////////////////////////////////////////////
//PARTE DE BD DE INICIO DE SESION Y REGISTRAR (USUARIOS)

	
	 //Inicializa la BBDD leyendo los datos de los ficheros CSV 
	
	public static void initilizeFromCSV() {
		//Sólo se inicializa la BBDD si la propiedad initBBDD es true.
		if (properties.get("loadCSV").equals("true")) {
			//Se borran los datos, si existía alguno
			//this.borrarDatos();
			borrarDatos();
			
			//Se leen los personajes del CSV
			List<Usuario> usuarios = loadCSVUsuarios();
			//this.loadCSVUsuarios();
			//Se insertan los personajes en la BBDD
			insertarUsuario(usuarios.toArray(new Usuario[usuarios.size()]));				
			//this.insertarUsuario
		}
	}
	
	private static List<Usuario> loadCSVUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(CSV_USUARIOS))) {
			String linea = null;
			
			//Omitir la cabecera
			in.readLine();			
			
			while ((linea = in.readLine()) != null) {
				usuarios.add(Usuario.parseCSV(linea));
			}			
			
		} catch (Exception ex) {
			logger.warning(String.format("Error leyendo personajes del CSV: %s", ex.getMessage()));
		}
		
		return usuarios;
	}
	public static void crearBBDD() {
		//Sólo se crea la BBDD si la propiedad initBBDD es true.
		if (properties.get("createBBDD").equals("true")) {
			String sql1 = "CREATE TABLE IF NOT EXISTS Personaje (\n"
	                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                + " nick TEXT NOT NULL,\n"
	                + " password TEXT NOT NULL,\n"
	                + " nombre TEXT NOT NULL\n"
	                + " apellidos TEXT NOT NULL,\n"
	                + " telefono TEXT NOT NULL,\n"
	                + " fechaUltimoLogin TEXT NOT NULL\n"
	                + " listaEmails TEXT NOT NULL,\n"
	                + ");";
	
	        //Se abre la conexión y se crea un PreparedStatement para cada tabla
			//Al abrir la conexión, si no existía el fichero, se crea la base de datos
			try (Connection con = DriverManager.getConnection(connectionString);
			     PreparedStatement pStmt1 = con.prepareStatement(sql1)) {
				
				//Se ejecutan las sentencias de creación de las tablas
		        if (!pStmt1.execute()) {
		        	logger.info("Se ha creado las tablas");
		        }
			} catch (Exception ex) {
				logger.warning(String.format("Error al crear las tablas: %s", ex.getMessage()));
			}
		}
	}
	
	public static void borrarDatos() {
		//Sólo se borran los datos si la propiedad cleanBBDD es true
		if (properties.get("cleanBBDD").equals("true")) {	
			String sql1 = "DELETE FROM Usuario;";

			
	        //Se abre la conexión y se crea un PreparedStatement para cada tabla
			//Al abrir la conexión, si no existía el fichero, se crea la base de datos
			try (Connection con = DriverManager.getConnection(connectionString);
			     PreparedStatement pStmt1 = con.prepareStatement(sql1)) {
				
				//Se ejecutan las sentencias de borrado de las tablas
		        if (!pStmt1.execute()) {
		        	logger.info("Se han borrado los datos");
		        }
			} catch (Exception ex) {
				logger.warning(String.format("Error al borrar los datos: %s", ex.getMessage()));
			}
		}
	}
	
	public static void insertarUsuario(Usuario... usuarios) {
		//Se define la plantilla de la sentencia SQL
		String sql = "INSERT INTO Usuario (nick, password, nombre, apellidos, telefono, fechaUltimoLogin, listaEmails) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
			 PreparedStatement pStmt = con.prepareStatement(sql)) {
									
			//Se recorren los clientes y se insertan uno a uno
			for (Usuario p : usuarios) {
				//Se definen los parámetros de la sentencia SQL
				pStmt.setString(1, p.getNick());
				pStmt.setString(2, p.getPassword());
				pStmt.setString(1, p.getNombre());
				pStmt.setString(2, p.getApellidos());
				pStmt.setString(1, p.getTelefono());
				pStmt.setString(2, p.getFechaUltimoLogin());
				pStmt.setString(1, p.getListaEmails());
				
				if (pStmt.executeUpdate() != 1) {					
					logger.warning(String.format("No se ha insertado el Personaje: %s", p));
				} else {
					//Se actualiza el ID del personaje haciendo un Select					
					p.setId(getUsuarioByNombre(p.getNombre()).getId());	
					//this.getUsuarioByNombre
					logger.info(String.format("Se ha insertado el Personaje: %s", p));
				}
			}
			
			logger.info(String.format("%d Personajes insertados en la BBDD", usuarios.length));
		} catch (Exception ex) {
			logger.warning(String.format("Error al insertar personajes: %s", ex.getMessage()));
		}			
	}
	
	
	public static Usuario getUsuarioByNombre(String nombre) {
		Usuario usuario = null;
		String sql = "SELECT * FROM Usuario WHERE nombre = ? LIMIT 1";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			//Se definen los parámetros de la sentencia SQL
			pStmt.setString(1, nombre);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = pStmt.executeQuery();			

			//Se procesa el único resultado
			if (rs.next()) {
				usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setNick(rs.getString("nick"));
				usuario.setPassword(rs.getString("password"));	
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setFechaUltimoLogin(rs.getString("fechaUltimoLogin"));
				usuario.setListaEmails(rs.getString("listaEmails"));
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info(String.format("Se ha recuperado el personaje %s", usuario));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar el personaje con nombre %s: %s", nombre, ex.getMessage()));						
		}		
		
		return usuario;
	}
	
	public static boolean existeUsuario(Usuario usr) {
		String sql = "SELECT nick, password, nombre, apellidos, telefono, fechaUltimoLogin, listaEmails FROM usuarios WHERE usuario = ?" ;
		boolean resultado = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
				PreparedStatement pStmt = con.prepareStatement(sql)){
			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getNick());
			rs = ps.executeQuery();
			if (rs.next()) 
			{
				if(usr.getPassword().equals(rs.getString(2))) {
					return resultado = true;
				} else {
					return resultado = false;
				}
					
			}
			
		} catch (Exception e) {
			System.out.println("Algo esta mal");
			return resultado = false;
			
		}
		return resultado;
	
	}
	
	
	
	public static void main(String[] args) throws ClassNotFoundException {
// rec
		// Carga el sqlite-JDBC driver usando el cargador de clases
		Class.forName("...");
		//Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			// Crear una conexión de BD
			connection = DriverManager.getConnection("...");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg

			statement.executeUpdate("drop table if exists person");
			statement.executeUpdate("create table person (id integer, name string)");
			
			
			
			//statement.executeUpdate("create table person (nick string, password string, nombre string, apellidos string, telefono string, fechaUltimoLogin string)");
			////creo que así estaría mejor
			
			
			
			int res = statement.executeUpdate("insert into person values(1, 'leo')");
			System.out.println( res );
			res = statement.executeUpdate("insert into person values(2, 'yui')");
			System.out.println( res );
			ResultSet rs = statement.executeQuery("select * from person");
			while(rs.next()) {
				// Leer el resultset
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if(connection != null)
					connection.close();
			} catch(SQLException e) {
				// Cierre de conexión fallido
				System.err.println(e);
			}
		}
	}
	
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	////PARTE DE LEER INFO DE USUARIO
	
	//no se porque pero toda la parte de usuario (estos 3 metodos) estaban comentados, los acabo de descomentar	
	
	public static void crearTablaUsuario(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Usuario (nick String, pass String, nombre String, apellido String, tlf String, fechaUltimoLogin String)";
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
					} catch (SQLException e) {
			 //TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public static void insertarUsuario(Connection con, String nick, String pass, String nombre, String apellido, String telefono, String fechaUltimoLogIn) {
			String sql = "INSERT INTO Usuario VALUES('"+nick+"','"+pass+"','"+nombre+"','"+apellido+"',"+telefono+",'"+fechaUltimoLogIn+"')";
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
						// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public static Usuario obtenerDatosUsuario(Connection con, String nick) {
			String sql = "SELECT * FROM Usuario WHERE nick='"+nick+"'";
			Usuario u = null;
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()) {
					String n = rs.getString("nick");
					String pass = rs.getString("pass");
					String nom = rs.getString("nombre");
					String a = rs.getString("apellido");
					String tlf = rs.getString("telefono");
					String fuli = rs.getString("fechaUltimoLogIn");
					u = new Usuario(n, pass, nom, a, tlf, fuli);
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return u;
		}
	
	
	//PARTE DE CARTAS
	
	public static void crearTablaCartas(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Carta (nombre String, puntos String, coste String, rutaFoto String)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertarCarta(Connection con, String nombre, String puntos, String coste, String rutaFoto) {
		String sql = "INSERT INTO Carta VALUES('"+nombre+"','"+puntos+"','"+coste+"','"+rutaFoto+"')";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Carta obtenerDatosCarta(Connection con, String nombre) {
		String sql = "SELECT * FROM Carta WHERE nombre='"+nombre+"'";
		Carta c = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				String nom = rs.getString("nombre");
				int puntos = Integer.parseInt(rs.getString("puntos"));
				int coste = Integer.parseInt(rs.getString("coste"));
				String rutaF = rs.getString("rutaFoto");
				boolean esPortero = rs.getBoolean("esPortero");
				
				if ( esPortero == false ) { 
					c = new Jugador(nom, puntos, coste, rutaF);
				}else {
					c = new Portero(nom, puntos, coste, rutaF);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	//PARA METER EN VENTANACARTA
	
	public static Set<Jugador> cargarJugadores(Connection con){
		String sql = "SELECT * FROM Carta";
		HashSet<Jugador> jugadorSet = new HashSet<>();
		Jugador c = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				if (rs.getString(5) == "FALSE") {
					
				}
				String nombre = rs.getString("nombre");
				Integer puntos = Integer.parseInt(rs.getString("puntos"));
				Integer coste = Integer.parseInt(rs.getString("coste"));
				String rutaFoto = rs.getString("rutaFoto");
				String esPortero = rs.getString("esPortero");
				
				if ( esPortero.equals("FALSE") ) { 
					c = new Jugador(nombre, puntos, coste, rutaFoto);
				}
				
				jugadorSet.add(c);
			}
			rs.close();
			st.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jugadorSet;
		
	}	
	
	public static Set<Portero> cargarPorteros(Connection con){
		String sql = "SELECT * FROM Carta";
		HashSet<Portero> porteroSet = new HashSet<>();
		Portero c = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				Integer puntos = Integer.parseInt(rs.getString("puntos"));
				Integer coste = Integer.parseInt(rs.getString("coste"));
				String rutaFoto = rs.getString("rutaFoto");
				String esPortero = rs.getString("esPortero");
								
				if ( esPortero.equals("TRUE") ) { 
					c = new Portero(nombre, puntos, coste, rutaFoto);
				}
				porteroSet.add(c);
			}
			rs.close();
			st.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return porteroSet;
		
	}
	
	
	public static Set<Carta> cargarCartas(Connection con){
		String sql = "SELECT * FROM Carta";
		HashSet<Carta> cartaSet = new HashSet<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Carta c = null;
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				Integer puntos = Integer.parseInt(rs.getString("puntos"));
				Integer coste = Integer.parseInt(rs.getString("coste"));
				String rutaFoto = rs.getString("rutaFoto");
				
				boolean esPortero = rs.getBoolean("esPortero");
				
				if ( esPortero == false ) { 
					c = new Jugador(nombre, puntos, coste, rutaFoto);
				}else {
					c = new Portero(nombre, puntos, coste, rutaFoto);
				}
				cartaSet.add(c);
			}
			rs.close();
			st.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cartaSet;
		
	}
	
	//PARA METER Y SACAR ALINEACION
	
	
	public static List<Carta> cargarAlineacion(Connection con){
		String sql = "SELECT * FROM Alineacion";
		List<Carta> listaCarta = new ArrayList<>();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Carta c = null;
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				Integer puntos = Integer.parseInt(rs.getString("puntos"));
				Integer coste = Integer.parseInt(rs.getString("coste"));
				String rutaFoto = rs.getString("rutaFoto");
				
				boolean esPortero = rs.getBoolean("esPortero");
				
				if ( esPortero == false ) { 
					c = new Jugador(nombre, puntos, coste, rutaFoto);
				}else {
					c = new Portero(nombre, puntos, coste, rutaFoto);
				}
				
				listaCarta.add(c);
			}
			rs.close();
			st.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		return listaCarta;
		
	}

	
	
	
	//PARTE DE LEER INFO DE JUGADOR
	
	/*
	public static void crearTablaJugador(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Jugador (nombre String, puntos String, coste String, rutaFoto String)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertarJugador(Connection con, String nombre, String puntos, String coste, String rutaFoto) {
		String sql = "INSERT INTO Usuario VALUES('"+nombre+"','"+puntos+"','"+coste+"','"+rutaFoto+"')";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Jugador obtenerDatosJugador(Connection con, String nombre) {
		String sql = "SELECT * FROM Jugador WHERE nombre='"+nombre+"'";
		Jugador j = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				
					
				String n = rs.getString("nombre");
				int p = Integer.parseInt(rs.getString("puntos"));
				int c = Integer.parseInt(rs.getString("coste"));
				String r = rs.getString("rutaFoto");
			j = new Jugador(n, p, c, r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	
	
	
	//PARTE DE LEER INFO DE PORTERO
	
	
	public static void crearTablaPortero(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Portero (nombre String, puntos String, coste String, rutaFoto String)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertarPortero(Connection con, String nombre, String puntos, String coste, String rutaFoto) {
		String sql = "INSERT INTO Carta VALUES('"+nombre+"','"+puntos+"','"+coste+"','"+rutaFoto+"',TRUE)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Portero obtenerDatosPortero(Connection con, String nombre) {
		String sql = "SELECT * FROM Jugador WHERE nombre='"+nombre+"'";
		Portero p = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				
					
				String n = rs.getString("nombre");
				int pts = Integer.parseInt(rs.getString("puntos"));
				int c = Integer.parseInt(rs.getString("coste"));
				String r = rs.getString("rutaFoto");
			p = new Portero(n, pts, c, r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	*/

	public static void cargarCartasInicial(Connection con) {

		String sql = "INSERT INTO Carta values ('Lewan', 99, 16, 'imagenes//lewan.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Ronaldo', 98, 14, 'imagenes//ronaldo.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Neuer', 97, 12, 'imagenes//Neuer.gif', 'TRUE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Mbappe', 97, 12, 'imagenes//mbappe.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Casillas', 96, 10, 'imagenes//casillas.gif', 'TRUE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Messi', 96, 10, 'imagenes//messi.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Ramos', 96, 8, 'imagenes//ramos.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Chiellini', 95, 8, 'imagenes//chiellini.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Modric', 92, 6, 'imagenes//modric.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Neymar', 92, 6, 'imagenes//neymar.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Pique', 92, 1, 'imagenes//pique.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Salah', 92, 1, 'imagenes//salah.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Buffon', 89, 1, 'imagenes//buffon.gif', 'TRUE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Koke', 85, 1, 'imagenes//koke.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Carta values ('Haland', 82, 1, 'imagenes//haland.gif', 'FALSE');";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

	
	
	
	//devolver lista cartas de inventario (cartas)
	
	//NO FUNCIONA, SON PRUEBAS PARA GUARDAR LAS CARTAS EN BASE DE DATOS.
	/*
	public static Portero guardarCartas(Connection con) {
		
		
		 for (int i = 0; i < panelCartaJugador.getRowCount(); i++) {
			 System.out.println("Registro número: "+i);
			 System.out.println("ID: "+TablaDatos.getValueAt(i, 0));
			 System.out.println("NOMBRE: "+TablaDatos.getValueAt(i, 1));
			 System.out.println("TELÉFONO: "+TablaDatos.getValueAt(i, 2));
			 }
		
		panelCartaJugador
		
		
		JPanel panelTiendaJugador = new JPanel(new GridLayout(0, 2));
		for(int i=0;i<50;i++) {
			JLabel lblTiendaJugador = new JLabel();
			//lblTiendaJugador.setSize(102, 164);
			lblTiendaJugador.setSize(120, 193);
			ImageIcon imTienda = new ImageIcon("imagenes/lewan.gif");
			ImageIcon imcdTienda= new ImageIcon(imTienda.getImage().getScaledInstance(lblTiendaJugador.getWidth(), lblTiendaJugador.getHeight(), Image.SCALE_DEFAULT));
			lblTiendaJugador.setIcon(imcdTienda);
			JPanel p = new JPanel(new BorderLayout());
			JPanel pw = new JPanel();
			JLabel l = new JLabel("          ");
			pw.add(l);
			p.add(pw, BorderLayout.WEST);
			p.add(lblTiendaJugador,BorderLayout.CENTER);
			panelTiendaJugador.add(p);
		}
		JScrollPane panelTiendaScroll = new JScrollPane(panelTiendaJugador);
		panelTiendaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelGeneral.add(panelTiendaScroll);
		
		
		
		
		
		
		
		
		String sql = "SELECT * FROM Jugador WHERE nombre='"+nombre+"'";
		Portero p = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				
					
				String n = rs.getString("nombre");
				int pts = Integer.parseInt(rs.getString("puntos"));
				int c = Integer.parseInt(rs.getString("coste"));
				String r = rs.getString("rutaFoto");
			p = new Portero(n, pts, c, r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}*/

}



