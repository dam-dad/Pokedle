
package teampoke.pokeapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @SerializedName("base_stat")
    @Expose
    private Integer baseStat;

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }

}
