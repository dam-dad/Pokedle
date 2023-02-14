package teampoke.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

	public class Type__1 {

		@SerializedName("name")
		@Expose
		private String name;
		@SerializedName("url")
		@Expose
		private String url;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

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
