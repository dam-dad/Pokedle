package teampoke.model;

public class Pokemon {
	
	private String nombrePokemon;
	private String tipoPrimPokemon;
	private String tipoSecPokemon;
	private String pesoPokemon;
	private String alturaPokemon;
	private String numPokemon;
	private String evoPokemon;
	private String preevoPokemon;
	private String formaDeEvoPokemon;
	
	public Pokemon() {
		// TODO Auto-generated constructor stub
	}
	
	public Pokemon(String name, String typePrim, String typeSec, String weight, 
			String height, String numPokedex, String evoTo, String evoFrom,
			String formEvoTo) {
		nombrePokemon = name;
		tipoPrimPokemon = typePrim;
		tipoSecPokemon = typeSec;
		pesoPokemon = weight;
		alturaPokemon = height;
		numPokemon = numPokedex;
		evoPokemon = evoTo;
		preevoPokemon = evoFrom;
		formaDeEvoPokemon = formEvoTo;
		
	}
	
	public String getNombrePokemon() {
		return nombrePokemon;
	}
	public void setNombrePokemon(String nombrePokemon) {
		this.nombrePokemon = nombrePokemon;
	}
	public String getTipoPrimPokemon() {
		return tipoPrimPokemon;
	}
	public void setTipoPrimPokemon(String tipoPrimPokemon) {
		this.tipoPrimPokemon = tipoPrimPokemon;
	}
	public String getTipoSecPokemon() {
		return tipoSecPokemon;
	}
	public void setTipoSecPokemon(String tipoSecPokemon) {
		this.tipoSecPokemon = tipoSecPokemon;
	}
	public String getPesoPokemon() {
		return pesoPokemon;
	}
	public void setPesoPokemon(String pesoPokemon) {
		this.pesoPokemon = pesoPokemon;
	}
	public String getAlturaPokemon() {
		return alturaPokemon;
	}
	public void setAlturaPokemon(String alturaPokemon) {
		this.alturaPokemon = alturaPokemon;
	}
	public String getNumPokemon() {
		return numPokemon;
	}
	public void setNumPokemon(String numPokemon) {
		this.numPokemon = numPokemon;
	}
	public String getEvoPokemon() {
		return evoPokemon;
	}
	public void setEvoPokemon(String evoPokemon) {
		this.evoPokemon = evoPokemon;
	}
	public String getPreevoPokemon() {
		return preevoPokemon;
	}
	public void setPreevoPokemon(String preevoPokemon) {
		this.preevoPokemon = preevoPokemon;
	}
	public String getFormaDeEvoPokemon() {
		return formaDeEvoPokemon;
	}
	public void setFormaDeEvoPokemon(String formaDeEvoPokemon) {
		this.formaDeEvoPokemon = formaDeEvoPokemon;
	}

	@Override
	public String toString() {
		return "Pokemon [nombrePokemon=" + nombrePokemon + ", tipoPrimPokemon=" + tipoPrimPokemon + ", tipoSecPokemon="
				+ tipoSecPokemon + ", pesoPokemon=" + pesoPokemon + ", alturaPokemon=" + alturaPokemon + ", numPokemon="
				+ numPokemon + ", evoPokemon=" + evoPokemon + ", preevoPokemon=" + preevoPokemon + "]";
	}
	
	
}
