package dominio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import dominioConHerencia.Jugador;

public class CartaTest {
	
	private Jugador car;
	private String nombre = "Mbappe";
	private int puntos = 321;
	private int coste = 123;
	private String rutaFoto = "imagenes/messi.gif";
	


	@Before
	public void setUp() throws Exception {
		car = new Jugador(nombre, puntos, coste, rutaFoto);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNombre() {
		
		assertEquals(car.getNombre(), "Mbappe");
	}

	@Test
	public void testSetNombre() {
		car.setNombre("Benzema");
		assertEquals(car.getNombre(), "Benzema");
	}

	@Test
	public void testGetPuntos() {
		assertEquals(car.getPuntos(), 321);
	}

	@Test
	public void testSetPuntos() {
		car.setPuntos(678);
		assertEquals(car.getPuntos(), 678);
	}

	@Test
	public void testGetCoste() {
		assertEquals(car.getCoste(), 123);
	}

	@Test
	public void testSetCoste() {
		car.setCoste(999);
		assertEquals(car.getCoste(), 999);
	}
	
	@Test
	public void testGetRutaFoto() {
		assertEquals(car.getRutaFoto(), "imagenes/messi.gif");
	}

	@Test
	public void testSetRutaFoto() {
		car.setRutaFoto("imagenes/lewan.gif");
	}

}
