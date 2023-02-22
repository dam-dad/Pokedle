package teampoke.pokeapi.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chain {

//	@SerializedName("evolution_details")
//	@Expose
//	private List<Object> evolutionDetails;

	@SerializedName("evolves_to")
	@Expose
	private List<EvolvesTo> evolvesTo;

	public List<EvolvesTo> getEvolvesTo() {
		return evolvesTo;
	}

	public void setEvolvesTo(List<EvolvesTo> evolvesTo) {
		this.evolvesTo = evolvesTo;
	}

}