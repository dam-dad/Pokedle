package teampoke.pokeapi.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPokemon {
	
	
	@SerializedName("count")
	private int numPokemons;
	@SerializedName("results")
	@Expose
	private List<Species> pokemons;

	public List<Species> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<Species> pokemons) {
		this.pokemons = pokemons;
	}

	public int getNumPokemons() {
		return numPokemons;
	}

	public void setNumPokemons(int numPokemons) {
		this.numPokemons = numPokemons;
	}
	
	

}
