package teampoke.pokeapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonSpecies {

	@SerializedName("evolution_chain")
	@Expose
	private Object evolTo;
	
	@SerializedName("evolves_from_species")
	@Expose
	private Object evolFrom;

	public Object getEvolTo() {
		return evolTo;
	}

	public void setEvolTo(Object evolTo) {
		this.evolTo = evolTo;
	}

	public Object getEvolFrom() {
		return evolFrom;
	}

	public void setEvolFrom(Object evolFrom) {
		this.evolFrom = evolFrom;
	}
	
	
	
}
