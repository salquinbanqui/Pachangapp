package dominio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ficheros.Preguntas;

public class PreguntasTest {
	
	private Preguntas preg;
	private int id = 1;
	private String pregunta = "Cuantos mundiales tiene Italia?";
	private String respuestaA = "1";
	private String respuestaB = "3";
	private String respuestaC = "4";
	private String respuestaD = "Ninguno";
	private String respuestaCorrecta = "A";

	@Before
	public void setUp() throws Exception {
		preg = new Preguntas(id, pregunta, respuestaA, respuestaB, respuestaC, respuestaD, respuestaCorrecta);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetId() {
		Preguntas preguntas = new Preguntas(1, "Cuantos mundiales tiene Italia?", "1", "3", "4", "Ninguno", "A");
		assertEquals(preg.getId(), preguntas.getId());
	}

	@Test
	public void testGetPregunta() {
		Preguntas preguntas = new Preguntas(1, "Cuantos mundiales tiene Italia?", "1", "3", "4", "Ninguno", "A");
		assertEquals(preg.getPregunta(), preguntas.getPregunta());
	}

	@Test
	public void testGetRespuestaA() {
		Preguntas preguntas = new Preguntas(1, "Cuantos mundiales tiene Italia?", "1", "3", "4", "Ninguno", "A");
		assertEquals(preg.getRespuestaA(), preguntas.getRespuestaA());
	}

	@Test
	public void testGetRespuestaB() {
		Preguntas preguntas = new Preguntas(1, "Cuantos mundiales tiene Italia?", "1", "3", "4", "Ninguno", "A");
		assertEquals(preg.getRespuestaB(), preguntas.getRespuestaB());
	}

	@Test
	public void testGetRespuestaC() {
		Preguntas preguntas = new Preguntas(1, "Cuantos mundiales tiene Italia?", "1", "3", "4", "Ninguno", "A");
		assertEquals(preg.getRespuestaC(), preguntas.getRespuestaC());
	}

	@Test
	public void testGetRespuestaD() {
		Preguntas preguntas = new Preguntas(1, "Cuantos mundiales tiene Italia?", "1", "3", "4", "Ninguno", "A");
		assertEquals(preg.getRespuestaD(), preguntas.getRespuestaD());
	}

	@Test
	public void testGetRespuestaCorrecta() {
		Preguntas preguntas = new Preguntas(1, "Cuantos mundiales tiene Italia?", "1", "3", "4", "Ninguno", "A");
		assertEquals(preg.getRespuestaCorrecta(), preguntas.getRespuestaCorrecta());
	}

	@Test
	public void testToString() {
		Preguntas preguntas = new Preguntas(1, "Cuantos mundiales tiene Italia?", "1", "3", "4", "Ninguno", "A");
		assertEquals(preg.toString(), preguntas.toString());
	}

}
