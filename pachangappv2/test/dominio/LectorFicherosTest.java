package dominio;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ficheros.LectorFicheros;
import ficheros.Preguntas;

public class LectorFicherosTest {
	
	ArrayList<Preguntas> arPreguntas = new ArrayList<Preguntas>();
	String fichero = "data\\preguntas.csv";
	
	LectorFicheros f;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLeerCSV() {
		
		Preguntas p = new Preguntas(1,"Cuando tuvo lugar la primera Copa Mundial de Futbol?"
				,"A- En 1920","B- En 1930","C- En 1940","D- En 1950","B");
		
		try {
			f.leerCSV(arPreguntas, fichero);
			
			assertEquals(arPreguntas.size(), 12);
			assertEquals(arPreguntas.get(0).getPregunta(), p.getPregunta());
			assertEquals(arPreguntas.get(0).getRespuestaA(), p.getRespuestaA());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
