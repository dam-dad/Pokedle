package teampoke.pokeapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

	@SerializedName("slot")
	@Expose
	private Integer slot;
	@SerializedName("type")
	@Expose
	private Type__1 type;

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public Type__1 getType() {
		return type;
	}

	public void setType(Type__1 type) {
		this.type = type;
	}
}
