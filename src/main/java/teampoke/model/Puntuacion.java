package teampoke.model;

public class Puntuacion {

	private static int puntuacion;
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		Puntuacion.puntuacion = puntuacion;
	}

	public static void sumarPuntos(int intentos) {
		if (intentos < 5)
			puntuacion += 10;
		else if (intentos >= 5 && intentos < 10)
			puntuacion += 5;
		else
			puntuacion++;
	}
	
	
	
}
