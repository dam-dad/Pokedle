package teampoke.globalstats;

public class MarcadorPersonal {

	private String name;
	private int value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public MarcadorPersonal(String nombre, int valor) {
		name = nombre;
		value = valor;
	}
	
	@Override
	public String toString() {
		return "El usuario: " + name + " obtuvo la siguiente puntuación: " + value;
	}
	
}
