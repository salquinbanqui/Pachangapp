package dominioConHerencia;

public class Portero extends Carta {

	public Portero(String nombre, int puntos, int coste, String rutaFoto) {
		super(nombre, puntos, coste, rutaFoto);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return nombre + ", " + puntos;
	}

	
}

