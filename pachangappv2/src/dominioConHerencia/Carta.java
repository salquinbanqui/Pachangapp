package dominioConHerencia;

public abstract class Carta {
	
	String nombre; //será como el id de la carta, ya que no se repetira
	int puntos; //puntos de valor de cada jugador, utilizado en el apartado de alineación
	int coste; //coste de cada jugador
	String rutaFoto; //ruta para ubicar la foto de la carta
	
	public Carta(String nombre, int puntos, int coste, String rutaFoto) {
		super();
		this.nombre = nombre;
		this.puntos = puntos;
		this.coste = coste;
		this.rutaFoto = rutaFoto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
	
	


	

}
