package teampoke.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Pokemon {
	
	private ObjectProperty<Image> imagenPokemon = new SimpleObjectProperty<>();
	private StringProperty nombrePokemon = new SimpleStringProperty();
	private StringProperty tipoPrimPokemon = new SimpleStringProperty();
	private StringProperty tipoSecPokemon = new SimpleStringProperty();
	private FloatProperty pesoPokemon = new SimpleFloatProperty();
	private FloatProperty alturaPokemon = new SimpleFloatProperty();
	private IntegerProperty numPokemon = new SimpleIntegerProperty();
	private BooleanProperty evoPokemon = new SimpleBooleanProperty();
	private BooleanProperty preevoPokemon = new SimpleBooleanProperty();
	private IntegerProperty vidaBasePokemon = new SimpleIntegerProperty();
	

	public final StringProperty nombrePokemonProperty() {
		return this.nombrePokemon;
	}
		
	public Pokemon() {
		// TODO Auto-generated constructor stub
	}

	
	public final String getNombrePokemon() {
		return this.nombrePokemonProperty().get();
	}
	
	public final void setNombrePokemon(final String nombrePokemon) {
		this.nombrePokemonProperty().set(nombrePokemon);
	}
	
	public final StringProperty tipoPrimPokemonProperty() {
		return this.tipoPrimPokemon;
	}
	
	public final String getTipoPrimPokemon() {
		return this.tipoPrimPokemonProperty().get();
	}
	
	public final void setTipoPrimPokemon(final String tipoPrimPokemon) {
		this.tipoPrimPokemonProperty().set(tipoPrimPokemon);
	}
	
	public final StringProperty tipoSecPokemonProperty() {
		return this.tipoSecPokemon;
	}
	
	public final String getTipoSecPokemon() {
		return this.tipoSecPokemonProperty().get();
	}
	
	public final void setTipoSecPokemon(final String tipoSecPokemon) {
		this.tipoSecPokemonProperty().set(tipoSecPokemon);
	}
	
	public final FloatProperty pesoPokemonProperty() {
		return this.pesoPokemon;
	}
	
	public final float getPesoPokemon() {
		return this.pesoPokemonProperty().get();
	}
	
	public final void setPesoPokemon(final float pesoPokemon) {
		this.pesoPokemonProperty().set(pesoPokemon);
	}
	
	public final FloatProperty alturaPokemonProperty() {
		return this.alturaPokemon;
	}
	
	public final float getAlturaPokemon() {
		return this.alturaPokemonProperty().get();
	}
	
	public final void setAlturaPokemon(final float alturaPokemon) {
		this.alturaPokemonProperty().set(alturaPokemon);
	}
	
	public final IntegerProperty numPokemonProperty() {
		return this.numPokemon;
	}
	
	public final int getNumPokemon() {
		return this.numPokemonProperty().get();
	}
	
	public final void setNumPokemon(final int numPokemon) {
		this.numPokemonProperty().set(numPokemon);
	}

	@Override
	public String toString() {
		return "Pokemon [nombrePokemon=" + nombrePokemon + ", tipoPrimPokemon=" + tipoPrimPokemon + ", tipoSecPokemon="
				+ tipoSecPokemon + ", pesoPokemon=" + pesoPokemon + ", alturaPokemon=" + alturaPokemon + ", numPokemon="
				+ numPokemon + ", evoPokemon=" + evoPokemon + ", preevoPokemon=" + preevoPokemon + ", vidaBase=" + vidaBasePokemon + "]";
	}


	
	public final BooleanProperty evoPokemonProperty() {
		return this.evoPokemon;
	}
	
	public final boolean isEvoPokemon() {
		return this.evoPokemonProperty().get();
	}
	
	public final void setEvoPokemon(final boolean evoPokemon) {
		this.evoPokemonProperty().set(evoPokemon);
	}
	
	public final BooleanProperty preevoPokemonProperty() {
		return this.preevoPokemon;
	}
	
	public final boolean isPreevoPokemon() {
		return this.preevoPokemonProperty().get();
	}
	
	public final void setPreevoPokemon(final boolean preevoPokemon) {
		this.preevoPokemonProperty().set(preevoPokemon);
	}
	

	public final ObjectProperty<Image> imagenPokemonProperty() {
		return this.imagenPokemon;
	}
	

	public final Image getImagenPokemon() {
		return this.imagenPokemonProperty().get();
	}
	

	public final void setImagenPokemon(final Image imagenPokemon) {
		this.imagenPokemonProperty().set(imagenPokemon);
	}

	public final IntegerProperty vidaBasePokemonProperty() {
		return this.vidaBasePokemon;
	}
	

	public final int getVidaBasePokemon() {
		return this.vidaBasePokemonProperty().get();
	}
	

	public final void setVidaBasePokemon(final int vidaBasePokemon) {
		this.vidaBasePokemonProperty().set(vidaBasePokemon);
	}
	
		
	
}
