package teampoke.model;

public class Puntuacion {

	private int puntuacion;

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int sumarPuntos(int intentos) {
		if (intentos < 5)
			puntuacion += 10;
		else if (intentos >= 5 && intentos < 10)
			puntuacion += 5;
		else
			puntuacion++;
		return puntuacion;
	}
	
	@Override
	public String toString() {
		return String.valueOf(puntuacion);
	}

}
