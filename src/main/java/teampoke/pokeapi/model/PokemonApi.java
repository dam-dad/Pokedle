package teampoke.pokeapi.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class PokemonApi {

	// Tipo primario (slot 1) y secundario (slot 2)
	@SerializedName("types")
	@Expose
	private List<Type> types = null;

	// Peso
	@SerializedName("weight")
	@Expose
	private Integer weight;

	// Altura
	@SerializedName("height")
	@Expose
	private Integer height;

	// Nº Pokedex
	@SerializedName("id")
	@Expose
	private Integer numPokedex;

	// EvolucionaTo
	private String evolTo;
	
	// EvolucionaFrom
	private String evolFrom;

	//Imagenes
    @SerializedName("sprites")
    @Expose
    private Sprites sprites;
    
    //Species
    @SerializedName("species")
    private Species species;
	
	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getNumPokedex() {
		return numPokedex;
	}

	public void setNumPokedex(Integer numPokedex) {
		this.numPokedex = numPokedex;
	}

	public String getEvolTo() {
		return evolTo;
	}

	public void setEvolTo(String evolTo) {
		this.evolTo = evolTo;
	}

	public String getEvolFrom() {
		return evolFrom;
	}

	public void setEvolFrom(String evolFrom) {
		this.evolFrom = evolFrom;
	}	
	
	public Sprites getSprites() {
		return sprites;
	}

	public void setSprites(Sprites sprites) {
		this.sprites = sprites;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}
}
