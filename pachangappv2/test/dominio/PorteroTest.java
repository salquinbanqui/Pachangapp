package dominio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dominioConHerencia.Portero;

public class PorteroTest {

	private Portero por;               
	private String nombre = "Casillas";
	private int puntos = 321;          
	private int coste = 123;           
	
	@Before
	public void setUp() throws Exception {
		por = new Portero(nombre, puntos, coste);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNombre() {
		assertEquals(por.getNombre(), "Casillas");
	}

	@Test
	public void testSetNombre() {
		por.setNombre("Neuer");
		assertEquals(por.getNombre(), "Neuer");
	}

	@Test
	public void testGetPuntos() {
		assertEquals(por.getPuntos(), 321);
	}

	@Test
	public void testSetPuntos() {
		por.setPuntos(678);
		assertEquals(por.getPuntos(), 678);
	}

	@Test
	public void testGetCoste() {
		assertEquals(por.getCoste(), 123);
	}

	@Test
	public void testSetCoste() {
		por.setCoste(999);
		assertEquals(por.getCoste(), 999);
	}

}
