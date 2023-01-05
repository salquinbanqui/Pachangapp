package base_de_datos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

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
	
	
	public static void crearTablaUsuario(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Usuario (nick String, pass String, nombre String, apellido String, tlf String, fechaUltimoLogin String)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	
	
	//PARTE DE LEER INFO DE JUGADOR
	

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
	
	public static Portero obtenerDatosPortero(Connection con, String nombre) {
		String sql = "SELECT * FROM Portero WHERE nombre='"+nombre+"'";
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
	
	//cuidao
	public static void cargarCartas(Connection con) {

		String sql = "INSERT INTO Jugador values ('Messi', 96, 30, 'imagenes/messi.gif');";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Portero values ('Neuer', 97, 10, 'imagenes/Neuer.gif');";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Portero values Lewan = new Jugador('a', 99, 15, 'imagenes/lewan.gif');";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Jugador values ('Neymar', 92, 25, 'imagenes/neymar.gif');";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Jugador values ('Gavi', 86, 17, 'imagenes/gavi.png');";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public Set<Jugador> sacarJugadores(Connection con){
		String sql = "SELECT * FROM Jugador";
		HashSet<Jugador> jugadorSet = new HashSet<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				Integer puntos = Integer.parseInt(rs.getString("puntos"));
				Integer coste = Integer.parseInt(rs.getString("coste"));
				String rutaFoto = rs.getString("rutaFoto");
				
				Jugador c = new Jugador(nombre, puntos, coste, rutaFoto);
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
	
	
	public Set<Portero> sacarPorteros(Connection con){
		String sql = "SELECT * FROM Jugador";
		HashSet<Portero> porteroSet = new HashSet<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				Integer puntos = Integer.parseInt(rs.getString("puntos"));
				Integer coste = Integer.parseInt(rs.getString("coste"));
				String rutaFoto = rs.getString("rutaFoto");
				
				Portero c = new Portero(nombre, puntos, coste, rutaFoto);
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
