package dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dominioConHerencia.Jugador;

public class JugadorTest {
	
		private Jugador jug;
		private String nombre = "Mbappe";
		private int puntos = 321;
		private int coste = 123;
		private String rutaFoto = "";
		
	@Before
	public void setUp() throws Exception {
		
		jug = new Jugador(nombre, puntos, coste, rutaFoto);
	}

	@Test
	public void testGetNombre() {
		
		assertEquals(jug.getNombre(), "Mbappe");
	}

	@Test
	public void testSetNombre() {
		jug.setNombre("Benzema");
		assertEquals(jug.getNombre(), "Benzema");
	}

	@Test
	public void testGetPuntos() {
		assertEquals(jug.getPuntos(), 321);
	}

	@Test
	public void testSetPuntos() {
		jug.setPuntos(678);
		assertEquals(jug.getPuntos(), 678);
	}

	@Test
	public void testGetCoste() {
		assertEquals(jug.getCoste(), 123);
	}

	@Test
	public void testSetCoste() {
		jug.setCoste(999);
		assertEquals(jug.getCoste(), 999);
	}

}