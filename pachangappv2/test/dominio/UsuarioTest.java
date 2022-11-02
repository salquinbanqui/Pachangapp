package dominio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	private Usuario usu;
	private String nick = "Mark";
	private String password = "12345";
	private String nombre = "Marcos";
	private String apellidos = "Lopez Gutierrez";
	private String telefono = "666894524";
	private String fecha = "2002-03-12";
	
	@Before
	public void setUp() throws Exception {
		usu = new Usuario(nick, password, nombre, apellidos, telefono, fecha);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUsuario() {
		Usuario persona = new Usuario("Mark", "12345", "Marcos", "Lopez Gutierrez", "666894524", "2002-03-12");
		assertEquals(persona.getNombre(), usu.getNombre());
		assertEquals(persona.getApellidos(), usu.getApellidos());
	}
	
	@Test
	public void testGetNick() {
		Usuario persona = new Usuario("Mark", "12345", "Marcos", "Lopez Gutierrez", "666894524", "2002-03-12");
		assertEquals(persona.getNick(), usu.getNick());
	}

	@Test
	public void testGetPassword() {
		Usuario persona = new Usuario("Mark", "12345", "Marcos", "Lopez Gutierrez", "666894524", "2002-03-12");
		assertEquals(persona.getPassword(), usu.getPassword());
	}

	@Test
	public void testGetNombre() {
		Usuario persona = new Usuario("Mark", "12345", "Marcos", "Lopez Gutierrez", "666894524", "2002-03-12");
		assertEquals(persona.getNombre(), usu.getNombre());
	}

	@Test
	public void testGetApellidos() {
		Usuario persona = new Usuario("Mark", "12345", "Marcos", "Lopez Gutierrez", "666894524", "2002-03-12");
		assertEquals(persona.getApellidos(), usu.getApellidos());
	}

	@Test
	public void testGetTelefono() {
		Usuario persona = new Usuario("Mark", "12345", "Marcos", "Lopez Gutierrez", "666894524", "2002-03-12");
		assertEquals(persona.getTelefono(), usu.getTelefono());
	}

	@Test
	public void testToString() {
		Usuario persona = new Usuario("Mark", "12345", "Marcos", "Lopez Gutierrez", "666894524", "2002-03-12");
		assertEquals(persona.toString(), usu.toString());
	}

}
