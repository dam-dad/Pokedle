package teampoke.pokeapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvolutionChain {

	@SerializedName("chain")
	@Expose
	private Chain chain;

	public Chain getChain() {
		return chain;
	}

	public void setChain(Chain chain) {
		this.chain = chain;
	}
}
